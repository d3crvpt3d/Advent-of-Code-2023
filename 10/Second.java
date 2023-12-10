import java.util.Scanner;

class Vector {

    char[][] matrix; // pointer to matrix
    int x;
    int y;
    int[] prev = new int[2];
    int[][] lut; // 1 = pipe, 2 = right, 0 = left
    String[][] direction;

    public Vector(int x, int y, char[][] matrix, int[][] lut, String[][] direction){
        this.x = x;
        this.y = y;
        this.prev[0] = -1;
        this.prev[1] = -1;
        this.matrix = matrix;
        this.lut = lut;
        this.direction = direction;
    }

    //biased
    public void takeFirstStep(){

        direction[this.x][this.y] = "↑";
        prev[0] = this.x;
        prev[1] = this.y;
        this.y--;

    }

    //changed to print direction of outside or inside
    public void takeNextStep(){

        //look at pipe
        switch (matrix[this.y][this.x]) { // matrix[y][x]

            case '|':
                if(this.prev[0] == this.x && this.prev[1] == this.y-1){
                    direction[this.x][this.y] = "↓";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y++;
                    
                }else{
                    direction[this.x][this.y] = "↑";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y--;
                    
                }

                break;
            case '-':
                if(this.prev[0] == this.x-1 && this.prev[1] == this.y){
                    direction[this.x][this.y] = "→";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x++;
                    
                }else{
                    direction[this.x][this.y] = "←";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x--;
                    
                }
                
                break;
            case 'L':
                if(this.prev[0] == this.x && this.prev[1] == this.y-1){
                    direction[this.x][this.y] = "↓→";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x++;
                    
                }else{
                    direction[this.x][this.y] = "←↑";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y--;
                    
                }

                break;
            case 'J':
                if(this.prev[0] == this.x && this.prev[1] == this.y-1){
                    direction[this.x][this.y] = "↓←";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x--;
                    
                }else{
                    direction[this.x][this.y] = "→↑";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y--;
                    
                }
                
                break;
            case '7':
                if(this.prev[0] == this.x && this.prev[1] == this.y+1){
                    direction[this.x][this.y] = "↑←";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x--;
                    
                }else{
                    direction[this.x][this.y] = "→↓";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y++;
                    
                }
                
                break;
            case 'F':
                if(this.prev[0] == this.x && this.prev[1] == this.y+1){
                    direction[this.x][this.y] = "↑→";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x++;
                    
                }else{
                    direction[this.x][this.y] = "←↓";
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















public class Second extends Thread{

    private static int size_x = 140;
    private static int size_y = 140;

    static int[][] lut = new int[size_x][size_y]; //pipe lut

    static char[][] matrix = new char[size_y][size_x]; //input as matrix

    static String[][] direction = new String[size_x][size_y];

    
 


/*
 * ███▄ ▄███▓ ▄▄▄       ██▓ ███▄    █ 
 ▓██▒▀█▀ ██▒▒████▄    ▓██▒ ██ ▀█   █ 
 ▓██    ▓██░▒██  ▀█▄  ▒██▒▓██  ▀█ ██▒
 ▒██    ▒██ ░██▄▄▄▄██ ░██░▓██▒  ▐▌██▒
 ▒██▒   ░██▒ ▓█   ▓██▒░██░▒██░   ▓██░
 ░ ▒░   ░  ░ ▒▒   ▓▒█░░▓  ░ ▒░   ▒ ▒ 
 ░  ░      ░  ▒   ▒▒ ░ ▒ ░░ ░░   ░ ▒░
 ░      ░     ░   ▒    ▒ ░   ░   ░ ░ 
        ░         ░  ░ ░           ░ 
 */
                                      
    
    public static void main(String[] args) throws InterruptedException {
        
        Scanner scanner = new Scanner(System.in);

        int currLine = 0;

        while (scanner.hasNextLine()) {
            
            matrix[currLine++] = scanner.nextLine().toCharArray();
        }
        scanner.close();

        Vector start = getS();

        iteratePipe(start); // fill lut with pipes

        // fill empty space with 2 if right of pipe POV
        for(int y = 0; y < lut[0].length; y++){
            for (int x = 0; x < lut.length; x++) {
                if(lut[x][y] == 1){
                    updateDirection(direction[x][y], x, y);
                }
            }
        }

        int output = 0;

        //print output
        for (int y = 0; y < lut.length; y++) {
            for (int x = 0; x < lut[0].length; x++) {
                //System.err.print(lut[x][y]);
                if(lut[x][y] == 2){
                    output++;//count for output
                }
            }
            //System.err.println();
        }

        System.out.println(output);

    }

    //right -> 2
    private static void updateDirection(String s, int tile_x_main, int tile_y_main){

        //System.err.println("updateDirection called with: "+s+" "+tile_x_main+" "+tile_y_main);

        char[] result = s.toCharArray();

        for(char c : result){

            int tile_x = tile_x_main;
            int tile_y = tile_y_main;

            if(c == '↑'){
                if(tile_x == lut.length-1){break;} // out of bounds
                tile_x++;
                while(lut[tile_x][tile_y] != 1){
                    lut[tile_x][tile_y] = 2;
                    tile_x++;
                    if(tile_x == lut.length){break;}
                }
            }else if(c == '↓'){
                if(tile_x == 0){break;} // out of bounds
                tile_x--;
                while (lut[tile_x][tile_y] != 1){
                    lut[tile_x][tile_y] = 2;
                    tile_x--;
                    if(tile_x < 0){break;}
                }
            }else if(c == '→'){
                if(tile_y == lut.length-1){break;} // out of bounds
                tile_y++;
                while (lut[tile_x][tile_y] != 1){
                    lut[tile_x][tile_y] = 2;
                    tile_y++;
                    if(tile_y == lut.length){break;}
                }
            }else if(c == '←'){
                if(tile_y == 0){break;} // out of bounds
                tile_y--;
                while (lut[tile_x][tile_y] != 1){
                    lut[tile_x][tile_y] = 2;
                    tile_y--;
                    if(tile_y < 0){break;}
                }
            }
            //elif end
        }
    }


    private static void iteratePipe(Vector start) throws InterruptedException{

        Vector pos1 = new Vector(start.x, start.y, matrix, lut, direction);

        //first Step
        pos1.takeFirstStep();

        lut[pos1.x][pos1.y] = 1;

        //first Step end

        //128 88 start
        
        while (!(pos1.x == start.x && pos1.y == start.y)) {

            //Thread.sleep(100);
            pos1.takeNextStep();
            lut[pos1.x][pos1.y] = 1;
        }

    }

    private static Vector getS(){

        for (int y = 0; y < matrix.length; y++) {
            
            for (int x = 0; x < matrix[0].length; x++) {
                if(matrix[y][x] == 'S'){
                    return new Vector(x, y, matrix, lut, direction);
                }
            }

        }

        System.err.println("no S");

        return null;
    }


}