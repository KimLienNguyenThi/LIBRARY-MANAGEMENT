package model;

public class ChiTietPhieuMuon {
	private int maPM;
	private int maSach;
	
	public ChiTietPhieuMuon() {
	}

	public ChiTietPhieuMuon(int maPM, int maSach) {
		super();
		this.maPM = maPM;
		this.maSach = maSach;
	}

	public int getMaPM() {
		return maPM;
	}

	public void setMaPM(int maPM) {
		this.maPM = maPM;
	}

	public int getMaSach() {
		return maSach;
	}

	public void setMaSach(int maSach) {
		this.maSach = maSach;
	}
}
