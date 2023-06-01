package model;

public class login_trave {
	private String Hoten;
	private String ChucVu;
	private int MaNV;

	public login_trave() {
	}

	public login_trave(String Hoten, String ChucVu, int MaNV) {
		super();
		this.Hoten = Hoten;
		this.ChucVu = ChucVu;
		this.MaNV = MaNV;
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
	public int getMaNV() {
		return MaNV;
	}

	public void setMaNV(int MaNV) {
		this.MaNV = MaNV;
	}

}