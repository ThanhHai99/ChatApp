/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *@author ADMIN
 */
public class infoUser {

    public int id;
    public String fullname;
    public boolean isOnline;

    public infoUser(int id, String fullname, boolean isOnline) {
        this.id = id;
        this.fullname = fullname;
        this.isOnline = isOnline;
    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return fullname+"";
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

}

