package model;

public class ChiTietLo {
	private int maDS;
	//private String tenSach;
	//private String theLoai;
	//private String NXB;
	//private String tacGia;
	private int soLuong;
	private int maLo;
	//private String maSach;
	
	
	public ChiTietLo() {
	}

	public ChiTietLo(int maDS, int soLuong, int maLo) {
		super();
		this.maDS = maDS;
		//this.tenSach = tenSach;
		//this.theLoai = theLoai;
		//this.NXB = nXB;
		//this.tacGia = tacGia;
		this.soLuong = soLuong;
		this.maLo = maLo;
		//this.maSach = maSach;
	}
	public ChiTietLo( int soLuong, int maLo) {
		super();
		this.soLuong = soLuong;
		this.maLo = maLo;
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

	public int getMaLo() {
		return maLo;
	}

	public void setMaLo(int maLo) {
		this.maLo = maLo;
	}

//	public String getMaSach() {
//		return maSach;
//	}
//
//	public void setMaSach(String maSach) {
//		this.maSach = maSach;
//	}
	
	


	
}
