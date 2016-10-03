package launcher.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import launcher.MainApp;
public class Person {
	
    private final StringProperty serverIP;
    private final StringProperty serverPing;
    private final StringProperty serverName;
    private MainApp main;
    /**
     * Default constructor.
     */
    public Person() {
        this(null, null, null);
    }
    
    public Person(String serverName, String serverPing, String serverIP) {
    	this.serverName = new SimpleStringProperty(serverName);
        this.serverIP = new SimpleStringProperty(serverIP);
        this.serverPing = new SimpleStringProperty(serverPing);
        this.serverName.set(MainApp.serverFinal);
        this.serverIP.set(MainApp.ipFinal);
        this.serverPing.set(MainApp.pingFinal);
        System.out.println(serverName);
        System.out.println(serverIP);
        System.out.println(serverPing);
    }
    public String getserverName() {
        return serverName.get();
    }

    public void setserverName(String serverName) {
        this.serverName.set(serverName);
    }

    public StringProperty serverNameProperty() {
        return serverName;
    }

    public String getserverIP() {
        return serverIP.get();
    }

    public void setserverIP(String serverIP) {
        this.serverIP.set(serverIP);
    }

    public StringProperty serverIPProperty() {
        return serverIP;
    }

    public String getserverPing() {
        return serverPing.get();
    }

    public void setserverPing(String serverPing) {
        this.serverPing.set(serverPing);
    }

    public StringProperty serverPingProperty() {
        return serverPing;
    }
}