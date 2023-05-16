
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;




public class QuanLySach {
	public static QuanLySach getInstance() {
		return new QuanLySach();
	}
	
	public JTable selectAll(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		
		try {
			
			
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();
			
			// B2: Tạo ra đối tượng Statement 
			Statement st = connection.createStatement();
			
			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT * FROM SACH";
					
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);		// trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ thông tin)
			
			// B4: Xử lý kết quả 
			while(rs.next()) {				// dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ
				int MaSach = rs.getInt("MaSach");
				String TenSach = rs.getString("TenSach");
				String TacGia = rs.getString("TacGia");
				String NXB = rs.getString("NXB");
				int NamXB = rs.getInt("NamXB");
				String TheLoai = rs.getString("TheLoai");
				String GiaSach = rs.getString("GiaSach");
				String NgonNgu = rs.getString("NgonNgu");
				String TinhTrang = rs.getString("TinhTrang");
				
				Object obj[]  = {MaSach, TenSach, TacGia,NXB, NamXB,TheLoai ,GiaSach,NgonNgu,TinhTrang};
				model.addRow(obj);		// thêm từng bộ dữ liệu lấy ra được vào danh sách kết quả
			}
			
			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);
			
		} catch (SQLException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}
	
	
	public JTable selectbyTenSach(JTable table, String tenSach) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		
		try {
			
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();
			
			// B2: Tạo ra đối tượng Statement 
			Statement st = connection.createStatement();
			
			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT * FROM SACH WHERE TenSach = '"+tenSach+"'";
					
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);		// trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ thông tin)
			
			// B4: Xử lý kết quả 
			while(rs.next()) {				// dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ
				int MaSach = rs.getInt("MaSach");
				String TenSach = rs.getString("TenSach");
				String TheLoai = rs.getString("TheLoai");
				int NamXB = rs.getInt("NamXB");
				String NXB = rs.getString("NXB");
				String TacGia = rs.getString("TacGia");
				String NgonNgu = rs.getString("NgonNgu");
				String TinhTrang = rs.getString("TinhTrang");
				String GiaSach = rs.getString("GiaSach");
				
				Object obj[]  = {MaSach, TenSach, TheLoai, NamXB, NXB, TacGia,NgonNgu,TinhTrang,GiaSach};
				model.addRow(obj);		// thêm từng bộ dữ liệu lấy ra được vào danh sách kết quả
			}
			
			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);
			
		} catch (SQLException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}

}
