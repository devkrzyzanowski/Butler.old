/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package butler;

import butler.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Michał
 */
public class Butler extends Application {
    public static Model model;
    public static StageManager stageManager;
    public static String USERNAME;

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static void setUSERNAME(String USERNAME) {
        Butler.USERNAME = USERNAME;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        model = new Model();
        stageManager = new StageManager();
        stageManager.changeStage(stage, "view/LoginPage.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
