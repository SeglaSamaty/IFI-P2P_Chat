package car.p2pchat.application;

import java.net.InetAddress;
import java.util.ArrayList;

public class User {
	private String username;
	private String pwd;
	private InetAddress ipAddress;
	private ArrayList<String> messages;// = new ArrayList<User> ();
	private ArrayList<String> newMessages;// = new ArrayList<User> ();
	private int stat; //0-Online, 1-unavailable, 2-busy, 3-offline
	private int role; //0-Utilisateur courant de l'application 1-Utilisateur distant
	
	/*Debut des Constructeurs des Objets Users */

	/**
	 * @param username
	 * @param ipAddress
	 * @param stat
	 * @param role
	 */
	public User(String username, String pwd, int stat, int role) {
		this.username = username;
		this.pwd = pwd;
		
		this.messages= new ArrayList<String> ();
		this.newMessages = new ArrayList<String>();
		this.messages.add("bla bla bla");
		
		this.stat = stat;
		this.role = role;
	}
	
	public User() {
		this.messages= new ArrayList<String> ();
		this.newMessages = new ArrayList<String>();
	}
	/*Fin des Constructeurs des Objets Users */
	
	/* Debut getters & setters*/
	
	public boolean update() {
		return true;
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public InetAddress getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(InetAddress ipAddress) {
		this.ipAddress = ipAddress;
	}

	public ArrayList<String> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}
	
	public ArrayList<String> getNewMessages() {
		return newMessages;
	}

	public void setNewMessages(ArrayList<String> newMessages) {
		this.newMessages = newMessages;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public boolean newUserSignal() {
		// TODO Auto-generated method stub
		return false;
	}

	/* Fin getters & setters*/

}
