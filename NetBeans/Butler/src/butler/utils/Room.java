/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.utils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author uwxyy
 */
public class Room {
    private StringProperty name  = new SimpleStringProperty();
    private IntegerProperty numberOfBeds = new SimpleIntegerProperty();
    private BooleanProperty privateBathroom = new SimpleBooleanProperty();
    
    public Room(String name, Integer numberOfBeds, Boolean privateBathroom){
        this.name.set(name);
        this.numberOfBeds.set(numberOfBeds);
        this.privateBathroom.set(privateBathroom);
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(StringProperty name) {
        this.name = name;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds.getValue();
    }

    public void setNumberOfBeds(IntegerProperty numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Boolean getPrivateBathroom() {
        return privateBathroom.getValue();
    }

    public void setPrivateBathroom(BooleanProperty privateBathroom) {
        this.privateBathroom = privateBathroom;
    }
}
