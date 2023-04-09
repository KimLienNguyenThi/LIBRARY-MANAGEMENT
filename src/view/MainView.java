package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.AlphaComposite;
import java.awt.CardLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainView extends JFrame {
	
	private JPanel contentPane;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JButton lastClicked;
	private JPanel panelTheThanhVien;
	private JTable tableMuonSach;

	/**
	 * Launch the application.
	 */
	
	public MainView(){
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
		setBounds(100, 100, 1100, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cardLayout = new CardLayout();
		
		JPanel panel_Top = new JPanel();
		panel_Top.setBorder(new MatteBorder(1, 1, 5, 1, (Color) new Color(0, 0, 0)));
		panel_Top.setBackground(new Color(0xE2FF99));
		panel_Top.setBounds(0, 0, 1086, 66);
		contentPane.add(panel_Top);
		panel_Top.setLayout(null);
		
		ImageIcon iconlibrary = new ImageIcon(MainView.class.getResource("/images/books.png"));
		Image imglibrary = iconlibrary.getImage();
		Image newImglibrary = imglibrary.getScaledInstance(58, 58, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconlibrary = new ImageIcon(newImglibrary);
		
		JButton btnLibrary = new JButton("");
		btnLibrary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btnLibrary);
				cardLayout.show(cardPanel, "panelHome");
			}
		});
		btnLibrary.setOpaque(true);
		btnLibrary.setBorderPainted(false);
		btnLibrary.setBackground(new Color(226, 255, 153));
		btnLibrary.setBounds(10, 2, 58, 58);
		panel_Top.add(btnLibrary);
		btnLibrary.setIcon(newIconlibrary);
		
		JLabel lblNewLabel = new JLabel("QUẢN LÝ THƯ VIỆN ABC");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(105, 10, 336, 46);
		panel_Top.add(lblNewLabel);
		
		ImageIcon iconUser = new ImageIcon(MainView.class.getResource("/images/exit.png"));
		Image imgUser = iconUser.getImage();
		Image newImgUser = imgUser.getScaledInstance(29,29, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconUser = new ImageIcon(newImgUser);
		
		JButton btnUser = new JButton("");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginView().setVisible(true);
				dispose();
			}
		});
		btnUser.setBackground(new Color(0xE2FF99));
		btnUser.setOpaque(true);
		btnUser.setBorderPainted(false);
		btnUser.setBounds(1018, 2, 58, 58);
		panel_Top.add(btnUser);
		btnUser.setIcon(newIconUser);
		
		JPanel panel_Left = new JPanel();
		panel_Left.setBackground(new Color(0xE2FF99));
		panel_Left.setBounds(0, 65, 238, 548);
		contentPane.add(panel_Left);
		panel_Left.setLayout(null);
		
		JLabel lblChucNang = new JLabel("Chức năng");
		lblChucNang.setForeground(new Color(255, 255, 255));
		lblChucNang.setBackground(new Color(0,0,0));
		lblChucNang.setOpaque(true);
		lblChucNang.setHorizontalAlignment(SwingConstants.CENTER);
		lblChucNang.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChucNang.setBounds(0, 0, 238, 25);
		panel_Left.add(lblChucNang);
		
		ImageIcon iconBill = new ImageIcon(MainView.class.getResource("/images/bill.png"));
		Image imgBill = iconBill.getImage();
		Image newImgBill = imgBill.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconBill = new ImageIcon(newImgBill);
		
		JButton btnHoaDon = new JButton("      HÓA ĐƠN");
		btnHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btnHoaDon);
				cardLayout.show(cardPanel, "panelHoaDon");
			}
		});
		btnHoaDon.setHorizontalAlignment(SwingConstants.LEFT);
		btnHoaDon.setBackground(new Color(0xE2FF99));
		btnHoaDon.setOpaque(true);
		btnHoaDon.setBorderPainted(false);
		btnHoaDon.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnHoaDon.setBounds(0, 51, 238, 75);
		panel_Left.add(btnHoaDon);
		btnHoaDon.setIcon(newIconBill);
		
		ImageIcon iconMnBooks = new ImageIcon(MainView.class.getResource("/images/mnbooks.png"));
		Image imgMnBooks = iconMnBooks.getImage();
		Image newImgMnBooks = imgMnBooks.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconMnBooks = new ImageIcon(newImgMnBooks);
		
		JButton btnQuanLySach = new JButton("  QUẢN LÝ SÁCH");
		btnQuanLySach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btnQuanLySach);
				cardLayout.show(cardPanel, "panelQuanLySach");
			}
		});
		btnQuanLySach.setHorizontalAlignment(SwingConstants.LEADING);
		btnQuanLySach.setBackground(new Color(0xE2FF99));
		btnQuanLySach.setOpaque(true);
		btnQuanLySach.setBorderPainted(false);
		btnQuanLySach.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnQuanLySach.setBounds(0, 136, 238, 75);
		panel_Left.add(btnQuanLySach);
		btnQuanLySach.setIcon(newIconMnBooks);
		
		ImageIcon iconExChange = new ImageIcon(MainView.class.getResource("/images/exchange.png"));
		Image imgExChange = iconExChange.getImage();
		Image newImgExChange = imgExChange.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconExChange = new ImageIcon(newImgExChange);
		
		JButton btnMuonSach = new JButton("    MƯỢN SÁCH");
		btnMuonSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btnMuonSach);
				cardLayout.show(cardPanel, "panelMuonSach");
			}
		});
		btnMuonSach.setHorizontalAlignment(SwingConstants.LEADING);
		btnMuonSach.setBackground(new Color(0xE2FF99));
		btnMuonSach.setOpaque(true);
		btnMuonSach.setBorderPainted(false);
		btnMuonSach.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnMuonSach.setBounds(0, 221, 238, 75);
		panel_Left.add(btnMuonSach);
		btnMuonSach.setIcon(newIconExChange);
		
		ImageIcon iconTrend = new ImageIcon(MainView.class.getResource("/images/trend.png"));
		Image imgTrend = iconTrend.getImage();
		Image newImgTrend = imgTrend.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconTrend = new ImageIcon(newImgTrend);
		
		JButton btnThongKe = new JButton("     THỐNG KÊ");
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btnThongKe);
				cardLayout.show(cardPanel, "panelThongKe");
			}
		});
		btnThongKe.setHorizontalAlignment(SwingConstants.LEADING);
		btnThongKe.setBackground(new Color(0xE2FF99));
		btnThongKe.setOpaque(true);
		btnThongKe.setBorderPainted(false);
		btnThongKe.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnThongKe.setBounds(0, 306, 238, 75);
		panel_Left.add(btnThongKe);
		btnThongKe.setIcon(newIconTrend);
		
		ImageIcon iconMember = new ImageIcon(MainView.class.getResource("/images/member.png"));
		Image imgMember = iconMember.getImage();
		Image newImgMember = imgMember.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconMember = new ImageIcon(newImgMember);
		
		JButton btnTheThanhVien = new JButton(" THẺ THÀNH VIÊN");
		btnTheThanhVien.setHorizontalAlignment(SwingConstants.LEADING);
		btnTheThanhVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btnTheThanhVien);
				cardLayout.show(cardPanel, "panelTheThanhVien");
			}
		});
		btnTheThanhVien.setOpaque(true);
		btnTheThanhVien.setBorderPainted(false);
		btnTheThanhVien.setBackground(new Color(0xE2FF99));
		btnTheThanhVien.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTheThanhVien.setBounds(0, 391, 238, 75);
		panel_Left.add(btnTheThanhVien);
		btnTheThanhVien.setIcon(newIconMember);
		
		cardPanel = new JPanel(cardLayout);
		cardPanel.setBounds(237, 65, 849, 548);
		contentPane.add(cardPanel);
		//cardPanel.setLayout(new CardLayout(0, 0));
		
		JPanel panelHome = new JPanel();
		panelHome.setBackground(new Color(255, 255, 255));
		cardPanel.add(panelHome, "panelHome");
		panelHome.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("XIN CHÀO!");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 70));
		lblNewLabel_3.setBounds(10, 10, 829, 172);
		panelHome.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(MainView.class.getResource("/images/background.png")));
		lblNewLabel_4.setBounds(0, 0, 849, 548);
		panelHome.add(lblNewLabel_4);
	  	
	  	JPanel panelHoaDon = new JPanel();
	  	panelHoaDon.setBackground(new Color(255, 255, 255));
	  	cardPanel.add(panelHoaDon, "panelHoaDon");
	  	
	  	JPanel panelQuanLySach = new JPanel();
	  	panelQuanLySach.setBackground(new Color(255, 255, 255));
	  	cardPanel.add(panelQuanLySach, "panelQuanLySach");
	  	
	  	JPanel panelMuonSach = new JPanel();
	  	panelMuonSach.setBackground(new Color(255, 255, 255));
	  	cardPanel.add(panelMuonSach, "panelMuonSach");
	  	panelMuonSach.setLayout(null);
	  	
	  	tableMuonSach = new JTable();
	  	tableMuonSach.setModel(new DefaultTableModel(
	  		new Object[][] {
	  			{null, null, null, null, null, null, null, null, null, null},
	  		},
	  		new String[] {
	  			"M\u00E3 HD", "M\u00E3 KH", "H\u1ECD t\u00EAn", "S\u0110T", "\u0110\u1ECBa \u0111i\u1EC3m", "S\u00E1ch m\u01B0\u1EE3n", "Ng\u00E0y m\u01B0\u1EE3n", "Ng\u00E0y tr\u1EA3", "S\u1ED1 l\u01B0\u1EE3ng", "Ph\u00ED m\u01B0\u1EE3n"
	  		}
	  	));
	  	tableMuonSach.getColumnModel().getColumn(0).setPreferredWidth(40);
	  	tableMuonSach.getColumnModel().getColumn(1).setPreferredWidth(40);
	  	tableMuonSach.getColumnModel().getColumn(8).setPreferredWidth(51);
	  	
	  	JScrollPane scrollPane = new JScrollPane(tableMuonSach);
	  	scrollPane.setBounds(10, 178, 829, 360);
	  	panelMuonSach.add(scrollPane);
	  	
	  	JLabel lblNewLabel_2 = new JLabel("New label");
	  	lblNewLabel_2.setIcon(new ImageIcon(MainView.class.getResource("/images/background.png")));
	  	lblNewLabel_2.setBounds(0, 0, 849, 548);
	  	panelMuonSach.add(lblNewLabel_2);
	  	
	  	JPanel panelThongKe = new JPanel();
	  	panelThongKe.setBackground(new Color(255, 255, 255));
	  	cardPanel.add(panelThongKe, "panelThongKe");
	  	panelThongKe.setLayout(null);
	  	
	  	panelTheThanhVien = new JPanel();
	  	cardPanel.add(panelTheThanhVien, "panelTheThanhVien");
	  	//panelTaiKhoan.setBackground(new Color(51, 51, 255));
	  	panelTheThanhVien.setLayout(null);
	  	
	  	JLabel lblNewLabel_1 = new JLabel("New label");
	  	lblNewLabel_1.setIcon(new ImageIcon(MainView.class.getResource("/images/background.png")));
	  	lblNewLabel_1.setBounds(0, 0, 777, 545);
	  	panelTheThanhVien.add(lblNewLabel_1);
	  	
	}
	
	private void changeButtonColor(JButton button) {
        if (lastClicked != null) {
        	lastClicked.setBackground(new Color(0xE2FF99));
        }
        button.setBackground(new Color(0X7A8F44));
        lastClicked = button;
    }
}








































