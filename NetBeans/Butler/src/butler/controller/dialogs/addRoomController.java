/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.dialogs;

import butler.model.Model;
import butler.utils.BooleanObject;
import butler.utils.ExtraItems;
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
import org.controlsfx.control.ListSelectionView;

/**
 *
 * @author uwxyy
 */
public class addRoomController extends DialogBox implements Initializable {
    
    @FXML private TextField roomNameTextField;
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
    @FXML private ListSelectionView<BooleanObject> extraListSelectionView;
    
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = butler.Butler.model;
        
        ObservableList<BooleanObject> booleanObjectObservableList;
        booleanObjectObservableList = FXCollections.observableArrayList();
        booleanObjectObservableList.add(new BooleanObject(0, "Balkon", Boolean.FALSE));
        booleanObjectObservableList.add(new BooleanObject(1, "Parawan", Boolean.FALSE));
        booleanObjectObservableList.add(new BooleanObject(2, "Koc", Boolean.FALSE));
        booleanObjectObservableList.add(new BooleanObject(3, "Leżak", Boolean.FALSE));
        booleanObjectObservableList.add(new BooleanObject(4, "Telewizor", Boolean.FALSE));
        booleanObjectObservableList.add(new BooleanObject(5, "Wi-Fi", Boolean.FALSE));
        booleanObjectObservableList.add(new BooleanObject(6, "Indywidualne wejście", Boolean.FALSE));
        booleanObjectObservableList.add(new BooleanObject(7, "Przyjazne zwierzętą", Boolean.FALSE));
        booleanObjectObservableList.add(new BooleanObject(8, "Czajnik", Boolean.FALSE));
        booleanObjectObservableList.add(new BooleanObject(9, "Zastawa stołowa", Boolean.FALSE));
        booleanObjectObservableList.add(new BooleanObject(10, "Lampka", Boolean.FALSE));
        
        extraListSelectionView.getSourceItems().addAll(booleanObjectObservableList);
//        for( BooleanObject bO : booleanObjectObservableList) {
//            extraListSelectionView.getSourceItems().add(bO.getName());
//        }

        intList = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numberOfSingleBedsChoiceBox.setItems(intList);
        numberOfDoubleBedsChoiceBox.setItems(intList);
        numberOfExtraBedsChoiceBox.setItems(intList);
    }
    
        @FXML private void addRoom(ActionEvent event) throws SQLException {
            String roomName = roomNameTextField.getText();
            Integer numberOfSingleBeds = numberOfSingleBedsChoiceBox.getValue();
            Integer numberOfDoubleBeds = numberOfDoubleBedsChoiceBox.getValue();
            Integer numberOfExtraBeds = numberOfExtraBedsChoiceBox.getValue();
            Integer floorNumber = Integer.valueOf(floorNumberTextField.getText());
            String building = buildingTextField.getText();
            String smallDescription = smallDescriptionTextField.getText();
            String bigDescription = bigDescriptionTextArea.getText();
            String extraDescription = extraDescriptionTextArea.getText();
            Double priceOfroom = Double.valueOf(priceOfRoomTextField.getText());
            Double priceOfAdult = Double.valueOf(priceOfAdultTextField.getText());
            Double priceOfMinor = Double.valueOf(priceOfMinorTextField.getText());
            ExtraItems eI = new ExtraItems();          
            
            Room room = new Room(roomName, numberOfSingleBeds, numberOfDoubleBeds,
                    numberOfExtraBeds, floorNumber, priceOfroom, priceOfAdult,
                    priceOfMinor, smallDescription, bigDescription, 
                    extraDescription, building, eI);
            model.addRoomToDataBase(room);
        }
}
