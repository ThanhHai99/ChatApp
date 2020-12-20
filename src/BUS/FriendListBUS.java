/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import com.fasterxml.jackson.core.JsonProcessingException;
import static BUS.UserBUS.dsuser;
import DAO.FriendListDAO;
import DAO.FriendListDAO;
import DTO.FriendListDTO;
import DTO.FriendListDTO;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author ADMIN
 */
public class FriendListBUS {
    
    public FriendListDTO findFriendListByID(int userID) throws SQLException, JsonProcessingException {
        FriendListDTO friendList = new FriendListDTO();
        FriendListDAO friendListDAO = new FriendListDAO();
        friendList = friendListDAO.findFriendListByID(userID);
        return friendList;
    }
    
    public void updateFriendList(FriendListDTO data) throws SQLException{
        FriendListDAO friendListDAO = new FriendListDAO();
        friendListDAO.updateFriendList(data);
    }
    
    public void insertFriendList(FriendListDTO data) throws SQLException{
        FriendListDAO friendListDAO = new FriendListDAO();
        friendListDAO.insertFriendList(data);
    }
}
