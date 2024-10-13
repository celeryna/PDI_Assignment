/************************************
 * Author: Celerina Reyes           *
 * Created: 09/10/2024              *
 * Purpose: Library of books system *
 ************************************/

import java.util.*;

public class Library
{
    // NAME: main                                                                    
    // IMPORT: args (String[]), command-line arguments 
    // EXPORT: none                                    
    // PURPOSE: The entry point for the application    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        BookManager manager = new BookManager();
        manager.readFile("StartingDataFile_1.csv");
        manager.createBookDatabase();
        int choice = 0;
      
        // A loop for the main library menu. It will keep running until the user chooses to exit :)
        do
        {
            System.out.println("\n************************************");  
            System.out.println("      Welcome to the Library        ");
            System.out.println("************************************");
            System.out.println(" 1 > View all Books");
            System.out.println(" 2 > View eBooks");
            System.out.println(" 3 > View non-eBooks");
            System.out.println(" 4 > View an author's Books");
            System.out.println(" 5 > Add Book");
            System.out.println(" 6 > Edit Book");
            System.out.println(" 7 > Exit");
            System.out.println("************************************");
            System.out.print("Your choice: ");
        
            // Try-catch block to handle invalid inputs for the menu choices :)
            try 
            {
                choice = sc.nextInt();
                sc.nextLine();
                
                // Switch-case for the different user choices :o
                switch(choice)
                {
                    case 1:
                        // View ALL books from the library :D
                        System.out.println("\n************************************");
                        System.out.println("           All Books");
                        System.out.println("************************************");
                        manager.viewAllBooks();
                        break;

                    case 2:
                        // View ONLY eBooks from the library ^.^
                        System.out.println("\n************************************");
                        System.out.println("              eBooks");
                        System.out.println("************************************");
                        manager.filterBooks(true);
                        break;

                    case 3:
                        //View ONLY non-eBooks from the library (GenZ: "omg what is that :O")
                        System.out.println("\n************************************");
                        System.out.println("          Non-eBooks");
                        System.out.println("************************************");
                        manager.filterBooks(false);
                        break;

                    case 4:
                        // View books by SPECIFIC author >:)
                        boolean found = false;

                        while(!found)
                        {
                            System.out.println("\n************************************");
                            System.out.println("          Books by Author");
                            System.out.println("************************************");
                            System.out.print("Enter Author Name (FirstName LastName): ");
                            String fullName = sc.nextLine();
                            found = manager.viewBooksByAuthor(fullName);
                        }
                        break;

                    case 5:
                        // Add a NEW book to the library XD
                        System.out.println("\n************************************");  
                        System.out.println("             Add Books");
                        System.out.println("************************************");
                        System.out.println("New Book:\n");
                        
                        String title;
                        int year = -1;
                        String isbn;
                        String ebook = "";
                        int edition = -1;
                        int numberOfAuthors = 0;
                    
                        // Prompt the user for the title...
                        System.out.print("Title: ");
                        title = sc.nextLine();  
                    
                        // Prompt the user for the year published (& handle invalid input)...
                        boolean validYear = false;
                        while (!validYear)
                        {
                            System.out.print("Published: ");
                            try
                            {
                                year = sc.nextInt();
                                sc.nextLine();
                                validYear = true;  // If input is valid, exit the loop :)
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Invalid input :( Please enter a valid year.");
                                sc.next();  // Clear the invalid input :(
                            }
                        }
                    
                        // Prompt the user for ISBN...
                        System.out.print("ISBN: ");
                        isbn = sc.nextLine();
                    
                        // Prompt the user for eBook status (& handle invalid input)...
                        boolean validEbook = false;
                        while (!validEbook)
                        {
                            System.out.print("eBook (true/false): ");
                            ebook = sc.nextLine();
                            if (ebook.equals("true") || ebook.equals("false"))
                            {
                                validEbook = true;
                            }
                            else
                            {
                                System.out.println("Invalid input :( Please enter 'true' or 'false'.");
                            }
                        }
                    
                        // Prompt the user for the edition and handle invalid input
                        boolean validEdition = false;
                        while (!validEdition)
                        {
                            System.out.print("Edition: ");
                            try
                            {
                                edition = sc.nextInt();
                                sc.nextLine();
                                validEdition = true;  // If input is valid, exit the loop :)
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Invalid input :( Please enter a valid edition number.");
                                sc.next();  // Clear the invalid input :(
                            }
                        }
                    
                        // Prompt the user for the number of authors (& handle invalid input)...
                        while (numberOfAuthors < 1 || numberOfAuthors > 3)
                        {
                            System.out.print("Enter number of authors (max 3): ");
                            try
                            {
                                numberOfAuthors = sc.nextInt();
                                sc.nextLine(); 
                                if (numberOfAuthors < 1 || numberOfAuthors > 3)
                                {
                                    System.out.println("Invalid number of authors :( Please enter between 1 and 3.");
                                }
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Invalid input:( Please enter a number between 1 and 3.");
                                sc.next();  // Clear the invalid input :(
                            }
                        }

                        // If valid input is provided, continue to enter the author's details :)
                        Author[] authors = new Author[numberOfAuthors];
                    
                        for (int i = 1; i <= numberOfAuthors; i++)
                        {
                            // A new Author object
                            authors[i-1] = new Author();
                    
                            System.out.println("\nAuthor " + i + ":");
                    
                            // Prompt the user for author details... (first name, last name, nationality, year of birth :))
                            System.out.print("First Name: ");
                            String firstName = sc.nextLine();
                            authors[i-1].setFirstName(firstName);
                    
                            System.out.print("Last Name: ");
                            String lastName = sc.nextLine();
                            authors[i-1].setLastName(lastName);
                    
                            System.out.print("Nationality: ");
                            String nationality = sc.nextLine();
                            authors[i-1].setNationality(nationality);
                    
                            boolean validYearOfBirth = false;
                            while (!validYearOfBirth)
                            {
                                System.out.print("Year of Birth: ");
                                try
                                {
                                    int yearOfBirth = sc.nextInt();
                                    sc.nextLine();
                                    authors[i-1].setYearOfBirth(yearOfBirth);
                                    validYearOfBirth = true;
                                }
                                catch (InputMismatchException e)
                                {
                                    System.out.println("Invalid input :( Please enter a valid year of birth.");
                                    sc.next();  // Clear the invalid input :(
                                }
                            }
                        }
                    
                        // Adds the NEW BOOK to the manager
                        manager.addBook(title, year, isbn, ebook, edition, authors);
                        System.out.println("\nNew book added successfully! :D");
                        break;
                    
                    
                    case 6:
                        // EDIT an existing book from the library :))
                        System.out.println("\n************************************");  
                        System.out.println("             Edit Books");
                        System.out.println("************************************");

                        // List ALL BOOKS with a number for the selection :O
                        for (int i = 0; i < manager.books.length; i++) 
                        {
                            if (manager.books[i] == null) 
                            {
                                continue;
                            }
                            int bookNum = i + 1;
                            System.out.println("[" + bookNum + "] " + manager.books[i].getTitle());
                        }

                        // A try-catch block for book selecting and Editing
                        try 
                        {
                            System.out.println("\n-- Select a book to edit by the number --");
                            System.out.print("Choice: ");
                            int bookChoice = sc.nextInt();
                            sc.nextLine();
                            bookChoice -= 1;

                            System.out.println("\nWhat value would you like to change from that book? \n");
                            System.out.println("[1] Title: " + manager.books[bookChoice].getTitle());
                            System.out.println("[2] Year: " + manager.books[bookChoice].getYear());
                            System.out.println("[3] ISBN: " + manager.books[bookChoice].getIsbn());
                            System.out.println("[4] eBook: " + manager.books[bookChoice].isEbook());
                            System.out.println("[5] Edition: " + manager.books[bookChoice].getEdition());
                            System.out.println("\n-- Select a value to edit by the number --");
                            System.out.print("Choice: ");
                            int detailChoice = sc.nextInt();
                            sc.nextLine();

                            System.out.print("New value: ");
                            String detailValue = sc.nextLine();

                            // Edit the chosen book detail...
                            manager.editBook(bookChoice, detailChoice, detailValue);
                            System.out.println("\nBook edited successfully! :D");

                        } 
                        catch (InputMismatchException e) 
                        {
                            System.out.println("Invalid input :( Please enter a valid number.");
                            sc.next();  // Clear the invalid input :(
                        }
                        break;

                    case 7:
                        // To EXIT the program (Nooo don't go D:)
                        System.out.println("Exiting...");
                        break;

                    default:
                        // To handle invalid menu choices (oopsies)
                        System.out.println("Invalid choice :( Try again.\n");
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Invalid choice :( Please enter a number.");
                sc.next();  // Clear the invalid input :(
            }
        }
        while (choice != 7); // Loop until the user chooses to leave (until 7 is input) :)

        sc.close(); // Bye bye Scanner
    }
}
