
import java.io.BufferedReader;
import java.io.FileReader;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NameRankApp extends Application {

    private TextField yearField = new TextField();
    private TextField genderField = new TextField();
    private TextField nameField = new TextField();
    private Text resultText = new Text("");

    public static void main(String[] args) {
        launch(args);
    }

    private void results() {

        String year = yearField.getText();
        String filename = String.format("namedata%s.txt", year);

        String gender = (genderField.getText().equals("M") ? "boy" : "girl");

        String name = nameField.getText().toLowerCase();
        String foundResult = "name not found!";

        boolean error = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.toLowerCase().contains(name)) {
                    foundResult = line;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            error = true;
        }

        String rank = foundResult.substring(0, 3);
        String result = String.format("%s name %s is ranked #%s in %s year", gender, name, rank, year);
        if (error) {
            result = "an error occurred!";
        }
        resultText.setText(result);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        /** START QUERY PANE */

        GridPane queryPane = new GridPane();
        queryPane.setHgap(10);
        queryPane.setVgap(10);
        queryPane.setPadding(new Insets(25, 25, 25, 25));

        Label yearLabel = new Label("Enter the Year:");
        queryPane.add(yearLabel, 0, 0);
        queryPane.add(yearField, 1, 0);

        Label genderLabel = new Label("Enter the Gender:");
        queryPane.add(genderLabel, 0, 1);
        queryPane.add(genderField, 1, 1);

        Label nameLabel = new Label("Enter the Name:");
        queryPane.add(nameLabel, 0, 2);
        queryPane.add(nameField, 1, 2);

        Button submitQueryBtn = new Button("Submit Query");
        queryPane.add(submitQueryBtn, 0, 3);

        Button exitBtn = new Button("Exit");
        queryPane.add(exitBtn, 1, 3);

        /** END QUERY PANE */

        /** START RESULT PANE */

        GridPane resultPane = new GridPane();
        resultPane.setHgap(10);
        resultPane.setVgap(10);
        resultPane.setPadding(new Insets(25, 25, 25, 25));

        resultPane.add(resultText, 0, 0);
        Text searchAgainPrompt = new Text("Do you want to search for another name?");
        resultPane.add(searchAgainPrompt, 0, 1);
        Button yesBtn = new Button("Yes");
        resultPane.add(yesBtn, 0, 2);
        Button noBtn = new Button("No");
        resultPane.add(noBtn, 1, 2);

        Stage resultStage = new Stage();
        Scene resultScene = new Scene(resultPane);
        resultStage.setScene(resultScene);
        resultStage.setTitle("Results");

        /** END RESULT PANE */

        /** START QUERY PANE BUTTONS */

        submitQueryBtn.setOnAction(e -> {
            results();
            resultStage.show();
        });

        exitBtn.setOnAction(e -> {
            System.exit(0);
        });

        /** END QUERY PANE BUTTONS */

        /** START RESULTS PANE BUTTONS */

        yesBtn.setOnAction(e -> {
            resultStage.hide();
        });

        noBtn.setOnAction(e -> {
            System.exit(0);
        });

        /** END RESULTS PANE BUTTONS */

        Scene queryScene = new Scene(queryPane);
        primaryStage.setScene(queryScene);
        primaryStage.setTitle("Search Name Ranking Application");
        primaryStage.show();
    }
}
