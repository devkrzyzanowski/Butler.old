/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.dialogs;

import java.net.URL;
import java.sql.SQLException;
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
        @FXML private void addRoom(ActionEvent event) throws SQLException {
            if(butler.Butler.model.addRoomToRoomList("test", 4, Boolean.TRUE)) {
                butler.Butler.model.addToOperationHistory("Pomyślnie dodano test do listy pokoi", butler.Butler.model.getUserName());
            } else {
                butler.Butler.model.addToOperationHistory("Błąd dodawania test do listy pokoi", butler.Butler.model.getUserName());
            }
        }
}
