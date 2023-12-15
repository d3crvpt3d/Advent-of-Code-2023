import java.util.ArrayList;
import java.util.Scanner;

class Second{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ArrayList<ArrayList<String>> arrayListArray = new ArrayList<ArrayList<String>>(0x100);

        for (int i = 0; i < 0x100; i++) {
            arrayListArray.add(new ArrayList<String>());
        }

        scanner.useDelimiter(",");

        int output = 0;

        while(scanner.hasNext()){
            String line = scanner.next();

            String label = line.split("[=-]")[0];
            int box = stringToHash(label);
            String operation = line.split("\\d+")[0].split("\\w+")[1];

            ArrayList<String> currBox = arrayListArray.get(box);
        
            switcheroo:
            switch (operation) {
                case "=":
                    for(int i = 0; i < currBox.size(); i++){
                        if(currBox.get(i).split("[=-]")[0].equals(label)){
                            currBox.set(i, line);
                            break switcheroo;
                        }
                    }
                    arrayListArray.get(box).add(line);
                    break;

                case "-":
                    for(int i = 0; i < currBox.size(); i++){
                        if(currBox.get(i).split("[=-]")[0].equals(label)){
                            currBox.remove(i);
                            break;
                        }
                    }
                    break;

                default:
                    break;
            }

        }
        scanner.close();

        for (int a = 0; a < arrayListArray.size(); a++) {
            
            for(int b = 0; b < arrayListArray.get(a).size(); b++){

                output += (a+1) * (b+1) * Integer.parseInt(arrayListArray.get(a).get(b).split("\\w+[=-]")[1]);
            }

        }

        System.out.println(output);
    }

    static private int stringToHash(String s){

        int o = 0;

        for(int i = 0; i < s.length(); i++){

            o += (int)s.charAt(i);
            o *= 17;
            o = o % 0x100;
        }


        return o;
    }

}