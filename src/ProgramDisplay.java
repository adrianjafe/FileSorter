package src;

import java.io.File;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

public class ProgramDisplay extends Application {

    private ListView<String> listView;
    private Label currentDirLabel;

    @Override
    public void start(Stage stage){

        // Componentes de la interfaz
        Button loadButton = new Button("📂 Cargar directorio");
        Button exitButton = new Button("🚪 Salir");

        listView = new ListView<>();
        currentDirLabel = new Label("Directorio actual: (ninguno)");

        // Layout botones
        HBox buttonBox = new HBox(10, loadButton, exitButton);
        buttonBox.setPadding(new Insets(10));

        // Layout principal
        VBox mainBox = new VBox(10, currentDirLabel, listView);
        mainBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(buttonBox);
        root.setCenter(mainBox);

        //Acciones de los botones
        loadButton.setOnAction(e -> chooseAndLoadDirectory(stage));
        exitButton.setOnAction(e -> stage.close());



        stage.setTitle("File Sorter");
        stage.setScene(new Scene(root, 1000, 500));
        stage.show();
    }

    private void chooseAndLoadDirectory(Stage stage) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Selecciona un directorio");
        File selectedDir = chooser.showDialog(stage);

        if (selectedDir != null) {
            currentDirLabel.setText("Directorio actual: " + selectedDir.getAbsolutePath());
            loadDirectoryContents(selectedDir);
        }
    }

    private void loadDirectoryContents(File dir) {
        listView.getItems().clear();
        File[] files = dir.listFiles();

        if (files != null) {
            for (File f : files) {
                String prefix = f.isDirectory() ? "[DIR] " : "[FILE] ";
                listView.getItems().add(prefix + f.getName());
            }
        } else {
            listView.getItems().add("(No se pudo leer el directorio)");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
