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

    public void createBookDatabase()
    {
        books = new Book[booksData.length];
        for (int i = 0; i < booksData.length; i++)
        {
            
            Book newBook = new Book(booksData[i][0], Integer.parseInt(booksData[i][13]), booksData[i][14], Boolean.parseBoolean(booksData[i][15]), Integer.parseInt(booksData[i][16]));
            Author[] authors = new Author[3];

            authors[0] = new Author();
            authors[0].setFirstName(booksData[i][2]);
            authors[0].setLastName(booksData[i][1]);
            authors[0].setNationality(booksData[i][3]);
            authors[0].setYearOfBirth(Integer.parseInt(booksData[i][4]));

            if (booksData[i][6] != null && !booksData[i][6].isEmpty())
            {
                authors[1] = new Author();
                authors[1].setFirstName(booksData[i][6]);
                authors[1].setLastName(booksData[i][5]);
                authors[1].setNationality(booksData[i][7]);
                authors[1].setYearOfBirth(Integer.parseInt(booksData[i][8]));
            }

            if (booksData[i][10] != null && !booksData[i][10].isEmpty())
            {
                authors[2] = new Author();
                authors[2].setFirstName(booksData[i][10]);
                authors[2].setLastName(booksData[i][9]);
                authors[2].setNationality(booksData[i][11]);
                authors[2].setYearOfBirth(Integer.parseInt(booksData[i][12]));
            }

            newBook.setAuthors(authors);
            books[i] = new Book();
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
            System.out.println("Author 1: " + books[i].getAuthors()[0].getFirstName() + " " + books[i].getAuthors()[0].getLastName() + " (" + books[i].getAuthors()[0].getNationality() + ", Born: " + books[i].getAuthors()[0].getYearOfBirth() + ")");

            if (books[i].getAuthors()[1] != null) 
            {
                System.out.println("Author 2: " + books[i].getAuthors()[1].getFirstName() + " " + books[i].getAuthors()[1].getLastName() + " (" + books[i].getAuthors()[1].getNationality() + ", Born: " + books[i].getAuthors()[1].getYearOfBirth() + ")");
            }

            if (books[i].getAuthors()[2] != null)
            {
                System.out.println("Author 3: " + books[i].getAuthors()[2].getFirstName() + " " + books[i].getAuthors()[2].getLastName() + " (" + books[i].getAuthors()[2].getNationality() + ", Born: " + books[i].getAuthors()[2].getYearOfBirth() + ")");
            }

            System.out.println("Year: " + books[i].getYear());
            System.out.println("ISBN: " + books[i].getIsbn());
            System.out.println("eBook: " + books[i].isEbook());
            System.out.println("Edition: " + books[i].getEdition());
            System.out.println("**************************************\n");
        }
    }

    public void filterBooks(boolean isEbook)
    {
        // Loop through the data to display books in a formatted way
        for (int i = 0; i < booksData.length; i++)
        {
            if (books[i].getTitle() == null)
            {
                continue; // Skip this row if title is null or empty
            }

            if (isEbook) 
            {
                if (books[i].isEbook() == true) {
                    continue; // Skip rows that are a non-ebook
                }
            }
            else 
            {
                if (books[i].isEbook() == false) {
                    continue; // Skip rows that are an ebook
                }
            }

            System.out.println("**************************************");
            System.out.println("Title: " + books[i].getTitle());
            System.out.println("Author 1: " + books[i].getAuthors()[0].getFirstName() + " " + books[i].getAuthors()[0].getLastName() + " (" + books[i].getAuthors()[0].getNationality() + ", Born: " + books[i].getAuthors()[0].getYearOfBirth() + ")");

            if (books[i].getAuthors()[1] != null) 
            {
                System.out.println("Author 2: " + books[i].getAuthors()[1].getFirstName() + " " + books[i].getAuthors()[1].getLastName() + " (" + books[i].getAuthors()[1].getNationality() + ", Born: " + books[i].getAuthors()[1].getYearOfBirth() + ")");
            }

            if (books[i].getAuthors()[2] != null)
            {
                System.out.println("Author 3: " + books[i].getAuthors()[2].getFirstName() + " " + books[i].getAuthors()[2].getLastName() + " (" + books[i].getAuthors()[2].getNationality() + ", Born: " + books[i].getAuthors()[2].getYearOfBirth() + ")");
            }

            System.out.println("Year: " + books[i].getYear());
            System.out.println("ISBN: " + books[i].getIsbn());
            System.out.println("eBook: " + books[i].isEbook());
            System.out.println("Edition: " + books[i].getEdition());
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
        
        for (int i = 0; i < books.length; i++)
        {
            if (books[i] == null)
            {
                continue; // Skip rows with no book title
            }

            //check for matching author 1
            if (books[i].getAuthors()[0].getFirstName().equalsIgnoreCase(firstName) && books[i].getAuthors()[0].getLastName().equalsIgnoreCase(lastName))
            {
                displayBookDetails(books[i], books[i].getAuthors()[0]);
                found = true;
            }

            //check for matching author 2, if exists
            if (books[i].getAuthors()[1] != null) {
                if (books[i].getAuthors()[1].getFirstName().equalsIgnoreCase(firstName) && books[i].getAuthors()[1].getLastName().equalsIgnoreCase(lastName))
                {
                    displayBookDetails(books[i], books[i].getAuthors()[1]);
                    found = true;
                }
            }

            //check for matching author 3, if exists
            if (books[i].getAuthors()[2] != null) {
                if (books[i].getAuthors()[2].getFirstName().equalsIgnoreCase(firstName) && books[i].getAuthors()[2].getLastName().equalsIgnoreCase(lastName))
                {
                    displayBookDetails(books[i], books[i].getAuthors()[2]);
                    found = true;
                }
            }
        }

        if (!found)
        {
            System.out.println("No books found for the author: " + fullname);
        }
    }

    private void displayBookDetails(Book book, Author author)
    {
        System.out.println("Book:");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Published: " + book.getYear());
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("eBook: " + book.isEbook());
        System.out.println("Edition: " + book.getEdition());
        System.out.println("Author:");
        
        // Display author 1 details
        System.out.println("Name: " + author.getFirstName() + " " + author.getLastName());
        System.out.println("Nationality: " + author.getNationality());
        System.out.println("Born: " + author.getYearOfBirth());
        
        System.out.println();
    }
}




