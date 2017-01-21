package lymalm.handAlphabet.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import lymalm.handAlphabet.MainApp;

public class TestViewController {

	@FXML
	private ImageView testImage;
	@FXML
	private Button settingsButton;
	@FXML
	private Button highScoreButton;
	@FXML
	private Button repeatButton;
	@FXML
	private Button correctButton;
	@FXML
	private Label scoreLabel;
	@FXML
	private Label repeatLabel;
	@FXML
	private Label answerLabel;
	@FXML
	private TextField answerField;
	
    // Reference to the main application.
    private MainApp mainApp;
	
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public TestViewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	Thread one = new Thread() {
    	    public void run() {
	            try {
    				Thread.sleep(3000);
	    	    	String[] haSvenska ={"å-1", "å-2", "å-3", "å-4", "å-5", "å-6", "å-7", "å-8", "å-9", "å-10", "å-11", "å-12"};
	    	    	int i = 0;
	    	    	while(true){
	    	    		File file = new File("resources/images/swedish/ha500x500/å/" + haSvenska[i%12] + ".png");
	    	            Image image = new Image(file.toURI().toString());
	    	            testImage.setImage(image);
	    				Thread.sleep(70);
	    				i++;
	    	    	}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
    	    }  
    	};

    	one.start();
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private void handleSettings(){
    	mainApp.showSettingsDialog();
    }
    
    @FXML
    private void handleHighScore(){
    	mainApp.showHighScoreDialog();
    }
    
    @FXML
    private void handleRepeat(){
    	String[] haJapanese = {"a", "i", "u", "e", "o", 
    			"ka", "ki", "ku", "ke", "ko", 
    			"sa", "shi", "su", "se", "so", 
    			"ta", "chi", "tsu", "te", "to", 
    			"ha", "hi", "fu", "he", "ho", 
    			"na", "ni", "nu", "ne", "no", 
    			"ma", "mi", "mu", "me", "mo", 
    			"ya", "yu", "yo", 
    			"ra", "ri", "ru", "re", "ro", 
    			"wa", "wo", "n"};
    	int randomPos = (int)(Math.random()*46);
    	File file = new File("resources/images/japanese/ha500x500/" + haJapanese[randomPos] + ".png");
        Image image = new Image(file.toURI().toString());
        testImage.setImage(image);
    }
    
    @FXML
    private void handleCorrectAnswer(){
    	
    }
    
    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }    
    	
}
