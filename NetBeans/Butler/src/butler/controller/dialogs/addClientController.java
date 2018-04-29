/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.dialogs;

import butler.model.Model;
import butler.utils.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Michał
 */
public class addClientController extends DialogBox implements Initializable {
    @FXML private Button addClientButton;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField cityTextField;
    @FXML private TextField streetTextField;
    @FXML private TextField homeNumberTextField;
    @FXML private TextField flatNumberTextField;
    @FXML private TextField zipCodeTextField;
    @FXML private TextField contactPhoneNumberTextField;
    @FXML private TextField emailTextField;
    private Model model;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = butler.Butler.model;
    }
    
    public Client createClient(){
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String city = cityTextField.getText();
        String street = streetTextField.getText();
        Integer homeNumber = Integer.valueOf(homeNumberTextField.getText());
        Integer flatNumber = Integer.valueOf(flatNumberTextField.getText());
        Integer zipCode = Integer.valueOf(zipCodeTextField.getText());
        Integer contactPhoneNumber = Integer.valueOf(contactPhoneNumberTextField.getText());
        String email = emailTextField.getText();
        Client c = new Client(firstName, lastName, city, street, homeNumber, flatNumber, zipCode, contactPhoneNumber, email);
        model.addClientToDataBase(c);
        return c;
    }
    
        public void close(){
        Stage stage = (Stage) addClientButton.getScene().getWindow();
        stage.close();        
    }
    
    @FXML private void addClient(ActionEvent event) {
        Client c = createClient();
        model.addClientToDataBase(c);
        cancelAction(event);
    }
    
    public Button getAddButton(){
        return addClientButton;
    }
    
}
