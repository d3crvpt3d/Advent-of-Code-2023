import java.util.Scanner;

class Vector {

    char[][] matrix; // pointer to matrix
    int x;
    int y;
    int[] prev = new int[2];
    String[][] direction;
    int[][] lut; // 1 = pipe, 2 = right, 0 = left

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
                    direction[x][y] = "→";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x++;
                    
                }else{
                    direction[this.x][this.y] = "↑";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y--;
                    
                }

                break;
            case 'J':
                if(this.prev[0] == this.x && this.prev[1] == this.y-1){
                    direction[this.x][this.y] = "←";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x--;
                    
                }else{
                    direction[this.x][this.y] = "↑";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y--;
                    
                }
                
                break;
            case '7':
                if(this.prev[0] == this.x && this.prev[1] == this.y+1){
                    direction[this.x][this.y] = "←";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x--;
                    
                }else{
                    direction[this.x][this.y] = "↓";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.y++;
                    
                }
                
                break;
            case 'F':
                if(this.prev[0] == this.x && this.prev[1] == this.y+1){
                    direction[this.x][this.y] = "→";
                    this.prev[0] = this.x;
                    this.prev[1] = this.y;
                    this.x++;
                    
                }else{
                    direction[this.x][this.y] = "↓";
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

    static int[][] lut = new int[140][140]; //pipe lut

    static char[][] matrix = new char[140][140]; //input as matrix

    static String[][] direction = new String[140][140]; //direction array

    //right -> 2 left -> 3
    private static void updateDirection(String s, int x, int y){
        int tile_x = 0;
        int tile_y = 0;

        switch (s) {
            case "↑":
                tile_x = Math.min(139, x+1);
                tile_y = y;
                while (lut[tile_x][tile_y] != 1) {
                    lut[tile_x][tile_x] = 2;
                    tile_x++;
                    if(tile_x == lut.length){break;}
                }
                break;
            case "↓":
                tile_x = Math.max(0, x-1);
                tile_y = y;
                while (lut[tile_x][tile_y] != 1) {
                    lut[tile_x][tile_x] = 2;
                    tile_x--;
                    if(tile_x < 0){break;}
                }
                break;
            case "→":
                tile_x = x;
                tile_y = Math.min(139, y+1);
                while (lut[tile_x][tile_y] != 1) {
                    lut[tile_x][tile_x] = 2;
                    tile_y++;
                    if(tile_y == lut.length){break;}
                }
                break;
            case "←":
                tile_x = x;
                tile_y = Math.max(0, y-1);
                while (lut[tile_x][tile_y] != 1) {
                    lut[tile_x][tile_x] = 2;
                    tile_y--;
                    if(tile_y < 0){break;}
                }
                break;
            
            default:
                break;
            }
    }
 


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

        getMaxDistanceFromAnimal(start); // fill lut with pipes

        //DEBUG
        //print direction array
        for (int y = 0; y < direction.length; y++) {
            for (int x = 0; x < direction[0].length; x++) {
                System.err.print(direction[x][y]);
            }
            System.err.println();
        }
        //DEBUG

        //fill lut by looking up direction array
        for (int y = 0; y < args.length; y++) {
            for (int x = 0; x < args.length; x++) {
                if(lut[x][y] == 1){
                    updateDirection(direction[x][y], x, y);
                }
            }
        }


        for (int y = 0; y < lut.length; y++) {
            for (int x = 0; x < lut[0].length; x++) {
                System.out.print(lut[x][y]);
            }
            System.out.println();
        }

    }




    private static void getMaxDistanceFromAnimal(Vector start) throws InterruptedException{

        Vector pos1 = new Vector(start.x, start.y, matrix, lut, direction);

        //first Step
        pos1.takeFirstStep();

        lut[pos1.x][pos1.y] = 1;

        //first Step end

        //128 88 start
        
        while (!(pos1.x == 88 && pos1.y == 128)) {

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