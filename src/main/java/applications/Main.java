package applications;

import controllers.GUIController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application{

	Stage primaryStage;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/introSceneGUI.fxml"));

		Parent parent = loader.load();
		Scene introScene = new Scene(parent,600, 600);

		GUIController guiController = loader.getController();
		guiController.initialize(primaryStage);

		primaryStage.setTitle("The Networked Client/Server GUI Example");
		primaryStage.setScene(introScene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});

	}

}