/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.fragments;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Michał
 */
public class ToolBarController implements Initializable {

    @FXML Button bookingScheduleButton, clientBaseButton, listOfRoomsButton,
            statisticsButton, priceOfRoomsButton, settingsButton,
            institutionButton, operationHistoryButton, sendBugButton;
    
    private ArrayList<Button> buttons;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new ArrayList<>();
        buttons.add(bookingScheduleButton);
        buttons.add(clientBaseButton);
        buttons.add(listOfRoomsButton);
        buttons.add(statisticsButton);
        buttons.add(priceOfRoomsButton);
        buttons.add(settingsButton);
        buttons.add(institutionButton);
        buttons.add(operationHistoryButton);
        buttons.add(sendBugButton);
        
        buttons.stream().map((b) -> {
            b.setOnMouseEntered((MouseEvent event) -> {
                b.setStyle("-fx-background-color: #999999;"
                        + "-fx-background-radius: 0;");
            });
            return b;
        }).forEachOrdered((b) -> {
            b.setOnMouseExited((MouseEvent event) -> {
                b.setStyle("-fx-background-color: #555555;"
                        + "-fx-background-radius: 0;");
            });
        });
    }
    
    @FXML private void setScreenToBookingSchedule(ActionEvent event) {
        setPage(event, "BookingSchedulePage");
    }
    @FXML private void setScreenToClientBase(ActionEvent event) {
        setPage(event, "ClientBasePage");
    }    
    @FXML private void setScreenToListOfRooms(ActionEvent event) throws SQLException{
        setPage(event, "listOfRoomsPage");
    }    
    @FXML private void setScreenToStatistics(ActionEvent event) {
        setPage(event, "StatisticsPage");
    }
    @FXML private void setScreenToPriceOfRooms(ActionEvent event) {
        setPage(event, "PriceOfRoomsPage");
    }
    @FXML private void setScreenToSettings(ActionEvent event) {
        setPage(event, "StatisticsPage");
    }
    @FXML private void setScreenToInstitution(ActionEvent event) {
        setPage(event, "StatisticsPage");
    }    
    @FXML private void setScreenToOperationHistory(ActionEvent event) throws SQLException{
        setPage(event, "OperationHistoryPage");
    }
    
    private void setPage(ActionEvent event, String path) {
        butler.Butler.stageManager.changeStage((Stage) ((Node) event.getSource())
            .getScene().getWindow(), "/butler/view/"+path+".fxml");
    }  
}
