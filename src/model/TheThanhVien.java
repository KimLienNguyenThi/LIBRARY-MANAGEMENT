package model;
import java.sql.Date;

public class TheThanhVien {
	private int maThe;
	private Date ngayDangKy;
	private Date hanThe;
	private int maDocGia;
	private int phiDangKy;

	
	public TheThanhVien() {
	}

	public TheThanhVien(int maThe, Date ngayDangKy, Date hanThe, int maDocGia, int phiDangKy) {
		super();
		this.maThe = maThe;
		this.ngayDangKy = ngayDangKy;
		this.hanThe = hanThe;
		this.maDocGia = maDocGia;
		this.phiDangKy = phiDangKy;
	}

	public int getMaThe() {
		return maThe;
	}

	public void setMaThe(int maThe) {
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

	public int getMaDocGia() {
		return maDocGia;
	}

	public void setMaDocGia(int maDocGia) {
		this.maDocGia = maDocGia;
	}

	public int getPhiDangKy() {
		return phiDangKy;
	}

	public void setPhiDangKy(int phiDangKy) {
		this.phiDangKy = phiDangKy;
	}
	
	
	
}
