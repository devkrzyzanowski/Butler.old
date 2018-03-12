/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler.controller;

import butler.model.Model;
import butler.utils.OperationHistory;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Michał
 */
public class historyController implements Initializable {
    @FXML private TableView<OperationHistory> operationTableView;
    @FXML private TableColumn<OperationHistory, String> operation, user;
    @FXML private TableColumn<OperationHistory, String> time, date;
    Model model;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       model = new Model();
       model.addToOperationHistory("testowanie histori operacji", "adminik");
        try {
            operationTableView.setItems(model.getOperationHistoryList());
        } catch (SQLException ex) {
            Logger.getLogger(historyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        operation.setCellValueFactory(new PropertyValueFactory<>("operation"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        user.setCellValueFactory(new PropertyValueFactory<>("user"));
    } 
    
    

        
}
