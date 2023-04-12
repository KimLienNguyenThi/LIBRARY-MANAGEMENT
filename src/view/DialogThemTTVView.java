package view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogThemTTVView extends JDialog {
	JFrame frame = new JFrame();
	
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	
	public DialogThemTTVView(JFrame parent){
		super(parent, "Dialog Default", true);
		this.init();        
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}

	/**
	 * Create the dialog.
	 */
	
	public void init() {
		setBounds(100, 100, 670, 450);
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 56, 636, 347);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblHTn = new JLabel("Họ tên");
		lblHTn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHTn.setBounds(60, 10, 109, 30);
		panel.add(lblHTn);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(179, 10, 395, 30);
		panel.add(textField_1);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSinThoi.setBounds(60, 50, 109, 30);
		panel.add(lblSinThoi);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(179, 50, 395, 30);
		panel.add(textField_2);
		
		JLabel lblThiGian = new JLabel("Thời Gian");
		lblThiGian.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblThiGian.setBounds(60, 133, 109, 30);
		panel.add(lblThiGian);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(179, 133, 395, 30);
		panel.add(textField_3);
		
		JLabel lblHanThe = new JLabel("Hạn thẻ");
		lblHanThe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHanThe.setBounds(60, 173, 109, 30);
		panel.add(lblHanThe);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(179, 173, 395, 30);
		panel.add(textField_4);
		
		JLabel lbldk = new JLabel("Phí đăng ký:");
		lbldk.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbldk.setBounds(179, 213, 94, 30);
		panel.add(lbldk);
		
		JLabel lblPhiDangKy = new JLabel("#########đ");
		lblPhiDangKy.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPhiDangKy.setBounds(283, 213, 291, 30);
		panel.add(lblPhiDangKy);
		
		JLabel lblaCh = new JLabel("Địa chỉ");
		lblaCh.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblaCh.setBounds(60, 93, 109, 30);
		panel.add(lblaCh);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(179, 93, 395, 30);
		panel.add(textField);
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(376, 279, 200, 40);
		panel.add(btnNewButton);
		
		JButton btnHyThaoTc = new JButton("Hủy thao tác");
		btnHyThaoTc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnHyThaoTc.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnHyThaoTc.setBounds(60, 277, 200, 40);
		panel.add(btnHyThaoTc);
		
		JLabel lblNewLabel = new JLabel("Thông tin thành viên mới");
		lblNewLabel.setBounds(10, 10, 587, 36);
		contentPane.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
	}
}

































