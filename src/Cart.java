import java.util.Scanner;

public class Cart {

    void checkout(int pro_quantity, String user_Sub_Choice, float Pro_Price, Scanner sc){
        System.out.println("\n|>>| CART |<<|");
        System.out.println("\n| "+user_Sub_Choice+" "+"x"+ pro_quantity +" "+ Pro_Price +"Rs |");

        float total_value = pro_quantity*Pro_Price;
        System.out.println("\n| Total Cart Value: "+ total_value +"Rs |");

        System.out.println("\n|>>| 1. Confirm Order || 2. Back  |<<|");

        System.out.print("\nEnter your choice: ");
        int user_choice = sc.nextInt();

        if (user_choice == 1) {
            place_order();
        } else {
            checkout(pro_quantity, user_Sub_Choice, Pro_Price, sc);
        }
    }
    void place_order(){
        System.out.println("Order Placed.");
        System.out.println("Bill Generated.");
        System.out.println("Thank you");
    } 
}
