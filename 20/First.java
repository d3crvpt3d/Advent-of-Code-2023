import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

class First{

    static HashMap<String, Module> hMap = new HashMap<String, Module>();

    static Collector collector = new Collector();
    
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

        for (int i = 0; i < 1; i++) {
            
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

        for(int i = 0; i < outputs.size(); i++) {
            First.collector.putHaMap(pulse, hMap.get(outputs.get(i)), this);
        }
        System.err.printf("Broadcaster send %b to %d\n", pulse, outputs.size());
        if(pulse){First.pulsesHigh+=outputs.size();}else{First.pulsesLow+=outputs.size();} //add pulses to first
        First.collector.send();
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

        for (int i = 0; i < outputs.size(); i++) {
            First.collector.putHaMap(b, hMap.get(outputs.get(i)), this);
        }
        System.err.printf("Conjunction send %b to %d\n", b, outputs.size());
        if(b){First.pulsesHigh+=outputs.size();}else{First.pulsesLow+=outputs.size();} //add pulses to first
        First.collector.send();
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
        for (int i = 0; i < outputs.size(); i++) {
            First.collector.putHaMap(state, hMap.get(outputs.get(i)), this);
        }
        System.err.printf("Flipflop send %b to %d\n", state, outputs.size());
        if(state){First.pulsesHigh+=outputs.size();}else{First.pulsesLow+=outputs.size();} //add pulses to first
        First.collector.send();
    }

}

class Output implements Module{

    @Override
    public void doThing(boolean pulse, Module from) {
        ;
    }
    
}

class Collector{

    private HashMap<Module, Tuple> hM_in;
    private HashMap<Module, Tuple> hM_out;

    public Collector(){
        this.hM_in = new HashMap<Module, Tuple>();
        this.hM_out = new HashMap<Module, Tuple>();
    }

    public void putHaMap(boolean pulse, Module moduleTo, Module from) {
        hM_in.put(moduleTo, new Tuple(from, pulse));
    }
    
    public void send(){
        for (Map.Entry<Module, Tuple> entry : hM_in.entrySet()) {
            //fill hM_out with new signals
        }

        //

        hM_in.clear(); //reset
    }

}

class Tuple{

    Module from;
    boolean pulse;

    public Tuple(Module from, boolean pulse){
        this.from = from;
        this.pulse = pulse;
    }

}