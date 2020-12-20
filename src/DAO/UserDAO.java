/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MyConnection.MySQLConnect;
import DTO.UserDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class UserDAO {

    public UserDAO() {

    }

    public ArrayList docDSSP() {
        MySQLConnect ConnectData = new MySQLConnect();
        ArrayList dsuser = new ArrayList<UserDTO>();
        try {
            String qry = "select * from user";
            ConnectData.st = ConnectData.conn.createStatement();
            ConnectData.rs = ConnectData.st.executeQuery(qry);
            while (ConnectData.rs.next()) {
                UserDTO user = new UserDTO();
                user.setId(ConnectData.rs.getInt(1));
                user.setUsername(ConnectData.rs.getString(2));
                user.setPassword(ConnectData.rs.getString(3));
                user.setFullname(ConnectData.rs.getString(4));
                user.setSex(ConnectData.rs.getString(5));
                user.setBirthday(ConnectData.rs.getString(6));
                dsuser.add(user);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        ConnectData.MySQLDisconnect();
        return dsuser;
    }

    public UserDTO takeInfoUserByID(int id) {
        MySQLConnect ConnectData = new MySQLConnect();
        UserDTO user = new UserDTO();
        try {
            String sql = "select fullname from user where userID = '" + id + "'";
            ConnectData.st = ConnectData.conn.createStatement();
            ConnectData.rs = ConnectData.st.executeQuery(sql);
            while (ConnectData.rs.next()) {
                user.setFullname(ConnectData.rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("loi");
        }
        ConnectData.MySQLDisconnect();
        return user;
    }
    public void Register(UserDTO data) throws SQLException{
        MySQLConnect ConnectData = new MySQLConnect();
        String sql = "insert into user (userID,username,password,fullname,sex,birthday) values (NULL,'"+data.getUsername()+"','"+data.getPassword()+"','"+data.getFullname()+"','"+data.getSex()+"','"+data.getBirthday()+"')";
        ConnectData.st = ConnectData.conn.createStatement();
        ConnectData.st.executeUpdate(sql);
        
        ConnectData.MySQLDisconnect();
    }
}
