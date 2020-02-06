package car.p2pchat.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginGui {
	
	@FXML
	private Label login_label;
	
	@FXML
	private Button login_OK;
	
	@FXML
	private TextField login_username;
	
	@FXML
	private PasswordField login_pwd;
	
	
	public LoginGui() {
	}

	@FXML
    private void initialize() 
    {
		
		//login_label.setText(" ca marcke !!");
		//System.out.println(login_label.getText());
    }
    
	@FXML
    public void printOutput() 
    {
        //login_label.setText(login_label.getText());
       // System.out.println(login_label.getText());
    }

	public Label getLogin_label() {
		return login_label;
	}

	public void setLogin_label(Label login_label) {
		this.login_label = login_label;
	}

	public Button getLogin_OK() {
		return login_OK;
	}

	public void setLogin_OK(Button login_OK) {
		this.login_OK = login_OK;
	}

	public TextField getLogin_username() {
		return login_username;
	}

	public void setLogin_username(TextField login_username) {
		this.login_username = login_username;
	}

	public PasswordField getLogin_pwd() {
		return login_pwd;
	}

	public void setLogin_pwd(PasswordField login_pwd) {
		this.login_pwd = login_pwd;
	}

}
