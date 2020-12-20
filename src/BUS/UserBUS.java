package BUS;

import DAO.UserDAO;
import DAO.UserDAO;
import DTO.UserDTO;
import DTO.UserDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserBUS {

    public static ArrayList<UserDTO> dsuser;

    public UserBUS() {
    }

    public void docDSuser() {
        UserDAO data = new UserDAO();
        if (dsuser == null) {
            dsuser = new ArrayList<UserDTO>();
        }
        dsuser = data.docDSSP();
    }

    public UserDTO Tim(String userName) {
        for (UserDTO user : dsuser) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        return null;
    }
    public UserDTO timFullname(){
        String fullName="";
        for (UserDTO user : dsuser) {
            if (user.getFullname().equals(fullName)) {
                return user;
            }
        }
        return null;
    }
    public UserDTO takeInfoUserByID(int id) {
        UserDTO user = new UserDTO();
        UserDAO userDAO = new UserDAO();
        user = userDAO.takeInfoUserByID(id);
        return user;
    }
    
    public void Register(UserDTO data) throws SQLException{
        UserDAO userDAO = new UserDAO();
        userDAO.Register(data);
    }
}
