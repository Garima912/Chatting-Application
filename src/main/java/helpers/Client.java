package helpers;

import controllers.ClientController;
import javafx.application.Platform;
import model.ClientPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.HashSet;
import java.util.function.Consumer;



public class Client extends Thread {

	Socket socketClient;
	ObjectOutputStream out;
	ObjectInputStream in;
	public int position = -1;
	
	private Consumer<Serializable> callback;
	private Consumer<Serializable> callback2;
	ClientPacket packet;
	ClientController clientController;

	public Client(Consumer<Serializable> call, Consumer<Serializable> call2, ClientController clientController){
		callback = call;
		callback2 = call2;
		this.clientController = clientController;
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
				packet = (ClientPacket) in.readObject();
				if (position < 0)  position = packet.getClientIds().size();

				if (!packet.fromServer){
					System.out.println("not from server, ignoring");
					continue;
				}
				callback.accept(packet.getMessage());
				System.out.println("Online client ::::" + packet.getClientIds());
				refreshLists();

				for(int x: packet.getClientIds()){
					callback2.accept(x);
				}
			}
			catch(Exception e) {}
		}
    }

    // refreshes all the GUI lists after a new packet is received with new clients
	private void refreshLists() {
		Platform.runLater(
				() -> {
					clientController.onlineClientsList.getItems().clear();
					clientController.recipientMenu.getItems().clear();
					clientController.addRecipientToMenu("All");
					clientController.sendTo.clear();
				}
		);
	}

	public void send(String data, HashSet<Integer> sendTo) {
		/* The idea of this function is to send msg to server, then server will decide (based on packet), which clients will recieve it*/
		sendTo.add(position-1);		// always add current client to list of recipients
		packet.recipients = sendTo;
		if(sendTo.contains(-1) ){	//Note -1 is "All"
			packet.setSendToAll(true);
		}
		else{
			packet.setSendToAll(false);
		}

		try {
			packet.setMessage(data);
			packet.fromServer = false;
			out.writeObject(packet);
			out.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
