import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Grocery {

    Scanner sc = new Scanner(System.in);

    void sub_list(String user_choice, Statement stmt, int Category_choice) {
        try {
            // ! Create SQL Query
            String pro_list = "SELECT pro_Name from grocery where pro_category = '" + user_choice + "'";

            ResultSet rs = stmt.executeQuery(pro_list);

            ArrayList<String> subcat_list = new ArrayList<String>();

            while (rs.next()) {
                subcat_list.add(rs.getString(1));
            }
            System.out.println("\n|**| Available "+user_choice+" |**|\n");

            for (int i = 0; i < subcat_list.size(); i++) {
                System.out.println("| "+(i + 1) + " | " + subcat_list.get(i));
            }
            
            System.out.println("| "+(subcat_list.size()+1)+" | Back");
            System.out.println();
            System.out.print("Enter your Choice: ");
            int sub_choice = sc.nextInt();

            if (sub_choice == (subcat_list.size()+1)) {
                new Client().sub_Category(Category_choice, sc, stmt);
            } else {
                String user_Sub_Choice = subcat_list.get(sub_choice - 1);
                pro_details(stmt, user_Sub_Choice, user_choice, Category_choice);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void pro_details(Statement stmt, String user_Sub_Choice, String user_choice, int Category_choice) {
        try {

            // ! Create SQL Query
            String product_details = "SELECT pro_Price, pro_DOM, available_Quantity from grocery where pro_Name = '" + user_Sub_Choice  + "'";

            ResultSet rs = stmt.executeQuery(product_details);

            float Pro_Price = 0;
            String Pro_DOM = null;
            int Available_Quantity = 0;

            while(rs.next()){
                Pro_Price = rs.getFloat("pro_Price");
                Pro_DOM = rs.getString("pro_DOM");
                Available_Quantity = rs.getInt("available_Quantity");
            }

            System.out.println("\n|>>|"+ user_Sub_Choice +"|<<|");

            System.out.println("\nProduct Details: | Rs "+ Pro_Price +" |"+" Manufactured date "+ Pro_DOM +" |"+" Available Quantity "+ Available_Quantity +" |");

            System.out.println("\n(Free Delivery on orders over Rs.399)");

            System.out.println("\n|>>| 1. ADD TO CART || 2. Back |<<|");

            System.out.print("\nEnter your Choice: ");
            int users_choice = sc.nextInt();

            if (users_choice == 1) {
                System.out.print("\nEnter number of quantity to be added: ");
                int pro_quantity = sc.nextInt();  
                new Cart().checkout(pro_quantity, user_Sub_Choice, Pro_Price, sc);

            }else if(users_choice == 2){
                sub_list(user_choice, stmt, Category_choice);
            }else{
                System.out.println("|>>| Invalid Choice. | Try Again Please. |<<|");
                sub_list(user_choice, stmt, Category_choice);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
