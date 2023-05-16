package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextArea;

public class PhieuMuonView extends JFrame {

	private JPanel contentPane;
	public static JPanel panel = new JPanel();
	public static int rowCount = 0;

	public static JTextArea textArea_TenSach = new JTextArea();
	public static JTextArea textArea_MaSach = new JTextArea();
	public static JTextArea textArea_TacGia = new JTextArea();

	public static JLabel lbl_TenNhanVien = new JLabel();
	public static JLabel lbl_time = new JLabel();
	public static JLabel lbl_NgayMuon = new JLabel();
	public static JLabel lbl_DiaChi = new JLabel();
	public static JLabel lbl_HoTen = new JLabel();
	public static JLabel lbl_SDT = new JLabel();
	public static JLabel lbl_MaThe = new JLabel();
	public static JLabel lbl_NgayTra = new JLabel();
	public static JLabel lbl_MaPm = new JLabel();

	/**
	 * Launch the application.
	 */

	public PhieuMuonView() {
		setResizable(false);
		this.init();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Create the frame.
	 */

	public void init() {

		setBounds(100, 100, 512, 420 + rowCount * 15);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 498, 383);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lbl0 = new JLabel("Phiếu mượn thư viện ABC");
		lbl0.setHorizontalAlignment(SwingConstants.CENTER);
		lbl0.setFont(new Font("Tahoma", Font.BOLD, 26));
		lbl0.setBounds(10, 10, 478, 47);
		panel.add(lbl0);

		JLabel lbl6 = new JLabel("Nhân viên: ");
		lbl6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl6.setBounds(50, 64, 60, 20);
		panel.add(lbl6);

		lbl_TenNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_TenNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_TenNhanVien.setBounds(120, 64, 131, 20);
		panel.add(lbl_TenNhanVien);

		lbl_time.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_time.setBounds(330, 64, 120, 20);
		panel.add(lbl_time);

		JSeparator separator_pm = new JSeparator();
		separator_pm.setForeground(Color.BLACK);
		separator_pm.setBounds(10, 94, 478, 2);
		panel.add(separator_pm);

		JLabel lbl1 = new JLabel("Mã thẻ");
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl1.setBounds(50, 134, 120, 20);
		panel.add(lbl1);

		JLabel lbl2 = new JLabel("Số điện thoại");
		lbl2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl2.setBounds(50, 164, 120, 20);
		panel.add(lbl2);

		JLabel lbl3 = new JLabel("Họ và tên");
		lbl3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl3.setBounds(50, 194, 120, 20);
		panel.add(lbl3);

		JLabel lbl4 = new JLabel("Địa chỉ");
		lbl4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl4.setBounds(50, 224, 120, 20);
		panel.add(lbl4);

		JLabel lbl5 = new JLabel("Ngày mượn");
		lbl5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl5.setBounds(50, 254, 93, 20);
		panel.add(lbl5);


		lbl_NgayMuon.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_NgayMuon.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_NgayMuon.setBackground(Color.WHITE);
		lbl_NgayMuon.setBounds(142, 254, 120, 20);
		panel.add(lbl_NgayMuon);

		JLabel lbl8 = new JLabel("Ngày trả");
		lbl8.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl8.setBounds(270, 254, 71, 20);
		panel.add(lbl8);


		lbl_DiaChi.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_DiaChi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_DiaChi.setBackground(Color.WHITE);
		lbl_DiaChi.setBounds(180, 224, 270, 20);
		panel.add(lbl_DiaChi);


		lbl_HoTen.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_HoTen.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_HoTen.setBackground(Color.WHITE);
		lbl_HoTen.setBounds(180, 194, 270, 20);
		panel.add(lbl_HoTen);


		lbl_SDT.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_SDT.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_SDT.setBackground(Color.WHITE);
		lbl_SDT.setBounds(180, 164, 270, 20);
		panel.add(lbl_SDT);


		lbl_MaThe.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_MaThe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_MaThe.setBackground(Color.WHITE);
		lbl_MaThe.setBounds(180, 134, 270, 20);
		panel.add(lbl_MaThe);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(10, 294, 478, 2);
		panel.add(separator_1);


		lbl_NgayTra.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_NgayTra.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_NgayTra.setBackground(Color.WHITE);
		lbl_NgayTra.setBounds(340, 254, 110, 20);
		panel.add(lbl_NgayTra);

		textArea_MaSach.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textArea_MaSach.setEditable(false);
		textArea_MaSach.setBounds(10, 316, 48, rowCount * 15);
		panel.add(textArea_MaSach);

		textArea_TenSach.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textArea_TenSach.setEditable(false);
		textArea_TenSach.setBounds(60, 316, 310, rowCount * 15);
		panel.add(textArea_TenSach);

		textArea_TacGia.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textArea_TacGia.setEditable(false);
		textArea_TacGia.setBounds(372, 316, 126, rowCount * 15);
		panel.add(textArea_TacGia);

		JLabel lblNewLabel = new JLabel("Mã sách |");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 294, 61, 20);
		panel.add(lblNewLabel);

		JLabel lblTnSch = new JLabel("Tên sách");
		lblTnSch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTnSch.setBounds(190, 294, 54, 20);
		panel.add(lblTnSch);

		JLabel lblTcGi = new JLabel("|          Tác giả");
		lblTcGi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTcGi.setBounds(368, 294, 120, 20);
		panel.add(lblTcGi);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(Color.BLACK);
		separator_1_1.setBounds(10, 312, 478, 2);
		panel.add(separator_1_1);

		JLabel lbl7 = new JLabel("Mã phiếu mượn");
		lbl7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl7.setBounds(50, 104, 120, 20);
		panel.add(lbl7);


		lbl_MaPm.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_MaPm.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_MaPm.setBackground(Color.WHITE);
		lbl_MaPm.setBounds(180, 104, 270, 20);
		panel.add(lbl_MaPm);
	}
}
