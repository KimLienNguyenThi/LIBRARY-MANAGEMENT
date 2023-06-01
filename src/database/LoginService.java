package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import database.cnDatabase;
import model.login_trave;


public class LoginService {
	
	 

	public static login_trave Login(String userName, String password) {
		
         Connection conn = database.cnDatabase.getConnection();
         try {
          //Class.forName("com.mysql.jdbc.Driver");
        	 
        	 Statement stmt = conn.createStatement();  
        	 ResultSet rs= stmt.executeQuery("select hotennv, chucvu,nhanvien.manv from login join nhanvien "
        	 		+ "        		 on login.manv = nhanvien.manv where login.MaTaiKhoan='" + userName 
        			 + "' and login.MatKhau='" + password + "'");  
        	 while(rs.next()) 
        	 {
        		 String hoten = rs.getString(1);
        		 String chucvu = rs.getString(2);
        		 int manv = rs.getInt(3);
        		  System.out.println(hoten );
        		  System.out.println(chucvu );
        		  System.out.println(manv);
        	 database.cnDatabase.disConnection(conn);
        		 return  new login_trave(hoten, chucvu,manv);
        				 }
        	 
         } catch (Exception ex) {
             System.out.println("connect failure!");
             ex.printStackTrace();
         }
        return null;
     }
	
}
