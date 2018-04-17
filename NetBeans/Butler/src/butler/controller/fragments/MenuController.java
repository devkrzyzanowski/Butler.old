/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller.fragments;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Michał
 */
public class MenuController implements Initializable {

    @FXML
    Button bookingScheduleButton, clientBaseButton, listOfRoomsButton,
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
    }
    
    @FXML private void setScreenToBookingSchedule(ActionEvent event) throws IOException{
        setPage(event, "BookingSchedulePage", 0);
    }
    @FXML private void setScreenToClientBase(ActionEvent event) throws IOException{
        setPage(event, "ClientBasePage", 1);
    }    
    @FXML private void setScreenToListOfRooms(ActionEvent event) throws IOException, SQLException{
        setPage(event, "listOfRoomsPage", 2);
    }    
    @FXML private void setScreenToStatistics(ActionEvent event) throws IOException{
        setPage(event, "StatisticsPage", 3);
    }
    @FXML private void setScreenToPriceOfRooms(ActionEvent event) throws IOException{
        setPage(event, "StatisticsPage", 4);
    }
    @FXML private void setScreenToSettings(ActionEvent event) throws IOException{
        setPage(event, "StatisticsPage", 5);
    }
    @FXML private void setScreenToInstitution(ActionEvent event) throws IOException{
        setPage(event, "StatisticsPage", 6);
    }    
    @FXML private void setScreenToOperationHistory(ActionEvent event) throws IOException, SQLException{
        setPage(event, "OperationHistoryPage", 7);
    }
    
    @FXML private void onMouseEntered(MouseEvent event) {
       
    }
        
    
    private void setSelected(Integer index){
        for (Button b : buttons){
            b.setStyle("-fx-background-color: #555555;"
                    + "-fx-background-radius: 0;");
        }
        buttons.get(index).setStyle("-fx-background-color: #333333;"
                    + "-fx-background-radius: 0;");
    }
    
    private void setPage(ActionEvent event, String path, Integer index) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/butler/view/"+path+".fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        setSelected(index);
        stage.show();
        
    }
    
}
