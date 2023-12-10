import java.util.Scanner;

class Vector {

    char[][] matrix; // pointer to matrix
    int x;
    int y;
    int[] prev = new int[2];

    public Vector(int x, int y, char[][] matrix){
        this.x = x;
        this.y = y;
        this.prev[0] = -1;
        this.prev[1] = -1;
        this.matrix = matrix;
    }

    //biased
    public void takeFirstStep(){

        if(prev[0] == -1){
            this.y--;
            prev[0] = this.x;
            prev[1] = this.y+1;
        }else{
            this.y++;
            prev[0] = this.x;
            prev[1] = this.y-1;
        }

    }


    public void takeNextStep(){

        //look at pipe
        switch (matrix[this.y][this.x]) { // matrix[y][x]

            case '|':
                if(this.prev[0] == this.x && this.prev[1] == this.y-1){
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y++;
                }else{
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y--;
                }

                break;
            case '-':
                if(this.prev[0] == this.x-1 && this.prev[1] == this.y){
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x++;
                }else{
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x--;
                }
                
                break;
            case 'L':
                if(this.prev[0] == this.x && this.prev[1] == this.y-1){
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x++;
                }else{
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y--;
                }

                break;
            case 'J':
                if(this.prev[0] == this.x && this.prev[1] == this.y-1){
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x--;
                }else{
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y--;
                }
                
                break;
            case '7':
                if(this.prev[0] == this.x && this.prev[1] == this.y+1){
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x--;
                }else{
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y++;
                }
                
                break;
            case 'F':
                if(this.prev[0] == this.x && this.prev[1] == this.y+1){
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x++;
                }else{
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y++;
                }

                break;

            default:
                System.err.println("WRONG CHAR");
                return;
        }



    }
    
}

public class First extends Thread{

    static char[][] matrix = new char[140][140];
 
    public static void main(String[] args) throws InterruptedException {
        
        Scanner scanner = new Scanner(System.in);

        int currLine = 0;

        while (scanner.hasNextLine()) {
            
            matrix[currLine++] = scanner.nextLine().toCharArray();
        }
        scanner.close();

        Vector start = getS();

        System.out.println(getMaxDistanceFromAnimal(start));

    }

    private static int getMaxDistanceFromAnimal(Vector start) throws InterruptedException{

        int length = 0;

        Vector pos1 = new Vector(start.x, start.y, matrix);
        Vector pos2 = new Vector(start.x, start.y, matrix);

        //first Step
        pos1.takeFirstStep();

        pos2.prev[0] = pos1.x; // block segment of pos 1 for pos2
        pos2.prev[1] = pos1.y;

        pos2.takeFirstStep();

        length++;

        //first Step end

        while (!(pos1.x == pos2.x && pos1.y == pos2.y)) {

            //Thread.sleep(100);
            
           
            pos1.takeNextStep();

            if(pos1.x == pos2.x && pos1.y == pos2.y){
                break;
            }

            pos2.takeNextStep();
        
            length++;
        }


        return length;

    }

    private static Vector getS(){

        for (int y = 0; y < matrix.length; y++) {
            
            for (int x = 0; x < matrix[0].length; x++) {
                if(matrix[y][x] == 'S'){
                    return new Vector(x, y, matrix);
                }
            }

        }

        System.err.println("no S");

        return null;
    }


}