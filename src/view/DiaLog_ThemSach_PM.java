package view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import database.Service23;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

public class DiaLog_ThemSach_PM extends JDialog {
	JFrame frame = new JFrame();

	private JPanel contentPane;
	public static JTable table;
	private JTextField textField_TimKiem;
	private JLabel lbl_ChonSach;

	/**
	 * Launch the application.
	 */

	public DiaLog_ThemSach_PM(JFrame parent) {
		super(parent, "Dialog Default", true);
		this.init();
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}

	/**
	 * Create the dialog.
	 */

	public void init() {
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 120, 966, 250);
		contentPane.add(panel);
		panel.setLayout(null);

		textField_TimKiem = new JTextField();
		textField_TimKiem.setForeground(new Color(0, 0, 0));
		textField_TimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_TimKiem.setColumns(10);
		textField_TimKiem.setBackground(new Color(226, 255, 153));
		textField_TimKiem.setBounds(676, 57, 300, 45);
		contentPane.add(textField_TimKiem);

		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 s\u00E1ch", "T\u00EAn s\u00E1ch", " T\u00E1c gi\u1EA3", "Nh\u00E0 XB",
						"N\u0103m XB", "Th\u1EC3 lo\u1EA1i", "Ng\u00F4n ng\u1EEF" }));

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
			}
		});

		Service23.getInstance().SelectAllSachCon(table);

		// ----------->Xử lý tìm kiếm cho table<-----------

		// Tạo đối tượng TableRowSorter để lọc dữ liệu trong bảng
		TableRowSorter<TableModel> sorter2 = new TableRowSorter<>(table.getModel());

		// Đặt TableRowSorter cho bảng
		table.setRowSorter(sorter2);

		// Tạo sự kiện KeyReleased cho JTextField
		textField_TimKiem.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String input = textField_TimKiem.getText().trim(); // Lấy dữ liệu từ JTextField
				if (input.length() == 0) {
					// Nếu JTextField rỗng, hiển thị tất cả dữ liệu
					sorter2.setRowFilter(null);
				} else {
					// Lọc dữ liệu theo nội dung JTextField
					sorter2.setRowFilter(RowFilter.regexFilter("(?i)" + input));
				}
			}
		});
		// ----------------------------------------------------------

		ImageIcon iconTimKiem = new ImageIcon(MainView.class.getResource("/images/search.png"));
		Image imgTimKiem = iconTimKiem.getImage();
		Image newImgTimKiem = imgTimKiem.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconTimKiem = new ImageIcon(newImgTimKiem);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 966, 250);
		panel.add(scrollPane);

		JButton btn_TimKiem = new JButton("");
		btn_TimKiem.setOpaque(true);
		btn_TimKiem.setBorderPainted(false);
		btn_TimKiem.setBackground(new Color(226, 255, 153));
		btn_TimKiem.setBounds(633, 57, 45, 45);
		btn_TimKiem.setIcon(newIconTimKiem);
		contentPane.add(btn_TimKiem);

		lbl_ChonSach = new JLabel("Chọn sách");
		lbl_ChonSach.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ChonSach.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_ChonSach.setBounds(10, 10, 966, 25);
		contentPane.add(lbl_ChonSach);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 966, 2);
		contentPane.add(separator);

		JButton btn_Ok = new JButton("Ok");
		btn_Ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();// lấy chỉ số của hàng được chọn trong table.
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Hãy chọn sách muốn thêm!");
				} else {
					int maSach = (int) table.getValueAt(row, 0);
					boolean check = false;

					// Kiểm tra xem mã sách đã được thêm vào bảng hay chưa.
					for (int element : MainView.arrMaSach) {
						if (element == maSach) {
							check = true;
						}
					}

					if (check) {
						JOptionPane.showMessageDialog(null, "Sách này đã được thêm rồi, hãy chọn sách khác!");
					} else {
						String tenSach = (String) table.getValueAt(row, 1);
						String tacGia = (String) table.getValueAt(row, 2);

						DefaultTableModel model = (DefaultTableModel) MainView.table_pm.getModel();
						model.addRow(new Object[] { maSach, tenSach, tacGia });

						MainView.arrMaSach.add(maSach);
						dispose();
					}
				}
			}
		});
		btn_Ok.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_Ok.setBounds(415, 408, 150, 45);
		contentPane.add(btn_Ok);
	}
}
