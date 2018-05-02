/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller;

import butler.model.Model;
import butler.utils.Room;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Michał
 */
public class listOfRoomsController implements Initializable {

    @FXML private Button addRoomButton;
    @FXML private TableView<Room> roomTableView;
    @FXML private TableColumn<Room, String> roomNameTableColumn, buildingTableColumn, extraDescriptionTableColumn;
    @FXML private TableColumn<Room, Integer> numberOfSingleBedsTableColumn, numberOfDoubleBedsTableColumn, numberOfExtraBedsTableColumn, floorNumberTableColumn; //FIRST
    @FXML private TableColumn<Room, Double> priceOfRoomTableColumn, priceOfAdultTableColumn, priceOfMinorTableColumn;
    Model model;
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = butler.Butler.model;
        roomNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        numberOfSingleBedsTableColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfSingleBeds"));
        numberOfDoubleBedsTableColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfDoubleBeds"));
        numberOfExtraBedsTableColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfExtraBeds"));
        floorNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("floorNumber"));
        buildingTableColumn.setCellValueFactory(new PropertyValueFactory<>("building"));
        priceOfRoomTableColumn.setCellValueFactory(new PropertyValueFactory<>("priceOfRoom"));
        priceOfAdultTableColumn.setCellValueFactory(new PropertyValueFactory<>("priceOfAdult"));
        priceOfMinorTableColumn.setCellValueFactory(new PropertyValueFactory<>("priceOfMinor"));
        extraDescriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("extraDescription"));
        roomTableView.setItems(model.getRoomList());
        
    }
   
    @FXML private void modifyRoomAction(ActionEvent event) {
        
    }
    
    @FXML private void addRoomAction(ActionEvent event) throws IOException {        
                butler.Butler.stageManager.addModalStage((Stage) ((Node) event.getSource())
                .getScene().getWindow(), "/butler/view/dialogs/addRoomDialog.fxml");
    }    
}
