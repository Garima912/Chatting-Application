package helpers;

import model.ClientPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class Client extends Thread {

	Socket socketClient;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	private Consumer<Serializable> callback;
	private Consumer<Serializable> callback2;
	private ClientPacket packet;

	public Client(Consumer<Serializable> call, Consumer<Serializable> call2){
		callback = call;
		callback2 = call2;
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
				// first time they connect. no IP yet
				if (packet.isNewClient){
					callback.accept(packet.getMessage());
					System.out.println("msg: "+ packet.getMessage());
//					for(int x: packet.getClientIdAndIp()){
//						callback2.accept(x);
//					}

					packet.setIpAddress(socketClient.getRemoteSocketAddress().toString());
					System.out.println("Inside client: Ip address is "+packet.getIpAddress());
					out.writeObject(packet);
				}
				else{

				}
			}
			catch(Exception e) {}
		}
    }
	
	public void send(String data) {
		
		try {
			packet.setMessage(data);
			out.writeObject(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
