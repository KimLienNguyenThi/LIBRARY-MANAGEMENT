package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.LoginService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	
	public LoginView() {
		setResizable(false);
		this.init();
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	
	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Quản Lý Thư Viện");
		lblNewLabel_1.setForeground(new Color(0, 0, 153));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, -7, 576, 57);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ABC");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 153));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(10, 26, 576, 57);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel = new JLabel("Tài khoản");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(149, 93, 114, 22);
		contentPane.add(lblNewLabel);
		
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(149, 120, 297, 46);
		contentPane.add(textField);
		
		textField_1 = new JPasswordField();
		textField_1.setColumns(10);
		textField_1.setBounds(149, 203, 297, 46);
		contentPane.add(textField_1);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu");
		lblMtKhu.setForeground(Color.BLUE);
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMtKhu.setBounds(149, 176, 114, 22);
		contentPane.add(lblMtKhu);
		
		JButton btnNewButton = new JButton("Đăng nhập");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String matkhau = textField_1.getText();
				String taikhoan = textField.getText();	
				String login = LoginService.Login(taikhoan, matkhau);
				if(login != "") {
					 JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				        JOptionPane.showMessageDialog(frame,
				                "Đăng nhập thành công",
				                "THÔNG BÁO",
				                JOptionPane.INFORMATION_MESSAGE);
				        System.out.println("connect failure!");
					new MainView(login).setVisible(true);
					dispose();
				}
				else {
					 JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				        JOptionPane.showMessageDialog(frame,
				                "Tài khoản hoặc Mật khẩu sai",
				                "THÔNG BÁO",
				                JOptionPane.ERROR_MESSAGE);
				}
	
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 153));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBackground(new Color(0, 0, 153));
		btnNewButton.setBounds(149, 277, 297, 46);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(LoginView.class.getResource("/images/background.png")));
		lblNewLabel_2.setBounds(10, -7, 586, 374);
		contentPane.add(lblNewLabel_2);
	}
}
