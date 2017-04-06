import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver{    
    
    public static List<String> txt= new ArrayList<String>();    
    
    public static void main(String [] args)throws IOException {
       
      try{
            //read the entire text into an arrayList
            FileInputStream fstream = new FileInputStream("rainbow.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;    
            int i=0;
            while ((strLine = br.readLine()) != null)   {
                txt.add(strLine.trim());           
            }       
            in.close();        
        }    
        catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
      }      
            findUnusedVar(txt);
            findSingleIfELse(txt);        
            findUndeclaredFunction(txt);
            findBracket();
        
   }
   
   public static void findUnusedVar (List<String> txt){
       UnusedVariable s = new UnusedVariable(txt);         
       s.declaredVar();        
       s.unusedVar();
       s.report();             
    }
    
   public static void findSingleIfELse (List<String> txt){
       IfElseStatements v = new IfElseStatements(txt);         
       v.singleIfElse(); 
       v.report();       
             
   }
    
   public static void findUndeclaredFunction (List<String> txt){
        UndeclareFunction u = new UndeclareFunction(txt);
        u.functionList();
        u.functionCall();
        u.report();
   }
    
   public static void findBracket (){
       Bracket b = new Bracket();   
       b.checkBalancedBrackets(txt);
             
   }    
}