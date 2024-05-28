import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        connect con = new connect();
        con.getconnect();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Cloud Kitchen");
        System.out.println();
        System.out.println("Press 1 to CUSTOMER Side");
        System.out.println("Press 2 to FOOD PREPARER Side");
        System.out.println("Press 3 to Exit");
        int n = sc.nextInt();
        while (n != 3) {
            if (n == 1) {
                System.out.println("PRESS 1 TO CUSTOMER LOG IN");
            System.out.println("PRESS 2 TO CUSTOMER SIGN UP");
                int n2 = sc.nextInt();
                if (n2 == 1) {
                    System.out.println("Enter your Username");
                    String uuname = sc.next();
                    System.out.println("Enter your Password");
                    String upass = sc.next();
                    con.userLogin(uuname, upass);
                } else if (n2 == 2) {
                    System.out.println("Enter your name");
                    String uname = sc.next();
                    System.out.println("Enter your Mobile Number");
                    long unum = con.checkUserMobNo();
//                    String unum = sc.next();
                    System.out.println("Enter your username");
//                    String uusername = sc.next();
                    String useruname = con.userUsernameCheck();
                    System.out.println("Enter your password");
                    String upass = con.validateUserPass();
                    con.userSignUp(uname, unum, useruname, upass);
                }
            } else if (n == 2) {
                System.out.println("PRESS 1 TO FOOD PREPARER LOG IN");
                System.out.println("PRESS 2 TO FOOD PREPARER SIGN UP");
                int n3 = sc.nextInt();
                if (n3 == 1) {
                    System.out.println("Enter your UserName");
                    String cuser = sc.next();
                    System.out.println("Enter your Password");
                    String cpass = sc.next( );
                    con.cookLogIn(cuser, cpass);
                } else if (n3 == 2) {
                    System.out.println("Enter your Name");
                    String cname = sc.next();
                    System.out.println("Enter your Mobile Number");
                    Long cnum = con.checkCookMobNo();
                    System.out.println("Enter your Username");
//                    String cuser = sc.next();
                    String cookuname=con.cookUsernameCheck();
                    System.out.println("Enter your Password");
                    String cpass = con.validateCookPass();
                    con.cookSignUp(cname, cnum, cookuname, cpass);
                }
            }
        }
    }
}