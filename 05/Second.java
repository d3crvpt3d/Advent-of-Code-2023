import java.util.Scanner;
import java.util.ArrayList;

public class Second {

    static long[] seeds;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //convert first seeds String to Array
        String[] seedsString = scanner.nextLine().split(":")[1].trim().split("\\s+");

        int iteration = 2*8; //change and look at lowest output from each thing

        int lengthas =  Integer.parseInt(seedsString[iteration+1]);

        seeds = new long[lengthas];

        int seediterator = 0;


        long range = Long.parseLong(seedsString[iteration+1].trim());
        long start = Long.parseLong(seedsString[iteration].trim());
        for (int j = 0; j < range; j++) {
            seeds[seediterator++] = j+start;
        }
        //end of convert first seeds

        int currentMap = -1;

        ArrayList<ArrayList<ArrayList<Long>>> map = new ArrayList<ArrayList<ArrayList<Long>>>();

        int inputNumber = 0;

        //create Maps
        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            //check for empty lines
            if(line.length() == 0){
                continue;
            }

            //skip if new map and change map
            if(Character.isAlphabetic(line.charAt(0))){
                inputNumber = 0;
                currentMap++; // 0 if seed to soil
                map.add(new ArrayList<ArrayList<Long>>());
                continue;
            }

            //fill current map with values from scanner
            String[] split = line.split("\\s+");
            map.get(currentMap).add(new ArrayList<Long>());
            map.get(currentMap).get(inputNumber).add(Long.parseLong(split[0])); //destination
            map.get(currentMap).get(inputNumber).add(Long.parseLong(split[1])); //source
            map.get(currentMap).get(inputNumber).add(Long.parseLong(split[2])); //range
            inputNumber++; // next tuple
        }
        scanner.close();

        seeds = sourceToDestinationMap(seeds, map.get(0));
        seeds = sourceToDestinationMap(seeds, map.get(1));
        seeds = sourceToDestinationMap(seeds, map.get(2));
        seeds = sourceToDestinationMap(seeds, map.get(3));
        seeds = sourceToDestinationMap(seeds, map.get(4));
        seeds = sourceToDestinationMap(seeds, map.get(5));
        seeds = sourceToDestinationMap(seeds, map.get(6));

        long min = seeds[0];

        for (long endseed : seeds) {
            if(endseed < min){
                min = endseed;
            }
        }

        System.out.println(min);
    }



    private static long[] sourceToDestinationMap(long[] seeds2, ArrayList<ArrayList<Long>> map2) {
        
        for (int i = 0; i < seeds2.length; i++) {
            for (ArrayList<Long> map : map2) {
                if(seeds2[i] >= map.get(1) && seeds2[i] <= map.get(1)+map.get(2)){ // if in range of current map
                    seeds2[i] = seeds2[i]+(map.get(0)-map.get(1)); // destination-source -> difference
                    break; // go to next seed
                }
                //if not in map -> no transformation
            }
        }
        
        return seeds2;
    }

}