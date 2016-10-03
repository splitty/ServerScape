package launcher.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class HowToImportController {
	@FXML
	private TextArea importView;
	
	private String howToImport = new String("Ok, here we go.  This one is quite easy to accomplish compared to making your own server file.  To import a pre-made server"
			+ "file simply navigate to 'Main' in the top left corner.  In that drop down menu select the option 'Load Server'.  You will be prompted with "
			+ "a file browser.  In this, find the .xml server file you wish to import, select it, and click open.  Following that, assuming the file is formatted"
			+ " correctly, the file is loaded.  All you have to do now is click refresh on the 'Home' page.");
	
	@FXML
	private void initialize(){
		
	}
	
	public void startHowToImport(){
		importView.setText(howToImport);
	}
	
}
