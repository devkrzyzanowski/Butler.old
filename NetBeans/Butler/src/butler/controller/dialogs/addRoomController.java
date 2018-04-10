/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.dialogs;

import butler.model.Model;
import butler.utils.Room;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author uwxyy
 */
public class addRoomController extends DialogBox implements Initializable {
    
    @FXML private TextField nameTextField;
    @FXML private ChoiceBox<Integer> numberOfSingleBedsChoiceBox;
    @FXML private ChoiceBox<Integer> numberOfDoubleBedsChoiceBox;
    @FXML private ChoiceBox<Integer> numberOfExtraBedsChoiceBox;
    @FXML private TextField floorNumberTextField;
    @FXML private TextField buildingTextField;
    @FXML private TextField smallDescriptionTextField;
    @FXML private TextArea bigDescriptionTextArea;
    @FXML private TextArea extraDescriptionTextArea;
    @FXML private TextField priceOfRoomTextField;
    @FXML private TextField priceOfAdultTextField;
    @FXML private TextField priceOfMinorTextField;
    private Model model;
    private ObservableList<Integer> intList;
    
    
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = butler.Butler.model;
        intList = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numberOfSingleBedsChoiceBox.setItems(intList);
        numberOfDoubleBedsChoiceBox.setItems(intList);
        numberOfExtraBedsChoiceBox.setItems(intList);
    }
    
        @FXML private void addRoom(ActionEvent event) throws SQLException {
            String name = nameTextField.getText();
            Integer numberOfSingleBeds = Integer.valueOf(numberOfSingleBedsChoiceBox.getValue());
            Integer numberOfDoubleBeds = Integer.valueOf(numberOfDoubleBedsChoiceBox.getValue());
            Integer numberOfExtraBeds = Integer.valueOf(numberOfExtraBedsChoiceBox.getValue());
            Integer floorNumber = Integer.valueOf(floorNumberTextField.getText());
            String building = buildingTextField.getText();
            String smallDescription = smallDescriptionTextField.getText();
            String bigDescription = bigDescriptionTextArea.getText();
            String extraDescription = extraDescriptionTextArea.getText();
            Double priceOfroom = Double.valueOf(priceOfRoomTextField.getText());
            Double priceOfAdult = Double.valueOf(priceOfAdultTextField.getText());
            Double priceOfMinor = Double.valueOf(priceOfMinorTextField.getText());
            Room room = new Room(name, numberOfSingleBeds, numberOfDoubleBeds, numberOfExtraBeds, floorNumber, priceOfroom, priceOfAdult, priceOfMinor, smallDescription, bigDescription, extraDescription, building);
            model.addRoomToDataBase(room);
        }
}
