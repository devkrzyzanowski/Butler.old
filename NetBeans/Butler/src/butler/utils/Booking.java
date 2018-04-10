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
    private StringProperty startOfReservationDate = new SimpleStringProperty();
    private StringProperty endOfReservationDate = new SimpleStringProperty();
    private IntegerProperty roomId = new SimpleIntegerProperty();
    private IntegerProperty paymentStatus = new SimpleIntegerProperty();
    
    public Booking(){
        
    }

    public IntegerProperty getId() {
        return id;
    }

    public StringProperty getStartOfReservationDate() {
        return startOfReservationDate;
    }

    public void setStartOfReservationDate(StringProperty startOfReservationDate) {
        this.startOfReservationDate = startOfReservationDate;
    }

    public StringProperty getEndOfReservationDate() {
        return endOfReservationDate;
    }

    public void setEndOfReservationDate(StringProperty endOfReservationDate) {
        this.endOfReservationDate = endOfReservationDate;
    }

    public IntegerProperty getRoomId() {
        return roomId;
    }

    public void setRoomId(IntegerProperty roomId) {
        this.roomId = roomId;
    }

    public IntegerProperty getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(IntegerProperty paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
}
