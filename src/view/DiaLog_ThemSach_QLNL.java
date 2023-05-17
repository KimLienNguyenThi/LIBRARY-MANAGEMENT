package view;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.AttributeSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import com.google.protobuf.Method;
import database.QuanLyNhapLo;
import model.ChiTietLo;
import model.LoSach;
import model.Sach;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DiaLog_ThemSach_QLNL extends JDialog {
	JFrame frame = new JFrame();
	public boolean isThemSach = false;
	private JPanel contentPane;
	private Dialog_ThemLo_QLNL frameParent;
	private JTextField textField_NhaXB_ThemSach_QLNL;
	private JTextField textField_TacGia_ThemSach_QLNL;
	private JTextField textField_TongSL_ThemLo;
	private JTextField textField_TenSach_ThemSach_QLNL;
	private JTextField textField_NamXB_ThemSach_QLNL;
	private JTextField textField_GiaSach_ThemSach_QLNL;
	private JTextField textField_SoLuong_ThemSach_QLNL;
	private JComboBox comboBox_NgonNgu_ThemSach_QLNL;
	private JComboBox comboBox_TheLoai_ThemSach_QLNL;
	public Sach _sach = new Sach();
	public DiaLog_ThemSach_QLNL(Dialog_ThemLo_QLNL parent) {
		super(parent, "Thêm Sách", true);
		frameParent = parent;
		this.setLocationRelativeTo(null);
		this.init();
		this.setVisible(false);
	}

	public void init() {
		setBounds(100, 100, 673, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_TenSach_ThemSach_QLNL = new JLabel("Tên sách:");
		lbl_TenSach_ThemSach_QLNL.setBounds(16, 17, 96, 20);
		lbl_TenSach_ThemSach_QLNL.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_TenSach_ThemSach_QLNL);

		JLabel lbl_Tacgia_ThemSach_QLNL = new JLabel("Tác giả:");
		lbl_Tacgia_ThemSach_QLNL.setBounds(16, 60, 102, 20);
		lbl_Tacgia_ThemSach_QLNL.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_Tacgia_ThemSach_QLNL);

		JLabel lbl_NamXB_ThemSach_QLNL = new JLabel("Năm Xuất bản:");
		lbl_NamXB_ThemSach_QLNL.setBounds(16, 147, 113, 20);
		lbl_NamXB_ThemSach_QLNL.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_NamXB_ThemSach_QLNL);

		JLabel lbl_TheLoai_ThemSach_QLNL = new JLabel("Thể loại:");
		lbl_TheLoai_ThemSach_QLNL.setBounds(388, 17, 96, 20);
		lbl_TheLoai_ThemSach_QLNL.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_TheLoai_ThemSach_QLNL);

		JLabel lbl_NhaXB_ThemSach_QLNL = new JLabel("Nhà Xuất bản:");
		lbl_NhaXB_ThemSach_QLNL.setBounds(16, 104, 113, 20);
		lbl_NhaXB_ThemSach_QLNL.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_NhaXB_ThemSach_QLNL);

		textField_TenSach_ThemSach_QLNL = new JTextField();
		textField_TenSach_ThemSach_QLNL.setBounds(133, 20, 200, 19);
		textField_TenSach_ThemSach_QLNL.setColumns(10);
		contentPane.add(textField_TenSach_ThemSach_QLNL);

		textField_NamXB_ThemSach_QLNL = new JTextField();
		textField_NamXB_ThemSach_QLNL.setBounds(133, 150, 200, 19);
		textField_NamXB_ThemSach_QLNL.setColumns(10);
		contentPane.add(textField_NamXB_ThemSach_QLNL);

		textField_NhaXB_ThemSach_QLNL = new JTextField();
		textField_NhaXB_ThemSach_QLNL.setBounds(133, 63, 200, 19);
		textField_NhaXB_ThemSach_QLNL.setColumns(10);
		contentPane.add(textField_NhaXB_ThemSach_QLNL);

		textField_TacGia_ThemSach_QLNL = new JTextField();
		textField_TacGia_ThemSach_QLNL.setBounds(133, 107, 200, 19);
		textField_TacGia_ThemSach_QLNL.setColumns(10);
		contentPane.add(textField_TacGia_ThemSach_QLNL);

		JButton btn_ThemSach_QLNL = new JButton("Thêm");
		btn_ThemSach_QLNL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				themSach();			
				frameParent.them_sach_Table(_sach);
				frameParent.ThanhToan();
			}
		});
		btn_ThemSach_QLNL.setBounds(355, 195, 84, 28);
		btn_ThemSach_QLNL.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(btn_ThemSach_QLNL);

		JButton btn_Huy_QLNL = new JButton("Hủy");
		btn_Huy_QLNL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_Huy_QLNL.setBounds(477, 195, 84, 28);
		btn_Huy_QLNL.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(btn_Huy_QLNL);
		
		
		JLabel lbl_NgonNgu_ThemSach_QLNL = new JLabel("Ngôn ngữ:");
		lbl_NgonNgu_ThemSach_QLNL.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl_NgonNgu_ThemSach_QLNL.setBounds(388, 60, 84, 20);
		contentPane.add(lbl_NgonNgu_ThemSach_QLNL);

		JLabel lbl_GiaSach_ThemSach_QLNL = new JLabel("Giá sách:");
		lbl_GiaSach_ThemSach_QLNL.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl_GiaSach_ThemSach_QLNL.setBounds(388, 104, 84, 20);
		contentPane.add(lbl_GiaSach_ThemSach_QLNL);

		textField_GiaSach_ThemSach_QLNL = new JTextField();
		textField_GiaSach_ThemSach_QLNL.setColumns(10);
		textField_GiaSach_ThemSach_QLNL.setBounds(471, 107, 137, 19);
	
		contentPane.add(textField_GiaSach_ThemSach_QLNL);

		JLabel lbl_SoLuong_ThemSach_QLNL = new JLabel("Số lượng:");
		lbl_SoLuong_ThemSach_QLNL.setFont(new Font("Dialog", Font.BOLD, 15));
		lbl_SoLuong_ThemSach_QLNL.setBounds(388, 147, 84, 20);
		contentPane.add(lbl_SoLuong_ThemSach_QLNL);

		textField_SoLuong_ThemSach_QLNL = new JTextField();
		
		// Sự kiện bắt chỉ cho nhập số
		textField_SoLuong_ThemSach_QLNL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				}
		
			}
		});
		textField_GiaSach_ThemSach_QLNL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				}
		
			}
		});
		textField_NamXB_ThemSach_QLNL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				}
		
			}
		});
		
		textField_SoLuong_ThemSach_QLNL.setColumns(10);
		textField_SoLuong_ThemSach_QLNL.setBounds(471, 150, 137, 19);
		contentPane.add(textField_SoLuong_ThemSach_QLNL);

		comboBox_NgonNgu_ThemSach_QLNL = new JComboBox();
		comboBox_NgonNgu_ThemSach_QLNL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		comboBox_NgonNgu_ThemSach_QLNL.setBounds(471, 62, 137, 21);
		comboBox_NgonNgu_ThemSach_QLNL.setModel(new DefaultComboBoxModel(
				new String[] { "Tiếng Anh", "Tiếng Việt", "Tiếng Hàn", "Tiếng Nhật", "Tiếng Trung" }));
		comboBox_NgonNgu_ThemSach_QLNL.setOpaque(true);
		comboBox_NgonNgu_ThemSach_QLNL.setBackground(Color.BLUE);
		contentPane.add(comboBox_NgonNgu_ThemSach_QLNL);

		comboBox_TheLoai_ThemSach_QLNL = new JComboBox();
		comboBox_TheLoai_ThemSach_QLNL.setBounds(471, 16, 137, 21);
		comboBox_TheLoai_ThemSach_QLNL.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		comboBox_TheLoai_ThemSach_QLNL.setModel(
				new DefaultComboBoxModel(new String[] { "Chính Trị- Khoa Học", "Tiểu Thuyết", "Truyện Ngôn Tình", "Thơ",
						"Kinh Doanh", "Tâm Lý Học", "Truyện Thiếu Nhi", "Trinh Thám", "Văn Học" }));
		comboBox_TheLoai_ThemSach_QLNL.setOpaque(true);
		comboBox_TheLoai_ThemSach_QLNL.setBackground(Color.BLUE);
		contentPane.add(comboBox_TheLoai_ThemSach_QLNL);

	}

	public void themSach() {
		_sach = new Sach();
		if (textField_TenSach_ThemSach_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_NamXB_ThemSach_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_NhaXB_ThemSach_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_TacGia_ThemSach_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_GiaSach_ThemSach_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_SoLuong_ThemSach_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
		}

		else {

			// Lấy dữ liệu nhập
			_sach.setTenSach(textField_TenSach_ThemSach_QLNL.getText());
			_sach.setNamXuatBan(Integer.valueOf(textField_NamXB_ThemSach_QLNL.getText()));
			_sach.setNXB(textField_NhaXB_ThemSach_QLNL.getText());
			_sach.setTacGia(textField_TacGia_ThemSach_QLNL.getText());
			_sach.setGiaSach(Integer.valueOf(textField_GiaSach_ThemSach_QLNL.getText()));
			_sach.setSoLuong(Integer.valueOf(textField_SoLuong_ThemSach_QLNL.getText()));

			_sach.setNgonNgu(comboBox_NgonNgu_ThemSach_QLNL.getSelectedItem().toString());
			_sach.setTheLoai(comboBox_TheLoai_ThemSach_QLNL.getSelectedItem().toString());
			
			// diaLogParent là dialog Thêm Lô, gọi hàm thêm 1 row sách vào
//			isThemSach = true;
			this.setVisible(false);

		}
	}
}
