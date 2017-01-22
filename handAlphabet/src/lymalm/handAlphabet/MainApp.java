package lymalm.handAlphabet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
	
	private String language;
	private String difficulty;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Öva upp ditt handalfabet!");
		this.primaryStage.setResizable(false);

		loadConfigurationFile();
		if(language == null || difficulty == null){
			boolean clickedOK = showSettingsDialog();
			if(!clickedOK)
				return;
		}
		showTestView();
	}	

	/**
	 * Loads configurations from the configuration file and creates it if 
	 * it doesn't exist. 
	 */
	private void loadConfigurationFile(){
		String cfgFilePath = "handAlphabet.cfg";
    	InputStream input = null;
		Properties prop = new Properties();

    	try {
    		new File("src/lymalm/handAlphabet/" + cfgFilePath).createNewFile();
    		input = MainApp.class.getResourceAsStream(cfgFilePath);
    		if(input == null)
	            System.out.println("Reload to update existence of file.");
    		else{
    			prop.load(input);
    			language = prop.getProperty("language");
    			difficulty = prop.getProperty("difficulty");
    		}
    	} catch(IOException ex) {
    		ex.printStackTrace();
        } finally {
        	if(input!=null){
        		try {
        			input.close();
        		} catch(IOException e) {
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

            settingsStage.showAndWait();;
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Loads the test view.
     */
    public void showTestView() {
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
     * @return the main stage.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Everything starts here!
     */
	public static void main(String[] args) {
		launch(args);
	}
}
