import java.util.Scanner;

public class First {

    public static void main(String[] args) {

        int output = 0;

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            output += cardPoints(getMatches(getYourNumbersOfCard(line), getWinningNumbersofCard(line)));

        }

        System.out.println(output);

        scanner.close();
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