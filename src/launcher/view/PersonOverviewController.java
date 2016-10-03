package launcher.view;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import launcher.MainApp;
import launcher.model.Person;

public class PersonOverviewController {
	@FXML
	private TextArea announcementView;
    @FXML
    private ImageView pingImage;
    @FXML
    private Label serverNameLabel;
    @FXML
    private Label serverIPLabel;
    @FXML
    private Label pingLabel;
    @FXML
    private Button refreshPing;
    @FXML
    private Button launchArma;
    
    private MainApp mainApp;    
    
    static String siteSrc = "http://lukelabenski.com/parse/newsmessage.html";    
	public String announceString;
    
   
    public PersonOverviewController() {
    }
	
    @FXML
    private void onStartNews(){
    	 Service<Void> serviceNews = new Service<Void>() {
 	        @Override
 	        protected Task<Void> createTask() {
 	            return new Task<Void>() {           
 	                @Override
 	                protected Void call() throws Exception {
 	                    //Background work                       
 	                    final CountDownLatch latch = new CountDownLatch(1);
 	                    Platform.runLater(new Runnable() {                          
 	                        @Override
 	                        public void run() {
 	                        	try{
 	                    	        Document doc = Jsoup.connect(siteSrc).get();
 	                    	        Elements news = doc.select("html");
 	                    	        	String announceFinal;
 	                    	            announceFinal = news.text();
 	                    	            announceString = announceFinal;
 	                    	            System.out.println("FIRST CHECK" + announceString);
 	                    	            grabAnnouncement();
 	                    		} catch (IOException ex) {
 	                				System.err.println("There was an error");
 	                    		
 	                            }finally{
 	                                latch.countDown();
 	                            }
 	                        }
 	                    });
 	                    latch.await();                      
 	                    //Keep with the background work
 	                    return null;
 	                }
 	            };
 	        }
 	    };
 	   serviceNews.start();
    }
    
	@FXML
    private void handleRefreshPing() {
		Service<Void> servicePing = new Service<Void>() {
	        @Override
	        protected Task<Void> createTask() {
	            return new Task<Void>() {           
	                @Override
	                protected Void call() throws Exception {
	                	Thread.sleep(3000);                   
	                    final CountDownLatch latch = new CountDownLatch(1);
	                    Platform.runLater(new Runnable() {                          
	                        @Override
	                        public void run() {
	                        	String ip = MainApp.pingFinal;

	                            String pingCmd = "ping " + "-n 1 "+ ip;
	                            String inputLine = "";
	                            try {
	                            	
	                                Runtime r = Runtime.getRuntime();
	                                Process p = r.exec(pingCmd);

	                                BufferedReader in = new BufferedReader(new
	                                InputStreamReader(p.getInputStream()));
	                                while ((inputLine = in.readLine()) != null) {
	                                    System.out.println(inputLine);
	                                    pingLabel.setText(inputLine);
	                                    resetImage();
	                                }
	                                in.close();

	                            } catch (IOException e) {
	                                System.out.println(e);
	                            }finally{
	                                latch.countDown();
	                            }
	                        }
	                    });
	                    latch.await();                    
	                    return null;
	                }
	            };
	        }
	    };   
	    showPersonDetails();
	    pingingImage();	
	    onStartNews();
	    servicePing.start();
	    
    }
	@FXML
	public void grabAnnouncement(){
    	System.out.println("SECOND CHECK" + announceString);
    	announcementView.setText(announceString);
    }
	
	@FXML
	private void pingingImage(){
		Image img = new Image("resources/pinging.jpg");  
		pingImage.setImage(img);
		System.out.println("I Have Been Run UPDATE PING PIC");
		
    }
	@FXML
	private void resetImage(){
		Image img = new Image("resources/placelogo.jpg");  
		pingImage.setImage(img);
	}
	
	private void runSteamGame(Person person) {
		URI uri;
		try {
			uri = new URI("steam://connect/" + MainApp.ipFinal);
			if (Desktop.isDesktopSupported()) {
			    Desktop.getDesktop().browse(uri);
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
   
    public void showPersonDetails() {
        if (MainApp.serverFinal != null) {
           
        	serverNameLabel.setText(MainApp.serverFinal);
        } else { 
        	serverNameLabel.setText("Please load a server file...");  
        	serverIPLabel.setText("For help select 'Help' from the above menu... ");   
        	pingLabel.setText("And follow the steps for importing an XML server file."); 
        }
        if (MainApp.ipFinal != null) {
            
        	serverIPLabel.setText(MainApp.ipFinal);
        } else { 
        	serverNameLabel.setText("Please load a server file..."); 
        	serverIPLabel.setText("For help select 'Help' from the above menu... "); 
        	pingLabel.setText("And follow the steps for importing an XML server file."); 
        }
        if (MainApp.pingFinal != null) {            
        	pingLabel.setText("Please click 'Refresh' to get ping...");  
        } else { 
        	serverNameLabel.setText("Please load a server file..."); 
        	serverIPLabel.setText("For help select 'Help' from the above menu... ");  
        	pingLabel.setText("And follow the steps for importing an XML server file."); 
        }       
        if (announceString != null) {            
        	announcementView.setText(announceString);
        } else { 
        	announcementView.setText("Uh...  There seems to have been a problem retrieving news from the devs...  Help...");
        }       

    	    
        
    }
    
    @FXML
    private void initialize() {
        onStartNews();
        showPersonDetails();
        refreshPing.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	handleRefreshPing();
            }
        });
        launchArma.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	runSteamGame(null);
            }
        });
       System.out.println("INIT" + MainApp.serverFinal);
       System.out.println("INIT" + MainApp.ipFinal);
       System.out.println("INIT" + MainApp.pingFinal);
    }

    
    
    
    
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp; 
    }
}