package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.QuanLyNhapLo;
import model.LoSach;
import model.Sach;
import javax.swing.JComboBox;

public class Dialog_SuaSach_XemChiTiet_QLNL extends JDialog {
	public int idSelectedSach = -1;
	JFrame frame = new JFrame();
	public Dialog_XemChiTiet_QLNL frameParent;
	public boolean isThemSach = false;
	private JPanel contentPane;
	private JTextField textField_TenSach_SuaSach_QLNL;
	private JTextField textField_TacGia_SuaSach_QLNL;
	private JTextField textField_TongSL_ThemLo;
	private JTextField textField_MaDS_SuaSach_QLNL;
	private JTextField textField_GiaSach_SuaSach_QLNL;
	public Sach _sach = new Sach();
	private JTextField textField_NamXB_SuaSach_QLNL;
	private JTextField textField_NhaXB_SuaSach_QLNL;
	private JTextField textField_SoLuong_SuaSach_QLNL;
	private JComboBox comboBox_NgonNgu_SuaSach_QLNL;
	private JComboBox comboBox_TheLoai_SuaSach_QLNL;
	public int idDauSach = 0;
	// private LoSach update_lo_sach;

//	private String ngonNgu = null;
//	private String theloai = null;


	// bên cha truyền qua iddausach

	public Dialog_SuaSach_XemChiTiet_QLNL(Dialog_XemChiTiet_QLNL parent, int idDauS, int malo) {

		super(parent, "Sửa Sách của Lô", true);
		this.setLocationRelativeTo(null);
		this.init();
		this.setVisible(false);

		frameParent = parent;
		idDauSach = idDauS;
		// hiện thị trung tâm màn hình
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		LayThongTinDeUpdate(idDauSach);
	}

	public void init() {
		setBounds(100, 100, 673, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_MaDS_SuaSach_QLNL = new JLabel("Mã DS:");
		lbl_MaDS_SuaSach_QLNL.setBounds(10, 10, 96, 20);
		lbl_MaDS_SuaSach_QLNL.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_MaDS_SuaSach_QLNL);

		JLabel lbl_NhaXB_SuaLo_QLNL = new JLabel("Nhà XB:");
		lbl_NhaXB_SuaLo_QLNL.setBounds(10, 127, 102, 17);
		lbl_NhaXB_SuaLo_QLNL.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_NhaXB_SuaLo_QLNL);

		JLabel lbl_TacGia_SuaSach_QLNL = new JLabel("Tác Giả:");
		lbl_TacGia_SuaSach_QLNL.setBounds(10, 85, 113, 17);
		lbl_TacGia_SuaSach_QLNL.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_TacGia_SuaSach_QLNL);

		JLabel lbl_NamXB_SuaSach_QLNL = new JLabel("Năm XB:");
		lbl_NamXB_SuaSach_QLNL.setBounds(401, 40, 114, 20);
		lbl_NamXB_SuaSach_QLNL.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_NamXB_SuaSach_QLNL);

		JLabel lbl_TenSach_SuaSach_QLNL = new JLabel("Tên Sách:");
		lbl_TenSach_SuaSach_QLNL.setBounds(10, 40, 113, 20);
		lbl_TenSach_SuaSach_QLNL.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_TenSach_SuaSach_QLNL);

		textField_MaDS_SuaSach_QLNL = new JTextField();
		textField_MaDS_SuaSach_QLNL.setEnabled(false);
		textField_MaDS_SuaSach_QLNL.setBounds(83, 10, 138, 19);
		textField_MaDS_SuaSach_QLNL.setColumns(10);
		contentPane.add(textField_MaDS_SuaSach_QLNL);

		textField_GiaSach_SuaSach_QLNL = new JTextField();
		textField_GiaSach_SuaSach_QLNL.setBounds(99, 170, 274, 19);
		textField_GiaSach_SuaSach_QLNL.setColumns(10);
		contentPane.add(textField_GiaSach_SuaSach_QLNL);

		textField_TenSach_SuaSach_QLNL = new JTextField();
		textField_TenSach_SuaSach_QLNL.setBounds(99, 40, 274, 19);
		textField_TenSach_SuaSach_QLNL.setColumns(10);
		contentPane.add(textField_TenSach_SuaSach_QLNL);

		textField_TacGia_SuaSach_QLNL = new JTextField();
		textField_TacGia_SuaSach_QLNL.setBounds(99, 85, 274, 19);
		textField_TacGia_SuaSach_QLNL.setColumns(10);
		contentPane.add(textField_TacGia_SuaSach_QLNL);

		JButton btn_Luu_SuaSach_QLNL = new JButton("Lưu");
		btn_Luu_SuaSach_QLNL.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				SuaSach();

			}
		});
		btn_Luu_SuaSach_QLNL.setBounds(203, 210, 84, 28);
		btn_Luu_SuaSach_QLNL.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(btn_Luu_SuaSach_QLNL);

		JButton btn_Huy_Suasach_QLNL = new JButton("Hủy");
		btn_Huy_Suasach_QLNL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_Huy_Suasach_QLNL.setBounds(369, 210, 84, 28);
		btn_Huy_Suasach_QLNL.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(btn_Huy_Suasach_QLNL);

		JLabel lbl_TheLoai_SuaSach_QLNL = new JLabel("Thể Loại:");
		lbl_TheLoai_SuaSach_QLNL.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl_TheLoai_SuaSach_QLNL.setBounds(401, 83, 113, 20);
		contentPane.add(lbl_TheLoai_SuaSach_QLNL);

		textField_NamXB_SuaSach_QLNL = new JTextField();
		textField_NamXB_SuaSach_QLNL.setColumns(10);
		textField_NamXB_SuaSach_QLNL.setBounds(495, 43, 154, 19);
		contentPane.add(textField_NamXB_SuaSach_QLNL);

		textField_NhaXB_SuaSach_QLNL = new JTextField();
		textField_NhaXB_SuaSach_QLNL.setColumns(10);
		textField_NhaXB_SuaSach_QLNL.setBounds(99, 127, 274, 19);
		contentPane.add(textField_NhaXB_SuaSach_QLNL);

		JLabel lbl_NgonNgu_SuaSach_QLNL = new JLabel("Ngôn Ngữ:");
		lbl_NgonNgu_SuaSach_QLNL.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl_NgonNgu_SuaSach_QLNL.setBounds(401, 128, 130, 18);
		contentPane.add(lbl_NgonNgu_SuaSach_QLNL);

		JLabel lbl_SoLuong_SuaSach_QLNL = new JLabel("Số Lượng:");
		lbl_SoLuong_SuaSach_QLNL.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl_SoLuong_SuaSach_QLNL.setBounds(401, 169, 130, 17);
		contentPane.add(lbl_SoLuong_SuaSach_QLNL);

		JLabel lbl_GiaSach_SuaLo_QLNL_3 = new JLabel("Giá Sách:");
		lbl_GiaSach_SuaLo_QLNL_3.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl_GiaSach_SuaLo_QLNL_3.setBounds(10, 170, 96, 20);
		contentPane.add(lbl_GiaSach_SuaLo_QLNL_3);

		textField_SoLuong_SuaSach_QLNL = new JTextField();
		textField_SoLuong_SuaSach_QLNL.setEnabled(false);
		textField_SoLuong_SuaSach_QLNL.setColumns(10);
		textField_SoLuong_SuaSach_QLNL.setBounds(495, 170, 154, 19);
		contentPane.add(textField_SoLuong_SuaSach_QLNL);

		comboBox_NgonNgu_SuaSach_QLNL = new JComboBox();
		comboBox_NgonNgu_SuaSach_QLNL.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox_NgonNgu_SuaSach_QLNL.setBounds(495, 127, 154, 21);
		comboBox_NgonNgu_SuaSach_QLNL.setModel(new DefaultComboBoxModel(
				new String[] { "Chọn ngôn ngữ", "Tiếng Anh", "Tiếng Việt", "Tiếng Hàn", "Tiếng Nhật", "Tiếng Trung" }));
		comboBox_NgonNgu_SuaSach_QLNL.setOpaque(true);
		comboBox_NgonNgu_SuaSach_QLNL.setBackground(Color.BLUE);
		contentPane.add(comboBox_NgonNgu_SuaSach_QLNL);

		comboBox_TheLoai_SuaSach_QLNL = new JComboBox();
		comboBox_TheLoai_SuaSach_QLNL.setBounds(495, 84, 154, 21);
		comboBox_TheLoai_SuaSach_QLNL.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox_TheLoai_SuaSach_QLNL.setModel(
				new DefaultComboBoxModel(new String[] { "Chính Trị- Khoa Học", "Tiểu Thuyết", "Truyện Ngôn Tình", "Thơ",
						"Kinh Doanh", "Tâm Lý Học", "Truyện Thiếu Nhi", "Trinh Thám", "Văn Học" }));
		comboBox_TheLoai_SuaSach_QLNL.setOpaque(true);
		comboBox_TheLoai_SuaSach_QLNL.setBackground(Color.BLUE);
		contentPane.add(comboBox_TheLoai_SuaSach_QLNL);

		////
		comboBox_NgonNgu_SuaSach_QLNL.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String selectedItem = comboBox_NgonNgu_SuaSach_QLNL.getSelectedItem().toString();
					if (selectedItem.equals("Chọn ngôn ngữ")) {
						_sach.setNgonNgu(null);

					} else if (selectedItem.equals("Tiếng Anh")) {
						_sach.setNgonNgu("Tiếng Anh");
					} else if (selectedItem.equals("Tiếng Việt")) {
						_sach.setNgonNgu("Tiếng Việt");
					} else if (selectedItem.equals("Tiếng Hàn")) {
						_sach.setNgonNgu("Tiếng Hàn");
					} else if (selectedItem.equals("Tiếng Nhật")) {
						_sach.setNgonNgu("Tiếng Nhật");
					} else if (selectedItem.equals("Tiếng Trung")) {
						_sach.setNgonNgu("Tiếng Trung");
					}
				}
			}
		});
		comboBox_NgonNgu_SuaSach_QLNL.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String selectedItem = comboBox_NgonNgu_SuaSach_QLNL.getSelectedItem().toString();
					if (selectedItem.equals("Chọn Thể loại")) {
						_sach.setTheLoai(null);

					} else if (selectedItem.equals("Chính Trị- Khoa Học")) {
						_sach.setTheLoai("Chính Trị- Khoa Học");
					} else if (selectedItem.equals("Tiểu Thuyết")) {
						_sach.setTheLoai("Tiểu Thuyết");
					} else if (selectedItem.equals("Truyện Ngôn Tình")) {
						_sach.setTheLoai("Truyện Ngôn Tình");
					} else if (selectedItem.equals("Thơ")) {
						_sach.setTheLoai("Thơ");
					} else if (selectedItem.equals("Kinh Doanh")) {
						_sach.setTheLoai("Kinh Doanh");
					} else if (selectedItem.equals("Tâm Lý Học")) {
						_sach.setTheLoai("Tâm Lý Học");
					} else if (selectedItem.equals("Truyện Thiếu Nhi")) {
						_sach.setTheLoai("Truyện Thiếu Nhi");
					} else if (selectedItem.equals("Trinh Thám")) {
						_sach.setTheLoai("Trinh Thám");
					} else if (selectedItem.equals("Văn Học")) {
						_sach.setTheLoai("Văn Học");
					}
				}
			}
		});
		////
		textField_GiaSach_SuaSach_QLNL.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}

			}

		});

	}

	// Vào hàm này gọi db lấy thông tin chitiet dau sách đó
	public void LayThongTinDeUpdate(int idDausach) {

		Sach sach = QuanLyNhapLo.getInstance().select_sachSua(idDausach);
		textField_MaDS_SuaSach_QLNL.setText(String.valueOf(sach.getMaDS()));
		textField_TenSach_SuaSach_QLNL.setText(sach.getTenSach());
		textField_NhaXB_SuaSach_QLNL.setText(sach.getNXB());
		textField_NamXB_SuaSach_QLNL.setText(String.valueOf(sach.getNamXuatBan()));
		textField_TacGia_SuaSach_QLNL.setText(String.valueOf(sach.getTacGia()));
		textField_GiaSach_SuaSach_QLNL.setText(String.valueOf(sach.getGiaSach()));
		textField_SoLuong_SuaSach_QLNL.setText(String.valueOf(sach.getSoLuong()));

		// lấy xong thì xem có d
		// neu co thì setText từng textFiled
//		System.out.println("3" +sach.getSoLuong());
//		System.out.println("1" +sach.getMaSach());
//		System.out.println("2" +sach.getTenSach());
	}

	public int SuaSach() {

		// Sach Update_sach = new Sach();

		if (textField_TenSach_SuaSach_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_TacGia_SuaSach_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_GiaSach_SuaSach_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_NamXB_SuaSach_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
		} else if (textField_NhaXB_SuaSach_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

			// frameParent.LoadDataUpdate();
		} else {

			// Lấy dữ liệu nhập
			_sach.setMaDS(Integer.valueOf(textField_MaDS_SuaSach_QLNL.getText()));
			_sach.setTenSach(textField_TenSach_SuaSach_QLNL.getText());
			_sach.setTacGia(textField_TacGia_SuaSach_QLNL.getText());
			_sach.setNamXuatBan(Integer.valueOf(textField_NamXB_SuaSach_QLNL.getText()));
			_sach.setNXB(textField_NhaXB_SuaSach_QLNL.getText());
			_sach.setGiaSach(textField_GiaSach_SuaSach_QLNL.getText());
			_sach.setSoLuong(Integer.valueOf(textField_SoLuong_SuaSach_QLNL.getText()));
			_sach.setNgonNgu(comboBox_NgonNgu_SuaSach_QLNL.getSelectedItem().toString());
			_sach.setTheLoai(comboBox_TheLoai_SuaSach_QLNL.getSelectedItem().toString());

			int idLoNew = QuanLyNhapLo.getInstance().UpdateSach(_sach,
					Integer.valueOf(textField_MaDS_SuaSach_QLNL.getText()));
			System.out.println(idLoNew);

			JOptionPane.showMessageDialog(frame, "Bạn đã sửa thành công.");
			this.setVisible(false);
			System.out.println("njnjnjnjsđsc"+
					idDauSach);
			//frameParent.getListSach(idDauSach);
			// new Dialog_ThemLo_QLNL(frame); // hien thi dialog

			// Xoá dữ liệu nhập trên màn hình
//			textField_MaLo_SuaLo_QLNL.setText("");
//			textField_TenNCC_SuaLo_QLNL.setText("");
//			textField_NgayNhap_SuaLo_QLNL.setText("");
//			textField_SDTNCC_SuaLo_QLNL.setText("");
//			textField_DiaChi_SuaLo_QLNL.setText("");
//			textField_TongTien_SuaLo_QLNL.setText("");
//			
			// trả về id Lô sách vừa mới thêm, để thực thi update data chitietlo
			return idLoNew;
		}
		return -1;
	}
//	public void update_get_Sach(int MaDS) {
//		Sach losachtest = QuanLyNhapLo.getInstance().select_ThongTinLo(MaLo);
//		textField_MaLo_SuaLo_QLNL.setText(String.valueOf(losachtest.getMaLo()));
//		// System.out.println(losachtest.getDiaChiNhaCungCap());
//		textField_TenNCC_SuaLo_QLNL.setText(String.valueOf(losachtest.getTenNhaCungCap()));
//		textField_DiaChi_SuaLo_QLNL.setText(losachtest.getDiaChiNhaCungCap());
//		textField_SDTNCC_SuaLo_QLNL.setText(losachtest.getSdtNhaCungCap());
//		textField_TongTien_SuaLo_QLNL.setText(String.valueOf(losachtest.getTongTienNhap()));
//
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String strDate = dateFormat.format(losachtest.getNgayNhap());
//		textField_NgayNhap_SuaLo_QLNL.setText(strDate);
//	}
}
