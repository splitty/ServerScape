package launcher.view;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import launcher.MainApp;

public class ScapeController {
	@FXML
    private ImageView chanCheck;
	@FXML
    private ImageView allCheck;
	@FXML
    private ImageView imageCheck;
	@FXML
    private ImageView chanImage;
	@FXML
    private ImageView allImage;
	@FXML
    private ImageView imageImage;
	@FXML
	private TextArea imageArea;
	@FXML
	private TextArea chanArea;
	@FXML
	private TextArea allArea;
	@FXML
	private TextField scapeAll;
	@FXML
	private TextField scapeImage;
	@FXML
	private TextField scapeChan;
	@FXML
	private Button imageGrab;
	@FXML
	private Button chanGrab;
	@FXML
	private Button allGrab;
	@FXML
	private Button folderSelectAll;
	@FXML
	private Button folderSelectChan;
	@FXML
	private Button folderSelectImage;
	
	static String srcChan;  
	static String srcImage; 
	static String srcAll; 
	static String srcThread;
	static String srcComplete;
	static String srcRName;
	static String srcSName;
	public static String folderPath;
	
    private MainApp mainApp;

   
    private void selectFoldersAll(){
    	DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = 
                directoryChooser.showDialog(mainApp.primaryStage);
        
        if(selectedDirectory == null){        	
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Directory Selected");
            alert.setContentText("Please Choose a Valid Directory");
            alert.showAndWait();
            Image img = new Image("resources/error.png"); 
            allCheck.setImage(img);
        }else{
        	folderPath = selectedDirectory.getAbsolutePath();
        	Alert alertinfo = new Alert(AlertType.INFORMATION);
            alertinfo.setTitle("Success!");
            alertinfo.setHeaderText("Good Choice!");
            alertinfo.setContentText("Directory Valid");
            alertinfo.showAndWait(); 
            Image img = new Image("resources/check.png"); 
            allCheck.setImage(img);
        }
    }
    private void selectFoldersChan(){
    	DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = 
                directoryChooser.showDialog(mainApp.primaryStage);
        
        if(selectedDirectory == null){        	
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Directory Selected");
            alert.setContentText("Please Choose a Valid Directory");
            alert.showAndWait();
            Image img = new Image("resources/error.png"); 
            chanCheck.setImage(img);
        }else{
        	folderPath = selectedDirectory.getAbsolutePath();
        	Alert alertinfo = new Alert(AlertType.INFORMATION);
            alertinfo.setTitle("Success!");
            alertinfo.setHeaderText("Good Choice!");
            alertinfo.setContentText("Directory Valid");
            alertinfo.showAndWait(); 
            Image img = new Image("resources/check.png"); 
            chanCheck.setImage(img);
        }
    }
    private void selectFoldersImage(){
    	DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = 
                directoryChooser.showDialog(mainApp.primaryStage);
        
        if(selectedDirectory == null){        	
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Directory Selected");
            alert.setContentText("Please Choose a Valid Directory");
            alert.showAndWait();
            Image img = new Image("resources/error.png"); 
            imageCheck.setImage(img);
        }else{
        	folderPath = selectedDirectory.getAbsolutePath();
        	Alert alertinfo = new Alert(AlertType.INFORMATION);
            alertinfo.setTitle("Success!");
            alertinfo.setHeaderText("Good Choice!");
            alertinfo.setContentText("Directory Valid");
            alertinfo.showAndWait(); 
            Image img = new Image("resources/check.png"); 
            imageCheck.setImage(img);
        }
    }
    
    public void chanGrabTransfer(){
    	
    	parseChan();
    }
    
    public void allGrabTransfer(){ 
    	
    	parseAll();
    }

	public void imageGrabTransfer(){
		
		parseImage();
	}
    
    public void showScapeDetails() {
    	Image imageLoad = new Image("resources/idlescape.jpg");
  	    imageImage.setImage(imageLoad);
  	    Image allLoad = new Image("resources/idlescape.jpg");
		allImage.setImage(allLoad);
		Image chanLoad = new Image("resources/idlescape.jpg");
		chanImage.setImage(chanLoad);
		
    	scapeChan.setPromptText("http://boards.4chan.org/example/");
    	scapeImage.setPromptText("http://example.com");
    	scapeAll.setPromptText("http://example.com");
    	folderSelectAll.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
             	selectFoldersAll();
             }
         });
    	folderSelectChan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	selectFoldersChan();
            }
        });
    	folderSelectImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	selectFoldersImage();
            }
        });
    	 imageGrab.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 imageGrabTransfer();
             }
         });
    	 chanGrab.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 chanGrabTransfer();
             }
         });
    	 allGrab.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 allGrabTransfer();
             }
         });
    }
    
    @FXML
    private void initialize() {
       
    }

   
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }
    
    
    public void setIdleImage() {
    	
    }
    @FXML
    private void parseImage(){
    	final Task taskImage;
        taskImage = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
            	try{
            		Image imageLoad = new Image("resources/workingscape.jpg");
            		imageImage.setImage(imageLoad);
            		String imageInput = scapeImage.getText();
            	   	Document doc = Jsoup.connect(imageInput).timeout(0).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();
            	   	Elements file = doc.select("img");
            	   	for (Element el : file) {
            	   		srcImage = el.absUrl("src");
            	   		System.out.println("File Found!");
            	   		System.out.println("src attribute is : " + srcImage);
            	   		grabMeImage(); 	                        				
            				}
            			} catch (Exception ex) {
            				System.err.println("There was an error");
            				Logger.getLogger(ScapeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            	Image imageLoad = new Image("resources/idlescape.jpg");
          	   	imageImage.setImage(imageLoad);
                return null;
            }
        };
 	   Thread threadImage = new Thread(taskImage);
 	   threadImage.setDaemon(true);
 	   threadImage.start();
    }
    
    @FXML
    private void parseAll(){
    	final Task taskAll;
        taskAll = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
            	try{
            		Image allLoad = new Image("resources/workingscape.jpg");
            		allImage.setImage(allLoad);
            		String allInput = scapeAll.getText();
            		Document doc = Jsoup.connect(allInput).timeout(0).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();
        				
        			Elements file = doc.getElementsByTag("a");
        			for (Element el : file) {
                	
        				srcAll = el.absUrl("href");
                    
        				System.out.println("File Found!");
        				System.out.println("src attribute is : " + srcAll);
        				grabMeAll();
        				 	                        				
            			}
            		} catch (Exception ex) {
            			System.err.println("There was an error");
            			Logger.getLogger(ScapeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            	Image allLoad = new Image("resources/idlescape.jpg");
          	   	allImage.setImage(allLoad);
                return null;
            }
        };
 	   Thread threadAll = new Thread(taskAll);
 	   threadAll.setDaemon(true);
 	   threadAll.start();
 	   
    }
    
    
    
    @FXML
    private void parseChan(){
    	
    	final Task task;
        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
            	try{
            		Image chanLoad = new Image("resources/workingscape.jpg");
            		chanImage.setImage(chanLoad);
            		String chanInput = scapeChan.getText();
            	   	Document doc = Jsoup.connect(chanInput).timeout(0).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();
            	   	Elements file = doc.select("a.fileThumb");
            	   	Element folder = doc.select("span.subject").first();
            	   	
            			srcThread = folder.text();
            			srcComplete = "4C" + srcThread;
        				System.out.println("Thread Name: " + srcThread);
        				String srcRWhite = srcComplete.replaceAll("\\s+", "");
        				String srcPass1 = srcRWhite.replaceAll("\\(.+?\\)", "");
        				String srcPass2 = srcPass1.replaceAll("\\.", "");
                        srcRName = srcPass2.replaceAll("[?:<>]", ""); 
                        srcSName = srcRName.replaceAll("\\/", "");
        				File dir = new File(folderPath + "//" + srcSName);
        				dir.mkdir();
            	
            	   	for (Element el : file) {
            	   		srcChan = el.absUrl("href");
            	   		System.out.println("File Found!");
            	   		System.out.println("src attribute is : " + srcChan);
            	   		grabMeChan(); 	                        				
            				}
            	   	
            			} catch (Exception ex) {
            				System.err.println("There was an error");
            				Logger.getLogger(ScapeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            		
            	Image chanLoad = new Image("resources/idlescape.jpg");
          	    chanImage.setImage(chanLoad);
                return null;
            }
        };
 	   Thread thread = new Thread(task);
 	   thread.setDaemon(true);
 	   thread.start();
 	   
    }
    

    public void grabMeChan() throws Exception{
    				int indexname = srcChan.lastIndexOf("/");       
                    String srcname = srcChan.substring(indexname, srcChan.length());
                    String srcTWhite = srcRName.replaceAll("\\s+", "");
                    String srcTName = srcTWhite.replaceAll("[?:<>]/", ""); 
                    String srcNName = srcTName.replaceAll("\\/", "");
                    String Path = folderPath + "//" + srcNName + "//";
                    String srcname2 = srcname.replaceAll("\\s+[?:<>]", "");
                    String myString = Path + srcname2;
                    try {
                    	URL url=new URL(srcChan);
                    	HttpURLConnection connection =
                    			(HttpURLConnection) url.openConnection();
                    	float totalDataRead=0;
                    	java.io.BufferedInputStream in = new java.io.BufferedInputStream(connection.getInputStream());
                    	java.io.FileOutputStream fos = new java.io.FileOutputStream(myString);
                    	java.io.BufferedOutputStream bout = new BufferedOutputStream(fos,1024);
                    	//Declaring the buffer size
                    	byte[] data = new byte[1024];
                    	int i=0;
                    	while((i=in.read(data,0,1024))>=0)
                    	{
                    		totalDataRead=totalDataRead+i;
                    		bout.write(data,0,i);
            			
                    	}	
                    	
                    	bout.close();
                    	in.close();
                                
                    }
                   
                    catch (FileNotFoundException e) {
                    	e.printStackTrace();
                    } catch (IOException e) {
                    	e.printStackTrace();
                    }       
    			
    }   
    
    public void grabMeAll() throws Exception{
    	
    	int indexname = srcAll.lastIndexOf("/");       
        String srcname = srcAll.substring(indexname, srcAll.length());
        String Path = folderPath + "//";
        String srcname2 = srcname.replaceAll("[?:<>]", "");
        String myString = Path + srcname2;
        try {
        	URL url=new URL(srcAll);
        	HttpURLConnection connection =
        			(HttpURLConnection) url.openConnection();
        	float totalDataRead=0;
        	java.io.BufferedInputStream in = new java.io.BufferedInputStream(connection.getInputStream());
        	java.io.FileOutputStream fos = new java.io.FileOutputStream(myString);
        	java.io.BufferedOutputStream bout = new BufferedOutputStream(fos,1024);
        	//Declaring the buffer size
        	byte[] data = new byte[1024];
        	int i=0;
        	while((i=in.read(data,0,1024))>=0)
        	{
        		totalDataRead=totalDataRead+i;
        		bout.write(data,0,i);
			
        	}	
        	
        	bout.close();
        	in.close();
                    
        }
       
        catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }

}   
    
    public void grabMeImage() throws Exception{
    	
    	int indexname = srcImage.lastIndexOf("/");       
        String srcname = srcImage.substring(indexname, srcImage.length());
        String Path = folderPath + "//";
        String srcname2 = srcname.replaceAll("[?:<>]", "");
        String myString = Path + srcname2;
        try {
        	URL url=new URL(srcImage);
        	HttpURLConnection connection =
        			(HttpURLConnection) url.openConnection();
        	float totalDataRead=0;
        	java.io.BufferedInputStream in = new java.io.BufferedInputStream(connection.getInputStream());
        	java.io.FileOutputStream fos = new java.io.FileOutputStream(myString);
        	java.io.BufferedOutputStream bout = new BufferedOutputStream(fos,1024);
        	//Declaring the buffer size
        	byte[] data = new byte[1024];
        	int i=0;
        	while((i=in.read(data,0,1024))>=0)
        	{
        		totalDataRead=totalDataRead+i;
        		bout.write(data,0,i);
			
        	}	
        	
        	bout.close();
        	in.close();
                    
        }
       
        catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }

}   
    
}
