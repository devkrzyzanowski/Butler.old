/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.fragments;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Michał
 */
public class FooterController implements Initializable {
    
    @FXML Label nameOfConnectedDB, nameOfUserDB;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameOfConnectedDB.setText(butler.Butler.model.getDataBaseName());
        nameOfUserDB.setText(butler.Butler.USERNAME);
    }
    
}
