/********************************************
 * Author: Celerina Reyes                   *
 * Created: 09/10/2024                      *
 * Purpose: Represent a Book in the library *
 *******************************************/

public class Book 
{
    // These are fields to store book detail...
    private String title;
    private int year;
    private String isbn;
    private boolean ebook;
    private int edition;
    private Author[] authors;

    // Constructor to initialise the book with default values :O
    public Book()
    {
        this.title = "None";
        this.year = 0000;
        this.isbn = "None";
        this.ebook = false;
        this.edition = 0;
    }

    // These constructors have parameters for initialised, without the athors array :)
    public Book(String title, int year, String isbn, boolean ebook, int edition)
    {
        this.title = title;
        this.year = year;
        this.isbn = isbn;
        this.ebook = ebook;
        this.edition = edition;
    }

    // Getter and Setter for the title...
    public String getTitle() 
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    // Getter and Setter for the published year...
    public int getYear()
    {
        return year;
    }
    public void setYear(int year)
    {
        this.year = year;
    }

    // Getter and Setter for the ISBN...
    public String getIsbn()
    {
        return isbn;
    }
    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    // Getter and Setter to check that the book is an eBook...
    public boolean isEbook()
    {
        return ebook;
    }
    public void setEbook(boolean ebook)
    {
        this.ebook = ebook;
    }

    // Getter and Setter for the edition...
    public int getEdition()
    {
        return edition;
    }
    public void setEdition(int edition)
    {
        this.edition = edition;
    }

    // Getter and Setter for authors...
    public Author[] getAuthors()
    {
        return authors;
    }
    public void setAuthors(Author[] authors)
    {
        this.authors = authors;
    }
}
