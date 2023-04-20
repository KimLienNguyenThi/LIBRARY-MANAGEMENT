package model;
import java.sql.Date;

public class TheThanhVien {
	private String maThe;
	private Date ngayDangKy;
	private Date hanThe;
	private String maDocGia;
	private int phiDangKy;

	
	public TheThanhVien() {
	}

	public TheThanhVien(String maThe, Date ngayDangKy, Date hanThe, String maDocGia, int phiDangKy) {
		super();
		this.maThe = maThe;
		this.ngayDangKy = ngayDangKy;
		this.hanThe = hanThe;
		this.maDocGia = maDocGia;
		this.phiDangKy = phiDangKy;
	}

	public String getMaThe() {
		return maThe;
	}

	public void setMaThe(String maThe) {
		this.maThe = maThe;
	}

	public Date getNgayDangKy() {
		return ngayDangKy;
	}

	public void setNgayDangKy(Date ngayDangKy) {
		this.ngayDangKy = ngayDangKy;
	}

	public Date getHanThe() {
		return hanThe;
	}

	public void setHanThe(Date hanThe) {
		this.hanThe = hanThe;
	}

	public String getMaDocGia() {
		return maDocGia;
	}

	public void setMaDocGia(String maDocGia) {
		this.maDocGia = maDocGia;
	}

	public int getPhiDangKy() {
		return phiDangKy;
	}

	public void setPhiDangKy(int phiDangKy) {
		this.phiDangKy = phiDangKy;
	}
	
	
	
}
