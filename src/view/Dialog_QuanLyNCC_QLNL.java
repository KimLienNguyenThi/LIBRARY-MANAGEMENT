package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
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
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import database.QuanLyNhapLo;
import model.NhaCungCap;
import model.PhieuNhapLo;
import model.Sach;
import view.MainView.RowPopup;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Dialog_QuanLyNCC_QLNL extends JDialog {
	JFrame frame = new JFrame();
	public MainView frameParent;
	private JPanel contentPane;
	public JTable table;
	public DefaultTableModel model;
	private JTextField textField_TimKiem_qlnl;
	private Icon newIconTimKiem;
	private Container panelQuanLyNhapLo;
	private int idSelectedNCC = 0;
	private JTextField textField_DiaChiNCC_QLNL;
	private JTextField textField_TenNCC_QLNL;
	private JTextField textField_SDTNCC_QLNL;
	private JTable table_ncc;
	private JButton btn_suaNCC_QLNL;
	private JButton btn_ThemNCC_QLNL;
	public Dialog_ThemLo_QLNL themLo_parent;
	public int imainview = 0; // từ mainview gọi qua
							// 1 là từ màn hình thêm lô gọi qua để thêm ncc

	public Dialog_QuanLyNCC_QLNL(MainView parent,Dialog_ThemLo_QLNL chanuoi, int showview) {
		super(parent, "Thêm Nhà cung cấp", true);
		this.setLocationRelativeTo(null);
		themLo_parent = chanuoi;
		imainview = showview;
		this.init();
		this.setVisible(false);
		// hiện thị trung tâm màn hình
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		frameParent = parent;
	}

	public void init() {
		setBounds(100, 100, 673, 380);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		btn_ThemNCC_QLNL = new JButton("Thêm");
		btn_ThemNCC_QLNL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Insert_NCC();
				LoadDataNCC();
				if(imainview == 1) {
			    themLo_parent.LoadNCC();
			    
			}
				JOptionPane.showMessageDialog(frame, "Bạn đã Thêm Thông tin NCC thành công.");
			}
		});

		btn_ThemNCC_QLNL.setBounds(250, 83, 111, 33);
		btn_ThemNCC_QLNL.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(btn_ThemNCC_QLNL);

		JLabel lbl_TenNCC_ThemLo = new JLabel("Tên NCC: ");
		lbl_TenNCC_ThemLo.setBounds(10, 10, 143, 20);
		lbl_TenNCC_ThemLo.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_TenNCC_ThemLo);

		JLabel lbl_SDT_ThemLo = new JLabel("Số điện thoại:");
		lbl_SDT_ThemLo.setBounds(392, 10, 102, 20);
		lbl_SDT_ThemLo.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_SDT_ThemLo);

		JLabel lbl_Diachi_ThemLo = new JLabel("Địa chỉ:");
		lbl_Diachi_ThemLo.setBounds(10, 51, 56, 20);
		lbl_Diachi_ThemLo.setFont(new Font("Time New Roman", Font.BOLD, 15));
		contentPane.add(lbl_Diachi_ThemLo);

		textField_TenNCC_QLNL = new JTextField();
		textField_TenNCC_QLNL.setBounds(85, 13, 276, 19);
		// textField_TenNCC_ThemLo.setColumns(10);
		contentPane.add(textField_TenNCC_QLNL);

		textField_DiaChiNCC_QLNL = new JTextField();
		textField_DiaChiNCC_QLNL.setBounds(85, 54, 543, 19);
		textField_DiaChiNCC_QLNL.setColumns(10);
		contentPane.add(textField_DiaChiNCC_QLNL);

		textField_SDTNCC_QLNL = new JTextField();
		textField_SDTNCC_QLNL.setBounds(504, 13, 124, 19);
		// textField_SDT_ThemLo.setColumns(10);
		contentPane.add(textField_SDTNCC_QLNL);

		textField_SDTNCC_QLNL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}

			}
		});

		JPanel panel_ThemNCC_QLNL = new JPanel();
		panel_ThemNCC_QLNL.setBounds(10, 122, 639, 200);
		contentPane.add(panel_ThemNCC_QLNL);
		panel_ThemNCC_QLNL.setLayout(null);

		table_ncc = new JTable();
		model = (DefaultTableModel) table_ncc.getModel();

		JScrollPane scrollPane = new JScrollPane(table_ncc);
		scrollPane.setBounds(0, 0, 640, 217);
		panel_ThemNCC_QLNL.add(scrollPane);
		// DefaultTableModel model = (DefaultTableModel) table.getModel();

		String MaNCC = null;
		String TenNCC = null;
		String SDTNCC = null;
		String DiaChiNCC = null;
		table_ncc.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 NCC", "T\u00EAn NCC", "S\u0110T", "\u0110\u1ECBa ch\u1EC9" }) {

			// ngăn chặn chỉnh sửa giá trị
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1 || column == 2 || column == 3)
					return false;
				return super.isCellEditable(row, column);
			}
		});

		btn_suaNCC_QLNL = new JButton("Update");
		btn_suaNCC_QLNL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update_ncc();
				LoadDataNCC();
				set_Them();
				if(imainview == 1) {
				    themLo_parent.LoadNCC();
				}
				JOptionPane.showMessageDialog(frame, "Bạn đã sửa thành công.");
			}
		});
		btn_suaNCC_QLNL.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btn_suaNCC_QLNL.setBounds(494, 83, 111, 33);
		contentPane.add(btn_suaNCC_QLNL);
		table_ncc.getColumnModel().getColumn(0).setPreferredWidth(37);
		table_ncc.getColumnModel().getColumn(1).setPreferredWidth(63);
		table_ncc.getColumnModel().getColumn(2).setPreferredWidth(74);
		table_ncc.getColumnModel().getColumn(3).setPreferredWidth(89);

		table_ncc.getSelectionModel().addListSelectionListener(new ListSelectionListener() { // su kien chon 1

			// o tren table
			public void valueChanged(ListSelectionEvent event) {
				int row = table_ncc.getSelectedRow(); // lấy chỉ số của hàng được chọn trong table.
				if (row >= 0) { // Đảm bảo là có hàng được chọn
					idSelectedNCC = Integer.valueOf(table_ncc.getValueAt(table_ncc.getSelectedRow(), 0).toString());
					System.out.println(idSelectedNCC);
				}
			}
		});
		LoadDataNCC();
		AddPopUp();
		set_Them();
	}

	public void LoadDataNCC() {
		((DefaultTableModel) table_ncc.getModel()).setRowCount(0);
		// Gọi sang hàm lấy dữ liệu để đổ vào dữ liệu lên table vừa khai báo
		table_ncc = QuanLyNhapLo.getInstance().select_NCC(table_ncc);
	}

	public void Insert_NCC() {
		int idNCCNew = them_NCC();
		if (idNCCNew == -6) // nếu <= 0 => thêm ko thành công
		{
			return;
		}
		if (idNCCNew <= 0) // nếu <= 0 => thêm ko thành công
		{
			JOptionPane.showMessageDialog(frame, "Thêm NCC không thành công!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	public int them_NCC() {
		// This method must return a result of type int/ bat buoc return
		NhaCungCap NhaCungCap = new NhaCungCap();

		Pattern patternSDT = Pattern.compile("^0[3798]{1}\\d{8}$");

		if (textField_TenNCC_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
			return -6;

		} else if (textField_SDTNCC_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
			return -6;

		} else if (patternSDT.matcher(textField_SDTNCC_QLNL.getText()).matches() == false) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đúng định dạng số điện thoại!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
			return -6;

		} else if (textField_DiaChiNCC_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
			return -6;

		} else {

			// Lấy dữ liệu nhập

			NhaCungCap.setTenNhaCungCap(textField_TenNCC_QLNL.getText());
			NhaCungCap.setSdtNhaCungCap(textField_SDTNCC_QLNL.getText());
			NhaCungCap.setDiaChiNhaCungCap(textField_DiaChiNCC_QLNL.getText());

			int idNCCNew = QuanLyNhapLo.getInstance().Insert_NCC(NhaCungCap);
			System.out.println(idNCCNew);
			// new Dialog_ThemLo_QLNL(frame); // hien thi dialog

			// Xoá dữ liệu nhập trên màn hình
			textField_TenNCC_QLNL.setText("");
			textField_SDTNCC_QLNL.setText("");
			textField_DiaChiNCC_QLNL.setText("");

			// trả về id Lô sách vừa mới thêm, để thực thi insert data chitietlo
			return idNCCNew;
		}
	}

	public class RowPopup extends JPopupMenu {
		public RowPopup(JTable table) {

			// dùng để show popupSỬA
			JMenuItem detail = new JMenuItem("Sửa TT NCC");
			detail.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					// JOptionPane.showMessageDialog(detail, "Xem chi tiết");
					// QuanLyNhapLo.getInstance().select_ThongTinNCC(idSelectedNCC);
					tt_NCC();
					set_Sua();
				}
			});
			add(detail);
		}
	}

	public void AddPopUp() {
		final RowPopup pop = new RowPopup(table_ncc);

		table_ncc.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println("so row" +table_QuanLyNhapLo.getRowCount());
				// TODO Auto-generated method stub
				if (SwingUtilities.isRightMouseButton(e)) {
					pop.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

	}

	public void tt_NCC() {
		NhaCungCap NhaCungCap = QuanLyNhapLo.getInstance().select_ThongTinNCC(idSelectedNCC);
		textField_TenNCC_QLNL.setText(NhaCungCap.getTenNhaCungCap());
		textField_SDTNCC_QLNL.setText(NhaCungCap.getSdtNhaCungCap());
		textField_DiaChiNCC_QLNL.setText(NhaCungCap.getDiaChiNhaCungCap());
	}

	public void update_ncc() {
		NhaCungCap NhaCungCap = new NhaCungCap();

		Pattern patternSDT = Pattern.compile("^0[3798]{1}\\d{8}$");

		if (textField_TenNCC_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_SDTNCC_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);
		} else if (patternSDT.matcher(textField_SDTNCC_QLNL.getText()).matches() == false) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đúng định dạng số điện thoại!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else if (textField_DiaChiNCC_QLNL.getText().equals("")) {
			JFrame frame = new JFrame("JOptionPane showMessageDialog example");
			JOptionPane.showMessageDialog(frame, "Vui lòng điền đủ thông tin!!!", "THÔNG BÁO",
					JOptionPane.ERROR_MESSAGE);

		} else {

			// Lấy dữ liệu nhập

			NhaCungCap.setTenNhaCungCap(textField_TenNCC_QLNL.getText());
			NhaCungCap.setSdtNhaCungCap(textField_SDTNCC_QLNL.getText());
			NhaCungCap.setDiaChiNhaCungCap(textField_DiaChiNCC_QLNL.getText());

			int idNCCNew = QuanLyNhapLo.getInstance().UpdateNCC(NhaCungCap, idSelectedNCC);
			System.out.println(idSelectedNCC);

			// Xoá dữ liệu nhập trên màn hình
			textField_TenNCC_QLNL.setText("");
			textField_SDTNCC_QLNL.setText("");
			textField_DiaChiNCC_QLNL.setText("");

		}
	}

	public void set_Them() {
		btn_ThemNCC_QLNL.setEnabled(true);
		btn_suaNCC_QLNL.setEnabled(false);
	}

	public void set_Sua() {
		btn_ThemNCC_QLNL.setEnabled(false);
		btn_suaNCC_QLNL.setEnabled(true);
	}
	
}
