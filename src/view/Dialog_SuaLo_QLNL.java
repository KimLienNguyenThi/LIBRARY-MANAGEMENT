package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.QuanLyNhapLo;
import model.LoSach;
import model.Sach;

public class Dialog_SuaLo_QLNL extends JDialog {
	JFrame frame = new JFrame();
	public Quan_Ly_Nhap_Lo_View frameParent;
	public boolean isThemSach = false;
	private JPanel contentPane;
	private JTextField textField_TenNCC_SuaLo_QLNL;
	private JTextField textField_DiaChi_SuaLo_QLNL;
	private JTextField textField_TongSL_ThemLo;
	private JTextField textField_MaLo_SuaLo_QLNL;
	private JTextField textField_TongTien_SuaLo_QLNL;
	public Sach _sach = new Sach();
	private JTextField textField_NgayNhap_SuaLo_QLNL;
	private JTextField textField_SDTNCC_SuaLo_QLNL;
	//private LoSach update_lo_sach;
	
	public Dialog_SuaLo_QLNL(Quan_Ly_Nhap_Lo_View parent, int idMaLo) {

		super(parent, "Sửa lô", true);
		this.setLocationRelativeTo(null);
		this.init();
		this.setVisible(false);
		
		frameParent =parent;
		// hiện thị trung tâm màn hình
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		update_get_LoSach(idMaLo);
	}

	public void init() {
		setBounds(100, 100, 673, 250);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_MaLo_SuaLo_QLNL = new JLabel("Mã Lô:");
		lbl_MaLo_SuaLo_QLNL.setBounds(10, 17, 96, 20);
		lbl_MaLo_SuaLo_QLNL.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_MaLo_SuaLo_QLNL);

		JLabel lbl_SDTNCC_SuaLo_QLNL = new JLabel("SDT NCC:");
		lbl_SDTNCC_SuaLo_QLNL.setBounds(418, 17, 102, 20);
		lbl_SDTNCC_SuaLo_QLNL.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_SDTNCC_SuaLo_QLNL);

		JLabel lbl_DiaChi_SuaLo_QLNL = new JLabel("Địa chỉ:");
		lbl_DiaChi_SuaLo_QLNL.setBounds(10, 106, 113, 20);
		lbl_DiaChi_SuaLo_QLNL.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_DiaChi_SuaLo_QLNL);

		JLabel lbl_NgayNhap_SuaLo_QLNL = new JLabel("Ngày Nhập:");
		lbl_NgayNhap_SuaLo_QLNL.setBounds(418, 60, 96, 20);
		lbl_NgayNhap_SuaLo_QLNL.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_NgayNhap_SuaLo_QLNL);

		JLabel lbl_TenNCC_SuaLo_QLNL = new JLabel("Tên NCC:");
		lbl_TenNCC_SuaLo_QLNL.setBounds(10, 60, 113, 20);
		lbl_TenNCC_SuaLo_QLNL.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_TenNCC_SuaLo_QLNL);

		textField_MaLo_SuaLo_QLNL = new JTextField();
		textField_MaLo_SuaLo_QLNL.setEnabled(false);
		textField_MaLo_SuaLo_QLNL.setBounds(99, 20, 274, 19);
		textField_MaLo_SuaLo_QLNL.setColumns(10);
		contentPane.add(textField_MaLo_SuaLo_QLNL);

		textField_TongTien_SuaLo_QLNL = new JTextField();
		textField_TongTien_SuaLo_QLNL.setBounds(512, 109, 122, 19);
		textField_TongTien_SuaLo_QLNL.setColumns(10);
		contentPane.add(textField_TongTien_SuaLo_QLNL);

		textField_TenNCC_SuaLo_QLNL = new JTextField();
		textField_TenNCC_SuaLo_QLNL.setBounds(99, 63, 274, 19);
		textField_TenNCC_SuaLo_QLNL.setColumns(10);
		contentPane.add(textField_TenNCC_SuaLo_QLNL);

		textField_DiaChi_SuaLo_QLNL = new JTextField();
		textField_DiaChi_SuaLo_QLNL.setBounds(99, 109, 274, 19);
		textField_DiaChi_SuaLo_QLNL.setColumns(10);
		contentPane.add(textField_DiaChi_SuaLo_QLNL);

		JButton btn_ThemSach_QLNL = new JButton("Lưu");
		btn_ThemSach_QLNL.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// themSach();
				SuaLo();
				frameParent.LoadDataUpdate();
				
				
			}
		});
		btn_ThemSach_QLNL.setBounds(222, 166, 84, 28);
		btn_ThemSach_QLNL.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(btn_ThemSach_QLNL);

		JButton btn_Huy_QLNL = new JButton("Hủy");
		btn_Huy_QLNL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_Huy_QLNL.setBounds(385, 166, 84, 28);
		btn_Huy_QLNL.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(btn_Huy_QLNL);

		JLabel lbl_TongTien_SuaLo_QLNL = new JLabel("Tổng Tiền:");
		lbl_TongTien_SuaLo_QLNL.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl_TongTien_SuaLo_QLNL.setBounds(418, 108, 84, 20);
		contentPane.add(lbl_TongTien_SuaLo_QLNL);

		textField_NgayNhap_SuaLo_QLNL = new JTextField();
		textField_NgayNhap_SuaLo_QLNL.setColumns(10);
		textField_NgayNhap_SuaLo_QLNL.setBounds(512, 63, 122, 19);
		contentPane.add(textField_NgayNhap_SuaLo_QLNL);

		textField_SDTNCC_SuaLo_QLNL = new JTextField();
		textField_SDTNCC_SuaLo_QLNL.setColumns(10);
		textField_SDTNCC_SuaLo_QLNL.setBounds(512, 20, 122, 19);
		contentPane.add(textField_SDTNCC_SuaLo_QLNL);
		textField_TongTien_SuaLo_QLNL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}

			}
		});

	}

	public int SuaLo() {

		LoSach Update_Losach = new LoSach();

		Pattern patternDate = Pattern.compile("^\\d{4}[-]\\d{2}[-]\\d{2}$");
		Pattern patternSDT = Pattern.compile("^0[3798]{1}\\d{8}$");

		if (textField_TenNCC_SuaLo_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_NgayNhap_SuaLo_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (patternDate.matcher(textField_NgayNhap_SuaLo_QLNL.getText()).matches() == false) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đúng định dạng ngày nhập!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_SDTNCC_SuaLo_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (patternSDT.matcher(textField_SDTNCC_SuaLo_QLNL.getText()).matches() == false) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đúng định dạng số điện thoại!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_DiaChi_SuaLo_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_TongTien_SuaLo_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
			//frameParent.LoadDataUpdate();
		} else {

			// Lấy dữ liệu nhập
			Update_Losach.setMaLo(Integer.valueOf(textField_MaLo_SuaLo_QLNL.getText()));
			Update_Losach.setTenNhaCungCap(textField_TenNCC_SuaLo_QLNL.getText());
			Update_Losach.setDiaChiNhaCungCap(textField_DiaChi_SuaLo_QLNL.getText());

			String ngayNhap = textField_NgayNhap_SuaLo_QLNL.getText();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			
			try {
				java.util.Date date = formatter.parse(ngayNhap);
				Update_Losach.setNgayNhap(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Update_Losach.setSdtNhaCungCap(textField_SDTNCC_SuaLo_QLNL.getText());
			Update_Losach.setTongTienNhap(Integer.valueOf(textField_TongTien_SuaLo_QLNL.getText()));

			int idLoNew = QuanLyNhapLo.getInstance().UpdateData(Update_Losach,Integer.valueOf(textField_MaLo_SuaLo_QLNL.getText()) );
			System.out.println(idLoNew);
			
			JOptionPane.showMessageDialog(frame, "Bạn đã sửa thành công.");
			this.setVisible(false);
			
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
	public void update_get_LoSach(int MaLo) {
		LoSach losachtest = QuanLyNhapLo.getInstance().select_ThongTinLo(MaLo);
		textField_MaLo_SuaLo_QLNL.setText(String.valueOf(losachtest.getMaLo()));
		// System.out.println(losachtest.getDiaChiNhaCungCap());
		textField_TenNCC_SuaLo_QLNL.setText(String.valueOf(losachtest.getTenNhaCungCap()));
		textField_DiaChi_SuaLo_QLNL.setText(losachtest.getDiaChiNhaCungCap());
		textField_SDTNCC_SuaLo_QLNL.setText(losachtest.getSdtNhaCungCap());
		textField_TongTien_SuaLo_QLNL.setText(String.valueOf(losachtest.getTongTienNhap()));

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(losachtest.getNgayNhap());
		textField_NgayNhap_SuaLo_QLNL.setText(strDate);
	}
}
