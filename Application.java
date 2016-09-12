import java.awt.Cursor;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/**
 * Main GUI
 */
public class Application extends JFrame implements ActionListener
{
    private JMenuBar menu;
    private JMenu m1,m2,m3;
    private JMenuItem AddNew,Search,Display,Delete,Exit,Modify,Help;
    private JButton
    btnAddNew,btnSearch,btnDelete,btnDisplay,btnModify,btnExit;
    private JPanel pMain,pSouth,pNorth,pCenter;
    private JTextArea tac;
    private JLabel aveL,medianL,sumL,sdL,rangeL,label;
    private boolean load = false;
    private HashMap hash = new HashMap();
    private int typeOfData = 99;

    public Application()
    {
        //menu bar and menu item initialization

        label = new JLabel("`                                                                          Please import a Database to begin (Select 'New Database') ! Buttons will become pressable after it has been imported.");
        AddNew = new JMenuItem("X");

        Search = new JMenuItem("Y");

        //text area initialization
        //tac = new JTextArea(2,3);

        //tac.setText("By: Evan Honeysett");

        //tac.setForeground(Color.white);
        //tac.setEditable(true);

        //button intialization
        btnAddNew = new JButton("Stats");
        btnAddNew.setToolTipText("View Statistics from the Database");
        btnAddNew.addActionListener(this);

        btnDelete = new JButton("Retrieve Data");
        btnDelete.setToolTipText("Get's data based on its key");
        btnDelete.addActionListener(this);

        btnDisplay = new JButton("Add Data");
        btnDisplay.setToolTipText("Add data to the Database");
        btnDisplay.addActionListener(this);

        btnModify = new JButton("New Database");
        btnModify.setToolTipText("Loads a new Database");
        btnModify.addActionListener(this);

        btnExit = new JButton("EXIT");
        btnExit.setToolTipText("Out of Program");
        btnExit.addActionListener(this);

        //initialization panel
        pMain = new JPanel();
        pNorth = new JPanel();
        pSouth = new JPanel();
        pCenter = new JPanel();

        //label = new JLabel(new
        //    ImageIcon("//H:/test.jpg"),JLabel.CENTER);

        //add menuitem to menu

        pMain.add(btnAddNew);
        pMain.add(btnDelete);
        pMain.add(btnDisplay);
        pMain.add(btnModify);
        pMain.add(btnExit);

        pMain.setLayout(new BoxLayout(pMain,BoxLayout.Y_AXIS));
        pMain.setBorder(BorderFactory.createTitledBorder("Options"));
        pMain.setLayout(new GridLayout(5,1));
        pMain.setBackground(Color.white);

        pCenter.setBackground(Color.white);
        pCenter.setLayout(new BoxLayout(pMain,BoxLayout.Y_AXIS));
        pCenter.setLayout(new GridLayout(1,1));
        pCenter.add(label);
        //pCenter.add(tac);

        pNorth.setBackground(Color.white);

        this.getContentPane().add(pMain,"West");
        this.getContentPane().add(pCenter,"Center");
        this.getContentPane().add(pNorth,"North");

        this.setSize(1500,300);
        this.setResizable(true);
        this.setLocation(150,150);
        this.setTitle("Database GUI");
        this.show();
    }

    public static void main(String args[])
    {
        Application g = new Application();
    }

    public void actionPerformed(ActionEvent evt){
        if(evt.getActionCommand().equals("EXIT"))
        {
            setVisible(false);
            dispose();
        }

        if(evt.getActionCommand().equals("Map"))
        {
            if(load)
            {
                label.setText("Test!");
            }

            //Icon g = new ImageIcon("//H:/test3.jpg");
            //label.setIcon(g);
            //pCenter.repaint();
            //repaint();
        }

        if(evt.getActionCommand().equals("Stats"))
        {
            if(load)
            {
                double average = Calc.ave(hash);
                double median = Calc.median(hash);
                double sum = Calc.sum(hash);
                int range = Calc.range(hash);
                double sd = Calc.sd(hash);

                String aveString = String.valueOf(average);
                String medString = String.valueOf(median);
                String sumString = String.valueOf(sum);
                String sdString = String.valueOf(sd);

                aveL = new JLabel("Average: " + aveString);
                medianL = new JLabel("Median: " + medString);
                sumL = new JLabel("Sum: " + sumString);
                rangeL = new JLabel("Range: " + range);
                sdL = new JLabel("Standard Deviation: " + sdString);

                pCenter.removeAll();
                pCenter.add(aveL);
                pCenter.add(medianL);
                pCenter.add(sumL);
                pCenter.add(rangeL);
                pCenter.add(sdL);
                pCenter.updateUI();
                setVisible(true);

            }
        }

        if(evt.getActionCommand().equals("Retrieve Data"))
        {
            if(load)
            {
                String getData = JOptionPane.showInputDialog("Enter key:");
                if(getData != null)
                {
                    int find = Integer.parseInt(getData);
                    if(find < hash.size())
                    {
                        label.setText("Here is the data associated wuth that key: " + hash.get(find));
                        pCenter.removeAll();
                        pCenter.add(label);
                        pCenter.updateUI();
                        setVisible(true);
                    }
                }
            }
        }

        if(evt.getActionCommand().equals("Add Data"))
        {
            if(load)
            {
                String data = JOptionPane.showInputDialog("Data to add:");
                if(data != null)
                {
                    if(typeOfData == 0)
                    {
                        Write.writeText(hash,data);
                        JOptionPane.showMessageDialog(null, "Data was added! Just Select 'New Database' again to refresh the data!\nOr look inside of the corresponding file.");

                    }
                    else if(typeOfData == 1)
                    {
                        Write.writeExcel(hash,data);
                        JOptionPane.showMessageDialog(null, "Data was added! Just Select 'New Database' again to refresh the data!\nOr look inside of the corresponding file.");

                    }
                    else if(typeOfData == 2)
                    {
                        try{
                            Write.writeDatabase(hash,data);
                            JOptionPane.showMessageDialog(null, "Data was added! Just Select 'New Database' again to refresh the data!\nOr look inside of the corresponding file.");

                        }
                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Oops! Something went wrong! Please try again!");

                        }
                    }
                }
            }
        }

        if(evt.getActionCommand().equals("New Database")) 
        {
            boolean valid = false;
            String getType = JOptionPane.showInputDialog("Enter type of database (text or excel or database):");
            while(!(valid) && getType != null )
            {
                load = false;
                if( getType.equals("text" ))
                {
                    String getName = JOptionPane.showInputDialog("Enter the name of the text document:\nMust be in directory and include .txt\n(I provided database.txt)");
                    try 
                    {
                        hash.clear();
                        boolean isLoaded = isTextLoaded(hash,getName);
                        if(isLoaded)
                        {
                            valid = true;
                            load = true;
                            typeOfData = 0;
                        }
                    }
                    catch (FileNotFoundException e) {
                    }
                }
                else if( getType.equals("excel") )
                {
                    String getName = JOptionPane.showInputDialog("Enter the name of the excel document:\nMust be in directory and include .xls (Microsoft '97-'03)\n(I provided test_book.xls)");
                    try 
                    {
                        hash.clear();
                        boolean isLoaded = isExcelLoaded(hash,getName);
                        if(isLoaded)
                        {
                            valid = true;
                            load = true;
                            typeOfData = 1;
                        }
                    }
                    catch (IOException e) {
                    }
                }
                else if( getType.equals("database") )
                {
                    String getName = JOptionPane.showInputDialog("Enter the credentials of the MySQL Database:\nWhere it is located...\n(I provided jdbc:mysql://localhost:3306/mydatabase)");
                    try
                    {
                        hash.clear();
                        boolean isLoaded = isDatabaseLoaded( hash, getName);
                        if(isLoaded)
                        {
                            valid = true;
                            load = true;
                            typeOfData = 2;
                        }
                    }
                    catch (Exception e)
                    {

                    }
                }

                else
                {
                    getType = JOptionPane.showInputDialog("That type is not valid!\nPlease try again:");
                }
            }
        }
    }

    /**
     * Checks if text document was found and loaded.
     */
    public boolean isTextLoaded(HashMap h, String getName) throws FileNotFoundException 
    {
        return ReadText.read(h,getName); 
    }

    /**
     * Checks if excel document was found and loaded.
     */
    public boolean isExcelLoaded(HashMap h, String getName) throws IOException 
    {
        ReadExcel f = new ReadExcel();
        f.setInputFile(getName);
        boolean r = f.read(h);
        return r;
    }

    /**
     * Checks if the database was found and loaded.
     */
    public boolean isDatabaseLoaded(HashMap h, String location) throws Exception
    {
        boolean con = ReadSQL.getConnection(location,hash);
        return con;
    }
}

