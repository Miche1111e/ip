package ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import cheryl.Cheryl;
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

    private Cheryl cheryl;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image cherylImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Cheyrl instance */
    public void setCheryl(Cheryl c) {
        this.cheryl = c;

        Platform.runLater(() -> {
            String welcome = cheryl.getWelcome();
            dialogContainer.getChildren().add(
                    DialogBox.getCherylDialog(welcome, cherylImage)
            );
        });
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Cheryl's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.trim().isEmpty()) {
            // ignore empty input
            userInput.clear();
            return;
        }

        String response = cheryl.getResponse(input.trim());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input.trim(), userImage),
                DialogBox.getCherylDialog(response, cherylImage)
        );
        userInput.clear();
    }
}

