/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.SQLException;
import DTO.MessageFriendDTO;
import DTO.MessageFriendDTO;
import MyConnection.MySQLConnect;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
public class MessageFriendDAO {

    public MessageFriendDTO getMessageByParticipantsID(int participant1, int participant2) {
        MySQLConnect ConnectData = new MySQLConnect();
        MessageFriendDTO messagefriend = new MessageFriendDTO();
        try {
            String sql = "select * from message where (participant1 = '" + participant1 + "' and participant2 = '" + participant2 + "') or (participant1 = '" + participant2 + "' and participant2 = '" + participant1 + "')";
            ConnectData.st = ConnectData.conn.createStatement();
            ConnectData.rs = ConnectData.st.executeQuery(sql);
            while (ConnectData.rs.next()) {
                messagefriend.setParticipant1(ConnectData.rs.getInt(1));
                messagefriend.setParticipant2(ConnectData.rs.getInt(2));
                messagefriend.setContent(ConnectData.rs.getString(3));
            } 
        } catch (SQLException e) {
            System.out.println("loi");
        }
        ConnectData.MySQLDisconnect();
        return messagefriend;
    }
    
            
    public void updateMessageFriend(MessageFriendDTO data) throws SQLException{
        MySQLConnect ConnectData = new MySQLConnect();
        String sql = "update message set content = '" +data.getContent()+ "' where (participant1 = '" + data.getParticipant1() + "' and participant2 = '" + data.getParticipant2() + "') or (participant1 = '" + data.getParticipant2() + "' and participant2 = '" + data.getParticipant1() + "')";
        ConnectData.st = ConnectData.conn.createStatement();
        ConnectData.st.executeUpdate(sql);
        
        ConnectData.MySQLDisconnect();
    }
    
    public void addMessageFriend(MessageFriendDTO data) throws SQLException{
        MySQLConnect ConnectData = new MySQLConnect();
        String sql = "insert into message (participant1,participant2,content) values ('" + data.getParticipant1()+ "' , '" +data.getParticipant2()+ "' , '" +data.getContent()+"')";
        ConnectData.st = ConnectData.conn.createStatement();
        ConnectData.st.executeUpdate(sql);
        
        ConnectData.MySQLDisconnect();
    }
}
