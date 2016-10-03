package launcher.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import launcher.MainApp;

public class AboutController {	 	
	    @FXML
	    private TextArea launcherArea;
	    @FXML
	    private TextArea devArea;
	   
	    private String devAreaString = new String("Hmm, where to begin?  Let’s start with Tristan (Swift Instincts).  "
	    		+ "Tristan is an artist to say the least, he specializes in animation with college experience in animation under his belt.  "
	    		+ "Mention digital art forms such as animation, or digital illustration as well as any kind of music "
	    		+ "(seriously, this guy will dig just about anything with a decent beat) and you have his attention.  "
	    		+ "His part of this project was the idea generation, graphic design, and initial testing of standard functions." + "\n\n"
	    		+ "Next up would be the server admin of whom this project is originally intended.  "
	    		+ "Matt (Kronyx) is the most dope Canadian I know (definitely not because he’s the only one I know).  "
	    		+ "Matt has been running game servers for a couple of games and has remote admin experience when it comes to the production of "
	    		+ "game server environments.  His part of this project was also the original concept creation and the initial testing of standard functions." + "\n\n"
	    		+ "Finally I guess there would be me Luke (Mane).  As you could have guessed, I’m Luke and I create applications for desktop and Android.  "
	    		+ "I originally intended to leave the software development field in search of a career in either music production or graphic design.  "
	    		+ "I was drug back into it by a taunt made by Tristan in a conversation about the new server Matt was making.  "
	    		+ "‘…Unless it would be too hard for you.’  Ugh.  I was stuck.  So, this came to be.  In the time I am not developing, I’m either gaming, "
	    		+ "golfing or messing around with designs.  My part in the project was the production of application, setup of parsing news server, "
	    		+ "the design of desktop elements, backend SQL connection for authentication (if by the time you see this there was no login screen, "
	    		+ "then pay no attention as SQL authentication was used for anti-abuse reasons).  So my job was to essentially put into play all of their ideas, "
	    		+ "and implement the app so that they could use it without problem in their sever development.");
	    private String launcherAreaString = new String("This launcher came as an idea from a server admin for an ARMA 3 Exile server.  "
	    		+ "When looking to promote his server, he wanted his own launcher with special information from the server, the mods, "
	    		+ "and be able to connect directly to the server from the launcher.  He mentioned it to me one day, and I took him up on the project.  "
	    		+ "Matt (the server admin whose site and server can be found at http://theweaponshop.ca) and my friend Tristan (a mod on the server at the time) "
	    		+ "were the inspiration and generated the ideas behind this project.  So I must say, without them, I probably wouldn’t have even touched the "
	    		+ "keyboard again with the intention of application creation besides automation and web scraping.  More on the actual launcher now, the launcher "
	    		+ "runs off of the latest JavaFX runtime and can be run on any computer with a compatible Java consumer environment.  No JDK required.");
	    
	    private MainApp mainApp;

	   

	    public void showAboutDetails() {
	    	launcherArea.setText(launcherAreaString);	
	    	devArea.setText(devAreaString);	
	    }
	    
	    @FXML
	    private void initialize() {
	       
	    }

	   
	    public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;

	    }
}
