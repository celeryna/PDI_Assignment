/********************************************
 * Author: Celerina Reyes                   *
 * Created: 09/10/2024                      *
 * Purpose: Represent a Book in the library *
 *******************************************/

public class Book 
{
    private String title;
    private int year;
    private String isbn;
    private boolean ebook;
    private int edition;
    private Author[] authors;

    public Book()
    {
        this.title = "None";
        this.year = 0000;
        this.isbn = "None";
        this.ebook = false;
        this.edition = 0;
    }

    // Constructor without authors array
    public Book(String title, int year, String isbn, boolean ebook, int edition)
    {
        this.title = title;
        this.year = year;
        this.isbn = isbn;
        this.ebook = ebook;
        this.edition = edition;
    }


    public String getTitle() 
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public boolean isEbook()
    {
        return ebook;
    }

    public void setEbook(boolean ebook)
    {
        this.ebook = ebook;
    }

    public int getEdition()
    {
        return edition;
    }

    public void setEdition(int edition)
    {
        this.edition = edition;
    }

    public Author[] getAuthors()
    {
        return authors;
    }

    public void setAuthors(Author[] authors)
    {
        this.authors = authors;
    }
}
