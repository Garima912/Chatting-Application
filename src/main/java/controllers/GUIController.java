package controllers;

import helpers.Client;
import helpers.Server;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class GUIController implements EventHandler {

    //intro scene GUI elements
	public Button serverBtn;
	public Button clientBtn;
    Stage primaryStage;

    public void initialize(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(Event event) {

        if(event.getSource().equals(serverBtn)){   // open server scene
            try {
                System.out.println("server is chosen");
                Parent pane = FXMLLoader.load(getClass().getResource("/fxml/serverGUI.fxml"));
                Scene serverScene = new Scene(pane, 600,600);
                serverScene.getStylesheets().add("/serverStyle.css");
                primaryStage.setScene(serverScene);
                primaryStage.setTitle("This is the Server");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(event.getSource().equals(clientBtn)){  // open client scene
            System.out.println("client is chosen");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/clientGUI.fxml"));
            Parent parent = null;
            try {
                parent = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.setScene(new Scene(parent, 600,600));
            primaryStage.setTitle("This is the Client");

        }

    }

}
