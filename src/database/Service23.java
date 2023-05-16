package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Service23 {
	public static Service23 getInstance() {
		return new Service23();
	}

	public JTable SelectAllTheDocGia(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		try {

			String sql = "SELECT TheDocGia.MaThe, DocGia.TenDG, DocGia.SDT, DocGia.DiaChi " + "FROM TheDocGia "
					+ "JOIN DocGia ON TheDocGia.MaDG = DocGia.MaDG " + "ORDER BY TheDocGia.MaThe ASC";

			Connection conn = cnDatabase.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);

			ResultSet rs = pst.executeQuery(sql);

			while (rs.next()) {
				int mathe = rs.getInt("MaThe");
				String tendg = rs.getString("TenDG");
				String sdt = rs.getString("SDT");
				String diachi = rs.getString("DiaChi");

				Object[] obj = { mathe, tendg, sdt, diachi };

				model.addRow(obj);
			}
			cnDatabase.disConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return table;
	}

	public JTable SelectAllPhieuMuon(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		try {
			String sql = "SELECT PhieuMuon.MaPM, TheDocGia.MaThe, DocGia.TenDG, "
					+ "DocGia.SDT, PhieuMuon.NgayMuon, PhieuMuon.NgayTra, PhieuMuon.TinhTrang " + "FROM PhieuMuon "
					+ "JOIN TheDocGia ON PhieuMuon.MaThe = TheDocGia.MaThe "
					+ "JOIN DocGia ON TheDocGia.MaDG = DocGia.MaDG " + "ORDER BY PhieuMuon.MaPM ASC ";

			Connection conn = cnDatabase.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);

			ResultSet rs = pst.executeQuery(sql);

			while (rs.next()) {
				int mapm = rs.getInt("MaPM");
				int mathe = rs.getInt("MaThe");
				String tendg = rs.getString("TenDG");
				String sdt = rs.getString("SDT");
				Date ngaymuon = rs.getDate("NgayMuon");
				Date ngaytra = rs.getDate("NgayTra");
				String tinhtrang = rs.getString("TinhTrang");

				Object[] obj = { mapm, mathe, tendg, sdt, ngaymuon, ngaytra, tinhtrang };

				model.addRow(obj);
			}
			cnDatabase.disConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return table;
	}

	public JTable SelectChiTietPhieuMuon(JTable table, int MaPM) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		try {

			String sql = "SELECT Sach.MaSach, Sach.TenSach, Sach.TacGia " + "FROM ChiTietPhieuMuon "
					+ "JOIN Sach ON ChiTietPhieuMuon.MaSach = Sach.MaSach " + "WHERE MaPM = ?";

			Connection conn = cnDatabase.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, MaPM);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int masach = rs.getInt("MaSach");
				String tensach = rs.getString("TenSach");
				String tacgia = rs.getString("TacGia");

				Object[] obj = { masach, tensach, tacgia };

				model.addRow(obj);
			}
			cnDatabase.disConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return table;
	}

	public JTable SelectAllSachCon(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		try {

			String sql = "SELECT MaSach, TenSach, TacGia, NXB, NamXB, TheLoai, NgonNgu " + "FROM Sach "
					+ "WHERE TinhTrang LIKE \"Còn\"";

			Connection conn = cnDatabase.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);

			ResultSet rs = pst.executeQuery(sql);

			while (rs.next()) {
				int masach = rs.getInt("MaSach");
				String tensach = rs.getString("TenSach");
				String tacgia = rs.getString("TacGia");
				String nhaxb = rs.getString("NXB");
				Date sdt = rs.getDate("NamXB");
				String theloai = rs.getString("TheLoai");
				String ngonngu = rs.getString("NgonNgu");

				Object[] obj = { masach, tensach, tacgia, nhaxb, sdt, theloai, ngonngu };

				model.addRow(obj);
			}
			cnDatabase.disConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return table;
	}

	public int UpdateTinhTrangPhieuMuonDaTra(int maPm) {
		int ketqua = 0;
		try {

			String sql = "UPDATE PhieuMuon " + "SET TinhTrang = 'Đã trả' "
					+ "WHERE MaPM = ? AND TinhTrang <> 'Đã trả'; ";

			Connection conn = cnDatabase.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, maPm);

			ketqua = pst.executeUpdate();

			cnDatabase.disConnection(conn);
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	public int UpdateTinhTrangPhieuMuonQuahan() {
		int ketqua = 0;
		try {

			String sql = "UPDATE PhieuMuon " + "SET TinhTrang = 'Quá hạn' "
					+ "WHERE NgayTra < CURRENT_DATE() AND TinhTrang <> 'Đã trả'; ";

			Connection conn = cnDatabase.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);

			ketqua = pst.executeUpdate(sql);

			cnDatabase.disConnection(conn);
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	public int UpdateTinhTrangSachCon(int maPm) {
		int ketqua = 0;
		try {

			String sql = "UPDATE SACH " + "SET TinhTrang = 'Còn' " + "WHERE MaSach " + "IN " + "(SELECT MaSach "
					+ " FROM ChiTietPhieuMuon " + " WHERE MaPM = ?); ";

			Connection conn = cnDatabase.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, maPm);

			ketqua = pst.executeUpdate();

			cnDatabase.disConnection(conn);
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	public int UpdateTinhTrangSachHet(int maPm) {
		int ketqua = 0;
		try {

			String sql = "UPDATE SACH " + "SET TinhTrang = 'Hết' " + "WHERE MaSach " + "IN " + "(SELECT MaSach "
					+ " FROM ChiTietPhieuMuon " + " WHERE MaPM = ?); ";

			Connection conn = cnDatabase.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setInt(1, maPm);

			ketqua = pst.executeUpdate();

			cnDatabase.disConnection(conn);
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	public int InsertPhieuMuon(int MaThe, Date NgayMuon, Date NgayTra) {
		int ketqua = 0;
		try {
			Connection conn = cnDatabase.getConnection();
			String sql = "INSERT INTO PhieuMuon (MaThe, NgayMuon, NgayTra) " + "VALUES (?, ?, ?); ";

			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, MaThe);
			pst.setDate(2, NgayMuon);
			pst.setDate(3, NgayTra);

			ketqua = pst.executeUpdate();
			cnDatabase.disConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	public int InsertChiTietPhieuMuon(int MaPM, int MaSach) {
		int ketqua = 0;
		try {
			Connection conn = cnDatabase.getConnection();
			String sql = "INSERT INTO ChiTietPhieuMuon (MaPM, MaSach) " + "VALUES (?, ?); ";

			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, MaPM);
			pst.setInt(2, MaSach);

			ketqua = pst.executeUpdate();
			cnDatabase.disConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ketqua;
	}

	public ArrayList<Integer> SelectAllMaPm(int MaThe) {
		ArrayList<Integer> arr = new ArrayList<>();

		try {
			String sql = "SELECT MaPM " + "FROM PhieuMuon " + "WHERE MaThe = ? ";

			Connection conn = cnDatabase.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, MaThe);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int mapm = rs.getInt("MaPM");

				arr.add(mapm);
			}
			cnDatabase.disConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}

}
