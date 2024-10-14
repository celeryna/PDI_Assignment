/********************************************
 * Author: Celerina Reyes                   *
 * Created: 10/10/2024                      *
 * Purpose: Represents an Author of a Book  *
 *******************************************/

public class Author
{
    // These are the private fields for the author details :)
    private String firstName;
    private String lastName;
    private int yearOfBirth;
    private String nationality;

    // Getter and Setter for the first name...
    public String getFirstName() 
    {
        return this.firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    // Getter and Setter for the last name...
    public String getLastName() 
    {
        return lastName;
    }
    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    // Getter and Setter for the year of birth...
    public int getYearOfBirth() 
    {
        return yearOfBirth;
    }
    public void setYearOfBirth(int yearOfBirth)
    {
        this.yearOfBirth = yearOfBirth;
    }

    // Getter and Setter for the Nationality...
    public String getNationality()
    {
        return nationality;
    }
    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

}
