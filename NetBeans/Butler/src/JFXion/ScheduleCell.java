/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package JFXion;

import butler.model.Model;
import butler.utils.Booking;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author MichalKrzyzanowski
 */
public class ScheduleCell extends Pane {
    private Integer idColumn;
    private Integer idRow;
    private Label text;
    private Integer bookingDays;
    private Model model;
    private String bgColor;
    
    
    public ScheduleCell(Integer idColumn, Integer idRow, Booking booking) {
        this.idColumn = idColumn;
        this.idRow = idRow;
        this.setMinSize(120, 20);
        this.setPrefSize(120, 20);
        this.bookingDays = booking.getBookingDays();
        model = butler.Butler.model;
        switch (booking.getBookingStatus()){
            case 1 :bgColor = "blue";  break;
            case 2 :bgColor = "green";  break;
            case 3 :bgColor = "yellow";  break;
            case 4 :bgColor = "orange";  break;
            default : bgColor = "red"; break;
        }
        text = new Label(model.getClientById(booking.getIdClient()).getFirstName() + " " + model.getClientById(booking.getIdClient()).getFirstName() + "\n" + model.getClientById(booking.getIdClient()).getContactPhoneNumber());
                this.setStyle("-fx-background-color: "+ bgColor +";"
                + " -fx-border-width : 1px;"
                + " -fx-border-color: lightgrey grey grey lightgrey;"
        );
        text.setLayoutX(14);
        text.setLayoutY(2);
        this.getChildren().add(text);
        addListeners();
    }

    public Integer getBookingDays() {
        return bookingDays;
    }

    public void setBookingDays(Integer bookingDays) {
        this.bookingDays = bookingDays;
    }

    public Integer getIdColumn() {
        return idColumn;
    }

    public void setIdColumn(Integer idColumn) {
        this.idColumn = idColumn;
    }

    public Integer getIdRow() {
        return idRow;
    }

    public void setIdRow(Integer idRow) {
        this.idRow = idRow;
    }

    public Label getText() {
        return text;
    }

    public void setText(Label text) {
        this.text = text;
    }

    private void addListeners() {
        
    }
    
}
