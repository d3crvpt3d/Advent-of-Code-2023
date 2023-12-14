import java.util.Scanner;

public class Second {

    public static void main(String[] args) {

        int output = 0;
        
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            System.out.println(line);

            int r = 0;
            int g = 0;
            int b = 0;

            for (int i = 7; i < line.length(); i++) { //iterate each char from line

                int tmp_r = 0;
                int tmp_g = 0;
                int tmp_b = 0;

                //if number
                if(line.charAt(i) >= '0' && line.charAt(i) <= '9'){

                    //if 2 digit number
                    if(line.charAt(i+1) >= '0' && line.charAt(i+1) <= '9'){

                        switch (line.charAt(i+3)) {
                            case 'r':
                                tmp_r = Integer.parseInt((""+line.charAt(i)+line.charAt(i+1)).trim());
                                break;
                             case 'g':
                                tmp_g = Integer.parseInt((""+line.charAt(i)+line.charAt(i+1)).trim());
                                break;
                             case 'b':
                                tmp_b = Integer.parseInt((""+line.charAt(i)+line.charAt(i+1)).trim());
                                break;
                        
                            default:
                                System.err.println("key not r/g/b");
                                break;
                        }


                        //if 1 digit number
                    }else{

                        switch (line.charAt(i+2)) {
                            case 'r':
                                tmp_r = Integer.parseInt((""+line.charAt(i)).trim());
                                break;
                             case 'g':
                                tmp_g = Integer.parseInt((""+line.charAt(i)).trim());
                                break;
                             case 'b':
                                tmp_b = Integer.parseInt((""+line.charAt(i)).trim());
                                break;
                        
                            default:
                                System.err.println("key not r/g/b");
                                break;
                        }

                    }

                //end if number
                }

                //save if greater
                r = Math.max(tmp_r, r);
                g = Math.max(tmp_g, g);
                b = Math.max(tmp_b, b);

            }

            System.out.println("r: "+r+" g: "+g+" b: "+b);
            //sum powers
            output += r*g*b;
            
        //end scanner has next line
        }

        System.out.println(output);
        scanner.close();
    }

}
