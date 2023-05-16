package view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import database.ThanhVien;
import model.DocGia;
import model.TheThanhVien;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.XDevAPIError;
import com.toedter.calendar.JDateChooser;

public class Dialog_GiaHanTheThanhVien extends JDialog {
	JFrame frame = new JFrame();
	private JPanel contentPane_DialogGHT;
	private DocGia docGia = new DocGia();
	private int maDG;
	private TheThanhVien theThanhVien = new TheThanhVien();
	private JLabel label_MaDocGia_DialogGHT;
	private JLabel label_MaThe_DialogGHT;
	private JLabel label_NgayDK_DialogSTTTV;
	private JLabel label_PhiDangKy_DialogGHT;
	private JLabel label_PhiDK_DialogGHT;
	private JComboBox comboBox_HanThe_DialogGHT;
	private JLabel lable_GiaHanThem_DialogGHT;

	public Dialog_GiaHanTheThanhVien(JFrame parent, int MaDG) {
		super(parent, "Gia hạn thẻ thành viên", true);
		maDG = MaDG;
		docGia = ThanhVien.getInstance().selectDocGiaBySDT(maDG);
		theThanhVien = ThanhVien.getInstance().selectTheThanhVienBySDT(maDG);

		// kiểm tra hạn thẻ có hết hạn chưa
		long time = System.currentTimeMillis();
		Date d = new Date(time);

		if (theThanhVien.getHanThe().after(d) == true) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Thẻ chưa hết hạn!!!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
		} else {
			this.init();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		}
	}

	public void init() {
		setBounds(100, 100, 590, 380);
		contentPane_DialogGHT = new JPanel();
		contentPane_DialogGHT.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane_DialogGHT);
		contentPane_DialogGHT.setLayout(null);

		JButton button_Luu_DialogGHT = new JButton("Lưu");
		button_Luu_DialogGHT.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_Luu_DialogGHT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				giaHanThe();
			}
		});
		button_Luu_DialogGHT.setBounds(246, 280, 72, 31);
		contentPane_DialogGHT.add(button_Luu_DialogGHT);

		JLabel label_HoTen_DialogGHT = new JLabel("New label");
		label_HoTen_DialogGHT.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_HoTen_DialogGHT.setBounds(53, 75, 200, 18);
		label_HoTen_DialogGHT.setText("Họ và tên: " + docGia.getTenDocGia());
		contentPane_DialogGHT.add(label_HoTen_DialogGHT);

		label_MaDocGia_DialogGHT = new JLabel("New label");
		label_MaDocGia_DialogGHT.setFont(new Font("Times New Roman", Font.BOLD, 19));
		label_MaDocGia_DialogGHT.setBounds(53, 32, 200, 21);
		label_MaDocGia_DialogGHT.setText("Mã thành viên: " + docGia.getMaDocGia());
		contentPane_DialogGHT.add(label_MaDocGia_DialogGHT);

		JLabel label_SDT_DialogGHT = new JLabel("New label");
		label_SDT_DialogGHT.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_SDT_DialogGHT.setBounds(53, 115, 200, 18);
		label_SDT_DialogGHT.setText("Số điện thoại: " + docGia.getSDT());
		contentPane_DialogGHT.add(label_SDT_DialogGHT);

		JLabel label_DiaChi_DialogGHT = new JLabel("New label");
		label_DiaChi_DialogGHT.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_DiaChi_DialogGHT.setBounds(53, 155, 200, 18);
		label_DiaChi_DialogGHT.setText("Địa chỉ: " + docGia.getDiaChi());
		contentPane_DialogGHT.add(label_DiaChi_DialogGHT);

		JLabel label_NgaySinh_DialogGHT = new JLabel("New label");
		label_NgaySinh_DialogGHT.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_NgaySinh_DialogGHT.setBounds(53, 195, 200, 18);
		label_NgaySinh_DialogGHT.setText("Ngày sinh: " + docGia.getNgaySinh());
		contentPane_DialogGHT.add(label_NgaySinh_DialogGHT);

		label_MaThe_DialogGHT = new JLabel("New label");
		label_MaThe_DialogGHT.setFont(new Font("Times New Roman", Font.BOLD, 19));
		label_MaThe_DialogGHT.setBounds(319, 32, 190, 21);
		label_MaThe_DialogGHT.setText("Mã thẻ: " + theThanhVien.getMaThe());
		contentPane_DialogGHT.add(label_MaThe_DialogGHT);

		label_PhiDangKy_DialogGHT = new JLabel("");
		label_PhiDangKy_DialogGHT.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_PhiDangKy_DialogGHT.setBounds(422, 115, 112, 21);
		contentPane_DialogGHT.add(label_PhiDangKy_DialogGHT);

		label_PhiDK_DialogGHT = new JLabel("New label");
		label_PhiDK_DialogGHT.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_PhiDK_DialogGHT.setBounds(319, 115, 82, 18);
		label_PhiDK_DialogGHT.setText("Phí đăng ký: ");
		contentPane_DialogGHT.add(label_PhiDK_DialogGHT);

		comboBox_HanThe_DialogGHT = new JComboBox();
		comboBox_HanThe_DialogGHT.setBackground(new Color(230, 230, 250));
		comboBox_HanThe_DialogGHT.setModel(new DefaultComboBoxModel(
				new String[] { "Chọn hạn thẻ", "12 tháng", "24 tháng", "36 tháng", "48 tháng" }));
		comboBox_HanThe_DialogGHT.setBounds(422, 74, 112, 22);
		comboBox_HanThe_DialogGHT.setOpaque(true);
		comboBox_HanThe_DialogGHT.setBackground(Color.BLUE);
		comboBox_HanThe_DialogGHT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tinhToanPhiDangKy();
			}
		});
		contentPane_DialogGHT.add(comboBox_HanThe_DialogGHT);

		lable_GiaHanThem_DialogGHT = new JLabel("Gia hạn thêm: ");
		lable_GiaHanThem_DialogGHT.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lable_GiaHanThem_DialogGHT.setBounds(319, 74, 93, 21);
		contentPane_DialogGHT.add(lable_GiaHanThem_DialogGHT);
	}

	public void giaHanThe() {
		// Nhập hạn thẻ
		Calendar c = Calendar.getInstance();
		long millis = System.currentTimeMillis();

		if (comboBox_HanThe_DialogGHT.getSelectedItem().equals("12 tháng")) {
			c.add(Calendar.YEAR, 1);
			long millis_1Y = TimeUnit.DAYS.toMillis(365);
			Date date3 = new Date(millis + millis_1Y);
			theThanhVien.setHanThe(date3);

		} else if (comboBox_HanThe_DialogGHT.getSelectedItem().equals("24 tháng")) {
			c.add(Calendar.YEAR, 2);
			long millis_2Y = TimeUnit.DAYS.toMillis(365 * 2);
			Date date3 = new Date(millis + millis_2Y);
			theThanhVien.setHanThe(date3);

		} else if (comboBox_HanThe_DialogGHT.getSelectedItem().equals("36 tháng")) {
			c.add(Calendar.YEAR, 3);
			long millis_3Y = TimeUnit.DAYS.toMillis(365 * 3);
			Date date3 = new Date(millis + millis_3Y);
			theThanhVien.setHanThe(date3);

		} else if (comboBox_HanThe_DialogGHT.getSelectedItem().equals("48 tháng")) {
			c.add(Calendar.YEAR, 4);
			long millis_4Y = TimeUnit.DAYS.toMillis(365 * 4);
			Date date3 = new Date(millis + millis_4Y);
			theThanhVien.setHanThe(date3);
		}

		theThanhVien.setPhiDangKy(Integer.valueOf(label_PhiDangKy_DialogGHT.getText()));

		java.sql.Date date = new java.sql.Date(millis);
		theThanhVien.setNgayDangKy(date);

		if (comboBox_HanThe_DialogGHT.getSelectedItem().equals("Chọn hạn thẻ")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng chọn hạn thẻ!!!", "THÔNG BÁO", JOptionPane.ERROR_MESSAGE);
		} else {
			int ketQua = ThanhVien.getInstance().updateTheThanhVien(theThanhVien, docGia, maDG);

			if (ketQua > 0) {
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				JOptionPane.showMessageDialog(frame, "Đã chỉnh sửa thông tin thành công", "THÔNG BÁO",
						JOptionPane.INFORMATION_MESSAGE);
				// Reset bảng mới
				((DefaultTableModel) MainView.table_QLTV.getModel()).setRowCount(0);
				ThanhVien.getInstance().selectAll(MainView.table_QLTV);
				//
			} else {
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				JOptionPane.showMessageDialog(frame, "Không thể chỉnh sửa. Vui lòng thử lại", "THÔNG BÁO",
						JOptionPane.INFORMATION_MESSAGE);
			}
			dispose();
		}
	}

	public void tinhToanPhiDangKy() {
		if (comboBox_HanThe_DialogGHT.getSelectedItem().equals("Chọn hạn thẻ")) {
			label_PhiDangKy_DialogGHT.setText("");
		} else if (comboBox_HanThe_DialogGHT.getSelectedItem().equals("12 tháng")) {
			label_PhiDangKy_DialogGHT.setText("100000");
		} else if (comboBox_HanThe_DialogGHT.getSelectedItem().equals("24 tháng")) {
			label_PhiDangKy_DialogGHT.setText("200000");
		} else if (comboBox_HanThe_DialogGHT.getSelectedItem().equals("36 tháng")) {
			label_PhiDangKy_DialogGHT.setText("300000");
		} else if (comboBox_HanThe_DialogGHT.getSelectedItem().equals("48 tháng")) {
			label_PhiDangKy_DialogGHT.setText("400000");
		}
	}
}