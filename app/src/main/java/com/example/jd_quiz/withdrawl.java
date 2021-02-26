package com.example.jd_quiz;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class withdrawl {
    private String UserId;
    private String emailId;
    private String RequestedBy;
    @ServerTimestamp
    private Date CreatedAt;

    public withdrawl(String userId, String emailId, String requestedBy) {
        UserId = userId;
        this.emailId = emailId;
        RequestedBy = requestedBy;
    }

    public withdrawl(){

    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getRequestedBy() {
        return RequestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        RequestedBy = requestedBy;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }
}
