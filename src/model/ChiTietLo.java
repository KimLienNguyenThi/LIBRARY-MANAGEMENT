package model;

public class ChiTietLo {
	private int maDS;
	private int soLuong;
	private int MaPN;

	
	
	public ChiTietLo() {
	}

	public ChiTietLo(int maDS, int soLuong, int MaPN) {
		super();
		this.maDS = maDS;
		//this.tenSach = tenSach;
		//this.theLoai = theLoai;
		//this.NXB = nXB;
		//this.tacGia = tacGia;
		this.soLuong = soLuong;
		this.MaPN = MaPN;
		//this.maSach = maSach;
	}
	public ChiTietLo( int soLuong, int MaPN) {
		super();
		this.soLuong = soLuong;
		this.MaPN = MaPN;
	}

	public int getMaDS() {
		return maDS;
	}

	public void setMaDS(int maDS) {
		this.maDS = maDS;
	}

//	public String getTenSach() {
//		return tenSach;
//	}
//
//	public void setTenSach(String tenSach) {
//		this.tenSach = tenSach;
//	}
//
//	public String getTheLoai() {
//		return theLoai;
//	}
//
//	public void setTheLoai(String theLoai) {
//		this.theLoai = theLoai;
//	}
//
//	public String getNXB() {
//		return NXB;
//	}
//
//	public void setNXB(String nXB) {
//		NXB = nXB;
//	}
//
//	public String getTacGia() {
//		return tacGia;
//	}
//
//	public void setTacGia(String tacGia) {
//		this.tacGia = tacGia;
//	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getMaPN() {
		return MaPN;
	}

	public void setMaPN(int MaPN) {
		this.MaPN = MaPN;
	}

//	public String getMaSach() {
//		return maSach;
//	}
//
//	public void setMaSach(String maSach) {
//		this.maSach = maSach;
//	}
	
	


	
}
