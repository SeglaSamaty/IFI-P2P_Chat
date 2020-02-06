package car.p2pchat.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import car.p2pchat.application.App;
import car.p2pchat.application.Chat;
import car.p2pchat.controllers.ChatGuiMain;
import car.p2pchat.protocol.ActionDescriptor;
import car.p2pchat.protocol.IfiP2P;
import car.p2pchat.protocol.Ontology;
import javafx.scene.text.Text;

public class Receiver{
	
	public String error_massage;   // Contient des message d'erreur qui pourrait subvenir
	//private InetAddress ipAddress; // l'Add IP de cette machine
	
	
	private  DatagramSocket ds;    // Le seul socket utilise pour l'envoie et la reception des msgs
	
	static final int PORT_UDP_RE = 7770;// Le port UDP que nous utilisons pour cette application de chat
	static final int PORT_UDP_SE = 7771;// Le port UDP que nous utilisons pour cette application de chat

	String data;
	DatagramPacket DpReceive;
	byte[] receive;
	
	
	
	public Receiver() {
		/*
		 * Ce constructeur cree le socket et recupere l'IP add de cette machine
		 * 
		 * */
		receive = new byte[65535];
		//DpReceive = null;
		//data = "";
		try {
			//this.ipAddress = InetAddress.getLocalHost();
			this.ds = new DatagramSocket(PORT_UDP_RE);
			
		} catch (Exception e) {
			error_massage = "Probleme sur la configuration reseau ";
			System.out.println(error_massage);
		}
		
	}
	 //@Override
	   // public void run() 
	public void start()
	/*
	 * Methode qui demarre l'ecoute du reseau, en attente de message (de paquets)
	 * C'est comme la partie serveur de cette application
	 * Un nouveau message est stocker dans le data qui lui est passe en parametre
	 * 
	 * */
	{
		
		
		
		System.out.println("Network listenner is up and running ... ");
		// Step 2 : create a DatgramPacket to receive the data. 
		DpReceive = new DatagramPacket(receive, receive.length); 
		while (true) 
		{ 	
			// Step 3 : revieve the data in byte buffer. 
			try {//System.out.println(DpSend.getData().length);
					System.out.println("data Rec0 : "+DpReceive.getData().length);
					ds.receive(DpReceive);
			} catch (IOException e) {
					// TODO Auto-generated catch block
				this.error_massage = "Erreur sur le reseau ";
			} 
			
			data = "" + data(receive);
			
			
			if (data.split(Ontology.DATA_SPLIT_CARACTER).length > 2) {
				ActionDescriptor actionDesc = IfiP2P.actionSelecter(data, DpReceive.getAddress());
				//A partir de ce moment on va appeller la methode App.management pour Executer l'action
				App.management(actionDesc);
				System.out.println("data Rec : "+actionDesc.getData());
			}
			
			//receive = new byte[65535]; //Reinitialisation du buffer
		} 
			
	} 
	
	public static StringBuilder data(byte[] a)
	/* 
	* Mothode de conversion des message recu en tableau de Byte
	* 
	* */
	{ 
		if (a == null) 
			return null; 
		StringBuilder ret = new StringBuilder(); 
		int i = 0; 
		while (a[i] != 0)
			{ 
				ret.append((char) a[i]);
				i++; 
			} 
		return ret; 
	}
	
	
}
