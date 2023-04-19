package model;
import java.sql.Date;

public class NhanVien {
	private String maNV;
	private String hoTenNV;
	private String diaChi;
	private String SDT;
	private String gioiTinh;
	private Date ngaySinh;
	
	
	public NhanVien() {
	}

	public NhanVien(String maNV, String hoTenNV, String diaChi, String sDT, String gioiTinh, Date ngaySinh) {
		super();
		this.maNV = maNV;
		this.hoTenNV = hoTenNV;
		this.diaChi = diaChi;
		SDT = sDT;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoTenNV() {
		return hoTenNV;
	}

	public void setHoTenNV(String hoTenNV) {
		this.hoTenNV = hoTenNV;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	
	
}
