/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.dialogs;

import butler.model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Michał
 */
public class addNewDataBaseStructureController extends DialogBox implements Initializable {
    @FXML private Button cancelButton;
    @FXML private TextField dbNameTextField, dbUserTextField;
    @FXML private PasswordField dbPasswordPasswordField;
    @FXML private Label testConnectionResult;
    private Model model;
    
    private ObservableList<String> dbTypes = FXCollections.observableArrayList("JavaDB", "MySQL", "Oracle", "MSSQL");
       
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = butler.Butler.model;
    }
    @FXML
    private void addStructure(ActionEvent event){
        model.loadDriver();
        model.createDataBase(dbNameTextField.getText(), dbUserTextField.getText(), dbPasswordPasswordField.getText());
    }
    
    @FXML private void testConnection(ActionEvent event) {
        if (true) {
            testConnectionResult.setText("UDANE");
            testConnectionResult.setStyle("-fx-background-color: #009900;");
        } else {
            testConnectionResult.setText("NIEUDANE");
            testConnectionResult.setStyle("-fx-background-color: #990000;");            
        }
    }
    

}
