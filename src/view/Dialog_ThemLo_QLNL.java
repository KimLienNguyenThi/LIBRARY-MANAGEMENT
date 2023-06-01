package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JDateChooser;

import database.LoginService;
import database.QuanLyNhapLo;
import model.ChiTietLo;
import model.DocGia;
import model.NhaCungCap;
import model.PhieuNhapLo;
import model.Sach;
import model.login_trave;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
//import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import view.Dialog_QuanLyNCC_QLNL.RowPopup;

public class Dialog_ThemLo_QLNL extends JDialog {
	JFrame frame = new JFrame();
	public MainView frameParent;
	private JPanel contentPane;
	private JTextField textField_NgayNhap_ThemLo;
	private JTextField textField_SDT_ThemLo;
	private JTextField textField_TongSL_ThemLo;
	private JTextField textField_ThanhToan_ThemLo;
	private JTextField textField_TenNCC_ThemLo;
	private JTextField textField_DiaChi_ThemLo;
	private JLabel lbl_MaNV_1_ThemLo ;
	private int thanhtoan = 0;
	// private JDatePickerImpl date_NgayNhap_ThemLo;
	public JTable table;
	public DefaultTableModel model;
	private JDateChooser chooser_NgayNhap_QLNL;
	private Container panelNCC_ThemLo;
	private JTable table_ncc;
	private int idSelectedNCC = 0;
	private int idLoNew;
	private String taikhoan;
	private String matkhau;
public int maNV;
	public Dialog_ThemLo_QLNL(MainView parent, int manv) {
		super(parent, "Thêm Lô", true);
		this.setLocationRelativeTo(null);
		this.init();
		maNV = manv;
		System.out.println(maNV);
		lbl_MaNV_1_ThemLo.setText("" + maNV);
		// this.initDatePicker();
		this.setVisible(false);
		// hiện thị trung tâm màn hình
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		frameParent = parent;
		
		
	}

	public void init() {
		setBounds(100, 100, 926, 479);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_TenNCC_ThemLo = new JLabel("Tên nhà cung cấp: ");
		lbl_TenNCC_ThemLo.setBounds(10, 40, 143, 20);
		lbl_TenNCC_ThemLo.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_TenNCC_ThemLo);

		JLabel lbl_SDT_ThemLo = new JLabel("Số điện thoại:");
		lbl_SDT_ThemLo.setBounds(362, 70, 102, 20);
		lbl_SDT_ThemLo.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_SDT_ThemLo);

		JLabel lbl_Diachi_ThemLo = new JLabel("Địa chỉ:");
		lbl_Diachi_ThemLo.setBounds(10, 100, 56, 20);
		lbl_Diachi_ThemLo.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_Diachi_ThemLo);

		JLabel lbl_ThanhToan_ThemLo = new JLabel("Thanh Toán:");
		lbl_ThanhToan_ThemLo.setBounds(10, 70, 96, 20);
		lbl_ThanhToan_ThemLo.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_ThanhToan_ThemLo);

		JLabel lbl_NgayNhap_ThemLo = new JLabel("Ngày nhập:");
		lbl_NgayNhap_ThemLo.setBounds(372, 40, 85, 20);
		lbl_NgayNhap_ThemLo.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_NgayNhap_ThemLo);

		JLabel lblNewLabel_7 = new JLabel("Vui lòng điền đầy đủ thông tin trước khi nhấn lưu");
		lblNewLabel_7.setBounds(369, 417, 254, 15);
		lblNewLabel_7.setForeground(new Color(128, 128, 128));
		lblNewLabel_7.setBackground(new Color(255, 255, 255));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.ITALIC, 12));
		contentPane.add(lblNewLabel_7);

		textField_TenNCC_ThemLo = new JTextField();
		textField_TenNCC_ThemLo.setBounds(156, 43, 196, 19);
		textField_TenNCC_ThemLo.setColumns(10);
		contentPane.add(textField_TenNCC_ThemLo);

		textField_DiaChi_ThemLo = new JTextField();
		textField_DiaChi_ThemLo.setBounds(156, 103, 467, 19);
		textField_DiaChi_ThemLo.setColumns(10);
		contentPane.add(textField_DiaChi_ThemLo);

		textField_ThanhToan_ThemLo = new JTextField();
		textField_ThanhToan_ThemLo.setEnabled(false);
		textField_ThanhToan_ThemLo.setBounds(156, 73, 196, 19);
		textField_ThanhToan_ThemLo.setColumns(10);
		contentPane.add(textField_ThanhToan_ThemLo);

		textField_NgayNhap_ThemLo = new JTextField();
		// textField_NgayNhap_ThemLo.setBounds(467, 13, 124, 19);
		textField_NgayNhap_ThemLo.setColumns(10);
		contentPane.add(textField_NgayNhap_ThemLo);

		textField_SDT_ThemLo = new JTextField();
		textField_SDT_ThemLo.setBounds(467, 73, 156, 19);
		textField_SDT_ThemLo.setColumns(10);
		contentPane.add(textField_SDT_ThemLo);

		// chỉ cho nhập số
		textField_ThanhToan_ThemLo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}

			}
		});

		textField_SDT_ThemLo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}

			}
		});
		JButton btn_Luu_ThemLo = new JButton("Lưu");
		btn_Luu_ThemLo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// themLo();
				// them_sach();

				Save();
				// Hàm này load tất cả => bỏ >> vì sài phân trang
				// frameParent.LoadDataList();
				// phân trang gọi nÀY
				frameParent.getPageData(1); // truyền 1 là lấy trang đầu tiên

				// Hàm này load tất cả => bỏ >> vì sài phân trang
				// frameParent.LoadTableSach();

				frameParent.getPageDataTable_QLSach(1);
			}
		});
		btn_Luu_ThemLo.setBounds(453, 379, 69, 28);
		btn_Luu_ThemLo.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btn_Luu_ThemLo);

		JPanel panel_ThemLo_QLNL = new JPanel();
		panel_ThemLo_QLNL.setBounds(20, 175, 603, 194);
		contentPane.add(panel_ThemLo_QLNL);
		panel_ThemLo_QLNL.setLayout(null);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		String IDSACH = null;
		String tenSach = null;
		String theLoai = null;
		int namXuatBan = 0;
		String nXB = null;
		String tacGia = null;
		int soLuong = 0;
		int giaSach = 0;
		String ngonNgu = null;
		String tinhTrang = null;

		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "T\u00EAn s\u00E1ch", "Th\u1EC3 lo\u1EA1i", "N\u0103m Xu\u1EA5t b\u1EA3n",
						"Nh\u00E0 xu\u1EA5t b\u1EA3n", "T\u00E1c gi\u1EA3", "S\u1ED1 l\u01B0\u1EE3ng",
						"Ng\u00F4n ng\u1EEF", "Gi\u00E1 s\u00E1ch" }) {

			// ngăn chặn chỉnh sửa giá trị
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1 || column == 2 || column == 3 || column == 4 || column == 5
						|| column == 6 || column == 7)
					return false;
				return super.isCellEditable(row, column);
			}
		});
		model = (DefaultTableModel) table.getModel();

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 603, 194);
		panel_ThemLo_QLNL.add(scrollPane);

		JButton btn_ThemSach_ThemLo = new JButton("Thêm Sách");
		btn_ThemSach_ThemLo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// cachs 1
				tinh_ThanhToan();
				// casch 2
//				DiaLog_ThemSach_QLNL jdiaLogThemSach = new DiaLog_ThemSach_QLNL(frame);
//				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//				jdiaLogThemSach.setLocation(dim.width / 2 - jdiaLogThemSach.getSize().width / 2,
//						dim.height / 2 - jdiaLogThemSach.getSize().height / 2);
//				jdiaLogThemSach.setVisible(true);
//				// nếu dialog thêm sách mà nhập thông tin ok hết, thì cờ này sẽ true, thì bên
//				// dialog này tiến hành thêm vào
//				// table
//				if (jdiaLogThemSach.isThemSach) {
//					them_sach_Table(jdiaLogThemSach._sach);
//					ThanhToan();
//				}
			}
		});
		btn_ThemSach_ThemLo.setFont(new Font("Tahoma", Font.BOLD, 18));
		btn_ThemSach_ThemLo.setBounds(112, 132, 124, 33);
		contentPane.add(btn_ThemSach_ThemLo);

		chooser_NgayNhap_QLNL = new JDateChooser();
		chooser_NgayNhap_QLNL.setDateFormatString("dd/MM/yyyy");
		chooser_NgayNhap_QLNL.setBounds(467, 41, 156, 19);
		chooser_NgayNhap_QLNL.getJCalendar().setMaxSelectableDate(new java.util.Date());
		contentPane.add(chooser_NgayNhap_QLNL);

		JButton btn_Huy_ThemLo = new JButton("Hủy");
		btn_Huy_ThemLo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_Huy_ThemLo.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_Huy_ThemLo.setBounds(174, 379, 69, 28);
		contentPane.add(btn_Huy_ThemLo);

		JButton btnNewButton = new JButton("Import File");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser();
				int returnValue = jf.showOpenDialog(null);
				// int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jf.getSelectedFile();
					String excelPath = selectedFile.getAbsolutePath();
					ReadFile(excelPath);
					System.out.println("log");
				}
				// System.out.println("tt");
				ThanhToan();
			}
		});
		btnNewButton.setBounds(390, 130, 124, 35);
		contentPane.add(btnNewButton);

		JPanel panel_ncc = new JPanel();
		panel_ncc.setBounds(633, 10, 269, 422);
		contentPane.add(panel_ncc);
		panel_ncc.setLayout(null);

		JButton btn_Themncc_ThemLo = new JButton("Thêm");
		btn_Themncc_ThemLo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				New_Dialog_ncc();
			}
		});
		btn_Themncc_ThemLo.setBounds(97, 379, 83, 33);
		btn_Themncc_ThemLo.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_ncc.add(btn_Themncc_ThemLo);

		JLabel lbl_Thongtin_TenNCC_ThemLo = new JLabel("Thông tin Nhà Cung Cấp");
		lbl_Thongtin_TenNCC_ThemLo.setBounds(47, 10, 212, 20);
		panel_ncc.add(lbl_Thongtin_TenNCC_ThemLo);
		lbl_Thongtin_TenNCC_ThemLo.setFont(new Font("Dialog", Font.BOLD, 15));

		table_ncc = new JTable();
		table_ncc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table_ncc.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 NCC", "T\u00EAn NCC", "SDT NCC", "\u0110\u1ECBa Ch\u1EC9 NCC" }) {

			// ngăn chặn chỉnh sửa giá trị
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1 || column == 2 || column == 3 || column == 4 || column == 5
						|| column == 6 || column == 7 || column == 8)
					return false;
				return super.isCellEditable(row, column);
			}

		});
		JScrollPane scrollPane_1 = new JScrollPane(table_ncc);
		scrollPane_1.setBounds(0, 45, 269, 316);
		panel_ncc.add(scrollPane_1);

		JLabel lbl_MaNV_ThemLo = new JLabel("Mã Nhân viên: ");
		lbl_MaNV_ThemLo.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl_MaNV_ThemLo.setBounds(10, 10, 143, 20);
		contentPane.add(lbl_MaNV_ThemLo);

		lbl_MaNV_1_ThemLo = new JLabel("");
		lbl_MaNV_1_ThemLo.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl_MaNV_1_ThemLo.setBounds(156, 10, 196, 20);
		contentPane.add(lbl_MaNV_1_ThemLo);
//		lbl_MaNV_1_ThemLo.setText("" + maNV);
		
		table_ncc.getSelectionModel().addListSelectionListener(new ListSelectionListener() { // su kien chon 1

			// o tren table
			public void valueChanged(ListSelectionEvent event) {
				int row = table_ncc.getSelectedRow(); // lấy chỉ số của hàng được chọn trong table.
				if (row >= 0) { // Đảm bảo là có hàng được chọn
					idSelectedNCC = Integer.valueOf(table_ncc.getValueAt(table_ncc.getSelectedRow(), 0).toString());
					System.out.println(idSelectedNCC);
					tt_NCC(idSelectedNCC);
				}

			}
		});
		LoadNCC();

		// AddPopUp();
		// set_Them();
	}

	public void New_Dialog_ncc() {
		new Dialog_QuanLyNCC_QLNL(frameParent, this, 1).setVisible(true);
	}

	public void LoadNCC() {
		((DefaultTableModel) table_ncc.getModel()).setRowCount(0);
		// Gọi sang hàm lấy dữ liệu để đổ vào dữ liệu lên table vừa khai báo
		table_ncc = QuanLyNhapLo.getInstance().select_NCC(table_ncc);
	}

	public void tinh_ThanhToan() {
		new DiaLog_ThemSach_QLNL(this).setVisible(true);
	}

	public int themLo() {

		PhieuNhapLo PhieuNhapLo = new PhieuNhapLo();
		NhaCungCap NhaCungCap = new NhaCungCap();

		Pattern patternDate = Pattern.compile("^\\d{2}[-|/]\\d{2}[-|/]\\d{4}$");
		Pattern patternSDT = Pattern.compile("^0[3798]{1}\\d{8}$");

		// kieerm tra xem ngay nhap co null k
		if (chooser_NgayNhap_QLNL.getDate() == null) {
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
			return -6;
		}
		// Format Chosser về dd/MM/yyy để kiểm tra tính đúng đắn của dữ liệu
		SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
		String formattedD = d.format(chooser_NgayNhap_QLNL.getDate());
		System.out.println(formattedD);

		if (textField_TenNCC_ThemLo.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
			return -6;

		} else if (textField_SDT_ThemLo.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
			return -6;

		} else if (patternSDT.matcher(textField_SDT_ThemLo.getText()).matches() == false) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đúng định dạng số điện thoại!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
			return -6;

		} else if (textField_DiaChi_ThemLo.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
			return -6;

		} else if (textField_ThanhToan_ThemLo.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
			return -6;
		} else if (patternDate.matcher(formattedD).matches() == false) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đúng định dạng ngày nhập!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

			return -6;
		} else {

			// Lấy dữ liệu nhập

			// đoạn này lấy ngày selected trên giao diện, kiểm tra vs ngày hiện tại đi
			// thì check them
			//PhieuNhapLo.setMaNV(Integer.valueOf(lbl_MaNV_1_ThemLo.getText()));
			PhieuNhapLo.setNgayNhap(chooser_NgayNhap_QLNL.getDate());
			PhieuNhapLo.setTongTienNhap(Integer.valueOf(textField_ThanhToan_ThemLo.getText()));
			PhieuNhapLo.setMaNCC(idSelectedNCC);
			PhieuNhapLo.setMaNV(Integer.valueOf(lbl_MaNV_1_ThemLo.getText()));
			int idLoNew = QuanLyNhapLo.getInstance().InsertData(PhieuNhapLo);

			// Xoá dữ liệu nhập trên màn hình
			textField_TenNCC_ThemLo.setText("");
			textField_SDT_ThemLo.setText("");
			textField_DiaChi_ThemLo.setText("");
			textField_ThanhToan_ThemLo.setText("");

			// trả về id Lô sách vừa mới thêm, để thực thi insert data chitietlo
			return idLoNew;
		}

	}

	public void them_sach_Table(Sach sach) {
		// muốn check thêm thì check, này kiểm tra xem có bị thiếu thông tin gì của sách
		if (sach == null || sach.getTenSach() == null || sach.getSoLuong() < 0) {
			System.out.println("1111");
			return;
		}

		Object[] obj = { sach.getTenSach(), sach.getTheLoai(), sach.getNamXuatBan(), sach.getNXB(), sach.getTacGia(),
				sach.getSoLuong(), sach.getNgonNgu(), sach.getGiaSach() };

		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		model.addRow(obj);
	}

	public void ThanhToan() {
		int soLuong = 0;
		int giaSach = 0;
		thanhtoan = 0;
		System.out.println("tt");
		Object[] rowData = new Object[table.getRowCount()];
		for (int i = 0; i < table.getRowCount(); i++) { // lấy từng row của table sách để thực thi

			soLuong = (int) table.getValueAt(i, 5);
			giaSach = (int) table.getValueAt(i, 7);
			thanhtoan = thanhtoan + (soLuong * giaSach);
		}
		textField_ThanhToan_ThemLo.setText(String.valueOf(thanhtoan));
	}

	public void Save() {

		// B1: Thêm Lô sách => có ID Lô tự tăng, sau đó lấy từng row trong Table Sách để
		// tiến hành insert bảng chitietlo và sách
		int idLoNew = themLo();
		if (idLoNew == -6) // nếu <= 0 => thêm ko thành công
		{
			return;
		}
		if (idLoNew <= 0) // nếu <= 0 => thêm ko thành công
		{
			JOptionPane.showMessageDialog(frame, "Nhập Lô mới không thành công!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			JOptionPane.showMessageDialog(this, "Bạn đã Thêm Lô mới thành công.");
		}

		int soLuong = 0;
		Object[] rowData = new Object[table.getRowCount()];
		for (int i = 0; i < table.getRowCount(); i++) { // lấy từng row của table sách để thực thi thêm data bảng
														// chitietlo và sách
			soLuong = (int) table.getValueAt(i, 5);

			// System.out.print((String)table.getValueAt(i, 3));

			ChiTietLo chitietLo = new ChiTietLo(soLuong, idLoNew);

			int chitietLoNew = QuanLyNhapLo.getInstance().InsertDataChiTietLo(chitietLo);

			Sach sach = new Sach();
			sach.setTenSach((String) table.getValueAt(i, 0));
			sach.setTheLoai((String) table.getValueAt(i, 1));
			sach.setNamXuatBan((int) table.getValueAt(i, 2));
			sach.setNXB((String) table.getValueAt(i, 3));
			sach.setTacGia((String) table.getValueAt(i, 4));
			sach.setNgonNgu((String) table.getValueAt(i, 6));
			sach.setGiaSach((int) table.getValueAt(i, 7));

			sach.setMaDS(chitietLoNew);

			System.out.print(" 1111aaaaaa" + (String) table.getValueAt(i, 3));
			for (int j = 0; j < soLuong; j++) {
				QuanLyNhapLo.getInstance().InsertDataSach(sach);
			}
			;
		}
		this.setVisible(false);
	}

	// ***********************
	//
	// import file
	//

	public void ReadFile(String path) {
		try {

			if (getExtensionByStringHandling(path).get().equals("xlsx") == false) {
				System.out.print(getExtensionByStringHandling(path).get() + "  dsadas");
				JOptionPane.showMessageDialog(frame, "File không đúng định dạng!!!", "THÔNG BÁO",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			File file = new File(path); // creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file

			int countRow = sheet.getLastRowNum();
			if (countRow < 4) {
				System.out.print("Template khong dung countRow: " + countRow);
				JOptionPane.showMessageDialog(frame, "File không đúng định dạng!!!", "THÔNG BÁO",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			while (itr.hasNext()) {
				Row row = itr.next();
				// đọc dữ liêu lô
				if (row.getRowNum() == 2) {
//					 tt_NCC((int) row.getCell(1).getNumericCellValue());
					idSelectedNCC = (int) row.getCell(1).getNumericCellValue();
					tt_NCC(idSelectedNCC);
//					
					chooser_NgayNhap_QLNL.setDate(row.getCell(2).getDateCellValue());
				}
				// đọc dữ liêu sách
				if (row != null && row.getRowNum() >= 5 && row.getCell(1) != null && row.getCell(2) != null
						&& row.getCell(3) != null && row.getCell(4) != null && row.getCell(5) != null
						&& row.getCell(6) != null && row.getCell(7) != null) {

//					String[] columnNames = { "Tên sách", "Thể loại", "Năm Xuất bản", "Nhà xuất bản", "Tác giả", "Số lượng",
//							"Ngôn ngữ", "Giá sách" };

					Object[] obj = { row.getCell(1).getStringCellValue(), // ten sách
							row.getCell(2).getStringCellValue(), // thể loani
							(int) (row.getCell(3).getNumericCellValue()), // NamXB
							row.getCell(4).getStringCellValue(), // NHA XB
							row.getCell(5).getStringCellValue(), // tác giả
							(int) row.getCell(6).getNumericCellValue(), // so luong
							row.getCell(7).getStringCellValue(), // ngon ngu
							(int) row.getCell(8).getNumericCellValue() // Gia Sach
					};

					table.setModel(model);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					table.setFillsViewportHeight(true);
					model.addRow(obj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Optional<String> getExtensionByStringHandling(String filename) {
		return Optional.ofNullable(filename).filter(f -> f.contains("."))
				.map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}

//	public class RowPopup extends JPopupMenu {
//		public RowPopup(JTable table) {
//
//			// dùng để show popupSỬA
//			JMenuItem detail = new JMenuItem("Sửa TT NCC");
//			detail.addActionListener(new ActionListener() {
//
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					// TODO Auto-generated method stub
//					// JOptionPane.showMessageDialog(detail, "Xem chi tiết");
//					// QuanLyNhapLo.getInstance().select_ThongTinNCC();
//					tt_NCC(idSelectedNCC);
//					// set_Sua();
//				}
//			});
//			add(detail);
//		}
//	}
//
//	public void AddPopUp() {
//		final RowPopup pop = new RowPopup(table_ncc);
//
//		table_ncc.addMouseListener(new MouseListener() {
//
//			@Override
//			public void mouseReleased(MouseEvent e) {
//
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//
//			}
//
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// System.out.println("so row" +table_QuanLyNhapLo.getRowCount());
//				// TODO Auto-generated method stub
//				if (SwingUtilities.isRightMouseButton(e)) {
//					pop.show(e.getComponent(), e.getX(), e.getY());
//				}
//			}
//		});
//
//	}

	public void tt_NCC(int idSelectedNCC) {
		NhaCungCap NhaCungCap = QuanLyNhapLo.getInstance().select_ThongTinNCC(idSelectedNCC);
		textField_TenNCC_ThemLo.setText(NhaCungCap.getTenNhaCungCap());
		textField_SDT_ThemLo.setText(NhaCungCap.getSdtNhaCungCap());
		textField_DiaChi_ThemLo.setText(NhaCungCap.getDiaChiNhaCungCap());
	}

	public void update_ncc() {
		NhaCungCap NhaCungCap = new NhaCungCap();

		Pattern patternSDT = Pattern.compile("^0[3798]{1}\\d{8}$");

		if (textField_TenNCC_ThemLo.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_SDT_ThemLo.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
		} else if (patternSDT.matcher(textField_SDT_ThemLo.getText()).matches() == false) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đúng định dạng số điện thoại!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_DiaChi_ThemLo.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else {

			// Lấy dữ liệu nhập

			NhaCungCap.setTenNhaCungCap(textField_TenNCC_ThemLo.getText());
			NhaCungCap.setSdtNhaCungCap(textField_SDT_ThemLo.getText());
			NhaCungCap.setDiaChiNhaCungCap(textField_DiaChi_ThemLo.getText());

			int idNCCNew = QuanLyNhapLo.getInstance().UpdateNCC(NhaCungCap, idSelectedNCC);
			System.out.println(idSelectedNCC);

			// Xoá dữ liệu nhập trên màn hình
			textField_TenNCC_ThemLo.setText("");
			textField_SDT_ThemLo.setText("");
			textField_DiaChi_ThemLo.setText("");

		}
	}
}
