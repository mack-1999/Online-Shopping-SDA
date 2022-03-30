
import java.sql.*;

public class demo_sql{
    public static void main(String args[]){
        try {
            //! Connection with JDBC
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shopping", "root","Mack@12345");
    
            Statement stmt = con.createStatement();
    
            //! SQL Query
            String verification = "SELECT pro_Price, pro_Name from grocery where pro_Category = 'Cooking Essentials'";
            ResultSet rs = stmt.executeQuery(verification);

            while (rs.next()) {

                String col1 = rs.getString(1);
                System.out.println(col1+"\t");

                //String col2 = rs.getString(2);
                //System.out.println(col2);  
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}