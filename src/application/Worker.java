package application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import DTO.infoGroup;
import BUS.FriendListBUS;
import BUS.UserBUS;
import DTO.UserDTO;
import DTO.FriendListDTO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DTO.infoUser;
import BUS.GroupChatBUS;
import DTO.GroupChatDTO;
import BUS.MessageFriendBUS;
import DTO.MessageFriendDTO;
import BUS.MessageGroupBUS;
import BUS.statisticalUser;
import DAO.FriendListDAO;
import DTO.MessageGroupDTO;
import DTO.contentMessage;
import java.awt.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Worker implements Runnable {

    private int myName;
    private Socket socket;
    BufferedReader in;
    BufferedWriter out;

    public Worker(Socket s) throws IOException {
        this.socket = s;
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    }

    public void sendToOne(int id, String mess) throws IOException, SQLException {
        for (Worker worker : Server.workers) {
            if (worker.myName == id) {
                worker.out.write(this.myName + "#" + mess + '\n');
                worker.out.flush();
                System.out.println("Server write: " + mess + " to " + worker.myName);
                MessageFriendBUS messagefriendBUS = new MessageFriendBUS();
                MessageFriendDTO messagefriend = new MessageFriendDTO();
                messagefriend = messagefriendBUS.getMessageByParticipantsID(this.myName, worker.myName);
                if (messagefriend.getParticipant1() == 0) {
                    insertMessageFriend(this.myName, worker.myName, mess);
                    break;
                } else {
                    updateMessageFriend(this.myName, worker.myName, mess);
                    break;
                }

            }
        }
    }

    public void sendToGroup(int groupID, String mess) throws SQLException, JsonProcessingException, IOException {
        GroupChatBUS groupchatBUS = new GroupChatBUS();
        GroupChatDTO groupchat = new GroupChatDTO();
        groupchat = groupchatBUS.getMemberListByGroupID(groupID);
        int[] arr = groupchat.getMemberlist();
        for (int i = 0; i < arr.length; i++) {
            if (this.myName == arr[i]) {
                continue;
            }
            for (Worker worker : Server.workers) {
                if (worker.myName == arr[i]) {
                    worker.out.write(this.myName + "#" + mess + '\n');
                    worker.out.flush();
                    System.out.println("Server write: " + mess + " to " + worker.myName);
                    MessageGroupBUS messagegroupBUS = new MessageGroupBUS();
                    MessageGroupDTO messagegroup = new MessageGroupDTO();
                    messagegroup = messagegroupBUS.showMessageGroup(groupID);
                    if (messagegroup.getGroupID() == 0) {
                        insertMessageGroup(groupID, this.myName, mess);
                        break;
                    } else {
                        updateMessageGroup(groupID, this.myName, mess);
                        break;
                    }
                }
            }
        }
    }

    public void updateMessageFriend(int participant1, int participant2, String mess) throws SQLException, JsonProcessingException {
        ArrayList arrMess = new ArrayList<contentMessage>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        MessageFriendBUS messagefriendBUS = new MessageFriendBUS();
        MessageFriendDTO messagefriend = new MessageFriendDTO();
        messagefriend = messagefriendBUS.getMessageByParticipantsID(participant1, participant2);
        contentMessage[] respone = new Gson().fromJson(messagefriend.getContent(), contentMessage[].class);
        for (contentMessage s : respone) {
            contentMessage contentLoad = new contentMessage(s.getFrom(), s.getTime(), s.getContent());
            arrMess.add(contentLoad);
        }
        contentMessage content = new contentMessage(participant1, dateFormat.format(date), mess);
        arrMess.add(content);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String JSONObject = gson.toJson(arrMess).replace("\"", "\\\"");
        MessageFriendDTO update = new MessageFriendDTO(participant1, participant2, JSONObject);
        messagefriendBUS.updateMessageFriend(update);
    }

    public void insertMessageFriend(int participant1, int participant2, String mess) throws SQLException {
        ArrayList arrMess = new ArrayList<contentMessage>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        contentMessage content = new contentMessage(participant1, dateFormat.format(date), mess);
        arrMess.add(content);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String JSONObject = gson.toJson(arrMess).replace("\"", "\\\"");
        MessageFriendBUS messagefriendBUS = new MessageFriendBUS();
        MessageFriendDTO insert = new MessageFriendDTO(participant1, participant2, JSONObject);
        messagefriendBUS.addMessageFriend(insert);
    }

    public void updateMessageGroup(int groupID, int sender, String mess) throws SQLException, JsonProcessingException {
        ArrayList arrMess = new ArrayList<contentMessage>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        MessageGroupBUS messagegroupBUS = new MessageGroupBUS();
        MessageGroupDTO messagegroup = new MessageGroupDTO();
        messagegroup = messagegroupBUS.showMessageGroup(groupID);
        contentMessage[] respone = new Gson().fromJson(messagegroup.getContent(), contentMessage[].class);
        for (contentMessage s : respone) {
            contentMessage contentLoad = new contentMessage(s.getFrom(), s.getTime(), s.getContent());
            arrMess.add(contentLoad);
        }
        contentMessage content = new contentMessage(sender, dateFormat.format(date), mess);
        arrMess.add(content);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String JSONObject = gson.toJson(arrMess).replace("\"", "\\\"");
        MessageGroupDTO update = new MessageGroupDTO(groupID, JSONObject);
        messagegroupBUS.updateMessageGroup(update);
    }

    public void insertMessageGroup(int groupID, int sender, String mess) throws SQLException {
        ArrayList arrMess = new ArrayList<contentMessage>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        contentMessage content = new contentMessage(sender, dateFormat.format(date), mess);
        arrMess.add(content);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String JSONObject = gson.toJson(arrMess).replace("\"", "\\\"");
        MessageGroupBUS messagegroupBUS = new MessageGroupBUS();
        MessageGroupDTO insert = new MessageGroupDTO(groupID, JSONObject);
        messagegroupBUS.addMessageGroup(insert);
    }

    public void systemCommand(String mess) throws IOException {
        this.out.write("system#" + mess + "\n");
        this.out.flush();
        System.out.println("Send: system#" + mess + " to " + this.myName);
    }

//    public void setName(String name) throws IOException{    
//        this.myName = name;
//        //systemCommand("showGUI,"+this.myName);
//    }
    public void login(String userName, String password) throws IOException, SQLException {
        UserBUS bususer = new UserBUS();
        bususer.docDSuser();
        UserDTO user = new UserDTO();
        user = bususer.Tim(userName);

        if (user == null) {
            systemCommand("login#Tài khoản không tồn tại");
        } else if (userName.equals(user.getUsername()) && password.equals(user.getPassword())) {
            for(Worker worker : Server.workers){
                if(worker.myName == user.getId()){
                    systemCommand("login#Tài khoản đã đăng nhập");
                    return;
                }
            }
            systemCommand("login#success#" + user.getFullname());
            this.myName = user.getId();
            showFriendList();
            showGroupChat();
        } else {
            systemCommand("login#Mật khẩu không đúng");
        }

    }
    
    public void register(String username,String password,String fullname,String sex,String birthday) throws SQLException, IOException{
        UserBUS userBUS = new UserBUS();
        userBUS.docDSuser();
        UserDTO user = new UserDTO();
        user = userBUS.Tim(username);
        if(user == null){
            UserDTO register = new UserDTO(0,username,password,fullname,sex,birthday);
            userBUS.Register(register);
            systemCommand("register#success");
        }else{
            systemCommand("register#Username đã tồn tại");
        }
    }
    
    public void addFriend(String username) throws IOException, SQLException{
        UserBUS bususer = new UserBUS();
        bususer.docDSuser();
        FriendListBUS friendlistBUS = new FriendListBUS();
        FriendListDTO friendlist = new FriendListDTO();
        friendlist = friendlistBUS.findFriendListByID(this.myName);
        UserDTO user = new UserDTO();
        user = bususer.Tim(username);
        if(user == null){
            systemCommand("addfriend#Tài khoản không tồn tại");
            return;
        }
        if(user.getId() == this.myName){
            return;
        }
        if(friendlist.getUserID() == 0 ) {
            insertFriendList(this.myName,user.getId());
        }else{                             
            updateFriendList(this.myName,user.getId());
        }
        systemCommand("addfriend#" + user.getFullname());
        showFriendList();
        showGroupChat();
        friendlist = friendlistBUS.findFriendListByID(user.getId());
        if(friendlist.getUserID() == 0 ) {
            insertFriendList(user.getId(),this.myName);
        }else{                             
            updateFriendList(user.getId(),this.myName);
        }
        
        for(Worker worker : Server.workers){
            if(worker.myName == user.getId()){
                worker.systemCommand("addfriend#");
                worker.showFriendList();
                worker.showGroupChat();
            }
        }
    }
    
    public void updateFriendList(int isAddID , int isAddedID) throws SQLException, JsonProcessingException{
        FriendListBUS friendlistBUS = new FriendListBUS();
        FriendListDTO friendlist = new FriendListDTO();
        friendlist = friendlistBUS.findFriendListByID(isAddID);
        int[] arr = friendlist.getUsername();
        int [] newarr = new int[arr.length + 1]; 
        for(int i = 0; i< arr.length; i++){
            if(arr[i] == isAddedID){
                return;
            }else{
                newarr[i] = arr[i];
            }
        }
        newarr[arr.length] = isAddedID;
        FriendListDTO updatefriendlist = new FriendListDTO(isAddID,newarr);
        friendlistBUS.updateFriendList(updatefriendlist);
    }
    
    public void insertFriendList(int isAddID , int isAddedID) throws SQLException{
        FriendListBUS friendlistBUS = new FriendListBUS();
        int[] arr = new int[1];
        arr[0] = isAddedID;
        FriendListDTO insertfriendlist = new FriendListDTO(isAddID,arr);
        friendlistBUS.insertFriendList(insertfriendlist);
    }
     
    public void addGroup(String groupname) throws SQLException, JsonProcessingException, IOException{
        GroupChatBUS groupchatBUS = new GroupChatBUS();
        groupchatBUS.showGroupChat();
        insertGroupChat(this.myName,groupname);
        systemCommand("addgroup#" + groupname);
        showFriendList();
        showGroupChat();
    }
    
    public void insertGroupChat(int id , String groupname) throws SQLException{
        GroupChatBUS groupchatBUS = new GroupChatBUS();
        int[] arr = new int[1];
        arr[0] = id;
        GroupChatDTO groupchatDTO = new GroupChatDTO(0,groupname,arr);
        groupchatBUS.insertGroupChat(groupchatDTO);
    }
    
    public void addMember(int groupid, String username) throws SQLException, JsonProcessingException, IOException{
        GroupChatBUS groupchatBUS = new GroupChatBUS();
        groupchatBUS.showGroupChat();
        insertMember(groupid,username);
        systemCommand("addmember#" + username);
        showFriendList();
        showGroupChat();
    }
        
    public void insertMember(int groupid, String username) throws SQLException, JsonProcessingException, IOException{
        GroupChatBUS groupchatBUS = new GroupChatBUS();
        GroupChatDTO groupchat = new GroupChatDTO();
        groupchat = groupchatBUS.getMemberListByGroupID(groupid);
        UserBUS bususer = new UserBUS();
        bususer.docDSuser();
        UserDTO user = new UserDTO();
        user = bususer.Tim(username);
        int[] arr = groupchat.getMemberlist();
        int [] newarr = new int[arr.length + 1]; 
        for(int i = 0; i< arr.length; i++){
            if(arr[i] == user.getId()){
                return;
            }else{
                newarr[i] = arr[i];
            }
        }
        newarr[arr.length] = user.getId();
        GroupChatDTO insertMember = new GroupChatDTO(groupid,groupchat.getGroupname(),newarr);
        groupchatBUS.insertMember(insertMember);
        for(Worker worker : Server.workers){
            if(worker.myName == user.getId()){
                worker.systemCommand("addmember#");
                worker.showFriendList();
                worker.showGroupChat();
            }
        }
    }
    
    public void showFriendList() throws SQLException, JsonProcessingException, IOException {
        UserBUS bususer = new UserBUS();
        UserDTO user = new UserDTO();
        FriendListBUS friendlistBUS = new FriendListBUS();
        FriendListDTO friendlist = friendlistBUS.findFriendListByID(this.myName);
        int[] arr = friendlist.getUsername();
        if (arr == null) {
            systemCommand("friendlist#");
            return;
        }
        ArrayList dsfriend = new ArrayList<infoUser>();
        for (int i = 0; i < arr.length; i++) {
            user = bususer.takeInfoUserByID(arr[i]);
            boolean isOnline = false;
            for (Worker worker : Server.workers) {
                if (arr[i] == worker.myName) {
                    isOnline = true;
                    break;
                }
            }
            infoUser infouser = new infoUser(arr[i], user.getFullname(), isOnline);
            dsfriend.add(infouser);
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String JSONObject = gson.toJson(dsfriend);
        systemCommand("friendlist#" + JSONObject);
    }

    public void showGroupChat() throws SQLException, JsonProcessingException, IOException {
        GroupChatBUS groupchatBUS = new GroupChatBUS();
        groupchatBUS.showGroupChat();
        ArrayList dsgroup = new ArrayList<infoGroup>();
        for (GroupChatDTO groupchat : groupchatBUS.dsgroupchat) {
            int[] arr = groupchat.getMemberlist();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == this.myName) {
                    infoGroup infogroup = new infoGroup(groupchat.getGroupID(), groupchat.getGroupname());
                    dsgroup.add(infogroup);
                    break;
                }
            }
        }
        if (dsgroup.size() == 0) {
            systemCommand("groupchat#");
            return;
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String JSONObject = gson.toJson(dsgroup);
        systemCommand("groupchat#" + JSONObject);
    }

    public void showMessageFriend(int participant1, int participant2) throws JsonProcessingException, IOException {
        MessageFriendBUS messagefriendBUS = new MessageFriendBUS();
        MessageFriendDTO messagefriend = new MessageFriendDTO();
        messagefriend = messagefriendBUS.getMessageByParticipantsID(participant1, participant2);
        if (messagefriend.getContent() == null) {
            systemCommand("showMessageFriend#");
            return;
        }
        systemCommand("showMessageFriend#" + messagefriend.getContent());
    }

    public void showMessageGroup(int groupID) throws JsonProcessingException, IOException {
        MessageGroupBUS messagegroupBUS = new MessageGroupBUS();
        MessageGroupDTO messagegroup = new MessageGroupDTO();
        messagegroup = messagegroupBUS.showMessageGroup(groupID);
        if (messagegroup.getContent() == null) {
            systemCommand("showMessageGroup#");
            return;
        }
        systemCommand("showMessageGroup#" + messagegroup.getContent());
    }
            
    public void Process(String line) throws IOException, SQLException {
        if (!line.contains("#")) {
            this.out.write("Syntax error" + '\n');
            this.out.flush();
            System.out.println("Server write: id is not found! to " + this.myName);
        } else {
            String[] parts = line.split("#");
            switch (parts[0]) {
                case "group": {
                    sendToGroup(Integer.parseInt(parts[1]), parts[2]);
                }
                break;
                case "login": {
                    login(parts[1], parts[2]);
                }
                break;
                case "register": {
                    register(parts[1],parts[2],parts[3],parts[4],parts[5]);
                }
                break;
                case "addfriend": {
                    addFriend(parts[1]);
                }
                break;
                case "addgroup": {
                    addGroup(parts[1]);
                }
                break;
                case "addmember": {
                    addMember(Integer.parseInt(parts[1]),parts[2]);
                }
                break;
                case "showMessageFriend": {
                    showMessageFriend(this.myName, Integer.parseInt(parts[1]));
                }
                break;
                case "showMessageGroup": {
                    showMessageGroup(Integer.parseInt(parts[1]));
                }
                break;
                default: {
                    sendToOne(Integer.parseInt(parts[0]), parts[1]);
                }
            }
        }
    }

    public void run() {
        System.out.println("Client " + socket.toString() + " accepted");
        try {
            String input = "";
            while (true) {
                input = in.readLine();
                System.out.println("Server received: " + input + " from " + socket.toString() + " # Client " + myName);
                Process(input);
//                Scanner sc = new Scanner(System.in);
//                String com = sc.nextLine(); // sumUser
//                if(com.equals("sumUser")) {
//                        System.out.println("Tổng số User đang online: " + Server.sum);
//                        statisticalUser statistical = new statisticalUser();
//                        statistical.statisticalUser();
//                        break;
//                }
            }
//            in.close();
//            out.close();
//            socket.close();
//            Server.workers.remove(this);
        } catch (IOException e) {
            System.out.println(e);
        } catch (SQLException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
