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
                System.out.println("server is chosen");
                Parent pane = FXMLLoader.load(getClass().getResource("/fxml/serverGUI.fxml"));
                primaryStage.setScene(new Scene(pane, 700,700));
                primaryStage.setTitle("This is the Server");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(event.getSource().equals(clientBtn)){
            System.out.println("client is chosen");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/clientGUI.fxml"));
            Parent parent = null;
            try {
                parent = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.setScene(new Scene(parent, 700,700));
            primaryStage.setTitle("This is the Client");

        }

    }

}
