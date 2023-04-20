package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class MainView extends JFrame {
	JFrame frame = new JFrame();
	
	private JPanel contentPane;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JButton lastClicked;
	private JPanel panelTheDocGia;
	private JTable table_QuanLySach;
	private JTable table_QuanLyNhapLo;
	private JTable table_QuanLyPhieuMuon;
	private JTextField textField_TimKiem_qlnl;
	private JTextField textField_TimKiem_qls;
	private JTextField textField_TimKiem_qlpm;
	JLabel lblNewLabel_6 = new JLabel("");
	private JTextField textField_TimKiem_qldg;
	private JTable table_QuanLyDocGia;
	/**
	 * Launch the application.
	 */
	
	public MainView(String hotendn){
		
		setResizable(false);
		this.init();
		this.setLocationRelativeTo(null);
		this.setVisible(false);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setText(hotendn);
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
		
		ImageIcon iconTimKiem = new ImageIcon(MainView.class.getResource("/images/search.png"));
		Image imgTimKiem = iconTimKiem.getImage();
		Image newImgTimKiem = imgTimKiem.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconTimKiem = new ImageIcon(newImgTimKiem);
		
		JButton btn_Library_top = new JButton("");
		btn_Library_top.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btn_Library_top);
				cardLayout.show(cardPanel, "panelHome");
			}
		});
		btn_Library_top.setOpaque(true);
		btn_Library_top.setBorderPainted(false);
		btn_Library_top.setBackground(new Color(226, 255, 153));
		btn_Library_top.setBounds(10, 2, 58, 58);
		panel_Top.add(btn_Library_top);
		btn_Library_top.setIcon(newIconlibrary);
		
		JLabel lbl_Namelibrary_top = new JLabel("QUẢN LÝ THƯ VIỆN ABC");
		lbl_Namelibrary_top.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbl_Namelibrary_top.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Namelibrary_top.setBounds(105, 10, 336, 46);
		panel_Top.add(lbl_Namelibrary_top);
		
		ImageIcon iconUser = new ImageIcon(MainView.class.getResource("/images/exit.png"));
		Image imgUser = iconUser.getImage();
		Image newImgUser = imgUser.getScaledInstance(29,29, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconUser = new ImageIcon(newImgUser);
		
		JButton btn_Logout_top = new JButton("");
		btn_Logout_top.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginView().setVisible(true);
				dispose();
			}
		});
		btn_Logout_top.setBackground(new Color(0xE2FF99));
		btn_Logout_top.setOpaque(true);
		btn_Logout_top.setBorderPainted(false);
		btn_Logout_top.setBounds(1018, 2, 58, 58);
		panel_Top.add(btn_Logout_top);
		btn_Logout_top.setIcon(newIconUser);
		
		
		lblNewLabel_6.setBounds(531, 10, 95, 31);
		panel_Top.add(lblNewLabel_6);
		
		JPanel panel_Left = new JPanel();
		panel_Left.setBackground(new Color(0xE2FF99));
		panel_Left.setBounds(0, 65, 238, 548);
		contentPane.add(panel_Left);
		panel_Left.setLayout(null);
		
		JLabel lbl_ChucNang_left = new JLabel("Chức năng");
		lbl_ChucNang_left.setForeground(new Color(255, 255, 255));
		lbl_ChucNang_left.setBackground(new Color(0,0,0));
		lbl_ChucNang_left.setOpaque(true);
		lbl_ChucNang_left.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ChucNang_left.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_ChucNang_left.setBounds(0, 0, 238, 25);
		panel_Left.add(lbl_ChucNang_left);
		
		ImageIcon iconBill = new ImageIcon(MainView.class.getResource("/images/bill.png"));
		Image imgBill = iconBill.getImage();
		Image newImgBill = imgBill.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconBill = new ImageIcon(newImgBill);
		
		JButton btn_PhieuMuon_left = new JButton("PHIẾU MƯỢN");
		btn_PhieuMuon_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btn_PhieuMuon_left);
				cardLayout.show(cardPanel, "panelHoaDon");
			}
		});
		btn_PhieuMuon_left.setHorizontalAlignment(SwingConstants.LEFT);
		btn_PhieuMuon_left.setBackground(new Color(0xE2FF99));
		btn_PhieuMuon_left.setOpaque(true);
		btn_PhieuMuon_left.setBorderPainted(false);
		btn_PhieuMuon_left.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_PhieuMuon_left.setBounds(0, 35, 238, 75);
		panel_Left.add(btn_PhieuMuon_left);
		btn_PhieuMuon_left.setIcon(newIconBill);
		
		ImageIcon iconMnBooks = new ImageIcon(MainView.class.getResource("/images/mnbooks.png"));
		Image imgMnBooks = iconMnBooks.getImage();
		Image newImgMnBooks = imgMnBooks.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconMnBooks = new ImageIcon(newImgMnBooks);
		
		JButton btn_QLPhieuMuon_left = new JButton("QLý PHIẾU MƯỢN");
		btn_QLPhieuMuon_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btn_QLPhieuMuon_left);
				cardLayout.show(cardPanel, "panelQuanLySach");
			}
		});
		btn_QLPhieuMuon_left.setHorizontalAlignment(SwingConstants.LEADING);
		btn_QLPhieuMuon_left.setBackground(new Color(0xE2FF99));
		btn_QLPhieuMuon_left.setOpaque(true);
		btn_QLPhieuMuon_left.setBorderPainted(false);
		btn_QLPhieuMuon_left.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_QLPhieuMuon_left.setBounds(0, 120, 238, 75);
		panel_Left.add(btn_QLPhieuMuon_left);
		btn_QLPhieuMuon_left.setIcon(newIconMnBooks);
		
		ImageIcon iconExChange = new ImageIcon(MainView.class.getResource("/images/exchange.png"));
		Image imgExChange = iconExChange.getImage();
		Image newImgExChange = imgExChange.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconExChange = new ImageIcon(newImgExChange);
		
		JButton btn_QuanLySach_left = new JButton("QUẢN LÝ SÁCH");
		btn_QuanLySach_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btn_QuanLySach_left);
				cardLayout.show(cardPanel, "panelMuonSach");
			}
		});
		btn_QuanLySach_left.setHorizontalAlignment(SwingConstants.LEADING);
		btn_QuanLySach_left.setBackground(new Color(0xE2FF99));
		btn_QuanLySach_left.setOpaque(true);
		btn_QuanLySach_left.setBorderPainted(false);
		btn_QuanLySach_left.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_QuanLySach_left.setBounds(0, 205, 238, 75);
		panel_Left.add(btn_QuanLySach_left);
		btn_QuanLySach_left.setIcon(newIconExChange);
		
		ImageIcon iconTrend = new ImageIcon(MainView.class.getResource("/images/trend.png"));
		Image imgTrend = iconTrend.getImage();
		Image newImgTrend = imgTrend.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconTrend = new ImageIcon(newImgTrend);
		
		JButton btn_QuanLyNhapLo_left = new JButton("QLý NHẬP LÔ");
		btn_QuanLyNhapLo_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btn_QuanLyNhapLo_left);
				cardLayout.show(cardPanel, "panelThongKe");
			}
		});
		btn_QuanLyNhapLo_left.setHorizontalAlignment(SwingConstants.LEADING);
		btn_QuanLyNhapLo_left.setBackground(new Color(0xE2FF99));
		btn_QuanLyNhapLo_left.setOpaque(true);
		btn_QuanLyNhapLo_left.setBorderPainted(false);
		btn_QuanLyNhapLo_left.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_QuanLyNhapLo_left.setBounds(0, 290, 238, 75);
		panel_Left.add(btn_QuanLyNhapLo_left);
		btn_QuanLyNhapLo_left.setIcon(newIconTrend);
		
		ImageIcon iconMember = new ImageIcon(MainView.class.getResource("/images/member.png"));
		Image imgMember = iconMember.getImage();
		Image newImgMember = imgMember.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconMember = new ImageIcon(newImgMember);
		
		JButton btn_TheDocGia_left = new JButton(" THẺ ĐỘC GIẢ");
		btn_TheDocGia_left.setHorizontalAlignment(SwingConstants.LEADING);
		btn_TheDocGia_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btn_TheDocGia_left);
				cardLayout.show(cardPanel, "panelTheThanhVien");
			}
		});
		btn_TheDocGia_left.setOpaque(true);
		btn_TheDocGia_left.setBorderPainted(false);
		btn_TheDocGia_left.setBackground(new Color(0xE2FF99));
		btn_TheDocGia_left.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_TheDocGia_left.setBounds(0, 375, 238, 75);
		panel_Left.add(btn_TheDocGia_left);
		btn_TheDocGia_left.setIcon(newIconMember);
		
		ImageIcon iconReading = new ImageIcon(MainView.class.getResource("/images/reading.png"));
		Image imgReading = iconReading.getImage();
		Image newImgReading = imgReading.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconReading = new ImageIcon(newImgReading);
		
		JButton btn_QuanLyDocGia_left = new JButton("QLý ĐỘC GIẢ");
		btn_QuanLyDocGia_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btn_QuanLyDocGia_left);
				cardLayout.show(cardPanel, "panelQuanLyDocGia");
			}
		});
		btn_QuanLyDocGia_left.setOpaque(true);
		btn_QuanLyDocGia_left.setHorizontalAlignment(SwingConstants.LEADING);
		btn_QuanLyDocGia_left.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_QuanLyDocGia_left.setBorderPainted(false);
		btn_QuanLyDocGia_left.setBackground(new Color(226, 255, 153));
		btn_QuanLyDocGia_left.setBounds(0, 460, 238, 75);
		panel_Left.add(btn_QuanLyDocGia_left);
		btn_QuanLyDocGia_left.setIcon(newIconReading);
		
		cardPanel = new JPanel(cardLayout);
		cardPanel.setBounds(237, 65, 849, 548);
		contentPane.add(cardPanel);
		//cardPanel.setLayout(new CardLayout(0, 0));
		
		JPanel panelHome = new JPanel();
		panelHome.setBackground(new Color(255, 255, 255));
		cardPanel.add(panelHome, "panelHome");
		panelHome.setLayout(null);
		
		JLabel lbl_XinChao_home = new JLabel("XIN CHÀO!");
		lbl_XinChao_home.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_XinChao_home.setFont(new Font("Tahoma", Font.BOLD, 70));
		lbl_XinChao_home.setBounds(10, 10, 829, 172);
		panelHome.add(lbl_XinChao_home);
		
		JLabel lbl_image_home = new JLabel("New label");
		lbl_image_home.setIcon(new ImageIcon(MainView.class.getResource("/images/background.png")));
		lbl_image_home.setBounds(0, 0, 849, 548);
		panelHome.add(lbl_image_home);
	  	
	  	JPanel panelPhieuMuon = new JPanel();
	  	panelPhieuMuon.setBackground(new Color(255, 255, 255));
	  	cardPanel.add(panelPhieuMuon, "panelHoaDon");
	  	panelPhieuMuon.setLayout(null);
	  	


	  
	  	
	  	JPanel panelQLPhieuMuon = new JPanel();
	  	panelQLPhieuMuon.setBackground(new Color(255, 255, 255));
	  	cardPanel.add(panelQLPhieuMuon, "panelQuanLySach");
	  	panelQLPhieuMuon.setLayout(null);
	  	
	  	JScrollPane scrollPane_qlpm = new JScrollPane();
	  	scrollPane_qlpm.setBounds(10, 122, 829, 415);
	  	panelQLPhieuMuon.add(scrollPane_qlpm);
	  	
	  	table_QuanLyPhieuMuon = new JTable();
	  	table_QuanLyPhieuMuon.setFont(new Font("Tahoma", Font.PLAIN, 10));
	  	table_QuanLyPhieuMuon.setModel(new DefaultTableModel(
	  		new Object[][] {
	  			{null, null, null, null, null, null, null, null, null, null, null},
	  		},
	  		new String[] {
	  			"M\u00E3 PM", "M\u00E3 NV", "M\u00E3 DG", "H\u1ECD t\u00EAn", "S\u0110T", "\u0110\u1ECBa ch\u1EC9", "T\u00EAn s\u00E1ch", "S\u1ED1 l\u01B0\u1EE3ng", "Ng\u00E0y m\u01B0\u1EE3n", "Ng\u00E0y tr\u1EA3", "T\u00ECnh tr\u1EA1ng"
	  		}
	  	));
	  	table_QuanLyPhieuMuon.getColumnModel().getColumn(0).setPreferredWidth(86);
	  	table_QuanLyPhieuMuon.getColumnModel().getColumn(2).setPreferredWidth(86);
	  	table_QuanLyPhieuMuon.getColumnModel().getColumn(3).setPreferredWidth(64);
	  	table_QuanLyPhieuMuon.getColumnModel().getColumn(4).setPreferredWidth(78);
	  	table_QuanLyPhieuMuon.getColumnModel().getColumn(5).setPreferredWidth(89);
	  	table_QuanLyPhieuMuon.getColumnModel().getColumn(6).setPreferredWidth(52);
	  	table_QuanLyPhieuMuon.getColumnModel().getColumn(7).setPreferredWidth(69);
	  	table_QuanLyPhieuMuon.getColumnModel().getColumn(8).setPreferredWidth(67);
	  	scrollPane_qlpm.setViewportView(table_QuanLyPhieuMuon);
	  	
	  	JButton btn_TimKiem_qlpm = new JButton("");
	  	btn_TimKiem_qlpm.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {
	  		}
	  	});
	  	btn_TimKiem_qlpm.setOpaque(true);
	  	btn_TimKiem_qlpm.setBorderPainted(false);
	  	btn_TimKiem_qlpm.setBackground(new Color(226, 255, 153));
	  	btn_TimKiem_qlpm.setBounds(470, 11, 45, 45);
	  	panelQLPhieuMuon.add(btn_TimKiem_qlpm);
	  	btn_TimKiem_qlpm.setIcon(newIconTimKiem);
	  	
	  	textField_TimKiem_qlpm = new JTextField();
	  	textField_TimKiem_qlpm.setText("  Search");
	  	textField_TimKiem_qlpm.setForeground(Color.LIGHT_GRAY);
	  	textField_TimKiem_qlpm.setFont(new Font("Tahoma", Font.PLAIN, 20));
	  	textField_TimKiem_qlpm.setColumns(10);
	  	textField_TimKiem_qlpm.setBackground(new Color(226, 255, 153));
	  	textField_TimKiem_qlpm.setBounds(513, 11, 300, 45);
	  	panelQLPhieuMuon.add(textField_TimKiem_qlpm);
	  	///////////////////////////////////////////////////////////////////////////////////////////
	  	
//	  	JPanel panelQLPhieuMuon = new JPanel();
//	  	panelQLPhieuMuon.setBackground(new Color(255, 255, 255));
//	  	cardPanel.add(panelQLPhieuMuon, "panelQuanLySach");
//	  	panelQLPhieuMuon.setLayout(null);
	  	
	  	JPanel panelQuanLySach = new JPanel();
	  	panelQuanLySach.setBackground(new Color(255, 255, 255));
	  	cardPanel.add(panelQuanLySach, "panelMuonSach");
	  	panelQuanLySach.setLayout(null);
	  	
	  	JPanel panelQuanLyNhapLo = new JPanel();
	  	panelQuanLyNhapLo.setBackground(new Color(255, 255, 255));
	  	cardPanel.add(panelQuanLyNhapLo, "panelThongKe");
	  	panelQuanLyNhapLo.setLayout(null);
	  	
	  	JPanel panel_table_qlnl = new JPanel();
	  	panel_table_qlnl.setBounds(10, 123, 829, 415);
	  	panelQuanLyNhapLo.add(panel_table_qlnl);
	  	panel_table_qlnl.setLayout(null);
	  	
	  	table_QuanLyNhapLo = new JTable();
	  	table_QuanLyNhapLo.setModel(new DefaultTableModel(
	  		new Object[][] {
	  			{null, null, null, null, null, null},
	  		},
	  		new String[] {
	  			"M\u00E3 l\u00F4", "Ng\u00E0y nh\u1EADp", "Thanh to\u00E1n", "Nh\u00E0 cung c\u1EA5p", "\u0110\u1ECBa ch\u1EC9 cung c\u1EA5p", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i"
	  		}
	  	));
	  	table_QuanLyNhapLo.getColumnModel().getColumn(0).setPreferredWidth(37);
	  	table_QuanLyNhapLo.getColumnModel().getColumn(1).setPreferredWidth(63);
	  	table_QuanLyNhapLo.getColumnModel().getColumn(2).setPreferredWidth(74);
	  	table_QuanLyNhapLo.getColumnModel().getColumn(3).setPreferredWidth(89);
	  	table_QuanLyNhapLo.getColumnModel().getColumn(4).setPreferredWidth(101);
	  	
	  	JScrollPane scrollPane_qlnl = new JScrollPane(table_QuanLyNhapLo);
	  	scrollPane_qlnl.setBounds(0, 0, 829, 415);
	  	panel_table_qlnl.add(scrollPane_qlnl);
	  	
	  	JButton btn_TimKiem_qlnl = new JButton("");
	  	btn_TimKiem_qlnl.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {
	  		}
	  	});
	  	btn_TimKiem_qlnl.setOpaque(true);
	  	btn_TimKiem_qlnl.setBorderPainted(false);
	  	btn_TimKiem_qlnl.setBackground(new Color(226, 255, 153));
	  	btn_TimKiem_qlnl.setBounds(467, 10, 45, 45);
	  	panelQuanLyNhapLo.add(btn_TimKiem_qlnl);
	  	btn_TimKiem_qlnl.setIcon(newIconTimKiem);
	  	
	  	textField_TimKiem_qlnl = new JTextField();
	  	textField_TimKiem_qlnl.setText("  Search");
	  	textField_TimKiem_qlnl.setForeground(Color.LIGHT_GRAY);
	  	textField_TimKiem_qlnl.setFont(new Font("Tahoma", Font.PLAIN, 20));
	  	textField_TimKiem_qlnl.setColumns(10);
	  	textField_TimKiem_qlnl.setBackground(new Color(226, 255, 153));
	  	textField_TimKiem_qlnl.setBounds(510, 10, 300, 45);
	  	panelQuanLyNhapLo.add(textField_TimKiem_qlnl);
	  	
	  	panelTheDocGia = new JPanel();
	  	cardPanel.add(panelTheDocGia, "panelTheThanhVien");
	  	panelTheDocGia.setLayout(null);
	  	
	  	JPanel panel_table_qls = new JPanel();
	  	panel_table_qls.setBounds(10, 123, 829, 415);
	  	panelQuanLySach.add(panel_table_qls);
	  	
	  	table_QuanLySach = new JTable();
	  	table_QuanLySach.setModel(new DefaultTableModel(
	  		new Object[][] {
	  			{null, null, null, null, null, null, null, null, null},
	  		},
	  		new String[] {
	  			"M\u00E3 s\u00E1ch", "T\u00EAn s\u00E1ch", "T\u00E1c gi\u1EA3", "Nh\u00E0 XB", "N\u0103m XB", "Th\u1EC3 lo\u1EA1i", "Gi\u00E1 s\u00E1ch", "Ng\u00F4n ng\u1EEF", "T\u00ECnh tr\u1EA1ng"
	  		}
	  	));
	  	table_QuanLySach.getColumnModel().getColumn(0).setPreferredWidth(56);
	  	table_QuanLySach.getColumnModel().getColumn(1).setPreferredWidth(59);
	  	table_QuanLySach.getColumnModel().getColumn(8).setPreferredWidth(51);
	  	panel_table_qls.setLayout(null);
	  	
	  	JScrollPane scrollPane_qls = new JScrollPane(table_QuanLySach);
	  	scrollPane_qls.setBounds(0, 0, 829, 415);
	  	panel_table_qls.add(scrollPane_qls);
	  	
	  	textField_TimKiem_qls = new JTextField();
	  	textField_TimKiem_qls.setText("  Search");
	  	textField_TimKiem_qls.setForeground(Color.LIGHT_GRAY);
	  	textField_TimKiem_qls.setFont(new Font("Tahoma", Font.PLAIN, 20));
	  	textField_TimKiem_qls.setColumns(10);
	  	textField_TimKiem_qls.setBackground(new Color(226, 255, 153));
	  	textField_TimKiem_qls.setBounds(510, 10, 300, 45);
	  	panelQuanLySach.add(textField_TimKiem_qls);
	  	
	  	JButton btn_TimKiem_qls = new JButton("");
	  	btn_TimKiem_qls.setOpaque(true);
	  	btn_TimKiem_qls.setBorderPainted(false);
	  	btn_TimKiem_qls.setBackground(new Color(226, 255, 153));
	  	btn_TimKiem_qls.setBounds(467, 10, 45, 45);
	  	btn_TimKiem_qls.setIcon(newIconTimKiem);
	  	panelQuanLySach.add(btn_TimKiem_qls);
	  	
	  	JPanel panelQuanLyDocGia = new JPanel();
	  	cardPanel.add(panelQuanLyDocGia, "panelQuanLyDocGia");
	  	panelQuanLyDocGia.setLayout(null);
	  	
	  	textField_TimKiem_qldg = new JTextField();
	  	textField_TimKiem_qldg.setText("  Search");
	  	textField_TimKiem_qldg.setForeground(Color.LIGHT_GRAY);
	  	textField_TimKiem_qldg.setFont(new Font("Tahoma", Font.PLAIN, 20));
	  	textField_TimKiem_qldg.setColumns(10);
	  	textField_TimKiem_qldg.setBackground(new Color(226, 255, 153));
	  	textField_TimKiem_qldg.setBounds(510, 10, 300, 45);
	  	panelQuanLyDocGia.add(textField_TimKiem_qldg);
	  	
	  	JButton btn_TimKiem_qldg = new JButton("");
	  	btn_TimKiem_qldg.setOpaque(true);
	  	btn_TimKiem_qldg.setBorderPainted(false);
	  	btn_TimKiem_qldg.setBackground(new Color(226, 255, 153));
	  	btn_TimKiem_qldg.setBounds(467, 10, 45, 45);
	  	panelQuanLyDocGia.add(btn_TimKiem_qldg);
	  	
	  	JPanel panel_qldg = new JPanel();
	  	panel_qldg.setBounds(10, 122, 829, 415);
	  	panelQuanLyDocGia.add(panel_qldg);
	  	panel_qldg.setLayout(null);
	  	
	  	
	  	table_QuanLyDocGia = new JTable();
	  	table_QuanLyDocGia.setModel(new DefaultTableModel(
	  		new Object[][] {
	  			{null, null, null, null, null, null, null, null},
	  		},
	  		new String[] {
	  			"M\u00E3 DG", "H\u1ECD t\u00EAn", "S\u0110T", "Ng\u00E0y Sinh", "\u0110\u1ECBa ch\u1EC9", "Tgian \u0110\u0103ng k\u00FD", "H\u1EA1n th\u1EBB", "Ph\u00ED \u0111\u0103ng k\u00FD"
	  		}
	  	));
	  	
	  	JScrollPane scrollPane_qldg = new JScrollPane(table_QuanLyDocGia);
	  	scrollPane_qldg.setBounds(0, 0, 829, 416);
	  	panel_qldg.add(scrollPane_qldg);

	  	

	}
	
	private void changeButtonColor(JButton button) {
        if (lastClicked != null) {
        	lastClicked.setBackground(new Color(0xE2FF99));
        }
        button.setBackground(new Color(0X7A8F44));
        lastClicked = button;
    }
}








































