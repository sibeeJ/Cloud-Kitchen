import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class connect{
    Scanner sc = new Scanner(System.in);
    Home home = new Home();
    static Connection con=null;
    static Statement stmt;
    static String useruname="";
    static String cookusername="";
    static long usermob = 0;
    static long cookmob = 0;
    static String userpass = "";
    static String cookpass = "";
   public void getconnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/cloudkitchen", "root", "");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void userSignUp(String name,Long num,String uname,String pass) throws Exception
    {
        String query = "INSERT INTO sign_up(Name,Mobile_Number,Username,Password) VALUES('"+name+"','"+num+"','"+uname+"','"+pass+"')";
//        Statement stmt = (Statement) con.createStatement();
        stmt.executeUpdate(query);
        System.out.println("Sign up Successful");
    }



    public String userUsernameCheck() throws SQLException {
        String name=sc.next();
        String query="select Username from sign_up where Username='"+name+"'";
        ResultSet res=stmt.executeQuery(query);
        if(res.next()){
            System.out.println("Username already exists");
            System.out.println("Enter another Username");
            userUsernameCheck();
        }
        else{
            useruname=name;
        }
        return useruname;
    }

    public  long checkUserMobNo()
    {
        String mobno = sc.next();
        Pattern ptrn = Pattern.compile("^[6-9][0-9]{9}$");
        Matcher match = ptrn.matcher(mobno);
        boolean check = match.find();
        if(check) {
            usermob = Long.parseLong(mobno);
        }
        else {
            System.out.println("Enter 10 digit Correct Mobile Number which starts with 9 or 8 or 7 or 6");
            System.out.println("Enter Mobile Number ");
            checkUserMobNo();
        }
        return usermob;
    }

    public String validateUserPass()
    {
        System.out.println("Password must have atlest one lowercase, one upppercase, one numeric , special characters among @#$% and length should be within 8 to 20 characters");
        String pass = sc.next();
        Pattern ptrn = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$");
        Matcher mat = ptrn.matcher(pass);
        boolean check = mat.find();
        if(check)
            userpass = pass;
        else
            validateUserPass();
        return userpass;
    }


    public void userLogin(String uname,String pass) throws Exception
    {
//        Statement stmt = (Statement) con.createStatement();
        ResultSet res = stmt.executeQuery("SELECT COUNT(*) FROM sign_up where Username='"+uname+"' and Password = '"+pass+"'");
        if(res.next())
        {
            int count = res.getInt(1);
            if(count==1)
            {
                System.out.println("Logged in Successfully");
                System.out.println();
                System.out.println("Welcome " +uname+ " to our Cloud Kitchen");
                home.uUserHome(uname);
            }
            else
            {
                System.out.println("Invalid Username and Password! Try Again !!!");
                System.out.println();
                System.out.println("Enter your Correct UserName Again");
                String uuname = sc.next();
                System.out.println("Enter your Correct Password Again");
                String upass = sc.next();
                userLogin(uuname,upass);
            }
        }
    }




    public void cookLogIn(String cuser,String cpass) throws Exception
    {
//        Statement stmt = (Statement) con.createStatement();
        ResultSet res = stmt.executeQuery("SELECT COUNT(*) FROM cooksign_up where Username='"+cuser+"' and Password = '"+cpass+"'");
        if(res.next())
        {
            int count = res.getInt(1);
            if(count==1)
            {
                System.out.println("Logged in Successfully");
                System.out.println();
                System.out.println("Welcome Chef " +cuser+ " to our Cloud Kitchen");

                ResultSet res1 = stmt.executeQuery("SELECT cookId,Name from cooksign_up where username = '"+cuser+"'");
                int cookid =0;
                String name = "";
                if(res1.next())
                {
                    cookid = res1.getInt(1);
                    name = res1.getString(2);
                }
                home.pUserHome(cookid,name);
            }
            else
            {
                System.out.println("Invalid Username and Password");
                System.out.println();
                System.out.println("Enter your Username Again");
                String cookuname = sc.next();
                System.out.println("Enter your Password Again");
                String cookpass = sc.next();
                cookLogIn(cookuname,cookpass);
            }
        }
    }
    public String cookUsernameCheck() throws SQLException {
       String name=sc.next();
       String query="select Username from cooksign_up where Username='"+name+"'";
       ResultSet res=stmt.executeQuery(query);
       if(res.next()){
           System.out.println("UserName Already Exists");
           System.out.println("Enter Another Username");
           cookUsernameCheck();
       }
       else{
            cookusername=name;
       }
       return cookusername;
    }

    public  long checkCookMobNo() throws Exception
    {
        String mobno = sc.next();
        Pattern ptrn = Pattern.compile("^[6-9][0-9]{9}$");
        Matcher match = ptrn.matcher(mobno);
        boolean check = match.find();
        if(check) {
            cookmob = Long.parseLong(mobno);
        }
        else {
            System.out.println("Enter 10 digit Correct Mobile Number which starts with 9 or 8 or 7 or 6");
            System.out.println("Enter Mobile Number ");
            checkCookMobNo();
        }
        return cookmob;
    }


    public String validateCookPass()
    {
        System.out.println("Password must have atlest one lowercase, one upppercase, one numeric , special characters among @#$%. and length should be within 8 to 20 characters");
        String pass = sc.next();
        Pattern ptrn = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$");
        Matcher mat = ptrn.matcher(pass);
        boolean check = mat.find();
        if(check)
            cookpass = pass;
        else
            validateCookPass();
        return cookpass;
    }



    public void cookSignUp(String cname,Long cnum,String confirmeduuname,String cpass) throws Exception
    {
        System.out.println(confirmeduuname);
        String query = "INSERT INTO cooksign_up (Name,Mobile_Number,Username,Password) VALUES('"+cname+"','"+cnum+"','"+confirmeduuname+"','"+cpass+"')";
        Statement stmt = (Statement) con.createStatement();
        stmt.executeUpdate(query);
        System.out.println("Sign up successfully");
    }


//    public String checkCookUserName(String cname) throws Exception {
////        Statement stmt = (Statement) con.createStatement();
////        ResultSet res = stmt.executeQuery("SELECT COUNT(*) FROM cooksign_up where Username = '" + cname + "'");
//
//        ResultSet res = stmt.executeQuery("SELECT Username FROM cooksign_up where Username = '"+cname+"'");
//        if (res.next() == false) {
//            System.out.println(cname);
//            return cname;
//        } else {
//            System.out.println("Username You have Entered is already exists");
//            System.out.println("Enter Another Username");
//            String new_name = sc.next();
//            checkCookUserName(new_name);
//        }
//        return "";
//    }
        /*  int count = res.getInt(1);
            if (count > 0) {
                System.out.println("username already exists");
                cname = null;
                String new_name=sc.next();
                checkCookUserName(new_name);
            } else {
                System.out.println(cname);
                return cname;
            }*/
//    }


//    static Statement stmt;
//    {
//        try {
//            stmt = (Statement) con.createStatement();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//            if (res.next()) {
//                int count = 0;
//                count = res.getInt(1);
//                if (count != 0) {
//                    System.out.println("Username already exits");
//                }
//                while (count != 0) {
//                    System.out.println("Enter Any other Username");
//                    String uname1 = sc.next();
//                    checkUserName(uname1);
//                        uname = uname1;
//                        count=0;
//                }
//            }
//        public void signup() throws Exception {
//            String query = "create table if not exists sign_up(Name VARCHAR(20),Mobile_Number INT, Username VARCHAR(30), Password VARCHAR(30))";
//            Statement stmt = (Statement) con.createStatement();
//            int res = stmt.executeUpdate(query);
//        }

    //    public String checkUserName(String uname) throws Exception {
////        Statement stmt = (Statement) con.createStatement();
//        ResultSet res = stmt.executeQuery("SELECT COUNT(*) FROM sign_up where Username = '" + uname + "'");
//        while (res.next()) {
//            int count = res.getInt(1);
//            if (count > 0) {
//                System.out.println("username already exists");
//                String new_username=sc.next();
//                res.close();
//                checkUserName(new_username);
//            } else {
////                System.out.println(uname);
//                return uname;
//            }
//        }
////        System.out.println(uname);
//        return "";
//    }


}


