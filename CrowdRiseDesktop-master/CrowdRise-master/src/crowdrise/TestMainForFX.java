package crowdrise;/**
 * Created by alaa on 09/02/16.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TestMainForFX extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Solutions");

        initRootLayout();
        showSolutionOverview();
    }

    public void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TestMainForFX.class.getResource("/gui/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            //show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSolutionOverview(){
        //load Solution interfrace
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TestMainForFX.class.getResource("/gui/Solution.fxml"));
            AnchorPane solutionOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(solutionOverview);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
