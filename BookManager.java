import java.io.*;
import java.util.Spliterators;


public class BookManager {

    public String[][] booksData;

    public void readFile(String pFileName)
    {
        FileInputStream fileStream = null;
        InputStreamReader isr;
        BufferedReader bufRdr;
        String line;
        try
        {
            fileStream = new FileInputStream(pFileName);
            isr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(isr);

            //count rows
            int rows = 0;
            while (bufRdr.readLine() != null)
            {
                rows++;
            }
            fileStream.close();

            fileStream = new FileInputStream(pFileName);
            isr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(isr);

            //read header and count columns
            line = bufRdr.readLine();
            String[] headers = line.split(",");
            int columns = headers.length;

            //initialise 2D array
            booksData = new String[rows][columns];

            int i = 0;
            while((line = bufRdr.readLine()) != null)
            {
                String[] values = line.split(",");

                for (int j = 0; j < values.length; j++)
                {
                    booksData[i][j] = values[j];
                }
                i++;
            }
            fileStream.close();
        }
        catch(IOException errorDetails)
        {
            if(fileStream != null)
            {
                try
                {
                    fileStream.close();
                }
                catch(IOException ex2)
                {
                }
            }
            System.out.println("Error in file processing: " + errorDetails.getMessage());
        }
    }

    public void viewAllBooks()
    {
        // Loop through the data to display books in a formatted way
        for (int i = 0; i < booksData.length; i++)
        {
            if (booksData[i][0] == null || booksData[i][0].isEmpty())
            {
                continue; // Skip this row if title is null or empty
            }
            System.out.println("**************************************");
            System.out.println("Title: " + booksData[i][0]);
            System.out.println("Author 1: " + booksData[i][2] + " " + booksData[i][1] + " (" + booksData[i][3] + ", Born: " + booksData[i][4] + ")");

            if (booksData[i][6] != null && !booksData[i][6].isEmpty())
            {
                System.out.println("Author 2: " + booksData[i][6] + " " + booksData[i][5] + " (" + booksData[i][7] + ", Born: " + booksData[i][8] + ")");
            }

            if (booksData[i][10] != null && !booksData[i][10].isEmpty())
            {
                System.out.println("Author 3: " + booksData[i][10] + " " + booksData[i][9] + " (" + booksData[i][11] + ", Born: " + booksData[i][12] + ")");
            }

            System.out.println("Year: " + booksData[i][13]);
            System.out.println("ISBN: " + booksData[i][14]);
            System.out.println("eBook: " + (booksData[i][15].equalsIgnoreCase("true") ? "Yes" : "No"));
            System.out.println("Edition: " + booksData[i][16]);
            System.out.println("**************************************\n");
        }
    }

}



