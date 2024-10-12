/************************************
 * Author: Celerina Reyes           *
 * Created: 09/10/2024              *
 * Purpose: Library of books system *
 ************************************/

import java.util.*;

public class Library
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        BookManager manager = new BookManager();
        manager.readFile("StartingDataFile_1.csv");
        manager.createBookDatabase();
        int choice = 0;
      
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
        
            try 
            {
                choice = sc.nextInt();
                sc.nextLine(); // Consume leftover newline after nextInt()

                switch(choice)
                {
                    case 1:
                        manager.viewAllBooks();
                        break;

                    case 2:
                        manager.filterBooks(true);
                        break;

                    case 3:
                        System.out.println("\n************************************");
                        System.out.println("          Non-eBooks");
                        System.out.println("************************************");
                        manager.filterBooks(false);
                        break;

                    case 4:
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
                    
                        // Prompt for the title
                        System.out.print("Title: ");
                        title = sc.nextLine();  
                    
                        // Prompt for the year and handle invalid input
                        boolean validYear = false;
                        while (!validYear)
                        {
                            System.out.print("Published: ");
                            try
                            {
                                year = sc.nextInt();
                                sc.nextLine();  // Consume the newline left by nextInt()
                                validYear = true;  // If input is valid, exit the loop
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Invalid input :( Please enter a valid year.");
                                sc.next();  // Clear the invalid input
                            }
                        }
                    
                        // Prompt for ISBN
                        System.out.print("ISBN: ");
                        isbn = sc.nextLine();
                    
                        // Prompt for eBook status and handle invalid input
                        boolean validEbook = false;
                        while (!validEbook)
                        {
                            System.out.print("eBook (true/false): ");
                            ebook = sc.nextLine();
                            if (ebook.equals("true") || ebook.equals("false"))
                            {
                                validEbook = true;  // Valid input entered
                            }
                            else
                            {
                                System.out.println("Invalid input :( Please enter 'true' or 'false'.");
                            }
                        }
                    
                        // Prompt for the edition and handle invalid input
                        boolean validEdition = false;
                        while (!validEdition)
                        {
                            System.out.print("Edition: ");
                            try
                            {
                                edition = sc.nextInt();
                                sc.nextLine();  // Consume the newline left by nextInt()
                                validEdition = true;  // If input is valid, exit the loop
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Invalid input :( Please enter a valid edition number.");
                                sc.next();  // Clear the invalid input
                            }
                        }
                    
                        // Prompt for the number of authors and handle invalid input
                        while (numberOfAuthors < 1 || numberOfAuthors > 3)
                        {
                            System.out.print("Enter number of authors (max 3): ");
                            try
                            {
                                numberOfAuthors = sc.nextInt();
                                sc.nextLine();  // Consume the newline left by nextInt()
                                if (numberOfAuthors < 1 || numberOfAuthors > 3)
                                {
                                    System.out.println("Invalid number of authors :( Please enter between 1 and 3.");
                                }
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Invalid input:( Please enter a number between 1 and 3.");
                                sc.next();  // Clear the invalid input
                            }
                        }
                    
                        // Proceed to enter author details if valid input is provided
                        Author[] authors = new Author[numberOfAuthors];
                    
                        for (int i = 1; i <= numberOfAuthors; i++)
                        {
                            authors[i-1] = new Author();
                    
                            System.out.println("\nAuthor " + i + ":");
                    
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
                                    sc.nextLine();  // Consume the newline after nextInt()
                                    authors[i-1].setYearOfBirth(yearOfBirth);
                                    validYearOfBirth = true;
                                }
                                catch (InputMismatchException e)
                                {
                                    System.out.println("Invalid input :( Please enter a valid year of birth.");
                                    sc.next();  // Clear the invalid input
                                }
                            }
                        }
                    
                        // Add the new book to the manager
                        manager.addBook(title, year, isbn, ebook, edition, authors);
                        System.out.println("\nNew book added successfully! :D");
                        break;
                    
                    
                    case 6:
                        System.out.println("\n************************************");  
                        System.out.println("             Edit Books");
                        System.out.println("************************************");

                        for (int i = 0; i < manager.books.length; i++) 
                        {
                            if (manager.books[i] == null) 
                            {
                                continue;
                            }
                            int bookNum = i + 1;
                            System.out.println("[" + bookNum + "] " + manager.books[i].getTitle());
                        }

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
                            manager.editBook(bookChoice, detailChoice, detailValue);
                            System.out.println("\nBook edited successfully! :D");

                        } 
                        catch (InputMismatchException e) 
                        {
                            System.out.println("Invalid input :( Please enter a valid number.");
                            sc.next();  // Clear the invalid input
                        }
                        break;

                    case 7:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice :( Try again.\n");
                }
            } 
            catch (InputMismatchException e) 
            {
                System.out.println("Invalid choice :( Please enter a number.");
                sc.next();  // Clear the invalid input
            }
        }
        while (choice != 7);

        sc.close();
    }
}
