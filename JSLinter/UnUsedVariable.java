import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class UnusedVariable implements Detector{
    //pattern let/var varName = value;
    Pattern r = Pattern.compile("(let|var)(.*?)\\=(.*?)\\;");
    //pattern let/var varName;
    Pattern r1 = Pattern.compile("(let|var)(.*?)\\;");
    String varName="";             
    ArrayList<String> txt;
    public UnusedVariable(ArrayList<String> text) {
        txt = text;
    }       
       
    /*
     * Method declaredVar:
     * - Using java regex and pattern class to find the list of declared 
     *   var in the text file
     * Parameters:
     * ArrayList<String> declaredVar  :  list of the declared variables
     * ArrayList<String> lines        :  list of the text lines that contain variable declarations
     * 
     */
    public void declaredVar (ArrayList<String> declaredVar){        
           
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
    
     /*
     * Method unusedVar:
     * - scan through the input file and find the number of times each variable appears 
     * - push the data into a hashmap called "counts" with key: variable name and value: occurrence of variable
     * - Whenever the variable is found the first time, also push the line number into a hashmap called "location", with the key is the variable name
     * Parameters:
     * ArrayList<String> declaredVar        :   list of the declared variables
     * HashMap <String, Integer> counts     :   hashmap holds the occurence of each variable
     * HashMap <String, Integer> location     :   hashmap holds the location of each variable declaration
     * ArrayList<String> txt                :   every line of text in the input file
     */   
     public void unusedVar (ArrayList<String> declaredVar, HashMap <String, Integer> counts, HashMap <String, Integer> location ){
        for (int i = 0; i < declaredVar.size(); i++){  
            for (int j = 0; j < txt.size(); j++){             
                if (txt.get(j).contains(declaredVar.get(i))){            
                    if (counts.get(declaredVar.get(i)) == null) {
                        counts.put(declaredVar.get(i),1);
                        location.put(declaredVar.get(i),j+1);              
                    } 
                    else if (counts.get(declaredVar.get(i)) >= 1) {
                        counts.put(declaredVar.get(i),counts.get(declaredVar.get(i)) + 1);
                    }
                }               
            }           
        }
    }    
    
    
    /*
     * Method report:
     * - reports the unused variables based on the occurrence of them
     *      ~ if a variable only appear one time in the text file, it is an unused variable
     *      ~ the hashmap "counts" keeps track of the occurences of every variables for future use, if needed
     * Parameters:     
     * HashMap <String, Integer> counts     :   hashmap holds the occurence of each variable
     * HashMap <String, Integer> counts     :   hashmap holds the location of each variable declaration
     * 
     */   
    
    public void report( HashMap <String, Integer> counts, HashMap <String, Integer> location){
        System.out.println("Below are the list of unused variables and the line numbers which they are declared on");
        System.out.println("Variable Name"+ "\t\t" + "Line Number");
        for (String key : counts.keySet()) {                  
                if (counts.get(key)==1){                      
                    System.out.println(key + "\t\t\t  "+location.get(key)); 
                }
        }
    }
           
}

