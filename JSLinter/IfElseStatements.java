import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class IfElseStatements{
    List<String> txt;
    List<String> clonedTxt = new ArrayList<String>();    
  
   public IfElseStatements(List<String> text) {
        txt = text;        
    }       
       
   public void singleIfElse (List<Integer> ifLocation, List<Integer> elseLocation, List<Integer> bracket_missingIf, List<Integer> bracket_missingElse){         
       clonedTxt.addAll(txt);
       clonedTxt.removeAll(Arrays.asList(null,"")); 
       clonedTxt.add("");  
       int a =0;
       int b = 0;
           
       for (int i=0; i<txt.size(); i++){
           if (txt.get(i).contains("if")&& !txt.get(i).contains("else")){
               ifLocation.add(i+1);
           }
             
           if ((txt.get(i).contains("else") || txt.get(i).contains("else if"))){
               elseLocation.add(i+1);
             }             
           
       }              
       
       for (int i=0; i<clonedTxt.size()-1; i++){
           if (clonedTxt.get(i).contains("if") && !clonedTxt.get(i).contains("else" )){
               if (!clonedTxt.get(i).contains("{") && !clonedTxt.get(i+1).contains( "{")){
                  bracket_missingIf.add(ifLocation.get(a));                        
               }       
               a++;
           }
           
           if ((clonedTxt.get(i).contains("else") || clonedTxt.get(i).contains("else if"))){  
               if (!clonedTxt.get(i).contains("{") && !clonedTxt.get(i+1).contains( "{")){
                   bracket_missingElse.add(elseLocation.get(b));  
               }  
               b++;
           }
                
        }
          
   }
    
   public void report (List<Integer> bracket_missingIf, List<Integer> bracket_missingElse)
   {
        System.out.println("Below are if/else statement that don't have curly bracket ");
        System.out.println("If statements on lines:  "+bracket_missingIf);        
        System.out.println("Else statements on lines:  "+bracket_missingElse);
   }
      
}


   
   
   
     
