/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller;

import JFXion.IonSchedule;
import butler.model.Model;
import butler.utils.Booking;
import butler.utils.Legend;
import butler.utils.LegendCell;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;


/**
 *
 * @author Michał
 */
public class BookingScheduleController implements Initializable {

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
        bundle = ResourceBundle.getBundle("resources.bundles.messages");
        model = butler.Butler.model;
        legendGridPane = new GridPane();
        legendPane.getChildren().add(legendGridPane);
        int i = 0;
        for (Legend l : model.getLegendList()) {
            legendGridPane.addRow(i, new LegendCell(l.getLegendName(), Color.web(l.getColor())));
            i++;
        }
 
        ionSchedule = new IonSchedule(model.getRoomList(), model.getBookingList());
        ionSchedule.setLayoutX(0);
        ionSchedule.setLayoutY(6);
        ionSchedule.setMinSize(960, 690);
        ionSchedule.setMaxSize(960, 690);
        AnchorPane.setBottomAnchor(ionSchedule, 0d);
        AnchorPane.setTopAnchor(ionSchedule, 0d);
        AnchorPane.setLeftAnchor(ionSchedule, 0d);
        AnchorPane.setRightAnchor(ionSchedule, 0d);
        ionScheduleBox.getChildren().add(ionSchedule);

    }
    @FXML private void addReservation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/butler/view/dialogs/addReservationDialog.fxml"));
        Scene scene = new Scene(root);
        //scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(addReservationButton.getScene().getWindow());
        stage.setTitle("Dodaj rezerwację");
        stage.show();
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
