package lymalm.handAlphabet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import lymalm.handAlphabet.model.*;
import lymalm.handAlphabet.view.*;

public class MainApp extends Application {

	private Stage primaryStage;
	
	private Language language;
	private Difficulty difficulty;
	private String cfgFilePath = "handAlphabet.cfg";
	
	@Override
	public void start(Stage primaryStage){
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("HA");
		this.primaryStage.setResizable(false);

		loadConfigurationFile();
		if(language == null || difficulty == null){
			boolean clickedOk = showSettingsDialog();
			if(!clickedOk)
				return;	// Don't show main program (exits).
			else
				saveSettings();
		}
		showTestView();
	}

	/**
	 * Loads configurations from the configuration file and creates it if 
	 * it doesn't exist. 
	 */
	private void loadConfigurationFile(){
    	InputStream input = null;
		Properties prop = new Properties();

    	try{
    		(new File(cfgFilePath)).createNewFile();
    		input = new FileInputStream(cfgFilePath);
    		
			prop.load(input);
			try{
				language = Language.valueOf(prop.getProperty("language"));
			}catch(IllegalArgumentException | NullPointerException e){
				language = null;	
			}
			try{
				difficulty = Difficulty.valueOf(prop.getProperty("difficulty"));
			}catch(IllegalArgumentException | NullPointerException e){
				difficulty = null;
			}
    	}catch(IOException ex){
    		System.out.println("The file could not be opened.");
    		ex.printStackTrace();
        }finally{
        	if(input!=null){
        		try{
        			input.close();
        		}catch(IOException e){
        			e.printStackTrace();
        		}
        	}
        }
	}

    /**
     * Opens a settings dialog to change difficulty etc. If the user
     * clicks OK, the changes are saved and true is returned.
     * 
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showSettingsDialog(){
    	try {
            // Load layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SettingsDialog.fxml"));
            AnchorPane settingsDialog = (AnchorPane)loader.load();

            // Show the scene.
            Stage settingsStage = new Stage();
            Scene scene = new Scene(settingsDialog);
            settingsStage.setScene(scene);

            // Give the controller access to the main app.
            SettingsDialogController controller = loader.getController();
            controller.setDialogStage(settingsStage);
            controller.setMainApp(this);

            settingsStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Loads the test view.
     */
    public void showTestView(){
        try {
            // Load layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TestView.fxml"));
            AnchorPane testView = (AnchorPane)loader.load();

            // Show the scene.
            Scene scene = new Scene(testView);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            TestViewController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a High Score dialog where the user can see records for 
     * different difficulties. 
     */
    public void showHighScoreDialog(){
    	
    }
    
    /**
     * Save settings to configuration file.
     */
    public void saveSettings(){
    	OutputStream output = null;
    	Properties prop = new Properties();
    	
    	try{
    		output = new FileOutputStream(cfgFilePath);

    		// set the properties value
    		prop.setProperty("language", language.toString());
    		prop.setProperty("difficulty", difficulty.toString());

    		// save properties to project root folder
    		prop.store(output, null);

    	}catch(IOException io){
    		System.out.println("The file could not be opened.");
    		io.printStackTrace();
    	}finally{
    		if(output != null){
    			try{
    				output.close();
    			}catch(IOException e){
    				e.printStackTrace();
    			}
    		}

    	}
    }
    
    /**
     * @return the main stage.
     */
    public Stage getPrimaryStage(){
        return primaryStage;
    }
    
    /**
     * @return current language.
     */
    public Language getCurrentLanguage(){
    	return language;
    }

    /**
     * Sets current language.
     */
    public void setCurrentLanguage(Language lang){
    	language = lang;
    }

    /**
     * @return current difficulty.
     */
    public Difficulty getCurrentDifficulty(){
    	return difficulty;
    }

    /**
     * Sets current difficulty.
     */
    public void setCurrentDifficulty(Difficulty diff){
    	difficulty = diff;
    }
    
    /**
     * Everything starts here!
     */
	public static void main(String[] args){
		launch(args);
	}
}
