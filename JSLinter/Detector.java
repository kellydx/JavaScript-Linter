import java.io.*;
import java.util.*;
/**
 * Write a description of interface Linter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Detector{
    /**
     * An example of a method header - replace this comment with your own
     * 
     * @param  y    a sample parameter for a method
     * @return        the result produced by sampleMethod 
     */
    public void match (ArrayList<String> lines);
    public void declaredVar (ArrayList<String> declaredVar, ArrayList<String> lines);
    public void unusedVar (ArrayList<String> declaredVar, HashMap <String, Integer> counts, HashMap <String, Integer> location );
    public void report( HashMap <String, Integer> counts, HashMap <String, Integer> location);
}
