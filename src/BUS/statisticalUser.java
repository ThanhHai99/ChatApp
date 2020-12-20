/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import MyConnection.MySQLConnect;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class statisticalUser {
    public void statisticalUser() throws SQLException{
        MySQLConnect ConnectData = new MySQLConnect();
        String sql = "select count(*) from user";
        ConnectData.st = ConnectData.conn.createStatement();
        ConnectData.rs = ConnectData.st.executeQuery(sql);
        while (ConnectData.rs.next()) {
            System.out.println("Tổng user : "+ ConnectData.rs.getInt(1));
        }
        ConnectData.MySQLDisconnect();
    }
}
