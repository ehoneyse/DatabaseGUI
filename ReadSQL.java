import java.sql.*;
import java.util.*;
import java.io.*;
    /**
     * read the SQL database.
     * Username: Evan
     * Password: apcs
     * Off of standard driver.
     * Reads off of the sales line ONLY.
     */
public class ReadSQL
{
    public static boolean getConnection(String host,HashMap h) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver") ;
            Connection conn = null;
            Properties connectionProps = new Properties();
            connectionProps.put("user", "Evan");
            connectionProps.put("password", "apcs" );

            conn = DriverManager.getConnection(host,connectionProps);
            //System.out.println(conn);
            int counter = 0;
            Statement stmt = conn.createStatement() ;
            String query = "select Sales from data ;";
            ResultSet rs = stmt.executeQuery(query) ;
            while (rs.next()) {
                h.put(counter,rs.getString(1)); 
                counter++;
            }
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }
}

