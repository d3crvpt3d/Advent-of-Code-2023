import java.util.ArrayList;
import java.util.Scanner;

public class First {
    
    public static void main(String[] args) {

        System.out.println("Start");
        
        new First();

    }

    ArrayList<ArrayList<Character>> inputField = new ArrayList<ArrayList<Character>>();
    boolean[][][] resultField;
    int size_x;
    int size_y;

    public First(){

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

        System.err.println(size_x+" "+size_y); //DEBUG

        resultField = new boolean[inputField.size()][inputField.get(0).size()][4];

        new Tracer(0, 0, '→', this);

        int output = 0;
        //count lighted fields
        for (boolean[][] b2 : resultField) {
            for (boolean[] b1 : b2) {
                if(b1[0] || b1[1] || b1[2] || b1[3]){
                    output++;
                }
            }
        }

        System.out.println("Output: "+output);
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
    private First first;

    public Tracer(int x, int y, char c, First first){

        this.x = x;
        this.y = y;
        this.c = c;
        this.first = first;

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

        System.err.println(this+":"+x+" "+y+" "+c); //DEBUG

        int tmp_dir = 0;

        //check if duplicate field and set field direction true
        switch (c) {
            case '←':
                tmp_dir = 0;
                if(first.getResultField(x, y, 0)){
                    return 'x';
                }
                first.setResultField(x, y, 0);
                break;
            case '→':
                tmp_dir = 1;
                 if(first.getResultField(x, y, 1)){
                    return 'x';
                }
                first.setResultField(x, y, 1);
                break;
            case '↑':
                tmp_dir = 2;
                 if(first.getResultField(x, y, 2)){
                    return 'x';
                }
                first.setResultField(x, y, 2);
                break;
            case '↓':
                tmp_dir = 3;
                 if(first.getResultField(x, y, 3)){
                    return 'x';
                }
                first.setResultField(x, y, 3);
                break;
            default:
                break;
        }

        //if not duplicate

        switch (first.getInputField(x, y)) {
            case '.':
                return c;

            case '|':
            
                switch (tmp_dir) {
                    case 0:
                        new Tracer(x, y, '↓', first);
                        return '↑';
                    case 1:
                        new Tracer(x, y, '↓', first);
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
                        new Tracer(x, y, '←', first);
                        return '→';
                    case 3:
                        new Tracer(x, y, '←', first);
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

        return 'x';
    }

    private boolean outOfBounds(int x, int y){
        if( x < 0 || x >= first.getField_size_x() ||
            y < 0 || y >= first.getField_size_y()){
            return true;
        }
        return false;
    }
}