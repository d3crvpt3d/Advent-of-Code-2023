import java.util.Scanner;

public class ChangeInput {
    
    int y = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = 0;
        int steps = 64;

        while (sc.hasNextLine()) {
            String s = sc.nextLine();

            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '#'){
                    if(x+y >= steps){a++;}
                }
            }
        }
    }

}
