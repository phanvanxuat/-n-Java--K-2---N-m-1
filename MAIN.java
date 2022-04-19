package QuanlyNhanVien;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet.ColorAttribute;

import org.w3c.dom.events.MouseEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.net.http.WebSocket.Listener;
import java.rmi.activation.ActivationGroupID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.KeyAdapter;

public class MAIN extends JFrame {
	private JTextField jtfName;
	private JTextField jtfAddress;
	private JTextField jtfMark;
	private JTextField jtfID;
	private JTextField jtftangCa;
	private JTextField jtftongTG;
	private JTextField jtfHo;
	private JTextField Medical;
	private JTextField jtftiLeMoiGio;
	private JTextField jtfTen;
	private JTextField jtfThuong;
	private JLabel total1;
	private JTextField jtfKhac;
	private JTextField jtfluongCoban;
	private JLabel Department;
	private DefaultTableModel list;
	private int Dem = 0;
	private String luong;
	Connection con;
	ResultSet rs;
	Statement stmt;
	private JButton jbtcapNhat;
	private JButton jbthienThi;
//	
	private JButton jbtchat;
//	
	private JTable table;
	private JButton jbtxoa1dong;
	private JTextField jtftimKiem; 

	public MAIN() {

		ketnoi();
		this.setTitle("Quản lý nhân sự");
		this.setSize(2200, 2000);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel jpall = new JPanel();
		jpall.setLayout(new BorderLayout());
		
	
		Border border1 = BorderFactory.createLineBorder(Color.cyan);
		TitledBorder tBorder1 = BorderFactory.createTitledBorder(border1, "");
		jpall.setBorder(tBorder1);
		jpall.setBackground(Color.green);

		JPanel center = new JPanel();
		center.setLayout(new GridLayout(6, 4));
		center.setBackground(new Color(175, 238, 238));
	

		JPanel jpanelhead = new JPanel();
		jpanelhead.setLayout(new GridLayout(1, 3));
	
		jpanelhead.setBackground(new Color(255, 192, 203));
		
		

		
		JLabel jlbtimKiemNhanVien = new JLabel("Tìm kiếm nhân viên", 0);
		
		jlbtimKiemNhanVien.setForeground(new Color(30, 144, 255));
		jtftimKiem = new JTextField(100);
		jtftimKiem.addKeyListener(new KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				timKiemTen();
			}
			//jlbtimKiemNhanVien.setFont(new Font("Serif", Font.PLAIN, 20));
		});
		JPanel jpntimKiemNhanVien = new JPanel();
		JButton jbttroVe = new JButton("Trở về");
		jbttroVe.setIcon(new ImageIcon(MAIN.class.getResource("/img/back.png")));

		// bam back tro ve
		jbttroVe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				list.setRowCount(Dem);
				Dem--;
			}

		});

		jbttroVe.setBackground(new Color(255, 99, 71));
		jbttroVe.setForeground(Color.WHITE);
//		ADD
		JButton jbtThem = new JButton("Thêm");
		jbtThem.setIcon(new ImageIcon(MAIN.class.getResource("/img/add.png")));
		jbtThem.setBackground(new Color(255, 99, 71));
		jbtThem.setForeground(Color.WHITE);
		
		
		JLabel emty10 = new JLabel();
		JLabel emty11 = new JLabel();
		jpanelhead.add(emty10);
		jpanelhead.add(emty11);
		
		jpanelhead.add(jbttroVe);
		jpanelhead.add(jbtThem);
		
		
		
		//search
	
		JPanel p_timKiem =new JPanel();
		p_timKiem.setLayout(new GridLayout(1, 3));
		JLabel emty21 = new JLabel("                           ");
		JLabel emty22 = new JLabel("                           ");
		//màu phần đầu
		p_timKiem.setBackground(new Color(255, 192, 203));
		
		p_timKiem.add(emty21); p_timKiem.add(emty22);
		JPanel jpnCenter = new JPanel();
		jpnCenter.setBackground(Color.RED);
		jpnCenter.setLayout(new GridLayout(3, 0));

		JPanel jpnsearch1 = new JPanel();
		jpnsearch1.setLayout(new BorderLayout());
		
		JLabel emty12 = new JLabel("                        ");
		JLabel emty13 = new JLabel();
		jpnsearch1.add(emty12, BorderLayout.WEST);
		jpnsearch1.add(jtftimKiem, BorderLayout.CENTER);
		JLabel emty20 = new JLabel("                        ");
		p_timKiem.add(jlbtimKiemNhanVien);
		jpntimKiemNhanVien.setLayout(new BorderLayout());
		jpnsearch1.add(p_timKiem, BorderLayout.WEST);
		jpntimKiemNhanVien.add(emty20, BorderLayout.WEST);
		jpntimKiemNhanVien.add(jpnsearch1, BorderLayout.CENTER);
		jpntimKiemNhanVien.add(jpanelhead, BorderLayout.EAST);
		
		JLabel jlbID = new JLabel("ID nhân viên", 0);
		jtfID = new JTextField(50);


		JLabel jlbHo = new JLabel("Họ và tên", 0);
		jtfHo = new JTextField(50);

		JLabel jlbTen = new JLabel( );
		jtfTen = new JTextField(50);
		
		JLabel jlbEmty = new JLabel();

		JLabel jlbluongCoban = new JLabel("Lương cơ bản", 0);
		jtfluongCoban = new JTextField(50);

		JLabel medical = new JLabel("Giới tính", 0);
		Medical = new JTextField(50);

		JLabel jlbThuong = new JLabel("Thưởng", 0);
		jtfThuong = new JTextField(50);

		JLabel jlbKhac = new JLabel("Khác", 0);
		jtfKhac = new JTextField(50);

		JLabel jlbtangCa = new JLabel("Tăng ca", 0);
		jtftangCa = new JTextField(50);

		JLabel jlbtiLeMoiGio = new JLabel("Tỉ lệ mỗi giờ", 0);
		jtftiLeMoiGio = new JTextField(50);

		JLabel jlbtongTG = new JLabel("Tổng thời gian tăng ca", 0);
		jtftongTG = new JTextField(50);

		JLabel jlbTongcong = new JLabel("Tổng cộng", 0);
		total1 = new JLabel("");
		jlbTongcong.setText("Tính lương");

		JLabel department = new JLabel("");
		Department = new JLabel();

//		Table
		JPanel tablenode = new JPanel();
		tablenode.setLayout(new GridLayout(1, 2));

		JPanel tablejpn = new JPanel();
		tablejpn.setLayout(new BorderLayout());
		list = new DefaultTableModel();
	
		Border border = BorderFactory.createLineBorder(Color.blue);
		TitledBorder tBorder = BorderFactory.createTitledBorder(border, "Giao diện bảng");
		tablejpn.setBorder(tBorder);
		tablejpn.setBackground(Color.cyan);

		JPanel content = new JPanel();
		content.setLayout(new GridLayout(2, 1));
		



		JPanel button = new JPanel();
		button.setLayout(new GridLayout(6, 1));

		JLabel emty14 = new JLabel();
		JLabel emty15 = new JLabel();
		JLabel emty16 = new JLabel();
		jbtcapNhat = new JButton("Cập nhật");
		jbtcapNhat.setIcon(new ImageIcon(MAIN.class.getResource("/img/update.png")));
		jbtcapNhat.setBackground(Color.CYAN);
		jbtcapNhat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CapNhatDL();
			}
			
		});
		jbthienThi = new JButton("Hiển thị");
		jbthienThi.setIcon(new ImageIcon(MAIN.class.getResource("/img/show.png")));

		jbthienThi.setBackground(Color.CYAN);
		jbthienThi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HienThiDL();
			}
			
		});
//		jbt chat
		jbtchat = new JButton("Chat...");
//		jbtchat.setIcon(new ImageIcon(MAIN.class.getResource("/img/show.png")));

		jbtchat.setBackground(Color.CYAN);
		jbtchat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ManagerChatter mChatter = new ManagerChatter();
				mChatter.setVisible(true);
				ClientChatter cChatter = new ClientChatter();
				cChatter.setVisible(true);
				HienThiDL();
			}
		});
//		
		
		JButton jbttinhToan = new JButton("Tính toán");
		jbttinhToan.setIcon(new ImageIcon(MAIN.class.getResource("/img/calculator.png")));

		jbttinhToan.setBackground(Color.CYAN);
		

//		
		jbttinhToan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String tinhLuong = String.valueOf(tinhluong());
				total1.setText(tinhLuong);
			}

		});
		jbtxoa1dong = new JButton("Xóa 1 dòng");
		jbtxoa1dong.setIcon(new ImageIcon(MAIN.class.getResource("/img/delete1.png")));

		jbtxoa1dong.setBackground(Color.CYAN);
		jbtxoa1dong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				list.removeRow(row);
				xoa1dong();
			}
			
		});
		JButton jbtxoaHet = new JButton("Xóa hết");
		jbtxoaHet.setIcon(new ImageIcon(MAIN.class.getResource("/img/delete.png")));
		jbtxoaHet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				list.setRowCount(0);
				xoahet();
				
			}

		});
		jbtxoaHet.setBackground(Color.CYAN);
		button.add(jbtcapNhat);
		button.add(jbttinhToan);
		button.add(jbtxoaHet);
		button.add(jbthienThi);
		button.add(jbtchat);
		button.add(jbtxoa1dong);
	

		list.addColumn("Nhân viên");
		list.addColumn("Họ và tên");
		list.addColumn("Lương");
		list.addColumn("Giới tính");
		list.addColumn("Thưởng");
		list.addColumn("Khác");
		list.addColumn("Tăng ca");
		list.addColumn("Tỷ lệ mỗi giờ");
		list.addColumn("Tổng thời gian tăng ca");

		table = new JTable(list);
		JScrollPane sc = new JScrollPane(table);
		tablejpn.add(sc, BorderLayout.CENTER);
		tablejpn.add(button, BorderLayout.EAST);
		tablenode.add(tablejpn);
		table.setBackground(new Color(224, 255, 255));

		// ADD OR BACK
		jbtThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				ThemDL();
				list.addRow(new String[] { jtfID.getText(), jtfHo.getText(), jtfluongCoban.getText(),
						Medical.getText(), jtfThuong.getText(), jtfKhac.getText(), jtftangCa.getText(),
						jtftiLeMoiGio.getText(), jtftongTG.getText() });
				luong = String.valueOf(tinhluong());
				reset();
				Dem++;
			}

		});
		table.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {

			}

			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				jtfID.setText((String) table.getValueAt(row, 0));
				jtfHo.setText((String) table.getValueAt(row, 1));
				jtfluongCoban.setText((String) table.getValueAt(row, 2));
				Medical.setText((String) table.getValueAt(row, 3));
				jtfThuong.setText((String) table.getValueAt(row, 4));
				jtfKhac.setText((String) table.getValueAt(row, 5));
				jtftiLeMoiGio.setText((String) table.getValueAt(row, 7));
				jtftangCa.setText((String) table.getValueAt(row, 6));
				jtftongTG.setText((String) table.getValueAt(row, 8));
			}

			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JLabel img = new JLabel();
		img.setIcon(new ImageIcon(MAIN.class.getResource("/img/java1.png")));

		center.add(jlbID);
		center.add(jtfID);
		center.add(jlbtangCa);
		center.add(jtftangCa);

		center.add(jlbHo);
		center.add(jtfHo);

		center.add(medical);
		center.add(Medical);
		center.add(jlbtiLeMoiGio);
		center.add(jtftiLeMoiGio);
		
		center.add(jlbThuong);
		center.add(jtfThuong);

		center.add(jlbKhac);
		center.add(jtfKhac);

		center.add(jlbluongCoban);
		center.add(jtfluongCoban);
		center.add(jlbtongTG);
		center.add(jtftongTG);

		center.add(jlbTongcong);
		center.add(total1);
		center.add(department);
		center.add(Department);
		center.add(jlbTen);
		center.add(jlbEmty);

		JPanel pcenterJPanel = new JPanel();
		pcenterJPanel.setLayout(new BorderLayout());
		pcenterJPanel.add(center, BorderLayout.CENTER);
		pcenterJPanel.add(img, BorderLayout.EAST);

		JLabel emty18 = new JLabel();
		JLabel emty17 = new JLabel();


		
		jpall.add(jpntimKiemNhanVien, BorderLayout.NORTH);
		jpall.add(pcenterJPanel, BorderLayout.CENTER);
		jpntimKiemNhanVien.setBackground(new Color(255, 192, 203));

		content.add(jpall);
		content.add(tablenode);
		this.add(content);
		
		this.setVisible(true);
		//tablejpn
	}

	public void reset() {
		jtfID.setText("");
		jtfHo.setText("");
		jtfTen.setText("");
		jtfluongCoban.setText("");
		Medical.setText("");
		jtfThuong.setText("");
		jtfKhac.setText("");
		jtftiLeMoiGio.setText("");
		jtftangCa.setText("");
		jtftongTG.setText("");
		total1.setText("");
	}

	public double tinhluong() {
		return ((Double.parseDouble(jtfluongCoban.getText()) + Double.parseDouble(jtfThuong.getText())+Double.parseDouble(jtftongTG.getText())) 
				* Double.parseDouble(jtftiLeMoiGio.getText()) ) + Double.parseDouble(jtfKhac.getText())   ;
	}

	public void ketnoi() {
		String URL = "jdbc:sqlserver://DESKTOP-UKQAS44\\SQLEXPRESS:1433;databaseName=QUANLYNHANSU1\r\n"
				+ ";user=sa;password=sa";

		try {
			con = DriverManager.getConnection(URL);
			stmt = con.createStatement();
			JOptionPane.showMessageDialog(this, "Kết nối thành công");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Kết nối thất bại");
			e.printStackTrace();
		}
	}

	public void ThemDL() {
		String sql = "Exec ThemDL '" + jtfID.getText() + "', N'" + jtfHo.getText() + "', "
				+ Double.parseDouble(jtfluongCoban.getText()) + ",N'" + Medical.getText() + "', '" + jtfThuong.getText()
				+ "', N'" + jtfKhac.getText() + "', " + Double.parseDouble(jtftangCa.getText()) + ", "
				+ Double.parseDouble(jtftiLeMoiGio.getText()) + ", " + Double.parseDouble(jtftongTG.getText());
		try {
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Thêm dữ liệu thất bại");
			e.printStackTrace();
		}
	}
	public void xoahet() {
		String sql = "Truncate table NHANSU";
		try {
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(this, "Xóa dữ liệu thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Xóa dữ liệu thất bại");
			e.printStackTrace();		}
		
	}
	public void xoa1dong() {
		String maNV = jtfID.getText(); 
		String sql = "Delete NHANSU Where MaNhanVien = '" + maNV +"'";
		try {
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(this, "Xóa dữ liệu thành công");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Xóa dữ liệu thất bại");
			e.printStackTrace();		}
		
	}
	public void CapNhatDL() {
		String sql = "Exec UpdateDL '" + jtfID.getText() + "', N'" + jtfHo.getText() + "', "
				+ Double.parseDouble(jtfluongCoban.getText()) + ",N'" + Medical.getText() + "', '" + jtfThuong.getText()
				+ "', N'" + jtfKhac.getText() + "', " + Double.parseDouble(jtftangCa.getText()) + ", "
				+ Double.parseDouble(jtftiLeMoiGio.getText()) + ", " + Double.parseDouble(jtftongTG.getText());
		try {
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(this, "Cập nhật dữ liệu thành công");
			list.setRowCount(0);
			HienThiDL();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Cập nhật dữ liệu thất bại");
			e.printStackTrace();
		}
	}
	public void HienThiDL() {
		String sql = "SELECT * FROM NHANSU";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			list.setRowCount(0);
			while(rs.next()) {
				list.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9) });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void timKiemTen() {
	    String option = jtftimKiem.getText();
	    if (option.equals("")) {
	    	HienThiDL();
	    }
	    else {
	    	try {
		    	String sql = "EXEC Timkiem N'" + option + "'";
	            list.setRowCount(0);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					String MaNV = rs.getString(1);
                    String Hovaten = rs.getString(2);
                    String Luongcoban = rs.getString(3);
                    String GioiTinh = rs.getString(4);
                    String Thuong = rs.getString(5);
                    String khac = rs.getString(6);
                    String tangca = rs.getString(7);
                    String tilemoigio = rs.getString(8);
                    String tongthoigiantangca = rs.getString(9);
                    list.addRow(new String[] {MaNV, Hovaten, Luongcoban,Thuong,khac,tangca,tilemoigio,tongthoigiantangca });
				}
	    	} catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
	    }
	    }
	public static void main(String args[]) {
		new MAIN();

	}
}
