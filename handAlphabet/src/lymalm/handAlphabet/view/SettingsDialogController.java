package lymalm.handAlphabet.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import lymalm.handAlphabet.MainApp;
import lymalm.handAlphabet.model.*;

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
	private Language language;
	private Difficulty difficulty;
	
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }
    
    /**
     * Sets up language descriptions for every language in the language
     * choice box.
     */
    private void setUpLanguageChoiceBox(){
    	languageChoiceBox.getItems().removeAll(languageChoiceBox.getItems());
    	languageChoiceBox.getItems().addAll(Language.getDescriptionList());
    	language = mainApp.getCurrentLanguage();
    	if(language == null)
    		language = Language.Swedish;
    	languageChoiceBox.getSelectionModel().select(Language.getDescription(language));
    }

    /**
     * Sets up the choice box with different difficulties.
     */
    private void setUpDifficultyChoiceBox(){
    	difficultyChoiceBox.getItems().removeAll(difficultyChoiceBox.getItems());
    	difficultyChoiceBox.getItems().addAll(Difficulty.getDescriptionList(language));
    	difficulty = mainApp.getCurrentDifficulty();
    	if(difficulty == null)
    		difficulty = Difficulty.Easy;
    	difficultyChoiceBox.getSelectionModel().select(Difficulty.getDescription(language, difficulty));
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
    	setUpLanguageChoiceBox();
    	setUpDifficultyChoiceBox();
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
    	// Transfer selected language and difficulty to main app.
    	int langIndex = languageChoiceBox.getSelectionModel().getSelectedIndex();
    	int diffIndex = difficultyChoiceBox.getSelectionModel().getSelectedIndex();
    	mainApp.setCurrentLanguage(Language.values()[langIndex]);
    	mainApp.setCurrentDifficulty(Difficulty.values()[diffIndex]);
    	
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
