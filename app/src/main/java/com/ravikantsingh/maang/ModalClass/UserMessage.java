package com.ravikantsingh.maang.ModalClass;

public class UserMessage {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    User sender;
    long createdAt;

    class User {
        String nickname;
        String profileUrl;
    }
}
