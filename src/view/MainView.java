package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
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
import com.toedter.calendar.JDateChooser;

import Paging.Pagination;
import database.QuanLyNhapLo;
import database.Service23;
import database.ThanhVien;
import model.DocGia;
import model.TheThanhVien;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import database.QuanLySach;

public class MainView extends JFrame {
	JFrame frame = new JFrame();
	private CardLayout cardLayout;
	private JPanel panel_Left;
	public static ArrayList<Integer> arrIDSACH = new ArrayList<>();
	public static JTable table_QuanLyPhieuMuon;

	public static JTable table_QLTV;
	private JTextField textField_TimKiem_QLTV;
	private JTextField textFieldTimKiemQLNhap;
	private JTextField textField_TenDocGia_TTV;
	private JTextField textField_SDT_TTV;
	private JTextField textField_Diachi_TTV;
	private JLabel jLabel_PhiDangKy_TTV;
	private int MaDocGia_QLTV;
	private JComboBox comboBox_HanThe_TTV;
	private JDateChooser chooser_NgaySinh_TTV;

	private JPanel panel_Top;
	private JPanel panel_pm;
	private JPanel contentPane;
	private JPanel cardPanel;
	private JPanel panelTheDocGia;

	private JTextField textField_TimKiem_qlnl;
	private JTextField textField_Search;
	private JTextField textField_TimKiem_qlpm;
	private JTextField textField_TimKiem_qldg;
	private JTextField textField_TimKiem_pm;

	public static JTable table_pm;
	private JTable table_QuanLyDocGia;
	public static JTable table_DocGia_pm;
	private JTable table_QuanLySach;
	private JTable table_QuanLyNhapLo;

	public JLabel lbl_TenNhanVien = new JLabel();
	private JLabel lbl_HovaTen_pm;
	private JLabel lbl_SDT_pm;
	private JLabel lbl_DiaChi_pm;
	private JLabel lbl_Time_pm;
	private JLabel lbl_NgayMuon_pm;
	private JLabel lbl_MaThe_pm;
	private JLabel lbl_MaNV_1_ThemLo = new JLabel();
	private JLabel lbl_MaNhanVien = new JLabel();
	private JButton lastClicked;
	public JButton btn_TheDocGia_left;
	public JButton btn_Library_top;
	public JButton btn_PhieuMuon_left;
	public JButton btn_QLPhieuMuon_left;
	public JButton btn_QuanLySach_left;
	public JButton btn_QuanLyNhapLo_left;
	public JButton btn_QuanLyDocGia_left;

	private JButton btn_Them_QlNhapLo;
	private JButton btn_XemChiTiet_QLNhapLo;

	private java.sql.Date ngayTra;
	private java.sql.Date ngayMuon;

	private int MaPM;
	private int maTheDocGia;
	private int SlHangTablePm = 0;

	private JComboBox<Integer> comboBox_NgayTra_pm;
	private SimpleDateFormat formatter;

	private ImageIcon newIconTimKiem = getScaledIcon("/images/search.png", 25, 25);;
	private int idSelectedPhieuNhapLo = 0;
	private JPanel panelThemDocGia;
	private JTable tableMuonSach;
	private JTextField textField;

	private JPanel panel_table_qlnl;
	private JScrollPane scrollPane_qlnl;
	JPanel panelQuanLyNhapLo;
	JPanel panelQuanLySach;
	public int MaNV;
	public JPanel panelQLPhieuMuon;
	// ******************************
	// Paging cho table quản Lý SÁch
	JButton[] buttonsTable_QLSach = new JButton[9]; // tạo 9 button
	final int PAGE_SIZE_Table_QLSach = 25; // kích thước mỗi trang muốn hiện
	double tableRowCountTable_QLSach;
	int totalPagesTable_QLSach; // Total có tổng số bao nhiêu trang 
	int currentPageTable_QLSach; // trang hiện tại đang hiện
	int startRowTable_QLSach; // dòng lấy bắt đầu
	int numbersTable_QLSach[]; // lưu số trang
	JPanel pagingPanelTable_QLSach;
	JPanel	pagingPanel_QLDG;
	// ******************************
	// Paging cho table quản Lý SÁch

	// ******************************
	// Paging cho table quan ly nhap lo
	JButton[] buttonsTable_QLNL = new JButton[9]; // tạo 9 button
	final int PAGE_SIZE_Table_QLNL = 7; // kích thước mỗi trang muốn hiện
	double tableRowCount;
	int totalPagesTable_QLNL; //Total có tổng số bao nhiêu trang 
	int currentPageTable_QLNL; // trang hiện tại đang hiện
	int startRowTable_QLNL; // dòng lấy bắt đầu
	int numbersTable_QLNL[]; // lưu số trang
	public JPanel pagingPanel = null;
	// ******************************
	
	// Paging cho table quan ly phieu muon
	
		JButton[] buttonsTable_QLPM = new JButton[9]; // tạo 9 button
		final int PAGE_SIZE_Table_QLPM = 10; // kích thước mỗi trang muốn hiện
		double tableRowCountTable_QLPM;
		int totalPagesTable_QLPM; // Total có tổng số bao nhiêu trang 
		int currentPageTable_QLPM; // trang hiện tại đang hiện
		int startRowTable_QLPM; // dòng lấy bắt đầu
		int numbersTable_QLPM[]; // lưu số trang
		JPanel pagingPanelTable_QLPM = null;
		// ******************************
		
		// Paging cho table quản Lý doc gia
		JButton[] buttonsTable_QLDG = new JButton[9]; // tạo 9 button
		final int PAGE_SIZE_Table_QLDG = 4; // kích thước mỗi trang muốn hiện
		double tableRowCountTable_QLDG;
		int totalPagesTable_QLDG; // Total có tổng số bao nhiêu trang 
		int currentPageTable_QLDG; // trang hiện tại đang hiện
		int startRowTable_QLDG; // dòng lấy bắt đầu
		int numbersTable_QLDG[]; // lưu số trang
		JPanel pagingPanelTable_QLDG;
		private Container panelQlyDocGia;
		// ******************************
	/**
	 * Launch the application.
	 */

	public MainView(String hotendn, String chucVu, int manv) {
		System.out.println(chucVu);
		System.out.println(manv);
		setResizable(false);

		this.KhoiTaoButtonLeft();
		check_quyen(chucVu);
		this.init();

		this.setLocationRelativeTo(null);
		this.setVisible(false);
		MaNV = manv;

		lbl_TenNhanVien.setText(hotendn);
		lbl_MaNhanVien.setText(""+MaNV);;
		
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

		// Formate Date
		Date date = new Date();
		formatter = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = formatter.format(date);

		panel_Top = new JPanel();
		panel_Top.setBorder(new MatteBorder(1, 1, 5, 1, (Color) new Color(0, 0, 0)));
		panel_Top.setBackground(new Color(0xE2FF99));
		panel_Top.setBounds(0, 0, 1086, 66);
		contentPane.add(panel_Top);
		panel_Top.setLayout(null);

		// Khởi tạo và set các icon
		ImageIcon newIconLibrary = getScaledIcon("/images/books.png", 58, 58);
		ImageIcon newIconTimKiem = getScaledIcon("/images/search.png", 25, 25);
		ImageIcon newIconUser = getScaledIcon("/images/exit.png", 29, 29);
		ImageIcon newIconBill = getScaledIcon("/images/bill.png", 20, 20);
		ImageIcon newIconMnBooks = getScaledIcon("/images/mnbooks.png", 20, 20);
		ImageIcon newIconExChange = getScaledIcon("/images/exchange.png", 20, 20);
		ImageIcon newIconTrend = getScaledIcon("/images/trend.png", 20, 20);
		ImageIcon newIconMember = getScaledIcon("/images/member.png", 20, 20);
		ImageIcon newIconReading = getScaledIcon("/images/reading.png", 20, 20);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(MainView.class.getResource("/images/background.png")));
		lblNewLabel_1.setBounds(0, 0, 849, 548);

		btn_Library_top = new JButton("");
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
		btn_Library_top.setIcon(newIconLibrary);

		JLabel lbl_Namelibrary_top = new JLabel("QUẢN LÝ THƯ VIỆN ABC");
		lbl_Namelibrary_top.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbl_Namelibrary_top.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Namelibrary_top.setBounds(105, 10, 336, 46);
		panel_Top.add(lbl_Namelibrary_top);

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

		panel_Left = new JPanel();
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

		// btn_PhieuMuon_left = new JButton("PHIẾU MƯỢN");
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

		// btn_QLPhieuMuon_left = new JButton("QLý PHIẾU MƯỢN");
		btn_QLPhieuMuon_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lấy dữ liệu dưới sql lên table
				Service23.getInstance().UpdateTinhTrangPhieuMuonQuahan();

				// Cập nhật bảng Quản lý phiếu mượn
				((DefaultTableModel) MainView.table_QuanLyPhieuMuon.getModel()).setRowCount(0);
				//Service23.getInstance().SelectAllPhieuMuon(table_QuanLyPhieuMuon);
				getPageDataTable_QLPM(1);

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

		// btn_QuanLySach_left = new JButton("QUẢN LÝ SÁCH");
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

		// btn_QuanLyNhapLo_left = new JButton("QLý NHẬP LÔ");
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

		// btn_TheDocGia_left = new JButton(" THẺ ĐỘC GIẢ");
		btn_TheDocGia_left.setHorizontalAlignment(SwingConstants.LEADING);
		btn_TheDocGia_left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtonColor(btn_TheDocGia_left);
				cardLayout.show(cardPanel, "panelThemDocGia");
			}
		});
		btn_TheDocGia_left.setOpaque(true);
		btn_TheDocGia_left.setBorderPainted(false);
		btn_TheDocGia_left.setBackground(new Color(0xE2FF99));
		btn_TheDocGia_left.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_TheDocGia_left.setBounds(0, 375, 238, 75);
		panel_Left.add(btn_TheDocGia_left);
		btn_TheDocGia_left.setIcon(newIconMember);

		// btn_QuanLyDocGia_left = new JButton("QLý ĐỘC GIẢ");
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

		JPanel panelHome = new JPanel();
		panelHome.setBackground(new Color(255, 255, 255));
		cardPanel.add(panelHome, "panelHome");
		panelHome.setLayout(null);

		JLabel lbl_XinChao_home = new JLabel("XIN CHÀO!");
		lbl_XinChao_home.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_XinChao_home.setFont(new Font("Tahoma", Font.BOLD, 70));
		lbl_XinChao_home.setBounds(10, 10, 829, 172);
		panelHome.add(lbl_XinChao_home);
		panelHome.add(lblNewLabel_1);

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

		lbl_Time_pm = new JLabel("");
		lbl_Time_pm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_Time_pm.setBounds(330, 54, 120, 20);
		panel_pm.add(lbl_Time_pm);
		TimeNow(lbl_Time_pm);

		lbl_TenNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_TenNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_TenNhanVien.setBounds(131, 54, 131, 20);
		panel_pm.add(lbl_TenNhanVien);
		
		lbl_MaNhanVien.setText("<dynamic>");
		lbl_MaNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_MaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_MaNhanVien.setBounds(116, 54, 131, 20);
		panel_pm.add(lbl_MaNhanVien);
		
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

		lbl_SDT_pm = new JLabel("");
		lbl_SDT_pm.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_SDT_pm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_SDT_pm.setBackground(Color.WHITE);
		lbl_SDT_pm.setBounds(180, 140, 270, 20);
		panel_pm.add(lbl_SDT_pm);

		lbl_HovaTen_pm = new JLabel("");
		lbl_HovaTen_pm.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_HovaTen_pm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_HovaTen_pm.setBackground(Color.WHITE);
		lbl_HovaTen_pm.setBounds(180, 170, 270, 20);
		panel_pm.add(lbl_HovaTen_pm);

		lbl_DiaChi_pm = new JLabel("");
		lbl_DiaChi_pm.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_DiaChi_pm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_DiaChi_pm.setBackground(Color.WHITE);
		lbl_DiaChi_pm.setBounds(180, 200, 270, 20);
		panel_pm.add(lbl_DiaChi_pm);

		lbl_NgayMuon_pm = new JLabel("" + formattedDate);
		lbl_NgayMuon_pm.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_NgayMuon_pm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_NgayMuon_pm.setBackground(Color.WHITE);
		lbl_NgayMuon_pm.setBounds(142, 230, 120, 20);
		panel_pm.add(lbl_NgayMuon_pm);

		// Gán Biến ngày mượn = thời gian hiện tại
		Date now = new Date();
		ngayMuon = new java.sql.Date(now.getTime());
		System.err.println(ngayMuon);

		comboBox_NgayTra_pm = new JComboBox<>();
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
						ComboBoxNgayTra(1);
					} else if (selectedItem.equals("3 tháng")) {
						ComboBoxNgayTra(3);
					} else if (selectedItem.equals("5 tháng")) {
						ComboBoxNgayTra(5);
					} else if (selectedItem.equals("7 tháng")) {
						ComboBoxNgayTra(7);
					} else if (selectedItem.equals("10 tháng")) {
						ComboBoxNgayTra(10);
					} else if (selectedItem.equals("12 tháng")) {
						ComboBoxNgayTra(12);
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
		table_pm.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table_pm.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 s\u00E1ch", "T\u00EAn s\u00E1ch", "T\u00E1c gi\u1EA3" }));
		table_pm.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_pm.getColumnModel().getColumn(1).setPreferredWidth(150);
		table_pm.getColumnModel().getColumn(2).setPreferredWidth(200);

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
		table_DocGia_pm.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table_DocGia_pm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_DocGia_pm.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 Th\u1EBB", "H\u1ECD t\u00EAn", "S\u0110T", "\u0110\u1ECBa ch\u1EC9" }));
		table_DocGia_pm.getColumnModel().getColumn(0).setPreferredWidth(100);
		table_DocGia_pm.getColumnModel().getColumn(1).setPreferredWidth(440);
		table_DocGia_pm.getColumnModel().getColumn(2).setPreferredWidth(360);
		table_DocGia_pm.getColumnModel().getColumn(3).setPreferredWidth(250);
	
		JScrollPane scrollPane_pm2 = new JScrollPane(table_DocGia_pm);
		scrollPane_pm2.setBounds(0, 0, 299, 238);
		panel_3.add(scrollPane_pm2);

		// Lấy dữ liệu dưới sql lên table
		Service23.getInstance().SelectAllTheDocGia(table_DocGia_pm);

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

		// Xử lý sự kiện tìm kiếm cho table
		SearchTable(table_DocGia_pm, textField_TimKiem_pm);

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
				ActionBtn_XoaSach_Pm();
			}
		});
		btn_XoaSach_pm.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_XoaSach_pm.setBounds(520, 481, 111, 45);
		panelPhieuMuon.add(btn_XoaSach_pm);

		JButton btn_Huy_pm = new JButton("Hủy");
		btn_Huy_pm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionBtn_Huy_Pm();
			}
		});
		btn_Huy_pm.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_Huy_pm.setBounds(728, 388, 111, 45);
		panelPhieuMuon.add(btn_Huy_pm);

		JButton btn_InPhieu_pm = new JButton("In phiếu");
		btn_InPhieu_pm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionBtn_InPhieu_Pm();
			}
		});

		btn_InPhieu_pm.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_InPhieu_pm.setBounds(728, 481, 111, 45);
		panelPhieuMuon.add(btn_InPhieu_pm);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(MainView.class.getResource("/images/background.png")));
		lblNewLabel.setBounds(0, 0, 849, 548);
		panelPhieuMuon.add(lblNewLabel);

		panelQLPhieuMuon = new JPanel();
		panelQLPhieuMuon.setBackground(new Color(255, 255, 255));
		cardPanel.add(panelQLPhieuMuon, "panelQuanLyPhieuMuon");
		panelQLPhieuMuon.setLayout(null);

		JScrollPane scrollPane_qlpm = new JScrollPane();
		scrollPane_qlpm.setBounds(10, 81, 829, 441);
		panelQLPhieuMuon.add(scrollPane_qlpm);

		textField_TimKiem_qlpm = new JTextField();
		textField_TimKiem_qlpm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					getPageDataTable_QLPM(1);
				}
			}
		});
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
		table_QuanLyPhieuMuon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table_QuanLyPhieuMuon.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 PM", "M\u00E3 th\u1EBB", "H\u1ECD t\u00EAn", "S\u0110T",
						"Ng\u00E0y m\u01B0\u1EE3n", "H\u1EA1n tr\u1EA3", "T\u00ECnh tr\u1EA1ng" })
		// ngăn chặn chỉnh sửa giá trịƠ
		{

			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1 || column == 2 || column == 3 || column == 4 || column == 5
						|| column == 6)
					return false;
				return super.isCellEditable(row, column);
			}

		});
		table_QuanLyPhieuMuon.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_QuanLyPhieuMuon.getColumnModel().getColumn(1).setPreferredWidth(50);
		table_QuanLyPhieuMuon.getColumnModel().getColumn(2).setPreferredWidth(90);
		table_QuanLyPhieuMuon.getColumnModel().getColumn(3).setPreferredWidth(90);
		table_QuanLyPhieuMuon.getColumnModel().getColumn(4).setPreferredWidth(90);
		scrollPane_qlpm.setViewportView(table_QuanLyPhieuMuon);

		table_QuanLyPhieuMuon.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int row = table_QuanLyPhieuMuon.getSelectedRow(); // lấy chỉ số của hàng được chọn trong table.
				if (row >= 0) { // Đảm bảo là có hàng được chọn
					Object value = table_QuanLyPhieuMuon.getValueAt(row, 0);
					MaPM = ((Integer) value).intValue();
				}
			}
		});

		// Lấy dữ liệu Quảng lý phiếu mượn từ sql lên table
	//	Service23.getInstance().SelectAllPhieuMuon(table_QuanLyPhieuMuon);

		// Sự kiện Tìm kiếm trong bảng quản lý phiếu mượn
		//SearchTable(table_QuanLyPhieuMuon, textField_TimKiem_qlpm);

		JButton btn_TimKiem_qlpm = new JButton("");
		btn_TimKiem_qlpm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPageDataTable_QLPM(1);
			}
		});
		btn_TimKiem_qlpm.setOpaque(true);
		btn_TimKiem_qlpm.setBorderPainted(false);
		btn_TimKiem_qlpm.setBackground(new Color(226, 255, 153));
		btn_TimKiem_qlpm.setBounds(470, 11, 45, 45);
		panelQLPhieuMuon.add(btn_TimKiem_qlpm);
		btn_TimKiem_qlpm.setIcon(newIconTimKiem);

		// Tạo popupMenu khi click chuộc phải vào hàng table
		setupPopupMenu(table_QuanLyPhieuMuon, frame);

		
		// init phân trang sách
		currentPageTable_QLPM = 1;
		getCountTable_QLPM();
		getPageDataTable_QLPM(1);
			
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(MainView.class.getResource("/images/background.png")));
		lblNewLabel_2.setBounds(0, 0, 849, 548);
		panelQLPhieuMuon.add(lblNewLabel_2);
		
		
	
		panelQuanLySach = new JPanel();
		panelQuanLySach.setBackground(new Color(255, 255, 255));
		cardPanel.add(panelQuanLySach, "panelQuanLySach");
		panelQuanLySach.setLayout(null);

//		panelTheDocGia = new JPanel();
//		cardPanel.add(panelTheDocGia, "panelTheDocGia");
//		panelTheDocGia.setLayout(null);

		////////
		// Quan Ly Sach
		JPanel panel_2 = new JPanel();
		// panel_2.setBounds(10, 123, 829, 415);
		panel_2.setBounds(10, 97, 829, 423);

		panelQuanLySach.add(panel_2);

		table_QuanLySach = new JTable();
		table_QuanLySach.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table_QuanLySach.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "T\u00EAn S\u00E1ch", "T\u00E1c gi\u1EA3", "Nh\u00E0 XB", "N\u0103m XB",
						"Th\u1EC3 Lo\u1EA1i", "Gi\u00E1 S\u00E1ch", "Ng\u00F4n ng\u1EEF", "T\u00ECnh tr\u1EA1ng" }) {
			// ngăn chặn chỉnh sửa giá trị
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1 || column == 2 || column == 3 || column == 4 || column == 5
						|| column == 6 || column == 7 || column == 8)
					return false;
				return super.isCellEditable(row, column);
			}

		});

		table_QuanLySach.getColumnModel().getColumn(0).setPreferredWidth(30);
		table_QuanLySach.getColumnModel().getColumn(1).setPreferredWidth(90);
		table_QuanLySach.getColumnModel().getColumn(2).setPreferredWidth(90);
		table_QuanLySach.getColumnModel().getColumn(4).setPreferredWidth(40);
		table_QuanLySach.getColumnModel().getColumn(5).setPreferredWidth(80);
		table_QuanLySach.getColumnModel().getColumn(8).setPreferredWidth(51);
		panel_2.setLayout(null);
		// table_QuanLySach = QuanLySach.getInstance().selectAll(table_QuanLySach);
//	  	table_QuanLySach = QuanLySach.getInstance().selectbyTenSach(table_QuanLySach);
		JScrollPane scrollPane = new JScrollPane(table_QuanLySach);
		scrollPane.setBounds(0, 0, 829, 423);
		panel_2.add(scrollPane);

		textField_Search = new JTextField();
		textField_Search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					getPageDataTable_QLSach(1);
				}
			}
		});
		textField_Search.setForeground(Color.LIGHT_GRAY);
		textField_Search.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_Search.setColumns(10);
		textField_Search.setBackground(new Color(226, 255, 153));
		textField_Search.setBounds(510, 10, 300, 45);
		panelQuanLySach.add(textField_Search);
		// ----------->Xử lý tìm kiếm cho table_QuanLySach<-----------

		// Tạo đối tượng TableRowSorter để lọc dữ liệu trong bảng
//		TableRowSorter<TableModel> sorter1 = new TableRowSorter<>(table_QuanLySach.getModel());
//
//		// Đặt TableRowSorter cho bảng
//		table_QuanLySach.setRowSorter(sorter1);
//
//		// Tạo sự kiện KeyReleased cho JTextField
//		textField_Search.addKeyListener(new KeyAdapter() {
//			public void keyReleased(KeyEvent e) {
//				String input = textField_Search.getText().trim(); // Lấy dữ liệu từ JTextField
//				if (input.length() == 0) {
//					// Nếu JTextField rỗng, hiển thị tất cả dữ liệu
//					sorter1.setRowFilter(null);
//				} else {
//					// Lọc dữ liệu theo nội dung JTextField
//					sorter1.setRowFilter(RowFilter.regexFilter("(?i)" + input));
//				}
//			}
//		});

		JButton btn_TimKiem_qls = new JButton("");
		btn_TimKiem_qls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPageDataTable_QLSach(1);
			}
		});
		btn_TimKiem_qls.setOpaque(true);
		btn_TimKiem_qls.setBorderPainted(false);
		btn_TimKiem_qls.setBackground(new Color(226, 255, 153));
		btn_TimKiem_qls.setBounds(467, 10, 45, 45);
		btn_TimKiem_qls.setIcon(newIconTimKiem);
		panelQuanLySach.add(btn_TimKiem_qls);

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(MainView.class.getResource("/images/background.png")));
		lblNewLabel_3.setBounds(0, 0, 849, 520);
		panelQuanLySach.add(lblNewLabel_3);

//		JPanel panelQuanLyDocGia = new JPanel();
//		cardPanel.add(panelQuanLyDocGia, "panelQuanLyDocGia");
//		panelQuanLyDocGia.setLayout(null);
//
//		textField_TimKiem_qldg = new JTextField();
//		textField_TimKiem_qldg.setText("  Search");
//		textField_TimKiem_qldg.setForeground(Color.LIGHT_GRAY);
//		textField_TimKiem_qldg.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		textField_TimKiem_qldg.setColumns(10);
//		textField_TimKiem_qldg.setBackground(new Color(226, 255, 153));
//		textField_TimKiem_qldg.setBounds(510, 10, 300, 45);
//		panelQuanLyDocGia.add(textField_TimKiem_qldg);
//
//		JButton btn_TimKiem_qldg = new JButton("");
//		btn_TimKiem_qldg.setOpaque(true);
//		btn_TimKiem_qldg.setBorderPainted(false);
//		btn_TimKiem_qldg.setBackground(new Color(226, 255, 153));
//		btn_TimKiem_qldg.setBounds(467, 10, 45, 45);
//		panelQuanLyDocGia.add(btn_TimKiem_qldg);
//
//		JPanel panel_qldg = new JPanel();
//		panel_qldg.setBounds(10, 122, 829, 415);
//		panelQuanLyDocGia.add(panel_qldg);
//		panel_qldg.setLayout(null);
//
//		table_QuanLyDocGia = new JTable();
//		table_QuanLyDocGia
//				.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null }, },
//						new String[] { "M\u00E3 DG", "H\u1ECD t\u00EAn", "S\u0110T", "Ng\u00E0y Sinh",
//								"\u0110\u1ECBa ch\u1EC9", "Tgian \u0110\u0103ng k\u00FD", "H\u1EA1n th\u1EBB",
//								"Ph\u00ED \u0111\u0103ng k\u00FD" }));
//
//		JScrollPane scrollPane_qldg = new JScrollPane(table_QuanLyDocGia);
//		scrollPane_qldg.setBounds(0, 0, 829, 416);
//		panel_qldg.add(scrollPane_qldg);

		//// G. HUY //////
		panelThemDocGia = new JPanel();
		cardPanel.add(panelThemDocGia, "panelThemDocGia");
		panelThemDocGia.setLayout(null);

		JPanel panel_TTV = new JPanel();
		panel_TTV.setBackground(new Color(255, 255, 255));
		panel_TTV.setBounds(10, 10, 829, 528);
		panelThemDocGia.add(panel_TTV);
		panel_TTV.setLayout(null);

		JLabel labelTitle_TTV = new JLabel("THẺ ĐỘC GIẢ");
		labelTitle_TTV.setForeground(new Color(0, 0, 0));
		labelTitle_TTV.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle_TTV.setFont(new Font("Tahoma", Font.BOLD, 30));
		labelTitle_TTV.setBounds(10, 10, 809, 66);
		panel_TTV.add(labelTitle_TTV);

		JLabel lblTncGi = new JLabel("Tên độc giả");
		lblTncGi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTncGi.setBounds(55, 123, 115, 35);
		panel_TTV.add(lblTncGi);

		JLabel label_NgaySinh_TTV = new JLabel("Ngày sinh");
		label_NgaySinh_TTV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_NgaySinh_TTV.setBounds(55, 203, 83, 35);
		panel_TTV.add(label_NgaySinh_TTV);

		JLabel label_SDT_TTV = new JLabel("Số điện thoại");
		label_SDT_TTV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_SDT_TTV.setBounds(55, 281, 115, 35);
		panel_TTV.add(label_SDT_TTV);

		JLabel label_PhiDangKy_TTV = new JLabel("Phí đăng ký");
		label_PhiDangKy_TTV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_PhiDangKy_TTV.setBounds(457, 281, 88, 35);
		panel_TTV.add(label_PhiDangKy_TTV);

		JLabel label_HanThe_TTV = new JLabel("Hạn thẻ");
		label_HanThe_TTV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_HanThe_TTV.setBounds(457, 203, 69, 35);
		panel_TTV.add(label_HanThe_TTV);

		JLabel label_DiaChi_TTV = new JLabel("Địa chỉ");
		label_DiaChi_TTV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_DiaChi_TTV.setBounds(457, 126, 69, 35);
		panel_TTV.add(label_DiaChi_TTV);

		textField_TenDocGia_TTV = new JTextField();
		textField_TenDocGia_TTV.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_TenDocGia_TTV.setColumns(10);
		textField_TenDocGia_TTV.setBounds(180, 126, 200, 35);
		panel_TTV.add(textField_TenDocGia_TTV);

		textField_SDT_TTV = new JTextField();
		textField_SDT_TTV.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_SDT_TTV.setColumns(10);
		textField_SDT_TTV.setBounds(180, 284, 200, 35);
		panel_TTV.add(textField_SDT_TTV);

		textField_Diachi_TTV = new JTextField();
		textField_Diachi_TTV.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_Diachi_TTV.setColumns(10);
		textField_Diachi_TTV.setBounds(558, 126, 200, 35);
		panel_TTV.add(textField_Diachi_TTV);

		jLabel_PhiDangKy_TTV = new JLabel();
		jLabel_PhiDangKy_TTV.setFont(new Font("Tahoma", Font.BOLD, 15));
		jLabel_PhiDangKy_TTV.setBackground(new Color(230, 230, 250));
		jLabel_PhiDangKy_TTV.setOpaque(true);
		jLabel_PhiDangKy_TTV.setBounds(555, 281, 200, 35);
		panel_TTV.add(jLabel_PhiDangKy_TTV);

		chooser_NgaySinh_TTV = new JDateChooser();
		chooser_NgaySinh_TTV.setDateFormatString("dd/MM/yyyy");
		chooser_NgaySinh_TTV.getJCalendar().setMaxSelectableDate(new java.util.Date());
		chooser_NgaySinh_TTV.setBounds(180, 203, 200, 35);
		panel_TTV.add(chooser_NgaySinh_TTV);

		JButton button_Luu_TTV = new JButton("Lưu");
		button_Luu_TTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themThanhVien();
				getPageDataTABLE_QLDG(1);
			}
		});
		button_Luu_TTV.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_Luu_TTV.setBounds(240, 437, 150, 35);
		panel_TTV.add(button_Luu_TTV);

		JButton button_HuyThaoTac_TTV = new JButton("Huỷ thao tác");
		button_HuyThaoTac_TTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				huyThemThanhVien();
			}
		});
		button_HuyThaoTac_TTV.setFont(new Font("Tahoma", Font.BOLD, 15));
		button_HuyThaoTac_TTV.setBounds(430, 437, 150, 35);
		panel_TTV.add(button_HuyThaoTac_TTV);

		JLabel label_GhiChu_TTV = new JLabel("* Vui lòng điền đầy đủ thông tin trước khi nhấn lưu");
		label_GhiChu_TTV.setHorizontalAlignment(SwingConstants.CENTER);
		label_GhiChu_TTV.setForeground(new Color(128, 128, 128));
		label_GhiChu_TTV.setBackground(new Color(255, 255, 255));
		label_GhiChu_TTV.setFont(new Font("Tahoma", Font.ITALIC, 12));
		label_GhiChu_TTV.setBounds(237, 413, 321, 14);
		panel_TTV.add(label_GhiChu_TTV);

		comboBox_HanThe_TTV = new JComboBox();
		comboBox_HanThe_TTV.setFont(new Font("Tahoma", Font.BOLD, 15));
		comboBox_HanThe_TTV.setBackground(new Color(230, 230, 250));
		comboBox_HanThe_TTV.setModel(new DefaultComboBoxModel(
				new String[] { "Chọn hạn thẻ", "12 tháng", "24 tháng", "36 tháng", "48 tháng" }));
		comboBox_HanThe_TTV.setBounds(558, 206, 200, 35);
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
		panelThemDocGia.add(label_TTV);

		 panelQlyDocGia = new JPanel();
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

		
		textField_TimKiem_QLTV.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					getPageDataTABLE_QLDG(1);
				}
			}
		});
		
		JButton btnTimKiem_QLTV = new JButton("");
		btnTimKiem_QLTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPageDataTABLE_QLDG(1);
			}
		});
		btnTimKiem_QLTV.setBackground(new Color(0xE2FF99));
		btnTimKiem_QLTV.setOpaque(true);
		btnTimKiem_QLTV.setBorderPainted(false);
		btnTimKiem_QLTV.setBounds(467, 10, 45, 45);
		panelQlyDocGia.add(btnTimKiem_QLTV);
		btnTimKiem_QLTV.setIcon(newIconTimKiem);

		JPanel panel_QLTV = new JPanel();
		panel_QLTV.setBounds(10, 114, 829, 399);
		panelQlyDocGia.add(panel_QLTV);
		panel_QLTV.setLayout(null);

		JScrollPane scrollPane_QLTV = new JScrollPane();
		scrollPane_QLTV.setBounds(0, 0, 829, 401);
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
				new String[] { "Mã ĐG", "H\u1ECD v\u00E0 t\u00EAn", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i",
						"Ng\u00E0y sinh", "\u0110\u1ECBa ch\u1EC9", "TG \u0111\u0103ng k\u00FD", "H\u1EA1n th\u1EBB",
						"Ph\u00ED \u0111\u0103ng k\u00FD", "T\u00ECnh Tr\u1EA1ng" }) {
			// ngăn chặn chỉnh sửa giá trị
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1 || column == 2 || column == 3 || column == 4 || column == 5
						|| column == 6 || column == 7 || column == 8)
					return false;
				return super.isCellEditable(row, column);
			}

		});

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

		//ThanhVien.getInstance().selectAll(table_QLTV);

		// Tạo đối tượng TableRowSorter để lọc dữ liệu trong bảng
		//TableRowSorter<TableModel> sorter_QLTV = new TableRowSorter<>(table_QLTV.getModel());

		// Đặt TableRowSorter cho bảng
		//table_QLTV.setRowSorter(sorter_QLTV);

		// khoi tao tach trang
		currentPageTable_QLDG = 1;
		getCountTABLE_QLDG();
		getPageDataTABLE_QLDG(1);
		
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(MainView.class.getResource("/images/background.png")));
		lblNewLabel_4.setBounds(0, 0, 849, 548);
		panelQlyDocGia.add(lblNewLabel_4);

		// Tạo sự kiện KeyReleased cho textField_TimKiem_QLTV
		textField_TimKiem_QLTV.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String input = textField_TimKiem_QLTV.getText().trim(); // Lấy dữ liệu từ JTextField
				if (input.length() == 0) {
					// Nếu JTextField rỗng, hiển thị tất cả dữ liệu
					//sorter_QLTV.setRowFilter(null);
				} else {
					// Lọc dữ liệu theo nội dung JTextField
				//	sorter_QLTV.setRowFilter(RowFilter.regexFilter("(?i)" + input));
				}
			}
		});

		// init phân trang sách
		currentPageTable_QLSach = 1;
		getCountTable_QLSach();
		getPageDataTable_QLSach(1);
		// getPaginationDetailsTable_QLSach();

		// QuanLyNhapLo
		initTableQuanLyNhapLo();

		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon(MainView.class.getResource("/images/background.png")));
		lblNewLabel_5.setBounds(10, 10, 849, 548);
		panelQuanLyNhapLo.add(lblNewLabel_5);
	}

	// Sự kiện btn_Huy_pm
	private void ActionBtn_Huy_Pm() {
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
			arrIDSACH.clear();
		} else {
			// Xử lý khi người dùng chọn No
		}
	}

	// Sự kiện btn_XoaSach_pm
	private void ActionBtn_XoaSach_Pm() {
		int row = table_pm.getSelectedRow(); // lấy chỉ số của hàng được chọn trong table.
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Hãy chọn sách muốn xóa!");
		} else {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa?", "Thông báo",
					JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				// Xử lý khi người dùng chọn Yes
				int IDSACH = (int) table_pm.getValueAt(row, 0);
				arrIDSACH.remove(Integer.valueOf(IDSACH));

				DefaultTableModel model = (DefaultTableModel) table_pm.getModel();
				model.removeRow(row);
			} else {
				// Xử lý khi người dùng chọn No
			}
		}
	}

	// Hàm Tạo và lưu file PDF phiếu mượn
	private void ActionSavePDF(int MaPhieuMuonLonNhat) {

		// Vẽ tất cả hình ảnh trong jPanel thành file PDF
		try {
			Dimension size = PhieuMuonView.panel.getSize();
			BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = image.createGraphics();
			PhieuMuonView.panel.paint(g2);
			Document document = new Document();
			FileOutputStream fileOutputStream = new FileOutputStream(
					"File PDF Phiếu Mượn/PhieuMuon Mã[" + MaPhieuMuonLonNhat + "].pdf");
			PdfWriter.getInstance(document, fileOutputStream);
			document.open();
			com.itextpdf.text.Image pdfImage = com.itextpdf.text.Image.getInstance(new java.awt.image.BufferedImage(
					image.getColorModel(), image.copyData(null), image.isAlphaPremultiplied(), null), null);
			document.add(pdfImage);
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
			File file = new File("File PDF Phiếu Mượn/PhieuMuon Mã[" + MaPhieuMuonLonNhat + "].pdf");
			Desktop.getDesktop().open(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// Sự kiện btn_InPhieu_pm
	private void ActionBtn_InPhieu_Pm() {
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
					int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Thông báo",
							JOptionPane.YES_NO_OPTION);
					if (dialogResult == JOptionPane.YES_OPTION) {
						Service23.getInstance().InsertPhieuMuon(maTheDocGia, ngayMuon, ngayTra, MaNV);
						ArrayList<Integer> arr = Service23.getInstance().SelectAllMaPm(maTheDocGia);
						maPmLonNhat = Collections.max(arr);

						if (arr.isEmpty()) {
							// Xử lý trường hợp khi ArrayList là rỗng
						} else {
							for (int i = 0; i < arrIDSACH.size(); i++) {
								Service23.getInstance().InsertChiTietPhieuMuon(maPmLonNhat, arrIDSACH.get(i));
								Service23.getInstance().UpdateTinhTrangSachHet(maPmLonNhat);
							}
							// Cập nhật bảng Quản lý phiếu mượn
							((DefaultTableModel) MainView.table_QuanLyPhieuMuon.getModel()).setRowCount(0);
							//Service23.getInstance().SelectAllPhieuMuon(table_QuanLyPhieuMuon);
							getPageDataTable_QLPM(1);
							
							// Cập nhật bảng Thêm sách
							((DefaultTableModel) DiaLog_ThemSach_PM.table.getModel()).setRowCount(0);
							Service23.getInstance().SelectAllSachCon(DiaLog_ThemSach_PM.table);

							// Cập nhật bảng Quản lý Sach
							((DefaultTableModel) table_QuanLySach.getModel()).setRowCount(0);
							QuanLySach.getInstance().selectAll(table_QuanLySach);

							// Gắn dữ liệu vào PhieuMuonView.java
							PhieuMuonView.lbl_time.setText(lbl_Time_pm.getText());
							PhieuMuonView.lbl_MaPm.setText(maPmLonNhat + "");
							PhieuMuonView.lbl_MaThe.setText(lbl_MaThe_pm.getText());
							PhieuMuonView.lbl_HoTen.setText(lbl_HovaTen_pm.getText());
							PhieuMuonView.lbl_SDT.setText(lbl_SDT_pm.getText());
							PhieuMuonView.lbl_DiaChi.setText(lbl_DiaChi_pm.getText());
							PhieuMuonView.lbl_NgayMuon.setText(lbl_NgayMuon_pm.getText());

							// SimpleDateFormat formatterNgayTra = new SimpleDateFormat("dd/MM/yyyy");
							String formattedNgayTra = formatter.format(ngayTra);

							PhieuMuonView.lbl_NgayTra.setText(formattedNgayTra + "");
							PhieuMuonView.rowCount = SlHangTablePm;

							// Đổ dữ liệu từ table table_pm vào PhieuMuonView.textArea
							SetDataTextarea(table_pm, 0, PhieuMuonView.textArea_IDSACH);
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
							arrIDSACH.clear();

							new PhieuMuonView().setVisible(false);

							ActionSavePDF(maPmLonNhat);

						}
					} else {
						// Xử lý khi người dùng chọn No
					}
				}

			}
		}
		getPageDataTable_QLSach(1);
	}

	// Hàm Xử lý Tìm kiếm trong table
	public static void SearchTable(JTable table, JTextField textField) {
		// Tạo đối tượng TableRowSorter để lọc dữ liệu trong bảng
		TableRowSorter<TableModel> sorter1 = new TableRowSorter<>(table.getModel());

		// Đặt TableRowSorter cho bảng
		table.setRowSorter(sorter1);

		// Tạo sự kiện KeyReleased cho JTextField
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String input = textField.getText().trim(); // Lấy dữ liệu từ JTextField
				if (input.length() == 0) {
					// Nếu JTextField rỗng, hiển thị tất cả dữ liệu
					sorter1.setRowFilter(null);
				} else {
					// Lọc dữ liệu theo nội dung JTextField
					sorter1.setRowFilter(RowFilter.regexFilter("(?i)" + input));
				}
			}
		});
	}

	// Hàm lấy thời gian hiện tại và hiển thị liên tục
	private void TimeNow(JLabel lable) {
		// Gọi lại hàm sau 1 giây
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String formattedDate = formatter.format(date);
				lable.setText(formattedDate);
			}
		});
		timer.start();
	}

	// Hàm đổi màu khi ấn vào button
	private void changeButtonColor(JButton button) {
		if (lastClicked != null) {
			lastClicked.setBackground(new Color(0xE2FF99));
		}
		button.setBackground(new Color(0X7A8F44));
		lastClicked = button;
	}

	// Hàm đổ dữ liệu từ table vào Textarea
	public void SetDataTextarea(JTable table, int columTable, JTextArea textArea) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < table.getRowCount(); i++) {
			Object value = table.getValueAt(i, columTable);
			buffer.append(value.toString());
			buffer.append("\n");
		}
		textArea.setText(buffer.toString());
	}

	// Hàm set image
	private static ImageIcon getScaledIcon(String imagePath, int width, int height) {
		ImageIcon icon = new ImageIcon(MainView.class.getResource(imagePath));
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
	}

	// Hàm set ComboBox ngày trả
	private void ComboBoxNgayTra(int thang) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, thang);
		ngayTra = new java.sql.Date(cal.getTimeInMillis());
	}

	// Hàm set PopupMenu khi chuộc phải vào hàng trong table
	private void setupPopupMenu(JTable table, JFrame jFrame) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getButton() == MouseEvent.BUTTON3) { // kiểm tra chuột phải
					int row = table.rowAtPoint(e.getPoint()); // lấy chỉ số dòng được nhấn chuột
					table.setRowSelectionInterval(row, row); // chọn dòng được nhấn chuột

					JPopupMenu popupMenu = new JPopupMenu();
					popupMenu.setBackground(Color.BLUE);

					JMenuItem menuItemXemChiTiet = new JMenuItem("Xem chi tiết");
					menuItemXemChiTiet.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							showDialog_XemChiTiet_QLPM( MaPM );
						}
					});

					JMenuItem menuItemĐanhauDaTra = new JMenuItem("Đánh dấu đã trả");
					menuItemĐanhauDaTra.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?",
									"Thông báo", JOptionPane.YES_NO_OPTION);
							if (dialogResult == JOptionPane.YES_OPTION) {
								// Xử lý khi người dùng chọn Yes
								Service23.getInstance().UpdateTinhTrangPhieuMuonDaTra(MaPM);
								Service23.getInstance().UpdateTinhTrangSachCon(MaPM);

								// Cập nhật bảng Quản lý phiếu mượn
								((DefaultTableModel) table.getModel()).setRowCount(0);
								//Service23.getInstance().SelectAllPhieuMuon(table);
								getPageDataTable_QLPM(1);

								JOptionPane.showMessageDialog(null, "Đã cập nhật!");
							} else {
								// Xử lý khi người dùng chọn No
							}
						}
					});

					popupMenu.add(menuItemXemChiTiet);
					popupMenu.add(menuItemĐanhauDaTra);
					popupMenu.show(table, e.getX(), e.getY()); // hiển thị menu
				}
			}
		});
	}

	// QuanLyNhapLo
	public void initTableQuanLyNhapLo() {

		// khoi tao lần đầu
		table_QuanLyNhapLo = new JTable();
		table_QuanLyNhapLo.setFont(new Font("Tahoma", Font.PLAIN, 13));

		table_QuanLyNhapLo
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Mã Nhập Lô", "T\u00EAn NCC",
						"S\u0110T NCC", "\u0110\u1ECBa ch\u1EC9", "Ng\u00E0y nh\u1EADp", "T\u1ED5ng ti\u1EC1n" }) {

					// ngăn chặn chỉnh sửa giá trị
					public boolean isCellEditable(int row, int column) {
						if (column == 0 || column == 1 || column == 2 || column == 3 || column == 4 || column == 5)
							return false;
						return super.isCellEditable(row, column);
					}
				});
		
		table_QuanLyNhapLo.getColumnModel().getColumn(0).setPreferredWidth(20);
		table_QuanLyNhapLo.getColumnModel().getColumn(1).setPreferredWidth(120);
		table_QuanLyNhapLo.getColumnModel().getColumn(2).setPreferredWidth(70);
		table_QuanLyNhapLo.getColumnModel().getColumn(3).setPreferredWidth(115);
		table_QuanLyNhapLo.getColumnModel().getColumn(4).setPreferredWidth(45);
		table_QuanLyNhapLo.getColumnModel().getColumn(5).setPreferredWidth(45);
		table_QuanLyNhapLo.getSelectionModel().addListSelectionListener(new ListSelectionListener() { // su kien chon 1
			// o tren table
			public void valueChanged(ListSelectionEvent event) {
				int row = table_QuanLyNhapLo.getSelectedRow(); // lấy chỉ số của hàng được chọn trong table.
				if (row >= 0) { // Đảm bảo là có hàng được chọn
					idSelectedPhieuNhapLo = Integer
							.valueOf(table_QuanLyNhapLo.getValueAt(table_QuanLyNhapLo.getSelectedRow(), 0).toString());
					System.out.println(idSelectedPhieuNhapLo);
				}
			}
		});

		table_QuanLyNhapLo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table_QuanLyNhapLo.setFillsViewportHeight(true);

		// LoadDataList();
		AddPopUp();

		panelQuanLyNhapLo = new JPanel();
		panelQuanLyNhapLo.setBackground(new Color(255, 255, 255));
		cardPanel.add(panelQuanLyNhapLo, "panelQuanLyNhapLo");
		panelQuanLyNhapLo.setLayout(null);

		panel_table_qlnl = new JPanel();
		// panel_table_qlnl.setBounds(10, 123, 829, 415);ttt
		panel_table_qlnl.setBounds(22, 120, 817, 400);
		panelQuanLyNhapLo.add(panel_table_qlnl);
		panel_table_qlnl.setLayout(null);

		scrollPane_qlnl = new JScrollPane(table_QuanLyNhapLo);
		scrollPane_qlnl.setBounds(0, 0, 817, 415);
		panel_table_qlnl.add(scrollPane_qlnl);

		JButton btn_TimKiem_qlnl = new JButton("");
		btn_TimKiem_qlnl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPageData(1);
			}
		});
		btn_TimKiem_qlnl.setOpaque(true);
		btn_TimKiem_qlnl.setBorderPainted(false);
		btn_TimKiem_qlnl.setBackground(new Color(226, 255, 153));
		btn_TimKiem_qlnl.setBounds(467, 10, 45, 45);

		panelQuanLyNhapLo.add(btn_TimKiem_qlnl);
		btn_TimKiem_qlnl.setIcon(newIconTimKiem);

		textField_TimKiem_qlnl = new JTextField();
		textField_TimKiem_qlnl.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					getPageData(1);
				}
			}
		});
		textField_TimKiem_qlnl.setText("");
		textField_TimKiem_qlnl.setForeground(Color.LIGHT_GRAY);
		textField_TimKiem_qlnl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_TimKiem_qlnl.setColumns(10);
		textField_TimKiem_qlnl.setBackground(new Color(226, 255, 153));
		textField_TimKiem_qlnl.setBounds(510, 10, 300, 45);
		panelQuanLyNhapLo.add(textField_TimKiem_qlnl);

		// tìm từ db nên ko sài
		// SearchTable(table_QuanLyNhapLo, textField_TimKiem_qlnl);

		JButton btn_Them_QlNhapLo = new JButton("Thêm");
		btn_Them_QlNhapLo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Them_QlNhapLo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				them_lo();
				// dispose();
			}
		});
		btn_Them_QlNhapLo.setBounds(74, 75, 100, 29);
		panelQuanLyNhapLo.add(btn_Them_QlNhapLo);

		JButton btn_XemChiTiet_QLNhapLo = new JButton("Xem Chi Tiết");
		btn_XemChiTiet_QLNhapLo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xemchitiet();
				// dispose();
			}
		});
		btn_XemChiTiet_QLNhapLo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_XemChiTiet_QLNhapLo.setBounds(290, 75, 100, 29);
		panelQuanLyNhapLo.add(btn_XemChiTiet_QLNhapLo);

		JButton btn_QLNCC_QLNhapLo = new JButton("Quản lý NCC");
		btn_QLNCC_QLNhapLo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLy_NCC();

			}
		});
		btn_QLNCC_QLNhapLo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_QLNCC_QLNhapLo.setBounds(490, 75, 100, 29);
		btn_QLNCC_QLNhapLo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelQuanLyNhapLo.add(btn_QLNCC_QLNhapLo);

		// khoi tao trang dau tien
		currentPageTable_QLNL = 1;
		getCount();
		getPageData(1);
		// getPaginationDetails();
	}

//	public void LoadDataList() {
//		((DefaultTableModel) table_QuanLyNhapLo.getModel()).setRowCount(0);
//		// Gọi sang hàm lấy dữ liệu để đổ vào dữ liệu lên table vừa khai báo
//		table_QuanLyNhapLo = QuanLyNhapLo.getInstance().selectAll(table_QuanLyNhapLo);
//	}

	public class RowPopup extends JPopupMenu {
		public RowPopup(JTable table) {

			// dùng để show popup Thêm
			JMenuItem detail_Update = new JMenuItem("Thêm Lô");
			detail_Update.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					// JOptionPane.showMessageDialog(detail, "Xem chi tiết");
					them_lo();
				}
			});

			add(detail_Update);
			// dùng để show popup xem chi tiết
			JMenuItem detail = new JMenuItem("Xem Chi Tiết + Sửa Lô");
			detail.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					// JOptionPane.showMessageDialog(detail, "Xem chi tiết");
					xemchitiet();
				}
			});
			add(detail);
		}
	}

	public void AddPopUp() {
		final RowPopup pop = new RowPopup(table_QuanLyNhapLo);

		table_QuanLyNhapLo.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println("so row" +table_QuanLyNhapLo.getRowCount());
				// TODO Auto-generated method stub
				if (SwingUtilities.isRightMouseButton(e)) {
					pop.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		final RowPopup pop1 = new RowPopup(table_QuanLyNhapLo);
		table_QuanLyNhapLo.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (SwingUtilities.isRightMouseButton(e)) {
					pop.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	public void them_lo() {
		new Dialog_ThemLo_QLNL(this, MaNV).setVisible(true);
	}
	
	public void showDialog_XemChiTiet_QLPM(int MaPM) {
		new Dialog_XemChiTiet_QLPM(this, MaPM).setVisible(true);
	}

	public void QuanLy_NCC() {
		new Dialog_QuanLyNCC_QLNL(this, null, 0).setVisible(true);
	}

	public void xemchitiet() {
		if (idSelectedPhieuNhapLo == 0) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng chọn lô muốn xem!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
		} else {
			new Dialog_XemChiTiet_QLNL(this, idSelectedPhieuNhapLo).setVisible(true);

		}
	}

	public void themThanhVien() {

		DocGia docGia = new DocGia();
		TheThanhVien theThanhVien = new TheThanhVien();

		// Date now
		long millis = System.currentTimeMillis();
		java.sql.Date dateNow = new java.sql.Date(millis);
		if (chooser_NgaySinh_TTV.getDate() == null) {
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
		}
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

		} else if (chooser_NgaySinh_TTV.getDate().equals(null)) {
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

//		} else if (KtraSDT_TTV() == 1  ) {
		} else if (KtraSDT_ThanhVien() == 1) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Số điện thoại đã được đăng ký. Vui lòng kiểm tra lại!!!", "THÔNG BÁO",
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
			java.sql.Date date1 = java.sql.Date.valueOf(formattedDate);
			docGia.setNgaySinh(date1);

			docGia.setSDT(textField_SDT_TTV.getText());

			docGia.setDiaChi(textField_Diachi_TTV.getText());

			theThanhVien.setNgayDangKy(dateNow);

			// Nhập hạn thẻ
			Calendar c = Calendar.getInstance();

			if (comboBox_HanThe_TTV.getSelectedItem().equals("12 tháng")) {
				c.add(Calendar.YEAR, 1);
				long millis_1Y = TimeUnit.DAYS.toMillis(365);
				java.sql.Date date3 = new java.sql.Date(millis + millis_1Y);
				theThanhVien.setHanThe(date3);

			} else if (comboBox_HanThe_TTV.getSelectedItem().equals("24 tháng")) {
				c.add(Calendar.YEAR, 2);
				long millis_2Y = TimeUnit.DAYS.toMillis(365 * 2);
				java.sql.Date date3 = new java.sql.Date(millis + millis_2Y);
				theThanhVien.setHanThe(date3);

			} else if (comboBox_HanThe_TTV.getSelectedItem().equals("36 tháng")) {
				c.add(Calendar.YEAR, 3);
				long millis_3Y = TimeUnit.DAYS.toMillis(365 * 3);
				java.sql.Date date3 = new java.sql.Date(millis + millis_3Y);
				theThanhVien.setHanThe(date3);

			} else if (comboBox_HanThe_TTV.getSelectedItem().equals("48 tháng")) {
				c.add(Calendar.YEAR, 4);
				long millis_4Y = TimeUnit.DAYS.toMillis(365 * 4);
				java.sql.Date date3 = new java.sql.Date(millis + millis_4Y);
				theThanhVien.setHanThe(date3);
			}

			theThanhVien.setPhiDangKy(Integer.valueOf(jLabel_PhiDangKy_TTV.getText()));

			new Dialog_ThemDocGia(this, docGia, theThanhVien); // hien thi dialog

			// Xoá dữ liệu nhập trên màn hình
			huyThemThanhVien();
		}
	}
	/// ktra sdt có trùng nhau k

	public int KtraSDT_ThanhVien() {
//		String sdt;
//		int ktra=0;
//		for (int i = 0; i < table_QLTV.getRowCount(); i++) { // lấy từng row của table sách để thực thi
//
//			sdt = (String) table_QLTV.getValueAt(i, 2);
//			if(textField_SDT_TTV.getText().equals(sdt) == true ){
//				return ktra=1;
//			}
//		}
//		return ktra;
		return ThanhVien.getInstance().KtraSDT_TTV(textField_SDT_TTV.getText());
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
					JMenuItem menuItemSuaThongTin_QLTV = new JMenuItem("Sửa thông tin độc giả");
					menuItemSuaThongTin_QLTV.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							new Dialog_SuaThongTinDocGia(jFrame, MaDocGia_QLTV);
						}
					});

					JMenuItem menuItemGiaHanThe_QLTV = new JMenuItem("Gia hạn thẻ độc giả");
					menuItemGiaHanThe_QLTV.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							new Dialog_GiaHanTheDocGia(jFrame, MaDocGia_QLTV);
						}
					});

					popupMenu.add(menuItemSuaThongTin_QLTV);
					popupMenu.add(menuItemGiaHanThe_QLTV);
					popupMenu.show(table, e.getX(), e.getY()); // hiển thị menu
				}
			}
		});
	}

	/// Hàm load lại danh sach sách

	// load table sách
//	public void LoadTableSach() {
//		((DefaultTableModel) table_QuanLySach.getModel()).setRowCount(0);
//		// Gọi sang hàm lấy dữ liệu để đổ vào dữ liệu lên table vừa khai báo
//		table_QuanLySach = QuanLySach.getInstance().selectAll(table_QuanLySach);
//	}

	// load table doc gia phieu muon
	public static void LoadTableDocGiaPM() {
		((DefaultTableModel) table_DocGia_pm.getModel()).setRowCount(0);
		// Gọi sang hàm lấy dữ liệu để đổ vào dữ liệu lên table vừa khai báo
		table_DocGia_pm = Service23.getInstance().SelectAllTheDocGia(table_DocGia_pm);
	}

	// load table quan ly phieu muon
	public static void LoadTableQLTheDocGia() {
		((DefaultTableModel) table_QLTV.getModel()).setRowCount(0);
		// Gọi sang hàm lấy dữ liệu để đổ vào dữ liệu lên table vừa khai báo
		table_QLTV = ThanhVien.getInstance().selectAll(table_QLTV);
	}

	public void chucvu_quanlykho() {
		// ẩn đi những chức năng nhân viên đó không có quyền thực hiện
		btn_PhieuMuon_left.setEnabled(false);
		btn_QLPhieuMuon_left.setEnabled(false);
		btn_QuanLyDocGia_left.setEnabled(false);
		btn_TheDocGia_left.setEnabled(false);

//		btn_QuanLySach_left.setEnabled(true);
//		btn_QuanLyNhapLo_left.setEnabled(true);
	}

	public void chucvu_nhanvien() {
		// ẩn đi những chức năng nhân viên đó không có quyền thực hiện
//		btn_PhieuMuon_left.setEnabled(true);
//		btn_QLPhieuMuon_left.setEnabled(true);
//		btn_QuanLyDocGia_left.setEnabled(true);
//		btn_TheDocGia_left.setEnabled(true);
		btn_QuanLySach_left.setEnabled(false);
		btn_QuanLyNhapLo_left.setEnabled(false);

	}

	public void khongcoquyentruycap() {
		btn_PhieuMuon_left.setEnabled(false);
		btn_QLPhieuMuon_left.setEnabled(false);
		btn_QuanLyDocGia_left.setEnabled(false);
		btn_TheDocGia_left.setEnabled(false);
		btn_QuanLySach_left.setEnabled(false);
		btn_QuanLyNhapLo_left.setEnabled(false);
	}

	public void check_quyen(String chucvu) {

		if (chucvu == null) {
			khongcoquyentruycap();
		} else if (chucvu.equals("manage_stock")) {
			chucvu_quanlykho();
			System.out.println("adada1!" + chucvu);
		} else if (chucvu.equals("staff")) {
			chucvu_nhanvien();
		}

	}

	public void KhoiTaoButtonLeft() {
		btn_QLPhieuMuon_left = new JButton("QLý PHIẾU MƯỢN");
		btn_PhieuMuon_left = new JButton("PHIẾU MƯỢN");
		btn_QuanLyDocGia_left = new JButton("QLý ĐỘC GIẢ");
		btn_TheDocGia_left = new JButton(" THẺ ĐỘC GIẢ");
		btn_QuanLySach_left = new JButton("QUẢN LÝ SÁCH");
		btn_QuanLyNhapLo_left = new JButton("QLý NHẬP LÔ");
	}

	// ******************************
	// Paging cho table quan ly nhap lo

	public void getPaginationDetails() {
		if (pagingPanel != null && pagingPanel.countComponents() != 0) {
			System.out.println("rrtetetetet  ");
			pagingPanel.removeAll();
			pagingPanel.revalidate();
			pagingPanel.repaint();
		} else {
			pagingPanel = new JPanel();
			pagingPanel.setBackground(new Color(255, 255, 255));
			pagingPanel.setBounds(10, 518, 829, 29);
			panelQuanLyNhapLo.add(pagingPanel);
		}

		numbersTable_QLNL = Pagination.getPageNos(currentPageTable_QLNL, totalPagesTable_QLNL);
		buttonsTable_QLNL[0] = new JButton("Start");
		buttonsTable_QLNL[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				var pageNumber = "1";
				getPageData(Integer.parseInt(pageNumber));
			}
		});
		pagingPanel.add(buttonsTable_QLNL[0]);
		buttonsTable_QLNL[1] = new JButton("<<");
		buttonsTable_QLNL[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				var pageNumber = (currentPageTable_QLNL - 1) + "";
				getPageData(Integer.parseInt(pageNumber));
			}
		});
		pagingPanel.add(buttonsTable_QLNL[1]);
		for (int i = 0; i < numbersTable_QLNL.length; i++) {
			if (numbersTable_QLNL[i] != 0) {
				buttonsTable_QLNL[i + 2] = new JButton(numbersTable_QLNL[i] + "");
				buttonsTable_QLNL[i + 2].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						var pageNumber = ((JButton) e.getSource()).getText();
						getPageData(Integer.parseInt(pageNumber));
					}
				});
				pagingPanel.add(buttonsTable_QLNL[i + 2]);
			}
			if (numbersTable_QLNL[i] == currentPageTable_QLNL && currentPageTable_QLNL != 0) {
				buttonsTable_QLNL[i + 2].setBackground(Color.BLUE);
			}
		}
		buttonsTable_QLNL[7] = new JButton(">>");
		buttonsTable_QLNL[7].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				var pageNumber = (currentPageTable_QLNL + 1) + "";
				getPageData(Integer.parseInt(pageNumber));
			}
		});
		pagingPanel.add(buttonsTable_QLNL[7]);
		buttonsTable_QLNL[8] = new JButton("End");
		buttonsTable_QLNL[8].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				var pageNumber = (totalPagesTable_QLNL) + "";
				getPageData(Integer.parseInt(pageNumber));
			}
		});
		pagingPanel.add(buttonsTable_QLNL[8]);
		/* ẩn button khi trang hiện tại là 1 Start và << */
		if (currentPageTable_QLNL == 1) {
			buttonsTable_QLNL[0].setEnabled(false);
			buttonsTable_QLNL[1].setEnabled(false);
		}
		/* ẩn button khi trang hiện tại là cúng cùi >> và End */
		if (currentPageTable_QLNL == totalPagesTable_QLNL && currentPageTable_QLNL != 0) {
			buttonsTable_QLNL[7].setEnabled(false);
			buttonsTable_QLNL[8].setEnabled(false);
		}
		if (currentPageTable_QLNL == 0) {
			buttonsTable_QLNL[7].setEnabled(false);
			buttonsTable_QLNL[8].setEnabled(false);
			buttonsTable_QLNL[0].setEnabled(false);
			buttonsTable_QLNL[1].setEnabled(false);
		}
	}

	/* Lấy tổng số row của table và tính toán số trang */
	public void getCount() {
		tableRowCount = QuanLyNhapLo.getInstance().selectAllCount(textField_TimKiem_qlnl.getText().trim());
		if (tableRowCount > 0) {
			totalPagesTable_QLNL = (int) Math.ceil(tableRowCount / PAGE_SIZE_Table_QLNL);

			System.out.println("rrrow count is " + tableRowCount + "page Count" + totalPagesTable_QLNL);
		} else {
			totalPagesTable_QLNL = 0;
			currentPageTable_QLNL = 0;
			tableRowCount = 0;
			JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu!");
		}
	}

	/* Get data from table based on page no */
	public void getPageData(int pageNo) {
		currentPageTable_QLNL = pageNo;
		getCount();
		System.out.println("row count issss " + pageNo);
		// tính toán row bắt đầu phân trang
		startRowTable_QLNL = PAGE_SIZE_Table_QLNL * (pageNo - 1);
		LoadDataListLimit(PAGE_SIZE_Table_QLNL, startRowTable_QLNL);
		getPaginationDetails();
	}

	// Hàm load lại data với lấy số row và bỏ qua số row
	public void LoadDataListLimit(int limit, int offset) {
		((DefaultTableModel) table_QuanLyNhapLo.getModel()).setRowCount(0);
		// Gọi sang hàm lấy dữ liệu để đổ vào dữ liệu lên table vừa khai báo
		table_QuanLyNhapLo = QuanLyNhapLo.getInstance().selectAllLimit(table_QuanLyNhapLo, limit, offset,
				textField_TimKiem_qlnl.getText().trim());
	}

	// Kết phần phân trang cho table QL nhập lô
	// ******************************

	// ******************************
	
	// Paging cho table quản Lý SÁch

	public void getPaginationDetailsTable_QLSach() {
		if (pagingPanelTable_QLSach != null && pagingPanelTable_QLSach.countComponents() != 0) {
			System.out.println("etPaginationDetailsTable_QLSachhh11");
			pagingPanelTable_QLSach.removeAll();
			pagingPanelTable_QLSach.revalidate();
			pagingPanelTable_QLSach.repaint();
		} else {
			pagingPanelTable_QLSach = new JPanel();
			pagingPanelTable_QLSach.setBackground(new Color(255, 255, 255));
			pagingPanelTable_QLSach.setBounds(10, 518, 829, 29);
			panelQuanLySach.add(pagingPanelTable_QLSach);
		}

		// tính toán số button sẽ hiện thị giữa start << ... >> end
		numbersTable_QLSach = Pagination.getPageNos(currentPageTable_QLSach, totalPagesTable_QLSach);
		System.out.println("aanumbersTable_QLSachrow count is " + numbersTable_QLSach.length + "page Count"
				+ totalPagesTable_QLSach);

		// button start khi click vào set page hiện tai =1
		buttonsTable_QLSach[0] = new JButton("Start");
		buttonsTable_QLSach[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				var pageNumber = "1";
				getPageDataTable_QLSach(Integer.parseInt(pageNumber));
			}
		});

		// button << giảm 1 trang khi click
		pagingPanelTable_QLSach.add(buttonsTable_QLSach[0]);
		buttonsTable_QLSach[1] = new JButton("<<");
		buttonsTable_QLSach[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				var pageNumber = (currentPageTable_QLSach - 1) + "";
				getPageDataTable_QLSach(Integer.parseInt(pageNumber));
			}
		});
		pagingPanelTable_QLSach.add(buttonsTable_QLSach[1]);

		// for số button ở giữa và add hành đông click vào, khi click vào thì lấy số
		// hiện thị truyền vào hàm để load lại data
		for (int i = 0; i < numbersTable_QLSach.length; i++) {
			if (numbersTable_QLSach[i] != 0) {
				buttonsTable_QLSach[i + 2] = new JButton(numbersTable_QLSach[i] + "");
				buttonsTable_QLSach[i + 2].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// get số hiện thi trên button và truyên va@o ham
						var pageNumber = ((JButton) e.getSource()).getText();
						getPageDataTable_QLSach(Integer.parseInt(pageNumber));
					}
				});
				pagingPanelTable_QLSach.add(buttonsTable_QLSach[i + 2]);
			}
			if (numbersTable_QLSach[i] == currentPageTable_QLSach && currentPageTable_QLSach != 0) {
				buttonsTable_QLSach[i + 2].setBackground(Color.BLUE);
			}
		}

		// button >> +1 page khi click
		buttonsTable_QLSach[7] = new JButton(">>");
		buttonsTable_QLSach[7].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				var pageNumber = (currentPageTable_QLSach + 1) + "";
				getPageDataTable_QLSach(Integer.parseInt(pageNumber));
			}
		});
		pagingPanelTable_QLSach.add(buttonsTable_QLSach[7]);

		// button end khi click thì đến trang cuối
		buttonsTable_QLSach[8] = new JButton("End");
		buttonsTable_QLSach[8].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				var pageNumber = (totalPagesTable_QLSach) + "";
				getPageDataTable_QLSach(Integer.parseInt(pageNumber));
			}
		});
		pagingPanelTable_QLSach.add(buttonsTable_QLSach[8]);
		/* ẩn button khi trang hiện tại là 1 Start và << */
		if (currentPageTable_QLSach == 1) {
			buttonsTable_QLSach[0].setEnabled(false);
			buttonsTable_QLSach[1].setEnabled(false);
		}
		/* ẩn button khi trang hiện tại là cúng cùi >> và End */
		if (currentPageTable_QLSach == totalPagesTable_QLSach && currentPageTable_QLSach != 0) {
			buttonsTable_QLSach[7].setEnabled(false);
			buttonsTable_QLSach[8].setEnabled(false);
		}
		// nêú ko có row nào thì ẩn 4 button
		if (currentPageTable_QLSach == 0) {
			buttonsTable_QLSach[7].setEnabled(false);
			buttonsTable_QLSach[8].setEnabled(false);
			buttonsTable_QLSach[0].setEnabled(false);
			buttonsTable_QLSach[1].setEnabled(false);
		}
	}

	/* Lấy tổng số row của table và tính toán số trang */
	public void getCountTable_QLSach() {
		// lấy search trên giao diện
		String search = textField_Search.getText().trim();
		// get tổng row của table
		tableRowCountTable_QLSach = QuanLySach.getInstance().selectAllCount(search);
		if (tableRowCountTable_QLSach > 0) {
			// có số row của table thì tính toán số trang
			totalPagesTable_QLSach = (int) Math.ceil(tableRowCountTable_QLSach / PAGE_SIZE_Table_QLSach);
			System.out.println("getCountTable_QLSach row count is " + tableRowCountTable_QLSach + "page Count"
					+ totalPagesTable_QLSach);
		} else {
			// nếu ko có row nào thì set = 0 để ẩn 4 button << >> end start
			tableRowCountTable_QLSach = 0;
			currentPageTable_QLSach = 0;
			totalPagesTable_QLSach = 0;
			JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu!");
		}
	}

	/* Get data from table based on page no */
	public void getPageDataTable_QLSach(int pageNo) {
		currentPageTable_QLSach = pageNo;

		// thực hiện get tổng row, tính toán số trang
		getCountTable_QLSach();

		System.out.println("row count getPageDataTable_QLSach " + pageNo);
		// tính toán row bắt đầu phân trang
		startRowTable_QLSach = PAGE_SIZE_Table_QLSach * (pageNo - 1);
		// load lại data sau khi thực hiện thêm sửa xóa. thay đổi chọn trang
		LoadDataListLimitTable_QLSach(PAGE_SIZE_Table_QLSach, startRowTable_QLSach);
		// thực hiện load lại button chọn trang ben dưới
		getPaginationDetailsTable_QLSach();
	}

	// Hàm load lại data với lấy số row và bỏ qua số row
	public void LoadDataListLimitTable_QLSach(int limit, int offset) {
		((DefaultTableModel) table_QuanLySach.getModel()).setRowCount(0);
		// Gọi sang hàm lấy dữ liệu để đổ vào dữ liệu lên table vừa khai báo
		table_QuanLySach = QuanLySach.getInstance().selectAllLimit(table_QuanLySach, limit, offset,
				textField_Search.getText().trim());
	}

	// Kết phần phân trang cho table QL Sách
	// ******************************
	
	
	
	
	// Paging cho table quản Lý phieu muon

	public void getPaginationDetailsTable_QLPM() {
		if (pagingPanelTable_QLPM != null && pagingPanelTable_QLPM.countComponents() != 0) {
			System.out.println("etPaginationDetailsTable_QLSachhh11");
			pagingPanelTable_QLPM.removeAll();
			pagingPanelTable_QLPM.revalidate();
			pagingPanelTable_QLPM.repaint();
		} else {
			pagingPanelTable_QLPM = new JPanel();
			pagingPanelTable_QLPM.setBackground(new Color(255, 255, 255));
			pagingPanelTable_QLPM.setBounds(10, 518, 829, 29);
			panelQLPhieuMuon.add(pagingPanelTable_QLPM);
		}

		// tính toán số button sẽ hiện thị giữa start << ... >> end
		numbersTable_QLPM = Pagination.getPageNos(currentPageTable_QLPM, totalPagesTable_QLPM);
		System.out.println("currentPageTable_QLPM=? " + currentPageTable_QLPM + "page Count"+ totalPagesTable_QLPM);
		System.out.println("aanumbersTable_QLPMrow count is " + numbersTable_QLPM.length + "page Count"+ totalPagesTable_QLPM);

		// button start khi click vào set page hiện tai =1
		buttonsTable_QLPM[0] = new JButton("Start");
		buttonsTable_QLPM[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				var pageNumber = "1";
				getPageDataTable_QLPM(Integer.parseInt(pageNumber));
			}
		});

		// button << giảm 1 trang khi click
		pagingPanelTable_QLPM.add(buttonsTable_QLPM[0]);
		buttonsTable_QLPM[1] = new JButton("<<");
		buttonsTable_QLPM[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				var pageNumber = (currentPageTable_QLPM - 1) + "";
				getPageDataTable_QLPM(Integer.parseInt(pageNumber));
			}
		});
		pagingPanelTable_QLPM.add(buttonsTable_QLPM[1]);

		// for số button ở giữa và add hành đông click vào, khi click vào thì lấy số
		// hiện thị truyền vào hàm để load lại data
		for (int i = 0; i < numbersTable_QLPM.length; i++) {
			if (numbersTable_QLPM[i] != 0) {
				buttonsTable_QLPM[i + 2] = new JButton(numbersTable_QLPM[i] + "");
				buttonsTable_QLPM[i + 2].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// get số hiện thi trên button và truyên va@o ham
						var pageNumber = ((JButton) e.getSource()).getText();
						getPageDataTable_QLPM(Integer.parseInt(pageNumber));
					}
				});
				pagingPanelTable_QLPM.add(buttonsTable_QLPM[i + 2]);
			}
			if (numbersTable_QLPM[i] == currentPageTable_QLPM && currentPageTable_QLPM != 0) {
				buttonsTable_QLPM[i + 2].setBackground(Color.BLUE);
			}
		}

		// button >> +1 page khi click
		buttonsTable_QLPM[7] = new JButton(">>");
		buttonsTable_QLPM[7].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				var pageNumber = (currentPageTable_QLPM + 1) + "";
				getPageDataTable_QLPM(Integer.parseInt(pageNumber));
			}
		});
		pagingPanelTable_QLPM.add(buttonsTable_QLPM[7]);

		// button end khi click thì đến trang cuối
		buttonsTable_QLPM[8] = new JButton("End");
		buttonsTable_QLPM[8].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				var pageNumber = (totalPagesTable_QLPM) + "";
				getPageDataTable_QLPM(Integer.parseInt(pageNumber));
			}
		});
		pagingPanelTable_QLPM.add(buttonsTable_QLPM[8]);
		/* ẩn button khi trang hiện tại là 1 Start và << */
		if (currentPageTable_QLPM == 1) {
			buttonsTable_QLPM[0].setEnabled(false);
			buttonsTable_QLPM[1].setEnabled(false);
		}
		/* ẩn button khi trang hiện tại là cúng cùi >> và End */
		if (currentPageTable_QLPM == totalPagesTable_QLPM && currentPageTable_QLPM != 0) {
			buttonsTable_QLPM[7].setEnabled(false);
			buttonsTable_QLPM[8].setEnabled(false);
		}
		// nêú ko có row nào thì ẩn 4 button
		if (currentPageTable_QLPM == 0) {
			buttonsTable_QLPM[7].setEnabled(false);
			buttonsTable_QLPM[8].setEnabled(false);
			buttonsTable_QLPM[0].setEnabled(false);
			buttonsTable_QLPM[1].setEnabled(false);
		}
	}

	/* Lấy tổng số row của table và tính toán số trang */
	public void getCountTable_QLPM() {
		// lấy search trên giao diện
		String search = textField_TimKiem_qlpm.getText().trim();
		// get tổng row của table
		tableRowCountTable_QLPM = Service23.getInstance().selectAllCount(search);
		if (tableRowCountTable_QLPM > 0) {
			// có số row của table thì tính toán số trang
			totalPagesTable_QLPM = (int) Math.ceil(tableRowCountTable_QLPM / PAGE_SIZE_Table_QLPM);
			
			System.out.println("getCountTable_QLPM row count is " + tableRowCountTable_QLPM + "page Count"
					+ totalPagesTable_QLPM);
		} else {
			// nếu ko có row nào thì set = 0 để ẩn 4 button << >> end start
			tableRowCountTable_QLPM = 0;
			currentPageTable_QLPM = 0;
			totalPagesTable_QLPM = 0;
			JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu!");
		}
	}

	/* Get data from table based on page no */
	public void getPageDataTable_QLPM(int pageNo) {
		currentPageTable_QLPM = pageNo;

		// thực hiện get tổng row, tính toán số trang
		getCountTable_QLPM();

		System.out.println("row count getPageDataTable_QLPM " + pageNo);
		// tính toán row bắt đầu phân trang
		startRowTable_QLPM = PAGE_SIZE_Table_QLPM * (pageNo - 1);
		// load lại data sau khi thực hiện thêm sửa xóa. thay đổi chọn trang
		LoadDataListLimitTable_QLPM(PAGE_SIZE_Table_QLPM, startRowTable_QLPM);
		// thực hiện load lại button chọn trang ben dưới
		getPaginationDetailsTable_QLPM();
	}

	// Hàm load lại data với lấy số row và bỏ qua số row
	public void LoadDataListLimitTable_QLPM(int limit, int offset) {
		((DefaultTableModel) table_QuanLyPhieuMuon.getModel()).setRowCount(0);
		// Gọi sang hàm lấy dữ liệu để đổ vào dữ liệu lên table vừa khai báo
		table_QuanLyPhieuMuon = Service23.getInstance().SelectAllPhieuMuonlimit(table_QuanLyPhieuMuon, limit, offset,
				textField_TimKiem_qlpm.getText().trim());
	}

	// Kết phần phân trang cho table QL phieumuon
	// ******************************
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Paging cho table quan ly DOC GIA

		public void getPaginationDetailsTABLE_QLDG() {
			if (pagingPanel_QLDG != null && pagingPanel_QLDG.countComponents() != 0) {
				System.out.println("rrtetetetet  ");
				pagingPanel_QLDG.removeAll();
				pagingPanel_QLDG.revalidate();
				pagingPanel_QLDG.repaint();
			} else {
				pagingPanel_QLDG = new JPanel();
				pagingPanel_QLDG.setBackground(new Color(255, 255, 255));
				pagingPanel_QLDG.setBounds(10, 518, 829, 29);
				panelQlyDocGia.add(pagingPanel_QLDG);
			}

			numbersTable_QLDG = Pagination.getPageNos(currentPageTable_QLDG, totalPagesTable_QLDG);
			System.out.println("numbersTable_QLDG count is " + numbersTable_QLDG.length 
					+ "totalPagesTable_QLDGpage Count" + totalPagesTable_QLDG
					+ "currentPageTable_QLDG: " + currentPageTable_QLDG);
			
			buttonsTable_QLDG[0] = new JButton("Start");
			buttonsTable_QLDG[0].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					var pageNumber = "1";
					getPageDataTABLE_QLDG(Integer.parseInt(pageNumber));
				}
			});
			pagingPanel_QLDG.add(buttonsTable_QLDG[0]);
			buttonsTable_QLDG[1] = new JButton("<<");
			buttonsTable_QLDG[1].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					var pageNumber = (currentPageTable_QLDG - 1) + "";
					getPageDataTABLE_QLDG(Integer.parseInt(pageNumber));
				}
			});
			pagingPanel_QLDG.add(buttonsTable_QLDG[1]);
			for (int i = 0; i < numbersTable_QLDG.length; i++) {
				if (numbersTable_QLDG[i] != 0) {
					buttonsTable_QLDG[i + 2] = new JButton(numbersTable_QLDG[i] + "");
					buttonsTable_QLDG[i + 2].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							var pageNumber = ((JButton) e.getSource()).getText();
							getPageDataTABLE_QLDG(Integer.parseInt(pageNumber));
						}
					});
					pagingPanel_QLDG.add(buttonsTable_QLDG[i + 2]);
				}
				if (numbersTable_QLDG[i] == currentPageTable_QLDG && currentPageTable_QLDG != 0) {
					buttonsTable_QLDG[i + 2].setBackground(Color.BLUE);
				}
			}
			buttonsTable_QLDG[7] = new JButton(">>");
			buttonsTable_QLDG[7].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					var pageNumber = (currentPageTable_QLDG + 1) + "";
					getPageDataTABLE_QLDG(Integer.parseInt(pageNumber));
				}
			});
			pagingPanel_QLDG.add(buttonsTable_QLDG[7]);
			buttonsTable_QLDG[8] = new JButton("End");
			buttonsTable_QLDG[8].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					var pageNumber = (totalPagesTable_QLDG) + "";
					getPageDataTABLE_QLDG(Integer.parseInt(pageNumber));
				}
			});
			pagingPanel_QLDG.add(buttonsTable_QLDG[8]);
			/* ẩn button khi trang hiện tại là 1 Start và << */
			if (currentPageTable_QLDG == 1) {
				buttonsTable_QLDG[0].setEnabled(false);
				buttonsTable_QLDG[1].setEnabled(false);
			}
			/* ẩn button khi trang hiện tại là cúng cùi >> và End */
			if (currentPageTable_QLDG == totalPagesTable_QLDG && currentPageTable_QLDG != 0) {
				buttonsTable_QLDG[7].setEnabled(false);
				buttonsTable_QLDG[8].setEnabled(false);
			}
			if (currentPageTable_QLDG == 0) {
				buttonsTable_QLDG[7].setEnabled(false);
				buttonsTable_QLDG[8].setEnabled(false);
				buttonsTable_QLDG[0].setEnabled(false);
				buttonsTable_QLDG[1].setEnabled(false);
			}
		}

		/* Lấy tổng số row của table và tính toán số trang */
		public void getCountTABLE_QLDG() {
			tableRowCountTable_QLDG = ThanhVien.getInstance().selectAllCount_QLQG(textField_TimKiem_QLTV.getText().trim());
			if (tableRowCountTable_QLDG > 0) {
				totalPagesTable_QLDG = (int) Math.ceil(tableRowCountTable_QLDG / PAGE_SIZE_Table_QLDG);

				System.out.println("totalPagesTable_QLDGrrrow count is " + tableRowCountTable_QLDG + "totalPagesTable_QLDGpage Count" + totalPagesTable_QLDG);
			} else {
				totalPagesTable_QLDG = 0;
				currentPageTable_QLDG = 0;
				tableRowCountTable_QLDG = 0;
				JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu!");
			}
		}

		/* Get data from table based on page no */
		public void getPageDataTABLE_QLDG(int pageNo) {
			currentPageTable_QLDG = pageNo;
			getCountTABLE_QLDG();
			
			// tính toán row bắt đầu phân trang
			startRowTable_QLDG = PAGE_SIZE_Table_QLDG * (pageNo - 1);
			LoadDataListLimitTABLE_QLDG(PAGE_SIZE_Table_QLDG, startRowTable_QLDG);
			getPaginationDetailsTABLE_QLDG();
		}

		// Hàm load lại data với lấy số row và bỏ qua số row
		public void LoadDataListLimitTABLE_QLDG(int limit, int offset) {
			((DefaultTableModel) table_QLTV.getModel()).setRowCount(0);
			// Gọi sang hàm lấy dữ liệu để đổ vào dữ liệu lên table vừa khai báo
			table_QLTV = ThanhVien.getInstance().selectAllLimit_QLQG(table_QLTV, limit, offset,
					textField_TimKiem_QLTV.getText().trim());
		}

		// Kết phần phân trang cho table QL doc gia 
}
