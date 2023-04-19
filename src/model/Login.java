package model;

public class Login {
	private String maNV;
	private String matKhau;
	
	
	public Login() {
	}
	
	public Login(String maNV, String matKhau) {
		super();
		this.maNV = maNV;
		this.matKhau = matKhau;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	
	
	
}
