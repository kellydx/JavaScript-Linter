import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the driver to test the following methods
 * 
 *          findUnusedVar           : find declared but unused variables
 *          findSingleIfELse        : find conditional statements with curly brackets   
 *          findUndeclaredFunction  : find underclared function call
 *          findBracket             : find missing/ extra curly bracket and report line number
 */
public class Driver{    
    
   public static List<String> txt= new ArrayList<String>();    
    
    public static void main(String [] args)throws IOException {
      // if no file name is entered  
      if (args.length < 1){
			System.out.println("No filename found. Please enter an input file name ");		
			return;
      }
		
      try{
            //read the entire text into an arrayList
            FileInputStream fstream = new FileInputStream(args[0]);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine; 
            IgnoreComment cmt = new IgnoreComment();
            int i=0;
            while ((strLine = br.readLine()) != null)   {
                strLine = cmt.ignoreComments(strLine.trim());
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
            System.out.println("\n------ Missing/ extra curly brackets Report ------\n");
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