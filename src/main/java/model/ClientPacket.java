package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientPacket implements Serializable {

    private ArrayList<Integer> clientIds =  new ArrayList<>(); // will contain list of online client IDs
    private String message = "";
    private String ipAddress = "";
    private boolean sendToAll = true;
    public boolean isMessage = true;
    ArrayList<String> recipientsIPAddresses;

    public ClientPacket() {
        recipientsIPAddresses = new ArrayList<>();
    }

    public ArrayList<Integer> getClientIds() {
        return clientIds;
    }

    public void setClientIds(ObservableList<Integer>  clientIds) {
        this.clientIds = new ArrayList<>(clientIds);
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

    public void addRecipient(String ipAddress){
        recipientsIPAddresses.add(ipAddress);
    }
    public ArrayList getRecipientAddresses(){
        return recipientsIPAddresses;
    }

}
