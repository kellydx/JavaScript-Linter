import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class UnDeclareFunction 
{
   
    public void functionList (HashMap <String, Integer> functionList, ArrayList<String> txt  ){
        String key = "";
        String patt = "function\\s{0,}([a-zA-Z_$][a-zA-Z0-9_$]*)\\s{0,}\\(\\s{0,}\\S*?\\s{0,}\\).*";
        String patt1 ="(let|var)[\\s]{0,}([a-zA-Z_$][a-zA-Z0-9_$]*)[\\s]{0,}[\\=][\\s]{0,}(function)[\\s]{0,}[\\(]([\\s\\S]*?)[\\)].*";
        Pattern r = Pattern.lk;compile(patt);
        Pattern r1 = Pattern.compile(patt1);
       
        
        for (int j = 0; j <txt.size(); j++){
            //matcher for function dosomething(){} 
            Matcher m = r.matcher(txt.get(j));
            //matcher for var/let doSomething = function {} 
            Matcher m1 = r1.matcher(txt.get(j));
            if (m.find()){
                key = m.group().split("\\(")[0].split("function")[1].trim();                 
                if (functionList.get(key) == null) {
                        functionList.put(key,1);
                        //location.put(key,j+1);              
                    } 
                    else if (functionList.get(functionList.get(j)) >= 1) {
                        functionList.put(key,functionList.get(key) + 1);
                    }
               
                }
                
            else if (m1.find()){
                key = m.group().split("\\=")[0].split("var")[1].trim();                 
                if (functionList.get(key) == null) {
                        functionList.put(key,1);
                        //location.put(key,j+1);              
                    } 
                    else if (functionList.get(functionList.get(j)) >= 1) {
                        functionList.put(key,functionList.get(key) + 1);
                    }
               
                }
   
   
       
    }

}

}


    
