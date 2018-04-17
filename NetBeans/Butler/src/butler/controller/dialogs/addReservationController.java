/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.dialogs;

import butler.model.Model;
import butler.utils.Client;
import butler.utils.Room;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Michał
 */
public class addReservationController extends DialogBox implements Initializable {
    
    @FXML ComboBox selectRoomComboBox;
    @FXML TextField selectClientTextField;
    private Client selectedClient;
   
    
    private ObservableList<Room> roomObservableList = FXCollections.observableArrayList();
    
        @Override
    public void initialize(URL location, ResourceBundle resources) {
            Model model = butler.Butler.model;
        try {
            roomObservableList.addAll(model.getRoomList());
        } catch (SQLException ex) {
            Logger.getLogger(addReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        selectRoomComboBox.setItems(roomObservableList);
    }
    
    @FXML private void addReservation(ActionEvent event){
        
    }
            @FXML private void openSelectClientDialog(ActionEvent event) throws IOException{
        Stage stage = new Stage();            
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/butler/view/dialogs/selectClientDialog.fxml"));
        Parent parent = (Parent) fXMLLoader.load();
        selectClientController sCC = fXMLLoader.getController();
        sCC.getSelectedButton().setOnAction(e -> {
            selectedClient = sCC.getTable().getSelectionModel().getSelectedItem();
            selectClientTextField.setText(selectedClient.getFirstName() + " " + selectedClient.getLastName());
            sCC.close();
        });
        
        Scene scene = new Scene(parent);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
}
