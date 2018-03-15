/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.fragments;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Michał
 */
public class MenuBarController implements Initializable {
    
    private double xOffset = 0;
    private double yOffset = 0;
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
        @FXML private void onMouseDragged(MouseEvent event){
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
