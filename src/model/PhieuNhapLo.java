package model;

import java.util.Date;

public class PhieuNhapLo {
	private int MaPN;

	private Date ngayNhap;
	private int tongTienNhap;
	private int MaNCC;
	private int MaNV;
	public PhieuNhapLo() {
	}

	public PhieuNhapLo(int MaPN, Date ngayNhap, int tongTienNhap, int MaNCC, int MaNV) {
		super();
		this.MaPN = MaPN;
		
		this.ngayNhap = ngayNhap;
		
		this.tongTienNhap = tongTienNhap;
		this.MaNCC = MaNCC;
		this.MaNV=  MaNV;
	}

	public int getMaPN() {
		return MaPN;
	}

	public void setMaPN(int MaPN) {
		this.MaPN = MaPN;
	}

	
	public Date getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}


	public int getTongTienNhap() {
		return tongTienNhap;
	}

	public void setTongTienNhap(int tongTienNhap) {
		this.tongTienNhap = tongTienNhap;
	}	
	public int getMaNCC() {
		return MaNCC;
	}

	public void setMaNCC(int MaNCC) {
		this.MaNCC = MaNCC;
	}
	public int getMaNV() {
		return MaNV;
	}

	public void setMaNV(int MaNV) {
		this.MaNV = MaNV;
	}

}
