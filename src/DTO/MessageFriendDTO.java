/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Arrays;

/**
 *
 * @author ADMIN
 */
public class MessageFriendDTO {
    private int participant1, participant2;
    private String content;
    public MessageFriendDTO(){
        
    }
    public MessageFriendDTO(int participant1, int participant2, String content){
        this.participant1 = participant1;
        this.participant2 = participant2;
        this.content = content;
    }

    public int getParticipant1() {
        return participant1;
    }

    public int getParticipant2() {
        return participant2;
    }

    public String getContent() {
        return content;
    }
    
    public void setParticipant1(int participant1) {
        this.participant1 = participant1;
    }

    public void setParticipant2(int participant2) {
        this.participant2 = participant2;
    }

    public void setContent(String content) {
        this.content = content;
    } 
}
