
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

public class Main extends Application {

    // text field data fields
    public TextField firstNameField = new TextField();
    public TextField lastNameField = new TextField();
    public TextField cityField = new TextField();
    public TextField provinceField = new TextField();
    public TextField postalCodeField = new TextField();

    // file reading and position tracking data
    public Vector<String> lines = new Vector<>();
    public int currentRecord = 0;

    // launches GUI
    public static void main(String[] args) {
        launch(args);
    }

    // return array of current record based on 'currentRecord'
    public String[] getCurRecord() {
        return lines.get(currentRecord).split(";");
    }

    // set all input fields to the 'str' var data
    public void setCurRecord(String[] str) {
        TextField[] arr = {firstNameField, lastNameField, cityField, provinceField, postalCodeField};
        for (int i = 0; i < str.length; i++) {
            arr[i].setText(str[i]);
        }
    }

    // returns the next record based on an incremented 'currentRecord'
    // does not allow going above 'lines' vector size
    public String[] getNextRecord() {
        if (currentRecord != lines.size() - 1) {
            return lines.get(++currentRecord).split(";");
        } else {
            return getCurRecord();
        }
    }

    // returns the previous record based on decremented 'currentRecord'
    // does not allow going below 0
    public String[] getPrevRecord() {
        if (currentRecord != 0) {
            return lines.get(--currentRecord).split(";");
        } else {
            return getCurRecord();
        }
    }

    // loads all lines from the 'addressBook.txt' file into lines vector
    public void readAllLines() {
        String line;
        try {
            RandomAccessFile file = new RandomAccessFile("addressBook.txt", "rw");
            file.seek(0);
            while ((line = file.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // writes all lines in 'lines' vector to 'addressBook.txt' file
    public void writeAllLines() {
        try {
            RandomAccessFile file = new RandomAccessFile("addressBook.txt", "rw");
            file.seek(0);
            for (String line : lines) {
                file.write(line.getBytes(StandardCharsets.UTF_8));
                file.write("\n".getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // returns the current values of the TextField objects
    public String[] getCurrentTextValues() {
        return new String[]{firstNameField.getText(), lastNameField.getText(), cityField.getText(),
                provinceField.getText(), postalCodeField.getText()};
    }

    // the *real* main method
    @Override
    public void start(Stage primaryStage) throws Exception {

        // initialize
        readAllLines();

        // GridPane with some formatting
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(25, 25, 25, 25));

        /* begin label and field creating and addition */

        // First Name
        Label firstNameLabel = new Label("First Name: ");
        gp.add(firstNameLabel, 0, 0);
        gp.add(firstNameField, 1, 0);

        // Last Name
        Label lastNameLabel = new Label("Last Name: ");
        gp.add(lastNameLabel, 0, 1);
        gp.add(lastNameField, 1, 1);

        // City
        Label cityLabel = new Label("City: ");
        gp.add(cityLabel, 0, 2);
        gp.add(cityField, 1, 2);

        // Province
        Label provinceLabel = new Label("Province: ");
        gp.add(provinceLabel, 0, 3);
        gp.add(provinceField, 1, 3);

        // Postal Code
        Label postalCodeLabel = new Label("Postal Code: ");
        gp.add(postalCodeLabel, 0, 4);
        gp.add(postalCodeField, 1, 4);

        /* end label and field creating and addition */

        // create all buttons
        Button addBtn = new Button("Add");
        Button firstBtn = new Button("First");
        Button nextBtn = new Button("Next");
        Button prevBtn = new Button("Previous");
        Button lastBtn = new Button("Last");
        Button updBtn = new Button("Update");

        // initialize all text fields with first record
        setCurRecord(getCurRecord());

        firstBtn.setOnAction(e -> {
            currentRecord = 0;
            setCurRecord(getCurRecord());
        });

        nextBtn.setOnAction(e -> setCurRecord(getNextRecord()));
        prevBtn.setOnAction(e -> setCurRecord(getPrevRecord()));

        lastBtn.setOnAction(e -> {
            currentRecord = lines.size() - 1;
            setCurRecord(getCurRecord());
        });

        addBtn.setOnAction(e -> {
            // add to 'lines' vector then write to file
            lines.add(String.join(";", getCurrentTextValues()));
            writeAllLines();
        });

        updBtn.setOnAction(e -> {
            // alter current record then write to file
            lines.set(currentRecord, String.join(";", getCurrentTextValues()));
            writeAllLines();
        });

        // button positioning
        gp.add(firstBtn, 3, 0);
        gp.add(prevBtn, 3, 1);
        gp.add(nextBtn, 3, 2);
        gp.add(lastBtn, 3, 3);
        gp.add(addBtn, 3, 4);
        gp.add(updBtn, 3, 5);

        // create and add scene to 'primaryStage'
        Scene scene = new Scene(gp);
        primaryStage.setScene(scene);

        // set the title and show the stage
        primaryStage.setTitle("Address Book");
        primaryStage.show();
    }
}
