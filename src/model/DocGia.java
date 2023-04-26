package model;
import java.sql.Date;

public class DocGia {
	private int maDocGia;
	private String tenDocGia;
	private String SDT;
	private String diaChi;
	private Date ngaySinh;
	
	public DocGia() {
	}

	public DocGia(int maDocGia, String tenDocGia, String sDT, String diaChi, Date ngaySinh) {
		super();
		this.maDocGia = maDocGia;
		this.tenDocGia = tenDocGia;
		SDT = sDT;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
	}

	public int getMaDocGia() {
		return maDocGia;
	}

	public void setMaDocGia(int maDocGia) {
		this.maDocGia = maDocGia;
	}

	public String getTenDocGia() {
		return tenDocGia;
	}

	public void setTenDocGia(String tenDocGia) {
		this.tenDocGia = tenDocGia;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	
	
}
