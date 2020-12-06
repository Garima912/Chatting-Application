package helpers;

import com.sun.xml.internal.ws.api.message.Packet;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.ClientPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.function.Consumer;



public class Client extends Thread {

	Socket socketClient;
	ObjectOutputStream out;
	ObjectInputStream in;

	private Consumer<Serializable> callback;
	private ClientPacket packet;

	public Client(Consumer<Serializable> call){
		callback = call;
	}

	@Override
	public void run() {

		try {
			socketClient= new Socket("127.0.0.1",5555);
			out = new ObjectOutputStream(socketClient.getOutputStream());
			in = new ObjectInputStream(socketClient.getInputStream());
			socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {}

		while(true) {

			try {
				System.out.println("stuff here");
				ClientPacket clientPacket = (ClientPacket) in.readObject();
				System.out.println("size is "+ clientPacket.getClientIds().size());

				callback.accept(clientPacket);
			}
			catch(Exception e) {
				System.out.println("error");
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public void send(String data) {

		ClientPacket packet = new ClientPacket();
		packet.setMessage(data);
		try {
			System.out.println("object written");
			out.writeObject(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
