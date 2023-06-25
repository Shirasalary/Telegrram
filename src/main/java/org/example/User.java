package org.example;

public class User {

    private static int ID = 0;
    private long chatId;
    private int id;
    private int statusRequest;
    private String apiTypeRequest;

    private int countRequest;

    public User(long chatId){
        this.id = ID;
        ID++;
        this.chatId = chatId;
        this.countRequest = 1;
        this.statusRequest = Constants.START_REQUEST;
        this.apiTypeRequest = "";
    }

    public boolean isEqualApiTypeRequest(String type) {
        return this.apiTypeRequest.equals(type);
    }

    public void setApiTypeRequest(String apiTypeRequest) {
        this.apiTypeRequest = apiTypeRequest;
    }

    public int getStatusRequest(){
        return this.statusRequest;
    }

    public void setStatusRequest(int newStatus){
        this.statusRequest = newStatus;
    }

    public void addRequest(){
        this.countRequest++;
    }

    public int getCountRequest() {
        return this.countRequest;
    }

    public boolean isEqualChatId(long toCheck){
        return this.chatId == toCheck;
    }

    public String toString(){
        return "User -> Id Chat: " + this.chatId;
    }
}
