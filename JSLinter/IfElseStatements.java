import java.io.*;
import java.util.*;
import java.util.ArrayList;
/**
 * This class:
 *      - Find if/else statement that don't have curly brackets
 *      - Report if it is an 'if' or a 'else' block
 *      - Report the line number the block is on
 *      
 * Methods:
 *      singleIfElse()  : find the lines that contain if/ else statements without curly brackets
 *      report()        : display the ouput mentioned above
 *       
 * 
 * Note: This class has the constructor that take an arrayList as a parameter,
 * a cloned ArrayList is created to avoid modification within
 * the class affects the original arrayList
 */
public class IfElseStatements{
    List<String> txt;    
    List<String> clonedTxt = new ArrayList<String>();    
    List<Integer> ifLocation= new ArrayList<Integer>();
    List<Integer> elseLocation= new ArrayList<Integer>();
    List<Integer> bracket_missing_if= new ArrayList<Integer>();
    List<Integer> bracket_missing_else= new ArrayList<Integer>();
    
   public IfElseStatements(List<String> text) {
        txt = text;        
    }       
   
   /**
    * 
    * This method 
    *   - scan the original text file, find and record the line numbers of the
    *     lines which contain if/ else statements
    *   - clones the text and remove all empty lines
    *   - scan through the cloned text and find the lines that contain if/ else statements
    *     without the curly bracket on either the same line or the next line
    * 
    * @param List<Integer> ifLocation             : arrayList holds the line numbers of all if statement
    * @param List<Integer> elseLocation           : arrayList holds the line numbers of all else statement
    * @param List<Integer> bracket_missing_if     : arrayList holds the line numbers of if statement without brackets
    * @param List<Integer> bracket_missing_else   : arrayList holds the line numbers of else statement without brackets
    */ 
    
   public void singleIfElse (){         
       // clone the list txt
       clonedTxt.addAll(txt);
       clonedTxt.removeAll(Arrays.asList(null,"")); 
       clonedTxt.add("");  
       int a =0;
       int b = 0;
       // Scan throught the original txt and find all conditional statements    
       for (int i=0; i<txt.size(); i++){
           if (txt.get(i).contains("if")&& !txt.get(i).contains("else")){
               ifLocation.add(i+1);
           }
             
           if ((txt.get(i).contains("else") || txt.get(i).contains("else if"))){
               elseLocation.add(i+1);
             }             
           
       }  
       
       // Scan throught the clone txt and find all conditional statements without brackets
       for (int i=0; i<clonedTxt.size()-1; i++){
           if (clonedTxt.get(i).contains("if") && !clonedTxt.get(i).contains("else" )){
               if (!clonedTxt.get(i).contains("{") && !clonedTxt.get(i+1).contains( "{")){
                  bracket_missing_if.add(ifLocation.get(a));                        
               }       
               a++;
           }
           
           if ((clonedTxt.get(i).contains("else") || clonedTxt.get(i).contains("else if"))){  
               if (!clonedTxt.get(i).contains("{") && !clonedTxt.get(i+1).contains( "{")){
                   bracket_missing_else.add(elseLocation.get(b));  
               }  
               b++;
           }                
        }          
   }
    
   /*
    * This method reports:
    *   - whether if it is an 'if', or 'else' block that is missing brackets
    *   - Line number of the block
    *   
    * @List<Integer> bracket_missing_if     : arrayList holds the line numbers of if statement without brackets
    * @List<Integer> bracket_missing_else   : arrayList holds the line numbers of else statement without brackets
    */
   public void report (){
        System.out.println("Below are if/else statement that don't have curly bracket ");
        System.out.println("If statements on lines:  "+bracket_missing_if);        
        System.out.println("Else statements on lines:  "+bracket_missing_else);
   }      
}


   
   
   
     
