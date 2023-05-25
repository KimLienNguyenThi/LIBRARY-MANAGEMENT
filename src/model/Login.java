package model;

public class Login {
	private int MaTaiKhoan;
	private int maNV;
	private String matKhau;
	
	
	public Login() {
	}
	
	public Login(int MaTaiKhoan,int maNV, String matKhau) {
		super();
		this.MaTaiKhoan = MaTaiKhoan;
		this.maNV = maNV;
		this.matKhau = matKhau;
	}

	
	public int getMaTaiKhoan() {
		return MaTaiKhoan;
	}

	public void setMaTaiKhoan(int MaTaiKhoan) {
		this.MaTaiKhoan = MaTaiKhoan;
	}
	public int getMaNV() {
		return maNV;
	}

	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}
	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	
	
	
}
