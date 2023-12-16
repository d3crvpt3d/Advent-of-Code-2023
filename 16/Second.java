import java.util.ArrayList;
import java.util.Scanner;

public class Second {
    
    public static void main(String[] args) {

        System.out.println("Start");
        
        new Second();

    }

    ArrayList<ArrayList<Character>> inputField = new ArrayList<ArrayList<Character>>();
    boolean[][][] resultField;
    int size_x;
    int size_y;

    public Second(){

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            char[] cA = scanner.nextLine().toCharArray();
            
            ArrayList<Character> lineList = new ArrayList<Character>();

            for (char c : cA) {
                lineList.add(c);
            }

            inputField.add(lineList);

        }
        scanner.close();

        
        size_x = inputField.get(0).size();
        size_y = inputField.size();

//        System.err.println(size_x+" "+size_y); //DEBUG

        resultField = new boolean[inputField.size()][inputField.get(0).size()][4];

        int whole_output = 0;
        int ax_max = 0;
        int ay_max = 0;
        //top
        for(int ax = 0; ax < size_x; ax++){
            int ay = 0;
            new Tracer(ax, ay, '↓', this);

            int output = 0;
            //count lighted fields
            for (int l = 0; l < resultField.length; l++) {
                for (int m = 0; m < resultField[0].length; m++) {
                    if( resultField[l][m][0] || resultField[l][m][1] ||
                        resultField[l][m][2] || resultField[l][m][3]){
                        output++;
                        resultField[l][m][0] = false;
                        resultField[l][m][1] = false;
                        resultField[l][m][2] = false;
                        resultField[l][m][3] = false;
                    }
                }
            }
            if(output > whole_output){
                whole_output = output;
                ax_max = ax;
                ay_max = ay;
            }
        }
        System.err.println("Top DONE");
        //bot
        for(int ax = 0; ax < size_x; ax++){
            int ay = size_y-1;
            new Tracer(ax, ay, '↑', this);

            int output = 0;
            //count lighted fields
            for (int l = 0; l < resultField.length; l++) {
                for (int m = 0; m < resultField[0].length; m++) {
                    if( resultField[l][m][0] || resultField[l][m][1] ||
                        resultField[l][m][2] || resultField[l][m][3]){
                        output++;
                        resultField[l][m][0] = false;
                        resultField[l][m][1] = false;
                        resultField[l][m][2] = false;
                        resultField[l][m][3] = false;
                    }
                }
            }
            if(output > whole_output){
                whole_output = output;
                ax_max = ax;
                ay_max = ay;
            }
        }
        System.err.println("Bot DONE");
        //left
        for(int ay = 0; ay < size_x; ay++){
            int ax = 0;
            new Tracer(ax, ay, '→', this);

            int output = 0;
            //count lighted fields
            for (int l = 0; l < resultField.length; l++) {
                for (int m = 0; m < resultField[0].length; m++) {
                    if( resultField[l][m][0] || resultField[l][m][1] ||
                        resultField[l][m][2] || resultField[l][m][3]){
                        output++;
                        resultField[l][m][0] = false;
                        resultField[l][m][1] = false;
                        resultField[l][m][2] = false;
                        resultField[l][m][3] = false;
                    }
                }
            }
            if(output > whole_output){
                whole_output = output;
                ax_max = ax;
                ay_max = ay;
            }
        }
        System.err.println("Left DONE");
        //right
        for(int ay = 0; ay < size_x; ay++){
            int ax = size_x-1;
            new Tracer(ax, ay, '←', this);

            int output = 0;
            //count lighted fields
            for (int l = 0; l < resultField.length; l++) {
                for (int m = 0; m < resultField[0].length; m++) {
                    if( resultField[l][m][0] || resultField[l][m][1] ||
                        resultField[l][m][2] || resultField[l][m][3]){
                        output++;
                        resultField[l][m][0] = false;
                        resultField[l][m][1] = false;
                        resultField[l][m][2] = false;
                        resultField[l][m][3] = false;
                    }
                }
            }
            if(output > whole_output){
                whole_output = output;
                ax_max = ax;
                ay_max = ay;
            }
        }
        System.err.println("Right DONE");

        System.out.println("Output: "+whole_output+" with: "+ax_max+" "+ay_max);
    }

    public char getInputField(int x, int y) {
        return inputField.get(y).get(x);
    }

    public boolean getResultField(int x, int y, int direction) {
        return resultField[y][x][direction];
    }

    //change result field
    public synchronized void setResultField(int x, int y, int direction) {
        this.resultField[y][x][direction] = true;
    }

    public int getField_size_y(){
        return size_y;
    }

    public int getField_size_x(){
        return size_x;
    }

}

/**
 * Tracer
 */
class Tracer implements Runnable{

    private int x;
    private int y;
    private char c;
    private Second second;

    public Tracer(int x, int y, char c, Second second){

        this.x = x;
        this.y = y;
        this.c = c;
        this.second = second;

        this.run();
    }

    //parallel trace field // update coords //check out of bounds
    @Override
    public void run() {

        this.c = getDirection();

        whiledings:
        while (c != 'x') {

            switch (c) {
                case '←':
                    x--;
                    if(outOfBounds(x, y)){
                        break whiledings;
                    }
                    this.c = getDirection();
                    break;
                case '→':
                    x++;
                    if(outOfBounds(x, y)){
                        break whiledings;
                    }
                    this.c = getDirection();
                    break;
                case '↑':
                    y--;
                    if(outOfBounds(x, y)){
                        break whiledings;
                    }
                    this.c = getDirection();
                    break;
                case '↓':
                    y++;
                    if(outOfBounds(x, y)){
                        break whiledings;
                    }
                    this.c = getDirection();
                    break;
                default:
                    break;
            }
        }
    }
    
    private char getDirection(){

//        System.err.println(this+":"+x+" "+y+" "+c); //DEBUG

        int tmp_dir = 0;

        //check if duplicate field and set field direction true
        switch (c) {
            case '←':
                tmp_dir = 0;
                if(second.getResultField(x, y, 0)){
                    return 'x';
                }
                second.setResultField(x, y, 0);
                break;
            case '→':
                tmp_dir = 1;
                 if(second.getResultField(x, y, 1)){
                    return 'x';
                }
                second.setResultField(x, y, 1);
                break;
            case '↑':
                tmp_dir = 2;
                 if(second.getResultField(x, y, 2)){
                    return 'x';
                }
                second.setResultField(x, y, 2);
                break;
            case '↓':
                tmp_dir = 3;
                 if(second.getResultField(x, y, 3)){
                    return 'x';
                }
                second.setResultField(x, y, 3);
                break;
            default:
                break;
        }

        //if not duplicate

        switch (second.getInputField(x, y)) {
            case '.':
                return c;

            case '|':
            
                switch (tmp_dir) {
                    case 0:
                        new Tracer(x, y, '↓', second);
                        return '↑';
                    case 1:
                        new Tracer(x, y, '↓', second);
                        return '↑';
                    case 2:
                        return c;
                    case 3:
                        return c;
                    default:
                        break ;
                }
            
            case '-':
            
                switch (tmp_dir) {
                    case 0:
                        return c;
                    case 1:
                        return c;
                    case 2:
                        new Tracer(x, y, '←', second);
                        return '→';
                    case 3:
                        new Tracer(x, y, '←', second);
                        return '→';
                    default:
                        break ;
                }

            case '/':
            
                switch (tmp_dir) {
                    case 0:
                        return '↓';
                    case 1:
                        return '↑';
                    case 2:
                        return '→';
                    case 3:
                        return '←';
                    default:
                        break ;
                }
            
            case '\\':
            
                switch (tmp_dir) {
                    case 0:
                        return '↑';
                    case 1:
                        return '↓';
                    case 2:
                        return '←';
                    case 3:
                        return '→';
                    default:
                        break;
                }
            
            default:
                break;
        }
//        System.err.println("ERROR IN SWITCHING INPUT");
        return 'x';
    }

    private boolean outOfBounds(int x, int y){
        if( x < 0 || x >= second.getField_size_x() ||
            y < 0 || y >= second.getField_size_y()){
            return true;
        }
        return false;
    }
}