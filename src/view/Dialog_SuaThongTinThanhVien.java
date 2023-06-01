package view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import database.ThanhVien;
import model.DocGia;
import model.TheThanhVien;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class Dialog_SuaThongTinThanhVien extends JDialog {
	JFrame frame = new JFrame();
	private JPanel contentPane_DialogSTTTV;
	private DocGia docGia = new DocGia();
	private int maDG;
	private TheThanhVien theThanhVien = new TheThanhVien();
	private JTextField textField_HoTen_DialogSTTTV;
	private JTextField textField_SDT_DialogSTTTV;
	private JTextField textField_DiaChi_DialogSTTTV;
	private JLabel label_MaDocGia_DialogSTTTV;
	private JLabel label_MaThe__DialogSTTTV;
	private JLabel label_NgayDK_DialogSTTTV;
	private JLabel label_HanThe_DialogSTTTV;
	private JLabel label_PhiDK_DialogSTTTV;
	private JDateChooser chooser_NgaySinh_DialogSTTTV;

	public Dialog_SuaThongTinThanhVien(JFrame parent, int MaDG) {
		super(parent, "Sửa thông tin thành viên", true);
		maDG = MaDG;
		docGia = ThanhVien.getInstance().selectDocGiaBySDT(maDG);
		theThanhVien = ThanhVien.getInstance().selectTheThanhVienBySDT(maDG);

		this.init();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void init() {
		setBounds(100, 100, 590, 380);
		contentPane_DialogSTTTV = new JPanel();
		contentPane_DialogSTTTV.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane_DialogSTTTV);
		contentPane_DialogSTTTV.setLayout(null);

		JButton button_Luu_DialogSTTTV = new JButton("Lưu");
		button_Luu_DialogSTTTV.setFont(new Font("Tahoma", Font.BOLD, 13));
		button_Luu_DialogSTTTV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kiemTraThongTin();
				MainView.LoadTableDocGiaPM();
				//MainView.LoadTableQLTheDocGia();
			}
		});
		button_Luu_DialogSTTTV.setBounds(246, 280, 72, 31);
		contentPane_DialogSTTTV.add(button_Luu_DialogSTTTV);

		JLabel label_Ten_DialogSTTTV = new JLabel("New label");
		label_Ten_DialogSTTTV.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_Ten_DialogSTTTV.setBounds(53, 75, 72, 18);
		label_Ten_DialogSTTTV.setText("Họ và tên: ");
		contentPane_DialogSTTTV.add(label_Ten_DialogSTTTV);

		label_MaDocGia_DialogSTTTV = new JLabel("New label");
		label_MaDocGia_DialogSTTTV.setFont(new Font("Tahoma", Font.BOLD, 19));
		label_MaDocGia_DialogSTTTV.setBounds(53, 32, 200, 21);
		label_MaDocGia_DialogSTTTV.setText("Mã thành viên: " + docGia.getMaDocGia());
		contentPane_DialogSTTTV.add(label_MaDocGia_DialogSTTTV);

		JLabel label_SDT_DialogSTTTV = new JLabel("New label");
		label_SDT_DialogSTTTV.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_SDT_DialogSTTTV.setBounds(53, 115, 89, 18);
		label_SDT_DialogSTTTV.setText("Số điện thoại: ");
		contentPane_DialogSTTTV.add(label_SDT_DialogSTTTV);

		JLabel label_DiaChi_DialogSTTTV = new JLabel("New label");
		label_DiaChi_DialogSTTTV.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_DiaChi_DialogSTTTV.setBounds(53, 155, 52, 18);
		label_DiaChi_DialogSTTTV.setText("Địa chỉ: ");
		contentPane_DialogSTTTV.add(label_DiaChi_DialogSTTTV);

		JLabel label_NgaySinh_DialogSTTTV = new JLabel("New label");
		label_NgaySinh_DialogSTTTV.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_NgaySinh_DialogSTTTV.setBounds(53, 195, 78, 18);
		label_NgaySinh_DialogSTTTV.setText("Ngày sinh: ");
		contentPane_DialogSTTTV.add(label_NgaySinh_DialogSTTTV);

		label_MaThe__DialogSTTTV = new JLabel("New label");
		label_MaThe__DialogSTTTV.setFont(new Font("Tahoma", Font.BOLD, 19));
		label_MaThe__DialogSTTTV.setBounds(319, 32, 190, 21);
		label_MaThe__DialogSTTTV.setText("Mã thẻ: " + theThanhVien.getMaThe());
		contentPane_DialogSTTTV.add(label_MaThe__DialogSTTTV);

		label_NgayDK_DialogSTTTV = new JLabel("New label");
		label_NgayDK_DialogSTTTV.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_NgayDK_DialogSTTTV.setBounds(319, 75, 190, 18);
		label_NgayDK_DialogSTTTV.setText("Ngày đăng ký: " + theThanhVien.getNgayDangKy() + "");
		contentPane_DialogSTTTV.add(label_NgayDK_DialogSTTTV);

		label_HanThe_DialogSTTTV = new JLabel("New label");
		label_HanThe_DialogSTTTV.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_HanThe_DialogSTTTV.setBounds(319, 115, 190, 18);
		label_HanThe_DialogSTTTV.setText("Hạn thẻ: " + theThanhVien.getHanThe() + "");
		contentPane_DialogSTTTV.add(label_HanThe_DialogSTTTV);

		label_PhiDK_DialogSTTTV = new JLabel("New label");
		label_PhiDK_DialogSTTTV.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_PhiDK_DialogSTTTV.setBounds(319, 155, 190, 18);
		label_PhiDK_DialogSTTTV.setText("Phí đăng ký: " + theThanhVien.getPhiDangKy() + "");
		contentPane_DialogSTTTV.add(label_PhiDK_DialogSTTTV);

		textField_HoTen_DialogSTTTV = new JTextField();
		textField_HoTen_DialogSTTTV.setBounds(147, 75, 129, 20);
		textField_HoTen_DialogSTTTV.setText(docGia.getTenDocGia());
		contentPane_DialogSTTTV.add(textField_HoTen_DialogSTTTV);
		textField_HoTen_DialogSTTTV.setColumns(10);

		textField_SDT_DialogSTTTV = new JTextField();
		textField_SDT_DialogSTTTV.setColumns(10);
		textField_SDT_DialogSTTTV.setBounds(147, 115, 129, 20);
		textField_SDT_DialogSTTTV.setText(docGia.getSDT());
		contentPane_DialogSTTTV.add(textField_SDT_DialogSTTTV);

		textField_DiaChi_DialogSTTTV = new JTextField();
		textField_DiaChi_DialogSTTTV.setColumns(10);
		textField_DiaChi_DialogSTTTV.setBounds(147, 155, 129, 20);
		textField_DiaChi_DialogSTTTV.setText(docGia.getDiaChi());
		contentPane_DialogSTTTV.add(textField_DiaChi_DialogSTTTV);

		chooser_NgaySinh_DialogSTTTV = new JDateChooser();
		chooser_NgaySinh_DialogSTTTV.setDateFormatString("dd/MM/yyyy");
		chooser_NgaySinh_DialogSTTTV.getJCalendar().setMaxSelectableDate(new java.util.Date());
		chooser_NgaySinh_DialogSTTTV.setBounds(147, 195, 129, 20);
		chooser_NgaySinh_DialogSTTTV.setDate(docGia.getNgaySinh());
		contentPane_DialogSTTTV.add(chooser_NgaySinh_DialogSTTTV);
	}

	public void kiemTraThongTin() {

		// Format Chosser về dd/MM/yyy để kiểm tra tính đúng đắn của dữ liệu
		SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
		String formattedD = d.format(chooser_NgaySinh_DialogSTTTV.getDate());
		System.out.println(formattedD);

		// Bắt lỗi nhập sai định dạng
		Pattern patternDate = Pattern.compile("^\\d{2}[-|/]\\d{2}[-|/]\\d{4}$");
		Pattern patternSDT = Pattern.compile("^0[3798]{1}\\d{8}$");

		if (patternDate.matcher(formattedD).matches() == false) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đúng định dạng ngày sinh!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (patternSDT.matcher(textField_SDT_DialogSTTTV.getText()).matches() == false) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đúng định dạng số điện thoại!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
		} else {
			suaThongTin();
			dispose();
		}
		
	}

	public void suaThongTin() {

		docGia.setTenDocGia(textField_HoTen_DialogSTTTV.getText());
		docGia.setSDT(textField_SDT_DialogSTTTV.getText());
		docGia.setDiaChi(textField_DiaChi_DialogSTTTV.getText());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(chooser_NgaySinh_DialogSTTTV.getDate());
		System.out.println(formattedDate);
		Date date = Date.valueOf(formattedDate);
		System.out.println(date.toString());
		docGia.setNgaySinh(date);

		int ketQua = ThanhVien.getInstance().updateThongTinThanhVien(docGia, maDG);

		if (ketQua > 0) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Đã gia hạn thẻ thành công", "THÔNG BÁO",
					JOptionPane.INFORMATION_MESSAGE);
			// Reset bảng mới
			((DefaultTableModel) MainView.table_QLTV.getModel()).setRowCount(0);
			ThanhVien.getInstance().selectAll(MainView.table_QLTV);
			//
		} else {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Không thể gia hạn. Vui lòng thử lại", "THÔNG BÁO",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
}
