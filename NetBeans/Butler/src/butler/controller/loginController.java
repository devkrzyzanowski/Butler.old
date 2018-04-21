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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    @FXML private void addDataBaseStructure(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/butler/view/dialogs/addNewDataBaseStructure.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML private void handleLoginButton(ActionEvent event) throws IOException {
                   butler.Butler.model = new Model();
        try {
                if (butler.Butler.model.connectToDataBase(loginTextField.getText(), passwordTextField.getText())) {
                    setPage(event, "ClientBasePage");
                } else {
                    System.out.println("Error login");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Błąd połączenia z bazą danych");
            alert.showAndWait();
                }
                //TODO init
            } catch (SQLException ex) {
                Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        @FXML void showRegulations() {

    }  
    private void setPage(ActionEvent event, String path) throws IOException{
        ResourceBundle bundle = ResourceBundle.getBundle("resources.bundles.messages");
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/butler/view/"+path+".fxml"), bundle);

        Parent root = fXMLLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
