package view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import database.ThanhVien;
import model.DocGia;
import model.TheThanhVien;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class Dialog_ThemDocGia extends JDialog {
	JFrame frame = new JFrame();
	private JPanel contentPane;
	private DocGia docGia = new DocGia();
	private TheThanhVien theThanhVien = new TheThanhVien();

	/**
	 * Launch the application.
	 */

	public Dialog_ThemDocGia(JFrame parent, DocGia dg, TheThanhVien ttv) {
		super(parent, "Kiểm tra thông tin", true);
		docGia = dg;
		theThanhVien = ttv;
		this.init();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */

	public void init() {
		setBounds(100, 100, 590, 380);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Lưu");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThanhVien.getInstance().themThanhVien(docGia, theThanhVien);
				MainView.LoadTableDocGiaPM();
				MainView.LoadTableQLTheDocGia();
				dispose();
			}
		});
		btnNewButton.setBounds(246, 280, 72, 31);
		contentPane.add(btnNewButton);

		JLabel label_Ten_DialogTTV = new JLabel("New label");
		label_Ten_DialogTTV.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_Ten_DialogTTV.setBounds(53, 75, 200, 18);
		label_Ten_DialogTTV.setText("Họ và tên: " + docGia.getTenDocGia());
		contentPane.add(label_Ten_DialogTTV);

		JLabel lblMcGi = new JLabel("New label");
		lblMcGi.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblMcGi.setBounds(53, 32, 200, 21);
		lblMcGi.setText("Mã độc giả: 14");
		contentPane.add(lblMcGi);
		theThanhVien.setMaDocGia(ThanhVien.getInstance().selectRowLast_DocGia() + 1);

		JLabel label_SDT_DialogTTV = new JLabel("New label");
		label_SDT_DialogTTV.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_SDT_DialogTTV.setBounds(53, 115, 200, 18);
		label_SDT_DialogTTV.setText("Số điện thoại: " + docGia.getSDT());
		contentPane.add(label_SDT_DialogTTV);

		JLabel label_DiaChi_DialogTTV = new JLabel("New label");
		label_DiaChi_DialogTTV.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_DiaChi_DialogTTV.setBounds(53, 155, 200, 18);
		label_DiaChi_DialogTTV.setText("Địa chỉ: " + docGia.getDiaChi());
		contentPane.add(label_DiaChi_DialogTTV);

		JLabel label_NgaySinh_DialogTTV = new JLabel("New label");
		label_NgaySinh_DialogTTV.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_NgaySinh_DialogTTV.setBounds(53, 195, 200, 18);
		label_NgaySinh_DialogTTV.setText("Ngày sinh: " + docGia.getNgaySinh());
		contentPane.add(label_NgaySinh_DialogTTV);

		JLabel label_MaThe__DialogTTV = new JLabel("New label");
		label_MaThe__DialogTTV.setFont(new Font("Tahoma", Font.BOLD, 19));
		label_MaThe__DialogTTV.setBounds(319, 32, 190, 21);
		label_MaThe__DialogTTV.setText("Mã thẻ: " + (ThanhVien.getInstance().selectRowLast_TheThanhVien() + 1));
		contentPane.add(label_MaThe__DialogTTV);

		JLabel label_NgayDK_DialogTTV = new JLabel("New label");
		label_NgayDK_DialogTTV.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_NgayDK_DialogTTV.setBounds(319, 75, 190, 18);
		label_NgayDK_DialogTTV.setText("Ngày đăng ký: " + theThanhVien.getNgayDangKy());
		contentPane.add(label_NgayDK_DialogTTV);

		JLabel label_HanThe_DialogTTV = new JLabel("New label");
		label_HanThe_DialogTTV.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_HanThe_DialogTTV.setBounds(319, 115, 190, 18);
		label_HanThe_DialogTTV.setText("Hạn thẻ: " + theThanhVien.getHanThe());
		contentPane.add(label_HanThe_DialogTTV);

		JLabel label_PhiDK_DialogTTV = new JLabel("New label");
		label_PhiDK_DialogTTV.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_PhiDK_DialogTTV.setBounds(319, 155, 190, 18);
		label_PhiDK_DialogTTV.setText("Phí đăng ký: " + theThanhVien.getPhiDangKy());
		contentPane.add(label_PhiDK_DialogTTV);
	}

}
