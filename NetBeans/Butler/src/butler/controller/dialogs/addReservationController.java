/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.dialogs;

import butler.model.Model;
import butler.utils.Client;
import butler.utils.Legend;
import butler.utils.Room;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Michał
 */
public class addReservationController extends DialogBox implements Initializable {
    
    @FXML TextField selectClientTextField, selectRoomTextField;
    @FXML DatePicker fromDatePicker, toDatePicker;
    @FXML Button addReservationButton;
    @FXML ComboBox<Legend> selectStatusComboBox;
    private Client selectedClient;
    private Room selectedRoom;
    private Model model;
   
    
    private ObservableList<Room> roomObservableList = FXCollections.observableArrayList();
    
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = butler.Butler.model;
        selectStatusComboBox.setItems(model.getLegendList());
    }
    
    private void sendReservationToDataBase(){
        Timestamp ts = Timestamp.valueOf(fromDatePicker.getValue().atStartOfDay());
        Timestamp ts2 = Timestamp.valueOf(toDatePicker.getValue().atStartOfDay());
        
        model.addBookingToDataBase(ts, ts2, selectedClient.getId(), selectedRoom.getId(), selectStatusComboBox.getSelectionModel().getSelectedItem().getIdLegend());
    }
    
    @FXML private void addReservation(ActionEvent event){
        sendReservationToDataBase();
        close();
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
    
    @FXML private void openSelectRoomDialog(ActionEvent event) throws IOException{
        Stage stage = new Stage();            
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/butler/view/dialogs/selectRoomDialog.fxml"));
        Parent parent = (Parent) fXMLLoader.load();
        selectRoomController sCC = fXMLLoader.getController();
        sCC.getSelectedButton().setOnAction(e -> {
            selectedRoom = sCC.getTable().getSelectionModel().getSelectedItem();
            selectRoomTextField.setText(selectedRoom.getRoomName());
            sCC.close();
        });
        
        Scene scene = new Scene(parent);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    
        public void close(){
        Stage stage = (Stage) addReservationButton.getScene().getWindow();
        stage.close();        
    }
}
