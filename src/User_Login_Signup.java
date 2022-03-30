import java.util.Scanner; //for Scanner Class
import java.sql.*; //for JDBC Connection

public class User_Login_Signup implements Login_Signup {

    Scanner sc = new Scanner(System.in);

    // ! User Registeration Code
    public void register() {
        System.out.print("\nEnter First Name: ");
        String Fname = sc.next();

        System.out.print("\nEnter Last Name: ");
        String Lname = sc.next();

        System.out.print("\nEnter Mobile Number: ");
        long Mnumber =  sc.nextLong();

        sc.nextLine(); // for /n
        System.out.print("\nEnter Address: ");
        String Address = sc.nextLine();

        System.out.print("\nEnter User Name: ");
        String Uname = sc.next();

        System.out.print("\nEnter Password: ");
        String Pass = sc.next();

        System.out.print("\nConfirm Password: ");
        String ConPass = sc.next();

        //Todo: Returns a string whose value is this string, with all leading and trailing
        Fname = Fname.trim();
        Lname = Lname.trim();
        Uname = Uname.trim();
        Pass = Pass.trim();
        ConPass = ConPass.trim();

        int flag = 0;

        if (Pass.equals(ConPass) && Pass.length() >= 8) {
            for (int i = 1; i <= Pass.length() - 1; i++) {
                if (Pass.charAt(i) == '$' || Pass.charAt(i) == '#' || Pass.charAt(i) == '@' || Pass.charAt(i) == '%') {
                    flag = 1;
                }
            }
            if (flag == 0) {
                System.out.println("\nPassword must contain atleast single special character i.e. ($, @, #, %,).");
                System.out.println("Try Again.");
                this.register();
            }
        } else {
            System.out.println("\nPassword didn't Matched | Password Must Contain Min. 8 charaters.");
            System.out.println("Try Again.");
            this.register();
        }

        if (flag == 1) {
            try {
                // ! Connection with JDBC
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "Mack@12345");

                Statement stmt = con.createStatement();

                // ! Create SQL Query
                String check = " SELECT * FROM user_loginDB WHERE users_name = '" + Uname + "' ";

                ResultSet rs = stmt.executeQuery(check);

                String databaseUsername = null;

                while (rs.next()) {
                    databaseUsername = rs.getString("users_name");
                }
                //! If user_id already present in table display below message.
                if (Uname.equals(databaseUsername)) {
                    System.out.println("\nUser id already exist. | Please try with different user id.");
                    this.register();
                } else {
                    // ! SQL Query for inserting data into table (user_loginDB).
                    String insert = "INSERT INTO user_loginDB VALUES('" + Uname + "','" + Pass + "','" + Fname + "','" + Lname + "','" + Mnumber + "','" + Address + "')";

                    stmt.executeUpdate(insert); //Query Executed 
                    System.out.println("\nAccount Created Successfully!!");

                    this.login(); //! calling login method.
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // ! User login Code
    public void login() {
        try {
            Scanner sc = new Scanner(System.in);

            // Check Username and Password
            System.out.print("\nEnter Username: ");
            String name = sc.next();
            System.out.print("\nEnter Password: ");
            String password = sc.next();

            // ! Connection with JDBC
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root", "Mack@12345");

            Statement stmt = con.createStatement();

            // ! SQL Query
            String verification = "SELECT * FROM user_loginDB WHERE users_name = '" + name + "' ";

            ResultSet rs = stmt.executeQuery(verification);

            String Database_Username = null;
            String Database_Password = null;
            String Database_Fname = null;
            String Database_Lname = null;
            Long Database_Mnumber = null;
            String Database_Address = null;

            // Check Username and Password
            while (rs.next()) {
                Database_Username = rs.getString("users_name");
                Database_Password = rs.getString("users_password");
                Database_Fname = rs.getString("First_name");
                Database_Lname = rs.getString("Last_name");
                Database_Mnumber = rs.getLong("Mobile_No");
                Database_Address = rs.getString("Address");
            }

            if (name.equals(Database_Username) && password.equals(Database_Password)) {
                System.out.println("\nSuccessful Login!");
                //displaying user_name with welcome msg.
                System.out.println("\n|***| "+ Database_Fname +", Welcome to E-Mart |***|");
                
                new Client().main_Category(sc, stmt);
            } else {
                System.out.println("Incorrect User Name or Password \n Try Again Please");
                this.login();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}