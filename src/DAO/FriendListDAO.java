/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;        
import DTO.FriendListDTO;
import MyConnection.MySQLConnect;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author ADMIN
 */
public class FriendListDAO {
    public FriendListDTO findFriendListByID(int id) throws SQLException,JsonProcessingException {
        MySQLConnect ConnectData = new MySQLConnect();
        String sql = "select * from friendlist where userID = '" +id+"'";
        ConnectData.st = ConnectData.conn.createStatement();
        ConnectData.rs = ConnectData.st.executeQuery(sql);
        FriendListDTO friendlist = new FriendListDTO();
        while (ConnectData.rs.next()) {
            friendlist.setUserID(ConnectData.rs.getInt(1));
            ObjectMapper mapper = new ObjectMapper();
            String arr = ConnectData.rs.getString(2);
            int[] pp1 = mapper.readValue(arr, int[].class);
            friendlist.setUsername(pp1);
        }
        ConnectData.MySQLDisconnect();
        return friendlist;
    }
    
    public void updateFriendList(FriendListDTO data) throws SQLException{
        MySQLConnect ConnectData = new MySQLConnect();
        String sql = "update friendlist set username = '" +Arrays.toString(data.getUsername())+ "' where userID = '" +data.getUserID()+"'";
        ConnectData.st = ConnectData.conn.createStatement();
         ConnectData.st.executeUpdate(sql);
        
        ConnectData.MySQLDisconnect ();     
    }
    
    public void insertFriendList(FriendListDTO data) throws SQLException{
        MySQLConnect ConnectData = new MySQLConnect();
        String sql = "insert into friendlist (userID,username) values ('" +data.getUserID()+ "','" +Arrays.toString(data.getUsername())+"')";
        ConnectData.st = ConnectData.conn.createStatement();
        ConnectData.st.executeUpdate(sql);
        
        ConnectData.MySQLDisconnect (); 
    }
}

