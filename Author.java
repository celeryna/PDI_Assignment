/********************************************
 * Author: Celerina Reyes                   *
 * Created: 09/10/2024                      *
 * Purpose: Represents an Author of a Book  *
 *******************************************/

public class Author
{
    private String firstName;
    private String lastName;
    private int yearOfBirth;
    private String nationality;

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getFirstName() 
    {
        return this.firstName;
    }

    public String getLastName() 
    {
        return lastName;
    }

    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    public int getYearOfBirth() 
    {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth)
    {
        this.yearOfBirth = yearOfBirth;
    }

    public String getNationality()
    {
        return nationality;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

}
