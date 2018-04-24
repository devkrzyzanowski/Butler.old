/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.dialogs;

import JFXion.IonSchedule;
import butler.controller.BookingScheduleController;
import butler.model.Model;
import butler.utils.Booking;
import butler.utils.Legend;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author MichalKrzyzanowski
 */
public class ModifyBookingController extends DialogBox implements Initializable {
    private Booking booking;
    @FXML private Label label;
    @FXML ComboBox<Legend> selectStatusComboBox;
    private IonSchedule ion;
    private Model model;
    public ModifyBookingController () {
    }
    

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = butler.Butler.model;
        selectStatusComboBox.setItems(model.getLegendList());
    }
//            Stage stage = new Stage();            
//        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/butler/view/dialogs/selectRoomDialog.fxml"));
//        Parent parent = (Parent) fXMLLoader.load();
//        selectRoomController sCC = fXMLLoader.getController();
//        sCC.getSelectedButton().setOnAction(e -> {
//            selectedRoom = sCC.getTable().getSelectionModel().getSelectedItem();
//            selectRoomTextField.setText(selectedRoom.getRoomName());
//            sCC.close();
//        });
//        
//        Scene scene = new Scene(parent);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
//
//        stage.initStyle(StageStyle.TRANSPARENT);
//        stage.setScene(scene);
//        stage.show();

    public void init(Booking booking, IonSchedule ion) {
        this.ion = ion;
        this.booking = booking;
        label.setText(String.valueOf(booking.getBookingDays()));
    }
    
    @FXML private void edit(ActionEvent event) {
        Integer legendId = selectStatusComboBox.getSelectionModel().getSelectedItem().getIdLegend();
        if (legendId == null) legendId = 13;
        model.setBookingStatus(booking.getId().getValue(), legendId);
        Stage stage = (Stage) label.getScene().getWindow();
        ion.update();
        stage.close();
    }
    
    @FXML private void removeReservation(ActionEvent event) {
        butler.Butler.model.removeBookingById(booking.getId().getValue());
        Stage stage = (Stage) label.getScene().getWindow();
        ion.update();
        stage.close();
    }
}
