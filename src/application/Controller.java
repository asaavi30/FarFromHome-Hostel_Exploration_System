package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class Controller implements Initializable{
	@FXML
    private ComboBox<String> com;
	private String[] types = {"Girls","Boys","International"};

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		com.getItems().addAll(types);
	}

}
