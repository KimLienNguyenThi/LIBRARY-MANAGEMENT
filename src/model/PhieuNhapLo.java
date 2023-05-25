package model;

import java.util.Date;

public class PhieuNhapLo {
	private int MaPN;

	private Date ngayNhap;
	private int tongTienNhap;
	
	
	public PhieuNhapLo() {
	}

	public PhieuNhapLo(int MaPN, Date ngayNhap, int tongTienNhap) {
		super();
		this.MaPN = MaPN;
		
		this.ngayNhap = ngayNhap;
		
		this.tongTienNhap = tongTienNhap;
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

}
