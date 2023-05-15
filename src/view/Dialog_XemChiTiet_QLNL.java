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
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.EventObject;
import java.util.regex.Pattern;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import database.QuanLyNhapLo;
import model.ChiTietLo;
import model.DocGia;
import model.LoSach;
import model.Sach;

public class Dialog_XemChiTiet_QLNL extends JDialog {

	JFrame frame = new JFrame();
	public Quan_Ly_Nhap_Lo_View frameParent;
	public int idMaLoTruyenTuParent = 0;
	private JPanel contentPane;
	private JTextField textField_TenNCC_XemChiTiet;
	private JTextField textField_DiaChi_XemChiTiet;
	private JTextField textField_ThanhToan_XemChiTiet;
	private JTextField textField_NgayNhap_XemChiTiet;
	private JTextField textField_SDT_XemChiTiet;
	private JTextField textField_MaLo_XemChiTiet;
	private JTable table_ChiTietSach;
	private JScrollPane _jscrollPane;
	public int idSelectedDauSach = 0;
	private DefaultTableModel model;
	public int idMaLoParent;
	
	public int rowTruocEdit = 0;
	public int columnTruocEdit = 0;

	public Dialog_XemChiTiet_QLNL(Quan_Ly_Nhap_Lo_View parent, int idMaLo) {

		super(parent, "XEM CHI TIẾT", true);
		this.setLocationRelativeTo(null);
		this.init();
		this.setVisible(false);
		frameParent = parent;
		idMaLoParent = idMaLo;
		// hiện thị trung tâm màn hình
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		initTableSach();
		getLoSach(idMaLo);
	    getListSach(idMaLo);
		//DefaultTableModel model;
	} 
	
//	CellEditorListener ChangeNotification = new CellEditorListener() {
//        public void editingCanceled(ChangeEvent e) {
//            System.out.println("The user canceled editing.");
//        }
//
//        public void editingStopped(ChangeEvent e) {
//            System.out.println("The user stopped editing successfully.");
//        }
//    };

	public void initTableSach() {
		 	//NumberFormat nf = NumberFormat.getCurrencyInstance();
		// init table sách
		table_ChiTietSach = new JTable();
		table_ChiTietSach.setModel(
				new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null, null } },
						new String[] { "Mã DS", "Tên sách", "Nhà xuất bản", "Năm Xuất bản", "Tác giả", "Thể loại",
								"Ngôn ngữ", "Giá sách", "Số lượng" })
					{

					// ngăn chặn chỉnh sửa giá trị
					public boolean isCellEditable(int row, int column) {
						if (column == 0 || column == 8) // chặn ma dau sach và soluong
							return false;
						
						return super.isCellEditable(row, column);
					}
					
				});
		// chỉ cho nhập số ở năm xb  va gia sach
		try {
            MaskFormatter formatter = new MaskFormatter("####");
            formatter.setAllowsInvalid(false);
            table_ChiTietSach.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(new JFormattedTextField(formatter)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
		try {
            MaskFormatter formatter = new MaskFormatter("##########");
            formatter.setAllowsInvalid(false);
            table_ChiTietSach.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(new JFormattedTextField(formatter)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
		table_ChiTietSach.getSelectionModel().addListSelectionListener(new ListSelectionListener() { // su kien chon 1
			// o tren table
			public void valueChanged(ListSelectionEvent event) {
				int row = table_ChiTietSach.getSelectedRow(); // lấy chỉ số của hàng được chọn trong table.
				if (row >= 0) { // Đảm bảo là có hàng được chọn
					idSelectedDauSach = Integer
							.valueOf(table_ChiTietSach.getValueAt(table_ChiTietSach.getSelectedRow(), 0).toString());
					
//					rowTruocEdit = table_ChiTietSach.getSelectedRow();
//					columnTruocEdit =table_ChiTietSach.getSelectedColumn();
				}
			}
		});
		
		table_ChiTietSach.getModel().addTableModelListener(new TableModelListener() {		  
		      public void tableChanged(TableModelEvent e) {
		    	  
		    	  
		         System.out.println("ccasca");
		      }
		    });
		

		// Đưa dữ liệu từ biến table đưa lên giao diện cho ngta xem
		// _jscrollPane.setViewportView(table_QuanLyNhapLo);
		_jscrollPane = new JScrollPane(table_ChiTietSach);

		_jscrollPane.setBounds(20, 138, 629, 138);
		contentPane.add(_jscrollPane);
	}

	/**
	 * Create the frame.
	 */
	public void init() {

		setBounds(100, 100, 673, 380);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_TenNCC_XemChiTiet = new JLabel("Tên nhà cung cấp: ");
		lbl_TenNCC_XemChiTiet.setBounds(227, 13, 143, 20);
		lbl_TenNCC_XemChiTiet.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_TenNCC_XemChiTiet);

		JLabel lbl_SDT_XemChiTiet = new JLabel("Số điện thoại:");
		lbl_SDT_XemChiTiet.setBounds(227, 43, 102, 20);
		lbl_SDT_XemChiTiet.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_SDT_XemChiTiet);

		JLabel lbl_Diachi_XemChiTiet = new JLabel("Địa chỉ:");
		lbl_Diachi_XemChiTiet.setBounds(227, 73, 56, 20);
		lbl_Diachi_XemChiTiet.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_Diachi_XemChiTiet);

		JLabel lbl_ThanhToan_XemChiTiet = new JLabel("Thanh Toán:");
		lbl_ThanhToan_XemChiTiet.setBounds(10, 43, 96, 20);
		lbl_ThanhToan_XemChiTiet.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_ThanhToan_XemChiTiet);

		JLabel lbl_NgayNhap_XemChiTiet = new JLabel("Ngày nhập:");
		lbl_NgayNhap_XemChiTiet.setBounds(10, 73, 85, 20);
		lbl_NgayNhap_XemChiTiet.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_NgayNhap_XemChiTiet);

		JLabel lblNewLabel_7 = new JLabel("Vui lòng điền đầy đủ thông tin trước khi nhấn lưu");
		lblNewLabel_7.setBounds(395, 286, 254, 15);
		lblNewLabel_7.setForeground(new Color(128, 128, 128));
		lblNewLabel_7.setBackground(new Color(255, 255, 255));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.ITALIC, 12));
		contentPane.add(lblNewLabel_7);

		textField_TenNCC_XemChiTiet = new JTextField();
		textField_TenNCC_XemChiTiet.setBounds(375, 16, 274, 19);
		textField_TenNCC_XemChiTiet.setColumns(10);
		contentPane.add(textField_TenNCC_XemChiTiet);

		textField_DiaChi_XemChiTiet = new JTextField();
		textField_DiaChi_XemChiTiet.setBounds(375, 76, 274, 19);
		textField_DiaChi_XemChiTiet.setColumns(10);
		contentPane.add(textField_DiaChi_XemChiTiet);

		textField_ThanhToan_XemChiTiet = new JTextField();
		textField_ThanhToan_XemChiTiet.setBounds(110, 46, 107, 19);
		textField_ThanhToan_XemChiTiet.setColumns(10);
		contentPane.add(textField_ThanhToan_XemChiTiet);

		textField_NgayNhap_XemChiTiet = new JTextField();
		textField_NgayNhap_XemChiTiet.setEnabled(false);
		textField_NgayNhap_XemChiTiet.setBounds(110, 76, 107, 19);
		textField_NgayNhap_XemChiTiet.setColumns(10);
		contentPane.add(textField_NgayNhap_XemChiTiet);

		textField_SDT_XemChiTiet = new JTextField();
		textField_SDT_XemChiTiet.setBounds(375, 43, 274, 19);
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
				// getListSach(idMaLoTruyenTuParent);

				SuaChiTietLo();
				
				//getLoSach(idMaLoParent);
			    getListSach(idMaLoParent);
				//frameParent.LoadDataUpdate();
			    frameParent.LoadDataList();
			}
		});

		btn_Luu_XemChiTiet.setBounds(479, 305, 104, 28);
		btn_Luu_XemChiTiet.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(btn_Luu_XemChiTiet);

		JLabel lbl_Malo_XemChiTiet = new JLabel("Mã Lô:");
		lbl_Malo_XemChiTiet.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl_Malo_XemChiTiet.setBounds(10, 13, 56, 20);
		contentPane.add(lbl_Malo_XemChiTiet);

		textField_MaLo_XemChiTiet = new JTextField();
		textField_MaLo_XemChiTiet.setEnabled(false);
		textField_MaLo_XemChiTiet.setColumns(10);
		textField_MaLo_XemChiTiet.setBounds(110, 13, 107, 19);
		contentPane.add(textField_MaLo_XemChiTiet);

	}



	public void getLoSach(int MaLo) {
		LoSach losachtest = QuanLyNhapLo.getInstance().select_ThongTinLo(MaLo);
		// System.out.println(losachtest.getDiaChiNhaCungCap());
		textField_MaLo_XemChiTiet.setText(String.valueOf(losachtest.getMaLo()));
		textField_TenNCC_XemChiTiet.setText(losachtest.getTenNhaCungCap());
		textField_DiaChi_XemChiTiet.setText(losachtest.getDiaChiNhaCungCap());
		textField_SDT_XemChiTiet.setText(losachtest.getSdtNhaCungCap());
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

		LoSach Update_Losach = new LoSach();

		Pattern patternDate = Pattern.compile("^\\d{4}[-]\\d{2}[-]\\d{2}$");
		Pattern patternSDT = Pattern.compile("^0[3798]{1}\\d{8}$");

		if (textField_TenNCC_XemChiTiet.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_NgayNhap_XemChiTiet.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (patternDate.matcher(textField_NgayNhap_XemChiTiet.getText()).matches() == false) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đúng định dạng ngày nhập!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_SDT_XemChiTiet.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (patternSDT.matcher(textField_SDT_XemChiTiet.getText()).matches() == false) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đúng định dạng số điện thoại!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_DiaChi_XemChiTiet.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_ThanhToan_XemChiTiet.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else {

			// Lấy dữ liệu nhập
			Update_Losach.setMaLo(Integer.valueOf(textField_MaLo_XemChiTiet.getText()));
			Update_Losach.setTenNhaCungCap(textField_TenNCC_XemChiTiet.getText());
			Update_Losach.setDiaChiNhaCungCap(textField_DiaChi_XemChiTiet.getText());

			String ngayNhap = textField_NgayNhap_XemChiTiet.getText();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			try {
				java.util.Date date = formatter.parse(ngayNhap);
				Update_Losach.setNgayNhap(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Update_Losach.setSdtNhaCungCap(textField_SDT_XemChiTiet.getText());
			Update_Losach.setTongTienNhap(Integer.valueOf(textField_ThanhToan_XemChiTiet.getText()));

			int idLoNew = QuanLyNhapLo.getInstance().UpdateData(Update_Losach,
					Integer.valueOf(textField_MaLo_XemChiTiet.getText()));
			System.out.println(idLoNew);			
			return idLoNew;
		}
		return -1;
	}

	public void SuaChiTietLo() {
		// vì sửa trên table luôn

		int soLuong = 0;
		Object[] rowData = new Object[table_ChiTietSach.getRowCount()];
		for (int i = 0; i < table_ChiTietSach.getRowCount(); i++) { // lấy từng row của table sách để thực thi thêm data bảng
			// Lấy số lượng
			// soLuong = (int) table_ChiTietSach.getValueAt(i, 8);
			int maDauSach = (int) table_ChiTietSach.getValueAt(i, 0);		
			// Lay đúng column trên table
			Sach sach = new Sach();
			sach.setTenSach((String) table_ChiTietSach.getValueAt(i, 1));
			sach.setTheLoai((String) table_ChiTietSach.getValueAt(i, 5));
			sach.setNamXuatBan((int) table_ChiTietSach.getValueAt(i, 3));
			sach.setNXB((String) table_ChiTietSach.getValueAt(i, 2));
			sach.setTacGia((String) table_ChiTietSach.getValueAt(i, 4));
			sach.setNgonNgu((String) table_ChiTietSach.getValueAt(i, 6));
			sach.setGiaSach((int) table_ChiTietSach.getValueAt(i, 7));

			QuanLyNhapLo.getInstance().UpdateSach(sach, maDauSach);
			
			
			//this.setVisible(false);
		}
		JOptionPane.showMessageDialog(this, "Bạn đã sửa thành công.");
	}
	
	public void getListSach(int idMaLo) {
		((DefaultTableModel) table_ChiTietSach.getModel()).setRowCount(0);
		table_ChiTietSach = QuanLyNhapLo.getInstance().select_sachchitiet(table_ChiTietSach, idMaLo);
	}
}
