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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import database.QuanLyNhapLo;
import model.ChiTietLo;
import model.DocGia;
import model.LoSach;
import model.Sach;
import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
//import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

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
	private int thanhtoan = 0;
	// private JDatePickerImpl date_NgayNhap_ThemLo;
	public JTable table;
	public DefaultTableModel model;
	private JDateChooser chooser_NgayNhap_QLNL;

	public Dialog_ThemLo_QLNL(MainView parent) {
		super(parent, "Thêm Lô", true);
		this.setLocationRelativeTo(null);
		this.init();
		// this.initDatePicker();
		this.setVisible(false);
		// hiện thị trung tâm màn hình
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		frameParent = parent;

	}

	public void init() {
		setBounds(100, 100, 673, 380);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_TenNCC_ThemLo = new JLabel("Tên nhà cung cấp: ");
		lbl_TenNCC_ThemLo.setBounds(10, 10, 143, 20);
		lbl_TenNCC_ThemLo.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_TenNCC_ThemLo);

		JLabel lbl_SDT_ThemLo = new JLabel("Số điện thoại:");
		lbl_SDT_ThemLo.setBounds(362, 40, 102, 20);
		lbl_SDT_ThemLo.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_SDT_ThemLo);

		JLabel lbl_Diachi_ThemLo = new JLabel("Địa chỉ:");
		lbl_Diachi_ThemLo.setBounds(10, 70, 56, 20);
		lbl_Diachi_ThemLo.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_Diachi_ThemLo);

		JLabel lbl_ThanhToan_ThemLo = new JLabel("Thanh Toán:");
		lbl_ThanhToan_ThemLo.setBounds(10, 40, 96, 20);
		lbl_ThanhToan_ThemLo.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_ThanhToan_ThemLo);

		JLabel lbl_NgayNhap_ThemLo = new JLabel("Ngày nhập:");
		lbl_NgayNhap_ThemLo.setBounds(362, 10, 85, 20);
		lbl_NgayNhap_ThemLo.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_NgayNhap_ThemLo);

		JLabel lblNewLabel_7 = new JLabel("Vui lòng điền đầy đủ thông tin trước khi nhấn lưu");
		lblNewLabel_7.setBounds(366, 318, 254, 15);
		lblNewLabel_7.setForeground(new Color(128, 128, 128));
		lblNewLabel_7.setBackground(new Color(255, 255, 255));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.ITALIC, 12));
		contentPane.add(lblNewLabel_7);

		textField_TenNCC_ThemLo = new JTextField();
		textField_TenNCC_ThemLo.setBounds(156, 13, 174, 19);
		textField_TenNCC_ThemLo.setColumns(10);
		contentPane.add(textField_TenNCC_ThemLo);

		textField_DiaChi_ThemLo = new JTextField();
		textField_DiaChi_ThemLo.setBounds(156, 73, 435, 19);
		textField_DiaChi_ThemLo.setColumns(10);
		contentPane.add(textField_DiaChi_ThemLo);

		textField_ThanhToan_ThemLo = new JTextField();
		textField_ThanhToan_ThemLo.setEnabled(false);
		textField_ThanhToan_ThemLo.setBounds(156, 40, 174, 19);
		textField_ThanhToan_ThemLo.setColumns(10);
		contentPane.add(textField_ThanhToan_ThemLo);

		textField_NgayNhap_ThemLo = new JTextField();
		// textField_NgayNhap_ThemLo.setBounds(467, 13, 124, 19);
		textField_NgayNhap_ThemLo.setColumns(10);
		contentPane.add(textField_NgayNhap_ThemLo);

		textField_SDT_ThemLo = new JTextField();
		textField_SDT_ThemLo.setBounds(467, 43, 124, 19);
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
				frameParent.LoadDataList();
				frameParent.LoadTableSach();
			}
		});
		btn_Luu_ThemLo.setBounds(466, 286, 69, 28);
		btn_Luu_ThemLo.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(btn_Luu_ThemLo);

		JPanel panel_ThemLo_QLNL = new JPanel();
		panel_ThemLo_QLNL.setBounds(20, 138, 629, 138);
		contentPane.add(panel_ThemLo_QLNL);
		panel_ThemLo_QLNL.setLayout(null);

		table = new JTable();
		// DefaultTableModel model = (DefaultTableModel) table.getModel();

		String maSach = null;
		String tenSach = null;
		String theLoai = null;
		int namXuatBan = 0;
		String nXB = null;
		String tacGia = null;
		int soLuong = 0;
		int giaSach = 0;
		String ngonNgu = null;
		String tinhTrang = null;

		// Đặt lại tên cho biến Table'
		String[] columnNames = { "Tên sách", "Thể loại", "Năm Xuất bản", "Nhà xuất bản", "Tác giả", "Số lượng",
				"Ngôn ngữ", "Giá sách" };
		model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(columnNames);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 629, 138);
		panel_ThemLo_QLNL.add(scrollPane);

		JButton btn_ThemSach_ThemLo = new JButton("Thêm Sách");
		btn_ThemSach_ThemLo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//cachs 1
				tinh_ThanhToan();
				//casch 2
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
		btn_ThemSach_ThemLo.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btn_ThemSach_ThemLo.setBounds(48, 95, 124, 33);
		contentPane.add(btn_ThemSach_ThemLo);

		chooser_NgayNhap_QLNL = new JDateChooser();
		chooser_NgayNhap_QLNL.setDateFormatString("dd/MM/yyyy");
		chooser_NgayNhap_QLNL.setBounds(467, 13, 124, 19);
		chooser_NgayNhap_QLNL.getJCalendar().setMaxSelectableDate(new java.util.Date());
		contentPane.add(chooser_NgayNhap_QLNL);
		
		JButton btn_Huy_ThemLo = new JButton("Hủy");
		btn_Huy_ThemLo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_Huy_ThemLo.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btn_Huy_ThemLo.setBounds(227, 286, 69, 28);
		contentPane.add(btn_Huy_ThemLo);
	}
	public void tinh_ThanhToan() {
		new DiaLog_ThemSach_QLNL(this).setVisible(true);
	}
	public int themLo() {
		// This method must return a result of type int/ bat buoc return
		LoSach _Losach = new LoSach();

		Pattern patternDate = Pattern.compile("^\\d{2}[-|/]\\d{2}[-|/]\\d{4}$");
		// Pattern patternDate = Pattern.compile("^\\d{4}[-]\\d{2}[-]\\d{2}$");
		Pattern patternSDT = Pattern.compile("^0[3798]{1}\\d{8}$");

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

			_Losach.setTenNhaCungCap(textField_TenNCC_ThemLo.getText());
			_Losach.setDiaChiNhaCungCap(textField_DiaChi_ThemLo.getText());

			// đoạn này lấy ngày selected trên giao diện, kiểm tra vs ngày hiện tại đi
			// thì check them
			// java.util.Date selectedValue = (java.util.Date)
			// date_NgayNhap_ThemLo.getModel().getValue();
			// _Losach.setNgayNhap(selectedValue);
			_Losach.setNgayNhap(chooser_NgayNhap_QLNL.getDate());
			_Losach.setSdtNhaCungCap(textField_SDT_ThemLo.getText());
			_Losach.setTongTienNhap(Integer.valueOf(textField_ThanhToan_ThemLo.getText()));

			int idLoNew = QuanLyNhapLo.getInstance().InsertData(_Losach);
			System.out.println(idLoNew);
			// new Dialog_ThemLo_QLNL(frame); // hien thi dialog

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
		Object[] rowData = new Object[table.getRowCount()];
		for (int i = 0; i < table.getRowCount(); i++) { // lấy từng row của table sách để thực thi

			soLuong = (int) table.getValueAt(i, 5);
			giaSach = (int) table.getValueAt(i, 7);
			thanhtoan = thanhtoan + (soLuong * giaSach);
		}
		textField_ThanhToan_ThemLo.setText(String.valueOf(thanhtoan) );
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
			JOptionPane.showMessageDialog(frame, "Thêm Lô Sách không thành công!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
			return;
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

//	public void initDatePicker() {
//		UtilDateModel model = new UtilDateModel();
//
//		// Sét ngày hiện tại làm mặc định
//		Calendar cal = Calendar.getInstance();
//		model.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
//		model.setSelected(true);
//
//		// Khởi tạo 1 date picker
//		JDatePanelImpl datePanel = new JDatePanelImpl(model);
//		date_NgayNhap_ThemLo = new JDatePickerImpl(datePanel, new DateLabelFormatter());
//		date_NgayNhap_ThemLo.setBounds(467, 13, 124, 19);
//		//date_NgayNhap_ThemLo.setma
//		
//		//date_NgayNhap_ThemLo.getComponent(1).setEnabled(false);
////		date_NgayNhap_ThemLo.(param -> new DateCell() {
////	        @Override
////	        public void updateItem(LocalDate date, boolean empty) {
////	            super.updateItem(date, empty);
////	            setDisable(empty || date.compareTo(LocalDate.now()) > 0 );
////	        }
////	    });
//		
//		contentPane.add(date_NgayNhap_ThemLo);
//
//	}

//	public class DateLabelFormatter extends AbstractFormatter {
//
//		private String datePattern = "yyyy-MM-dd";
//		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
//
//		@Override
//		public Object stringToValue(String text) throws ParseException {
//			return dateFormatter.parseObject(text);
//		}
//
//		@Override
//		public String valueToString(Object value) throws ParseException {
//			if (value != null) {
//				Calendar cal = (Calendar) value;
//				
//				//System.out.print("âdad");
//				return dateFormatter.format(cal.getTime());
//			}
//
//			return "";
//		}
//
//	}
}
