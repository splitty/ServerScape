package launcher.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.stage.FileChooser;
import launcher.MainApp;
import launcher.view.LoginController;
public class RootLayoutController {
	
		
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
	
    
    @FXML
    private void handleMain() {
    	mainApp.showPersonOverview();
    }
  
    
    
    
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }

  
    @FXML
    private void handleMod() {
    	mainApp.showModOverview();
    }
    
    
    
    @FXML
    private void exportXML() {
    	FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.savePersonDataToFile(file);
        }
    }
    @FXML
    private void handleLoadHelp() {
    	mainApp.showHowToImportController();
    }

    @FXML
    private void handleCreateHelp() {
    	mainApp.showHowToCreateController();
    }

    
    
    @FXML
    private void handleAbout() {
    	mainApp.showAboutOverview();
    }
    
    @FXML
    private void handleDevSite() {
    	try {
            Desktop.getDesktop().browse(new URI("http://lukelabenski.com"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    private void handleFileAll() {
    	mainApp.showScapeOverview();
    }
    
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
    
    
}