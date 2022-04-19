package QuanlyNhanVien;



import QuanlyNhanVien.MAIN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SignUp extends JFrame implements ActionListener {
   
    JLabel tendangnhaplb = new JLabel("Tên đăng nhập");
    JTextField tendangnhap = new JTextField();

    JLabel matkhaulb = new JLabel("Mật khẩu");
    JPasswordField matkhau = new JPasswordField();

    JLabel chucvulb = new JLabel("Chức vụ");
    JComboBox chucvu = new JComboBox();

    JButton Dangnhap = new JButton("Đăng nhập");
    JButton Thoat = new JButton("Thoát");

    ArrayList accounts_lít = new ArrayList();
	private Connection con;

    public SignUp() {
        setTitle("Đăng Nhập");
        Container con = this.getContentPane();
        
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 5));
        p1.add(tendangnhaplb);
        p1.add(tendangnhap);
        p1.add(matkhaulb);
        p1.add(matkhau);
     

        chucvu.addItem("Nhân viên ");
        chucvu.addItem("Quản lý ");
        p1.add(chucvulb);
        p1.add(chucvu);

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(Dangnhap);
        p2.add(Thoat);
        Dangnhap.addActionListener(this);
        Thoat.addActionListener(this);
        con.add(p1);
        con.add(p2, "South");
        setSize(400, 200);
        this.setVisible(true);
        setLocationRelativeTo(null);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Đăng nhập")) {
            String uername = tendangnhap.getText();
            String pass = matkhau.getText();
            StringBuilder sb = new StringBuilder();
            if (uername.equals("")) {
                sb.append("vui lòng nhập tên tài khoản!\n");
            }
            if (pass.equals("")) {
                sb.append("vui lòng nhập mật khầu!");
            }
            if (sb.length() > 0) {
                JOptionPane.showMessageDialog(this, sb.toString());
            } else {
                try {
                	String URL = "jdbc:sqlserver://DESKTOP-UKQAS44\\SQLEXPRESS:1433;databaseName=TAIKHOAN\r\n"
            				+ ";user=sa;password=sa";
                	con = DriverManager.getConnection(URL);
                    String sql = "SELECT * FROM dbo.TAIKHOAN\n" +
                            "WHERE TenDangNhap = ? AND MatKhau = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, tendangnhap.getText());
                    ps.setString(2, matkhau.getText());
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        MAIN main = new MAIN();
                        main.setVisible(true);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "tài khoản hoặc mật khẩu không đúng");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (e.getActionCommand() == "Thoát") {
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        SignUp sig = new SignUp();
    }
}
