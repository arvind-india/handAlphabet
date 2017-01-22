package lymalm.handAlphabet.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lymalm.handAlphabet.MainApp;

public class SettingsDialogController {

	@FXML
	private Label languageLabel;
	@FXML
	private Label difficultyLabel;
	@FXML
	private ChoiceBox<String> languageChoiceBox;
	@FXML 
	private ChoiceBox<String> difficultyChoiceBox; 
	@FXML
	private Button okButton;
	@FXML
	private Button cancelButton;

    private Stage dialogStage;
	private boolean okClicked = false;
	
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	languageChoiceBox.getItems().removeAll(languageChoiceBox.getItems());
    	languageChoiceBox.getItems().addAll("Svenskt teckenspråk", "JSL - 日本手話");
    	languageChoiceBox.getSelectionModel().select(0);

    	difficultyChoiceBox.getItems().removeAll(difficultyChoiceBox.getItems());
    	difficultyChoiceBox.getItems().addAll("Lätt", "Medel", "Svårt");
    	difficultyChoiceBox.getSelectionModel().select(0);
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
    	okClicked = true;
    	dialogStage.close();
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
    	dialogStage.close();
    }

}
