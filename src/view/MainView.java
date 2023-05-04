package view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import java.awt.CardLayout;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Component;

import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import database.QuanLySach;
import database.ThanhVien;
import model.DocGia;
import model.TheThanhVien;

import javax.swing.JTextField;
import javax.swing.RowFilter;

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
	private JPanel panelThemThanhVien;
	private JTextField textField_TimKiem_QLTV;
	private JTextField textField_TenDocGia_TTV;
	private JTextField textField_NgaySinh_TTV;
	private JTextField textField_SDT_TTV;
	private JTextField textField_Diachi_TTV;
	private JTextField textField_PhiDangKy_TTV;
	private JTable table_QLTV;
	private JTable tableMuonSach;
	private JComboBox comboBox_HanThe;

	private JTextField textField_Search;

	private JTable tableQuanLySach;
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
//				changeButtonColor(btn_QLPhieuMuon_left);
//				cardLayout.show(cardPanel, "panelQuanLySach");
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
//				changeButtonColor(btn_QuanLySach_left);
//				cardLayout.show(cardPanel, "panelMuonSach");
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
				cardLayout.show(cardPanel, "panelQlyDocGia");
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
	  	
	  	panelThemThanhVien = new JPanel();
	  	cardPanel.add(panelThemThanhVien, "panelTheThanhVien");
	  	panelThemThanhVien.setLayout(null);
	  	// Quan Ly Sach
	  	JPanel panel_2 = new JPanel();
	  	panel_2.setBounds(10, 123, 829, 415);
	  	panelQuanLySach.add(panel_2);
	  	
	  	table_QuanLySach = new JTable();
	  	table_QuanLySach.setModel(new DefaultTableModel(
	  		new Object[][] {
	  			{null, null, null, null, null, null, null, null, null},
	  		},
	  		new String[] {
	  			"ID", "Tên Sách", "Tác giả", "Nhà XB", "Năm XB", "Thể Loại", "Giá Sách", "Ngôn ngữ", "Tình trạng"
	  		}
	  	));
	  	table_QuanLySach.getColumnModel().getColumn(0).setPreferredWidth(40);
	  	table_QuanLySach.getColumnModel().getColumn(1).setPreferredWidth(40);
	  	table_QuanLySach.getColumnModel().getColumn(8).setPreferredWidth(51);
	  	panel_2.setLayout(null);
	  	table_QuanLySach = QuanLySach.getInstance().selectAll(table_QuanLySach);
//	  	table_QuanLySach = QuanLySach.getInstance().selectbyTenSach(table_QuanLySach);
	  	JScrollPane scrollPane = new JScrollPane(table_QuanLySach);
	  	scrollPane.setBounds(0, 0, 829, 415);
	  	panel_2.add(scrollPane);
	  	
//	  	JButton btnThemQLNhapSach = new JButton("Thêm");
//	  	btnThemQLNhapSach.setFont(new Font("Tahoma", Font.BOLD, 16));
//	  	btnThemQLNhapSach.setBounds(200, 67, 120, 45);
//	  	panelQuanLySach.add(btnThemQLNhapSach);
//	  	
////	  	JButton btnSuaQLNhapSach = new JButton("Sửa");
////	  	btnSuaQLNhapSach.addActionListener(new ActionListener() {
//	  		public void actionPerformed(ActionEvent e) {
//	  		}
//	  	});
//	  	btnSuaQLNhapSach.setFont(new Font("Tahoma", Font.BOLD, 16));
//	  	btnSuaQLNhapSach.setBounds(10, 67, 120, 45);
//	  	panelQuanLySach.add(btnSuaQLNhapSach);
	  	
	  	textField_Search = new JTextField();
	  	textField_Search.setForeground(Color.LIGHT_GRAY);
	  	textField_Search.setFont(new Font("Tahoma", Font.PLAIN, 20));
	  	textField_Search.setColumns(10);
	  	textField_Search.setBackground(new Color(226, 255, 153));
	  	textField_Search.setBounds(510, 10, 300, 45);
	  	panelQuanLySach.add(textField_Search);
	 // ----------->Xử lý tìm kiếm cho table_QuanLySach<-----------

	 		// Tạo đối tượng TableRowSorter để lọc dữ liệu trong bảng
	 		TableRowSorter<TableModel> sorter1 = new TableRowSorter<>(table_QuanLySach.getModel());

	 		// Đặt TableRowSorter cho bảng
	 		table_QuanLySach.setRowSorter(sorter1);

	 		// Tạo sự kiện KeyReleased cho JTextField
	 		textField_Search.addKeyListener(new KeyAdapter() {
	 			public void keyReleased(KeyEvent e) {
	 				String input = textField_Search.getText().trim(); // Lấy dữ liệu từ JTextField
	 				if (input.length() == 0) {
	 					// Nếu JTextField rỗng, hiển thị tất cả dữ liệu
	 					sorter1.setRowFilter(null);
	 				} else {
	 					// Lọc dữ liệu theo nội dung JTextField
	 					sorter1.setRowFilter(RowFilter.regexFilter("(?i)" + input));
	 				}
	 			}
	 		});
	  	
	  	JButton btnTimKiemQLNhap_1 = new JButton("");
	  	btnTimKiemQLNhap_1.setOpaque(true);
	  	btnTimKiemQLNhap_1.setBorderPainted(false);
	  	btnTimKiemQLNhap_1.setBackground(new Color(226, 255, 153));
	  	btnTimKiemQLNhap_1.setBounds(467, 10, 45, 45);
	  	btnTimKiemQLNhap_1.setIcon(newIconTimKiem);
	  	panelQuanLySach.add(btnTimKiemQLNhap_1);
	  	
	  	JLabel lblNewLabel_32 = new JLabel("New label");
	  	lblNewLabel_32.setIcon(new ImageIcon(MainView.class.getResource("/images/background.png")));
	  	lblNewLabel_32.setBounds(0, 0, 849, 548);
	  	panelQuanLySach.add(lblNewLabel_32);
	  	
	  	JPanel panel = new JPanel();
	  	panel.setBackground(new Color(255, 255, 255));
	  	panel.setBounds(102, 41, 635, 437);
	  	panelThemThanhVien.add(panel);
	  	panel.setLayout(null);
	  	
	  	JLabel labelTitle_TTV = new JLabel("THẺ THÀNH VIÊN");
	  	labelTitle_TTV.setForeground(new Color(0, 0, 0));
	  	labelTitle_TTV.setHorizontalAlignment(SwingConstants.CENTER);
	  	labelTitle_TTV.setFont(new Font("Times New Roman", Font.BOLD, 25));
	  	labelTitle_TTV.setBounds(200, 26, 236, 50);
	  	panel.add(labelTitle_TTV);
	  	
	  	JLabel label_TenThanhVien_TTV = new JLabel("Tên thành viên");
	  	label_TenThanhVien_TTV.setBounds(55, 125, 72, 14);
	  	panel.add(label_TenThanhVien_TTV);
	  	
	  	JLabel label_NgaySinh_TTV = new JLabel("Ngày sinh");
	  	label_NgaySinh_TTV.setBounds(55, 180, 72, 14);
	  	panel.add(label_NgaySinh_TTV);
	  	
	  	JLabel label_SDT_TTV = new JLabel("Số điện thoại");
	  	label_SDT_TTV.setBounds(58, 234, 69, 14);
	  	panel.add(label_SDT_TTV);
	  	
	  	JLabel label_PhiDangKy_TTV = new JLabel("Phí đăng ký");
	  	label_PhiDangKy_TTV.setBounds(340, 234, 69, 14);
	  	panel.add(label_PhiDangKy_TTV);
	  	
	  	JLabel label_HanThe_TTV = new JLabel("Hạn thẻ");
	  	label_HanThe_TTV.setBounds(340, 180, 69, 14);
	  	panel.add(label_HanThe_TTV);
	  	
	  	JLabel label_DiaChi_TTV = new JLabel("Địa chỉ");
	  	label_DiaChi_TTV.setBounds(340, 125, 69, 14);
	  	panel.add(label_DiaChi_TTV);
	  	
	  	textField_TenDocGia_TTV = new JTextField();
	  	textField_TenDocGia_TTV.setColumns(10);
	  	textField_TenDocGia_TTV.setBounds(137, 122, 130, 20);
	  	panel.add(textField_TenDocGia_TTV);
	  	
	  	textField_NgaySinh_TTV = new JTextField();
	  	textField_NgaySinh_TTV.setColumns(10);
	  	textField_NgaySinh_TTV.setBounds(137, 177, 130, 20);
	  	panel.add(textField_NgaySinh_TTV);
	  	
	  	textField_SDT_TTV = new JTextField();
	  	textField_SDT_TTV.setColumns(10);
	  	textField_SDT_TTV.setBounds(137, 231, 130, 20);
	  	panel.add(textField_SDT_TTV);
	  	
	  	textField_Diachi_TTV = new JTextField();
	  	textField_Diachi_TTV.setColumns(10);
	  	textField_Diachi_TTV.setBounds(430, 122, 130, 20);
	  	panel.add(textField_Diachi_TTV);
	  	
	  	textField_PhiDangKy_TTV = new JTextField();
	  	textField_PhiDangKy_TTV.setColumns(10);
	  	textField_PhiDangKy_TTV.setBounds(430, 231, 130, 20);
	  	panel.add(textField_PhiDangKy_TTV);
	  	
	  	JButton button_Luu_TTV = new JButton("Lưu");
	  	button_Luu_TTV.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {
	  			themThanhVien();
	  		}
	  	});
	  	button_Luu_TTV.setFont(new Font("Times New Roman", Font.BOLD, 15));
	  	button_Luu_TTV.setBounds(175, 363, 109, 33);
	  	panel.add(button_Luu_TTV);
	  	
	  	JButton button_HuyThaoTac_TTV = new JButton("Huỷ thao tác");
	  	button_HuyThaoTac_TTV.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {
	  			huyThemThanhVien();
	  		}
	  	});
	  	button_HuyThaoTac_TTV.setFont(new Font("Times New Roman", Font.BOLD, 15));
	  	button_HuyThaoTac_TTV.setBounds(340, 363, 115, 33);
	  	panel.add(button_HuyThaoTac_TTV);
	  	
	  	JLabel lblNewLabel_7 = new JLabel("Vui lòng điền đầy đủ thông tin trước khi nhấn lưu");
	  	lblNewLabel_7.setForeground(new Color(128, 128, 128));
	  	lblNewLabel_7.setBackground(new Color(255, 255, 255));
	  	lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
	  	lblNewLabel_7.setFont(new Font("Tahoma", Font.ITALIC, 12));
	  	lblNewLabel_7.setBounds(168, 319, 318, 14);
	  	panel.add(lblNewLabel_7);
	  	
	  	comboBox_HanThe = new JComboBox();
	  	comboBox_HanThe.setBackground(new Color(255, 255, 255));
	  	comboBox_HanThe.setModel(new DefaultComboBoxModel(new String[] {"12 tháng", "24 tháng", "36 tháng", "48 tháng"}));
	  	comboBox_HanThe.setBounds(430, 176, 130, 22);
	  	comboBox_HanThe.setOpaque(true);
	  	comboBox_HanThe.setBackground(Color.BLUE);
	  	panel.add(comboBox_HanThe);
	  	
	  	JLabel lblNewLabel_5 = new JLabel("New label");
	  	lblNewLabel_5.setIcon(new ImageIcon(MainView.class.getResource("/images/background.png")));
	  	lblNewLabel_5.setBounds(0, 0, 849, 548);
	  	panelThemThanhVien.add(lblNewLabel_5);
	  	
	  	JPanel panelQlyDocGia = new JPanel();
	  	panelQlyDocGia.setBackground(new Color(255, 255, 255));
	  	cardPanel.add(panelQlyDocGia, "panelQlyDocGia");
	  	panelQlyDocGia.setLayout(null);
	  	
	  	textField_TimKiem_QLTV = new JTextField();
	  	textField_TimKiem_QLTV.setBackground(new Color(0xE2FF99));
	  	textField_TimKiem_QLTV.setForeground(new Color(192, 192, 192));
	  	textField_TimKiem_QLTV.setFont(new Font("Tahoma", Font.PLAIN, 20));
	  	textField_TimKiem_QLTV.setBounds(510, 10, 300, 45);
	  	panelQlyDocGia.add(textField_TimKiem_QLTV);
	  	textField_TimKiem_QLTV.setColumns(10);
	  	
	  	JButton btnTimKiem_QLTV = new JButton("");
	  	btnTimKiem_QLTV.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {
	  			hienThiThongTinThanhVien();
	  		}
	  	});
	  	btnTimKiem_QLTV.setBackground(new Color(0xE2FF99));
	  	btnTimKiem_QLTV.setOpaque(true);
	  	btnTimKiem_QLTV.setBorderPainted(false);
	  	btnTimKiem_QLTV.setBounds(467, 10, 45, 45);
	  	panelQlyDocGia.add(btnTimKiem_QLTV);
	  	btnTimKiem_QLTV.setIcon(newIconTimKiem);
	  	
	  	JPanel panel_3 = new JPanel();
	  	panel_3.setBounds(10, 114, 829, 423);
	  	panelQlyDocGia.add(panel_3);
	  	panel_3.setLayout(null);
	  	
	  	JScrollPane scrollPane_1 = new JScrollPane();
	  	scrollPane_1.setBounds(0, 0, 829, 423);
	  	panel_3.add(scrollPane_1);
	  	
	  	table_QLTV = new JTable();
	  	table_QLTV.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	  	table_QLTV.setFont(new Font("Tahoma", Font.PLAIN, 13));
	  	table_QLTV.setModel(new DefaultTableModel(
	  		new Object[][] {
	  		},
	  		new String[] {
	  			"M\u00E3 TV", "H\u1ECD v\u00E0 t\u00EAn", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "Ng\u00E0y sinh", "\u0110\u1ECBa ch\u1EC9", "TG \u0111\u0103ng k\u00FD", "H\u1EA1n th\u1EBB", "Ph\u00ED \u0111\u0103ng k\u00FD", "T\u00ECnh Tr\u1EA1ng"
	  		}
	  	));
	  	table_QLTV.getColumnModel().getColumn(0).setPreferredWidth(39);
	  	table_QLTV.getColumnModel().getColumn(1).setPreferredWidth(98);
	  	table_QLTV.getColumnModel().getColumn(2).setPreferredWidth(74);
	  	table_QLTV.getColumnModel().getColumn(3).setPreferredWidth(67);
	  	table_QLTV.getColumnModel().getColumn(4).setPreferredWidth(122);
	  	table_QLTV.getColumnModel().getColumn(5).setPreferredWidth(73);
	  	table_QLTV.getColumnModel().getColumn(6).setPreferredWidth(69);
	  	table_QLTV.getColumnModel().getColumn(7).setPreferredWidth(66);
	  	table_QLTV.getColumnModel().getColumn(8).setPreferredWidth(61);
	  	scrollPane_1.setViewportView(table_QLTV);
	  	
	  	JButton btnNewButton = new JButton("");
	  	btnNewButton.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {
	  			suaThongTinThanhVien();
	  		}
	  	});
	  	btnNewButton.setBackground(new Color(255, 255, 255));
	  	btnNewButton.setBorderPainted(false);
	  	btnNewButton.setIcon(new ImageIcon("D:\\CODE\\Java\\BaiTapLon2023\\src\\images\\suaThongTin.png"));
	  	btnNewButton.setBounds(56, 31, 60, 59);
	  	panelQlyDocGia.add(btnNewButton);
	}
	
	private void changeButtonColor(JButton button) {
        if (lastClicked != null) {
        	lastClicked.setBackground(new Color(0xE2FF99));
        }
        button.setBackground(new Color(0X7A8F44));
        lastClicked = button;
    }
	
	public void themThanhVien() {

		DocGia docGia = new DocGia();
		TheThanhVien theThanhVien = new TheThanhVien();
		
		// Biến dùng kiểm tra tính đúng đắn của dữ liệu
		Pattern patternDate = Pattern.compile("^\\d{4}[-|/]\\d{2}[-|/]\\d{2}$");
		Pattern patternSDT = Pattern.compile("^0[3798]{1}\\d{8}$");
			
		// Kiểm tra thông tin có đủ không
		if(textField_TenDocGia_TTV.getText().equals("")) {		
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
	        JOptionPane.showMessageDialog(frame,
	                "Vui lòng điền đủ thông tin!!!",
	                "THÔNG BÁO",
	                JOptionPane.ERROR_MESSAGE);
	                	       
		}else if (textField_NgaySinh_TTV.getText().equals("")) {		
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
	        JOptionPane.showMessageDialog(frame,
	                "Vui lòng điền đủ thông tin!!!",
	                "THÔNG BÁO",
	                JOptionPane.ERROR_MESSAGE);
	                
		}else if(patternDate.matcher(textField_NgaySinh_TTV.getText()).matches() == false) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
	        JOptionPane.showMessageDialog(frame,
	                "Vui lòng điền đúng định dạng ngày sinh!!!",
	                "THÔNG BÁO",
	                JOptionPane.ERROR_MESSAGE); 
	                
		}else if (textField_SDT_TTV.getText().equals("")) {		
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
	        JOptionPane.showMessageDialog(frame,
	                "Vui lòng điền đủ thông tin!!!",
	                "THÔNG BÁO",
	                JOptionPane.ERROR_MESSAGE);
	             
		} else if(patternSDT.matcher(textField_SDT_TTV.getText()).matches() == false) {
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
		        JOptionPane.showMessageDialog(frame,
		                "Vui lòng điền đúng định dạng số điện thoại!!!",
		                "THÔNG BÁO",
		                JOptionPane.ERROR_MESSAGE); 
		        
		}else if (textField_Diachi_TTV.getText().equals("")) {		
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
	        JOptionPane.showMessageDialog(frame,
	                "Vui lòng điền đủ thông tin!!!",
	                "THÔNG BÁO",
	                JOptionPane.ERROR_MESSAGE);	        

		}else if (textField_PhiDangKy_TTV.getText().equals("")) {		
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
	        JOptionPane.showMessageDialog(frame,
	                "Vui lòng điền đủ thông tin!!!",
	                "THÔNG BÁO",
	                JOptionPane.ERROR_MESSAGE); 
		}else {
			
			// Lấy dữ liệu nhập
			docGia.setTenDocGia(textField_TenDocGia_TTV.getText()); 
			
			String ngaySinh = textField_NgaySinh_TTV.getText();
			java.sql.Date date1 = Date.valueOf(ngaySinh);
			docGia.setNgaySinh(date1);

			docGia.setSDT(textField_SDT_TTV.getText()); 

			docGia.setDiaChi(textField_Diachi_TTV.getText()); 

			long millis = System.currentTimeMillis();
	        java.sql.Date date2 = new java.sql.Date(millis);
			theThanhVien.setNgayDangKy(date2);
			
			
				// Nhập hạn thẻ
			Calendar c = Calendar.getInstance();
			
			if(comboBox_HanThe.getSelectedItem().equals("12 tháng")) {
				c.add(Calendar.YEAR, 1);
				long millis_1Y = TimeUnit.DAYS.toMillis(365);
				Date date3 = new Date(millis + millis_1Y);
		        theThanhVien.setHanThe(date3);

			}else if(comboBox_HanThe.getSelectedItem().equals("24 tháng")) {
				c.add(Calendar.YEAR, 2);
				long millis_2Y = TimeUnit.DAYS.toMillis(365*2);
				Date date3 = new Date(millis + millis_2Y);
		        theThanhVien.setHanThe(date3);
				
			}else if(comboBox_HanThe.getSelectedItem().equals("36 tháng")) {
				c.add(Calendar.YEAR, 3);
				long millis_3Y = TimeUnit.DAYS.toMillis(365*3);
				Date date3 = new Date(millis + millis_3Y);
		        theThanhVien.setHanThe(date3);

			}else if(comboBox_HanThe.getSelectedItem().equals("48 tháng")) {
				c.add(Calendar.YEAR, 4);
				long millis_4Y = TimeUnit.DAYS.toMillis(365*4);
				Date date3 = new Date(millis + millis_4Y);
		        theThanhVien.setHanThe(date3);
			}
				        

			String phiDK = textField_PhiDangKy_TTV.getText();
	        int PDK = Integer.valueOf(phiDK);
	        theThanhVien.setPhiDangKy(PDK); 
			 
			new Dialog_ThemThanhVien(this, docGia, theThanhVien); // hien thi dialog
			 
				// Xoá dữ liệu nhập trên màn hình
				textField_TenDocGia_TTV.setText("");
				textField_NgaySinh_TTV.setText("");
				textField_SDT_TTV.setText("");
				textField_Diachi_TTV.setText("");
				textField_PhiDangKy_TTV.setText("");
		}	
	}
	
	
	public void huyThemThanhVien() {
		textField_TenDocGia_TTV.setText("");
		textField_NgaySinh_TTV.setText("");
		textField_SDT_TTV.setText("");
		textField_Diachi_TTV.setText("");
		textField_PhiDangKy_TTV.setText("");
	}
	
	
	public void hienThiThongTinThanhVien() {
		// Xoá toàn bộ dữ liệu trên bảng
		DefaultTableModel dtm = (DefaultTableModel) table_QLTV.getModel();
		dtm.setRowCount(0);
		
		if(textField_TimKiem_QLTV.getText().equals("")) {
			ThanhVien.getInstance().selectAll(table_QLTV);
		}else {
			ThanhVien.getInstance().selectBySDT(table_QLTV, textField_TimKiem_QLTV.getText());
		}
	}
	
	
	public void suaThongTinThanhVien() {
		new Dialog_SuaThongTinThanhVien(this, textField_TimKiem_QLTV.getText());
	}
	
}








































