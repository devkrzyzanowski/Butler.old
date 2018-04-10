/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.utils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author uwxyy
 */
public class Room {
    private StringProperty roomName = new SimpleStringProperty();
    private IntegerProperty numberOfSingleBeds = new SimpleIntegerProperty();
    private IntegerProperty numberOfDoubleBeds = new SimpleIntegerProperty();
    private IntegerProperty numberOfExtraBeds = new SimpleIntegerProperty();
    private IntegerProperty floorNumber = new SimpleIntegerProperty();
    private DoubleProperty priceOfRoom = new SimpleDoubleProperty();
    private DoubleProperty priceOfAdult = new SimpleDoubleProperty();
    private DoubleProperty priceOfMinor = new SimpleDoubleProperty();
    private StringProperty smallDescription = new SimpleStringProperty();
    private StringProperty bigDescription = new SimpleStringProperty();
    private StringProperty extraDescription = new SimpleStringProperty();
    private StringProperty building = new SimpleStringProperty();
    
    
    public Room(String roomName, Integer numberOfSingleBeds, Integer numberOfDoubleBeds,
                Integer numberOfExtraBeds, Integer floorNumber, Double priceOfRoom,
                Double priceOfAdult, Double priceOfMinor, String smallDescription,
                String bigDescription, String extraDescription, String building){
        this.roomName.set(roomName);
        this.numberOfSingleBeds.set(numberOfSingleBeds);
        this.numberOfDoubleBeds.set(numberOfDoubleBeds);
        this.numberOfExtraBeds.set(numberOfExtraBeds);
        this.floorNumber.set(floorNumber);
        this.priceOfRoom.set(priceOfRoom);
        this.priceOfAdult.set(priceOfAdult);
        this.priceOfMinor.set(priceOfMinor);
        this.smallDescription.set(smallDescription);
        this.bigDescription.set(bigDescription);
        this.extraDescription.set(extraDescription);
    }

    public String getRoomName() {
        return roomName.getValue();
    }

    public void setName(StringProperty roomName) {
        this.roomName = roomName;
    }

    public Integer getNumberOfSingleBeds() {
        return numberOfSingleBeds.getValue();
    }

    public void setNumberOfSingleBeds(IntegerProperty numberOfSingleBeds) {
        this.numberOfSingleBeds = numberOfSingleBeds;
    }

    public Integer getNumberOfDoubleBeds() {
        return numberOfDoubleBeds.getValue();
    }

    public void setNumberOfDoubleBeds(IntegerProperty numberOfDoubleBeds) {
        this.numberOfDoubleBeds = numberOfDoubleBeds;
    }

    public Integer getNumberOfExtraBeds() {
        return numberOfExtraBeds.getValue();
    }

    public void setNumberOfExtraBeds(IntegerProperty numberOfExtraBeds) {
        this.numberOfExtraBeds = numberOfExtraBeds;
    }

    public Integer getFloorNumber() {
        return floorNumber.getValue();
    }

    public void setFloorNumber(IntegerProperty floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Double getPriceOfRoom() {
        return priceOfRoom.getValue();
    }

    public void setPriceOfRoom(DoubleProperty priceOfRoom) {
        this.priceOfRoom = priceOfRoom;
    }

    public Double getPriceOfAdult() {
        return priceOfAdult.getValue();
    }

    public void setPriceOfAdult(DoubleProperty priceOfAdult) {
        this.priceOfAdult = priceOfAdult;
    }

    public Double getPriceOfMinor() {
        return priceOfMinor.getValue();
    }

    public void setPriceOfMinor(DoubleProperty priceOfMinor) {
        this.priceOfMinor = priceOfMinor;
    }

    public String getSmallDescription() {
        return smallDescription.getValue();
    }

    public void setSmallDescription(StringProperty smallDescription) {
        this.smallDescription = smallDescription;
    }

    public String getBigDescription() {
        return bigDescription.getValue();
    }

    public void setBigDescription(StringProperty bigDescription) {
        this.bigDescription = bigDescription;
    }

    public String getExtraDescription() {
        return extraDescription.getValue();
    }

    public void setExtraDescription(StringProperty extraDescription) {
        this.extraDescription = extraDescription;
    }

    public String getBuilding() {
        return building.getValue();
    }

    public void setBuilding(StringProperty building) {
        this.building = building;
    }

}
