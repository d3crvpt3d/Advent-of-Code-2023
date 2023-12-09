import java.util.Scanner;
import java.util.ArrayList;

public class Second {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        ArrayList<ArrayList<ArrayList<Long>>> matrix = new ArrayList<ArrayList<ArrayList<Long>>>(); // get(line).get(depth).get(index)

        int currLine = 0;

        while (scanner.hasNextLine()) {

            matrix.add(new ArrayList<ArrayList<Long>>()); // add line
            matrix.get(currLine).add(new ArrayList<Long>()); // add depth 0
            
            String line = scanner.nextLine();

            for (String s : line.trim().split("\\s+")) {
                matrix.get(currLine).get(0).add(Long.parseLong(s)); // and int to depth 0 of line
            }

            currLine++;

        }
        scanner.close();

        // depth = 0 done (bis auf letztes element)

        //calculate each depth: 
        for (ArrayList<ArrayList<Long>> line : matrix) {
            
            int depth_idx = 0;
            //while last/first element of current depth is not 0 create new depth
            while(      line.get(depth_idx).get(line.get(depth_idx).size()-1) != 0
                    ||  line.get(depth_idx).get(0) != 0){

                //new depth
                line.add(new ArrayList<Long>());
                depth_idx++;

                //fill new depth
                for (int i = 0; i < line.get(depth_idx-1).size()-1; i++) {
                    
                    line.get(depth_idx).add(
                                            line.get(depth_idx-1).get(i+1)-
                                            line.get(depth_idx-1).get(i)
                                            );
                
                }

            }

        }

        //DONE

        System.err.println(matrix.get(0).toString().replace(", [", ",\n["));

        //calculate first element for each depth
        for (ArrayList<ArrayList<Long>> lines : matrix) {
            
            for (int depths = lines.size()-2; depths > 0; depths--) {


                lines.get(depths-1).add(0,
                                        lines.get(depths-1).get(0)-
                                        lines.get(depths).get(0)
                                        );

            }

        }

        System.err.println(matrix.get(0).toString().replace(", [", ",\n["));

        long output = 0;

        for (ArrayList<ArrayList<Long>> lar : matrix) {
            output += lar.get(0).get(0); // add first element of depth 0 of each line to output
        }

        System.out.println(output);

    }
    
}