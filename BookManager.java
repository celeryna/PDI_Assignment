/******************************************
 * Author: Celerina Reyes                 *
 * Created: 09/10/2024                    *
 * Purpose: Manages the library's books   *
 *****************************************/

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
            if (booksData[i][0] == null)
            {
                continue;
            }

            Book newBook = new Book(booksData[i][0], Integer.parseInt(booksData[i][13]), booksData[i][14], Boolean.parseBoolean(booksData[i][15]), Integer.parseInt(booksData[i][16]));
            Author[] newAuthors = new Author[3];

            newAuthors[0] = new Author();
            newAuthors[0].setFirstName(booksData[i][2]);
            newAuthors[0].setLastName(booksData[i][1]);
            newAuthors[0].setNationality(booksData[i][3]);
            newAuthors[0].setYearOfBirth(Integer.parseInt(booksData[i][4]));

            if (booksData[i][6] != null && !booksData[i][6].isEmpty())
            {
                newAuthors[1] = new Author();
                newAuthors[1].setFirstName(booksData[i][6]);
                newAuthors[1].setLastName(booksData[i][5]);
                newAuthors[1].setNationality(booksData[i][7]);
                newAuthors[1].setYearOfBirth(Integer.parseInt(booksData[i][8]));
            }

            if (booksData[i][10] != null && !booksData[i][10].isEmpty())
            {
                newAuthors[2] = new Author();
                newAuthors[2].setFirstName(booksData[i][10]);
                newAuthors[2].setLastName(booksData[i][9]);
                newAuthors[2].setNationality(booksData[i][11]);
                newAuthors[2].setYearOfBirth(Integer.parseInt(booksData[i][12]));
            }

            newBook.setAuthors(newAuthors);
            books[i] = new Book();
            books[i] = newBook;
        }
    }

    public void viewAllBooks()
    {
        for (int i = 0; i < books.length; i++)
        {
            if (books[i] == null)
            {
                continue;
            }

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

    public void filterBooks(boolean isEbook)
    {
        for (int i = 0; i < books.length; i++)
        {
            if (books[i] == null)
            {
                continue;
            }

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

    public boolean viewBooksByAuthor(String fullname)
    {
        String[] nameParts = fullname.split(" ");

        if (nameParts.length < 2)
        {
            System.out.println("Please enter both first name and last name!");
            return false;
        }

        String firstName = nameParts[0];
        String lastName = nameParts[1];

        boolean found = false;

        System.out.println();
        
        for (int i = 0; i < books.length; i++)
        {
            if (books[i] == null)
            {
                continue;
            }

            //check for matching author 1
            if (books[i].getAuthors()[0].getFirstName().equals(firstName) && books[i].getAuthors()[0].getLastName().equals(lastName))
            {
                displayBookDetails(books[i], books[i].getAuthors()[0]);
                found = true;
            }

            //check for matching author 2, if exists
            if ((books[i].getAuthors().length > 1) && (books[i].getAuthors()[1] != null))
            {
                if (books[i].getAuthors()[1].getFirstName().equals(firstName) && books[i].getAuthors()[1].getLastName().equals(lastName))
                {
                    displayBookDetails(books[i], books[i].getAuthors()[1]);
                    found = true;
                }
            }

            //check for matching author 3, if exists
            if ((books[i].getAuthors().length > 2) && (books[i].getAuthors()[2] != null)) {
                if (books[i].getAuthors()[2].getFirstName().equals(firstName) && books[i].getAuthors()[2].getLastName().equals(lastName))
                {
                    displayBookDetails(books[i], books[i].getAuthors()[2]);
                    found = true;
                }
            }
        }

        if(!found)
        {
            System.out.println("No books found for the author " + fullname + "." + " :( ");
            System.out.println("Please try again... :)");
        }

        return found;
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

    public void addBook(String title, int year, String isbn, String ebook, int edition, Author[] authors)
    {

        boolean isEbook = ebook.equals("true");
    
        Book newBook = new Book(title, year, isbn, isEbook, edition);
        newBook.setAuthors(authors);
    
        Book[] newBooksArray = new Book[books.length + 1];
    
        for (int i = 0; i < books.length; i++)
        {
            newBooksArray[i] = books[i];
        }
    
        newBooksArray[books.length] = newBook;
    
        books = newBooksArray;
    }
    
    public void editBook(int bookIndex, int bookDetail, String detail)
    {

        switch (bookDetail)
        {
            case 1:
                books[bookIndex].setTitle(detail);
                break;
            case 2:
                books[bookIndex].setYear(Integer.parseInt(detail));
                break;
            case 3:
                books[bookIndex].setIsbn(detail);
                break;
            case 4:
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
                books[bookIndex].setEdition(Integer.parseInt(detail));
                break;
            default:
                System.out.println("Please select a valid option... :(");
        }
    }
}




