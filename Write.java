import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

import jxl.*;
import jxl.write.*;
import jxl.write.Number;

import jxl.write.biff.RowsExceededException;
/**
 * Writes to the different files.
 */
public class Write
{
    /**
     * Writes to the end of the text document.
     */
    public static void writeText(HashMap h, String newData)
    {
        try
        {
            FileWriter write = new FileWriter("database.txt",true);
            PrintWriter text = new PrintWriter(write);
            text.println(newData);
            text.flush();
            write.close();
        }
        catch (IOException ie)
        {
            System.out.println(ie);
        }
    }

    /**Writes to the end of the excel document.
     */
    public static void writeExcel(HashMap h, String newData) {

        try {
            File exlFile = new File("test_book.xls");
            WritableWorkbook writableWorkbook = Workbook
                .createWorkbook(exlFile);

            WritableSheet writableSheet = writableWorkbook.createSheet(
                    "Data", 0);

            for (int i = 0 ; i < h.size() ; i++)
            {
                int digit = Integer.parseInt((String) h.get(i));
                Number num = new Number(0, i, digit);
                writableSheet.addCell(num);
            }

            writableSheet.addCell(new Number(0,h.size(),Integer.parseInt(newData)));

            //Write and close the workbook
            writableWorkbook.write();
            writableWorkbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes to the database and pust it in order.
     */
    public static void writeDatabase(HashMap h, String newData) throws Exception
    {
        Connection connection = null;
        try
        {
            // Register MySQL JDBC driver to be known by 
            // the DriverManager object.
            Class.forName("com.mysql.jdbc.Driver");

            // Get a connection to database. We prepare the 
            // connection information here such as database 
            // url, user and password.
            String url = "jdbc:mysql://localhost/myDatabase";
            String user = "Evan";
            String password = "apcs";
            connection = DriverManager.getConnection(url, 
                user, password);

            // Create a statement object instance from the 
            // connection
            Statement stmt = connection.createStatement();

            // We are going to execute an insert statement. 
            // First you have to create a table that has an 
            // ID, NAME and ADDRESS field. For ID you can use 
            // an auto number, while NAME and ADDRESS are 
            // VARCHAR fields.
            String sql = "INSERT INTO data (Sales) " +
                "VALUES ('"+ newData + "')";

            // Call an execute method in the statement object 
            // and passed the sql or query string to it.
            stmt.execute(sql);

            // After this statement is executed you'll have a 
            // record in your users table.
        } catch (ClassNotFoundException e)
        {

        } catch (SQLException e)
        {

        } finally
        {
            if (connection != null)
            {
                connection.close();
            }
        }
    }

}


