import java.util.*;
import java.io.*;
/**
 * Read in a text file.
 * Name is specified by user.
 */
public class ReadText
{
    public static boolean read(HashMap h,String name) throws FileNotFoundException
    {
        int counter = 0;
        try {
            Scanner scanner = new Scanner(new File(name));
            while (scanner.hasNext()) 
            {
            h.put(counter, scanner.next());
            counter++;
            }
        } catch (FileNotFoundException ex ) {
            return false;
        }
        return true;
    }
}