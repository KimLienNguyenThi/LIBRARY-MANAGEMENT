package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import database.cnDatabase;


public class LoginService {
	 public static String Login(String userName, String password) {
         Connection conn = database.cnDatabase.getConnection();
         try {
          //Class.forName("com.mysql.jdbc.Driver");
        	 Statement stmt = conn.createStatement();  
        	 ResultSet rs= stmt.executeQuery("select hotennv from login join nhanvien "
        	 		+ "        		 on login.manv = nhanvien.manv where login.MANV='" + userName 
        			 + "' and login.MatKhau='" + password + "'");  
        	 while(rs.next()) 
        	 {
        		 String hoten = rs.getString(1);
//        		 SELECT hotennv
//        		 FROM login join nhanvien
//        		 on login.manv = nhanvien.manv;
//        		 System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  ");  
        		
        	 database.cnDatabase.disConnection(conn);
        		 return hoten;
        				 }
        	 
         } catch (Exception ex) {
             System.out.println("connect failure!");
             ex.printStackTrace();
         }
        return "";
     }
}
