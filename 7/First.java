import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

class Hand implements Comparable<Hand>{

    String cards;
    int bid;
    String cardsSorted;
    int cardRank;
    
    public Hand(String cards, int bid){


        char[] tmp = cards.toCharArray();

        Arrays.sort(tmp);

        this.cardsSorted = String.valueOf(tmp);

        this.cards = cards;
        this.bid = bid;
    }

    @Override
    public int compareTo(Hand o) {
        return this.cardRank - o.cardRank;
    }

}


public class First {

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
                out = 10;
                break;
            case 'T':
                out = 9;
                break;
            case '9':
                out = 8;
                break;
            case '8':
                out = 7;
                break;
            case '7':
                out = 6;
                break;
            case '6':
                out = 5;
                break;
            case '5':
                out = 4;
                break;
            case '4':
                out = 3;
                break;
            case '3':
                out = 2;
                break;
            case '2':
                out = 1;
                break;
            default:
            System.err.println("WRONG CHAR");
            out = -1;
        }
        return out;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Hand> inputList = new ArrayList<Hand>(1000);
        
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
                    //System.err.println(a.cards.charAt(index)+" a;b "+b.cards.charAt(index));
                    return cardValueFromChar(a.cards.charAt(index))-cardValueFromChar(b.cards.charAt(index));                    }
                }
            System.err.println("error in sort");
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
            System.err.println(output);
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


        char card1 = hand.charAt(0);
        char card2;

        // Full House return 4
        int fh_counter = 1;
        int firstend;
        while(hand.charAt(fh_counter) == card1){

            if (fh_counter == 4) {
                return 6; // five of a kind
            }
            fh_counter++;
        }
        firstend = fh_counter;
        if(fh_counter == 4){return 5;} // four of a kind
        card2 = hand.charAt(fh_counter);
        while (hand.charAt(fh_counter) == card2) {
            if(fh_counter-firstend == 3){
                return 5; // four of a kind
            }
            if(fh_counter == 4){
                return 4; // full house
            }
            fh_counter++;
        }


        int tempLength = 0;        
        int maxLength = 0;
        int numberofpairs = 0;

        for (int i = 1; i < hand.length(); i++) {

            if(hand.charAt(i) == hand.charAt(i-1)){
                tempLength = 1+tempLength;
                numberofpairs++;
            }else{
                tempLength = 0;
            }

            if(tempLength >= maxLength){
                maxLength = tempLength;
            }
        }

        if(maxLength == 2){
            return 3; // three of a kind
        }

        if (numberofpairs == 2) {
            return 2; // two pairs
        }

        return maxLength; // 0: highCard

    }

}