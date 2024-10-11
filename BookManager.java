import java.io.*;


public class BookManager
{

    public String[][] booksData;
    public Book[] books;

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

    public void createBookDatabase(){
        for (int i = 0; i < booksData.length; i++)
        {
            
            Book newBook = new Book(booksData[i][0], Integer.parseInt(booksData[i][13]), booksData[i][14], Boolean.parseBoolean(booksData[i][15]), Integer.parseInt(booksData[i][16]));
            Author[] authors = new Author[3];

            authors[0].setFirstName(booksData[i][2]);
            authors[0].setLastName(booksData[i][1]);
            authors[0].setNationality(booksData[i][3]);
            authors[0].setYearOfBirth(Integer.parseInt(booksData[i][4]));

            if (booksData[i][6] != null && !booksData[i][6].isEmpty())
            {
                authors[1].setFirstName(booksData[i][6]);
                authors[1].setLastName(booksData[i][5]);
                authors[1].setNationality(booksData[i][7]);
                authors[1].setYearOfBirth(Integer.parseInt(booksData[i][8]));
            }

            if (booksData[i][10] != null && !booksData[i][10].isEmpty())
            {
                authors[1].setFirstName(booksData[i][10]);
                authors[1].setLastName(booksData[i][9]);
                authors[1].setNationality(booksData[i][11]);
                authors[1].setYearOfBirth(Integer.parseInt(booksData[i][12]));
            }

            newBook.setAuthors(authors);
            books[i] = newBook;
        }
    }

    public void viewAllBooks()
    {
        // Loop through the data to display books in a formatted way
        for (int i = 0; i < books.length; i++)
        {
            if (books[i].getTitle() == null)
            {
                continue; // Skip this row if title is null or empty
            }
            System.out.println("**************************************");
            System.out.println("Title: " + books[i].getTitle());
            System.out.println("Author 1: " + books[i].getAuthors()[0].getFirstName() + " " + books[i].getAuthors()[0].getLastName() + " (" + booksData[i][3] + ", Born: " + booksData[i][4] + ")");

            if (booksData[i][6] != null && !booksData[i][6].isEmpty())
            {
                System.out.println("Author 2: " + booksData[i][6] + " " + booksData[i][5] + " (" + booksData[i][7] + ", Born: " + booksData[i][8] + ")");
            }

            if (booksData[i][10] != null && !booksData[i][10].isEmpty())
            {
                System.out.println("Author 3: " + booksData[i][10] + " " + booksData[i][9] + " (" + booksData[i][11] + ", Born: " + booksData[i][12] + ")");
            }

            System.out.println("Year: " + books[i].getYear());
            System.out.println("ISBN: " + booksData[i][14]);
            System.out.println("eBook: " + books[i].isEbook());
            System.out.println("Edition: " + booksData[i][16]);
            System.out.println("**************************************\n");
        }
    }

    public void filterBooks(boolean isEbook)
    {
        // Loop through the data to display books in a formatted way
        for (int i = 0; i < booksData.length; i++)
        {
            if (booksData[i][0] == null || booksData[i][0].isEmpty())
            {
                continue; // Skip this row if title is null or empty
            }

            if (isEbook) 
            {
                if (books[i].isEbook() == false) {
                    continue; // Skip rows that are a non-ebook
                }
            }
            else 
            {
                if (booksData[i][15].equals("TRUE")) {
                    continue; // Skip rows that are an ebook
                }
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

    public void viewBooksByAuthor(String fullname)
    {
        String[] nameParts = fullname.split(" ");

        if (nameParts.length < 2)
        {
            System.out.println("Please enter both first name and last name.");
            return;
        }

        String firstName = nameParts[0];
        String lastName = nameParts[1];

        boolean found = false;

        System.out.println();
        
        for (int i = 0; i < booksData.length; i++)
        {
            if (booksData[i][0] == null || booksData[i][0].isEmpty())
            {
                continue; // Skip rows with no book title
            }

            //check for matching author 1
            if (booksData[i][2].equalsIgnoreCase(firstName) && booksData[i][1].equalsIgnoreCase(lastName))
            {
                displayBookDetails(i);
                found = true;
            }

            //check for matching author 2, if exists
            if (booksData[i][6] != null && !booksData[i][6].isEmpty() &&
            booksData[i][6].equalsIgnoreCase(firstName) && booksData[i][5].equalsIgnoreCase(lastName))
            {
                displayBookDetails(i);
                found = true;
            }

            //check for matching author 3, if exists
            if (booksData[i][10] != null && !booksData[i][10].isEmpty() &&
            booksData[i][10].equalsIgnoreCase(firstName) && booksData[i][9].equalsIgnoreCase(lastName))
            {
                displayBookDetails(i);
                found = true;
            }
        }

        if (!found)
        {
            System.out.println("No books found for the author: " + fullname);
        }
    }

    private void displayBookDetails(int bookRow)
    {
        System.out.println("Book:");
        System.out.println("Title: " + booksData[bookRow][0]);
        System.out.println("Published: " + booksData[bookRow][13]);
        System.out.println("ISBN: " + booksData[bookRow][14]);
        System.out.println("eBook: " + (booksData[bookRow][15].equalsIgnoreCase("true") ? "true" : "false"));
        System.out.println("Edition: " + booksData[bookRow][16]);
        System.out.println("Author:");
        
        // Display author 1 details
        System.out.println("Name: " + booksData[bookRow][2] + " " + booksData[bookRow][1]);
        System.out.println("Nationality: " + booksData[bookRow][3]);
        System.out.println("Born: " + booksData[bookRow][4]);
        
        System.out.println();
    }
}




