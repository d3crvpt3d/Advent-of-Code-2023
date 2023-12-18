import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

class Charac implements Comparable<Charac>{

    int cardValueFromChar(char a){
        int out;
        
        switch (a) {
            case 'A':
                out = 13;
                break;
            case 'K':
                out = 12;
                break;
            case 'Q':
                out = 11;
                break;
            case 'J':
                out = 1;
                break;
            case 'T':
                out = 10;
                break;
            case '9':
                out = 9;
                break;
            case '8':
                out = 8;
                break;
            case '7':
                out = 7;
                break;
            case '6':
                out = 6;
                break;
            case '5':
                out = 5;
                break;
            case '4':
                out = 4;
                break;
            case '3':
                out = 3;
                break;
            case '2':
                out = 2;
                break;
            default:
            System.err.println("WRONG CHAR");
            out = -1;
        }
        return out;
    }

    char c;
    public Charac(char c){
        this.c = c;
    }

    @Override
    public int compareTo(Charac o) {
        return cardValueFromChar(this.c) - cardValueFromChar(o.c);
    }

    
}

class Hand implements Comparable<Hand>{

    String cards;
    int bid;
    String cardsSorted;
    int cardRank;

    
    
    public Hand(String cards, int bid){

        Charac[] charak = new Charac[5];

        for (int i = 0; i < cards.length(); i++) {
            charak[i] = new Charac(cards.charAt(i));
        }

        String tmp = "";

        Arrays.sort(charak);

        for (int i = 0; i < 5; i++) {
            tmp = tmp.concat(String.valueOf(charak[i].c));
        }

        this.cardsSorted = String.valueOf(tmp);

        this.cards = cards;
        this.bid = bid;
    }

    @Override
    public int compareTo(Hand o) {
        return this.cardRank - o.cardRank;
    }

}


public class Second {

    private static int cardValueFromChar(char a){
        int out;
        
        switch (a) {
            case 'A':
                out = 13;
                break;
            case 'K':
                out = 12;
                break;
            case 'Q':
                out = 11;
                break;
            case 'J':
                out = 1;
                break;
            case 'T':
                out = 10;
                break;
            case '9':
                out = 9;
                break;
            case '8':
                out = 8;
                break;
            case '7':
                out = 7;
                break;
            case '6':
                out = 6;
                break;
            case '5':
                out = 5;
                break;
            case '4':
                out = 4;
                break;
            case '3':
                out = 3;
                break;
            case '2':
                out = 2;
                break;
            default:
            System.err.println("WRONG CHAR");
            out = -1;
        }
        return out;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Hand> inputList = new ArrayList<Hand>();
        
        while (scanner.hasNextLine()) {
            
            String line = scanner.nextLine();

            inputList.add(new Hand(line.split("\\s+")[0], Integer.parseInt(line.split("\\s+")[1]) ));

        }
        scanner.close();


        Hand[] sortedArray = new Hand[inputList.size()];



        for (int i = 0; i < sortedArray.length; i++) {
            sortedArray[i] = inputList.get(i);
        }
        

        //sort each list by highest cards
        Arrays.sort(sortedArray, (a, b) -> {
            for (int index = 0; index < 5; index++) {
                if(cardValueFromChar(a.cards.charAt(index)) != cardValueFromChar(b.cards.charAt(index))){
                    //System.err.println(a.cards.charAt(index)+" a;b "+b.cards.charAt(index)+" = "+ (Integer.signum(cardValueFromChar(a.cards.charAt(index))-cardValueFromChar(b.cards.charAt(index)))));
                    return Integer.signum(cardValueFromChar(a.cards.charAt(index))-cardValueFromChar(b.cards.charAt(index)));
                }
            }
            System.err.println("a==b");
            return 0;
        });

        for (Hand hand : sortedArray) {
            hand.cardRank = getHandRank(hand.cardsSorted);
        }


        // sort by rank    
        Arrays.sort(sortedArray);
        //is now sorted by rank

        for (int i = 0; i < sortedArray.length; i++) {
            System.err.println(sortedArray[i].cards+" hat Bid:"+sortedArray[i].bid+" und ist "+ getNameOfHand(sortedArray[i].cardRank)+" Sorted:"+sortedArray[i].cardsSorted);
        }

        long output = 0;

        for (int i = 0; i < sortedArray.length; i++) {
            output += (i+1) * sortedArray[i].bid;
        }

        System.out.println("Output: "+output);

    }

    private static String getNameOfHand(int n){
    
        switch (n) {
            case 6:
                return "Five of a Kind";
            case 5:
                return "Four of a Kind";
            case 4:
                return "Full House";
            case 3:
                return "Three of a Kind";
            case 2:
                return "Two Pairs";
        
            default:
                return n+1+" Card";
        }

    }

    private static int getHandRank(String hand){



        if (getMaxSame(hand) == 5) {// five
            return 6;
        }else if(getMaxSame(hand) == 4){// four
            return 5;
        }else if(numberDiffCards(hand) == 2 && getMaxSame(hand) == 3){ // full house
            return 4;
        }else if(getMaxSame(hand) == 3){
            return 3;
        }else if(getMaxSame(hand) == 2 && numberDiffCards(hand) == 3){ // two pairs
            return 2;
        }else if(getMaxSame(hand) == 2){ // one pair
            return 1;
        }

        return 0;
    }

    private static int numberDiffCards(String cardtmp){

        String cards = cardtmp;

        int i = 0;

        while (cards.charAt(i) == 'J') {
            i++;
            if(i == 5){
                return 1;
            }
        }

        cards = cards.replace('J', cards.charAt(i));

        boolean[] barr = new boolean[13];

        //fill slot from char
        for (int j = 0; j < 5; j++) {
            barr[cardValueFromChar(cards.charAt(j))-1] = true; // char values = [1-13]
        }

        i = 0;

        //count array for filled slots
        for (boolean b : barr) {
            if(b){
                i++;
            }
        }

        return i;
    }

    private static int getMaxSame(String cardtmp){

        String cards = cardtmp;

        int[] arr = new int[13];
        int max = 0;

        for (int i = 0; i < cards.length(); i++) {
            arr[cardValueFromChar(cards.charAt(i))-1]++;
        }

        for (int i = 1; i < arr.length; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
        }


        return max+arr[0]; // return max value

    }

    
}