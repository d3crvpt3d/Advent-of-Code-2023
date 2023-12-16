public class NiceTime {
    
    private long startTime;

    public NiceTime(){
        this.startTime = System.nanoTime();
    }

    public String getElapsedTime(){
        //time
        String elapsed = new String(""+(System.nanoTime()-startTime));
        System.err.println(System.nanoTime()-startTime);
        String[] end = {"ns","µs","ms","s"};
        int magnitude = elapsed.length()/3;
        int format = elapsed.length() % 3;
        return elapsed.substring(0, format)+"."+elapsed.substring(format, elapsed.length()-1)+end[Math.min(end.length-1, magnitude)]+"\n";
    }


}