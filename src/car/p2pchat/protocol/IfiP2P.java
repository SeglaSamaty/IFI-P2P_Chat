package car.p2pchat.protocol;

import java.net.InetAddress;

public class IfiP2P {
	/*
	 * Cette clase est l'implemenetation d'un pprotocol applicatif de chat
	 */

	public IfiP2P() {
		
	}
	
	public static ActionDescriptor actionSelecter(String data, InetAddress senderIp) {
		ActionDescriptor actionDesc = new ActionDescriptor(); //Contient 
		/*
		 * Les message que gere ce protocole sont de la forme de << Code|Sender|Donnees >>
		 * avec pour taille "code" qui est code sur  4 Caracteres; Sender qui est code sur 17 Caracteres et le reste est poure les donnees text
		 * Le separateur et les codes sont definit dans la classe Ontology
		 */
		
		
		String []messageSubdivise = data.split(Ontology.DATA_SPLIT_CARACTER); //Cette variable contient le message subdivise selon le caractere de separation mais 
		//il peut contenir plus de 3 section si la partie donnee contenait le caractere separateur nous allons donc reconstruire le message dans messageSubdiviseUtil
		//pour n'avoir que 3 sections
		
		String []messageSubdiviseUtile = {messageSubdivise[0], messageSubdivise[1], ""};
		int i = 0;
		//for (String string : messageSubdivise) {
		//	if (i>1) {
		//		messageSubdiviseUtile[2] = messageSubdiviseUtile[2]+string;
		//	}
		//}
		//System.out.println(messageSubdivise[0]);
		//System.out.println(messageSubdivise[1]);
		//System.out.println(messageSubdivise[2]);
		//Debut de la gestion des different type de message
		actionDesc.setSender(messageSubdivise[1]);;
		actionDesc.setData(messageSubdivise[2]);
		actionDesc.setSenderIp(senderIp);
		
		switch (messageSubdiviseUtile[0]) {
		case Ontology.STATUS_TYPE_BUSY:
			//Pour ce type de message la donnee represente le mot de passe et le sender sont username le type d'action est le 0
			actionDesc.setAction(1);	
			break;
		case Ontology.STATUS_TYPE_OUT:
			//Pour ce type de message la donnee represente le mot de passe et le sender sont username le type d'action est le 0
			actionDesc.setAction(2);	
			break;
		case Ontology.CONNECT:
			//Pour ce type de message la donnee represente le mot de passe et le sender sont username le type d'action est le 0
			actionDesc.setAction(7);	
			break;
		case Ontology.CONNECT_ACK:
			//Pour ce type de message la donnee represente le mot de passe et le sender sont username le type d'action est le 0
			actionDesc.setAction(3);	
			break;
		case Ontology.MESSAGE:
			actionDesc.setAction(11);	
			break;
		case Ontology.USER_ACK:
			actionDesc.setAction(12);	
			break;
		case Ontology.USER_DELETE:
			actionDesc.setAction(13);	
			break;
		default:
			break;
		}
		return actionDesc;
		
	}
	
}
