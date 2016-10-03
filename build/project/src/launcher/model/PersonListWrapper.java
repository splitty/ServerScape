package launcher.model;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "launchers")
public class PersonListWrapper {
	
    public String serverName;
    public String serverIP;
    public String serverPing;
    private List<Mods> mods;
    
    @XmlElement(name = "mods")
    public List<Mods> getMods() {
        return mods;
    }
    
    public void setMods(List<Mods> mods) {
        this.mods = mods;
    }      
    
    @XmlElement(name = "serverName")
    public String getServerXML() {
        return serverName;
    }
    
    public void setServerXML(String serverName) {  
        this.serverName = serverName;  
    }  
    
    @XmlElement(name = "serverPing")
    public String getPingXML() {
        return serverPing;
    }
    
    public void setPingXML(String serverPing) {  
        this.serverPing = serverPing;  
    }   
    
    @XmlElement(name = "serverIP")
    public String getIPXML() {
        return serverIP;
    }    
    
    public void setIPXML(String serverIP) {  
        this.serverIP = serverIP;  
    } 
    
}