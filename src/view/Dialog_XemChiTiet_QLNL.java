package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.EventObject;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultCellEditor;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import database.QuanLyNhapLo;
import model.ChiTietLo;
import model.DocGia;
import model.NhaCungCap;
import model.PhieuNhapLo;
import model.Sach;

public class Dialog_XemChiTiet_QLNL extends JDialog {

	JFrame frame = new JFrame();
	public MainView frameParent;
	public int idMaPNTruyenTuParent = 0;
	private JPanel contentPane;
	private JTextField textField_TenNCC_XemChiTiet;
	private JTextField textField_DiaChi_XemChiTiet;
	private JTextField textField_ThanhToan_XemChiTiet;
	private JTextField textField_NgayNhap_XemChiTiet;
	private JTextField textField_SDT_XemChiTiet;
	private JTextField textField_MaPN_XemChiTiet;
	private JTable table_ChiTietSach;
	private JScrollPane _jscrollPane;
	public int idSelectedDauSach = 0;
	private DefaultTableModel model;
	public int idMaPNParent;
	public int thanhtoan = 0;
	public int rowTruocEdit = 0;
	public int columnTruocEdit = 0;
	private JButton btn_Sua_XemChiTiet;
	private JButton btn_Huy_XemChiTiet;
	private String TheLoai;
	private static final String solve = "Solve";

	public Dialog_XemChiTiet_QLNL(MainView parent, int idMaPN) {

		super(parent, "XEM CHI TIẾT", true);
		this.setLocationRelativeTo(null);
		this.init();
		this.setVisible(false);
		
		frameParent = parent;
		idMaPNParent = idMaPN;

		// hiện thị trung tâm màn hình
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		initTableSach();
		getPhieuNhapLo(idMaPN);
		getListSach(idMaPN);
		set_Luu();
		// DefaultTableModel model;
	}

	public void initTableSach() {
		// NumberFormat nf = NumberFormat.getCurrencyInstance();
		// init table sách
		table_ChiTietSach = new JTable();
		table_ChiTietSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table_ChiTietSach.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 DS", "T\u00EAn s\u00E1ch", "Nh\u00E0 xu\u1EA5t b\u1EA3n",
						"N\u0103m Xu\u1EA5t b\u1EA3n", "T\u00E1c gi\u1EA3", "Th\u1EC3 lo\u1EA1i", "Ng\u00F4n ng\u1EEF",
						"Gi\u00E1 s\u00E1ch", "S\u1ED1 l\u01B0\u1EE3ng"

				})
				//		{
//				// ngăn chặn chỉnh sửa giá trị
//				public boolean isCellEditable(int row, int column) {
//					if (column == 0 || column == 1 || column == 2 || column == 3 || column == 4 || column == 5
//							|| column == 6 || column == 7 || column == 8)
//						return false;
//					return super.isCellEditable(row, column);
//				}
//
//			}
						);
		// chỉ cho nhập số ở năm xb va gia sach
		try {
			MaskFormatter formatter = new MaskFormatter("####");
			formatter.setAllowsInvalid(false);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		/// our combobox thể loại
		TableColumn colTheLoai = table_ChiTietSach.getColumnModel().getColumn(5);
		JComboBox<String> TheLoai = new JComboBox<>();
		TheLoai.addItem("Chính Trị- Khoa Học");
		TheLoai.addItem("Tiểu Thuyết");
		TheLoai.addItem("Truyện Ngôn Tình");
		TheLoai.addItem("Thơ");
		TheLoai.addItem("Kinh Doanh");
		TheLoai.addItem("Tâm Lý Học");
		TheLoai.addItem("Truyện Thiếu Nhi");
		TheLoai.addItem("Trinh Thám");
		TheLoai.addItem("Văn Học");
		TheLoai.addItem("Ngoại Ngữ");
		TheLoai.addItem("Kỹ Năng Sống");
		
		colTheLoai.setCellEditor(new DefaultCellEditor(TheLoai));

		/// our combobox Ngôn ngữ
		TableColumn colNgonNgu = table_ChiTietSach.getColumnModel().getColumn(6);
		JComboBox<String> NgonNgu = new JComboBox<>();
		NgonNgu.addItem("Tiếng Anh");
		NgonNgu.addItem("Tiếng Việt");
		NgonNgu.addItem("Tiếng Hàn");
		NgonNgu.addItem("Tiếng Nhật");
		NgonNgu.addItem("Tiếng Trung");
		NgonNgu.addItem("Tiếng Mỹ");
		
		colNgonNgu.setCellEditor(new DefaultCellEditor(NgonNgu));
		
		////
		
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter2 = new NumberFormatter(format);
//
		formatter2.setMinimum(-1);
		formatter2.setMaximum(999999);
		formatter2.setAllowsInvalid(false);
		formatter2.setValueClass(Integer.class);

		table_ChiTietSach.getSelectionModel().addListSelectionListener(new ListSelectionListener() { // su kien chon 1
			// o tren table
			public void valueChanged(ListSelectionEvent event) {
				int row = table_ChiTietSach.getSelectedRow(); // lấy chỉ số của hàng được chọn trong table.
				if (row >= 0) { // Đảm bảo là có hàng được chọn
					idSelectedDauSach = Integer
							.valueOf(table_ChiTietSach.getValueAt(table_ChiTietSach.getSelectedRow(), 0).toString());

				}
			}
		});

// table thanh ddoi de tinh lai thanh toan 	
		table_ChiTietSach.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				load_ThanhToan();
			}
		});

		// Đưa dữ liệu từ biến table đưa lên giao diện cho ngta xem
		// _jscrollPane.setViewportView(table_QuanLyNhapLo);
		_jscrollPane = new JScrollPane(table_ChiTietSach);

		_jscrollPane.setBounds(9, 148, 652, 138);
		contentPane.add(_jscrollPane);

	}

	/**
	 * Create the frame.
	 */
	public void init() {

		setBounds(100, 100, 682, 380);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_TenNCC_XemChiTiet = new JLabel("Tên nhà cung cấp: ");
		lbl_TenNCC_XemChiTiet.setBounds(244, 42, 143, 20);
		lbl_TenNCC_XemChiTiet.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_TenNCC_XemChiTiet);

		JLabel lbl_SDT_XemChiTiet = new JLabel("Số điện thoại:");
		lbl_SDT_XemChiTiet.setBounds(244, 80, 102, 20);
		lbl_SDT_XemChiTiet.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_SDT_XemChiTiet);

		JLabel lbl_Diachi_XemChiTiet = new JLabel("Địa chỉ:");
		lbl_Diachi_XemChiTiet.setBounds(244, 109, 56, 20);
		lbl_Diachi_XemChiTiet.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_Diachi_XemChiTiet);

		JLabel lbl_ThanhToan_XemChiTiet = new JLabel("Thanh Toán:");
		lbl_ThanhToan_XemChiTiet.setBounds(9, 76, 96, 20);
		lbl_ThanhToan_XemChiTiet.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_ThanhToan_XemChiTiet);

		JLabel lbl_NgayNhap_XemChiTiet = new JLabel("Ngày nhập:");
		lbl_NgayNhap_XemChiTiet.setBounds(10, 109, 85, 20);
		lbl_NgayNhap_XemChiTiet.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_NgayNhap_XemChiTiet);

		JLabel lblNewLabel_7 = new JLabel("Vui lòng điền đầy đủ thông tin trước khi nhấn lưu");
		lblNewLabel_7.setBounds(314, 327, 254, 15);
		lblNewLabel_7.setForeground(new Color(128, 128, 128));
		lblNewLabel_7.setBackground(new Color(255, 255, 255));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.ITALIC, 12));
		contentPane.add(lblNewLabel_7);

		textField_TenNCC_XemChiTiet = new JTextField();
		textField_TenNCC_XemChiTiet.setBounds(387, 45, 274, 19);
		textField_TenNCC_XemChiTiet.setColumns(10);
		contentPane.add(textField_TenNCC_XemChiTiet);

		textField_DiaChi_XemChiTiet = new JTextField();
		textField_DiaChi_XemChiTiet.setBounds(387, 112, 274, 19);
		textField_DiaChi_XemChiTiet.setColumns(10);
		contentPane.add(textField_DiaChi_XemChiTiet);

		textField_ThanhToan_XemChiTiet = new JTextField();
		textField_ThanhToan_XemChiTiet.setBounds(127, 83, 107, 19);
		textField_ThanhToan_XemChiTiet.setColumns(10);
		contentPane.add(textField_ThanhToan_XemChiTiet);

		textField_NgayNhap_XemChiTiet = new JTextField();
		textField_NgayNhap_XemChiTiet.setEnabled(false);
		textField_NgayNhap_XemChiTiet.setBounds(127, 112, 107, 19);
		textField_NgayNhap_XemChiTiet.setColumns(10);
		contentPane.add(textField_NgayNhap_XemChiTiet);

		textField_SDT_XemChiTiet = new JTextField();
		textField_SDT_XemChiTiet.setBounds(387, 83, 274, 19);
		textField_SDT_XemChiTiet.setColumns(10);
		contentPane.add(textField_SDT_XemChiTiet);

		// chỉ cho nhập số
		textField_ThanhToan_XemChiTiet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}

			}
		});
		textField_SDT_XemChiTiet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}

			}
		});

		JButton btn_Luu_XemChiTiet = new JButton("Lưu");
		btn_Luu_XemChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SuaLo();
				
				SuaChiTietLo();
				getListSach(idMaPNParent);
				// frameParent.LoadDataUpdate();
				
				// Hàm này load tất cả => bỏ >> vì sài phân trang
				// phân trang gọi nÀY
				//frameParent.LoadDataList();
				frameParent.getPageData(1); // truyền 1 là lấy trang đầu tiên
				
				// Hàm này load tất cả => bỏ >> vì sài phân trang
				//frameParent.LoadTableSach();
				
				frameParent.getPageDataTable_QLSach(1);
			}
		});

		JButton btn_Huy_XemChiTiet = new JButton("Hủy");
		btn_Huy_XemChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_Huy_XemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_Huy_XemChiTiet.setBounds(138, 296, 104, 28);
		contentPane.add(btn_Huy_XemChiTiet);

		JButton btn_Sua_XemChiTiet = new JButton("Sửa");
		btn_Sua_XemChiTiet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				set_Sua();
				
			}
		});
		btn_Sua_XemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_Sua_XemChiTiet.setBounds(472, 8, 104, 28);
		contentPane.add(btn_Sua_XemChiTiet);

		btn_Luu_XemChiTiet.setBounds(394, 296, 104, 28);
		btn_Luu_XemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btn_Luu_XemChiTiet);

		JLabel lbl_Malo_XemChiTiet = new JLabel("Mã Phiếu Nhập:");
		lbl_Malo_XemChiTiet.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl_Malo_XemChiTiet.setBounds(10, 42, 117, 20);
		contentPane.add(lbl_Malo_XemChiTiet);

		textField_MaPN_XemChiTiet = new JTextField();
		textField_MaPN_XemChiTiet.setEnabled(false);
		textField_MaPN_XemChiTiet.setColumns(10);
		textField_MaPN_XemChiTiet.setBounds(127, 45, 107, 19);
		contentPane.add(textField_MaPN_XemChiTiet);

	}

	public void getPhieuNhapLo(int MaPN) {
		PhieuNhapLo losachtest = QuanLyNhapLo.getInstance().select_ThongTinLo(MaPN);
		NhaCungCap NhaCungCap = QuanLyNhapLo.getInstance().select_ThongTinCC(MaPN);
		// System.out.println(losachtest.getDiaChiNhaCungCap());
		textField_MaPN_XemChiTiet.setText(String.valueOf(losachtest.getMaPN()));
		textField_TenNCC_XemChiTiet.setText(NhaCungCap.getTenNhaCungCap());
		textField_DiaChi_XemChiTiet.setText(NhaCungCap.getDiaChiNhaCungCap());
		textField_SDT_XemChiTiet.setText(NhaCungCap.getSdtNhaCungCap());
		textField_ThanhToan_XemChiTiet.setText(String.valueOf(losachtest.getTongTienNhap()));

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(losachtest.getNgayNhap());
		textField_NgayNhap_XemChiTiet.setText(strDate);
	}

	public void Update_sach_Table(Sach sach) {

		// muốn check thêm thì check, này kiểm tra xem có bị thiếu thông tin gì của sách
		if (sach == null || sach.getTenSach() == null || sach.getSoLuong() < 0) {
			System.out.println("1111");
			return;
		}

		Object[] obj = { sach.getTenSach(), sach.getTheLoai(), sach.getNamXuatBan(), sach.getNXB(), sach.getTacGia(),
				sach.getSoLuong(), sach.getNgonNgu(), sach.getGiaSach() };

		table_ChiTietSach.setModel(model);
		table_ChiTietSach.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table_ChiTietSach.setFillsViewportHeight(true);
		model.addRow(obj);
	}

	public int SuaLo() {

		PhieuNhapLo Update_Losach = new PhieuNhapLo();

		Pattern patternDate = Pattern.compile("^\\d{4}[-]\\d{2}[-]\\d{2}$");
		Pattern patternSDT = Pattern.compile("^0[3798]{1}\\d{8}$");

		if (textField_NgayNhap_XemChiTiet.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (patternDate.matcher(textField_NgayNhap_XemChiTiet.getText()).matches() == false) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đúng định dạng ngày nhập!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);


		} else if (textField_ThanhToan_XemChiTiet.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else {

			// Lấy dữ liệu nhập
			Update_Losach.setMaPN(Integer.valueOf(textField_MaPN_XemChiTiet.getText()));
			//Update_Losach.setTenNhaCungCap(textField_TenNCC_XemChiTiet.getText());
			//Update_Losach.setDiaChiNhaCungCap(textField_DiaChi_XemChiTiet.getText());

			String ngayNhap = textField_NgayNhap_XemChiTiet.getText();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			try {
				java.util.Date date = formatter.parse(ngayNhap);
				Update_Losach.setNgayNhap(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			//Update_Losach.setSdtNhaCungCap(textField_SDT_XemChiTiet.getText());
			Update_Losach.setTongTienNhap(Integer.valueOf(textField_ThanhToan_XemChiTiet.getText()));
			
			int idLoNew = QuanLyNhapLo.getInstance().UpdateData(Update_Losach,
					Integer.valueOf(textField_MaPN_XemChiTiet.getText()));
			System.out.println("ssssssssss" + textField_MaPN_XemChiTiet.getText());
			
			// Hàm này load tất cả => bỏ >> vì sài phân trang
			//frameParent.LoadDataList();
			// phân trang gọi nÀY
			frameParent.getPageData(1); // truyền 1 là lấy trang đầu tiên			
			JOptionPane.showMessageDialog(this, "Bạn đã sửa thành công.");
			this.setVisible(false);
			return idLoNew;

		}
		return -1;
	}

	public void SuaChiTietLo() {
		// vì sửa trên table luôn

		int soLuong = 0;
		Object[] rowData = new Object[table_ChiTietSach.getRowCount()];
		for (int i = 0; i < table_ChiTietSach.getRowCount(); i++) { // lấy từng row của table sách để thực thi thêm data
																	// bảng
			// Lấy số lượng
			// soLuong = (int) table_ChiTietSach.getValueAt(i, 8);
			int maDauSach = (int) table_ChiTietSach.getValueAt(i, 0);
			// Lay đúng column trên table
			Sach sach = new Sach();
			sach.setTenSach((String) table_ChiTietSach.getValueAt(i, 1));
			sach.setTheLoai((String) table_ChiTietSach.getValueAt(i, 5));

			// vì gdien hieu là string nên integer.parseInt
			// trim bỏ khoảng trắng 2 đuầ
			sach.setNamXuatBan(Integer.parseInt(table_ChiTietSach.getValueAt(i, 3).toString().trim()));
			sach.setNXB((String) table_ChiTietSach.getValueAt(i, 2));
			sach.setTacGia((String) table_ChiTietSach.getValueAt(i, 4));
			sach.setNgonNgu((String) table_ChiTietSach.getValueAt(i, 6));
			sach.setGiaSach(Integer.parseInt(table_ChiTietSach.getValueAt(i, 7).toString().trim().replace(",", "")));

			QuanLyNhapLo.getInstance().UpdateSach(sach, maDauSach);
			// QuanLyNhapLo.getInstance().select_sachchitiet( table_ChiTietSach, maDauSach);
		}

	}

	public void getListSach(int idMaPN) {
		((DefaultTableModel) table_ChiTietSach.getModel()).setRowCount(0);
		table_ChiTietSach = QuanLyNhapLo.getInstance().select_sachchitiet(table_ChiTietSach, idMaPN);

	}

	public void set_Luu() {
		// btn_Sua_XemChiTiet.setEnabled(true);
		textField_ThanhToan_XemChiTiet.setEnabled(false);
		textField_TenNCC_XemChiTiet.setEnabled(false);
		textField_DiaChi_XemChiTiet.setEnabled(false);
		textField_SDT_XemChiTiet.setEnabled(false);
		table_ChiTietSach.setEnabled(false);
		// btn_Huy_XemChiTiet.setEnabled(true);

		System.out.println("ddddddddddđ");
	}

	public void set_Sua() {
		// btn_Sua_XemChiTiet.setEnabled(false);
		textField_ThanhToan_XemChiTiet.setEnabled(true);
		textField_TenNCC_XemChiTiet.setEnabled(false);
		textField_DiaChi_XemChiTiet.setEnabled(false);
		textField_SDT_XemChiTiet.setEnabled(false);
		table_ChiTietSach.setEnabled(true);
		// btn_Huy_XemChiTiet.setEnabled(true);
	}

	public void load_ThanhToan() {
		int soLuong = 0;
		int giaSach = 0;
		thanhtoan = 0;
		Object[] rowData = new Object[table_ChiTietSach.getRowCount()];
		for (int i = 0; i < table_ChiTietSach.getRowCount(); i++) { // lấy từng row của table sách để thực thi

			soLuong = (int) table_ChiTietSach.getValueAt(i, 8);
			giaSach = Integer.parseInt(table_ChiTietSach.getValueAt(i, 7).toString().trim().replace(",", ""));
			thanhtoan = thanhtoan + (soLuong * giaSach);
		}
		textField_ThanhToan_XemChiTiet.setText(String.valueOf(thanhtoan));
	}

}
