package org.example;

public class User {

    private static int ID = 0;
    private long chatId;
    private int id;

    private int countRequest;

    public User(long chatId){
        this.id = ID;
        ID++;
        this.chatId = chatId;
        this.countRequest =0;
    }

    public void addRequest(){
        this.countRequest++;
    }

    public boolean isEqualChatId(long toCheck){
        return this.chatId == toCheck;
    }
}
