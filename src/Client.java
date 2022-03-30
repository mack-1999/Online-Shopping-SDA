import java.sql.*; //for JDBC Connection
import java.util.ArrayList; //for storing Category list
import java.util.Scanner; //for Scanner Class

public class Client {
    // ? Main App Interface Starts.
    void main_Category(Scanner sc, Statement stmt){ 

        System.out.println("\n1. Grocery | 2. Electronics | 3. Furniture");
        
        System.out.print("\nStart Shopping | What you need: ");
        int Category_choice = sc.nextInt();

        this.sub_Category(Category_choice, sc, stmt);

        sc.close();
    }

    void sub_Category(int Category_choice, Scanner sc, Statement stmt){
        switch (Category_choice) {
            case 1: //Todo: 1. Grocery
                try {
                    //! SQL Query
                    String pro_list = "SELECT distinct pro_Category from grocery";

                    //! Executing SQL Query
                    ResultSet rs = stmt.executeQuery(pro_list);

                    //! Created Array list Variable
                    ArrayList<String> category_list = new ArrayList<String>();

                    while (rs.next()) {
                        //! Adding table Column values in array list.
                        category_list.add(rs.getString(1)); 
                    }
                    System.out.println(); //Just for Space

                    for (int i = 0; i < category_list.size(); i++) {
                        //! Printing Array list Elements one by one.
                        System.out.println("| "+(i + 1) + " | " + category_list.get(i));
                    }
                    System.out.println("| "+(category_list.size()+1)+" | Back");
                    System.out.println();
                    System.out.print("Enter your Choice: ");
                    int grocery_choice = sc.nextInt();

                    if (grocery_choice == (category_list.size()+1)) {
                        this.main_Category(sc, stmt);
                    } else {
                        String user_choice = category_list.get(grocery_choice - 1);
                        new Grocery().sub_list(user_choice, stmt, Category_choice);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case 2: //Todo: 2. Electronics

                break;

            case 3: //Todo: 3. Beauty

                break;

            default:
                System.out.println("\n|>>| Invalid Choice. | Try Again Please. |<<|\n");
                this.main_Category(sc, stmt);
                break;
        }
    }
    // ? Main App Interface End.
    public static void main(String args[]) {

        // Client appobj = new Client(); //Main object
        Scanner sc = new Scanner(System.in);

        //? User Registeration & login Starts
        
        System.out.println("\n|>>| 1. REGISTRATION |<<|\n|>>| 2. LOGIN        |<<|");
         
        System.out.print("\nEnter your choice: ");
        int choice = sc.nextInt();
         
        if (choice == 1) {
            new User_Login_Signup().register();
        } else if (choice == 2) {
            new User_Login_Signup().login();
        } else {
            System.out.println("Invalid Option. | Choose Proper Option.");
        }
        sc.close();
        //? User Login & Registeration Code Ends
    }
}