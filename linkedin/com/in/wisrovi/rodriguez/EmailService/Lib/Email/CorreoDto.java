package SenInt2.fcv.org.EmailService.linkedin.com.in.wisrovi.rodriguez.EmailService.Lib.Email;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;

public class CorreoDto implements Serializable {

    private Properties sessionInstance;
    private String sessionTransport;
    private Boolean sessionDebug;
    private String transportName;
    private String transportUser;
    private String transportPassword;
    private Address from;
    private List<Address> to;
    private List<Address[]> cc;
    private List<Address[]> bcc;
    private String messageSubject;
    private String messageCharset;
    private String messageSubtype;
    private String messageText;

    public CorreoDto() {
        try {
            cc = new ArrayList<>();
            bcc = new ArrayList<>();
            sessionInstance = UtilCorreo.getProperties("Correo.properties");
            sessionTransport = UtilCorreo.getProperties(sessionInstance, "sessionTransport");
            transportName = UtilCorreo.getProperties(sessionInstance, "transportName");
            transportUser = UtilCorreo.getProperties(sessionInstance, "transportUser");
            transportPassword = UtilCorreo.getProperties(sessionInstance, "transportPassword");
            messageCharset = UtilCorreo.getProperties(sessionInstance, "messageCharset");
            messageSubtype = UtilCorreo.getProperties(sessionInstance, "messageSubtype");
            from = new InternetAddress(transportUser,transportName);
        } catch (Exception ex) {
            UtilCorreo.Exception(ex);
        }
    }

    public Properties getSessionInstance() {
        return sessionInstance;
    }

    public void setSessionInstance(Properties sessionInstance) {
        this.sessionInstance = sessionInstance;
    }

    public String getSessionTransport() {
        return sessionTransport;
    }

    public void setSessionTransport(String sessionTransport) {
        this.sessionTransport = sessionTransport;
    }

    public Boolean getSessionDebug() {
        return sessionDebug;
    }

    public void setSessionDebug(Boolean sessionDebug) {
        this.sessionDebug = sessionDebug;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }

    public String getTransportUser() {
        return transportUser;
    }

    public void setTransportUser(String transportUser) {
        this.transportUser = transportUser;
    }

    public String getTransportPassword() {
        return transportPassword;
    }

    public void setTransportPassword(String transportPassword) {
        this.transportPassword = transportPassword;
    }

    public Address getFrom() {
        return from;
    }

    public void setFrom(Address from) {
        this.from = from;
    }

    public List<Address> getTo() {
        return to;
    }

    public void setTo(List<Address> to) {
        this.to = to;
    }

    public List<Address[]> getCc() {
        return cc;
    }

    public void setCc(List<Address[]> cc) {
        this.cc = cc;
    }

    public List<Address[]> getBcc() {
        return bcc;
    }

    public void setBcc(List<Address[]> bcc) {
        this.bcc = bcc;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageCharset() {
        return messageCharset;
    }

    public void setMessageCharset(String messageCharset) {
        this.messageCharset = messageCharset;
    }

    public String getMessageSubtype() {
        return messageSubtype;
    }

    public void setMessageSubtype(String messageSubtype) {
        this.messageSubtype = messageSubtype;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    
}
