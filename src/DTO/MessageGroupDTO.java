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
public class MessageGroupDTO {
    private int groupID;
    private String content;
    public MessageGroupDTO(){
        
    }
    public MessageGroupDTO(int groupID,String content){
        this.groupID = groupID;
        this.content = content;
    }

    public int getGroupID() {
        return groupID;
    }

    public String getContent() {
        return content;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
