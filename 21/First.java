import java.util.Scanner;

class First{

    
    static int notReachable = 0;
    static int steps = 64+1;
    static int posxy_S = 65; // 65 = S

    public static void main(String[] args){

        NiceTime nt = new NiceTime();

        Scanner sc = new Scanner(System.in);

        int pos_y = 0;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();

            for(int pos_x = 0; pos_x < s.length(); pos_x++){
                if(s.charAt(pos_x) == '#' || s.charAt(pos_x) == 'S'){
                    
                    if(mhd(Math.abs(trnsltd(pos_x)), Math.abs(trnsltd(pos_y)) )< steps){
                        notReachable++;
                    }
                }
                if(mhd(pos_x, pos_y) % 2 != 0){ //if not reachable, due to not even
                    notReachable++;
                }
            }
            pos_y++;

        }
        sc.close();

        int karoArea = 4*(steps*(steps+1)) - (4*steps) - 3;

        System.err.println("N Steine: "+notReachable);
        System.err.println("Karo Area "+karoArea);

        System.out.println(karoArea-notReachable);

        System.err.println(nt.getElapsedTime());

    }

    private static int trnsltd(int a){
        return Math.abs(a)-posxy_S;
    }

    private static int mhd(int x, int y){
        return x+y;
    }
}