package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import database.QuanLyNhapLo;
import database.ThanhVien;
import model.LoSach;
import model.TheThanhVien;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Quan_Ly_Nhap_Lo_View extends JFrame {
	private JScrollPane _jscrollPane;
	private JPanel contentPane;
	public JTable table_QuanLyNhapLo;
	private LoSach _losach;

	public int idSelectedLoSach = 1;

	// private String ;
	private Icon newIconTimKiem;
	private JTextField txtSdtOrTn;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quan_Ly_Nhap_Lo_View frame = new Quan_Ly_Nhap_Lo_View();
					Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation(dim.width / 3 - frame.getSize().width / 3,
							dim.height / 3 - frame.getSize().height / 3);
					frame.setVisible(true);
					frame.initTableLo();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void initTableLo() {
		// khoi tao lần đầu
		table_QuanLyNhapLo = new JTable();
		
		table_QuanLyNhapLo.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null } },
				new String[] { "Mã lô", "Tên NCC", "SĐT NCC", "Địa chỉ", "Ngày nhập", "Tổng tiền" }) {
			
			// ngăn chặn chỉnh sửa giá trị
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 1 || column == 2|| column == 3 || column == 4 || column ==5 )
					return false;
				return super.isCellEditable(row, column);
			}
		});
		table_QuanLyNhapLo.getSelectionModel().addListSelectionListener(new ListSelectionListener() { // su kien chon 1
			// o tren table
			public void valueChanged(ListSelectionEvent event) {
				int row = table_QuanLyNhapLo.getSelectedRow(); // lấy chỉ số của hàng được chọn trong table.
				if (row >= 0) { // Đảm bảo là có hàng được chọn
					idSelectedLoSach = Integer
							.valueOf(table_QuanLyNhapLo.getValueAt(table_QuanLyNhapLo.getSelectedRow(), 0).toString());
					System.out.println(idSelectedLoSach);
				}
			}
		});

		table_QuanLyNhapLo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table_QuanLyNhapLo.setFillsViewportHeight(true);

		table_QuanLyNhapLo.getColumnModel().getColumn(0).setCellEditor(null);
		_jscrollPane = new JScrollPane(table_QuanLyNhapLo);
		_jscrollPane.setBounds(0, 88, 540, 180);
		contentPane.add(_jscrollPane);
		LoadDataList();
		AddPopUp();
	}

	/**
	 * Create the frame.
	 */
	public Quan_Ly_Nhap_Lo_View() {

		ImageIcon iconlibrary = new ImageIcon(MainView.class.getResource("/images/books.png"));
		Image imglibrary = iconlibrary.getImage();
		Image newImglibrary = imglibrary.getScaledInstance(58, 58, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconlibrary = new ImageIcon(newImglibrary);

		ImageIcon iconTimKiem = new ImageIcon(MainView.class.getResource("/images/search.png"));
		Image imgTimKiem = iconTimKiem.getImage();
		Image newImgTimKiem = imgTimKiem.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconTimKiem = new ImageIcon(newImgTimKiem);

//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btn_TimKiem_pm = new JButton("");
		btn_TimKiem_pm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				table_QuanLyNhapLo = QuanLyNhapLo.getInstance().TimKiemLo(table_QuanLyNhapLo, textField_TimKiem_QLNL.getText() );
				Timkiemlosach();
			}
		});
		btn_TimKiem_pm.setOpaque(true);
		btn_TimKiem_pm.setBorderPainted(false);
		btn_TimKiem_pm.setBackground(new Color(226, 255, 153));
		btn_TimKiem_pm.setBounds(253, 10, 33, 25);
		contentPane.add(btn_TimKiem_pm);
		btn_TimKiem_pm.setIcon(newIconTimKiem);

		txtSdtOrTn = new JTextField();
		txtSdtOrTn.setForeground(new Color(0, 0, 0));
		txtSdtOrTn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtSdtOrTn.setColumns(10);
		txtSdtOrTn.setBackground(new Color(226, 255, 153));
		txtSdtOrTn.setBounds(288, 10, 228, 25);
		contentPane.add(txtSdtOrTn);

		JButton btn_Them_QlNhapLo = new JButton("Thêm");
		btn_Them_QlNhapLo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btn_Them_QlNhapLo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				them_lo();
				// dispose();
			}
		});
		btn_Them_QlNhapLo.setBounds(38, 45, 100, 29);
		contentPane.add(btn_Them_QlNhapLo);

		JButton btn_XemChiTiet_QLNhapLo = new JButton("Xem Chi Tiết");
		btn_XemChiTiet_QLNhapLo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xemchitiet();
				// dispose();
			}
		});
		btn_XemChiTiet_QLNhapLo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btn_XemChiTiet_QLNhapLo.setBounds(215, 45, 100, 29);
		contentPane.add(btn_XemChiTiet_QLNhapLo);

	}

	public void LoadDataList() {
		((DefaultTableModel) table_QuanLyNhapLo.getModel()).setRowCount(0);
		// Gọi sang hàm lấy dữ liệu để đổ vào dữ liệu lên table vừa khai báo
		table_QuanLyNhapLo = QuanLyNhapLo.getInstance().selectAll(table_QuanLyNhapLo);
	}

	public void them_lo() {
		new Dialog_ThemLo_QLNL(this).setVisible(true);
	}

	public void xemchitiet() {
		new Dialog_XemChiTiet_QLNL(this, idSelectedLoSach).setVisible(true);
	}

	public void updateLo() {
		new Dialog_SuaLo_QLNL(this, idSelectedLoSach).setVisible(true);
	}

	public class RowPopup extends JPopupMenu {
		public RowPopup(JTable table) {
			
			// dùng để show popup Thêm
						JMenuItem detail_Update = new JMenuItem("Thêm");
						detail_Update.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								// JOptionPane.showMessageDialog(detail, "Xem chi tiết");
								them_lo();
							}
						});

						add(detail_Update);
			// dùng để show popup xem chi tiết
			JMenuItem detail = new JMenuItem("Xem chi tiết");
			detail.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					// JOptionPane.showMessageDialog(detail, "Xem chi tiết");
					xemchitiet();
				}
			});

			add(detail);

			
		}
	}

	public void LoadDataUpdate() {
		((DefaultTableModel) table_QuanLyNhapLo.getModel()).setRowCount(0);
		// Gọi sang hàm lấy dữ liệu để đổ vào dữ liệu lên table vừa khai báo
		table_QuanLyNhapLo = QuanLyNhapLo.getInstance().selectAll(table_QuanLyNhapLo);
	}

	public void Timkiemlosach() {
		((DefaultTableModel) table_QuanLyNhapLo.getModel()).setRowCount(0);
		// Gọi sang hàm lấy dữ liệu để đổ vào dữ liệu lên table vừa khai báo
		table_QuanLyNhapLo = QuanLyNhapLo.getInstance().TimKiemLo(table_QuanLyNhapLo, txtSdtOrTn.getText());
	}

	public void AddPopUp() {
		final RowPopup pop = new RowPopup(table_QuanLyNhapLo);
		
		table_QuanLyNhapLo.addMouseListener(new MouseListener() {

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
		final RowPopup pop1 = new RowPopup(table_QuanLyNhapLo);
		table_QuanLyNhapLo.addMouseListener(new MouseListener() {

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

				if (SwingUtilities.isRightMouseButton(e)) {
					pop.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

}