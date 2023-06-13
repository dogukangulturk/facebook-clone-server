package com.orcrist.facebookcloneserver.util;

public class EmailDetails {

    private String recipient;
    private String subject;
    private String msgBody;
    private String attachment;

    public EmailDetails(String recipient, String subject, String msgBody, String attachment) {
        this.recipient = recipient;
        this.subject = subject;
        this.msgBody = msgBody;
        this.attachment = attachment;
    }

    public EmailDetails() {
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
