package launcher.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class HowToCreateController {
	@FXML
	private TextArea createView;
	
	private String howToImport = new String("This is a bit more complicated due to the actual programming nature of the server filetype being used."
			+ "  Indeed, if you wish to create your own server, you should either be willing to learn a basic structure of .xml or already have this knowledge.  "
			+ "To start we must understand how the xml works.  It starts with what we will call ‘tags’ for the sake of relatability to HTML.  "
			+ "These tags are used to specify certain parts of the code which you wish to have the launcher read.  "
			+ "For instance, the <serverName> tag will specify the name of the server, in text.  "
			+ "The way that the launcher is set up is for two different extraction points in the file.  "
			+ "The first being the <launcher> tag which is the tag which holds all others.  "
			+ "Directly under this tag is the first set of tags that are going to be read.  "
			+ "These tags will specify things like the server’s name, in game IP WITH PORT, and the IP for pinging WITHOUT PORT.  "
			+ "The <serverIP> tag is the IP with the port, and <serverPing> is the IP without the port.  "
			+ "Continuing down the file there is a tabbed set of tags.  This is the <mods> tag set.  "
			+ "This set contains more tags that specify the mods that players should have installed to play on the server.  "
			+ "It includes the name, the version of the mod that is required and the page at which the player can download the file at.  "
			+ "The finishing touches of the XML is the closing tags, every time there is an opening tag (one without a ‘/’) "
			+ "there needs to be a closing tag of the same name-set.  Make sure the tags are indeed lined up in proper tabbed spots are it will make "
			+ "it much easier to diagnose and solve problems in the future if you run into them.  That’s about it.  "
			+ "For more information on creating a serverfile, look into .xml structuring.");
	
	@FXML
	private void initialize(){
		
	}
	
	public void startHowToCreate(){
		createView.setText(howToImport);
	}
	
}
