package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ClientPacket implements Serializable {

//    private Set<Integer> clientIds =  new HashSet<>(); // will contain list of online client IDs

    private HashMap<Integer, String> clientIdAndIp = new HashMap<>();

    private String message = "";
    private String ipAddress = "";
    private boolean sendToAll = true;
    public boolean isMessage = false;
    public boolean isNewClient = true;
    public int positionInServer = 0;

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

    public void addRecipient(String ipAddress) {
        this.recipients.add(ipAddress);
    }

    private Set<String> recipients = new HashSet<>();  // the client recipients of this packet

}
