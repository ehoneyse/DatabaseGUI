Here is the basics on the GUI.

First off, run the main method of the Application class.
This brings you to a GUI where the data is held.

To start, select New Database, define the type (it currently supports text, excel, and MySQL)
    I have provided the files...
        database.txt (text)
        test_book.xls (excel)
        jdbc:mysql://localhost:3306/mydatabase (database location)
            **Note**
            ** Database will only read from the sales column in order to not overload the database with querys (I crashed my internet a few times trying) **
        
Once one of them is imported, you will be able to select the other buttons.
    Stats:
        Basic information about the data.
    Retrieve Data:
        Gets data based on its key
            The key goes in numerical order, so if the number you want is 7th in the list use key 6.
    Add Data:
        Adds data to the type of files.
            **Note**
            **  You have to select New Database again and re-import the files for updates to occur. **
    Exit:
        Exits out of the program.

For best results, dont change the size of the frame too much, because the stats are printed in a line.

That's all!

        