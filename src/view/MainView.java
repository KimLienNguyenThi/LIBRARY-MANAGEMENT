package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import database.Service23;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

public class MainView extends JFrame {
	JFrame frame = new JFrame();

	public static ArrayList<Integer> arrMaSach = new ArrayList<>();
	private JPanel contentPane;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JButton lastClicked;
	private JPanel panelTheDocGia;
	private JTable table_QuanLySach;
	private JTable table_QuanLyNhapLo;
	public static JTable table_QuanLyPhieuMuon;
	private JTextField textField_TimKiem_qlnl;
	private JTextField textField_TimKiem_qls;
	private JTextField textField_TimKiem_qlpm;
	private JTextField textField_TimKiem_qldg;
	private JTable table_QuanLyDocGia;
	static JTable table_pm;
	private JTextField textField_TimKiem_pm;
	private JTable table_DocGia_pm;
	private JPanel panel_pm;
	private JLabel lbl_TenNhanVien = new JLabel();
	private int MaPM;

	private java.sql.Date ngayTra;
	private java.sql.Date ngayMuon;
	private int maTheDocGia;
	private JLabel lbl_MaThe_pm;
	private int SlHangTablePm = 0;

	/**
	 * Launch the application.
	 */

	public MainView(String hotendn) {
		setResizable(false);
		this.init();
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		lbl_TenNhanVien.setText(hotendn);
		PhieuMuonView.lbl_TenNhanVien.setText(hotendn);
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

		Date date = new Date();
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String formattedDate = formatter1.format(date);
		String formattedDateTime = formatter2.format(date);

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
		Image newImgUser = imgUser.getScaledInstance(29, 29, java.awt.Image.SCALE_SMOOTH);
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

		JPanel panel_Left = new JPanel();
		panel_Left.setBackground(new Color(0xE2FF99));
		panel_Left.setBounds(0, 65, 238, 548);
		contentPane.add(panel_Left);
		panel_Left.setLayout(null);

		JLabel lbl_ChucNang_left = new JLabel("Chức năng");
		lbl_ChucNang_left.setForeground(new Color(255, 255, 255));
		lbl_ChucNang_left.setBackground(new Color(0, 0, 0));
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
				cardLayout.show(cardPanel, "panelPhieuMuon");
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
				Service23.getInstance().UpdateTinhTrangPhieuMuonQuahan();

				// Cập nhật bảng Quản lý phiếu mượn
				((DefaultTableModel) MainView.table_QuanLyPhieuMuon.getModel()).setRowCount(0);
				Service23.getInstance().SelectAllPhieuMuon(table_QuanLyPhieuMuon);

				changeButtonColor(btn_QLPhieuMuon_left);
				cardLayout.show(cardPanel, "panelQuanLyPhieuMuon");
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
				cardLayout.show(cardPanel, "panelQuanLySach");
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
				cardLayout.show(cardPanel, "panelQuanLyNhapLo");
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
				cardLayout.show(cardPanel, "panelTheDocGia");
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
		cardPanel.add(panelPhieuMuon, "panelPhieuMuon");
		panelPhieuMuon.setLayout(null);

		panel_pm = new JPanel();
		panel_pm.setBounds(10, 10, 500, 528);
		panelPhieuMuon.add(panel_pm);
		panel_pm.setLayout(null);

		JLabel lbl0_pm = new JLabel("Phiếu mượn thư viện ABC");
		lbl0_pm.setFont(new Font("Tahoma", Font.BOLD, 26));
		lbl0_pm.setHorizontalAlignment(SwingConstants.CENTER);
		lbl0_pm.setBounds(10, 0, 480, 47);
		panel_pm.add(lbl0_pm);

		JLabel lbl_Time_pm = new JLabel("");
		lbl_Time_pm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_Time_pm.setBounds(330, 54, 120, 20);
		panel_pm.add(lbl_Time_pm);

		lbl_TenNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_TenNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_TenNhanVien.setBounds(120, 54, 131, 20);
		panel_pm.add(lbl_TenNhanVien);

		// Gọi lại hàm sau 1 giây
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String formattedDate = formatter.format(date);
				lbl_Time_pm.setText(formattedDate);
			}
		});
		// Bắt đầu gọi
		timer.start();

		JLabel lbl7_pm = new JLabel("Nhân viên: ");
		lbl7_pm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl7_pm.setBounds(50, 54, 60, 20);
		panel_pm.add(lbl7_pm);

		JSeparator separator_pm = new JSeparator();
		separator_pm.setForeground(new Color(0, 0, 0));
		separator_pm.setBounds(50, 84, 400, 2);
		panel_pm.add(separator_pm);

		JLabel lbl1_pm = new JLabel("Mã thẻ");
		lbl1_pm.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl1_pm.setBounds(50, 110, 120, 20);
		panel_pm.add(lbl1_pm);

		lbl_MaThe_pm = new JLabel("");
		lbl_MaThe_pm.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_MaThe_pm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_MaThe_pm.setBackground(new Color(255, 255, 255));
		lbl_MaThe_pm.setBounds(180, 110, 270, 20);
		panel_pm.add(lbl_MaThe_pm);

		JLabel lbl2_pm = new JLabel("Số điện thoại");
		lbl2_pm.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl2_pm.setBounds(50, 140, 120, 20);
		panel_pm.add(lbl2_pm);

		JLabel lbl3_pm = new JLabel("Họ và tên");
		lbl3_pm.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl3_pm.setBounds(50, 170, 120, 20);
		panel_pm.add(lbl3_pm);

		JLabel lbl4_pm = new JLabel("Địa chỉ");
		lbl4_pm.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl4_pm.setBounds(50, 200, 120, 20);
		panel_pm.add(lbl4_pm);

		JLabel lbl5_pm = new JLabel("Ngày mượn");
		lbl5_pm.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl5_pm.setBounds(50, 230, 93, 20);
		panel_pm.add(lbl5_pm);

		JLabel lbl6_pm = new JLabel("Hạn trả");
		lbl6_pm.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl6_pm.setBounds(270, 230, 71, 20);
		panel_pm.add(lbl6_pm);

		JLabel lbl_SDT_pm = new JLabel("");
		lbl_SDT_pm.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_SDT_pm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_SDT_pm.setBackground(Color.WHITE);
		lbl_SDT_pm.setBounds(180, 140, 270, 20);
		panel_pm.add(lbl_SDT_pm);

		JLabel lbl_HovaTen_pm = new JLabel("");
		lbl_HovaTen_pm.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_HovaTen_pm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_HovaTen_pm.setBackground(Color.WHITE);
		lbl_HovaTen_pm.setBounds(180, 170, 270, 20);
		panel_pm.add(lbl_HovaTen_pm);

		JLabel lbl_DiaChi_pm = new JLabel("");
		lbl_DiaChi_pm.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_DiaChi_pm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_DiaChi_pm.setBackground(Color.WHITE);
		lbl_DiaChi_pm.setBounds(180, 200, 270, 20);
		panel_pm.add(lbl_DiaChi_pm);

		JLabel lbl_NgayMuon_pm = new JLabel("" + formattedDate);
		lbl_NgayMuon_pm.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_NgayMuon_pm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_NgayMuon_pm.setBackground(Color.WHITE);
		lbl_NgayMuon_pm.setBounds(142, 230, 120, 20);
		panel_pm.add(lbl_NgayMuon_pm);

		// Gán Biến ngày mượn = thời gian hiện tại
		Date now = new Date();
		ngayMuon = new java.sql.Date(now.getTime());
		System.err.println(ngayMuon);

		JComboBox comboBox_NgayTra_pm = new JComboBox();
		comboBox_NgayTra_pm.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox_NgayTra_pm.setModel(new DefaultComboBoxModel(
				new String[] { "Chọn hạn trả", "1 tháng", "3 tháng", "5 tháng", "7 tháng", "10 tháng", "12 tháng" }));
		comboBox_NgayTra_pm.setBounds(340, 228, 110, 25);
		panel_pm.add(comboBox_NgayTra_pm);
		comboBox_NgayTra_pm.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String selectedItem = comboBox_NgayTra_pm.getSelectedItem().toString();

					if (selectedItem.equals("Chọn hạn trả")) {
						ngayTra = null;
					} else if (selectedItem.equals("1 tháng")) {
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.MONTH, 1);
						ngayTra = new java.sql.Date(cal.getTimeInMillis());
					} else if (selectedItem.equals("3 tháng")) {
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.MONTH, 3);
						ngayTra = new java.sql.Date(cal.getTimeInMillis());
					} else if (selectedItem.equals("5 tháng")) {
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.MONTH, 5);
						ngayTra = new java.sql.Date(cal.getTimeInMillis());
					} else if (selectedItem.equals("7 tháng")) {
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.MONTH, 7);
						ngayTra = new java.sql.Date(cal.getTimeInMillis());
					} else if (selectedItem.equals("10 tháng")) {
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.MONTH, 10);
						ngayTra = new java.sql.Date(cal.getTimeInMillis());
					} else if (selectedItem.equals("12 tháng")) {
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.MONTH, 12);
						ngayTra = new java.sql.Date(cal.getTimeInMillis());
					}
				}
			}
		});

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(50, 270, 400, 2);
		panel_pm.add(separator_1);

		JPanel panel_pm2 = new JPanel();
		panel_pm2.setBounds(50, 282, 400, 236);
		panel_pm.add(panel_pm2);
		panel_pm2.setLayout(null);

		table_pm = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_pm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_pm.setFont(new Font("Tahoma", Font.PLAIN, 10));
		table_pm.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 s\u00E1ch", "T\u00EAn s\u00E1ch", "T\u00E1c gi\u1EA3" }));
		table_pm.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_pm.getColumnModel().getColumn(1).setPreferredWidth(208);
		table_pm.getColumnModel().getColumn(2).setPreferredWidth(54);

		table_pm.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				// Cập nhật số lượng hàng hiện tại của JTable
				SlHangTablePm = table_pm.getModel().getRowCount();
			}
		});

		JScrollPane scrollPane_pm = new JScrollPane(table_pm);
		scrollPane_pm.setBounds(0, 0, 400, 236);
		panel_pm2.add(scrollPane_pm);

		JPanel panel_pm3 = new JPanel();
		panel_pm3.setBounds(520, 10, 319, 323);
		panelPhieuMuon.add(panel_pm3);
		panel_pm3.setLayout(null);

		JButton btn_TimKiem_pm = new JButton("");
		btn_TimKiem_pm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_TimKiem_pm.setOpaque(true);
		btn_TimKiem_pm.setBorderPainted(false);
		btn_TimKiem_pm.setBackground(new Color(226, 255, 153));
		btn_TimKiem_pm.setBounds(10, 10, 35, 35);
		panel_pm3.add(btn_TimKiem_pm);
		btn_TimKiem_pm.setIcon(newIconTimKiem);

		textField_TimKiem_pm = new JTextField();
		textField_TimKiem_pm.setForeground(new Color(0, 0, 0));
		textField_TimKiem_pm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_TimKiem_pm.setColumns(10);
		textField_TimKiem_pm.setBackground(new Color(226, 255, 153));
		textField_TimKiem_pm.setBounds(45, 10, 264, 35);
		panel_pm3.add(textField_TimKiem_pm);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 75, 299, 238);
		panel_pm3.add(panel_3);
		panel_3.setLayout(null);

		table_DocGia_pm = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_DocGia_pm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_DocGia_pm.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 Th\u1EBB", "H\u1ECD t\u00EAn", "S\u0110T", "\u0110\u1ECBa ch\u1EC9" }));

		JScrollPane scrollPane_pm2 = new JScrollPane(table_DocGia_pm);
		scrollPane_pm2.setBounds(0, 0, 299, 238);
		panel_3.add(scrollPane_pm2);

		Service23.getInstance().SelectAllTheDocGia(table_DocGia_pm);

		//////////////
		table_DocGia_pm.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table_DocGia_pm.getSelectedRow(); // lấy chỉ số của hàng được chọn trong table.
				int maThe = (int) table_DocGia_pm.getValueAt(row, 0);
				String hoTen = (String) table_DocGia_pm.getValueAt(row, 1);
				String SDT = (String) table_DocGia_pm.getValueAt(row, 2);
				String diaChi = (String) table_DocGia_pm.getValueAt(row, 3);
				lbl_MaThe_pm.setText("" + maThe);
				maTheDocGia = maThe;
				lbl_HovaTen_pm.setText(hoTen);
				lbl_SDT_pm.setText(SDT);
				lbl_DiaChi_pm.setText(diaChi);
			}
		});

		// ----------->Xử lý tìm kiếm cho table_docgia_pm<-----------

		// Tạo đối tượng TableRowSorter để lọc dữ liệu trong bảng
		TableRowSorter<TableModel> sorter1 = new TableRowSorter<>(table_DocGia_pm.getModel());

		// Đặt TableRowSorter cho bảng
		table_DocGia_pm.setRowSorter(sorter1);

		// Tạo sự kiện KeyReleased cho JTextField
		textField_TimKiem_pm.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String input = textField_TimKiem_pm.getText().trim(); // Lấy dữ liệu từ JTextField
				if (input.length() == 0) {
					// Nếu JTextField rỗng, hiển thị tất cả dữ liệu
					sorter1.setRowFilter(null);
				} else {
					// Lọc dữ liệu theo nội dung JTextField
					sorter1.setRowFilter(RowFilter.regexFilter("(?i)" + input));
				}
			}
		});
		// ----------------------------------------------------------

		JButton btn_ThemSach_pm = new JButton("Thêm sách");
		btn_ThemSach_pm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DiaLog_ThemSach_PM(frame).setVisible(true);
			}
		});
		btn_ThemSach_pm.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_ThemSach_pm.setBounds(520, 388, 111, 45);
		panelPhieuMuon.add(btn_ThemSach_pm);

		JButton btn_XoaSach_pm = new JButton("Xóa sách");
		btn_XoaSach_pm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_pm.getSelectedRow(); // lấy chỉ số của hàng được chọn trong table.
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Hãy chọn sách muốn xóa!");
				} else {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa?", "Thông báo",
							JOptionPane.YES_NO_OPTION);
					if (dialogResult == JOptionPane.YES_OPTION) {
						// Xử lý khi người dùng chọn Yes
						int maSach = (int) table_pm.getValueAt(row, 0);
						arrMaSach.remove(Integer.valueOf(maSach));
						// arrMaSach.re

						DefaultTableModel model = (DefaultTableModel) table_pm.getModel();
						model.removeRow(row);
					} else {
						// Xử lý khi người dùng chọn No
					}
				}
			}
		});
		btn_XoaSach_pm.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_XoaSach_pm.setBounds(520, 481, 111, 45);
		panelPhieuMuon.add(btn_XoaSach_pm);

		JButton btn_Huy_pm = new JButton("Hủy");
		btn_Huy_pm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn hủy không?", "Thông báo",
						JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					// Xử lý khi người dùng chọn Yes
					lbl_MaThe_pm.setText("");
					lbl_HovaTen_pm.setText("");
					lbl_SDT_pm.setText("");
					lbl_DiaChi_pm.setText("");
					comboBox_NgayTra_pm.setSelectedIndex(0);
					// table_pm.setModel(null);
					((DefaultTableModel) table_pm.getModel()).setRowCount(0);
					arrMaSach.clear();
				} else {
					// Xử lý khi người dùng chọn No
				}
			}
		});
		btn_Huy_pm.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_Huy_pm.setBounds(728, 388, 111, 45);
		panelPhieuMuon.add(btn_Huy_pm);

		JButton btn_InPhieu_pm = new JButton("In phiếu");
		btn_InPhieu_pm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int maPmLonNhat;

				if (lbl_MaThe_pm.getText() == "") {
					JOptionPane.showMessageDialog(null, "Hãy chọn thông tin độc giả!");
				} else {
					if (ngayTra == null) {
						JOptionPane.showMessageDialog(null, "Hãy chọn ngày trả!");
					} else {

						if (SlHangTablePm == 0) {
							JOptionPane.showMessageDialog(null, "Hãy Thêm sách!");
						} else {
							int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?",
									"Thông báo", JOptionPane.YES_NO_OPTION);
							if (dialogResult == JOptionPane.YES_OPTION) {
								Service23.getInstance().InsertPhieuMuon(maTheDocGia, ngayMuon, ngayTra);
								ArrayList<Integer> arr = Service23.getInstance().SelectAllMaPm(maTheDocGia);
								maPmLonNhat = Collections.max(arr);

								if (arr.isEmpty()) {
									// Xử lý trường hợp khi ArrayList là rỗng
								} else {
									for (int i = 0; i < arrMaSach.size(); i++) {
										Service23.getInstance().InsertChiTietPhieuMuon(maPmLonNhat, arrMaSach.get(i));
										Service23.getInstance().UpdateTinhTrangSachHet(maPmLonNhat);
									}
									// Cập nhật bảng Quản lý phiếu mượn
									((DefaultTableModel) MainView.table_QuanLyPhieuMuon.getModel()).setRowCount(0);
									Service23.getInstance().SelectAllPhieuMuon(table_QuanLyPhieuMuon);

									// Cập nhật bảng Thêm sách
									((DefaultTableModel) DiaLog_ThemSach_PM.table.getModel()).setRowCount(0);
									Service23.getInstance().SelectAllSachCon(DiaLog_ThemSach_PM.table);

									// Gắn dữ liệu vào PhieuMuonView.java
									PhieuMuonView.lbl_time.setText(lbl_Time_pm.getText());
									PhieuMuonView.lbl_MaPm.setText(maPmLonNhat + "");
									PhieuMuonView.lbl_MaThe.setText(lbl_MaThe_pm.getText());
									PhieuMuonView.lbl_HoTen.setText(lbl_HovaTen_pm.getText());
									PhieuMuonView.lbl_SDT.setText(lbl_SDT_pm.getText());
									PhieuMuonView.lbl_DiaChi.setText(lbl_DiaChi_pm.getText());
									PhieuMuonView.lbl_NgayMuon.setText(lbl_NgayMuon_pm.getText());

									// SimpleDateFormat formatterNgayTra = new SimpleDateFormat("dd/MM/yyyy");
									String formattedNgayTra = formatter1.format(ngayTra);

									PhieuMuonView.lbl_NgayTra.setText(formattedNgayTra + "");
									PhieuMuonView.rowCount = SlHangTablePm;

									// Đổ dữ liệu từ table table_pm vào PhieuMuonView.textArea
									SetDataTextarea(table_pm, 0, PhieuMuonView.textArea_MaSach);
									SetDataTextarea(table_pm, 1, PhieuMuonView.textArea_TenSach);
									SetDataTextarea(table_pm, 2, PhieuMuonView.textArea_TacGia);

									// Set tất cả dữ liệu trên phiếu mượn về null
									lbl_MaThe_pm.setText("");
									lbl_HovaTen_pm.setText("");
									lbl_SDT_pm.setText("");
									lbl_DiaChi_pm.setText("");
									comboBox_NgayTra_pm.setSelectedIndex(0);
									// table_pm.setModel(null);
									((DefaultTableModel) table_pm.getModel()).setRowCount(0);
									arrMaSach.clear();

									new PhieuMuonView().setVisible(false);
									
									
									

										try {
											// Tạo đối tượng BufferedImage với kích thước tương ứng
											Dimension size = PhieuMuonView.panel.getSize();
											BufferedImage image = new BufferedImage(size.width, size.height,
													BufferedImage.TYPE_INT_RGB);

											// Tạo đối tượng Graphics2D từ BufferedImage
											Graphics2D g2 = image.createGraphics();

											// Vẽ nội dung của JPanel lên Graphics2D
											PhieuMuonView.panel.paint(g2);

											// Tạo đối tượng Document
											Document document = new Document();

											// Khởi tạo PdfWriter với Document và FileOutputStream để ghi vào file PDF
											PdfWriter.getInstance(document, new FileOutputStream(
													"File PDF Phiếu Mượn/PhieuMuon Mã[" + maPmLonNhat + "].pdf"));

											// Mở Document
											document.open();

											// Chèn ảnh từ BufferedImage vào PDF
											com.itextpdf.text.Image pdfImage = com.itextpdf.text.Image.getInstance(
													new java.awt.image.BufferedImage(image.getColorModel(),
															image.copyData(null), image.isAlphaPremultiplied(), null),
													null);
											document.add(pdfImage);

											// Đóng Document
											document.close();
										} catch (FileNotFoundException e2) {
											e2.printStackTrace();
										} catch (BadElementException e2) {
											e2.printStackTrace();
										} catch (DocumentException e2) {
											e2.printStackTrace();
										} catch (IOException e2) {
											e2.printStackTrace();
										}

									
									// Mở file pdf của phiếu mượn
									try {
										File file = new File("File PDF Phiếu Mượn/PhieuMuon Mã[" + maPmLonNhat + "].pdf");
										Desktop.getDesktop().open(file);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
									
								}
							} else {
								// Xử lý khi người dùng chọn No
							}
						}

					}
				}
			}
		});

		btn_InPhieu_pm.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_InPhieu_pm.setBounds(728, 481, 111, 45);
		panelPhieuMuon.add(btn_InPhieu_pm);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(MainView.class.getResource("/images/background.png")));
		lblNewLabel.setBounds(0, 0, 849, 548);
		panelPhieuMuon.add(lblNewLabel);

		JPanel panelQLPhieuMuon = new JPanel();
		panelQLPhieuMuon.setBackground(new Color(255, 255, 255));
		cardPanel.add(panelQLPhieuMuon, "panelQuanLyPhieuMuon");
		panelQLPhieuMuon.setLayout(null);

		JScrollPane scrollPane_qlpm = new JScrollPane();
		scrollPane_qlpm.setBounds(10, 122, 829, 415);
		panelQLPhieuMuon.add(scrollPane_qlpm);

		textField_TimKiem_qlpm = new JTextField();
		textField_TimKiem_qlpm.setForeground(new Color(0, 0, 0));
		textField_TimKiem_qlpm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_TimKiem_qlpm.setColumns(10);
		textField_TimKiem_qlpm.setBackground(new Color(226, 255, 153));
		textField_TimKiem_qlpm.setBounds(513, 11, 300, 45);
		panelQLPhieuMuon.add(textField_TimKiem_qlpm);

		table_QuanLyPhieuMuon = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_QuanLyPhieuMuon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_QuanLyPhieuMuon.setFont(new Font("Tahoma", Font.PLAIN, 10));
		table_QuanLyPhieuMuon.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 PM", "M\u00E3 th\u1EBB", "H\u1ECD t\u00EAn", "S\u0110T",
						"Ng\u00E0y m\u01B0\u1EE3n", "Ng\u00E0y tr\u1EA3", "T\u00ECnh tr\u1EA1ng" }));
		table_QuanLyPhieuMuon.getColumnModel().getColumn(0).setPreferredWidth(86);
		table_QuanLyPhieuMuon.getColumnModel().getColumn(1).setPreferredWidth(86);
		table_QuanLyPhieuMuon.getColumnModel().getColumn(2).setPreferredWidth(64);
		table_QuanLyPhieuMuon.getColumnModel().getColumn(3).setPreferredWidth(78);
		table_QuanLyPhieuMuon.getColumnModel().getColumn(4).setPreferredWidth(67);
		scrollPane_qlpm.setViewportView(table_QuanLyPhieuMuon);

		table_QuanLyPhieuMuon.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table_QuanLyPhieuMuon.getSelectedRow(); // lấy chỉ số của hàng được chọn trong table.
				if (row >= 0) { // Đảm bảo là có hàng được chọn
					Object value = table_QuanLyPhieuMuon.getValueAt(row, 0); // Lấy giá trị của cột đầu tiên (chỉ số cột
																				// là 0)
					MaPM = ((Integer) value).intValue();
				}
			}
		});

		// Lấy dữ liệu Quảng lý phiếu mượn từ sql lên table
		Service23.getInstance().SelectAllPhieuMuon(table_QuanLyPhieuMuon);

		// ----------->Xử lý tìm kiếm cho table_QuanLyPhieuMuon<-----------

		// Tạo đối tượng TableRowSorter để lọc dữ liệu trong bảng
		TableRowSorter<TableModel> sorter2 = new TableRowSorter<>(table_QuanLyPhieuMuon.getModel());

		// Đặt TableRowSorter cho bảng
		table_QuanLyPhieuMuon.setRowSorter(sorter2);

		// Tạo sự kiện KeyReleased cho JTextField
		textField_TimKiem_qlpm.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String input = textField_TimKiem_qlpm.getText().trim(); // Lấy dữ liệu từ JTextField
				if (input.length() == 0) {
					// Nếu JTextField rỗng, hiển thị tất cả dữ liệu
					sorter2.setRowFilter(null);
				} else {
					// Lọc dữ liệu theo nội dung JTextField
					sorter2.setRowFilter(RowFilter.regexFilter("(?i)" + input));
				}
			}
		});
		// ----------------------------------------------------------

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

		JButton btn_XemChiTiet_qlpm = new JButton("Xem chi tiết");
		btn_XemChiTiet_qlpm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (MaPM == 0) {
					JOptionPane.showMessageDialog(null, "Hãy chọn phiếu mượn muốn xem!");
				} else {
					new Dialog_XemChiTiet_QLPM(frame, MaPM).setVisible(true);
				}
			}
		});
		btn_XemChiTiet_qlpm.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_XemChiTiet_qlpm.setBounds(10, 43, 160, 45);
		panelQLPhieuMuon.add(btn_XemChiTiet_qlpm);

		JPanel panelQuanLySach = new JPanel();
		panelQuanLySach.setBackground(new Color(255, 255, 255));
		cardPanel.add(panelQuanLySach, "panelQuanLySach");
		panelQuanLySach.setLayout(null);

		JPanel panelQuanLyNhapLo = new JPanel();
		panelQuanLyNhapLo.setBackground(new Color(255, 255, 255));
		cardPanel.add(panelQuanLyNhapLo, "panelQuanLyNhapLo");
		panelQuanLyNhapLo.setLayout(null);

		JPanel panel_table_qlnl = new JPanel();
		panel_table_qlnl.setBounds(10, 123, 829, 415);
		panelQuanLyNhapLo.add(panel_table_qlnl);
		panel_table_qlnl.setLayout(null);

		table_QuanLyNhapLo = new JTable();
		table_QuanLyNhapLo.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, },
				new String[] { "M\u00E3 l\u00F4", "Ng\u00E0y nh\u1EADp", "Thanh to\u00E1n", "Nh\u00E0 cung c\u1EA5p",
						"\u0110\u1ECBa ch\u1EC9 cung c\u1EA5p", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i" }));
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
		cardPanel.add(panelTheDocGia, "panelTheDocGia");
		panelTheDocGia.setLayout(null);

		JPanel panel_table_qls = new JPanel();
		panel_table_qls.setBounds(10, 123, 829, 415);
		panelQuanLySach.add(panel_table_qls);

		table_QuanLySach = new JTable();
		table_QuanLySach.setModel(
				new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null, null }, },
						new String[] { "M\u00E3 s\u00E1ch", "T\u00EAn s\u00E1ch", "T\u00E1c gi\u1EA3", "Nh\u00E0 XB",
								"N\u0103m XB", "Th\u1EC3 lo\u1EA1i", "Gi\u00E1 s\u00E1ch", "Ng\u00F4n ng\u1EEF",
								"T\u00ECnh tr\u1EA1ng" }));
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
		table_QuanLyDocGia
				.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null }, },
						new String[] { "M\u00E3 DG", "H\u1ECD t\u00EAn", "S\u0110T", "Ng\u00E0y Sinh",
								"\u0110\u1ECBa ch\u1EC9", "Tgian \u0110\u0103ng k\u00FD", "H\u1EA1n th\u1EBB",
								"Ph\u00ED \u0111\u0103ng k\u00FD" }));

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

	public void SetDataTextarea(JTable table, int columTable, JTextArea textArea) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < table.getRowCount(); i++) {
			Object value = table.getValueAt(i, columTable);
			buffer.append(value.toString());
			buffer.append("\n");
		}
		textArea.setText(buffer.toString());
	}
}
