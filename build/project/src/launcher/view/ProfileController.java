package launcher.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import launcher.MainApp;
import launcher.view.LoginController;
public class ProfileController {
	
	private LoginController logControl;
	private String backUser; 
	private String backStatus;
	private String backRole;
	private String backBuild; 
	private MainApp mainApp;
	@FXML
	private Label profileName;
	@FXML
	private Label userName;
	@FXML
	private Label statusName;
	@FXML
	private Label roleName;
	@FXML
	private Label buildName;
	
	public ProfileController() {
    }
	public void queryProfiles() {
		String userChecking = "'" + logControl.userActualName + "'";
		try {
            String url = "jdbc:mysql://104.156.255.247:3306/details";
            String loginUser = "splitty";
            String loginPass = "Lukeer22";
            Connection conn = DriverManager.getConnection(url, loginUser, loginPass);
            String query = "SELECT * FROM logins WHERE name = "+ userChecking;
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs;
            
            rs = stmt.executeQuery();
            while ( rs.next() ) {
            	backUser = rs.getString("user");
                System.out.println(backUser);
                backStatus = rs.getString("status");
                System.out.println(backStatus);
                backRole = rs.getString("role");
                System.out.println(backRole);
                backBuild = rs.getString("build");
                System.out.println(backBuild);
                
            }
            showProfileDetails();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }
	}
    private void showProfileDetails() {
    	profileName.setText(logControl.userActualName);
    	userName.setText(backUser);
    	statusName.setText(backStatus);
    	buildName.setText(backBuild);
    	roleName.setText(backRole);
    }

	@FXML
    private void initialize() {
       
       
    }

   
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }
}
