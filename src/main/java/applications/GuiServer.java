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

public class GuiServer extends Application{

	Stage primaryStage;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
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


	// sample code given

//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		// TODO Auto-generated method stub
//		primaryStage.setTitle("The Networked Client/Server GUI Example");
//
//		this.serverChoice = new Button("Server");
//		this.serverChoice.setStyle("-fx-pref-width: 300px");
//		this.serverChoice.setStyle("-fx-pref-height: 300px");
//
//		this.serverChoice.setOnAction(e->{ primaryStage.setScene(sceneMap.get("server"));
//			primaryStage.setTitle("This is the Server");
//			serverConnection = new Server(data -> {
//				Platform.runLater(()->{
//					listItems.getItems().add(data.toString());
//				});
//
//			});
//
//		});
//
//
//		this.clientChoice = new Button("Client");
//		this.clientChoice.setStyle("-fx-pref-width: 300px");
//		this.clientChoice.setStyle("-fx-pref-height: 300px");
//
//		this.clientChoice.setOnAction(e-> {primaryStage.setScene(sceneMap.get("client"));
//			primaryStage.setTitle("This is a client");
//			clientConnection = new Client(data->{
//				Platform.runLater(()->{listItems2.getItems().add(data.toString());
//				});
//			});
//
//			clientConnection.start();
//		});
//
//		this.buttonBox = new HBox(400, serverChoice, clientChoice);
//		startPane = new BorderPane();
//		startPane.setPadding(new Insets(70));
//		startPane.setCenter(buttonBox);
//
//		startScene = new Scene(startPane, 800,800);
//
//		listItems = new ListView<String>();
//		listItems2 = new ListView<String>();
//
//		c1 = new TextField();
//		b1 = new Button("Send");
//		b1.setOnAction(e->{clientConnection.send(c1.getText()); c1.clear();});
//
//		sceneMap = new HashMap<String, Scene>();
//
//		sceneMap.put("server",  createServerGui());
//		sceneMap.put("client",  createClientGui());
//
//		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//			@Override
//			public void handle(WindowEvent t) {
//				Platform.exit();
//				System.exit(0);
//			}
//		});
//
//
//
//		primaryStage.setScene(startScene);
//		primaryStage.show();
//
//	}

//	public Scene createServerGui() {
//
//		BorderPane pane = new BorderPane();
//		pane.setPadding(new Insets(70));
//		pane.setStyle("-fx-background-color: coral");
//
//		pane.setCenter(listItems);
//
//		return new Scene(pane, 500, 400);
//
//
//	}
//
//	public Scene createClientGui() {
//
//		clientBox = new VBox(10, c1,b1,listItems2);
//		clientBox.setStyle("-fx-background-color: blue");
//		return new Scene(clientBox, 400, 300);
//
//	}

}