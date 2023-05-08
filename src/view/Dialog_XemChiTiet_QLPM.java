package view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.Service23;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class Dialog_XemChiTiet_QLPM extends JDialog {
	JFrame frame = new JFrame();
	
	private JPanel contentPane;
	private JTable table;
	private int MaPM;
	
	/**
	 * Launch the application.
	 */
	
	public Dialog_XemChiTiet_QLPM(JFrame parent, int MaPM){
		super(parent, "Dialog Default", true);
		this.MaPM = MaPM;
		this.init();        
		this.setLocationRelativeTo(null);
		this.setVisible(false);
	}

	/**
	 * Create the dialog.
	 */
	
	public void init() {
		setBounds(100, 100, 650, 400);
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 616, 343);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbl_ChiTietPhieuMuon = new JLabel("Chi tiết phiếu mượn");
		lbl_ChiTietPhieuMuon.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_ChiTietPhieuMuon.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ChiTietPhieuMuon.setBounds(10, 10, 596, 25);
		panel.add(lbl_ChiTietPhieuMuon);
		
		JLabel lbl0 = new JLabel("Mã phiếu mượn");
		lbl0.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl0.setBounds(10, 57, 106, 33);
		panel.add(lbl0);
		
		JLabel lbl_MaPhieuMuon = new JLabel(""+MaPM);
		lbl_MaPhieuMuon.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_MaPhieuMuon.setBounds(126, 57, 200, 33);
		panel.add(lbl_MaPhieuMuon);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 596, 2);
		panel.add(separator);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 100, 596, 233);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		table = new JTable(){
		  	   public boolean isCellEditable(int row, int column) {
		  	       return false;
		  	   }
		  	};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 s\u00E1ch", "T\u00EAn s\u00E1ch", "T\u00E1c gi\u1EA3"
			}
		));
		
		Service23.getInstance().SelectChiTietPhieuMuon(table, MaPM);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 596, 233);
		panel_1.add(scrollPane);
		
		ImageIcon iconCheck = new ImageIcon(MainView.class.getResource("/images/check.png"));
		Image imgCheck = iconCheck.getImage();
		Image newImgCheck = imgCheck.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIconCheck = new ImageIcon(newImgCheck);
		
		JButton btn_Check = new JButton("");
		btn_Check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	  			int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn không?", "Thông báo", JOptionPane.YES_NO_OPTION);
	  			if(dialogResult == JOptionPane.YES_OPTION){
	  				// Xử lý khi người dùng chọn Yes
	  				
	  				Service23.getInstance().UpdateTinhTrangPhieuMuonDaTra(MaPM);
	  				Service23.getInstance().UpdateTinhTrangSachCon(MaPM);
	  				
	  				// Cập nhật bảng Quản lý phiếu mượn
	  				((DefaultTableModel) MainView.table_QuanLyPhieuMuon.getModel()).setRowCount(0);
	  				Service23.getInstance().SelectAllPhieuMuon(MainView.table_QuanLyPhieuMuon);
	  				
	  				JOptionPane.showMessageDialog(null, "Đã cập nhật!");	
	
		  		} else {
		  			// Xử lý khi người dùng chọn No
		  		}
			}
		});
		btn_Check.setIcon(new ImageIcon(Dialog_XemChiTiet_QLPM.class.getResource("/images/check.png")));
		btn_Check.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_Check.setBounds(573, 57, 33, 33);
		panel.add(btn_Check);
		btn_Check.setIcon(newIconCheck);
		
		JLabel lbl1 = new JLabel("Đánh dấu đã trả tất cả sách");
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl1.setBounds(336, 56, 227, 33);
		panel.add(lbl1);
	}
}

































