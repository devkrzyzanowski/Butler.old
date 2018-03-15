/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller;

import butler.model.Model;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author uwxyy
 */
public class loginController {

    @FXML private Button exitButton;
    @FXML private Button minimizeButton;
    @FXML private TextField loginTextField;
    @FXML private PasswordField passwordTextField;

    
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    @FXML private void setScreenToRegisterPage(ActionEvent event) throws IOException{
        setPage(event, "RegisterPage");
    }    
    @FXML private void handleExitButton() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    @FXML private void handleMinimizeButton(ActionEvent event) {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }
    
    @FXML private void handleLoginButton(ActionEvent event) throws IOException {
                   butler.Butler.model = new Model();
        try {
                if (butler.Butler.model.connectToDataBase(loginTextField.getText(), passwordTextField.getText())) {
                    setPage(event, "ClientBasePage");
                } else {
                    System.out.println("Error login");
                }
                //TODO init
            } catch (SQLException ex) {
                Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        @FXML void showRegulations() {

    }  
    private void setPage(ActionEvent event, String path) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/butler/view/"+path+".fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
