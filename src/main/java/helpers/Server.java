package helpers;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Consumer;

import model.ClientPacket;

public class Server{

	int count = 1;	
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	public TheServer server;
	private Consumer<Serializable> callback;
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
		
				ClientThread c = new ClientThread(mysocket.accept(), count);
				packet.getClientIds().add(count);  // add the new client in the online clients list
				callback.accept("client has connected to server: " + "client #" + count);
				clients.add(c);
				c.start();
				System.out.println("Clients: " + clients.size());
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
			
			public synchronized void updateClients(String message) {
				for(int i = 0; i < clients.size(); i++) {
					ClientThread t = clients.get(i);
						try {
							t.out.reset();
							packet.setMessage(message);
							packet.fromServer = true;
							System.out.println("Update to: "+ packet.getMessage());
							t.out.writeObject(packet);
						} catch (Exception e) {
						}
				}
			}

			// this updates the chats list only
			public synchronized void updateClientChats(int clientNumber, ClientPacket packet){
				packet.setMessage("Client #"+clientNumber+" said: "+packet.getMessage());
				System.out.print("Clients is ");
				for (Integer x: packet.recipients){
					System.out.print(x+" ");
				}
				System.out.println("");

				for(int i = 0; i < clients.size(); i++) {
					ClientThread t = clients.get(i);
					try {
						t.out.reset();
						System.out.println("Update to: "+ packet.getMessage());
						// if 'i' is in the packet or 'All' is chosen
						if (packet.recipients.contains(i) || packet.getSendToAll()){
							System.out.println("Got here");
							packet.fromServer = true;
							t.out.writeObject(packet);
						}
					} catch (Exception e) {
					}
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
				updateClients("new client on server: client #"+ count);

				 while(true) {

					    try {
					    	packet = (ClientPacket) in.readObject();		// packet from client. To be decided which other client to send to
					    	callback.accept("client: " + count + " sent: " + packet.getMessage());
					    	updateClientChats(count, packet);
					    	}
					    catch(Exception e) {
					    	callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
							packet.getClientIds().remove(count);  // remove the client from online clients list
							clients.remove(this);
					    	updateClients("Client #"+count+" has left the server!");
					    	break;
					    }
					}
				}//end of run
			
			
		}//end of client thread
}


	
	

	
