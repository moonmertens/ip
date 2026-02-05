package bmo;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Bmo bmo;

    private Image userImage;
    private Image bmoImage;

    /**
     * Initializes the controller class.
     * This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        
        // Load images safely
        try {
            userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
        } catch (NullPointerException e) {
            // Image not found, handling logic if necessary, or simply continue with null
            System.err.println("User.png not found in /images/");
        }
        
        try {
            bmoImage = new Image(this.getClass().getResourceAsStream("/images/Bmo.png"));
        } catch (NullPointerException e) {
            System.err.println("Bmo.png not found in /images/");
        }
    }

    /**
     * Sets the Bmo instance for the controller.
     *
     * @param b The Bmo instance.
     */
    public void setBmo(Bmo b) {
        bmo = b;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bmo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBmoDialog(response, bmoImage)
        );
        userInput.clear();

        if (bmo.isExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
