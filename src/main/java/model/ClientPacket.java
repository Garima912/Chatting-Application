package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ClientPacket implements Serializable {

    private Set<Integer> clientIds =  new HashSet<Integer>(); // will contain list of online client IDs
    private String message = "";
    private String ipAddress = "";
    private boolean sendToAll = false;
    public boolean fromServer = true;       // if server is sending the packet
    public HashSet<Integer> recipients = new HashSet<>();

    public void setClientIds(Set<Integer> clientIds) {
        this.clientIds = clientIds;
    }

    public Set<Integer> getClientIds() {
        return clientIds;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSendToAll() {
        return sendToAll;
    }

    public void setSendToAll(Boolean sendToAll) {
        this.sendToAll = sendToAll;
    }

}
