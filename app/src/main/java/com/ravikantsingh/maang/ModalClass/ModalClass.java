package com.ravikantsingh.maang.ModalClass;

public class ModalClass {

    private String relatedsector;
    private String relatedscheme;
    private String likes;
    private String comments;
    private String imglink;
    private String pdflink;
    private String description;
    private String timestamp;
    private String uid;
    String textHash;

    public String getTextHash() {
        return textHash;
    }

    public void setTextHash(String textHash) {
        this.textHash = textHash;
    }

    public ModalClass(String relatedsector, String relatedscheme, String likes, String comments, String imglink, String pdflink, String description, String timestamp, String uid, String textHash, String userName) {
        this.relatedsector = relatedsector;
        this.relatedscheme = relatedscheme;
        this.likes = likes;
        this.comments = comments;
        this.imglink = imglink;
        this.pdflink = pdflink;
        this.description = description;
        this.timestamp = timestamp;
        this.uid = uid;
        this.textHash = textHash;
        this.userName = userName;
    }

    public ModalClass(String relatedsector, String relatedscheme, String likes, String comments, String imglink, String pdflink, String description, String timestamp, String uid, String userName) {
        this.relatedsector = relatedsector;
        this.relatedscheme = relatedscheme;
        this.likes = likes;
        this.comments = comments;
        this.imglink = imglink;
        this.pdflink = pdflink;
        this.description = description;
        this.timestamp = timestamp;
        this.uid = uid;
        this.userName = userName;
    }

    private String userName;

    public ModalClass(String relatedsector, String relatedscheme, String likes, String comments, String imglink, String pdflink, String description, String timestamp, String uid) {
        this.relatedsector = relatedsector;
        this.relatedscheme = relatedscheme;
        this.likes = likes;
        this.comments = comments;
        this.imglink = imglink;
        this.pdflink = pdflink;
        this.description = description;
        this.timestamp = timestamp;
        this.uid = uid;
    }

    public String getRelatedsector() {
        return relatedsector;
    }

    public void setRelatedsector(String relatedsector) {
        this.relatedsector = relatedsector;
    }

    public String getRelatedscheme() {
        return relatedscheme;
    }

    public void setRelatedscheme(String relatedscheme) {
        this.relatedscheme = relatedscheme;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    public String getPdflink() {
        return pdflink;
    }

    public void setPdflink(String pdflink) {
        this.pdflink = pdflink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
