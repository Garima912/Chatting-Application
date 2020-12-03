package controllers;

import helpers.Client;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable, EventHandler {

    public HBox parent;
    public VBox chatBox;
    public Text recipientsTxt;
    public ListView clientChatList;
    public TextField messageTxt;
    public Button sendBtn;
    public VBox clientsBox;
    public Text clientListTxt;
    public ListView onlineClientsList;
    public ComboBox<String> recipientsBox;
    Client clientConnection;
    ObservableList<String> recipientChoices = FXCollections.observableArrayList();
    ObservableList<Integer> availableClients = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientConnection = new Client(data->{

            Platform.runLater(()->{clientChatList.getItems().add(data.toString());
                System.out.println(data.toString()); });

        });

        clientConnection.start();
        updateRecipientsBox();
    }

    // this method will update the available clients list (for online Clients listView) and the recipients list for the combobox
    public void updateRecipientsBox() {
        recipientChoices.add("All");
         //TODO: get the clientIds from the packet and add to recipientsChoices ("Client " + clientIds(i) )
        recipientsBox.setItems(recipientChoices);
    }

    @Override
    public void handle(Event event) {

        if(event.getSource().equals(sendBtn)){
            System.out.println("text sent");
            clientConnection.send(messageTxt.getText()); messageTxt.clear();
        }
    }
}
