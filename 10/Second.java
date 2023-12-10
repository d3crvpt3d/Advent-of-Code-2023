import java.util.Scanner;

class Vector {

    char[][] matrix; // pointer to matrix
    int x;
    int y;
    int[] prev = new int[2];
    String direction;

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

    private void updateDirection(String c){
        switch (c) {
            case "north":
                
                break;
            case "east":
            
                break;
            case "south":
            
                break;
            case "west":
            
                break;
                
            default:
                System.err.println("Direction not defined:"+direction);
                break;
        }
    }

    //changed to print direction of outside or inside
    public void takeNextStep(){

        //look at pipe
        switch (matrix[this.y][this.x]) { // matrix[y][x]

            case '|':
                if(this.prev[0] == this.x && this.prev[1] == this.y-1){
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y++;
                    updateDirection("south");
                }else{
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y--;
                    updateDirection("north");
                }

                break;
            case '-':
                if(this.prev[0] == this.x-1 && this.prev[1] == this.y){
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x++;
                    updateDirection("east");
                }else{
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x--;
                    updateDirection("west");
                }
                
                break;
            case 'L':
                if(this.prev[0] == this.x && this.prev[1] == this.y-1){
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x++;
                    updateDirection("east");
                }else{
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y--;
                    updateDirection("north");
                }

                break;
            case 'J':
                if(this.prev[0] == this.x && this.prev[1] == this.y-1){
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x--;
                    updateDirection("west");
                }else{
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y--;
                    updateDirection("north");
                }
                
                break;
            case '7':
                if(this.prev[0] == this.x && this.prev[1] == this.y+1){
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x--;
                    updateDirection("west");
                }else{
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y++;
                    updateDirection("south");
                }
                
                break;
            case 'F':
                if(this.prev[0] == this.x && this.prev[1] == this.y+1){
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x++;
                    updateDirection("east");
                }else{
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y++;
                    updateDirection("south");
                }

                break;

            default:
                System.err.println("WRONG CHAR");
                return;
        }

        //print for fill algorithmus
        System.out.println(this.prev[0]+" "+this.prev[1]+" "+this.direction);


    }
    
}

public class Second extends Thread{

    static char[][] matrix = new char[140][140];
 
    public static void main(String[] args) throws InterruptedException {
        
        Scanner scanner = new Scanner(System.in);

        int currLine = 0;

        while (scanner.hasNextLine()) {
            
            matrix[currLine++] = scanner.nextLine().toCharArray();
        }
        scanner.close();

        Vector start = getS();

        getMaxDistanceFromAnimal(start);

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