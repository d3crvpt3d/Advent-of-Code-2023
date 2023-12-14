import java.util.Scanner;

public class Second {

    public static void main(String[] args) {

        int output = 0;

        Scanner scanner = new Scanner(System.in);

        int[] cardCount = new int[186];
        int[] match = new int[186];

        cardCount[0] = 1;

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            String line_tmp = line;

            int cardID = Integer.parseInt(line_tmp.split(":")[0].split("Card")[1].trim())-1;

            match[cardID] = getMatches(getYourNumbersOfCard(line), getWinningNumbersofCard(line));
        }
        scanner.close();

        //add +1 for the next x cards
        for (int i = 0; i < cardCount.length; i++) {
            for (int j = 0; j < cardCount[i]; j++) {
                for (int k = 0; k < match[i]; k++) {
                    if(cardCount[i+k+1]++ < cardCount.length){
                        cardCount[i+k+1]++;
                    }
                }
            }
        }

        for (int i = 0; i < cardCount.length; i++) {
            output += cardCount[i];
        }

        System.out.println(output);
    }

    static int[] getYourNumbersOfCard(String s){

        s = s.split(":")[1];
        
        String s0 = s.split("\\|")[0];

        int[] out = new int[10];

        int n = -1;
        for (String string: s0.split("\\s+")) {
            try {
                out[n] = Integer.parseInt(string);
            } catch (NumberFormatException e) {
                ;
            }
            n++;
            if (n == 10) {
                break;
            }
        }

        return out;
    }

    static int[] getWinningNumbersofCard(String s){

        s = s.split(":")[1];
        
        String s1 = s.split("\\|")[1];

        int[] out = new int[25];

        int n = -1;
        for (String string: s1.split("\\s+")) {
           try {
                out[n] = Integer.parseInt(string);
            } catch (NumberFormatException e) {
                ;
            }
            n++;
            if (n == 25) {
                break;
            }
        }

        return out;
    }

    static int getMatches(int[] yourNumbers, int[] winningNumbers){

        boolean[] map = new boolean[100]; //(not hash) map

        int matches = 0;

        for (int i : winningNumbers) {
            map[i] = true;
        }

        for (int j : yourNumbers) {
            if(map[j]){
                matches++;
            }
        }

        return matches;
    }

    static int cardPoints(int matches){
        if(matches == 0){return 0;}

        return (int) Math.pow(2, matches-1);
    }

}