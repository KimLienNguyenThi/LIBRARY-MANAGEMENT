package view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dialog_ThemSach_QLS extends JDialog {
	JFrame frame = new JFrame();
	
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	
	public Dialog_ThemSach_QLS(JFrame parent){
		super(parent, "Dialog Default", true);
		this.init();        
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}

	/**
	 * Create the dialog.
	 */
	
	public void init() {
		setBounds(100, 100, 464, 319);
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}

































