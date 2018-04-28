/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller;

import JFXion.IonSchedule;
import static butler.Butler.model;
import butler.model.Model;
import butler.utils.Legend;
import butler.utils.LegendCell;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author MichalKrzyzanowski
 */
public class PriceOfRoomsController implements Initializable {
    @FXML private AnchorPane main;
    @FXML private Pane legendPane;
    @FXML private ScrollPane legendScrollPane;
    @FXML private Button addReservationButton, removeReservationButton, modifyReservationButton;
    @FXML private Button selectClientButton, refreshButton;
    private GridPane legendGridPane;
    
    @FXML private AnchorPane ionScheduleBox;
    private Model model;
    private IonSchedule ionSchedule;
    @FXML ResourceBundle bundle;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       model = butler.Butler.model;
        legendGridPane = new GridPane();
        legendPane.getChildren().add(legendGridPane);
        int i = 0;
        for (Legend l : model.getLegendList()) {
            legendGridPane.addRow(i, new LegendCell(l.getLegendName(), Color.web(l.getColor())));
            i++;
        }
        ionSchedule = new IonSchedule(model.getRoomList(), model.getBookingList());
        AnchorPane.setBottomAnchor(ionSchedule, 0d);
        AnchorPane.setTopAnchor(ionSchedule, 0d);
        AnchorPane.setLeftAnchor(ionSchedule, 0d);
        AnchorPane.setRightAnchor(ionSchedule, 0d);
        ionScheduleBox.getChildren().add(ionSchedule);        
    }
    
    @FXML private void addReservation(ActionEvent event) {
        butler.Butler.stageManager.addModalStage("/butler/view/dialogs/addReservationDialog.fxml",
            ((Node) event.getSource()).getScene().getWindow());
        
    }
    
    @FXML private void removeReservation(ActionEvent event) {
    }
    
    @FXML private void modifyReservation(ActionEvent event) {

    }
    
    @FXML private void refreshData(ActionEvent event) {
            refresh();
        }
        
    public void refresh(){
            ionSchedule.update();
        }
}
