import java.io.*;
import java.util.*;

class UnUsedVariable implements Detector{
    
    ArrayList<String> txt;
    public UnUsedVariable(ArrayList<String> text) {
		txt = text;
	}  
	
	/*
     * Method match():
     * - scan the input file and add only the lines that contain variable declaration to an arrayList
     * Parameters:
     * ArrayList<String> lines  :  list of the text lines that contain variable declarations
     * String strLine           : each line of the input file
     * 
     */
    public void match (ArrayList<String> lines)
    {
         for (int i = 0; i < txt.size(); i++){        
         if (txt.get(i).matches("(let|var).*=.*;") || txt.get(i).matches("(let|var).*;")){
            lines.add(txt.get(i).trim());            
         }
    }
             
    }     
  
       
    /*
     * Method declaredVar:
     * - split each lines that contain variable declaration found in method match
     * to find the variable names
     * - push the variable names into an arraylist
     * Parameters:
     * ArrayList<String> declaredVar  :  list of the declared variables
     * ArrayList<String> lines        :  list of the text lines that contain variable declarations
     * 
     */
    public void declaredVar (ArrayList<String> declaredVar, ArrayList<String> lines){
        for (int i = 0; i < lines.size(); i++){
             String [] tokens = (lines.get(i)).split("\\W+");
             for (int k = 0; k < tokens.length; k++) {
             System.out.println(tokens[k]);  }  
             for (int j = 0; j < tokens.length; j++) {
                 if (tokens[j].equals("var") || tokens[j].equals("let")){
                     declaredVar.add(tokens[j+1]);
                    }
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

