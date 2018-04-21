/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.dialogs;

import butler.model.Model;
import butler.utils.Client;
import butler.utils.Room;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author uwxyy
 */
public class selectRoomController extends DialogBox implements Initializable {
    @FXML private TableView<Room> roomTableView;
    @FXML private TableColumn<Room, String> roomNameTableColumn, 
            buildingTableColumn, extraDescriptionTableColumn;
    @FXML private TableColumn<Room, Integer> numberOfSingleBedsTableColumn, 
            numberOfDoubleBedsTableColumn, numberOfExtraBedsTableColumn, floorNumberTableColumn;
    @FXML private Button selectRoomButton;
    private Model model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = butler.Butler.model;
        roomTableView.setItems(model.getRoomList());
        roomNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        buildingTableColumn.setCellValueFactory(new PropertyValueFactory<>("building"));
        extraDescriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("extraDescription"));
        numberOfSingleBedsTableColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfSingleBeds"));
        numberOfDoubleBedsTableColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfDoubleBeds"));
        numberOfExtraBedsTableColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfExtraBeds"));
        floorNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("floorNumber"));
    }
    
        public void close(){
        Stage stage = (Stage) selectRoomButton.getScene().getWindow();
        stage.close();        
    }
        @FXML private void selectRoom(ActionEvent event) {
        Stage stage = (Stage) selectRoomButton.getScene().getWindow();
        stage.close();
    }
    public Button getSelectedButton () {
        return selectRoomButton;
    }
    
        String getSelected() {
        if (!roomTableView.getSelectionModel().isEmpty()) {
            return roomTableView.getSelectionModel().getSelectedItem().getRoomName();
        } else {
            return "BRAK";
        }
    }
            TableView<Room> getTable() {
        return roomTableView;
    }
}
