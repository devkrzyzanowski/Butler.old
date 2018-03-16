/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller;

import butler.model.Model;
import butler.utils.Room;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Michał
 */
public class listOfRoomsController implements Initializable {

    @FXML private Button addRoomButton;
    @FXML private TableView<Room> roomTableView;
    @FXML private TableColumn<Room, String> name;
    @FXML private TableColumn<Room, Integer> numberOfBeds;
    @FXML private TableColumn<Room, Boolean> privateBathroom;
    Model model;
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = butler.Butler.model;
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberOfBeds.setCellValueFactory(new PropertyValueFactory<>("numberOfBeds"));
        privateBathroom.setCellValueFactory(new PropertyValueFactory<>("privateBathroom"));
        try {
            roomTableView.setItems(model.getRoomList());
        } catch (SQLException ex) {
            Logger.getLogger(OperationHistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    @FXML private void addRoomAction(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/butler/view/dialogs/addRoomDialog.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }    
    
}
