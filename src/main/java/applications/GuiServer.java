package applications;
//package applications;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.ResourceBundle;
//import java.util.concurrent.Executor;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import controllers.Client;
//import controllers.Server;
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.geometry.Insets;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.stage.WindowEvent;
//
//public class GuiServer extends Application {
//	@FXML Button serverBtn;
//	@FXML Button clientBtn;
//
//	HashMap<String, Scene> sceneMap;
//	Server serverConnection;
//	Client clientConnection;
//	Stage primaryStage;
//
//	ListView<String> listItems, listItems2;
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		launch(args);
//	}
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {
////		// TODO Auto-generated method stub
//		this.primaryStage =primaryStage;
//		primaryStage.setTitle("The Networked Server GUI Example");
//		Parent root = FXMLLoader.load(getClass().getResource("/fxml/introSceneGUI.fxml"));
//		primaryStage.setScene(new Scene(root, 800,600));
//		primaryStage.show();
//
//		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//			@Override
//			public void handle(WindowEvent t) {
//				Platform.exit();
//				System.exit(0);
//			}
//		});
//
////		sceneMap = new HashMap<>();
////
////		sceneMap.put("server", createServerGui());
////		sceneMap.put("client", createClientGui());
////
////		if (serverBtn == null){
////			System.out.println("Server buttn is null");
////		}
////		if (clientBtn == null){
////			System.out.println("CLient btn is null");
////		}
////
////		serverBtn.setOnAction(e->{ primaryStage.setScene(sceneMap.get("server"));
////											primaryStage.setTitle("This is the Server");
////				serverConnection = new Server(data -> {
////					Platform.runLater(()->{
////						listItems.getItems().add(data.toString());
////					});
////
////				});
////
////		});
////
////
////		clientBtn.setOnAction(e-> {primaryStage.setScene(sceneMap.get("client"));
////											primaryStage.setTitle("This is a client");
////											clientConnection = new Client(data->{
////							Platform.runLater(()->{listItems2.getItems().add(data.toString());
////											});
////							});
////
////											clientConnection.start();
////		});
////
////
////		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
////            @Override
////            public void handle(WindowEvent t) {
////                Platform.exit();
////                System.exit(0);
////            }
////        });
//
//
//	}
//
//	public Scene createServerGui() throws IOException {
//
//		Parent serverParent = FXMLLoader.load(getClass().getResource("/fxml/serverGUI.fxml"));
//		return new Scene(serverParent, 400, 300);
//
//
//	}
//
//	public Scene createClientGui() throws IOException {
//		Parent clientParent = FXMLLoader.load(getClass().getResource("/fxml/clientGUI.fxml"));
//		return new Scene(clientParent, 400, 300);
//
//	}
//
//	public void initialize() {
//		System.out.println("initialize called");
//		sceneMap = new HashMap<>();
//
//		try {
//			sceneMap.put("server", createServerGui());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		try {
//			sceneMap.put("client", createClientGui());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		if (serverBtn == null){
//			System.out.println("Server buttn is null");
//		}
//		if (clientBtn == null){
//			System.out.println("CLient btn is null");
//		}
//
//		serverBtn.setOnAction(e->{ primaryStage.setScene(sceneMap.get("server"));
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
//		clientBtn.setOnAction(e-> {primaryStage.setScene(sceneMap.get("client"));
//			primaryStage.setTitle("This is a client");
//			clientConnection = new Client(data->{
//				Platform.runLater(()->{listItems2.getItems().add(data.toString());
//				});
//			});
//
//			clientConnection.start();
//		});
//
//	}
//}

import java.util.HashMap;

import controllers.Client;
import controllers.GUIController;
import controllers.Server;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GuiServer extends Application{

//	TextField s1,s2,s3,s4, c1;
//	Button serverChoice,clientChoice,b1;

//	GridPane grid;
//	HBox buttonBox;
//	VBox clientBox;
//	Scene startScene;
//	BorderPane startPane;
//	ListView<String> listItems, listItems2;
//	Server serverConnection;
//	Client clientConnection;
//	HashMap<String, Scene> sceneMap;
	Stage primaryStage;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		Parent parent = FXMLLoader.load(getClass().getResource("/fxml/introSceneGUI.fxml"));
		Scene introScene = new Scene(parent,600, 600);

		primaryStage.setTitle("The Networked Client/Server GUI Example");
		GUIController controller = new GUIController(primaryStage);
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