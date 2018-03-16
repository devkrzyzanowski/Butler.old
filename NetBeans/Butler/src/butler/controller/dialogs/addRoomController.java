/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.dialogs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author uwxyy
 */
public class addRoomController implements Initializable {
    
    @FXML
    private Button cancelButton;
    
    
        @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
        @FXML private void cancelAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
        @FXML private void addRoom(ActionEvent event) {
            butler.Butler.model.addRoomToRoomList("test", 4, Boolean.TRUE);
        }
}
