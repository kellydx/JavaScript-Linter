import java.io.*;
import java.util.*;


/**
 * 
 * This Brackets class locate missing/ extra curly brackets throughout the code and report
 *      - If it is a missing or extra bracket
 *      - if it is a missing bracket:
 *          + Report the line number and the statement of the openning bracket
 *      - If ot is an extra bracket
 *          + Report the line number the extra bracket is on
 */


class Bracket{
    int line;
    String statement;
    
    public Bracket(){
        this.line = line;
        this.statement = statement;
    }
	
	public void setStatement(String str){
		this.statement = str;
	}

	public String getStatement(){
		return this.statement;
	}

	public void setLine(int l){
		this.line = l;
	}

	public int getLine(){
		return this.line;
	}	
	
	/**
	 * Method chechBalancedBracket:
	 *     - Scan every line of the text
	 *     - If a opening bracket is found, create an object with the line number and the line of code
	 *       and push it to a stack
	 *     - If a closing bracket is found while the stack is empty, it is an extra bracket
	 *     - If a closing bractket is found and the stack has an opening bracket object 
	 *       in it, remove the bracket object from the stack
	 *      - At the end, any brackets object that is still left in the stack, 
	 *        which means they can not find a matching bracket, are missing brackets
	 * 
	 * @param List <String> txt    : the entire text file read into an arrayList
	 */
	
	public void checkBalancedBrackets (List <String> txt ) {		
		Stack<Bracket> stack = new Stack<Bracket>();
		
		for (int i=0; i<txt.size(); i++){			
			if(txt.get(i).contains("{")){
				Bracket brk = new Bracket();
				brk.setLine(i+1);
				brk.setStatement(txt.get(i));			
				stack.push(brk);
			}
			
			if(txt.get(i).contains("}")){
				if(stack.empty()){
					System.out.println("Extra curly bracket found on line "+(i+1));
				}
				else{
					stack.pop();
				}
			}			
		}
		
		// any brackets object left in the stack are missing brackets
		while(!stack.empty()){
			System.out.println(stack.pop());
		}		
	}
	
	public String toString(){
	    return("Missing curly bracket corresponded to curly bracket found on line "+this.line+" : "+ this.statement);
	   }
	
}