package controllers;

import helpers.Client;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.HashSet;
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
    public MenuButton recipientMenu;
    Client clientConnection;
    public HashSet<Integer> sendTo =  new HashSet<>();  // contains the list of selected recipients
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addRecipientToMenu("All");
        clientConnection = new Client(data->{
            Platform.runLater(()->{clientChatList.getItems().add(data.toString());
                System.out.println(data.toString()); });

        }, list->{
            Platform.runLater(()->{
                onlineClientsList.getItems().add("Client " + list.toString());
                addRecipientToMenu("Client " + list.toString());
            });

        }, this);
        clientConnection.start();
    }

    @Override
    public void handle(Event event) {
        if(event.getSource().equals(sendBtn)){
            System.out.println("send to: " + sendTo);
            System.out.println("text sent");
            clientConnection.send(messageTxt.getText(), sendTo);  //planning to pass the sendTo arrayList to client class
            messageTxt.clear();
        }

        else if (event.getSource() instanceof CheckMenuItem){   // when user clicks on a recipient in the menu, the recipient is added to the 'sendTo' Hashset
            CheckMenuItem itemSelected = (CheckMenuItem) event.getSource();
            System.out.println("menu selected is "+itemSelected.getId());
            // add the item to the list of people to send to
            // Note: -1 because first item will always be "All". Therefore "All" is -1
            if (itemSelected.isSelected()){
                sendTo.add(Integer.valueOf(itemSelected.getId()));
            }
            else{
                sendTo.remove(Integer.valueOf(itemSelected.getId()));
            }
        }
    }


    public void addRecipientToMenu(String text){
        // this function creates a CheckMenuItem, adds it to recipientMenu and sets its onAction
        CheckMenuItem checkItem = new CheckMenuItem(text);
        checkItem.setId(String.valueOf(recipientMenu.getItems().size()-1));
        checkItem.setOnAction(this);

        recipientMenu.getItems().add(checkItem);
    }
}
