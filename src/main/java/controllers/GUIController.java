package controllers;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

public class GUIController implements EventHandler {

    //intro scene GUI elements
	public Button serverBtn;
	public Button clientBtn;

    //client GUI elements
    public HBox parent;
    public VBox chatBox;
    public Text recipientsTxt;
    public ChoiceBox recipientsBox;
    public ListView clientChatList;
    public TextField messageTxt;
    public Button sendBtn;
    public VBox clientsBox;
    public Text clientListTxt;
    public ListView onlineClientsList;
    Client clientConnection;

    //server GUI elements
    public BorderPane pane;
    public ListView listItems;
    Server serverConnection;
    HashMap<String, Scene> sceneMap;
    Stage primaryStage;

    public void initialize(Stage primaryStage, BorderPane serverParent, HBox clientParent){
        this.primaryStage = primaryStage;
        System.out.println("Got the primary stage");

        if (this.primaryStage == null){
            System.out.println("primary stage is null");
        }
        else{
            System.out.println("Primary stage is not null");
        }

    }

    @Override
    public void handle(Event event) {

        if(event.getSource().equals(serverBtn)){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/serverGUI.fxml"));
                Parent parent = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            serverConnection = new Server(data -> {
                Platform.runLater(()->{
                    listItems.getItems().add(data.toString());
                });

            });
        }

        if(event.getSource().equals(clientBtn)){
            clientConnection = new Client(data->{
                Platform.runLater(()->{clientChatList.getItems().add(data.toString());
                });
            });

            clientConnection.start();
        }

    }


}
