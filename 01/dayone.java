class Main{

    public static void main(String[] args) {

        int output = 0;
        
        for (int i = 0; i < args.length; i++) { //i = input split by /n
            
            int tmp_out = 0;

            int a = 0;
            int b = args[i].length()-1;

            int k = 0;
            int l = args[i].length()-1;

            while (args[i].charAt(a) < '0' || args[i].charAt(a) > '9' ) {
                a++;
                if(a == args[i].length()){
                    return;
                }
            }

            if(isInt(0, a, i)){

            }

            //down top str to int
            if(true){
                ;
            }

            while (args[i].charAt(b) < '0' || args[i].charAt(b) > '9' ) {
                b--;
            }

            tmp_out = Integer.parseInt(""+args[i].charAt(a)+args[i].charAt(b));

            output += 

        }

        System.out.println(output);

    }

    //recursive biased grep like
    private static boolean isInt(int von, int bis, int iteration){
        
        

    }

}