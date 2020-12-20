/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import com.fasterxml.jackson.core.JsonProcessingException;
import DAO.MessageFriendDAO;
import DTO.MessageFriendDTO;
import java.sql.SQLException;
/**
 *
 * @author ADMIN
 */
public class MessageFriendBUS {
    public MessageFriendDTO getMessageByParticipantsID(int participant1,int participant2) throws JsonProcessingException{
        MessageFriendDTO messagefriend = new MessageFriendDTO();
        MessageFriendDAO messagefriendDAO = new MessageFriendDAO();
        messagefriend = messagefriendDAO.getMessageByParticipantsID(participant1, participant2);
        return messagefriend;
    }
    
    public void updateMessageFriend(MessageFriendDTO data) throws SQLException{
        MessageFriendDAO updatemessageDAO = new MessageFriendDAO();
        updatemessageDAO.updateMessageFriend(data);
    }
    
    public void addMessageFriend(MessageFriendDTO data) throws SQLException{
        MessageFriendDAO insertmessageDAO = new MessageFriendDAO();
        insertmessageDAO.addMessageFriend(data);
    }
}
