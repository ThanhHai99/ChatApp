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
public class GroupChatDTO {
    private int groupID;
    private String groupname;
    private int[] memberlist;
    public GroupChatDTO(int groupID, String groupname, int[] memberlist){
        this.groupID = groupID;
        this.groupname = groupname;
        this.memberlist = memberlist;
    }
    public GroupChatDTO(){
        
    }

    public int getGroupID() {
        return groupID;
    }

    public String getGroupname() {
        return groupname;
    }

    public int[] getMemberlist() {
        return memberlist;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public void setMemberlist(int[] memberlist) {
        this.memberlist = memberlist;
    }
    
}
