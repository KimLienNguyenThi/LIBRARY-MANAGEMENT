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
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

public class Dialog_SuaThongTinThanhVien extends JDialog {
	JFrame frame = new JFrame();
	private JPanel contentPane;
	private DocGia docGia = new DocGia();
	private String SDT;
	private TheThanhVien theThanhVien = new TheThanhVien(); 
	private JTextField textField_HoTen_DialogSTTTV;
	private JTextField textField_SDT_DialogSTTTV;
	private JTextField textField_DiaChi_DialogSTTTV;
	private JTextField textField_NgaySinh_DialogSTTTV;
	private JLabel label_MaDocGia_DialogSTTTV;
	private JLabel label_MaThe__DialogSTTTV;
	private JLabel label_NgayDK_DialogSTTTV;
	private JLabel label_HanThe_DialogSTTTV;
	private JLabel label_PhiDK_DialogSTTTV;
	
	
	/**
	 * Launch the application.
	 */
	
	public Dialog_SuaThongTinThanhVien(JFrame parent, String sdt){
		super(parent, "Sửa thông tin thành viên", true);
		SDT = sdt;
		docGia = ThanhVien.getInstance().selectDocGiaBySDT(SDT);
		theThanhVien = ThanhVien.getInstance().selectTheThanhVienBySDT(SDT);
		
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
				kiemTraThongTin();
				dispose();
				
			}
		});
		btnNewButton.setBounds(246, 280, 72, 31);
		contentPane.add(btnNewButton);
		
		JLabel label_Ten_DialogTTV = new JLabel("New label");
		label_Ten_DialogTTV.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_Ten_DialogTTV.setBounds(53, 75, 72, 18);
		label_Ten_DialogTTV.setText("Họ và tên: ");
		contentPane.add(label_Ten_DialogTTV);
		
		label_MaDocGia_DialogSTTTV = new JLabel("New label");
		label_MaDocGia_DialogSTTTV.setFont(new Font("Times New Roman", Font.BOLD, 19));
		label_MaDocGia_DialogSTTTV.setBounds(53, 32, 200, 21);
		label_MaDocGia_DialogSTTTV.setText("Mã thành viên: " +docGia.getMaDocGia());
		contentPane.add(label_MaDocGia_DialogSTTTV);
		
		JLabel label_SDT_DialogTTV = new JLabel("New label");
		label_SDT_DialogTTV.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_SDT_DialogTTV.setBounds(53, 115, 89, 18);
		label_SDT_DialogTTV.setText("Số điện thoại: ");
		contentPane.add(label_SDT_DialogTTV);
		
		JLabel label_DiaChi_DialogTTV = new JLabel("New label");
		label_DiaChi_DialogTTV.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_DiaChi_DialogTTV.setBounds(53, 155, 52, 18);
		label_DiaChi_DialogTTV.setText("Địa chỉ: ");
		contentPane.add(label_DiaChi_DialogTTV);
		
		JLabel label_NgaySinh_DialogTTV = new JLabel("New label");
		label_NgaySinh_DialogTTV.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_NgaySinh_DialogTTV.setBounds(53, 195, 78, 18);
		label_NgaySinh_DialogTTV.setText("Ngày sinh: ");
		contentPane.add(label_NgaySinh_DialogTTV);
		
		 label_MaThe__DialogSTTTV = new JLabel("New label");
		label_MaThe__DialogSTTTV.setFont(new Font("Times New Roman", Font.BOLD, 19));
		label_MaThe__DialogSTTTV.setBounds(319, 32, 190, 21);
		label_MaThe__DialogSTTTV.setText("Mã thẻ: " +theThanhVien.getMaThe());
		contentPane.add(label_MaThe__DialogSTTTV);
		
		 label_NgayDK_DialogSTTTV = new JLabel("New label");
		label_NgayDK_DialogSTTTV.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_NgayDK_DialogSTTTV.setBounds(319, 75, 190, 18);
		label_NgayDK_DialogSTTTV.setText("Ngày đăng ký: " +theThanhVien.getNgayDangKy()+"");
		contentPane.add(label_NgayDK_DialogSTTTV);
		
		 label_HanThe_DialogSTTTV = new JLabel("New label");
		label_HanThe_DialogSTTTV.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_HanThe_DialogSTTTV.setBounds(319, 115, 190, 18);
		label_HanThe_DialogSTTTV.setText("Hạn thẻ: " +theThanhVien.getHanThe()+"");
		contentPane.add(label_HanThe_DialogSTTTV);
		
		 label_PhiDK_DialogSTTTV = new JLabel("New label");
		label_PhiDK_DialogSTTTV.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label_PhiDK_DialogSTTTV.setBounds(319, 155, 190, 18);
		label_PhiDK_DialogSTTTV.setText("Phí đăng ký: " +theThanhVien.getPhiDangKy()+"");
		contentPane.add(label_PhiDK_DialogSTTTV);
		
		textField_HoTen_DialogSTTTV = new JTextField();
		textField_HoTen_DialogSTTTV.setBounds(147, 75, 129, 20);
		textField_HoTen_DialogSTTTV.setText(docGia.getTenDocGia());
		contentPane.add(textField_HoTen_DialogSTTTV);
		textField_HoTen_DialogSTTTV.setColumns(10);
		
		textField_SDT_DialogSTTTV = new JTextField();
		textField_SDT_DialogSTTTV.setColumns(10);
		textField_SDT_DialogSTTTV.setBounds(147, 115, 129, 20);
		textField_SDT_DialogSTTTV.setText(docGia.getSDT());
		contentPane.add(textField_SDT_DialogSTTTV);
		
		textField_DiaChi_DialogSTTTV = new JTextField();
		textField_DiaChi_DialogSTTTV.setColumns(10);
		textField_DiaChi_DialogSTTTV.setBounds(147, 155, 129, 20);
		textField_DiaChi_DialogSTTTV.setText(docGia.getDiaChi());
		contentPane.add(textField_DiaChi_DialogSTTTV);
		
		textField_NgaySinh_DialogSTTTV = new JTextField();
		textField_NgaySinh_DialogSTTTV.setColumns(10);
		textField_NgaySinh_DialogSTTTV.setBounds(147, 195, 129, 20);
		textField_NgaySinh_DialogSTTTV.setText(docGia.getNgaySinh()+"");
		contentPane.add(textField_NgaySinh_DialogSTTTV);
	}
	
	
	public void kiemTraThongTin() {
		// Bắt lỗi nhập sai định dạng
				Pattern patternDate = Pattern.compile("^\\d{4}[-|/]\\d{2}[-|/]\\d{2}$");
				Pattern patternSDT = Pattern.compile("^0[3798]{1}\\d{8}$");
				
				if(patternDate.matcher(textField_NgaySinh_DialogSTTTV.getText()).matches() == false) {
					JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			        JOptionPane.showMessageDialog(frame,
			                "Vui lòng điền đúng định dạng ngày sinh!!!",
			                "THÔNG BÁO",
			                JOptionPane.ERROR_MESSAGE); 
			        
				} else if(patternSDT.matcher(textField_SDT_DialogSTTTV.getText()).matches() == false) {
						JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				        JOptionPane.showMessageDialog(frame,
				                "Vui lòng điền đúng định dạng số điện thoại!!!",
				                "THÔNG BÁO",
				                JOptionPane.ERROR_MESSAGE); 
			    } else {
			    	suaThongTin();
			    }
	}
	
	public void suaThongTin() {
		
		docGia.setTenDocGia(textField_HoTen_DialogSTTTV.getText());
		docGia.setSDT(textField_SDT_DialogSTTTV.getText());
		docGia.setDiaChi(textField_DiaChi_DialogSTTTV.getText());
		String ngaySinh = textField_NgaySinh_DialogSTTTV.getText();
		java.sql.Date date = Date.valueOf(ngaySinh);
		docGia.setNgaySinh(date);
		
					
		int ketQua = ThanhVien.getInstance().updateThongTinThanhVien(docGia, SDT);
		
		if(ketQua >0) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
	        JOptionPane.showMessageDialog(frame,
	                "Đã chỉnh sửa thông tin thành công",
	                "THÔNG BÁO",
	                JOptionPane.INFORMATION_MESSAGE);
		}else {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
	        JOptionPane.showMessageDialog(frame,
	                "Không thể chỉnh sửa. Vui lòng thử lại",
	                "THÔNG BÁO",
	                JOptionPane.INFORMATION_MESSAGE);
		}
	}
}











