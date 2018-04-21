/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.dialogs;

import butler.utils.Booking;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    public void init(Booking booking) {
        this.booking = booking;
        label.setText(String.valueOf(booking.getBookingDays()));
    }
}
