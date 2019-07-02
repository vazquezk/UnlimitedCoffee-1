package com.unlimitedcoffee;

import java.util.ArrayList;

public class Conversation {
    String number;
    ArrayList<String> messages;

    public Conversation (String number, ArrayList<String>messages ){
        this.number = number;
        this.messages= messages;
    }

    public String getNumber() {
        return number;
    }

    public void addMessage(String msg){
        messages.add(msg);
    }
    public String findLastMessage(){
        return this.messages.get(this.messages.size()-1);
    }
}
