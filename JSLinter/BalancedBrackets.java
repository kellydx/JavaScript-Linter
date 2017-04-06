import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.util.*;
/**
 * 
 * 
 * 
 */
public class BalancedBrackets {
   public boolean checkBalancedBrackets(){
       Stack<Character> stack = new Stack<Character>();
        try{
        //read the entire text into an arrayList
        FileInputStream fstream = new FileInputStream("sketch.js");
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        boolean isBalanced = true;       
        
        while ((strLine = br.readLine()) != null)   {
           
           for (int i = 0; i < strLine.length(); i++){
                char cursor = strLine.charAt(i);
                System.out.println(cursor);
                if (cursor == '{'){
                    stack.push(cursor);                    
                }

                else if (cursor == '}'){                    
                    if (stack.isEmpty()){                         
                         return false;                            
                    }
                    
                    if(stack.pop() != '{') {
                        return false;
                    }
                }
            }
        }       
        in.close();        
       }    
       catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }      
         
       return stack.isEmpty();
   }
}
        



