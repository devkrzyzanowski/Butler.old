/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.dialogs;

import butler.model.Model;
import butler.utils.Booking;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author MichalKrzyzanowski
 */
public class BookingScheduleTableViewController implements Initializable {
    @FXML private TableView<Booking> bookingTableView;
    @FXML private TableColumn<Booking, String> beginBookingTableColumn, toBookingTableColumn;
    @FXML private TableColumn<Booking, Integer> roomTableColumn, clientTableColumn;
    private Model model;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = butler.Butler.model;
        bookingTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        bookingTableView.getSelectionModel().setCellSelectionEnabled(true);
        bookingTableView.setItems(model.getBookingList());

        beginBookingTableColumn.setCellValueFactory(new PropertyValueFactory<>("beginOfBooking"));
        toBookingTableColumn.setCellValueFactory(new PropertyValueFactory<>("endOfBooking"));
        roomTableColumn.setCellValueFactory(new PropertyValueFactory<>("idRoom"));
        clientTableColumn.setCellValueFactory(new PropertyValueFactory<>("idClient"));
    }
    
//            @FXML private void removeReservation(ActionEvent event) {
//            if (!bookingTableView.getSelectionModel().isEmpty()) {
//                model.removeBookingById(bookingTableView.getSelectionModel().getSelectedItem().getId().getValue());
//            }
//            refresh();
//    }
//            private void refresh(){
//            bookingTableView.setItems(model.getBookingList());
//        }
}
