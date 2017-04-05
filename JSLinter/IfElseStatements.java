import java.io.*;
import java.util.*;
class 	IfElseStatements 
{
   
    public IfElseStatements (){

	}
	
    public void match (ArrayList<Integer> ifLocation, ArrayList<Integer> elseLocation, String strLine, int i)
    {
           if (strLine.contains("if")&& !strLine.contains("else")&& !strLine.contains("{")){
               ifLocation.add(i+1);
             }
             
           if ((strLine.contains("else") || strLine.contains("else if"))&& !strLine.contains("{")){
               elseLocation.add(i+1);
             }             
           
    }   
    
    public void report (ArrayList<Integer> ifLocation, ArrayList<Integer> elseLocation)
    {
        System.out.println("Below are if/else statement that don't have curly bracket ");
        System.out.println("If statements on lines:  "+ifLocation);        
        System.out.println("If statements on lines:  "+elseLocation);
    }
    
}
   
   
   
     
