/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package JFXion;

import butler.model.Model;
import butler.utils.Booking;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
    private Color bgColor;
    private Integer idBooking;
    private Booking booking;
    
    public ScheduleCell(Integer idColumn, Integer idRow, Booking booking) {
        model = butler.Butler.model;        
        this.booking = booking;
        this.idColumn = idColumn;
        this.idRow = idRow;
        this.setMinSize(120, 20);
        this.setPrefSize(120, 20);
        this.bookingDays = booking.getBookingDays();
        this.idBooking = booking.getId().getValue();
        this.bgColor = Color.web(model.getLegendById(booking.getIdLegend()).getColor());
        System.out.println("test");
 
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String col = String.valueOf(bgColor.darker()).substring(2, 8);
                setStyle("-fx-background-color: #"+ col +";"
                + " -fx-border-width : 1px;"
                + " -fx-border-color: lightgrey grey grey lightgrey;");                
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String col = String.valueOf(bgColor.brighter()).substring(2, 8);
                setStyle("-fx-background-color: #"+ col +";"
                + " -fx-border-width : 1px;"
                + " -fx-border-color: lightgrey grey grey lightgrey;");                
            }
        });
        String col = String.valueOf(bgColor).substring(2, 8);
        text = new Label(model.getClientById(booking.getIdClient()).getFirstName()
                + " " + model.getClientById(booking.getIdClient()).getFirstName()
                + "\n" + model.getClientById(booking.getIdClient()).getContactPhoneNumber());
        
        this.setStyle("-fx-background-color: #"+ col +";"
                + " -fx-border-width : 1px;"
                + " -fx-border-color: lightgrey grey grey lightgrey;");
        text.setLayoutX(14);
        text.setLayoutY(2);
        this.getChildren().add(text);
        addListeners();
    }
    
     public Integer getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Integer idBooking) {
        this.idBooking = idBooking;
    }

    public Booking getBooking() {
        return booking;
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
