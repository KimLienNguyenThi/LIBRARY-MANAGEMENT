package test;

import javax.swing.UIManager;

import view.LoginView;
import view.MainView;

public class Test {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new LoginView().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

