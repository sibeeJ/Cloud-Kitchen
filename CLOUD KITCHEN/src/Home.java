import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Home{
        Scanner sc = new Scanner(System.in);
    public void pUserHome(int cookid,String name) throws SQLException {
            System.out.println("ENTER THE QUANTITY OF DISHES YOU NEED TO ADD TO MENU DATABASE");
            {
                int n2 = sc.nextInt();
                for(int i=0;i<n2;i++)
                {
                    sc.nextLine();
                    System.out.println("Recipe Name");
                    String recipename = sc.nextLine();
                    System.out.println("Quantity");
                    int quan = sc.nextInt();
                    System.out.println("Price");
                    int price = sc.nextInt();
                    String query = "INSERT INTO cook_dish(cook_id,Cook_name,Dish_name,quantity,price)VALUES('"+cookid+"','"+name+"','"+recipename+"','"+quan+"','"+price+"')";
                    Statement stmt = (Statement) connect.con.createStatement();
                    stmt.executeUpdate(query);
                }
                System.out.println("Your Dishes had to Added to the Database");
                System.exit(1);
            }
        }

    public void uUserHome(String username) throws Exception
    {
        System.out.println("----Available MENU ----");
        System.out.println();
        String query = "SELECT * FROM cook_dish WHERE quantity != 0";
        Statement stmt = (Statement) connect.con.createStatement();
        ResultSet res = stmt.executeQuery(query);
        System.out.printf("%-10s %-10s %-15s %-25s %-20s %-10s","DISH ID","COOK ID","COOK NAME","DISH NAME","QUANTITY","PRICE");
        System.out.println();
        while(res.next())
        {
            int cookid = res.getInt(1);
            String cookname = res.getString(2);
            String dishname = res.getString(3);
            int quan = res.getInt(4);
            int price = res.getInt(5);
            int dishid = res.getInt(6);
            System.out.printf("%-10s %-10s %-15s %-25s %-20s %-10s",dishid,cookid,cookname,dishname,quan,price);
            System.out.println();
        }
        System.out.println("ENTER 1 TO ORDER THE FOOD");
        int n = sc.nextInt();
        int totalprice=0;
        int rate=0;
        while(n!=0)
        {
                System.out.println("Enter the dish id of the food");
                int dishid = sc.nextInt();
                int quantity=0;
                String quanquery = "SELECT quantity,price FROM cook_dish WHERE dish_id='"+dishid+"'";
                ResultSet ress = stmt.executeQuery(quanquery);
                if(ress.next())
                {
                    quantity = ress.getInt(1);
                    rate = ress.getInt(2);
                }
                System.out.println("Available Quantity is "+quantity);
                System.out.println("Enter the quantity of the food");
                int quant  = sc.nextInt();
                if(quant>quantity)
                {
                    System.out.println("You have entered more the availability of food");
                    System.out.println("Available quantity is only "+quantity);
                    System.out.println("Please re-enter quantity within the availability");
                    quant =sc.nextInt();
                }
                totalprice = totalprice + (rate*quant);
                String res1 = "SELECT userid from sign_up where Username = '"+username+"'";
                ResultSet res2 = stmt.executeQuery(res1);
                res2.next();
                int userid = res2.getInt(1);
                String query1 = "INSERT INTO order_det(dish_id,quantity,userid) VALUES ('"+dishid+"','"+quant+"','"+userid+"')";
                stmt.executeUpdate(query1);
                String query2 = "UPDATE cook_dish set quantity =  quantity - '"+quant+"' where dish_id = '"+dishid+"' ";
                stmt.executeUpdate(query2);
//                String avaiquanquery = "SELECT quantity FROM cook_dish WHERE dish_id = '"+dishid+"'";
//                ResultSet remres = stmt.executeQuery(avaiquanquery);
//                remres.next();
//                int avaiquant = remres.getInt(1);
//                if(avaiquant==0)
//                {
//                    String remquery = "DELETE FROM cook_dish WHERE dish_id = '"+dishid+"'";
//                    stmt.executeUpdate(remquery);
//                }
                System.out.println("Enter 0 to complete your order");
                System.out.println("Enter 2 to order more");
                n=sc.nextInt();
                if(n==2)
                {
                    System.out.println("----Available MENU ----");
                    System.out.println();
                    String query3 = "SELECT * FROM cook_dish";
//                    Statement stmt = (Statement) connect.con.createStatement();
                    ResultSet res3 = stmt.executeQuery(query);
                    System.out.printf("%-10s %-10s %-15s %-25s %-20s %-10s","DISH ID","COOK ID","COOK NAME","DISH NAME","QUANTITY","PRICE");
                    System.out.println();
                    while(res3.next())
                    {
                        int cookid = res3.getInt(1);
                        String cookname = res3.getString(2);
                        String dishname = res3.getString(3);
                        int quan = res3.getInt(4);
                        int price = res3.getInt(5);
                        int dishid1 = res3.getInt(6);
                        System.out.printf("%-10s %-10s %-15s %-25s %-20s %-10s",dishid1,cookid,cookname,dishname,quan,price);
                        System.out.println();
                    }
                }
                else if(n==0) {
                    System.out.println("Bill Amount to be paid is "+totalprice);
                    System.out.println("Thank You for your Order !!!");
                    System.exit(1);
                }
        }
    }
}