import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

class First{

    static HashMap<String, Module> hMap = new HashMap<String, Module>();

    
    static int pulsesLow = 1000; //button pulses
    static int pulsesHigh = 0;

    public static void main(String[] args){
        NiceTime nt = new NiceTime();

        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()){
            String[] s = sc.nextLine()
                        .replaceAll("[->,]", "")
                        .split("\\s+");

            
            
            switch (s[0].charAt(0)) {
                case '%':
                    hMap.put(s[0].substring(1), new FlipFlop(s, hMap));
                    break;
                case '&':
                    hMap.put(s[0].substring(1), new Conjunction(s, hMap));
                    break;
                default:
                    hMap.put(s[0], new Broadcaster(s, hMap));
                    break;
            }

        }
        sc.close();

        hMap.put("output", new Output());

        for (int i = 0; i < 1000; i++) {
            
            hMap.get("broadcaster").doThing(false, new Output());

        }

        long l = (long)pulsesHigh * (long)pulsesLow;

        System.out.println(l);

        System.err.println(nt.getElapsedTime());
    }
}

interface Module{

    void doThing(boolean pulse, Module from);

}

class Broadcaster implements Module{
    private HashMap<String, Module> hMap;
    private ArrayList<String> outputs = new ArrayList<String>();

    public Broadcaster(String[] s, HashMap<String, Module> hMap){
        this.hMap = hMap;
        for (int i = 1; i < s.length; i++) {
            outputs.add(s[i]);
        }
    }

    @Override
    public void doThing(boolean pulse, Module from){

//        System.err.printf("Broadcaster send %b to %d\n", pulse, outputs.size());
        for(int i = 0; i < outputs.size(); i++) {
            if(pulse){First.pulsesHigh++;}else{First.pulsesLow++;} //add pulses to first
            hMap.get(outputs.get(i)).doThing(pulse, this);
        }
    }

}

class Conjunction implements Module{

    private HashMap<Module, AtomicBoolean> modMap = new HashMap<Module, AtomicBoolean>();
    private HashMap<String, Module> hMap;
    private ArrayList<String> outputs = new ArrayList<String>();

    public Conjunction(String[] s, HashMap<String, Module> hMap){
        this.hMap = hMap;
        for (int i = 1; i < s.length; i++) {
            outputs.add(s[i]);
        }
    }

    @Override
    public void doThing(boolean pulse, Module from){

        if(!modMap.containsKey(from)){
            modMap.put(from, new AtomicBoolean(pulse));
        }

        modMap.get(from).set(pulse);

        boolean b = false;

        for(Map.Entry<Module, AtomicBoolean> entry : modMap.entrySet()){
            if(!entry.getValue().get()){
                b = true;
            }
        }

//        System.err.printf("Conjunction send %b to %d\n", b, outputs.size());
        for (int i = 0; i < outputs.size(); i++) {
            if(b){First.pulsesHigh++;}else{First.pulsesLow++;} //add pulses to first
            hMap.get(outputs.get(i)).doThing(b, this);
        }
    }
    
}

class FlipFlop implements Module{

    private boolean state = false;
    private HashMap<String, Module> hMap;
    private ArrayList<String> outputs = new ArrayList<String>();

    public FlipFlop(String[] s, HashMap<String, Module> hMap){
        this.hMap = hMap;
        for (int i = 1; i < s.length; i++) {
            outputs.add(s[i]);
        }
    }

    @Override
    public void doThing(boolean pulse, Module from){

        if(pulse){return;}
        state = !state;
//        System.err.printf("Flipflop send %b to %d\n", state, outputs.size());
        for (int i = 0; i < outputs.size(); i++) {
            if(state){First.pulsesHigh++;}else{First.pulsesLow++;} //add pulses to first
            hMap.get(outputs.get(i)).doThing(state, this);
        }
    }

}

class Output implements Module{



    @Override
    public void doThing(boolean pulse, Module from) {
        ;
    }
    
}