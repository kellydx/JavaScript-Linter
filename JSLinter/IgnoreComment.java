import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * This class scan the text file for comments line
 * and replace them with an empty String
 * 
 * @param line      : each line of the text
 * @return          : the empty line
 */
public class IgnoreComment
{
    //pattern for comments
    Pattern multilineCommentPattern = Pattern.compile("(\\/*\\.*?\\*\\.*?)");
    Pattern singleLineCommentPattern = Pattern.compile("\\/\\/(.*)$");
    
    Matcher c;
    Matcher c1;
    public String ignoreComments(String line)
    {
        c = multilineCommentPattern.matcher(line);
        c1 = singleLineCommentPattern.matcher(line);
        // if a comment line is found, replace it with an empty string
        if (c.find( )||c1.find()) {
            line = "";
        }        
        return line;       
    }
}
