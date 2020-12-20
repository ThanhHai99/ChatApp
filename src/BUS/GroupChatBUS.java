/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import com.fasterxml.jackson.core.JsonProcessingException;
import DAO.GroupChatDAO;
import DTO.GroupChatDTO;
import static BUS.UserBUS.dsuser;
import DTO.FriendListDTO;
import MyConnection.MySQLConnect;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author ADMIN
 */
public class GroupChatBUS {

    public static ArrayList<GroupChatDTO> dsgroupchat;

    public void showGroupChat() throws SQLException, JsonProcessingException {
        GroupChatDAO data = new GroupChatDAO();
        if (dsgroupchat == null) {
            dsgroupchat = new ArrayList<GroupChatDTO>();
        }
        dsgroupchat = data.showGroupChat();
    }
    
    public GroupChatDTO getMemberListByGroupID(int groupID) throws SQLException, JsonProcessingException{
        GroupChatDTO memberlist = new GroupChatDTO();
        GroupChatDAO memberlistDAO = new GroupChatDAO();
        memberlist = memberlistDAO.getMemberListByGroupID(groupID);
        return memberlist;
    }
    
    public void insertGroupChat(GroupChatDTO data) throws SQLException{
        GroupChatDAO groupchatDAO = new GroupChatDAO();
        groupchatDAO.insertGroupChat(data);
    }
    
    public void insertMember(GroupChatDTO data) throws SQLException{
        GroupChatDAO groupchatDAO = new GroupChatDAO();
        groupchatDAO.insertMember(data);
    }
}
