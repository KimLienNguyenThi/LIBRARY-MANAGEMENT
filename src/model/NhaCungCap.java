package model;

public class NhaCungCap {
	
		private int MaNCC;
		private String tenNhaCungCap;
		private String sdtNhaCungCap;
		private String diaChiNhaCungCap;
		
		
		
		public NhaCungCap() {
		}

		public NhaCungCap(int MaNCC, String tenNhaCungCap, String sdtNhaCungCap, String diaChiNhaCungCap) {
			super();
			this.MaNCC = MaNCC;
			this.tenNhaCungCap = tenNhaCungCap;
			this.sdtNhaCungCap = sdtNhaCungCap;
			this.diaChiNhaCungCap = diaChiNhaCungCap;
			
		}

		public int getMaNCC() {
			return MaNCC;
		}

		public void setMaNCC(int MaPN) {
			this.MaNCC = MaNCC;
		}

		public String getTenNhaCungCap() {
			return tenNhaCungCap;
		}

		public void setTenNhaCungCap(String tenNhaCungCap) {
			this.tenNhaCungCap = tenNhaCungCap;
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

		

	}


