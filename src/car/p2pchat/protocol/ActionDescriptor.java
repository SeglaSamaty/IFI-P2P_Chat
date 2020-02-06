package car.p2pchat.protocol;

import java.net.InetAddress;

public class ActionDescriptor {
	private int action;
	private String sender;
	private String data;
	private InetAddress senderIp;
	
		
	/**
	 * 
	 */
	public ActionDescriptor() {
	}
	
	
	
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	public InetAddress getSenderIp() {
		return senderIp;
	}
	
	public void setSenderIp(InetAddress senderIp) {
		this.senderIp = senderIp;
	}
	
	
}
