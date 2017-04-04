import java.io.*;
import java.util.*;

public class Driver{
    public static HashMap <String, Integer> counts = new HashMap<String, Integer>(); 
    public static HashMap <String, Integer> location = new HashMap<String, Integer>(); 
    public static ArrayList<String> lines= new ArrayList<String>();
    public static ArrayList<String> declaredVar= new ArrayList<String>();
    public static ArrayList<String> txt= new ArrayList<String>();
   public static void main(String [] args){
       
      try{
        //read the entire text into an arrayList
        FileInputStream fstream = new FileInputStream("rainbow.txt");
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;    
              
        while ((strLine = br.readLine()) != null)   {
            txt.add(strLine.trim());                        
        }       
        in.close();        
       }    
       catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }      
        findUnUsedVar(txt);
   }
   
   public static void findUnUsedVar (ArrayList<String> txt){
       Detector s = new UnUsedVariable(txt);
        s.match(lines);        
        System.out.println(lines);
        s.declaredVar(declaredVar, lines);        
        s.unusedVar(declaredVar,counts,location);
        s.report(counts, location);        
    }
}