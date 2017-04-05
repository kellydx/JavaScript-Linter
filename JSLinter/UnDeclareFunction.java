import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class UndeclareFunction{
    
    List<String> txt = new ArrayList<String>();
    
    public UndeclareFunction(List<String> text) {
        txt = text;
    }     
    
    public void functionList (List <String> declaredFunction  ){
            String key;
            String patt = "function\\s{0,}([a-zA-Z_$][a-zA-Z0-9_$]*)\\s{0,}\\(\\s{0,}\\S*?\\s{0,}\\).*";
            String patt1 ="(let|var)\\s{0,}([a-zA-Z$_][a-zA-Z0-9$_]*)\\s{0,}\\=\\s{0,}(function)\\s{0,}\\((\\s{0,}\\S*?\\s{0,})\\).*";
            Pattern r = Pattern.compile(patt);
            Pattern r1 = Pattern.compile(patt1);       
        
            for (int j = 0; j <txt.size(); j++){
                //matckher for function dosomething(){} 
                Matcher m = r.matcher(txt.get(j));
                //matcher for var/let doSomething = function {} 
                Matcher m1 = r1.matcher(txt.get(j));            
            
                if (m.find()){
                    key = m.group().split("\\(")[0].split("function")[1].trim();              
                    declaredFunction.add(key);
                } 
                else if (m1.find()){
                    key = m1.group().split("\\=")[0].split("var|let")[1].trim();                 
                    declaredFunction.add(key);
                } 
                                  
            }
            System.out.println(declaredFunction);
    }   

    public void functionCall (HashMap <String, Integer> funcCall ){
        String key;
        // matches function call doSomething();
        String patt3 = "([a-zA-Z_$][a-zA-Z0-9_$]{1,}[\\s]{0,}[\\(]{1,}[\\s]{0,}\\S*?[\\s]{0,}[\\)])";
        // matches function call student.doSomething();
        String patt4 = "\\s{0,}[a-zA-Z_$][a-zA-Z0-9_$]{1,}.[a-zA-Z_$][a-zA-Z0-9_$]{1,}\\s{0,}\\(\\s{0,}\\S*?\\s{0,}\\).*";
        Pattern r2 = Pattern.compile(patt3);
        Pattern r3 = Pattern.compile(patt4);      
        
        for (int j = 0; j <txt.size(); j++){
           //matckher for function dosomething(){} 
           Matcher m2 = r2.matcher(txt.get(j));
            //matcher for var/let doSomething = function {} 
           Matcher m3 = r3.matcher(txt.get(j));
           //Matcher m4 = r4.matcher(txt.get(j));
           boolean flg = containsKey(txt.get(j));
           if (m2.find()&& !flg ){
                System.out.println(txt.get(j));
                key = m2.group().split("\\(")[0].trim();                 
                funcCall.put(key,j+1);
           } 
           else if (m3.find()&& !flg){
                System.out.println(txt.get(j));
                key = m3.group().split("\\=")[0].trim();        
                funcCall.put(key,j+1);
           } 
                  
        }
        
    }

    public boolean containsKey (String line ){
        String [] keyword = {"var", "function", "if", "else", "else if", "let","set"};
        boolean flag = false;
        for (int i=0; i<keyword.length; i++){
            if (line.contains(keyword[i]))
            flag = true;
        }
        return flag;
    }

    public void report (HashMap <String, Integer> funcCall, List <String> declaredFunction ){
       for (int i=0; i<declaredFunction.size(); i++){
           if (funcCall.containsKey(declaredFunction.get(i)))
           funcCall.remove(declaredFunction.get(i));
        }       
       System.out.println("Below are the list of fucntions calls that has not been declared \nand the line numbers which they are declared on");
       System.out.println("Function Name"+ "\t\t\t\t" + "Line Number");
       for (String key : funcCall.keySet()) {
           System.out.println(key + "\t\t\t\t\t  "+funcCall.get(key));            
       }
    }
}