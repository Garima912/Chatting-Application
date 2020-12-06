package controllers;

import helpers.Client;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.ClientPacket;

import java.net.URL;
import java.util.ArrayList;
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
    public ObservableSet<String> recipientChoices = FXCollections.observableSet(); //list of choices for the ComboBox
    ArrayList<Integer> sendTo =  new ArrayList<>();  // contains the list of selected recipients

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        recipientChoices.add("All");
        clientConnection = new Client(data->{
            Platform.runLater(()->{clientChatList.getItems().add(data.toString());
                System.out.println(data.toString()); });

        }, list->{
            Platform.runLater(()->{
                recipientChoices.add(String.valueOf(list));
                onlineClientsList.getItems().add("Client " + String.valueOf(list));
                recipientsBox.getItems().add("Client " + String.valueOf(list));
                System.out.println("online: " +recipientChoices);
            });

        }, this);

        clientConnection.start();
    }
    // **** select the recipient in the GUI before sending, or else will get an error ****
    @Override
    public void handle(Event event) {

        if(event.getSource().equals(sendBtn)){

            // for client 4 : char at 7 is '4'
            // add 4 to the arrayList 'sendTo'
            sendTo.add(Character.getNumericValue(recipientsBox.getValue().charAt(7))); // just checking for individual selection
            System.out.println("send to: " + sendTo);
            System.out.println("text sent");
            clientConnection.send(messageTxt.getText(), sendTo);  //planning to pass the sendTo arrayList to client class
            messageTxt.clear();
        }
    }
}
