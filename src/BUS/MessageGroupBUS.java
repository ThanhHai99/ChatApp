/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import com.fasterxml.jackson.core.JsonProcessingException;
import DAO.MessageGroupDAO;
import DTO.MessageGroupDTO;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class MessageGroupBUS {
    public MessageGroupDTO showMessageGroup(int groupID) throws JsonProcessingException{
        MessageGroupDTO messagegroup= new MessageGroupDTO();
        MessageGroupDAO messagegroupDAO = new MessageGroupDAO();
        messagegroup = messagegroupDAO.getMessageByGroupID(groupID);
        return messagegroup;
    }
    public void updateMessageGroup(MessageGroupDTO data) throws SQLException{
        MessageGroupDAO updatemessageDAO = new MessageGroupDAO();
        updatemessageDAO.updateMessageGroup(data);
    }
    
    public void addMessageGroup(MessageGroupDTO data) throws SQLException{
        MessageGroupDAO insertmessageDAO = new MessageGroupDAO();
        insertmessageDAO.addMessageGroup(data);
    }
}
