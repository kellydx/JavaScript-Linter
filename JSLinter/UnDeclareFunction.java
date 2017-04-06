import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class UndeclareFunction:
 *     - Locate function calls that have not been declared and 
 *     - report the name of the function and the line number that the function call happens on.
 *     
 * Methods:
 *    functionList()        : find the list of declared functions
 *    functionCall()        : find the list of all function call in the file
 *    containsKey()         : complement the functionCall method, used to ignore the function declaration
 *    report()              : display the output mentioned above
 * 
 */
class UndeclareFunction{
    
    List<String> txt = new ArrayList<String>();
    List<String> declaredFunction= new ArrayList<String>();
    HashMap <String, Integer> funcCall = new HashMap<String, Integer>();
    
    public UndeclareFunction(List<String> text) {
        txt = text;
    }     
    
    /**
     * Method functionList:
     *       - Scan through the text to find lines that match the patterns of function declaration
     *       - Split the lines found to get the function names and store it in the arrayList "declaredFunction"
     *       
     *        2 patterns is used in this class to detect function declaration:
     *          - pattern_func1 to match: function funcName() {...}
     *          - pattern_func2 to match: let/var = function() {...}
     *     
     */
    public void functionList (){
            String funcName;
            String pattern_func1 = "function\\s{0,}([a-zA-Z_$][a-zA-Z0-9_$]*)\\s{0,}\\(\\s{0,}\\S*?\\s{0,}\\).*";
            String pattern_func2 ="(let|var)\\s{0,}([a-zA-Z$_][a-zA-Z0-9$_]*)\\s{0,}\\=\\s{0,}(function)\\s{0,}\\((\\s{0,}\\S*?\\s{0,})\\).*";
            Pattern r = Pattern.compile(pattern_func1);
            Pattern r1 = Pattern.compile(pattern_func1);       
        
            for (int j = 0; j <txt.size(); j++){
                Matcher m = r.matcher(txt.get(j));
                Matcher m1 = r1.matcher(txt.get(j));            
            
                if (m.find()){
                    funcName = m.group().split("\\(")[0].split("function")[1].trim();              
                    declaredFunction.add(funcName);
                } 
                else if (m1.find()){
                    funcName = m1.group().split("\\=")[0].split("var|let")[1].trim();                 
                    declaredFunction.add(funcName);
                }                                  
            }
    }       
    
    /**
     * Method functionCall:
     *       - Scan through the text to find lines that match the patterns of function call
     *       - Split the lines found to get the function names and store it in the hashmap "funcCall", with the line numbers
     *       
     *        2 patterns is used in this class to detect function calls:
     *          - pattern_func1 to match: doSomething(..);
     *          - pattern_func2 to match: mustange.getEngineType() {...}
     *          
     * @ HashMap <String, Integer>  funcCall   :    hash map contains function names and line numbers 
     *     
     */
    public void functionCall (){
        String funcName;        
        String pattern_func_call1 = "([a-zA-Z_$][a-zA-Z0-9_$]{1,}\\s{0,}[\\(]{1,}[\\s]{0,}\\S*?[\\s]{0,}[\\)])";       
        String pattern_func_call2 = "\\s{0,}[a-zA-Z_$][a-zA-Z0-9_$]{1,}.[a-zA-Z_$][a-zA-Z0-9_$]{1,}\\s{0,}\\(\\s{0,}\\S*?\\s{0,}\\).*";
        Pattern r2 = Pattern.compile(pattern_func_call1);
        Pattern r3 = Pattern.compile(pattern_func_call2);      
        
        for (int j = 0; j <txt.size(); j++){           
           Matcher m2 = r2.matcher(txt.get(j));           
           Matcher m3 = r3.matcher(txt.get(j));          
           boolean flg = containsKey(txt.get(j));
           if (m2.find()&& !flg ){
                System.out.println(txt.get(j));
                funcName = m2.group().split("\\(")[0].trim();                 
                funcCall.put(funcName,j+1);
           } 
           else if (m3.find()&& !flg){
                System.out.println(txt.get(j));
                funcName = m3.group().split("\\=")[0].trim();        
                funcCall.put(funcName,j+1);
           }                   
        }        
    }
    
    /**
     * Method containsKey:
     *      - complements method functionCall, to ignore the lines contain function declaration
     * 
     * @param String line   : The string to check if contains function declaration keyword
     * @return flg          : false mean the string doesn't contain the keyword
     */
    public boolean containsKey (String line ){
        String [] keyword = {"var", "function", "if", "else", "else if", "let","set"};
        boolean flag = false;
        for (int i=0; i<keyword.length; i++){
            if (line.contains(keyword[i]))
            flag = true;
        }
        return flag;
    }
    
    /**
     * Method report:
     *      - chech the Function Calls against the Function List
     *      - if a function's name in the function Call detected in the function list, remove it from the HashMap
     *      - the left over elements in the hashmap is the undeclare functions     *         
     *     
     * @ HashMap <String, Integer> counts     :   hashmap holds the occurence of each variable
     * @ HashMap <String, Integer> counts     :   hashmap holds the location of each variable declaration
     * 
     */   
    public void report (){
       for (int i=0; i<declaredFunction.size(); i++){
           if (funcCall.containsKey(declaredFunction.get(i)))
           funcCall.remove(declaredFunction.get(i));
        }       
        
       System.out.println("Below are the list of functions calls that has not been declared \nand the line numbers which they are declared on");
       System.out.println("Function Name"+ "\t\t\t\t" + "Line Number");
       for (String key : funcCall.keySet()) {
           System.out.println(key + "\t\t\t\t\t  "+funcCall.get(key));            
       }
    }
}