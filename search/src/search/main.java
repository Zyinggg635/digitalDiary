/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package search;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Alert;

/**
 *
 * @author Grace
 */
public class main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       try {
           

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/search/search.fxml"));

        Scene searchScene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Search Diary Entries");
        stage.setScene(searchScene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Failed to load the search interface.");
        alert.showAndWait();
    }}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
