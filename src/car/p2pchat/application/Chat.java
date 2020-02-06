package car.p2pchat.application;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import car.p2pchat.controllers.ChatGuiMain;
import car.p2pchat.controllers.Groupe;
import car.p2pchat.controllers.LoginGui;
import car.p2pchat.network.Sender;
import car.p2pchat.protocol.Ontology;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Chat extends Application implements Runnable{

	Stage newStage; // pour stoccker les nouvelles interface graphique
	Sender sender; // Objet qui permet d'envoier les message sur le reseau
	
	// *****
	private LoginGui loginController;
	public static ChatGuiMain chatMainController;
	public static Groupe groupeController;
	private FXMLLoader loader;
	private Parent root;

	@Override
	public void start(Stage primaryStage) {
		try {
			loginController = new LoginGui();
			chatMainController = new ChatGuiMain();
			groupeController = new Groupe();

			sender = new Sender();

			deconnexion(primaryStage);
			
			

		} catch (Exception e) {

		}

	}

	public void lauch_main(Stage primaryStage) {
		if (login_checker() == true) {
			
			// launch the main windows
			loader = new FXMLLoader(getClass().getResource("/car/p2pchat/gui/main.fxml"));

			try {
				root = (Parent) loader.load();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			chatMainController = loader.getController();
			upadteMainPage();

			// Envoie par le boutton envoie
			
			chatMainController.getSend_but().setOnAction(s -> {
				String msg = chatMainController.getType_msg().getText();
				if (msg != "") { // pour eviter d'envoyer des messages vides
					
					if (App.utilisateurCourant != null) { //Pour eviter d'envoyer un msg sur un user null
						//System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
						try {
							sender.send_1_to_1(Ontology.MESSAGE+Ontology.DATA_SPLIT_CARACTER+App.utilisateur.getUsername()+Ontology.DATA_SPLIT_CARACTER+msg, InetAddress.getByName("10.42.0.245"));
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Chat.chatMainController.getMsg_ar().add(">>" + msg);
						chatMainController.getType_msg().setText("");
					}
				}
			});

			/*
			 * chatMainController.getAdd_group().setOnAction(s -> { loader = new
			 * FXMLLoader(getClass().getResource("/car/p2pchat/gui/groupe.fxml"));
			 * 
			 * try { root = (Parent) loader.load(); } catch (IOException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 * 
			 * groupeController = loader.getController(); Scene scene = new Scene(root, 900,
			 * 600); newStage.setScene(scene); newStage.setResizable(false);
			 * newStage.show(); groupeController.getValide().setOnAction(x->{
			 * chatMainController.itemListGroups.add("Groupe1"); newStage.close(); }); });
			 */
			Scene scene = new Scene(root, 900, 600);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		}
	}
	
	
	public void deconnexion(Stage primaryStage) {

		loader = new FXMLLoader(getClass().getResource("/car/p2pchat/gui/login.fxml"));

		try {
			root = (Parent) loader.load();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		loginController = loader.getController();
		Scene scene = new Scene(root, 500, 350);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		loginController.getLogin_pwd().setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					lauch_main(primaryStage);
				}
			}
		});

		
		loginController.getLogin_OK().setOnAction(e -> {
			lauch_main(primaryStage);

		});

	}

	public void upadteMainPage() {
		setUserlist(chatMainController.getItemListUsers());
		for (User user : App.usersList) {
			if (user.getRole() == 0) {
				chatMainController.getUsername().setText(user.getUsername());
			}
		}

		chatMainController.getList_users().setItems(chatMainController.getItemListUsers());
	
		chatMainController.getMsg_area().setItems(chatMainController.getMsg_ar());
		
		chatMainController.getList_users().getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {
					public void changed(ObservableValue<? extends String> observable, String preUsName,
							String activeUsName) {

						int i = 0;
						while (i < App.usersList.size()) {
							if (activeUsName.split(" ")[0].equals(App.usersList.get(i).getUsername())) {
								
								App.utilisateurCourant = App.usersList.get(i);
								chatMainController.getRemote_username().setText(activeUsName.split("")[0]);
								
							}
							i++;
						}
					}
				});

	}

	private void setUserlist(ObservableList<String> itemList) {
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

	private boolean login_checker() {
		/*
		 * verifie si l'utilisateur existe dans la liste de utilisateur et renvoie true
		 * si oui
		 */
		boolean ret = false;
		if (App.usersList.size() != 0) {
			for (User user : App.usersList) {
				if (user.getUsername().equals(loginController.getLogin_username().getText())
						&& user.getPwd().equals(loginController.getLogin_pwd().getText())) {
					loginController.getLogin_label().setText(" ACCES GRANTTED !");
					user.setRole(0);
					App.utilisateur = user;				
					sender.send_1_to_all(Ontology.CONNECT+Ontology.DATA_SPLIT_CARACTER+App.utilisateur.getUsername()+Ontology.DATA_SPLIT_CARACTER);
					System.out.println("Yes i'm in !!");
					ret = true;
					break;
				} else
					loginController.getLogin_label().setText(" ACCES DINIED likely a TYPO !");
			}
		} else {
			loginController.getLogin_label().setText(" REGISTRATION NEEDED !");
		}

		return ret;
	}

	
	@Override 
	public void run() {
		Application.launch(Chat.class);  
	}
	 
	
}
