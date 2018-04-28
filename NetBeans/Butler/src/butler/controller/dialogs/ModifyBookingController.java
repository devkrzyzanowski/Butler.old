/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.dialogs;

import JFXion.IonSchedule;
import butler.model.Model;
import butler.utils.Booking;
import butler.utils.Legend;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

    public void init(Booking booking, IonSchedule ion) {
        this.ion = ion;
        this.booking = booking;
        label.setText(String.valueOf(booking.getBookingDays()));
    }
    
    @FXML private void edit(ActionEvent event) {
        Integer legendId = selectStatusComboBox.getSelectionModel().getSelectedItem().getIdLegend();
        if (legendId == null) legendId = 13;
        model.setBookingStatus(booking.getId().getValue(), legendId);
        updateAndClose((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
    
    @FXML private void removeReservation(ActionEvent event) {
        butler.Butler.model.removeBookingById(booking.getId().getValue());
        updateAndClose((Stage) ((Node) event.getSource()).getScene().getWindow());
    }
    
    private void updateAndClose(Stage stage){
        ion.update();
        stage.close();        
    }
}
