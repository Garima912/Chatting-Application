package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientPacket implements Serializable {

    private ObservableList<Integer> clientIds =  FXCollections.observableArrayList(); // will contain list of online client IDs
    private String message = "";
    private String ipAddress = "";
    private Boolean sendToAll = true;

    public ObservableList<Integer> getClientIds() {
        return clientIds;
    }

    public void setClientIds(ObservableList<Integer>  clientIds) {
        this.clientIds = clientIds;
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
