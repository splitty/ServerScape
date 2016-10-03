package launcher;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import launcher.model.Mods;
import launcher.model.PersonListWrapper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import launcher.view.AboutController;
import launcher.view.HowToCreateController;
import launcher.view.HowToImportController;
import launcher.view.LoginController;
import launcher.view.ModController;
import launcher.view.PersonOverviewController;
import launcher.view.ProfileController;
import launcher.view.RootLayoutController;
import launcher.view.ScapeController;

public class MainApp extends Application {
    public Stage primaryStage;
    private BorderPane rootLayout = new BorderPane();
    public static String serverFinal; 
    public static String ipFinal;
    public static String pingFinal;
    public static String modFinal;
    public static String versionFinal;
    public static String webpageFinal;
    public Stage loginStage;
	private BorderPane rootLoginLayout;
	
		
    private ObservableList<Mods> modData = FXCollections.observableArrayList();
    
    
    public MainApp() {
    	modData.add(new Mods("Load a Server File to See Mods", "", ""));
    }  
    

    public ObservableList<Mods> getModData() {
        return modData;
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MainStage");
        this.primaryStage.getIcons().add(new Image("resources/placeico.jpg")); 
        
        initRootLayout();
        showPersonOverview();
    }
    
    
    public void initLoginRoot() {
        try {
        	
            

            Scene scene = new Scene(rootLayout, 400, 250);
            loginStage.setScene(scene);


           
            loginStage.show();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public void showLoginOverview(){
		try{
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/LoginView.fxml"));
	        AnchorPane loginOverview = (AnchorPane) loader.load();
	        
	        rootLayout.setCenter(loginOverview);
	        
	        LoginController controller = loader.getController();
	        controller.setLoginMain(this);
		}catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	

	
	 public Stage getLoginStage() {
	        return loginStage;
	    }
	 
	 public void startMain() {
		
			
		this.primaryStage = new Stage();
	    this.primaryStage.setTitle("The Multi-Game Launcher v0.0.0.1");
	    this.primaryStage.getIcons().add(new Image("resources/placeico.jpg"));
	    initRootLayout();
	    showPersonOverview();
        closeLogin();
        
        
	    }
	 public void initRootLayout(){
			try{
				FXMLLoader loaderBorder = new FXMLLoader();
	            loaderBorder.setLocation(MainApp.class
	                    .getResource("view/RootLayout.fxml"));
	            rootLayout = (BorderPane) loaderBorder.load();
		        System.out.println("TEST");
		        Scene sceneRoot = new Scene(rootLayout, 900, 550);
		        primaryStage.setScene(sceneRoot);
		        
		        RootLayoutController controllerRoot = loaderBorder.getController();
		        controllerRoot.setMainApp(this);
		        
		        primaryStage.show();
			
			}catch (Exception e) {
	            e.printStackTrace();
	        }
			File file = getPersonFilePath();
	        if (file != null) {
	            loadPersonDataFromFile(file);
	        }
		}
	 
	 public void showPersonOverview() {
		 try {
		 	
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
	        AnchorPane personOverview = (AnchorPane) loader.load();

	        rootLayout.setCenter(personOverview);

	        PersonOverviewController controller = loader.getController();
	        controller.setMainApp(this);
	 }catch (IOException e) {
         e.printStackTrace();
     }
	 }
   private void closeLogin() {
	   System.out.println("TEST CLOSE");
	   loginStage.close();
   }

   
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        launch(args);       

    }
    
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("Launcher - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("Launcher");
        }
    }
    
    public void loadPersonDataFromFile(File file) {
        try {
        	JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);          	
        	Unmarshaller um = context.createUnmarshaller();
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);
            modData.clear();
            modData.addAll(wrapper.getMods());

            ipFinal = wrapper.getIPXML();
            serverFinal = wrapper.getServerXML();
            pingFinal = wrapper.getPingXML();
            System.out.println(wrapper);
            System.out.println(ipFinal);
            System.out.println(pingFinal);
            System.out.println(serverFinal);
          
            setPersonFilePath(file);
            
            
        } catch (Exception e) { 
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            e.printStackTrace();
            alert.showAndWait();
        }
    }  
    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            PersonListWrapper wrapper = new PersonListWrapper();
            
            wrapper.setIPXML(ipFinal);
            wrapper.setServerXML(serverFinal);
            wrapper.setPingXML(pingFinal);
            wrapper.setMods(modData);
            m.marshal(wrapper, file);

            setPersonFilePath(file);
        } catch (Exception e) { 
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }   
    
    
    public void showModOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ModView.fxml"));
            AnchorPane modView = (AnchorPane) loader.load();

            rootLayout.setCenter(modView);

            ModController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void profileOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProfileView.fxml"));
            AnchorPane profView = (AnchorPane) loader.load();

            rootLayout.setCenter(profView);

            ProfileController controller = loader.getController();
            controller.setMainApp(this);
            controller.queryProfiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void showAboutOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AboutView.fxml"));
            AnchorPane aboutView = (AnchorPane) loader.load();

            rootLayout.setCenter(aboutView);

            AboutController controller = loader.getController();
            controller.setMainApp(this);
            controller.showAboutDetails();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showScapeOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ScapeView.fxml"));
            AnchorPane scapeView = (AnchorPane) loader.load();

            rootLayout.setCenter(scapeView);

            ScapeController controller = loader.getController();
            controller.setMainApp(this);
            controller.showScapeDetails();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showHowToImportController() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/HowToImport.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("How To- Load Server");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.getIcons().add(new Image("resources/placeico.jpg"));
            HowToImportController controller = loader.getController();
            controller.startHowToImport();

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showRequest() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AskForLogin.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ask for Logins");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.getIcons().add(new Image("resources/placeico.jpg"));

            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showHowToCreateController() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/HowToCreateXML.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage createStage = new Stage();
            createStage.setTitle("How To- Load Server");
            createStage.initModality(Modality.WINDOW_MODAL);
            createStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            createStage.setScene(scene);
            createStage.getIcons().add(new Image("resources/placeico.jpg"));
            HowToCreateController controller = loader.getController();
            controller.startHowToCreate();

            createStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}