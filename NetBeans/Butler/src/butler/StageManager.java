/*
 * Here comes the text of your licensed
 * Each line should be prefixed with  * 
 */
package butler;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author MichalKrzyzanowski
 */
public class StageManager {
    
    private ResourceBundle bundle;
    private FXMLLoader loader;
    private String title;
    
    public StageManager() {
        bundle = ResourceBundle.getBundle("resources.bundles.messages", new Locale("pl"));
        title = "error.undefined";
    }
    
    public void changeStage(Stage stage, String fxml) {
        loader = new FXMLLoader(this.getClass().getResource(fxml), bundle);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println(e);
        }
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
        stage.setScene(scene);
        stage.setTitle(bundle.getString(title));
        stage.show();
    }
    public void changeStage(Stage stage, String fxml, Integer width, Integer height) {
        changeStage(stage, fxml);
        stage.setWidth(width);
        stage.setHeight(height);
    }    
    public void addModalStage(Window owner, String fxml) {
       loader = new FXMLLoader(this.getClass().getResource(fxml), bundle);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println(e);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setTitle(bundle.getString(title));
        stage.show();         
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }
}
