package controllers;

import helpers.Server;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import model.ClientPacket;

import java.net.URL;
import java.util.ResourceBundle;

// This class updates the server GUI when new message is received
public class ServerController implements Initializable {

    public BorderPane pane;
    public ListView listItems;
    Server serverConnection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serverConnection = new Server(data -> {
            Platform.runLater(()->{
                listItems.getItems().add(data.toString());
            });
        });

    }

}
