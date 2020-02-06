package car.p2pchat.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import car.p2pchat.controllers.ChatGuiMain;

public class Sender{
	
	public String error_massage;   // Contient des message d'erreur qui pourrait subvenir
	private InetAddress ipAddress; // l'Add IP de cette machine
	
	
	private  DatagramSocket ds;    // Le seul socket utilise pour l'envoie et la reception des msgs
	
	static final int PORT_UDP_RE = 7770;// Le port UDP que nous utilisons pour cette application de chat
	static final int PORT_UDP_SE = 7771;// Le port UDP que nous utilisons pour cette application de chat
	
	public Sender() {
		/*
		 * Ce constructeur cree le socket et recupere l'IP add de cette machine
		 * 
		 * */
		try {
			this.ipAddress = InetAddress.getLocalHost();
			this.ds = new DatagramSocket(PORT_UDP_SE, this.ipAddress);
			
		} catch (Exception e) {
			error_massage = "Probleme sur la configuration reseau ";
			System.out.println(error_massage);
		}
		
	}

	
	public void send_1_to_1(String data, InetAddress remoteIpAddress)
	/*
	 * Methode pour envoyer un msg (paquet) a une autre machine qui utilise le meme port sur UDP
	 * c-a-d un et un seul autre utilisateur de l'application
	 * 
	 *  */
	{
		
		byte buf[] = null;
		buf = data.getBytes();
		DatagramPacket DpSend = new DatagramPacket(buf, buf.length, remoteIpAddress, PORT_UDP_RE);
		
		try {
			this.ds.send(DpSend);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.error_massage = "Le massage n'a pas pu etre envoyer ";
		}
		
	}
	
	
	public void send_1_to_all(String data)
	/*
	 *  Methode pour envoyer un msg (paquet) a toute les machines qui utilisent le meme port sur UDP
	 * 
	 *  */
	{
		InetAddress bdcast = null;
		//ChatGuiMain.msg_ar.add("00000");
		try {
			bdcast = InetAddress.getByName("255.255.255.255");
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			
			e1.printStackTrace();
		}
		byte buf[] = null;
		buf = data.getBytes();
		DatagramPacket DpSend = new DatagramPacket(buf, buf.length, bdcast , PORT_UDP_RE);
		
		try {
			this.ds.send(DpSend);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.error_massage = "Le massage n'a pas pu etre envoyer ";
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
