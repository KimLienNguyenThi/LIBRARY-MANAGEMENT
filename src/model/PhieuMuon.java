package model;
import java.sql.Date;

import javax.swing.JTable;
import javax.swing.JTextArea;

public class PhieuMuon {
	private int maPM;
	private int maThe;
	private Date ngayMuon;
	private Date ngayTra;
	private String tenNv;
	private String hoTen;
	private String sdt;
	private String diaChi;
	private JTable tableSachMuon;
	public static JTextArea textAreaModel = new JTextArea();
	
	public PhieuMuon() {
	}

	public PhieuMuon(JTable tableSachMuon) {
		super();
		this.tableSachMuon = tableSachMuon;
		//this.maPM = maPM;
		//this.maThe = maThe;
		//this.ngayTra = ngayTra;
	}

	public int getMaPM() {
		return maPM;
	}

	public void setMaPM(int maPM) {
		this.maPM = maPM;
	}

	public int getMaThe() {
		return maThe;
	}

	public void setMaThe(int maThe) {
		this.maThe = maThe;
	}

	public Date getNgayMuon() {
		return ngayMuon;
	}

	public void setNgayMuon(Date ngayMuon) {
		this.ngayMuon = ngayMuon;
	}

	public Date getNgayTra() {
		return ngayTra;
	}

	public void setNgayTra(Date ngayTra) {
		this.ngayTra = ngayTra;
	}

	public String getTenNv() {
		return tenNv;
	}

	public void setTenNv(String tenNv) {
		this.tenNv = tenNv;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public JTable getTableSachMuon() {
		return tableSachMuon;
	}

	public void setTableSachMuon(JTable tableSachMuon) {
		this.tableSachMuon = tableSachMuon;
	}
}
