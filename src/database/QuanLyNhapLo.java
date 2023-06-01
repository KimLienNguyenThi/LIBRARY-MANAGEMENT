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
import model.NhaCungCap;
import model.PhieuNhapLo;
import model.Sach;
import model.TheThanhVien;
import model.login_trave;
import view.MainView;

public class QuanLyNhapLo {

	public static QuanLyNhapLo getInstance() {
		return new QuanLyNhapLo();
	}
// đã sửa
	public JTable selectAll(JTable table) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String MaPN = null, SDTNCC = null, DiaChiCC = null, TenNhaCC = null;
		Date NgayNhap = null;
		Integer tongTien = 0;
		
		try {
			// B1: Tạo kết nối đến CSDL
		
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			String sql = " SELECT PhieuNhapLo.*,Nhacungcap.TenNhaCC, Nhacungcap.SDTNCC, Nhacungcap.DiaChiCC "
			+ "FROM quanlythuvien.PhieuNhapLo  JOIN quanlythuvien.Nhacungcap on PhieuNhapLo.mancc = nhacungcap.mancc" 
			+		" ORDER BY PhieuNhapLo.MaPN DESC ";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ
													// thông tin)

			// B4: Xử lý kết quả
			while (rs.next()) { // dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ
				MaPN = rs.getString("MaPN");
				TenNhaCC = rs.getString("TenNhaCC");
				SDTNCC = rs.getString("SDTNCC");
				DiaChiCC = rs.getString("DiaChiCC");
				NgayNhap = rs.getDate("NgayNhap");
				tongTien = rs.getInt("TongTienNhap");
				Object[] obj = { MaPN, TenNhaCC, SDTNCC, DiaChiCC, NgayNhap, tongTien };
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
	
	
	public JTable selectAllLimit(JTable table, int limit, int offset, String noidung) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String MaPN = null, SDTNCC = null, DiaChiCC = null, TenNhaCC = null;
		Date NgayNhap = null;
		Integer tongTien = 0;
		
		try {
			// B1: Tạo kết nối đến CSDL
		
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			String sql = " SELECT PhieuNhapLo.*,Nhacungcap.TenNhaCC, Nhacungcap.SDTNCC, Nhacungcap.DiaChiCC "
			+ "FROM quanlythuvien.PhieuNhapLo  JOIN quanlythuvien.Nhacungcap on PhieuNhapLo.mancc = nhacungcap.mancc " 
			+ " WHERE quanlythuvien.Nhacungcap.SDTNCC LIKE '%" + noidung + "%' OR quanlythuvien.Nhacungcap.TenNhaCC LIKE '%" + noidung + "%' "
			+		" ORDER BY PhieuNhapLo.MaPN DESC limit " + limit + " offset " + offset + "";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ
													// thông tin)

			// B4: Xử lý kết quả
			while (rs.next()) { // dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ
				MaPN = rs.getString("MaPN");
				TenNhaCC = rs.getString("TenNhaCC");
				SDTNCC = rs.getString("SDTNCC");
				DiaChiCC = rs.getString("DiaChiCC");
				NgayNhap = rs.getDate("NgayNhap");
				tongTien = rs.getInt("TongTienNhap");
				Object[] obj = { MaPN, TenNhaCC, SDTNCC, DiaChiCC, NgayNhap, tongTien };
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
	
	public int selectAllCount(String noidung) {
		Integer count = 0;
		
		try {
			// B1: Tạo kết nối đến CSDL
		
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			String sql = " SELECT count(*) as count "
			+ "FROM quanlythuvien.PhieuNhapLo  JOIN quanlythuvien.Nhacungcap on PhieuNhapLo.mancc = nhacungcap.mancc " 
			+ " WHERE quanlythuvien.Nhacungcap.SDTNCC LIKE '%" + noidung + "%' OR quanlythuvien.Nhacungcap.TenNhaCC LIKE '%" + noidung + "%' "
			+		" ORDER BY PhieuNhapLo.ngaynhap DESC";
			//System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ
													// thông tin)

			// B4: Xử lý kết quả
			while (rs.next()) { // dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ
				count = rs.getInt("count");				
			}

			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("qqqqqqqqqqqq " + count);
		return count;
	}
	
// đã sửa
	public int InsertData(PhieuNhapLo PhieuNhapLo) {
		// có dữ liệu từ người nhập
		// insert data
		// Date lúc này đang có dạng này: 'Sat Dec 15 16:37:57 MST 2012' biến đổi lại
		// date để insert được vào database
		Calendar cal = Calendar.getInstance();
		cal.setTime(PhieuNhapLo.getNgayNhap());
		String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.DATE);
		// output: 2023-04-29
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();
			// B2: Thực thi một câu lệnh SQL
			String sql = "INSERT INTO quanlythuvien.PhieuNhapLo ( NgayNhap, TongTienNhap, MaNCC, MaNV)"
					+ " VALUES ('" + formatedDate + "' , '"
					+ PhieuNhapLo.getTongTienNhap() + "' , '" 
					+ PhieuNhapLo.getMaNCC() + "' , '" 
					+ PhieuNhapLo.getMaNV() + "' )";
System.out.println(sql);
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
// thêm lô lôix
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
			String sql = "INSERT INTO quanlythuvien.chitietlo (SoLuong, MaPN)" + " VALUES ('" + chitietLo.getSoLuong()
					+ "' , '" + chitietLo.getMaPN() + "')";

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
	// thêm lô lỗi 
	
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
					+ sach.getGiaSach() + "'" + ", '" + sach.getMaDS() + "', 'Chưa Mượn')";

			kq = st.executeUpdate(sql) > 0; // trả về số lượng dòng đã upadate
			cnDatabase.disConnection(connection);
			return kq;
		} catch (Exception ex) {
			// Lỗi sẽ vô đây
			ex.printStackTrace();
			return false;
		}
	}

	public int UpdateData( PhieuNhapLo PhieuNhapLo, int MaPN) {
		// có dữ liệu từ người nhập
		// insert data
		int ketqua = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(PhieuNhapLo.getNgayNhap());
		String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
				+ cal.get(Calendar.DATE);
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			
			String sql = "UPDATE quanlythuvien.PhieuNhapLo " + "SET" + ""
					+ " NgayNhap = '" + formatedDate  +  "',"
					+ " TongTienNhap = " + PhieuNhapLo.getTongTienNhap() + ""
					  + " WHERE MaPN = '" + MaPN + "' ;";

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
// đã sửa
	public PhieuNhapLo select_ThongTinLo(int MaPN) {
		PhieuNhapLo losachchitiet = new PhieuNhapLo();
		NhaCungCap NhaCungCap = new NhaCungCap();
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT *  FROM quanlythuvien.PhieuNhapLo "
					+ "  WHERE MaPN = '" + MaPN + "' ;";

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ
													// thông tin)

			// B4: Xử lý kết quả
			while (rs.next()) { // dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ
				losachchitiet.setMaPN(rs.getInt("MaPN"));
			//	losachchitiet.setSdtNhaCungCap(rs.getString("SDTNCC"));
			//	losachchitiet.setDiaChiNhaCungCap(rs.getString("DiaChiCC"));
			//	losachchitiet.setTenNhaCungCap(rs.getString("TenNhaCC"));
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
	// đã sửa
	public NhaCungCap select_ThongTinCC(int MaPN) {
		NhaCungCap NhaCungCap = new NhaCungCap();
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT NhaCungCap.*  FROM quanlythuvien.NhaCungCap JOIN quanlythuvien.PhieuNhapLo on NhaCungCap.MaNCC = PhieuNhapLo.MaNCC "
					+ "  WHERE MaPN = '" + MaPN + "' ;";

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ
													// thông tin)

			// B4: Xử lý kết quả
			while (rs.next()) { // dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ
			
				NhaCungCap.setSdtNhaCungCap(rs.getString("SDTNCC"));
				NhaCungCap.setDiaChiNhaCungCap(rs.getString("DiaChiCC"));
				NhaCungCap.setTenNhaCungCap(rs.getString("TenNhaCC"));
				

			}

			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NhaCungCap;
	}
// k lỗi
	public JTable select_sachchitiet(JTable table, int MaPN) {

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
					+ "INNER join quanlythuvien.PhieuNhapLo on quanlythuvien.chitietlo.MaPN = quanlythuvien.PhieuNhapLo.MaPN "
					+ "WHERE quanlythuvien.chitietlo.MaPN = " + MaPN + " ;"; // kiểu int
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
// lỗi
	public JTable TimKiemLo(JTable table, String noidung) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String MaPN = null;
		String SDTNCC = null, DiaChiCC = null, TenNhaCC = null;
		;
		Date NgayNhap = null;
		Integer tongTien = 0;		
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT * " + "FROM quanlythuvien.PhieuNhapLo " + "WHERE SDTNCC LIKE '%" + noidung
					+ "%' OR TenNhaCC LIKE '%" + noidung + "%' ";

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ
													// thông tin)

			// B4: Xử lý kết quả
			while (rs.next()) { // dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ
				MaPN = rs.getString("MaPN");
				TenNhaCC = rs.getString("TenNhaCC");
				SDTNCC = rs.getString("SDTNCC");
				DiaChiCC = rs.getString("DiaChiCC");
				NgayNhap = rs.getDate("NgayNhap");
				tongTien = rs.getInt("TongTienNhap");
				Object[] obj = { MaPN, TenNhaCC, SDTNCC, DiaChiCC, NgayNhap, tongTien };
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

// k lỗi
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
	// k lỗi
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
	
	
	// QUẢN LÍ NHÀ CC
	public JTable select_NCC(JTable table) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String MaNCC = null, SDTNCC = null, DiaChiCC = null, TenNhaCC = null;
		
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			String sql = "SELECT * FROM quanlythuvien.NHACUNGCAP ";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ
													// thông tin)

			// B4: Xử lý kết quả
			while (rs.next()) { // dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ
				MaNCC = rs.getString("MaNCC");
				TenNhaCC = rs.getString("TenNhaCC");
				SDTNCC = rs.getString("SDTNCC");
				DiaChiCC = rs.getString("DiaChiCC");
				
				Object[] obj = { MaNCC, TenNhaCC, SDTNCC, DiaChiCC };
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
	// THÊM NHÀ CC
	public int Insert_NCC(NhaCungCap NhaCungCap) {
		
		
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();
			// B2: Thực thi một câu lệnh SQL
			String sql = "INSERT INTO quanlythuvien.NhaCungCap (TenNhaCC, SDTNCC, DiaChiCC )"
					+ " VALUES ('" + NhaCungCap.getTenNhaCungCap() + "' , '"
					+ NhaCungCap.getSdtNhaCungCap() + "' , '" + NhaCungCap.getDiaChiNhaCungCap()+ "' )";
			
			// B3: Tạo ra đối tượng Statement, với trả về id tự tăng của lô (để dùng insert
			// chitietlo)
			Statement st = connection.createStatement();
			st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st.getGeneratedKeys();
			int idNCCNew = -1;
			if (rs.next()) {
				idNCCNew = rs.getInt(1);
			}
			cnDatabase.disConnection(connection);
			return idNCCNew;
		} catch (Exception ex) {
			// Lỗi sẽ vô đây
			ex.printStackTrace();
			return -1;
		}
	}
	// LẤY THÔNG TIN 1 ROW NHÀ CC
	public NhaCungCap select_ThongTinNCC(int MaNCC) {
		NhaCungCap NhaCungCap = new NhaCungCap();
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			String sql =
					"SELECT * FROM quanlythuvien.NHACUNGCAP" +
			" where MaNCC = " + MaNCC + " ;";

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql); // trả về kết quả đã lấy ra (Kết quả lấy ra là 1 bộ dữ liệu đầy đủ
													// thông tin)

			// B4: Xử lý kết quả
			while (rs.next()) { // dữ liệu trả gồm nhiều bộ dữ liệu nên dùng ArrayList để lưu trữ
				//NhaCungCap.setMaNCC(rs.getInt("MaNCC"));
				NhaCungCap.setTenNhaCungCap(rs.getString("TenNhaCC"));
				NhaCungCap.setSdtNhaCungCap(rs.getString("SDTNCC"));
				NhaCungCap.setDiaChiNhaCungCap(rs.getString("DiaChiCC"));

			}

			// B5: Ngắt kết nối CSDL
			cnDatabase.disConnection(connection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return NhaCungCap;
	}
	public int UpdateNCC(NhaCungCap NhaCungCap, int MaNCC) {
		// có dữ liệu từ người nhập
		// insert data
		int ketqua = 0;
		
		try {
			// B1: Tạo kết nối đến CSDL
			Connection connection = cnDatabase.getConnection();

			// B2: Tạo ra đối tượng Statement
			Statement st = connection.createStatement();

			// B3: Thực thi một câu lệnh SQL
			
			String sql = "UPDATE quanlythuvien.NhaCungCap " + "SET" + " TenNhaCC = N'" + NhaCungCap.getTenNhaCungCap() + "',"
					 + " SDTNCC = '" + NhaCungCap.getSdtNhaCungCap() + "',"
					+ " DiaChiCC = N'" + NhaCungCap.getDiaChiNhaCungCap() + "'"
					+ " WHERE MaNCC = '" + MaNCC + "' ;";

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