package com.ravikantsingh.maang.ModalClass;

public class BaseMessage {
    String message;
    String UserImage;
    String time;
    String name;
    int type;

    public BaseMessage(String message, String time, int type) {
        this.message = message;
        this.time = time;
        this.type = type;
    }

    public BaseMessage(String message, String time, String name, int type) {
        this.message = message;
        this.time = time;
        this.name = name;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public BaseMessage(String message, String userImage, String time, String name, int type) {
        this.message = message;
        UserImage = userImage;
        this.time = time;
        this.name = name;
        this.type = type;
    }
}
