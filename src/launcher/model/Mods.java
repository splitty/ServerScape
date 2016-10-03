package launcher.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Mods {

    private final StringProperty modName;
    private final StringProperty versionNumber;
    private final StringProperty webPage;

    public Mods() {
        this(null, null, null);
    }

    public Mods(String modName, String versionNumber, String webPage) {
        this.modName = new SimpleStringProperty(modName);
        this.versionNumber = new SimpleStringProperty(versionNumber);
        this.webPage = new SimpleStringProperty(webPage);
        
    }

    public String getmodName() {
        return modName.get();
    }

    public void setmodName(String modName) {
        this.modName.set(modName);
    }

    public StringProperty modNameProperty() {
        return modName;
    }    
    public String getversionNumber() {
        return versionNumber.get();
    }

    public void setversionNumber(String versionNumber) {
        this.versionNumber.set(versionNumber);
    }

    public StringProperty versionNumberProperty() {
        return versionNumber;
    }  
    public String getwebPage() {
        return webPage.get();
    }

    public void setwebPage(String webPage) {
        this.webPage.set(webPage);
    }

    public StringProperty webPageProperty() {
        return webPage;
    }  
}