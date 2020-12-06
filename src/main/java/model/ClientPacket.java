package model;

import java.io.Serializable;
import java.util.HashMap;

public class ClientPacket implements Serializable {

    private HashMap<Integer, String> clientIdAndIp =  new HashMap<>(); // will contain list of online client IDs
    private String message = "";
    private String ipAddress = "";
    private Boolean sendToAll = true;

    public HashMap<Integer, String> getClientIdAndIp() {
        return clientIdAndIp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Boolean getSendToAll() {
        return sendToAll;
    }

    public void setSendToAll(Boolean sendToAll) {
        this.sendToAll = sendToAll;
    }

}
