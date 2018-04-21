/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package butler;

import butler.model.Model;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Michał
 */
public class Butler extends Application {
    public static Model model;
    public static ResourceBundle bundle;
    @Override
    public void start(Stage stage) throws Exception {
        model = new Model();
        Locale.setDefault(new Locale("pl"));
        bundle = ResourceBundle.getBundle("resources.bundles.messages", new Locale("pl"));
        FXMLLoader fXMLLoader = new FXMLLoader(this.getClass().getResource("view/LoginPage.fxml"), bundle);
        Parent root = fXMLLoader.load();
        
        //stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        //scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle(bundle.getString("app.title") + " " + bundle.getString("app.version"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
