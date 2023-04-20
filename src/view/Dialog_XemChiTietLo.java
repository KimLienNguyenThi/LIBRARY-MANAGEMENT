package view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dialog_XemChiTietLo extends JDialog {
	JFrame frame = new JFrame();
	
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	
	public Dialog_XemChiTietLo(JFrame parent){
		super(parent, "Dialog Default", true);
		this.init();        
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}

	/**
	 * Create the dialog.
	 */
	
	public void init() {
		setBounds(100, 100, 800, 480);
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}

































