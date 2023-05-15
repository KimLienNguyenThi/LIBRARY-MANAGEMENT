package model;

public class Sach {
	private String maSach;
	private String tenSach;
	private String theLoai;
	private int namXuatBan;
	private String NXB;
	private String tacGia;
	private int soLuong;
	private String ngonNgu;
	private String tinhTrang;
	private int giaSach;
	private int MaDS;
	
	public Sach() {
	}
	
	public Sach(String maSach, String tenSach, String theLoai, int namXuatBan, String nXB, String tacGia,
			int soLuong, String ngonNgu, String tinhTrang, int giaSach) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.theLoai = theLoai;
		this.namXuatBan = namXuatBan;
		this.NXB = nXB;
		this.tacGia = tacGia;
		this.soLuong = soLuong;
		this.ngonNgu = ngonNgu;
		this.tinhTrang = tinhTrang;
		this.giaSach = giaSach;
	}

	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public String getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}

	public int getNamXuatBan() {
		return namXuatBan;
	}

	public void setNamXuatBan(int namXuatBan) {
		this.namXuatBan = namXuatBan;
	}

	public String getNXB() {
		return NXB;
	}

	public void setNXB(String nXB) {
		NXB = nXB;
	}

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getNgonNgu() {
		return ngonNgu;
	}

	public void setNgonNgu(String ngonNgu) {
		this.ngonNgu = ngonNgu;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public int getGiaSach() {
		return giaSach;
	}

	public void setGiaSach(int giaSach) {
		this.giaSach = giaSach;
	}

	public int getMaDS() {
		return MaDS;
	}

	public void setMaDS(int maDauSach) {
		this.MaDS = maDauSach;
	}

	
}
