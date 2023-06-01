package model;
import java.sql.Date;

public class PhieuMuon {
	private int maPM;
	private int maNV;
	private int maDocGia;
	private int IDSACH;
	private Date ngayMuon;
	private Date ngayTra;
	
	
	public PhieuMuon() {
	}

	public PhieuMuon(int maPM, int maDocGia, int IDSACH, Date ngayMuon, Date ngayTra, int maNV) {
		super();
		this.maPM = maPM;
		this.maNV = maNV;
		this.maDocGia = maDocGia;
		this.IDSACH = IDSACH;
		this.ngayMuon = ngayMuon;
		this.ngayTra = ngayTra;
	}

	public int getMaPM() {
		return maPM;
	}

	public void setMaPM(int maPM) {
		this.maPM = maPM;
	}

	public int getMaNV() {
		return maNV;
	}

	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}

	public int getMaDocGia() {
		return maDocGia;
	}

	public void setMaDocGia(int maDocGia) {
		this.maDocGia = maDocGia;
	}

	public int getIDSACH() {
		return IDSACH;
	}

	public void setIDSACH(int IDSACH) {
		this.IDSACH = IDSACH;
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
