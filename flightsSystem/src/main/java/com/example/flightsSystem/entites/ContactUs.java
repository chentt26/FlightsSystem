package com.example.flightsSystem.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ContactUs {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;
    private String content;
    private String senderName;

    public ContactUs(){

    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public ContactUs(int messageId, String content, String senderName) {
        this.messageId = messageId;
        this.content = content;
        this.senderName = senderName;
    }

    @Override
    public String toString() {
        return "ContactUs{" +
                "messageId=" + messageId +
                ", content='" + content + '\'' +
                ", senderName='" + senderName + '\'' +
                '}';
    }
}
