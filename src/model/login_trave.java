package model;

public class login_trave {
	private String Hoten;
	private String ChucVu;

	public login_trave() {
	}

	public login_trave(String Hoten, String ChucVu) {
		super();
		this.Hoten = Hoten;
		this.ChucVu = ChucVu;
	}

	public String getHoten() {
		return Hoten;
	}

	public void setHoten(String Hoten) {
		this.Hoten = Hoten;
	}

	public String getChucVu() {
		return ChucVu;
	}

	public void setChucVu(String ChucVu) {
		this.ChucVu = ChucVu;
	}
}