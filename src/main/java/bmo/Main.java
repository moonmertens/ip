package bmo;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The GUI entry point for the application.
 */
public class Main extends Application {

    private Bmo bmo = new Bmo("bmo_data.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("BMO");
            fxmlLoader.<MainWindow>getController().setBmo(bmo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
