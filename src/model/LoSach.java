package model;
import java.sql.Date;

public class LoSach {
	private String maLo;
	private String tenNhaCungCap;
	private Date ngayNhap;
	private String sdtNhaCungCap;
	private String diaChiNhaCungCap;
	private int tongTienNhap;
	
	
	public LoSach() {
	}

	public LoSach(String maLo, String tenNhaCungCap, Date ngayNhap, String sdtNhaCungCap, String diaChiNhaCungCap,
			int tongTienNhap) {
		super();
		this.maLo = maLo;
		this.tenNhaCungCap = tenNhaCungCap;
		this.ngayNhap = ngayNhap;
		this.sdtNhaCungCap = sdtNhaCungCap;
		this.diaChiNhaCungCap = diaChiNhaCungCap;
		this.tongTienNhap = tongTienNhap;
	}

	public String getMaLo() {
		return maLo;
	}

	public void setMaLo(String maLo) {
		this.maLo = maLo;
	}

	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}

	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}

	public Date getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public String getSdtNhaCungCap() {
		return sdtNhaCungCap;
	}

	public void setSdtNhaCungCap(String sdtNhaCungCap) {
		this.sdtNhaCungCap = sdtNhaCungCap;
	}

	public String getDiaChiNhaCungCap() {
		return diaChiNhaCungCap;
	}

	public void setDiaChiNhaCungCap(String diaChiNhaCungCap) {
		this.diaChiNhaCungCap = diaChiNhaCungCap;
	}

	public int getTongTienNhap() {
		return tongTienNhap;
	}

	public void setTongTienNhap(int tongTienNhap) {
		this.tongTienNhap = tongTienNhap;
	}	

}
