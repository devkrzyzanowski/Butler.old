/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller;

import JFXion.IonSchedule;
import butler.model.Model;
import butler.utils.Booking;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author Michał
 */
public class BookingScheduleController implements Initializable {

    @FXML private AnchorPane main;
    @FXML private Button addReservationButton, removeReservationButton, modifyReservationButton;
    @FXML private Button selectClientButton, refreshButton;
    @FXML private TableView<Booking> bookingTableView;
    @FXML private TableColumn<Booking, String> beginBookingTableColumn, toBookingTableColumn;
    @FXML private TableColumn<Booking, Integer> roomTableColumn, clientTableColumn;
    @FXML private AnchorPane anchorPane;
    private Model model;
    private IonSchedule ionSchedule;
    @FXML ResourceBundle bundle;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bundle = ResourceBundle.getBundle("resources.bundles.messages");
        model = butler.Butler.model;
        bookingTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        bookingTableView.getSelectionModel().setCellSelectionEnabled(true);
 
        ionSchedule = new IonSchedule(model.getRoomList(), model.getBookingList());
        ionSchedule.setLayoutX(0);
        ionSchedule.setLayoutY(6);
        ionSchedule.setMinSize(960, 690);
        ionSchedule.setMaxSize(960, 690);
        anchorPane.getChildren().add(ionSchedule);
        bookingTableView.setItems(model.getBookingList());

        beginBookingTableColumn.setCellValueFactory(new PropertyValueFactory<>("beginOfBooking"));
        toBookingTableColumn.setCellValueFactory(new PropertyValueFactory<>("endOfBooking"));
        roomTableColumn.setCellValueFactory(new PropertyValueFactory<>("idRoom"));
        clientTableColumn.setCellValueFactory(new PropertyValueFactory<>("idClient"));
    }
    @FXML private void addReservation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/butler/view/dialogs/addReservationDialog.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
        @FXML private void removeReservation(ActionEvent event) {
            if (!bookingTableView.getSelectionModel().isEmpty()) {
                model.removeBookingById(bookingTableView.getSelectionModel().getSelectedItem().getId().getValue());
            }
            refresh();
    }
        @FXML private void modifyReservation(ActionEvent event) {

    }
        @FXML private void refreshData(ActionEvent event) {
            refresh();
        }
        
        private void refresh(){
            bookingTableView.setItems(model.getBookingList());
            ionSchedule.update();
        }

}
