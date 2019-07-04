package com.unlimitedcoffee;

public class Message {
    String number;
    String body;

    public Message(String number, String body) {
        this.number = number;
        this.body = body;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
