/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
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

    }
        @FXML private void modifyReservation(ActionEvent event) {

    }
}
