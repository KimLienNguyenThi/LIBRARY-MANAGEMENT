package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import java.awt.AlphaComposite;
import java.awt.CardLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Formatter;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import com.toedter.calendar.JDateChooser;

import database.LoginService;
import database.ThanhVien;
import database.cnDatabase;
import model.DocGia;
import model.TheThanhVien;

import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.Box;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainView extends JFrame {
//	private static final JTable DefaultTableModel = null;

	JFrame frame = new JFrame();

	private JPanel contentPane;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JButton lastClicked;
	private JPanel panelThemThanhVien;
	private JTable tableMuonSach;
	private JTextField textField_TimKiem_QLTV;
	private JTable table_1;
	private JTextField textFieldTimKiemQLNhap;
	private JTextField textField;
	private JTextField textField_1;
	JLabel lblNewLabel_6 = new JLabel("");
	private JTextField textField_TenDocGia_TTV;
	private JTextField textField_SDT_TTV;
	private JTextField textField_Diachi_TTV;
	private JLabel jLabel_PhiDangKy_TTV;
	public static JTable table_QLTV;
	private int MaDocGia_QLTV;

	private JComboBox comboBox_HanThe_TTV;
	private JDateChooser chooser_NgaySinh_TTV;

	/**
	 * Launch the application.
	 */

	public MainView(String hotendn) {

		setResizable(false);
		this.init();
		this.setLocationRelativeTo(null);
		this.setVisible(false);
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
		Image newImgUser = imgUser.getScaledInstance(29, 29, java.awt.Image.SCALE_SMOOTH);
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

		lblNewLabel_6.setBounds(531, 10, 95, 31);
		panel_Top.add(lblNewLabel_6);

		JPanel panel_Left = new JPanel();
		panel_Left.setBackground(new Color(0xE2FF99));
		panel_Left.setBounds(0, 65, 238, 548);
		contentPane.add(panel_Left);
		panel_Left.setLayout(null);

		JLabel lblChucNang = new JLabel("Chức năng");
		lblChucNang.setForeground(new Color(255, 255, 255));
		lblChucNang.setBackground(new Color(0, 0, 0));
		lblChucNang.setOpaque(true);
		lblChucNang.setHorizontalAlignment(SwingConstants.CENTER);
		lblChucNang.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChucNang.setBounds(0, 0, 238, 25);
		panel_Left.add(lblChucNang);

		ImageIcon iconBill = new ImageIcon(MainView.class.getResource("/images/bill.png"));
		Image imgBill = iconBill.getImage();
		Image newImgBill = imgBill.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconBill = new ImageIcon(newImgBill);

		JButton btnPhieuMuon = new JButton("PHIẾU MƯỢN");
		btnPhieuMuon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btnPhieuMuon);
				cardLayout.show(cardPanel, "panelHoaDon");
			}
		});
		btnPhieuMuon.setHorizontalAlignment(SwingConstants.LEFT);
		btnPhieuMuon.setBackground(new Color(0xE2FF99));
		btnPhieuMuon.setOpaque(true);
		btnPhieuMuon.setBorderPainted(false);
		btnPhieuMuon.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnPhieuMuon.setBounds(0, 51, 238, 75);
		panel_Left.add(btnPhieuMuon);
		btnPhieuMuon.setIcon(newIconBill);

		ImageIcon iconMnBooks = new ImageIcon(MainView.class.getResource("/images/mnbooks.png"));
		Image imgMnBooks = iconMnBooks.getImage();
		Image newImgMnBooks = imgMnBooks.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconMnBooks = new ImageIcon(newImgMnBooks);

		JButton btnQLPhieuMuon = new JButton("QLý PHIẾU MƯỢN");
		btnQLPhieuMuon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btnQLPhieuMuon);
				cardLayout.show(cardPanel, "panelQuanLySach");
			}
		});
		btnQLPhieuMuon.setHorizontalAlignment(SwingConstants.LEADING);
		btnQLPhieuMuon.setBackground(new Color(0xE2FF99));
		btnQLPhieuMuon.setOpaque(true);
		btnQLPhieuMuon.setBorderPainted(false);
		btnQLPhieuMuon.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnQLPhieuMuon.setBounds(0, 136, 238, 75);
		panel_Left.add(btnQLPhieuMuon);
		btnQLPhieuMuon.setIcon(newIconMnBooks);

		ImageIcon iconExChange = new ImageIcon(MainView.class.getResource("/images/exchange.png"));
		Image imgExChange = iconExChange.getImage();
		Image newImgExChange = imgExChange.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconExChange = new ImageIcon(newImgExChange);

		JButton btnMuonSach = new JButton("QUẢN LÝ SÁCH");
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

		JButton btnQuanLyNhap = new JButton("QUẢN LÝ NHẬP");
		btnQuanLyNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btnQuanLyNhap);
				cardLayout.show(cardPanel, "panelThongKe");
			}
		});
		btnQuanLyNhap.setHorizontalAlignment(SwingConstants.LEADING);
		btnQuanLyNhap.setBackground(new Color(0xE2FF99));
		btnQuanLyNhap.setOpaque(true);
		btnQuanLyNhap.setBorderPainted(false);
		btnQuanLyNhap.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnQuanLyNhap.setBounds(0, 306, 238, 75);
		panel_Left.add(btnQuanLyNhap);
		btnQuanLyNhap.setIcon(newIconTrend);

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

		JButton btnQLyThanhVien = new JButton("QLý Thành Viên");
		btnQLyThanhVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btnQLyThanhVien);
				cardLayout.show(cardPanel, "panelQlyDocGia");
			}
		});
		btnQLyThanhVien.setOpaque(true);
		btnQLyThanhVien.setHorizontalAlignment(SwingConstants.LEADING);
		btnQLyThanhVien.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnQLyThanhVien.setBorderPainted(false);
		btnQLyThanhVien.setBackground(new Color(226, 255, 153));
		btnQLyThanhVien.setBounds(0, 462, 238, 75);
		panel_Left.add(btnQLyThanhVien);

		cardPanel = new JPanel(cardLayout);
		cardPanel.setBounds(237, 65, 849, 548);
		contentPane.add(cardPanel);
		// cardPanel.setLayout(new CardLayout(0, 0));

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

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(82, 148, 195, 75);
		panelHome.add(lblNewLabel_1);

		JPanel panelPhieuMuon = new JPanel();
		panelPhieuMuon.setBackground(new Color(255, 255, 255));
		cardPanel.add(panelPhieuMuon, "panelHoaDon");
		panelPhieuMuon.setLayout(null);

		JPanel jPanel_PhieuMuon = new JPanel();
		jPanel_PhieuMuon.setBounds(71, 37, 658, 384);
		panelPhieuMuon.add(jPanel_PhieuMuon);
		jPanel_PhieuMuon.setLayout(null);

		JLabel jLabel_TenPhieuMuon = new JLabel("Thông tin khách hàng");
		jLabel_TenPhieuMuon.setFont(new Font("Tahoma", Font.BOLD, 22));
		jLabel_TenPhieuMuon.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel_TenPhieuMuon.setBounds(169, 21, 323, 42);
		jPanel_PhieuMuon.add(jLabel_TenPhieuMuon);

		JLabel jLabel_DiaChi = new JLabel("Địa chỉ:");
		jLabel_DiaChi.setBounds(27, 193, 64, 14);
		jPanel_PhieuMuon.add(jLabel_DiaChi);

		JLabel jLabel_SDT = new JLabel("SĐT:");
		jLabel_SDT.setBounds(27, 93, 64, 14);
		jPanel_PhieuMuon.add(jLabel_SDT);

		JLabel jLabel_HoTen = new JLabel("Họ và tên:");
		jLabel_HoTen.setBounds(27, 143, 64, 14);
		jPanel_PhieuMuon.add(jLabel_HoTen);

		JLabel jLabel_SoLuong = new JLabel("Số lượng:");
		jLabel_SoLuong.setBounds(27, 290, 89, 14);
		jPanel_PhieuMuon.add(jLabel_SoLuong);

		JLabel jLabel_PhiMuon_1 = new JLabel("Phí mượn:");
		jLabel_PhiMuon_1.setBounds(335, 196, 89, 14);
		jPanel_PhieuMuon.add(jLabel_PhiMuon_1);

		JLabel jLabel_TenSach = new JLabel("Tên sách:");
		jLabel_TenSach.setBounds(27, 237, 64, 14);
		jPanel_PhieuMuon.add(jLabel_TenSach);

		JLabel jLabel_ThoiGianMuon = new JLabel("Thời gian mượn:");
		jLabel_ThoiGianMuon.setBounds(335, 96, 89, 14);
		jPanel_PhieuMuon.add(jLabel_ThoiGianMuon);

		JLabel jLabel_NgayTra = new JLabel("Ngày trả:");
		jLabel_NgayTra.setBounds(335, 143, 89, 14);
		jPanel_PhieuMuon.add(jLabel_NgayTra);

		JLabel jLabel_PhiCoc_1 = new JLabel("Phí cọc:");
		jLabel_PhiCoc_1.setBounds(335, 237, 89, 14);
		jPanel_PhieuMuon.add(jLabel_PhiCoc_1);

		JLabel jLabel_TinhTongTien = new JLabel("  Tổng tiền của bạn:");
		jLabel_TinhTongTien.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabel_TinhTongTien.setOpaque(true);
		jLabel_TinhTongTien.setBackground(Color.WHITE);
		jLabel_TinhTongTien.setBounds(335, 291, 260, 36);
		jPanel_PhieuMuon.add(jLabel_TinhTongTien);

		JTextField textField_SDT = new JTextField();
		textField_SDT.setColumns(10);
		textField_SDT.setBounds(115, 90, 160, 20);
		jPanel_PhieuMuon.add(textField_SDT);

		JTextField textField_HoVaTen = new JTextField();
		textField_HoVaTen.setColumns(10);
		textField_HoVaTen.setBounds(115, 140, 160, 20);
		jPanel_PhieuMuon.add(textField_HoVaTen);

		JTextField textField_DiaChi = new JTextField();
		textField_DiaChi.setColumns(10);
		textField_DiaChi.setBounds(115, 190, 160, 20);
		jPanel_PhieuMuon.add(textField_DiaChi);

		JTextField textField_TenSach = new JTextField();
		textField_TenSach.setColumns(10);
		textField_TenSach.setBounds(115, 234, 160, 20);
		jPanel_PhieuMuon.add(textField_TenSach);

		JTextField textField_SoLuong = new JTextField();
		textField_SoLuong.setColumns(10);
		textField_SoLuong.setBounds(115, 287, 160, 20);
		jPanel_PhieuMuon.add(textField_SoLuong);

		JTextField textField_ThoiGianMuon = new JTextField();
		textField_ThoiGianMuon.setColumns(10);
		textField_ThoiGianMuon.setBounds(435, 93, 160, 20);
		jPanel_PhieuMuon.add(textField_ThoiGianMuon);

		JTextField textField_NgayTra = new JTextField();
		textField_NgayTra.setColumns(10);
		textField_NgayTra.setBounds(435, 140, 160, 20);
		jPanel_PhieuMuon.add(textField_NgayTra);

		JLabel jLabel_PhiMuon = new JLabel("aaaaaaaaaaa");
		jLabel_PhiMuon.setBackground(new Color(240, 240, 240));
		jLabel_PhiMuon.setOpaque(true);
		jLabel_PhiMuon.setBounds(434, 190, 161, 20);
		jPanel_PhieuMuon.add(jLabel_PhiMuon);

		JLabel jLabel_PhiCoc = new JLabel("bbbbbbbbbb");
		jLabel_PhiCoc.setBounds(434, 237, 160, 14);
		jPanel_PhieuMuon.add(jLabel_PhiCoc);

		JButton jButton_InHoaDon = new JButton("In phiếu");
		jButton_InHoaDon.setBounds(156, 452, 97, 39);
		panelPhieuMuon.add(jButton_InHoaDon);

		JButton jButton_HuyThaoTac = new JButton("Huỷ thao tác");
		jButton_HuyThaoTac.setBounds(340, 452, 97, 39);
		panelPhieuMuon.add(jButton_HuyThaoTac);

		JButton jButton_ThanhVien = new JButton("Thành viên");
		jButton_ThanhVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		jButton_ThanhVien.setBounds(528, 452, 153, 39);
		panelPhieuMuon.add(jButton_ThanhVien);

		JPanel panelQLPhieuMuon = new JPanel();
		panelQLPhieuMuon.setBackground(new Color(255, 255, 255));
		cardPanel.add(panelQLPhieuMuon, "panelQuanLySach");
		panelQLPhieuMuon.setLayout(null);

		JScrollPane jTable_HienThongTin = new JScrollPane();
		jTable_HienThongTin.setBounds(10, 122, 829, 415);
		panelQLPhieuMuon.add(jTable_HienThongTin);

		table_1 = new JTable();
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table_1.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null, null, null }, },
				new String[] { "M\u00E3 phi\u1EBFu", "M\u00E3 kh\u00E1ch h\u00E0ng", "S\u0110T",
						"H\u1ECD v\u00E0 t\u00EAn", "T\u00EAn s\u00E1ch", "S\u1ED1 l\u01B0\u1EE3ng",
						"Ng\u00E0y tr\u1EA3", "T\u1ED5ng ti\u1EC1n", "T\u00ECnh tr\u1EA1ng" }));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(86);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(86);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(64);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(78);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(89);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(52);
		table_1.getColumnModel().getColumn(6).setPreferredWidth(69);
		table_1.getColumnModel().getColumn(7).setPreferredWidth(67);
		jTable_HienThongTin.setViewportView(table_1);

		JLabel jLabel_TongKhachChuaTraSach = new JLabel("Số lượng khách chưa trả sách: ");
		jLabel_TongKhachChuaTraSach.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel_TongKhachChuaTraSach.setOpaque(true);
		jLabel_TongKhachChuaTraSach.setFont(new Font("Tahoma", Font.BOLD, 13));
		jLabel_TongKhachChuaTraSach.setBounds(34, 57, 301, 26);
		panelQLPhieuMuon.add(jLabel_TongKhachChuaTraSach);

		JButton btnTimKiemQLNhap_2 = new JButton("");
		btnTimKiemQLNhap_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimKiemQLNhap_2.setOpaque(true);
		btnTimKiemQLNhap_2.setBorderPainted(false);
		btnTimKiemQLNhap_2.setBackground(new Color(226, 255, 153));
		btnTimKiemQLNhap_2.setBounds(470, 11, 45, 45);
		panelQLPhieuMuon.add(btnTimKiemQLNhap_2);
		btnTimKiemQLNhap_2.setIcon(iconTimKiem);

		textField_1 = new JTextField();
		textField_1.setText("  Search");
		textField_1.setForeground(Color.LIGHT_GRAY);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(226, 255, 153));
		textField_1.setBounds(513, 11, 300, 45);
		panelQLPhieuMuon.add(textField_1);

		JPanel panelQuanLySach = new JPanel();
		panelQuanLySach.setBackground(new Color(255, 255, 255));
		cardPanel.add(panelQuanLySach, "panelMuonSach");
		panelQuanLySach.setLayout(null);

		JPanel panelQuanLyNhap = new JPanel();
		panelQuanLyNhap.setBackground(new Color(255, 255, 255));
		cardPanel.add(panelQuanLyNhap, "panelThongKe");
		panelQuanLyNhap.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 123, 829, 415);
		panelQuanLyNhap.add(panel_1);
		panel_1.setLayout(null);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, },
				new String[] { "M\u00E3 l\u00F4", "Ng\u00E0y nh\u1EADp", "Thanh to\u00E1n", "Nh\u00E0 cung c\u1EA5p",
						"\u0110\u1ECBa ch\u1EC9 cung c\u1EA5p", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i" }));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(37);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(63);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(74);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(89);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(101);

		JScrollPane scrollPane_3 = new JScrollPane(table_1);
		scrollPane_3.setBounds(0, 0, 829, 415);
		panel_1.add(scrollPane_3);

		JButton btnSuaQLNhap = new JButton("Sửa");
		btnSuaQLNhap.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSuaQLNhap.setBounds(10, 67, 120, 45);
		panelQuanLyNhap.add(btnSuaQLNhap);

		JButton btnThemQLNhap = new JButton("Thêm");
		btnThemQLNhap.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThemQLNhap.setBounds(200, 67, 120, 45);
		panelQuanLyNhap.add(btnThemQLNhap);

		JButton btnTimKiemQLNhap = new JButton("");
		btnTimKiemQLNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTimKiemQLNhap.setOpaque(true);
		btnTimKiemQLNhap.setBorderPainted(false);
		btnTimKiemQLNhap.setBackground(new Color(226, 255, 153));
		btnTimKiemQLNhap.setBounds(467, 10, 45, 45);
		panelQuanLyNhap.add(btnTimKiemQLNhap);
		btnTimKiemQLNhap.setIcon(newIconTimKiem);

		textFieldTimKiemQLNhap = new JTextField();
		textFieldTimKiemQLNhap.setText("  Search");
		textFieldTimKiemQLNhap.setForeground(Color.LIGHT_GRAY);
		textFieldTimKiemQLNhap.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldTimKiemQLNhap.setColumns(10);
		textFieldTimKiemQLNhap.setBackground(new Color(226, 255, 153));
		textFieldTimKiemQLNhap.setBounds(510, 10, 300, 45);
		panelQuanLyNhap.add(textFieldTimKiemQLNhap);

		JButton btnXemChiTietLo = new JButton("Xem chi tiết lô");
		btnXemChiTietLo.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnXemChiTietLo.setBounds(661, 68, 149, 45);
		panelQuanLyNhap.add(btnXemChiTietLo);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(MainView.class.getResource("/images/background.png")));
		lblNewLabel_2.setBounds(0, 0, 849, 548);
		panelQuanLyNhap.add(lblNewLabel_2);

		///////////////////////////////////////////////////////////////////////////////////////////
		panelThemThanhVien = new JPanel();
		cardPanel.add(panelThemThanhVien, "panelTheThanhVien");
		panelThemThanhVien.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 123, 829, 415);
		panelQuanLySach.add(panel_2);

		tableMuonSach = new JTable();
		tableMuonSach.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null, null }, },
				new String[] { "M\u00E3 HD", "M\u00E3 KH", "H\u1ECD t\u00EAn", "S\u0110T",
						"\u0110\u1ECBa \u0111i\u1EC3m", "S\u00E1ch m\u01B0\u1EE3n", "Ng\u00E0y m\u01B0\u1EE3n",
						"Ng\u00E0y tr\u1EA3", "S\u1ED1 l\u01B0\u1EE3ng", "Ph\u00ED m\u01B0\u1EE3n" }));
		tableMuonSach.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableMuonSach.getColumnModel().getColumn(1).setPreferredWidth(40);
		tableMuonSach.getColumnModel().getColumn(8).setPreferredWidth(51);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(tableMuonSach);
		scrollPane.setBounds(0, 0, 829, 415);
		panel_2.add(scrollPane);

		JButton btnThemQLNhap_1 = new JButton("Thêm");
		btnThemQLNhap_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnThemQLNhap_1.setBounds(200, 67, 120, 45);
		panelQuanLySach.add(btnThemQLNhap_1);

		JButton btnSuaQLNhap_1 = new JButton("Sửa");
		btnSuaQLNhap_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSuaQLNhap_1.setBounds(10, 67, 120, 45);
		panelQuanLySach.add(btnSuaQLNhap_1);

		textField = new JTextField();
		textField.setText("  Search");
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setColumns(10);
		textField.setBackground(new Color(226, 255, 153));
		textField.setBounds(510, 10, 300, 45);
		panelQuanLySach.add(textField);

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

		JPanel panel_TTV = new JPanel();
		panel_TTV.setBackground(new Color(255, 255, 255));
		panel_TTV.setBounds(102, 41, 635, 437);
		panelThemThanhVien.add(panel_TTV);
		panel_TTV.setLayout(null);

		JLabel labelTitle_TTV = new JLabel("THẺ THÀNH VIÊN");
		labelTitle_TTV.setForeground(new Color(0, 0, 0));
		labelTitle_TTV.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle_TTV.setFont(new Font("Times New Roman", Font.BOLD, 25));
		labelTitle_TTV.setBounds(200, 26, 236, 50);
		panel_TTV.add(labelTitle_TTV);

		JLabel label_TenThanhVien_TTV = new JLabel("Tên thành viên");
		label_TenThanhVien_TTV.setBounds(55, 125, 72, 14);
		panel_TTV.add(label_TenThanhVien_TTV);

		JLabel label_NgaySinh_TTV = new JLabel("Ngày sinh");
		label_NgaySinh_TTV.setBounds(55, 180, 72, 14);
		panel_TTV.add(label_NgaySinh_TTV);

		JLabel label_SDT_TTV = new JLabel("Số điện thoại");
		label_SDT_TTV.setBounds(58, 234, 69, 14);
		panel_TTV.add(label_SDT_TTV);

		JLabel label_PhiDangKy_TTV = new JLabel("Phí đăng ký");
		label_PhiDangKy_TTV.setBounds(340, 234, 69, 14);
		panel_TTV.add(label_PhiDangKy_TTV);

		JLabel label_HanThe_TTV = new JLabel("Hạn thẻ");
		label_HanThe_TTV.setBounds(340, 180, 69, 14);
		panel_TTV.add(label_HanThe_TTV);

		JLabel label_DiaChi_TTV = new JLabel("Địa chỉ");
		label_DiaChi_TTV.setBounds(340, 125, 69, 14);
		panel_TTV.add(label_DiaChi_TTV);

		textField_TenDocGia_TTV = new JTextField();
		textField_TenDocGia_TTV.setColumns(10);
		textField_TenDocGia_TTV.setBounds(137, 122, 130, 20);
		panel_TTV.add(textField_TenDocGia_TTV);

		textField_SDT_TTV = new JTextField();
		textField_SDT_TTV.setColumns(10);
		textField_SDT_TTV.setBounds(137, 231, 130, 20);
		panel_TTV.add(textField_SDT_TTV);

		textField_Diachi_TTV = new JTextField();
		textField_Diachi_TTV.setColumns(10);
		textField_Diachi_TTV.setBounds(430, 122, 130, 20);
		panel_TTV.add(textField_Diachi_TTV);

		jLabel_PhiDangKy_TTV = new JLabel();
		jLabel_PhiDangKy_TTV.setBackground(new Color(230, 230, 250));
		jLabel_PhiDangKy_TTV.setOpaque(true);
		jLabel_PhiDangKy_TTV.setBounds(430, 231, 130, 20);
		panel_TTV.add(jLabel_PhiDangKy_TTV);

		chooser_NgaySinh_TTV = new JDateChooser();
		chooser_NgaySinh_TTV.setDateFormatString("dd/MM/yyyy");
		chooser_NgaySinh_TTV.getJCalendar().setMaxSelectableDate(new java.util.Date());
		chooser_NgaySinh_TTV.setBounds(137, 178, 130, 20);
		panel_TTV.add(chooser_NgaySinh_TTV);

		JButton button_Luu_TTV = new JButton("Lưu");
		button_Luu_TTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themThanhVien();
			}
		});
		button_Luu_TTV.setFont(new Font("Times New Roman", Font.BOLD, 15));
		button_Luu_TTV.setBounds(175, 363, 109, 33);
		panel_TTV.add(button_Luu_TTV);

		JButton button_HuyThaoTac_TTV = new JButton("Huỷ thao tác");
		button_HuyThaoTac_TTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				huyThemThanhVien();
			}
		});
		button_HuyThaoTac_TTV.setFont(new Font("Times New Roman", Font.BOLD, 15));
		button_HuyThaoTac_TTV.setBounds(340, 363, 115, 33);
		panel_TTV.add(button_HuyThaoTac_TTV);

		JLabel label_GhiChu_TTV = new JLabel("* Vui lòng điền đầy đủ thông tin trước khi nhấn lưu");
		label_GhiChu_TTV.setForeground(new Color(128, 128, 128));
		label_GhiChu_TTV.setBackground(new Color(255, 255, 255));
		label_GhiChu_TTV.setHorizontalAlignment(SwingConstants.CENTER);
		label_GhiChu_TTV.setFont(new Font("Tahoma", Font.ITALIC, 12));
		label_GhiChu_TTV.setBounds(168, 319, 318, 14);
		panel_TTV.add(label_GhiChu_TTV);

		comboBox_HanThe_TTV = new JComboBox();
		comboBox_HanThe_TTV.setBackground(new Color(230, 230, 250));
		comboBox_HanThe_TTV.setModel(new DefaultComboBoxModel(
				new String[] { "Chọn hạn thẻ", "12 tháng", "24 tháng", "36 tháng", "48 tháng" }));
		comboBox_HanThe_TTV.setBounds(430, 176, 130, 22);
		comboBox_HanThe_TTV.setOpaque(true);
		comboBox_HanThe_TTV.setBackground(Color.BLUE);
		comboBox_HanThe_TTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tinhToanPhiDangKy();
			}
		});
		panel_TTV.add(comboBox_HanThe_TTV);

		JLabel label_TTV = new JLabel("New label");
		label_TTV.setIcon(new ImageIcon(MainView.class.getResource("/images/background.png")));
		label_TTV.setBounds(0, 0, 849, 548);
		panelThemThanhVien.add(label_TTV);

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
		btnTimKiem_QLTV.setBackground(new Color(0xE2FF99));
		btnTimKiem_QLTV.setOpaque(true);
		btnTimKiem_QLTV.setBorderPainted(false);
		btnTimKiem_QLTV.setBounds(467, 10, 45, 45);
		panelQlyDocGia.add(btnTimKiem_QLTV);
		btnTimKiem_QLTV.setIcon(newIconTimKiem);

		JPanel panel_QLTV = new JPanel();
		panel_QLTV.setBounds(10, 114, 829, 423);
		panelQlyDocGia.add(panel_QLTV);
		panel_QLTV.setLayout(null);

		JScrollPane scrollPane_QLTV = new JScrollPane();
		scrollPane_QLTV.setBounds(0, 0, 829, 423);
		panel_QLTV.add(scrollPane_QLTV);

		table_QLTV = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_QLTV.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		table_QLTV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table_QLTV.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 TV", "H\u1ECD v\u00E0 t\u00EAn", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i",
						"Ng\u00E0y sinh", "\u0110\u1ECBa ch\u1EC9", "TG \u0111\u0103ng k\u00FD", "H\u1EA1n th\u1EBB",
						"Ph\u00ED \u0111\u0103ng k\u00FD", "T\u00ECnh Tr\u1EA1ng" }));
		table_QLTV.getColumnModel().getColumn(0).setPreferredWidth(39);
		table_QLTV.getColumnModel().getColumn(1).setPreferredWidth(98);
		table_QLTV.getColumnModel().getColumn(2).setPreferredWidth(74);
		table_QLTV.getColumnModel().getColumn(3).setPreferredWidth(67);
		table_QLTV.getColumnModel().getColumn(4).setPreferredWidth(122);
		table_QLTV.getColumnModel().getColumn(5).setPreferredWidth(73);
		table_QLTV.getColumnModel().getColumn(6).setPreferredWidth(69);
		table_QLTV.getColumnModel().getColumn(7).setPreferredWidth(66);
		table_QLTV.getColumnModel().getColumn(8).setPreferredWidth(61);
		scrollPane_QLTV.setViewportView(table_QLTV);

		// Lấy mã độc giả của hàng được chọn khi nhấn chuột vào bảng
		table_QLTV.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table_QLTV.getSelectedRow(); // lấy chỉ số của hàng được chọn trong table.
				if (row >= 0) { // Đảm bảo là có hàng được chọn
					Object value = table_QLTV.getValueAt(row, 0);
					MaDocGia_QLTV = ((Integer) value).intValue();
				}
			}
		});

		// Tạo popupMenu khi click chuộc phải vào hàng table
		setupPopupMenu_QLTV(table_QLTV, frame);

		ThanhVien.getInstance().selectAll(table_QLTV);

		// Tạo đối tượng TableRowSorter để lọc dữ liệu trong bảng
		TableRowSorter<TableModel> sorter_QLTV = new TableRowSorter<>(table_QLTV.getModel());

		// Đặt TableRowSorter cho bảng
		table_QLTV.setRowSorter(sorter_QLTV);

		// Tạo sự kiện KeyReleased cho textField_TimKiem_QLTV
		textField_TimKiem_QLTV.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String input = textField_TimKiem_QLTV.getText().trim(); // Lấy dữ liệu từ JTextField
				if (input.length() == 0) {
					// Nếu JTextField rỗng, hiển thị tất cả dữ liệu
					sorter_QLTV.setRowFilter(null);
				} else {
					// Lọc dữ liệu theo nội dung JTextField
					sorter_QLTV.setRowFilter(RowFilter.regexFilter("(?i)" + input));
				}
			}
		});
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
		
		// Date now
		long millis = System.currentTimeMillis();
		java.sql.Date dateNow = new java.sql.Date(millis);

		// Format Chosser về dd/MM/yyy để kiểm tra tính đúng đắn của dữ liệu
		SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
		String formattedD = d.format(chooser_NgaySinh_TTV.getDate());
		System.out.println(formattedD);

		// Biến dùng kiểm tra tính đúng đắn của dữ liệu
		Pattern patternDate = Pattern.compile("^\\d{2}[-|/]\\d{2}[-|/]\\d{4}$");
		Pattern patternSDT = Pattern.compile("^0[3798]{1}\\d{8}$");

		// Kiểm tra thông tin có đủ không
		if (textField_TenDocGia_TTV.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_SDT_TTV.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (chooser_NgaySinh_TTV.getDate() == null) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (patternDate.matcher(formattedD).matches() == false) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đúng định dạng ngày sinh!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (patternSDT.matcher(textField_SDT_TTV.getText()).matches() == false) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đúng định dạng số điện thoại!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_Diachi_TTV.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (comboBox_HanThe_TTV.getSelectedItem().equals("Chọn hạn thẻ")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng chọn hạn thẻ!!!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);

		} else {

			// Lấy dữ liệu nhập
			docGia.setTenDocGia(textField_TenDocGia_TTV.getText());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = sdf.format(chooser_NgaySinh_TTV.getDate());
			Date date1 = Date.valueOf(formattedDate);
			docGia.setNgaySinh(date1);

			docGia.setSDT(textField_SDT_TTV.getText());

			docGia.setDiaChi(textField_Diachi_TTV.getText());

			theThanhVien.setNgayDangKy(dateNow);

			// Nhập hạn thẻ
			Calendar c = Calendar.getInstance();

			if (comboBox_HanThe_TTV.getSelectedItem().equals("12 tháng")) {
				c.add(Calendar.YEAR, 1);
				long millis_1Y = TimeUnit.DAYS.toMillis(365);
				Date date3 = new Date(millis + millis_1Y);
				theThanhVien.setHanThe(date3);

			} else if (comboBox_HanThe_TTV.getSelectedItem().equals("24 tháng")) {
				c.add(Calendar.YEAR, 2);
				long millis_2Y = TimeUnit.DAYS.toMillis(365 * 2);
				Date date3 = new Date(millis + millis_2Y);
				theThanhVien.setHanThe(date3);

			} else if (comboBox_HanThe_TTV.getSelectedItem().equals("36 tháng")) {
				c.add(Calendar.YEAR, 3);
				long millis_3Y = TimeUnit.DAYS.toMillis(365 * 3);
				Date date3 = new Date(millis + millis_3Y);
				theThanhVien.setHanThe(date3);

			} else if (comboBox_HanThe_TTV.getSelectedItem().equals("48 tháng")) {
				c.add(Calendar.YEAR, 4);
				long millis_4Y = TimeUnit.DAYS.toMillis(365 * 4);
				Date date3 = new Date(millis + millis_4Y);
				theThanhVien.setHanThe(date3);
			}

			theThanhVien.setPhiDangKy(Integer.valueOf(jLabel_PhiDangKy_TTV.getText()));

			new Dialog_ThemThanhVien(this, docGia, theThanhVien); // hien thi dialog

			// Xoá dữ liệu nhập trên màn hình
			huyThemThanhVien();
		}
	}

	public void tinhToanPhiDangKy() {
		if (comboBox_HanThe_TTV.getSelectedItem().equals("Chọn hạn thẻ")) {
			jLabel_PhiDangKy_TTV.setText("");
		} else if (comboBox_HanThe_TTV.getSelectedItem().equals("12 tháng")) {
			jLabel_PhiDangKy_TTV.setText("100000");
		} else if (comboBox_HanThe_TTV.getSelectedItem().equals("24 tháng")) {
			jLabel_PhiDangKy_TTV.setText("200000");
		} else if (comboBox_HanThe_TTV.getSelectedItem().equals("36 tháng")) {
			jLabel_PhiDangKy_TTV.setText("300000");
		} else if (comboBox_HanThe_TTV.getSelectedItem().equals("48 tháng")) {
			jLabel_PhiDangKy_TTV.setText("400000");
		}
	}

	public void huyThemThanhVien() {
		textField_TenDocGia_TTV.setText("");
		textField_SDT_TTV.setText("");
		textField_Diachi_TTV.setText("");
		jLabel_PhiDangKy_TTV.setText("");
		chooser_NgaySinh_TTV.setDate(null);
		comboBox_HanThe_TTV.setSelectedIndex(0);
	}

	// Hàm set PopupMenu khi chuộc phải vào hàng trong table
	private void setupPopupMenu_QLTV(JTable table, JFrame jFrame) {
		table.addMouseListener(new MouseAdapter() {
			// sự kiện click chuột
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getButton() == MouseEvent.BUTTON3) { // kiểm tra chuột phải
					int row = table.rowAtPoint(e.getPoint()); // lấy chỉ số dòng được nhấn chuột
					table.setRowSelectionInterval(row, row); // chọn dòng được nhấn chuột

					// Tạo menu chuột phải
					JPopupMenu popupMenu = new JPopupMenu();
					popupMenu.setBackground(Color.BLUE);

					// tạo jmenuItem chuột phải
					JMenuItem menuItemSuaThongTin_QLTV = new JMenuItem("Sửa thông tin thành viên");
					menuItemSuaThongTin_QLTV.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							new Dialog_SuaThongTinThanhVien(jFrame, row + 1);
						}
					});

					JMenuItem menuItemGiaHanThe_QLTV = new JMenuItem("Gia hạn thẻ thành viên");
					menuItemGiaHanThe_QLTV.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							new Dialog_GiaHanTheThanhVien(jFrame, row + 1);
						}
					});

					popupMenu.add(menuItemSuaThongTin_QLTV);
					popupMenu.add(menuItemGiaHanThe_QLTV);
					popupMenu.show(table, e.getX(), e.getY()); // hiển thị menu
				}
			}
		});
	}

}
