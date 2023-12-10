import java.util.Scanner;

public class FillAlgorithm {

    public static void main(String[] args) {

        String[][] pipes = new String[140][140];

        Scanner scanner = new Scanner(System.in);

        //set pipes direction
        while (scanner.hasNextLine()) {
            
            String[] line = scanner.nextLine().split("-");

            pipes[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = line[2];

        }
        scanner.close();







        
    }

}