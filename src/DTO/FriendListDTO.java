/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class FriendListDTO {
    private int userID;
    private int username[];
    
    public FriendListDTO(int userID,int username[]){
        this.userID = userID;
        this.username = username;
    }

    public FriendListDTO() {

    }

    public int getUserID() {
        return userID;
    }

    public int[] getUsername() {
        return username;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(int[] username) {
        this.username = username;
    }


    
}
