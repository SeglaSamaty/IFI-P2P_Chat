package car.p2pchat.controllers;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ChatGuiMain {
	
	@FXML
	private  MenuItem add_group;
	
	@FXML
	private  RadioButton status_unavailable;
	
	@FXML
	private  RadioButton status_busy;
	
	@FXML
	private  MenuBar menu;
	
	@FXML
	private  Menu menu_app;
	
	@FXML
	private  MenuItem menu_app_quit;
	
	@FXML
	private  MenuItem menu_app_decon;
	
	@FXML
	private  Menu menu_edit;
	
	@FXML
	private  MenuItem menu_edit_del;
	
	@FXML
	private  Menu menu_others;
	
	@FXML
	private  MenuItem menu_others_help;
	
	@FXML
	private  MenuItem menu_others_apro;
	
	@FXML 
	private ListView <String> list_groups;
	
	@FXML 
	private ListView <String> list_users;
	 
	@FXML
	private ListView<String> msg_area;
	
	@FXML
	private TextField type_msg;
	
	@FXML
	private Button send_but;
	
	@FXML
	private Button send_file_but;
	
	@FXML
	private Label remote_username;
		
	@FXML
	private Label username;
	
	@FXML
	private Label details;
	
	
	public static volatile ObservableList<String> itemListUsers;
	
	public static volatile ObservableList<String> itemListGroups;
	
	public static volatile ObservableList<String> msg_ar; //Declaration volatile permet de pouvoir modifier la variable de par tous les thread sans qu'ils ne garde des valeurs dans leur caches 
	
	
	public ChatGuiMain() {
		itemListUsers = FXCollections.observableArrayList();
		msg_ar = FXCollections.observableArrayList();
		itemListGroups = FXCollections.observableArrayList();
	}

	
	
	public ObservableList<String> getMsg_ar() {
		return msg_ar;
	}

	public void setMsg_ar(ObservableList<String> msg_ar) {
		this.msg_ar = msg_ar;
	}

	public RadioButton getStatus_unavailable() {
		return status_unavailable;
	}

	public void setStatus_unavailable(RadioButton status_unavailable) {
		this.status_unavailable = status_unavailable;
	}

	public RadioButton getStatus_busy() {
		return status_busy;
	}

	public void setStatus_busy(RadioButton status_busy) {
		this.status_busy = status_busy;
	}

	public MenuBar getMenu() {
		return menu;
	}

	public void setMenu(MenuBar menu) {
		this.menu = menu;
	}

	
	public MenuItem getAdd_group() {
		return add_group;
	}



	public void setAdd_group(MenuItem add_group) {
		this.add_group = add_group;
	}



	public ListView<String> getList_groups() {
		return list_groups;
	}



	public void setList_groups(ListView<String> list_groups) {
		this.list_groups = list_groups;
	}



	public Menu getMenu_app() {
		return menu_app;
	}

	public void setMenu_app(Menu menu_app) {
		this.menu_app = menu_app;
	}

	public MenuItem getMenu_app_quit() {
		return menu_app_quit;
	}

	public void setMenu_app_quit(MenuItem menu_app_quit) {
		this.menu_app_quit = menu_app_quit;
	}

	public Menu getMenu_edit() {
		return menu_edit;
	}

	public void setMenu_edit(Menu menu_edit) {
		this.menu_edit = menu_edit;
	}

	public MenuItem getMenu_edit_del() {
		return menu_edit_del;
	}

	public void setMenu_edit_del(MenuItem menu_edit_del) {
		this.menu_edit_del = menu_edit_del;
	}

	public Menu getMenu_others() {
		return menu_others;
	}

	public void setMenu_others(Menu menu_others) {
		this.menu_others = menu_others;
	}

	public MenuItem getMenu_others_help() {
		return menu_others_help;
	}

	public void setMenu_others_help(MenuItem menu_others_help) {
		this.menu_others_help = menu_others_help;
	}

	public ListView<String> getList_users() {
		return list_users;
	}

	public void setList_users(ListView<String> list_users) {
		this.list_users = list_users;
	}

	public ListView<String> getMsg_area() {
		return msg_area;
	}

	public void setMsg_area(ListView<String> msg_area) {
		this.msg_area = msg_area;
	}

	public TextField getType_msg() {
		return type_msg;
	}

	public void setType_msg(TextField type_msg) {
		this.type_msg = type_msg;
	}

	public Button getSend_but() {
		return send_but;
	}

	public void setSend_but(Button send_but) {
		this.send_but = send_but;
	}

	public Label getRemote_username() {
		return remote_username;
	}

	public void setRemote_username(Label remote_username) {
		this.remote_username = remote_username;
	}

	public Label getUsername() {
		return username;
	}

	public void setUsername(Label username) {
		this.username = username;
	}

	public Label getDetails() {
		return details;
	}

	public void setDetails(Label list_details) {
		this.details = list_details;
	}

	public ObservableList<String> getItemListUsers() {
		return itemListUsers;
	}

	public void setItemListUsers(ObservableList<String> itemListUsers) {
		this.itemListUsers = itemListUsers;
	}
	
}
