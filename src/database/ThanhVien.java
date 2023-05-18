package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.DocGia;
import model.TheThanhVien;
import view.MainView;

public class ThanhVien {

	public static ThanhVien getInstance() {
		return new ThanhVien();
	}

	public void themThanhVien(DocGia t, TheThanhVien x) {
		int ketqua = 0;

		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();
			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();
			// B3: Thực thi một câu lệnh SQL
			String sql = "INSERT INTO docgia (TenDG, SDT, NgaySinh, DiaChi)" + " VALUES ('" + t.getTenDocGia() + "' , '"
					+ t.getSDT() + "' , '" + t.getNgaySinh() + "' , '" + t.getDiaChi() + "' )";

			String sql1 = "INSERT INTO thedocgia (NgayDK, HanThe, MaDG, PhiDK)" + " VALUES ('" + x.getNgayDangKy()
					+ "' , '" + x.getHanThe() + "' , '" + x.getMaDocGia() + "' , '" + x.getPhiDangKy() + "' );";

			ketqua = st.executeUpdate(sql) + st.executeUpdate(sql1); // trả về số lượng dòng đã upadate

			// B4: Xử lý kết quả
			System.out.println("Ban da thuc thi: " + sql);
			System.out.println("Ban da thuc thi: " + sql1);
			System.out.println("Co " + ketqua + " dong da thay doi!");

			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public int KtraSDT_TTV(String sdt) {
//		int ketqua = 0;
//		String ketqua_SDT;
//		try {
//			// B1: Tạo kết nối đến CSDL
//			Connection connection = cnDatabase.getConnection();
//			// B2: Tạo ra đối tượng Statement
//			Statement st = connection.createStatement();
//			// B3: Thực thi một câu lệnh SQL
//			String sql = "SELECT SDT FROM docgia WHERE SDT = '" +sdt +"';";
//
//			System.out.println(sql);
//			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra
//			// B4: Xử lý kết quả
//			while (rs.next()) {
//				ketqua_SDT = rs.getString("SDT");
//				if(ketqua_SDT.equals("") == true) {
//					System.out.println("SDT BI TRUNG");
//					ketqua = 1;
//				}else {
//					ketqua = 0;
//				}
//			}
//
//			// B5: Ngắt kết nối CSDL
//			cnDatabase.disConnection(connection);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return ketqua;
//	}

	public int selectRowLast_DocGia() {
		int ketqua_docGia = 0;

		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();
			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();
			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT MaDG FROM docgia WHERE MaDG=(SELECT MAX(MaDG) FROM docgia);";

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra
			// B4: Xử lý kết quả
			while (rs.next()) {
				ketqua_docGia = rs.getInt("MaDG");
			}
			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua_docGia;
	}

	public int selectRowLast_TheThanhVien() {
		int ketqua_theThanhVien = 0;

		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();
			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();
			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT MaThe FROM thedocgia WHERE MaThe=(SELECT MAX(MaThe) FROM thedocgia);";

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra
			// B4: Xử lý kết quả
			while (rs.next()) {
				ketqua_theThanhVien = rs.getInt("MaThe");
			}
			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua_theThanhVien;
	}

	public JTable selectAll(JTable table) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String TenDG = null, SDT = null, DiaChi = null, TinhTrang = null;
		Date NgaySinh = null, NgayDK = null, HanThe = null;
		int MaDG = 0, PhiDK = 0;

		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();
			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();
			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT * " + "FROM docgia " + "INNER JOIN thedocgia " + "ON docgia.MaDG = thedocgia.MaDG;";

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ
													// thông tin)
			// B4: Xử lý kết quả
			while (rs.next()) { // dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ
				MaDG = rs.getInt("MaDG");
				TenDG = rs.getString("TenDG");
				SDT = rs.getString("SDT");
				DiaChi = rs.getString("DiaChi");
				NgaySinh = rs.getDate("NgaySinh");
				NgayDK = rs.getDate("NgayDK");
				HanThe = rs.getDate("HanThe");
				PhiDK = rs.getInt("PhiDK");

				long millis = System.currentTimeMillis();
				java.sql.Date date = new java.sql.Date(millis);
				if (HanThe.before(date) == true) {
					TinhTrang = "Hết hạn";
				} else {
					TinhTrang = "Còn";
				}

				Object[] obj = { MaDG, TenDG, SDT, NgaySinh, DiaChi, NgayDK, HanThe, PhiDK, TinhTrang };
				model.addRow(obj);
			}

			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return table;
	}

	public JTable selectBySDT(JTable table, String sdt) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String TenDG = null, SDT = null, DiaChi = null, TinhTrang = null;
		Date NgaySinh = null, NgayDK = null, HanThe = null;
		int MaDG = 0, PhiDK = 0;
		int timKiem = 0;

		// Tìm Mã độc giả trùng với sđt nhập vào
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();
			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();
			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT * FROM docgia WHERE SDT = '" + sdt + "';";

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			// B4: Xử lý kết quả
			while (rs.next()) {
				timKiem = rs.getInt("MaDG");
			}
			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Lấy dữ liệu dựa trên mã độc giả tìm được
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();
			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();
			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT * FROM docgia WHERE MaDG = " + timKiem + ";";

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ
													// thông tin)
			// B4: Xử lý kết quả
			while (rs.next()) { // dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ
				MaDG = rs.getInt("MaDG");
				TenDG = rs.getString("TenDG");
				SDT = rs.getString("SDT");
				DiaChi = rs.getString("DiaChi");
				NgaySinh = rs.getDate("NgaySinh");
			}
			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();
			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();
			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT * FROM thedocgia WHERE MaDG = " + timKiem + ";";

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ
													// thông tin)
			// B4: Xử lý kết quả
			while (rs.next()) {
				NgayDK = rs.getDate("NgayDK");
				HanThe = rs.getDate("HanThe");
				PhiDK = rs.getInt("PhiDK");
			}
			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		if (HanThe.before(date) == true) {
			TinhTrang = "Hết hạn";
		} else {
			TinhTrang = "";
		}

		Object[] obj = { MaDG, TenDG, SDT, NgaySinh, DiaChi, NgayDK, HanThe, PhiDK, TinhTrang };
		model.addRow(obj);
		return table;
	}

	public DocGia selectDocGiaBySDT(int maDG) {
		DocGia dg = new DocGia();

		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();
			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();
			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT * FROM docgia WHERE MaDG = " + maDG + ";";

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra
			// B4: Xử lý kết quả
			while (rs.next()) {
				dg.setMaDocGia(rs.getInt("MaDG"));
				dg.setTenDocGia(rs.getString("TenDG"));
				dg.setSDT(rs.getString("SDT"));
				dg.setDiaChi(rs.getString("DiaChi"));
				dg.setNgaySinh(rs.getDate("NgaySinh"));
			}
			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dg;
	}

	public TheThanhVien selectTheThanhVienBySDT(int maDG) {
		TheThanhVien theThanhVien = new TheThanhVien();

		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();
			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();
			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT * FROM thedocgia WHERE MaDG = " + maDG + ";";

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra
			// B4: Xử lý kết quả
			while (rs.next()) {
				theThanhVien.setMaThe(rs.getInt("MaThe"));
				theThanhVien.setNgayDangKy(rs.getDate("NgayDK"));
				theThanhVien.setHanThe(rs.getDate("HanThe"));
				theThanhVien.setMaDocGia(rs.getInt("MaDG"));
				theThanhVien.setPhiDangKy(rs.getInt("PhiDK"));
			}
			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theThanhVien;
	}

	public int updateThongTinThanhVien(DocGia dg, int maDG) {
		int ketqua = 0;

		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();
			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();
			// B3: Thực thi một câu lệnh SQL
			String sql = "UPDATE docgia " + "SET" + " TenDG = '" + dg.getTenDocGia() + "'," + " SDT = '" + dg.getSDT()
					+ "'," + " DiaChi= '" + dg.getDiaChi() + "'," + " NgaySinh= '" + dg.getNgaySinh() + "'"
					+ " WHERE MaDG = '" + maDG + "' ;";

			ketqua = st.executeUpdate(sql); // trả về số lượng dòng đã upadate
			// B4: Xử lý kết quả
			System.out.println("Ban da thuc thi: " + sql);
			System.out.println("Co " + ketqua + " dong da thay doi!");
			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketqua;
	}

	public int updateTheThanhVien(TheThanhVien ttv, DocGia dg, int maDG) {
		int ketqua = 0;

		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();
			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();
			// B3: Thực thi một câu lệnh SQL
			String sql = "UPDATE thedocgia " + "SET" + " NgayDK = '" + ttv.getNgayDangKy() + "'," + " HanThe = '"
					+ ttv.getHanThe() + "'," + " PhiDK = " + ttv.getPhiDangKy() + " WHERE MaDG = " + maDG + ";";

			ketqua = st.executeUpdate(sql); // trả về số lượng dòng đã upadate
			// B4: Xử lý kết quả
			System.out.println("Ban da thuc thi: " + sql);
			System.out.println("Co " + ketqua + " dong da thay doi!");
			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}
}
