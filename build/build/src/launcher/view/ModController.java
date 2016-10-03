package launcher.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import launcher.MainApp;
import launcher.model.Mods;

public class ModController {
	 @FXML
	    private TableView<Mods> modTable;
	    @FXML
	    private TableColumn<Mods, String> modNameColumn;

	    @FXML
	    private Label modNameLabel;
	    @FXML
	    private Label versionNumberLabel;
	    @FXML
	    private Label webPageLabel;

	    // Reference to the main application.
	    private MainApp mainApp;

	    /**
	     * The constructor.
	     * The constructor is called before the initialize() method.
	     */
	    public ModController() {
	    }

	    private void showModDetails(Mods mods) {
	        if (mods != null) {
	        	
	            modNameLabel.setText(mods.getmodName());
	            versionNumberLabel.setText(mods.getversionNumber());
	            webPageLabel.setText(mods.getwebPage());

	        } else {
	            modNameLabel.setText("");
	            versionNumberLabel.setText("");
	            webPageLabel.setText("");
	        }
	    }
	    
	    @FXML
	    private void initialize() {
	        // Initialize the person table with the two columns.
	        modNameColumn.setCellValueFactory(cellData -> cellData.getValue().modNameProperty());
	        showModDetails(null);
	        modTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showModDetails(newValue));
	    }

	   
	    public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;

	        modTable.setItems(mainApp.getModData());
	    }
}
