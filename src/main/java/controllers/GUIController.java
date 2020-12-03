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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GUIController implements EventHandler {

    //intro scene GUI elements
	public HBox container;
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

    public GUIController(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        Parent serverParent = FXMLLoader.load(getClass().getResource("/fxml/serverGUI.fxml"));
        Scene serverScene = new Scene(serverParent,600, 600);
        Parent clientParent = FXMLLoader.load(getClass().getResource("/fxml/clientGUI.fxml"));
        Scene clientScene = new Scene(clientParent,600, 600);
        sceneMap.put("server",  serverScene);
        sceneMap.put("client",  clientScene);
    }

    @Override
    public void handle(Event event) {

        if(event.getSource().equals(serverBtn)){
            primaryStage.setScene(sceneMap.get("server"));
            primaryStage.setTitle("This is the Server");
            serverConnection = new Server(data -> {
                Platform.runLater(()->{
                    listItems.getItems().add(data.toString());
                });

            });
        }
        if(event.getSource().equals(clientBtn)){
            primaryStage.setScene(sceneMap.get("client"));
            primaryStage.setTitle("This is a client");
            clientConnection = new Client(data->{
                Platform.runLater(()->{clientChatList.getItems().add(data.toString());
                });
            });

            clientConnection.start();
        }

    }

}
