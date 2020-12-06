package helpers;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.ClientPacket;
/*
 * Clicker: A: I really get it    B: No idea what you are talking about
 * C: kind of following
 */

public class Server{

	int count = 1;	
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	public TheServer server;
	private Consumer<Serializable> callback;
//	HashMap<Integer> clientIds =  new HashSet<>();
	ClientPacket packet = new ClientPacket();
	
	public Server(Consumer<Serializable> call){
		callback = call;
		server = new TheServer();
		server.start();
	}
	
	
	public class TheServer extends Thread{
		
		public void run() {
		
			try(ServerSocket mysocket = new ServerSocket(5555);){
		    System.out.println("Server is waiting for a client!");
		  
			
		    while(true) {
		    	// new client just connected. send a packet to the client, containing its position
				ClientThread c = new ClientThread(mysocket.accept(), count);
				clientIds.add(count);
				packet.setClientIds(clientIds);
				packet.getClientIdAndIp().put(count,)
				System.out.println("All clients are:: ");
				for (Integer val: clientIds){
					System.out.println("Value is "+ val);
				}

				callback.accept("client has connected to server: " + "client #" + count);
				clients.add(c);
				c.start();
				System.out.println("Clients: " + clientIds);
				count++;
				
			    }
			}//end of try
				catch(Exception e) {

					callback.accept("Server socket did not launch");
				}
			}//end of while
		}
	

		class ClientThread extends Thread{
			Socket connection;
			int count;
			ObjectInputStream in;
			ObjectOutputStream out;
			
			ClientThread(Socket s, int count){
				this.connection = s;
				this.count = count;	
			}
			
			public void updateClients(String message) {
				for(int i = 0; i < clients.size(); i++) {
					ClientThread t = clients.get(i);
					try {
						packet.setMessage(message);
						packet.setClientIds(clientIds);
					 t.out.writeObject(packet);
					}
					catch(Exception e) {}
				}
			}
			
			public void run(){
					
				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);	
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}

				clientIds.add(count);
				packet.isMessage = true;
				packet.setClientIds(clientIds);
				updateClients("client #"+count);

				 while(true) {
					    try {
					    	String data = in.readObject().toString();
					    	callback.accept("client: " + count + " sent: " + data);
					    	updateClients("client #"+count+" said: "+data);

					    	}
					    catch(Exception e) {
					    	callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
							clientIds.remove(count);
							packet.setClientIds(clientIds);
					    	updateClients("Client #"+count+" has left the server!");
					    	clients.remove(this);
					    	break;
					    }
					}
				}//end of run
			
			
		}//end of client thread
}


	
	

	
