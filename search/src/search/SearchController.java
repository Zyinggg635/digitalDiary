/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package search;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static javafx.application.Application.launch;

public class SearchController {

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> resultsListView;

private final String filePath = getClass().getResource("/search/entry.csv").getPath();

    // Handle the Search button click
    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().trim();

        if (keyword.isEmpty()) {
            showAlert("Error", "Please enter a keyword to search.", Alert.AlertType.ERROR);
            return;
        }

        List<String> results = searchEntries(keyword);

        if (results.isEmpty()) {
            results.add("No matching entries found.");
        }

        resultsListView.getItems().setAll(results);
    }

    // Handle the Back button click
    @FXML
    private void handleBack() {
        searchField.getScene().getWindow().hide();
    }

    // Search for entries in the CSV file matching the keyword
    private List<String> searchEntries(String keyword) {
        List<String> results = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            showAlert("Error", "Diary entries file not found.", Alert.AlertType.ERROR);
            return results;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            reader.readLine(); // Skip the header line
            while ((line = reader.readLine()) != null) {
                String[] entryDetails = line.split(",", 4);
                if (entryDetails.length == 4) {
                    String title = entryDetails[0];
                    String date = entryDetails[1];
                    String mood = entryDetails[2];
                    String content = entryDetails[3];

                    if (title.toLowerCase().contains(keyword.toLowerCase()) ||
                        content.toLowerCase().contains(keyword.toLowerCase())) {
                        results.add(String.format("Date: %s\nTitle: %s\nMood: %s\nContent: %s",
                                date, title, mood, content));
                    }
                }
            }
        } catch (IOException e) {
            showAlert("Error", "Failed to read the diary entries.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

        return results;
    }

    // Utility method to show alert dialogs
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


private String getFilePath() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Diary Entries File");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
    File selectedFile = fileChooser.showOpenDialog(null);

    if (selectedFile != null) {
        System.out.println("File Path: " + selectedFile.getAbsolutePath());
        return selectedFile.getAbsolutePath();
    }
    return null;
}


    
}
