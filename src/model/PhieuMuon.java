package model;
import java.sql.Date;

public class PhieuMuon {
	private String maPM;
	private String maNV;
	private String maDocGia;
	private String maSach;
	private Date ngayMuon;
	private Date ngayTra;
	
	
	public PhieuMuon() {
	}

	public PhieuMuon(String maPM, String maNV, String maDocGia, String maSach, Date ngayMuon, Date ngayTra) {
		super();
		this.maPM = maPM;
		this.maNV = maNV;
		this.maDocGia = maDocGia;
		this.maSach = maSach;
		this.ngayMuon = ngayMuon;
		this.ngayTra = ngayTra;
	}

	public String getMaPM() {
		return maPM;
	}

	public void setMaPM(String maPM) {
		this.maPM = maPM;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMaDocGia() {
		return maDocGia;
	}

	public void setMaDocGia(String maDocGia) {
		this.maDocGia = maDocGia;
	}

	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public Date getNgayMuon() {
		return ngayMuon;
	}

	public void setNgayMuon(Date ngayMuon) {
		this.ngayMuon = ngayMuon;
	}

	public Date getNgayTra() {
		return ngayTra;
	}

	public void setNgayTra(Date ngayTra) {
		this.ngayTra = ngayTra;
	}
	
	
}
