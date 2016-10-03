package launcher.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import launcher.MainApp;
import launcher.view.ProfileController;

public class LoginController {
	private ProfileController profControl;
	@FXML
	private Label loginLabel;
	@FXML
	private Label loginFail;
	@FXML
	private TextField userField;
	@FXML
	private PasswordField passField;
	@FXML
	private Button loginButton;
	@FXML
	private Button requestButton;
	public String userName;
	public String passWord;
	public String userActualName;
	LoginController logControl;
	private MainApp loginSet;
	
	@FXML
	public void setLoginForm() {
		userField.setPromptText("Username");
		passField.setPromptText("Password");
		loginFail.setText("");
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
           	 getCreds();
            }
        });
		requestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	loginSet.showRequest();
            }
        });		
	}
	
	public void setLoginMain(MainApp loginSet) {
		this.loginSet = loginSet; 
		setLoginForm();
	}
	
	
	
	private void getCreds() {
		userName = userField.getText();
		passWord = passField.getText();
		loginFail.setText("Checking Login");
		queryLogins();
	}
	
	public void queryLogins() {
		String userChecking = "'" + userName + "'";
		String passChecking = "'" + passWord + "'";
		try {
            String url = "jdbc:mysql://198.199.84.172:3306/details";
            String loginUser = "splitty";
            String loginPass = "Lukeer22";
            Connection conn = DriverManager.getConnection(url, loginUser, loginPass);
            String query = "SELECT name FROM logins WHERE user = "+ userChecking + " AND pass = " + passChecking;
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs;
            
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                userActualName = rs.getString("name");
                System.out.println(userActualName);
                
                
            }
            if (userActualName != null) { 
            	loginSet.startMain();
            	
            	System.out.println(userActualName + "COMPLETE");
            	
            } else { 
            	loginFail.setText("Login Failed");
            	loginFail.setTextFill(Color.rgb(210, 39, 30));
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }
	}
	
	
}
