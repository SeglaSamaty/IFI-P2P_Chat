package car.p2pchat.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Groupe {
	
	
	@FXML
	private Button valide;
	
	
	public Button getValide() {
		return valide;
	}

	public void setValide(Button valide) {
		this.valide = valide;
	}

	public Groupe() {
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

	
}
