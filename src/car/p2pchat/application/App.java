package car.p2pchat.application;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import car.p2pchat.controllers.ChatGuiMain;
import car.p2pchat.network.Receiver;
import car.p2pchat.protocol.ActionDescriptor;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;


public class App {

	public static ArrayList<User> usersList = new ArrayList<User> ();
	public volatile static User utilisateurCourant;// represente l'utilisateur avec qui on est en conversation
	public volatile static User utilisateur;
	
	
	
	public static void main(String[] args) {
		//usersList.add(new User("seg", "pwd", 3, 0));
		
		InetAddress ip = null;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User u = new User("seg1", "pwd", 0, 1);
		u.setIpAddress(ip);
		usersList.add(u);
		
		User u1 = new User("seg2", "pwd", 3, 1);
		u1.setIpAddress(ip);
		usersList.add(u1);
		
		User u2 = new User("seg", "pwd", 3, 1);
		u2.setIpAddress(ip);
		usersList.add(u2);
		
		
		Thread chatGui = new Thread(new Chat()); //creation du thread qui ecoute le reseau
		chatGui.start();// Lancement du thread qui ecoute le reseau
		
		Receiver netReceiver = new Receiver(); //creation du thread qui ecoute le reseau
		netReceiver.start();// Lancement du thread qui ecoute le reseau
		
		
		
		//Application.launch(Chat.class);
	}
	
	public static void management(ActionDescriptor actionDesc) {
		/*
		 * Cette methode Apporte les modification en fonction des activite des utilisateurs
		 */
		//ChatGuiMain.msg_ar.add("00000");
		//System.out.println(00000000000);
		User u = new User();
		switch (actionDesc.getAction()) {
		case 1:
			//L'Utilisateur signifie sont statut "Occupe", statut 2
			u = getUserByUsername(actionDesc.getSender());
			u.setStat(2);
			//On en profite pour mettre a jour sont address Ip
			u.setIpAddress(actionDesc.getSenderIp());
			setUserlist(ChatGuiMain.itemListUsers);
			break;
		case 2:
			//L'Utilisateur signifie sont statut "Indisponible", statut 1
			u = getUserByUsername(actionDesc.getSender());
			setUserlist(ChatGuiMain.itemListUsers);
			if (u != null) {
				u.setStat(1);
			//On en profite pour mettre a jour sont address Ip
				u.setIpAddress(actionDesc.getSenderIp());
			}
			break;
		case 3:
			//L'Utilisateur signifie est conecte, statut 0
			u = getUserByUsername(actionDesc.getSender());
			setUserlist(ChatGuiMain.itemListUsers);
			if (u != null) {
				u.setStat(0);
				//On en profite pour mettre a jour sont address Ip
				u.setIpAddress(actionDesc.getSenderIp());
			}
			break;
		case 11:
			//message
			u = getUserByUsername(actionDesc.getSender());
			if (u!=null) {
				u.getNewMessages().add(actionDesc.getData());
				u.getMessages().add(actionDesc.getData());
				setUserlist(ChatGuiMain.itemListUsers);
				if (u.getUsername().equals(utilisateur.getUsername())) {
					Platform.runLater(new Runnable(){
						
						@Override
						public void run() {
							ChatGuiMain.msg_ar.add("           			                          <<"+actionDesc.getData());
						}
						
						});		
				}else {
					u.getNewMessages().add(actionDesc.getData());// On garde le message pour l'afficher plu tard
				}
						
			}
			
			break;
		case 12:
			//user ack
			u = getUserByUsername(actionDesc.getSender());			
			if (u == null) {
				u = new User();
				u.setUsername(actionDesc.getSender());
				u.setPwd(actionDesc.getData());
				u.setIpAddress(actionDesc.getSenderIp());
				u.setRole(1);
				u.setStat(3);
				u.setMessages(new ArrayList <String> ());
				setUserlist(ChatGuiMain.itemListUsers);
				usersList.add(u);
			}
			break;
		case 13:
			//user del
			u = getUserByUsername(actionDesc.getSender());
			usersList.remove(usersList.indexOf(u));
			break;
		case 7:
			//L'Utilisateur S'est connecte sur un ordinateur distant sont statut va changer en Connecte stat 0
			u = getUserByUsername(actionDesc.getSender());
			u.setStat(0);
			//On  met a jour sont address Ip car il peut avoir change de machine
			setUserlist(ChatGuiMain.itemListUsers);
			u.setIpAddress(actionDesc.getSenderIp());
	
			break;

		default:
			break;
		}
	}
	
	public static boolean isConnected(User u ) {
		boolean ret = false;
		for (User user : usersList) {
			if(exist(u) && u.getStat() == 0) ret = true;
		}
		return ret;
	}
	
	public static boolean exist(User u) {
		boolean ret = false;
		for (User user : usersList) {
			if(user.getUsername().equals(u.getUsername())) ret = true;
		}
		return ret;
	}
	
	public static User getUserByUsername(String username) {
		User u = new User();
		for (User user : usersList) {
			if (user.getUsername().equals(username)) {
				u = user;
			} else u = null;
		}
		return u;
	}
	
	public static void setUserlist(ObservableList<String> itemList) {
		int i = 0;
		for (User user : App.usersList) {
			if (user.getRole() != 0) {
				
				//0-Online, 1-unavailable, 2-busy, 3-offline
				if (user.getStat() == 0) {
					itemList.add(user.getUsername()+"  [CONN]");
				}
				
				if (user.getStat() == 1) {
					itemList.add(user.getUsername()+"  [OCCUP]");
				}
				
				if (user.getStat() == 3) {
					itemList.add(user.getUsername()+"  [DECO]");
				}
			}
			i=i+1;
		}
	}
}
