package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SceneController {
	private Stage stage;
	private Scene scene;
	private Parent root;

	public void switchToHomePage(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
		public void switchToAdminLogIn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AdminLogIn.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
		public void switchToAfterFindHome(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AfterFindHome.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
		public void switchToOHDetails(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("OHdetails.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			}
		
		//For Admin login
		Connection con;
		PreparedStatement pst;
		@FXML
		private PasswordField aptext;
		@FXML
		private TextField autext;
		public void AdmiLogin(ActionEvent event) throws IOException {
			
			String au = autext.getText();
			String ap =  aptext.getText();
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farfromhome","root","Hero@MYSQL8021");
				Statement stm = con.createStatement();
				String sql = "select * from admin where username='"+au+"' and password='"+ap.toString()+"'";
				ResultSet rs = stm.executeQuery(sql);
				
				 if(rs.next())
				{
				
					 JOptionPane.showMessageDialog(null,"YOU'VE SUCCESSFULLY LOGGED IN!");
					
					 FXMLLoader loader = new FXMLLoader(getClass().getResource("AdmiModified.fxml"));	
						root = loader.load();	
						stage = (Stage)((Node)event.getSource()).getScene().getWindow();
						scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
			}
				else
				{
					JOptionPane.showMessageDialog(null,"LOGIN FAILED!");
				}
				}
				catch (ClassNotFoundException e)
				{
				e.printStackTrace();
				}
				catch (SQLException e)
				{
				e.printStackTrace();
				}

		}
		//For OHDetails
		@FXML
		private ComboBox<String> com;
		private String[] types = {"Girls Hostel","Boys Hostel","International Students' Hostel"};
		/*
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			com.getItems().addAll(types);
		}
*/
		@FXML
	    private TextField haddress;

	    @FXML
	    private TextField hcapacity;

	    @FXML
	    private TextField hcontact;

	    @FXML
	    private TextField hemail;

	    @FXML
	    private TextField hfees;

	    @FXML
	    private TextField hlocation;

	    @FXML
	    private TextField hname;

	    @FXML
	    private Button hsubmit;

	    @FXML
	    private TextField oaddress;

	    @FXML
	    private TextField ocontact;

	    @FXML
	    private TextField oemail;

	    @FXML
	    private TextField oname;

	    @FXML
	    private Button osubmit;

	    @FXML
	    void hostelsubmit(ActionEvent event) throws IOException {
	    	String hostelname = hname.getText();
			String hosteladdress =  haddress.getText();
			String hostelemail = hemail.getText();
			String hostellocation =  hlocation.getText();
			String hostelcapacity = hcapacity.getText();
			String hostelfees = hfees.getText();
			String hostelcontact = hcontact.getText();
			
			if(hostelname.equals("") || hostelcontact.equals("") || hosteladdress.equals("") || hostellocation.equals("") || hostelfees.equals("") || hostelcapacity.equals(""))
			{
			JOptionPane.showMessageDialog(null,"ALL DETAILS ARE REQUIRED!");
			}
			else {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farfromhome","root","Hero@MYSQL8021");
				pst = con.prepareStatement("insert into hostel_details(name,address,email,location,capacity,fees,contact)values(?,?,?,?,?,?,?)");
				pst.setString(1, hostelname);
				pst.setString(2, hosteladdress);
				pst.setString(3, hostelemail);
				pst.setString(4, hostellocation);
				pst.setString(5, hostelcapacity);
				pst.setString(6, hostelfees);
				pst.setString(7, hostelcontact);
				int status = pst.executeUpdate();
				if(status==1)
				{
				JOptionPane.showMessageDialog(null,"HOSTEL DETAILS HAVE BEEN SAVED");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));	
				root = loader.load();	
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
				}
				else
				{
				JOptionPane.showMessageDialog(null,"FAILED TO SAVE!");
				}
				}
				catch (ClassNotFoundException e)
				{
				e.printStackTrace();
				}
				catch (SQLException e)
				{
				e.printStackTrace();
				}
			}

	    }
	    

	    @FXML
	    void ownersubmit(ActionEvent event) {
	    	com.getItems().addAll(types);
	    	String ownername = oname.getText();
			String ownercontact = ocontact.getText();
			String owneraddress =  oaddress.getText();
			String owneremail = oemail.getText();
			
			if(ownername.equals("") || ownercontact.equals("") || owneraddress.equals("") || owneremail.equals(""))
			{
			JOptionPane.showMessageDialog(null,"ALL DETAILS ARE REQUIRED!");
			}
			else {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farfromhome","root","Hero@MYSQL8021");
				pst = con.prepareStatement("insert into owner_details(name,contact,address,email)values(?,?,?,?)");
				pst.setString(1, ownername);
				pst.setString(2, ownercontact);
				pst.setString(3, owneraddress);
				pst.setString(4, owneremail);
				int status = pst.executeUpdate();
				if(status==1)
				{
				JOptionPane.showMessageDialog(null,"YOUR DETAILS HAVE BEEN SAVED");
				}
				else
				{
				JOptionPane.showMessageDialog(null,"FAILED TO SAVE PLEASE TRY AGAIN!");
				oname.setText("");
				ocontact.setText("");
				oaddress.setText("");
				oemail.setText("");
				oname.requestFocus();
				}
				}
				catch (ClassNotFoundException e)
				{
				e.printStackTrace();
				}
				catch (SQLException e)
				{
				e.printStackTrace();
				}
			}

	    }
		
}
