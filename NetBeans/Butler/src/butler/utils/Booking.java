/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.utils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Michał
 */
public class Booking {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty beginOfBooking = new SimpleStringProperty();
    private StringProperty endOfBooking = new SimpleStringProperty();
    private IntegerProperty idClient = new SimpleIntegerProperty();
    private IntegerProperty idRoom = new SimpleIntegerProperty();
    
    public Booking(Integer id, String beginOfBooking, String endOfBooking, Integer clientId, Integer roomId){
        this.id.set(id);
        this.beginOfBooking.set(beginOfBooking);
        this.endOfBooking.set(endOfBooking);
        this.idClient.set(clientId);
        this.idRoom.set(roomId);
    }

    public IntegerProperty getId() {
        return id;
    }

    public void setId(IntegerProperty id) {
        this.id = id;
    }

    public String getBeginOfBooking() {
        return beginOfBooking.getValue();
    }

    public void setBeginOfBooking(StringProperty beginOfBooking) {
        this.beginOfBooking = beginOfBooking;
    }

    public StringProperty getEndOfBooking() {
        return endOfBooking;
    }

    public void setEndOfBooking(StringProperty endOfBooking) {
        this.endOfBooking = endOfBooking;
    }

    public IntegerProperty getClientId() {
        return idClient;
    }

    public IntegerProperty getIdClient() {
        return idClient;
    }

    public void setIdClient(IntegerProperty idClient) {
        this.idClient = idClient;
    }

    public IntegerProperty getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(IntegerProperty idRoom) {
        this.idRoom = idRoom;
    }

    public void setClientId(IntegerProperty clientId) {
        this.idClient = clientId;
    }

    public IntegerProperty getRoomId() {
        return idRoom;
    }

    public void setRoomId(IntegerProperty roomId) {
        this.idRoom = roomId;
    }

    
}
