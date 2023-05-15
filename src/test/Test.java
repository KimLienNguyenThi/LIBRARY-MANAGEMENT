package test;

import javax.swing.UIManager;

import view.Dialog_ThemThanhVien;
import view.LoginView;
import view.MainView;
import view.Quan_Ly_Nhap_Lo_View;

public class Test {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Quan_Ly_Nhap_Lo_View().main(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

