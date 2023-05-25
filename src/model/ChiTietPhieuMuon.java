package model;

public class ChiTietPhieuMuon {
	private int maPM;
	private int IDSACH;
	
	public ChiTietPhieuMuon() {
	}

	public ChiTietPhieuMuon(int maPM, int IDSACH) {
		super();
		this.maPM = maPM;
		this.IDSACH = IDSACH;
	}

	public int getMaPM() {
		return maPM;
	}

	public void setMaPM(int maPM) {
		this.maPM = maPM;
	}

	public int getIDSACH() {
		return IDSACH;
	}

	public void setIDSACH(int IDSACH) {
		this.IDSACH = IDSACH;
	}
}
