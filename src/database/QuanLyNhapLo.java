package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.ChiTietLo;
import model.DocGia;
import model.LoSach;
import model.Sach;
import model.TheThanhVien;
import view.MainView;

public class QuanLyNhapLo {

	public static QuanLyNhapLo getInstance() {
		return new QuanLyNhapLo();
	}

	public JTable selectAll(JTable table) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String MaLo = null, SDTNCC = null, DiaChiCC = null, TenNhaCC = null;
		Date NgayNhap = null;
		Integer tongTien = 0;
		
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT * " + "FROM quanlythuvien.losach " + "order by NgayNhap desc ";

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ
													// thông tin)

			// B4: Xử lý kết quả
			while (rs.next()) { // dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ
				MaLo = rs.getString("MaLo");
				TenNhaCC = rs.getString("TenNhaCC");
				SDTNCC = rs.getString("SDTNCC");
				DiaChiCC = rs.getString("DiaChiCC");
				NgayNhap = rs.getDate("NgayNhap");
				tongTien = rs.getInt("TongTienNhap");
				Object[] obj = { MaLo, TenNhaCC, SDTNCC, DiaChiCC, NgayNhap, tongTien };
				model.addRow(obj);
			}

			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}

	public int InsertData(LoSach losach) {
		// có dữ liệu từ người nhập
		// insert data
		// Date lúc này đang có dạng này: 'Sat Dec 15 16:37:57 MST 2012' biến đổi lại
		// date để insert được vào database
		Calendar cal = Calendar.getInstance();
		cal.setTime(losach.getNgayNhap());
		String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.DATE);
		// output: 2023-04-29
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();
			// B2: Thực thi một câu lệnh SQL
			String sql = "INSERT INTO quanlythuvien.losach (TenNhaCC, NgayNhap, SDTNCC, DiaChiCC, TongTienNhap)"
					+ " VALUES ('" + losach.getTenNhaCungCap() + "' , '" + formatedDate + "' , '"
					+ losach.getSdtNhaCungCap() + "' , '" + losach.getDiaChiNhaCungCap() + "' , '"
					+ losach.getTongTienNhap() + "' )";

			// B3: Tạo ra đối tượng Statement, với trả về id tự tăng của lô (để dùng insert
			// chitietlo)
			Statement st = connection.createStatement();
			st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st.getGeneratedKeys();
			int idLoNew = -1;
			if (rs.next()) {
				idLoNew = rs.getInt(1);
			}
			cnDatabase.disConnection(connection);
			return idLoNew;
		} catch (Exception ex) {
			// Lỗi sẽ vô đây
			ex.printStackTrace();
			return -1;
		}
	}

	public int InsertDataChiTietLo(ChiTietLo chitietLo) {
		// có dữ liệu từ người nhập
		// insert data
		boolean kq = false;// giá trị khởi tạo ban đầu
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			String sql = "INSERT INTO quanlythuvien.chitietlo (SoLuong, MaLo)" + " VALUES ('" + chitietLo.getSoLuong()
					+ "' , '" + chitietLo.getMaLo() + "')";

			st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st.getGeneratedKeys();
			int idChiTietLoNew = -1;
			if (rs.next()) {
				idChiTietLoNew = rs.getInt(1);
			}
			cnDatabase.disConnection(connection);
			return idChiTietLoNew;
		} catch (Exception ex) {
			// Lỗi sẽ vô đây
			ex.printStackTrace();
			return -1;
		}
	}

	public boolean InsertDataSach(Sach sach) {
		// có dữ liệu từ người nhập
		// insert data
		boolean kq = false;// giá trị khởi tạo ban đầu
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			String sql = "INSERT INTO quanlythuvien.sach (TenSach, TheLoai, NamXB, NXB, TacGia,NGonNgu, GiaSach, MaDS, TinhTrang)"
					+ " VALUES ('" + sach.getTenSach() + "' , '" + sach.getTheLoai() + "', '" + sach.getNamXuatBan()
					+ "'" + ", '" + sach.getNXB() + "', '" + sach.getTacGia() + "', '" + sach.getNgonNgu() + "', '"
					+ sach.getGiaSach() + "'" + ", '" + sach.getMaDS() + "', 'Còn')";

			kq = st.executeUpdate(sql) > 0; // trả về số lượng dòng đã upadate
			cnDatabase.disConnection(connection);
			return kq;
		} catch (Exception ex) {
			// Lỗi sẽ vô đây
			ex.printStackTrace();
			return false;
		}
	}

	public int UpdateData(LoSach losach, int maLo) {
		// có dữ liệu từ người nhập
		// insert data
		int ketqua = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(losach.getNgayNhap());
		String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.DATE);
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL

			String sql = "UPDATE quanlythuvien.losach " + "SET" + " TenNhaCC = N'" + losach.getTenNhaCungCap() + "',"
					+ " NgayNhap = '" + formatedDate + "'," + " SDTNCC = '" + losach.getSdtNhaCungCap() + "',"
					+ " DiaChiCC = N'" + losach.getDiaChiNhaCungCap() + "'," + " TongTienNhap = '"
					+ losach.getTongTienNhap() + "'" + " WHERE MaLo = '" + maLo + "' ;";

			System.out.println(sql);
			ketqua = st.executeUpdate(sql); // trả về số lượng dòng đã upadate
			// B4: Xử lý kết quả
			System.out.println("Ban da thuc thi: " + sql);
			System.out.println("Co " + ketqua + " dong da thay doi!");

			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);
//					return st.executeUpdate(sql) > 0;		// trả về số lượng dòng đã upadate	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketqua;
	}

	public LoSach select_ThongTinLo(int MaLo) {
		LoSach losachchitiet = new LoSach();
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT *  FROM quanlythuvien.losach   WHERE MaLo = '" + MaLo + "' ;";

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ
													// thông tin)

			// B4: Xử lý kết quả
			while (rs.next()) { // dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ
				losachchitiet.setMaLo(rs.getInt("MaLo"));
				losachchitiet.setSdtNhaCungCap(rs.getString("SDTNCC"));
				losachchitiet.setDiaChiNhaCungCap(rs.getString("DiaChiCC"));
				losachchitiet.setTenNhaCungCap(rs.getString("TenNhaCC"));
				losachchitiet.setNgayNhap(rs.getDate("NgayNhap"));
				losachchitiet.setTongTienNhap(rs.getInt("TongTienNhap"));

			}

			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return losachchitiet;
	}

	public JTable select_sachchitiet(JTable table, int MaLo) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int maDS = 0, namXuatBan = 0, soLuong = 0, giaSach = 0;
		String tenSach = null, theLoai = null, NXB = null, tacGia = null, ngonNgu = null;

		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT distinct quanlythuvien.sach.MaDS , quanlythuvien.sach.TenSach, quanlythuvien.sach.NamXB, quanlythuvien.sach.NXB,  quanlythuvien.sach.NgonNgu, quanlythuvien.sach.TheLoai,"
					+ " quanlythuvien.sach.GiaSach, quanlythuvien.sach.TacGia, quanlythuvien.chitietlo.SoLuong "
					+ "FROM quanlythuvien.sach INNER JOIN quanlythuvien.chitietlo on quanlythuvien.sach.MaDS = quanlythuvien.chitietlo.MaDS "
					+ "INNER join quanlythuvien.losach on quanlythuvien.chitietlo.MaLo = quanlythuvien.losach.MaLo "
					+ "WHERE quanlythuvien.chitietlo.MaLo = " + MaLo + " ;"; // kiểu int
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ
													// thông tin)

			// B4: Xử lý kết quả
			while (rs.next()) { // dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ

				maDS = rs.getInt("MaDS");
				tenSach = rs.getString("TenSach");
				namXuatBan = rs.getInt("NamXB");
				NXB = rs.getString("NXB");
				ngonNgu = rs.getString("NgonNgu");
				theLoai = rs.getString("TheLoai");
				giaSach = rs.getInt("GiaSach");
				tacGia = rs.getString("TacGia");
				soLuong = rs.getInt("SoLuong");

				Object[] obj = { maDS, tenSach, NXB, namXuatBan, tacGia, theLoai, ngonNgu, giaSach, soLuong };
				model.addRow(obj);
			}

			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}

	public JTable TimKiemLo(JTable table, String noidung) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String MaLo = null, SDTNCC = null, DiaChiCC = null, TenNhaCC = null;
		;
		Date NgayNhap = null;
		Integer tongTien = 0;		
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT * " + "FROM quanlythuvien.losach " + "WHERE SDTNCC LIKE '%" + noidung
					+ "%' OR TenNhaCC LIKE '%" + noidung + "%' ";

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ
													// thông tin)

			// B4: Xử lý kết quả
			while (rs.next()) { // dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ
				MaLo = rs.getString("MaLo");
				TenNhaCC = rs.getString("TenNhaCC");
				SDTNCC = rs.getString("SDTNCC");
				DiaChiCC = rs.getString("DiaChiCC");
				NgayNhap = rs.getDate("NgayNhap");
				tongTien = rs.getInt("TongTienNhap");
				Object[] obj = { MaLo, TenNhaCC, SDTNCC, DiaChiCC, NgayNhap, tongTien };
				model.addRow(obj);
			}

			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}


	public Sach select_sachSua( int MaDS) {
		
		Sach thongtinsach = new Sach();
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT distinct quanlythuvien.sach.MaDS , quanlythuvien.sach.TenSach, quanlythuvien.sach.NamXB, quanlythuvien.sach.NXB,"
					+ "  quanlythuvien.sach.NgonNgu, quanlythuvien.sach.TheLoai,"
					+ " quanlythuvien.sach.GiaSach, quanlythuvien.sach.TacGia, quanlythuvien.chitietlo.SoLuong "
					+ "FROM quanlythuvien.sach INNER JOIN quanlythuvien.chitietlo on quanlythuvien.sach.MaDS = quanlythuvien.chitietlo.MaDS "
					
					+ "WHERE quanlythuvien.chitietlo.MaDS = " + MaDS + " ;"; // kiểu int
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ
													// thông tin)

			// B4: Xử lý kết quả
			while (rs.next()) { // dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ

				thongtinsach.setMaDS(rs.getInt("MaDS"));
				thongtinsach.setTenSach(rs.getString("TenSach"));
				thongtinsach.setNamXuatBan(rs.getInt("NamXB"));
				thongtinsach.setNXB(rs.getString("NXB"));
				thongtinsach.setNgonNgu(rs.getString("NgonNgu"));
				thongtinsach.setTheLoai(rs.getString("TheLoai"));
				thongtinsach.setGiaSach(rs.getInt("GiaSach"));
				thongtinsach.setTacGia(rs.getString("TacGia"));
				thongtinsach.setSoLuong(rs.getInt("SoLuong"));
				
			}

			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thongtinsach;

	}
	public int UpdateSach(Sach sach, int maDS) {
		// có dữ liệu từ người nhập
		// insert data
		int ketqua = 0;
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
		
			String sql = "UPDATE (quanlythuvien.sach a join quanlythuvien.chitietlo b on a.mads =b.mads) "
					+ " SET"+
					" TenSach = '" +sach.getTenSach() +"',"+
					" NXB = '" +sach.getNXB()+"',"+
					" NamXB= '" +sach.getNamXuatBan() +"',"+
					" GiaSach= '" +sach.getGiaSach() +"',"+
					" NgonNgu = '" +sach.getNgonNgu() +"',"+
					" TacGia= '" +sach.getTacGia() +"',"+
					" TheLoai= '" +sach.getTheLoai() +"'"+
					  " WHERE a.Mads = " + maDS + " ;";

			System.out.println(sql);
			ketqua = st.executeUpdate(sql); // trả về số lượng dòng đã upadate
			// B4: Xử lý kết quả
			System.out.println("Ban da thuc thi: " + sql);
			System.out.println("Co " + ketqua + " dong da thay doi!");

			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);
//					return st.executeUpdate(sql) > 0;		// trả về số lượng dòng đã upadate	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketqua;
	}
}