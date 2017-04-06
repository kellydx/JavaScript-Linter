import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Kelly Duong
 * @date 04/01/2017
 * This class:
 *      - Finds declared variables that are not used in the code
 *      - Reports the name of the variable and the line number the variable is declared on.
 * 
 * Methods: 
 *      - declaredVar () : find the list of declared variables
 *      - unusedVar ()   : find the list of declared but not used variables
 *      - report( )      : report the output mentioned above
 * 
 */

public class UnusedVariable {
    //pattern let/var varName = value;
    Pattern r = Pattern.compile("(let|var)(.*?)\\=(.*?)\\;");
    //pattern let/var varName;
    Pattern r1 = Pattern.compile("(let|var)(.*?)\\;");               
    List<String> txt = new ArrayList<String>();
    ArrayList<String> declaredVar= new ArrayList<String>();
    HashMap <String, UnusedVariable> unused_var_collection = new HashMap<String, UnusedVariable>(); 
    int line;
    int count;    
    public UnusedVariable(List<String> text) {
        txt = text;        
        this.line = line;
        this.count = count;        
    }    
    
	public void setCount(int c){
		this.count = c;
	}

	public int getCount(){
		return this.count;
    }

	public void setLine(int l){
		this.line = l;
	}

	public int getLine(){
		return this.line;
	}	
	
    public String toString(){
	    return("  "+this.line);
	   }   
	   
    /**
     * Method declaredVar():
     *      - Using java regex matcher and pattern class to find the declared 
     *        variables in the text file, then add them to an arrayList 
     *  
     *  @ ArrayList<String> declaredVar        :   list of the declared variables
     */
    public void declaredVar (){        
        String varName="";     
        for (int i = 0; i < txt.size(); i++){      
            //pattern let/var varName = value;
            Matcher m = r.matcher(txt.get(i));
            //pattern let/var varName;
            Matcher m1 = r1.matcher(txt.get(i));
            
            if (m.find()){
                varName = m.group().split("\\=")[0].split("let|var")[1].trim();             
                declaredVar.add(varName);
            }
            else if (m1.find()){
                varName = m1.group().split(";")[0].split("var|let")[1].trim();
                declaredVar.add(varName);
            }
        }        
    }
    
     /**
     * Method unusedVar:
     *      - Scan through the input file and find the number of times each variable appears 
     *      
     *      - Whenever the variable is found the first time, create an object to hold the line number 
     *        and the count of it. Also push it into a hashmap with the key is the variable name    
     *        
     *      - When the same variable appears again, simply update the count of the object 
     *        corresponded to that var
     * 
     * @ ArrayList<String> declaredVar                      :   list of the declared variables
     * 
     * @ HashMap <String, Integer> unused_var_collection    :   hashmap holds the variable name as the
     *                                                          key and the value is an object that holds the 
     *                                                          line number and occurence of that variable     
     * 
     * @ ArrayList<String> txt                              :   every line of text in the input file
     */   
     public void unusedVar (){
       
         for (int i = 0; i < declaredVar.size(); i++){  
            for (int j = 0; j < txt.size(); j++){             
                if (txt.get(j).contains(declaredVar.get(i))){ 
                    if (unused_var_collection.get(declaredVar.get(i)) == null) {
                        UnusedVariable v = new UnusedVariable(txt);                    
                        v.setCount(1);
                        v.setLine(j+1);  
                        unused_var_collection.put(declaredVar.get(i),v);
                    } 
                    else if (unused_var_collection.get(declaredVar.get(i)).getCount() >= 1) {
                        unused_var_collection.get(declaredVar.get(i)).setCount(unused_var_collection.get(declaredVar.get(i)).getCount()+1);
                    }
                    
                }               
            }  
           
        }
    }    
    
    /**
     * Method report:
     * - reports the unused variables based on the occurrence of them
     *      ~ if a declared variable only appear one time in the text file, it is an unused variable
     *      ~ the hashmap "unused_var_collection" keeps track of the occurences of every variables for future use, if needed
     *     
     * @ HashMap <String, Integer> unused_var_collection     :   hashmap holds the variable name as the key 
     *                                                           and the value is an object that holds the 
     *                                                           line number and occurence of that variable   
     */   
    
    public void report( ){
        System.out.println("Below are the list of unused variables and the line numbers which they are declared on");
        System.out.println("Variable Name"+ "\t\t" + "Line Number");
                
        for (String key : unused_var_collection.keySet()) {                  
                if ((unused_var_collection.get(key)).getCount()==1){                      
                    System.out.println(key + "\t\t\t  "+unused_var_collection.get(key)); 
                }
        }
    }
           
}

