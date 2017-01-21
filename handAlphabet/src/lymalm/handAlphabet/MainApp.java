package lymalm.handAlphabet;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import lymalm.handAlphabet.model.*;
import lymalm.handAlphabet.view.*;

public class MainApp extends Application {

	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Öva upp ditt handalfabet!");
		this.primaryStage.setResizable(false);
		
		showTestView();
	}	

    /**
     * Loads the test view.
     */
    public void showTestView() {
        try {
            // Load layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TestView.fxml"));
            AnchorPane testView = (AnchorPane) loader.load();

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
     * Opens a settings dialog to change difficulty etc. If the user
     * clicks OK, the changes are saved and true is returned.
     * 
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showSettingsDialog(){
    	return true;
    }
    
    /**
     * Opens a High Score dialog where the user can see records for 
     * different difficulties. 
     */
    public void showHighScoreDialog(){
    	
    }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Everything starts here!
     */
	public static void main(String[] args) {
//		launch(args);
		RandomWordGenerator rwg = new RandomWordGenerator();
		System.out.println(rwg.getNewWord());
	}
}
