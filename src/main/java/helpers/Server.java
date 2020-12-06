package helpers;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

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
				packet.getClientIds().add(count);
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
			
			public void updateClients(String message) {

				for(int i = 0; i < clients.size(); i++) {
					ClientThread t = clients.get(i);
						try {
							t.out.reset();
							packet.setMessage(message);
							System.out.println("Update to: "+ packet.getMessage());
							t.out.writeObject(packet);
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
					    	packet = (ClientPacket) in.readObject();
					    	callback.accept("client: " + count + " sent: " + packet.getMessage());
					    	updateClients("client #"+count+" said: "+ packet.getMessage());
					    	}
					    catch(Exception e) {
					    	callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
							packet.getClientIds().remove(count);
							clients.remove(this);
							System.out.println("ONLINE after removing size: " + packet.getClientIds().size());
					    	updateClients("Client #"+count+" has left the server!");
							System.out.println("CLIENTS after removing size: " + clients.size());
					    	break;
					    }
					}
				}//end of run
			
			
		}//end of client thread
}


	
	

	
