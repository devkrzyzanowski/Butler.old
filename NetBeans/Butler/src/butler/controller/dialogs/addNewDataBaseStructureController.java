/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.dialogs;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Michał
 */
public class addNewDataBaseStructureController implements Initializable {
    @FXML private Button cancelButton;
    @FXML private TextField dbAddressTextField, dbNameTextField, dbPortTextField, dbUserNameTextField;
    @FXML private PasswordField dbPasswordPasswordField;
    @FXML private Label testConnectionResult;
    @FXML private ComboBox dbTypeComboBox;
    
    private ObservableList<String> dbTypes = FXCollections.observableArrayList("JavaDB", "MySQL", "Oracle", "MSSQL");
       
    private double xOffset = 0;
    private double yOffset = 0;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbTypeComboBox.setItems(dbTypes);
    
    }
    @FXML
    private void addStructure(ActionEvent event){

    }
    
    @FXML private void testConnection(ActionEvent event) {
        if (butler.Butler.model.tryConnectionToDataBase(dbAddressTextField.getText(), dbPortTextField.getText(), dbNameTextField.getText(), dbUserNameTextField.getText(), dbPasswordPasswordField.getText())) {
            testConnectionResult.setText("UDANE");
            testConnectionResult.setStyle("-fx-background-color: #009900;");
        } else {
            testConnectionResult.setText("NIEUDANE");
            testConnectionResult.setStyle("-fx-background-color: #990000;");            
        }
    }
    
    @FXML
    private void cancelAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML private void onMouseDragging(MouseEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }
    @FXML private void onMousePressed(MouseEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }
}
