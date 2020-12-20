/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DTO.KhachHangDTO;
import MyConnection.MySQLConnect;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class KhachHangDAO {

    public ArrayList readDSKH() {
        MySQLConnect connect = new MySQLConnect();
        ArrayList dskh = new ArrayList<KhachHangDTO>();
        try {
            String qry = "SELECT * FROM khachhang";
            connect.st = (Statement) connect.conn.createStatement();
            connect.rs = connect.st.executeQuery(qry);
            while (connect.rs.next()) {
                KhachHangDTO kh = new KhachHangDTO();
                kh.setMakh(connect.rs.getString(1));
                kh.setHo(connect.rs.getString(2));
                kh.setTen(connect.rs.getString(3));
                kh.setSdt(connect.rs.getString(4));
                kh.setDiachi(connect.rs.getString(5));
                kh.setNgaysinh(connect.rs.getString(6));
                kh.setGioitinh(connect.rs.getString(7));
                dskh.add(kh);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        connect.MySQLDisconnect();
        return dskh;
    }

    public void add(KhachHangDTO kh) {
        MySQLConnect connect = new MySQLConnect();
        try {
            String qry = "Insert into khachhang value (";
            qry += "'" + kh.getMakh() + "'";
            qry += "," + "'" + kh.getHo() + "'";
            qry += "," + "'" + kh.getTen() + "'";
            qry += "," + "'" + kh.getSdt() + "'";
            qry += "," + "'" + kh.getDiachi() + "'";
            qry += "," + "'" + kh.getNgaysinh() + "'";
            qry += "," + "'" + kh.getGioitinh() + "'";
            qry += ")";
            connect.st = connect.conn.createStatement();
            connect.st.executeUpdate(qry);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        connect.MySQLDisconnect();
    }

    public void delete(String ma) {
        MySQLConnect connect = new MySQLConnect();
        try {
            String qry = "DELETE FROM khachhang Where MaKH= '" + ma + "'";
            connect.st = (Statement) connect.conn.createStatement();
            connect.st.executeUpdate(qry);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi xoá thông tin");
        }
        connect.MySQLDisconnect();
    }

    public void update(KhachHangDTO kh) {
        MySQLConnect connect = new MySQLConnect();
        try {
            String qry = "Update khachhang set ";
            qry += "Ho=" + "'" + kh.getHo() + "'";
            qry += ",Ten=" + "'" + kh.getTen() + "'";
            qry += ",Sdt=" + "'" + kh.getSdt() + "'";
            qry += ",Diachi=" + "'" + kh.getDiachi() + "'";
            qry += ",Ngaysinh=" + "'" + kh.getNgaysinh() + "'";
            qry += ",Gioitinh=" + "'" + kh.getGioitinh() + "'";
            qry += " " + "where MaKH= '" + kh.getMakh() + "'";
            connect.st = (Statement) connect.conn.createStatement();
            connect.st.executeUpdate(qry);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        connect.MySQLDisconnect();
    }
}
