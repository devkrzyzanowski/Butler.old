/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.utils;

/**
 *
 * @author Michał
 */
public class ExtraItems {
    private Boolean balcon, beachScreen, blanket, sunbed, tv, wiFi,
            individualEntrance, friendlyAnimal, tableware, lamp = false;
    
    public ExtraItems(){
        
    }
    
    public void add(String s) {
        switch (s) {
            case "balcon" : setBalcon(true); break;
            
        }
    }

    public Boolean getBalcon() {
        return balcon;
    }

    public void setBalcon(Boolean balcon) {
        this.balcon = balcon;
    }

    public Boolean getBeachScreen() {
        return beachScreen;
    }

    public void setBeachScreen(Boolean beachScreen) {
        this.beachScreen = beachScreen;
    }

    public Boolean getBlanket() {
        return blanket;
    }

    public void setBlanket(Boolean blanket) {
        this.blanket = blanket;
    }

    public Boolean getSunbed() {
        return sunbed;
    }

    public void setSunbed(Boolean sunbed) {
        this.sunbed = sunbed;
    }

    public Boolean getTv() {
        return tv;
    }

    public void setTv(Boolean tv) {
        this.tv = tv;
    }

    public Boolean getWiFi() {
        return wiFi;
    }

    public void setWiFi(Boolean wiFi) {
        this.wiFi = wiFi;
    }

    public Boolean getIndividualEntrance() {
        return individualEntrance;
    }

    public void setIndividualEntrance(Boolean individualEntrance) {
        this.individualEntrance = individualEntrance;
    }

    public Boolean getFriendlyAnimal() {
        return friendlyAnimal;
    }

    public void setFriendlyAnimal(Boolean friendlyAnimal) {
        this.friendlyAnimal = friendlyAnimal;
    }

    public Boolean getTableware() {
        return tableware;
    }

    public void setTableware(Boolean tableware) {
        this.tableware = tableware;
    }

    public Boolean getLamp() {
        return lamp;
    }

    public void setLamp(Boolean lamp) {
        this.lamp = lamp;
    }
}
