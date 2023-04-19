package model;
import java.sql.Date;

public class DocGia {
	private String maDocGia;
	private String tenDocGia;
	private String SDT;
	private String diaChi;
	private Date ngaySinh;
	
	public DocGia() {
	}

	public DocGia(String maDocGia, String tenDocGia, String sDT, String diaChi, Date ngaySinh) {
		super();
		this.maDocGia = maDocGia;
		this.tenDocGia = tenDocGia;
		SDT = sDT;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
	}

	public String getMaDocGia() {
		return maDocGia;
	}

	public void setMaDocGia(String maDocGia) {
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
