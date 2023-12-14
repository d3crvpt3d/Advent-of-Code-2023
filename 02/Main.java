import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        

        int output = 0;
        
        while (scanner.hasNextLine()) { //each line

            String line = scanner.nextLine();

            boolean possible = true;
            
            for(int z = 7; z <  line.length(); z++){ //ignore "Game #: "

                int tmp_r = 0;
                int tmp_g = 0;
                int tmp_b = 0;

                while ( line.charAt(z) != ';') { //each subgame

                    
                    if( line.charAt(z) >= '0' &&  line.charAt(z) <= '9' ){

                        char val =  line.charAt(z+3); // r/g/b char

                        if( line.charAt(z+1) >= '0' &&  line.charAt(z+1) <= '9'){ //possible if single digit

                            switch (val) {
                                case 'r':
                                    tmp_r = Integer.parseInt(""+ line.charAt(z)+ line.charAt(z+1)); //parse double digit for red
                                    break;
                                case 'g':
                                    tmp_g = Integer.parseInt(""+ line.charAt(z)+ line.charAt(z+1)); //parse double digit for red
                                    break;
                                case 'b':
                                    tmp_b = Integer.parseInt(""+ line.charAt(z)+ line.charAt(z+1)); //parse double digit for red
                                    break;
                            
                                default:
                                    System.err.println("char not r/g/b");
                                    return;
                            }

                        }

                    }

                    z++;
                    if(z == line.length()){
                        break;
                    }
                }

                //check if impossible subgame
                if(12 - tmp_r < 0){
                    possible = false;
                }else if(13 - tmp_g < 0){
                    possible = false;
                }else if(14 - tmp_b < 0){
                    possible = false;
                }

            }

            //parse game ID
            int ID = Integer.parseInt( (""+ line.charAt(5) + ( line.charAt(6) != ':'?  line.charAt(6) : "") + ( line.charAt(7) != ':'?  line.charAt(7) : "") ).trim());

            if(possible){
                output += ID;
            }

        }

        System.out.println(output);

        scanner.close();

    }

}
