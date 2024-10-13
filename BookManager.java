/******************************************
 * Author: Celerina Reyes                 *
 * Created: 09/10/2024                    *
 * Purpose: Manages the library's books   *
 *****************************************/

import java.io.*;

public class BookManager
{

    public String[][] booksData; // This is the 2D array to store RAW book data from the CSV file :)
    public Book[] books; // This is the array to store Boob OBJECTS after processing the data from the CSV file :)

    // NAME: readFile
    // IMPORT: pFileName
    // EXPORT: none
    // PURPOSE: reads the CSV file and stores the data in a 2D array :)
    public void readFile(String pFileName)
    {
        FileInputStream fileStream = null; // For reading the CSV file :)
        InputStreamReader isr;
        BufferedReader bufRdr;
        String line;
        try
        {
            // Open the file for reading :)
            fileStream = new FileInputStream(pFileName);
            isr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(isr);

            // This counts the number of ROWS in the CSV file :J
            int rows = 0;
            while (bufRdr.readLine() != null)
            {
                rows++;
            }
            fileStream.close(); 
            
            // Reopen the file for reading again :)
            fileStream = new FileInputStream(pFileName);
            isr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(isr);

            // This counts the number of COLUMNS in the CSV file :P
            line = bufRdr.readLine();
            String[] headers = line.split(",");
            int columns = headers.length;

            // This initialises the booksData 2D array that store the CSV data :o
            booksData = new String[rows][columns];

            int i = 0;
            // Now this will read the actual data line by line and stores it in the 2D array
            while((line = bufRdr.readLine()) != null)
            {
                String[] values = line.split(",");

                for (int j = 0; j < values.length; j++)
                {
                    booksData[i][j] = values[j]; // This makes sure that each value is stored in the correct position
                }
                i++;
            }
            fileStream.close();
        }
        catch(IOException errorDetails) // Handles file error (oopsies)
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

    // NAME: createBookDatabase
    // IMPORT: none
    // EXPORT: none
    // PURPOSE: processes the data read from the CSV and then creates Book objects :o
    public void createBookDatabase()
    {
        //initialise the books array
        books = new Book[booksData.length];

        for (int i = 0; i < booksData.length; i++)
        {
            if (booksData[i][0] == null)
            {
                continue; // So empty rows are skipped :))
            }

            // This will create a new Book object for each row of data...
            Book newBook = new Book(booksData[i][0], Integer.parseInt(booksData[i][13]), booksData[i][14], Boolean.parseBoolean(booksData[i][15]), Integer.parseInt(booksData[i][16]));
            
            // This Initialises the array for up to 3 AUTHORS ONLY :p
            Author[] newAuthors = new Author[3];

            // This will create and set the FIRST AUTHOR :o
            newAuthors[0] = new Author();
            newAuthors[0].setFirstName(booksData[i][2]);
            newAuthors[0].setLastName(booksData[i][1]);
            newAuthors[0].setNationality(booksData[i][3]);
            newAuthors[0].setYearOfBirth(Integer.parseInt(booksData[i][4]));

            // If a second author is detected, this will create and set the SECOND AUTHOR :O
            if (booksData[i][6] != null && !booksData[i][6].isEmpty())
            {
                newAuthors[1] = new Author();
                newAuthors[1].setFirstName(booksData[i][6]);
                newAuthors[1].setLastName(booksData[i][5]);
                newAuthors[1].setNationality(booksData[i][7]);
                newAuthors[1].setYearOfBirth(Integer.parseInt(booksData[i][8]));
            }

            // If a third author is detected, this will create and set the THIRD AUHTOR :D
            if (booksData[i][10] != null && !booksData[i][10].isEmpty())
            {
                newAuthors[2] = new Author();
                newAuthors[2].setFirstName(booksData[i][10]);
                newAuthors[2].setLastName(booksData[i][9]);
                newAuthors[2].setNationality(booksData[i][11]);
                newAuthors[2].setYearOfBirth(Integer.parseInt(booksData[i][12]));
            }

            newBook.setAuthors(newAuthors); // Set all of the authors of the book
            books[i] = new Book();
            books[i] = newBook;
        }
    }

    // NAME: viewAllBooks
    // IMPORT: none
    // EXPORT: none
    // PURPOSE: prints the details of ALL the books of the library :)
    public void viewAllBooks()
    {
        for (int i = 0; i < books.length; i++)
        {
            if (books[i] == null)
            {
                continue; // NULL entries will be SKIPPED >:D
            }

            System.out.println();
            System.out.println("Title: " + books[i].getTitle());
            System.out.println("Author 1: " + books[i].getAuthors()[0].getFirstName() + " " + books[i].getAuthors()[0].getLastName() + " (" + books[i].getAuthors()[0].getNationality() + ", Born: " + books[i].getAuthors()[0].getYearOfBirth() + ")");

            // If theres more than 1 author, their details will also be printed
            if ((books[i].getAuthors().length > 1) && (books[i].getAuthors()[1] != null))
            {
                System.out.println("Author 2: " + books[i].getAuthors()[1].getFirstName() + " " + books[i].getAuthors()[1].getLastName() + " (" + books[i].getAuthors()[1].getNationality() + ", Born: " + books[i].getAuthors()[1].getYearOfBirth() + ")");
            }

            if ((books[i].getAuthors().length > 2) && (books[i].getAuthors()[2] != null))
            {
                System.out.println("Author 3: " + books[i].getAuthors()[2].getFirstName() + " " + books[i].getAuthors()[2].getLastName() + " (" + books[i].getAuthors()[2].getNationality() + ", Born: " + books[i].getAuthors()[2].getYearOfBirth() + ")");
            }

            System.out.println("Year: " + books[i].getYear());
            System.out.println("ISBN: " + books[i].getIsbn());
            System.out.println("eBook: " + books[i].isEbook());
            System.out.println("Edition: " + books[i].getEdition());
            System.out.println();
        }
    }

    // NAME: filterBooks
    // IMPORT: isBook (boolean)
    // EXPORT: none
    // PURPOSE: filters books based on if it is an eBook or not :O
    public void filterBooks(boolean isEbook)
    {
        for (int i = 0; i < books.length; i++)
        {
            if (books[i] == null)
            {
                continue; // NULL entries will be SKIPPED >:D
            }

            // This will only display eBooks or only display non-eBooks depending on the user's input :)
            if (isEbook) 
            {
                if (books[i].isEbook() == false) {
                    continue;
                }
            }
            else 
            {
                if (books[i].isEbook() == true) {
                    continue;
                }
            }

            // This will print the details of the filtered books :)
            System.out.println();
            System.out.println("Title: " + books[i].getTitle());
            System.out.println("Author 1: " + books[i].getAuthors()[0].getFirstName() + " " + books[i].getAuthors()[0].getLastName() + " (" + books[i].getAuthors()[0].getNationality() + ", Born: " + books[i].getAuthors()[0].getYearOfBirth() + ")");

            if ((books[i].getAuthors().length > 1) && (books[i].getAuthors()[1] != null)) 
            {
                System.out.println("Author 2: " + books[i].getAuthors()[1].getFirstName() + " " + books[i].getAuthors()[1].getLastName() + " (" + books[i].getAuthors()[1].getNationality() + ", Born: " + books[i].getAuthors()[1].getYearOfBirth() + ")");
            }

            if ((books[i].getAuthors().length > 2) && (books[i].getAuthors()[2] != null))
            {
                System.out.println("Author 3: " + books[i].getAuthors()[2].getFirstName() + " " + books[i].getAuthors()[2].getLastName() + " (" + books[i].getAuthors()[2].getNationality() + ", Born: " + books[i].getAuthors()[2].getYearOfBirth() + ")");
            }

            System.out.println("Year: " + books[i].getYear());
            System.out.println("ISBN: " + books[i].getIsbn());
            System.out.println("eBook: " + books[i].isEbook());
            System.out.println("Edition: " + books[i].getEdition());
            System.out.println();
        }
    }

    // NAME: viewBooksByAuthor
    // IMPORT: fullname (String)
    // EXPORT: found (boolean)
    // PURPOSE: finds and shows the user all books written by a SPECIFIC author :D
    public boolean viewBooksByAuthor(String fullname)
    {
        //This will split the full name to get the first and last names :)
        String[] nameParts = fullname.split(" ");

        if (nameParts.length < 2)
        {
            System.out.println("Please enter both first name and last name!");
            return false; // If the name is incomplete
        }

        String firstName = nameParts[0];
        String lastName = nameParts[1];

        boolean found = false;
        System.out.println();
        
        // This will search through the all the books in the library to find the ones by the given author :oo
        for (int i = 0; i < books.length; i++)
        {
            if (books[i] == null)
            {
                continue; // NULL entries will be SKIPPED >:)
            }

            // This will check for matching author 1... 
            if (books[i].getAuthors()[0].getFirstName().equals(firstName) && books[i].getAuthors()[0].getLastName().equals(lastName))
            {
                displayBookDetails(books[i], books[i].getAuthors()[0]);
                found = true;
            }

            // If there is an existing second author, it will check for a match... 
            if ((books[i].getAuthors().length > 1) && (books[i].getAuthors()[1] != null))
            {
                if (books[i].getAuthors()[1].getFirstName().equals(firstName) && books[i].getAuthors()[1].getLastName().equals(lastName))
                {
                    displayBookDetails(books[i], books[i].getAuthors()[1]);
                    found = true;
                }
            }

            // If there is an existing third author, it will check for a match... 
            if ((books[i].getAuthors().length > 2) && (books[i].getAuthors()[2] != null)) {
                if (books[i].getAuthors()[2].getFirstName().equals(firstName) && books[i].getAuthors()[2].getLastName().equals(lastName))
                {
                    displayBookDetails(books[i], books[i].getAuthors()[2]);
                    found = true;
                }
            }
        }

        // If there is no books by that author...
        if(!found)
        {
            System.out.println("No books found for the author " + fullname + "." + " :( ");
            System.out.println("Please try again... :)");
        }

        return found;
    }

    // NAME: displayBookDetails
    // IMPORT: book (Book), author (Author)
    // EXPORT: none
    // PURPOSE: prints details about a specific book and the author(s) :P
    private void displayBookDetails(Book book, Author author)
    {
        System.out.println("Book:");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Published: " + book.getYear());
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("eBook: " + book.isEbook());
        System.out.println("Edition: " + book.getEdition());
        System.out.println("Author:");
        
        // This will display author 1 details
        System.out.println("Name: " + author.getFirstName() + " " + author.getLastName());
        System.out.println("Nationality: " + author.getNationality());
        System.out.println("Born: " + author.getYearOfBirth());
        
        System.out.println();
    }

    // NAME: addBook
    // IMPORT: title (String), year (int), isbn (String), ebook (String), edition (int), authors (Author[])
    // EXPORT: none
    // PURPOSE: gives the user the ability to create a new Book object. This adds it to the library
    public void addBook(String title, int year, String isbn, String ebook, int edition, Author[] authors)
    {
        // This one was tough so...
        // 1. Convert the eBook string to a boolean value...
        boolean isEbook = ebook.equals("true");
    
        // 2. Create a new Book object
        Book newBook = new Book(title, year, isbn, isEbook, edition);
        newBook.setAuthors(authors);
    
        // 3. Create a new array to store all books, INCLUDING new ones
        Book[] newBooksArray = new Book[books.length + 1];
    
        // 4. Copy the old books to the new array
        for (int i = 0; i < books.length; i++)
        {
            newBooksArray[i] = books[i];
        }
    
        // 5. Add the new book to the array
        newBooksArray[books.length] = newBook;
    
        // 6. FINALLY replace the old books array with the new one :DD
        books = newBooksArray;
    }

    // NAME: editBook
    // IMPORT: bookIndex (int), bookDetail (int), detail (String)
    // EXPORT: none
    // PURPOSE: gives the user the ability to edit the values of an existing book :J
    public void editBook(int bookIndex, int bookDetail, String detail)
    {

        switch (bookDetail)
        {
            case 1:
                // Edit the book TITLE... 
                books[bookIndex].setTitle(detail);
                break;

            case 2:
                // Edit the PUBLICATION YEAR...
                books[bookIndex].setYear(Integer.parseInt(detail));
                break;

            case 3:
                // Edit the ISBN...
                books[bookIndex].setIsbn(detail);
                break;

            case 4:
                // Edit the EBOOK STATUS...
                if (detail.equals("true") || detail.equals("false"))
                {
                    books[bookIndex].setEbook(Boolean.parseBoolean(detail));
                } else
                {
                    System.out.println("Invalid input for eBook :(");
                    System.out.println("Please enter 'true' or 'false'... :)");
                }
                break;

            case 5:
                // Edit the EDITION NUMBER...
                books[bookIndex].setEdition(Integer.parseInt(detail));
                break;
            default:
                System.out.println("Please select a valid option... :(");
        }
    }
}




