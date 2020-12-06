package model;

import java.io.Serializable;
import java.util.HashSet;

public class ClientPacket implements Serializable {

    private HashSet<Integer> clientIds = new HashSet<>(); // will contain list of online client IDs
    private String message = "";
    private String ipAddress = "";
    private Boolean sendToAll = true;

    public HashSet<Integer> getClientIds() {
        return clientIds;
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
