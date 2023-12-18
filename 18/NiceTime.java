/**
 * @author D3crvpt3d
 * 
 * @version 0.1.0
 * 
 * Easy to use and lightweight timer that handels efficient String formatting
 * 
 * @see https://github.com/d3crvpt3d/NiceTime
 */
public class NiceTime {
    
    private long startTime;
    private int maxDecimalPlaces = 3;

    public NiceTime(){
        this.startTime = System.nanoTime();
    }

    /**
     * changes the amount of max. Decimal Places
     * 
     * @DefaultValue 3
     * 
     * @exception IllegalArgumentException
     */
    public void setMaxDecimalPlaces(int maxDecimalPlaces) throws IllegalArgumentException {
        if(maxDecimalPlaces < 0){
            throw new IllegalArgumentException("Decimal Places cant be Negative");
        }
        this.maxDecimalPlaces = maxDecimalPlaces;
    }

    /**
     * resets the time
     */
    public void resetTime(){
        this.startTime = System.nanoTime();
    }

    /**
     * 
     * @return elapsed nanoseconds from last reset
     */
    public long getElapsedNano(){
        return System.nanoTime()-startTime;
    }

    /**
     * Returns the elapsed Time from initializing this Object
     * or from last reset by calling 'resetTime()'
     * 
     * @return String
     */
    public String getElapsedTime(){
        //time
        String elapsed = new String(""+(System.nanoTime()-startTime));
        String[] end = {"ns","µs","ms","s"};
        int magnitude = elapsed.length()/3;
        int format = elapsed.length() % 3;

        return elapsed.substring(0, format)+"."+elapsed.substring(format, Math.min(format+Math.max(0, maxDecimalPlaces), elapsed.length()-1))+end[Math.min(end.length-1, magnitude)]+"\n";
    }


}