import java.util.*;

public class Library
{
    public static void main(String[] args)
    {
      Scanner sc = new Scanner(System.in);
      int choice;
      
      do
      {
        System.out.println("************************************");  
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
        System.out.println("Your choice: ");
        choice = sc.nextInt();
      
      
        switch(choice)
        {
          case 1:
              viewAllBooks();
              break;

          case 2:
              viewEbooks();
              break;

          case 3:
              viewNonebooks();
              break;

          case 4:
              System.out.print("Enter Author's Name: ");
              String authorName = sc.nextLine();
              viewBooksByAuthor(authorName
              break;
          case 5:
              addBook()
              break;
          case 6:
              editBook()
              break;
          case 7:
              System.out.println("Exiting...");
              break;

          default:
              System.out.println("Invalid choice. Try again.");
        }
      }
      while (choice != 7);
    }
}
