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
        Button moverButton = new Button("-> Mover archivos");
        Button clearButton = new Button("X Limpiar");
        Button exitButton = new Button("🚪 Salir");
        

        listView = new ListView<>();
        currentDirLabel = new Label("Directorio actual: (ninguno)");

        // Layout botones
        VBox buttonBox = new VBox(10, loadButton, moverButton, clearButton, exitButton);
        buttonBox.setPadding(new Insets(10));

        // Layout pantalla directorios
        VBox mainBox = new VBox(10, currentDirLabel, listView);
        mainBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setRight(buttonBox);
        root.setCenter(mainBox);

        //Acciones de los botones
        loadButton.setOnAction(e -> chooseAndLoadDirectory(stage));
        moverButton.setOnAction(e -> new FileMoverDisplay().start(new Stage()));
        exitButton.setOnAction(e -> stage.close());
        clearButton.setOnAction(e -> clearDirectoryContents(null));
        
        

        stage.setTitle("File Sorter");
        stage.setScene(new Scene(root, 750, 400));
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
        clearDirectoryContents(dir);
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

    private void clearDirectoryContents(File dir) {
        if (dir != null) {
            currentDirLabel.setText("Directorio actual: " + dir.getAbsolutePath());
        } else {
            listView.getItems().clear();
            currentDirLabel.setText("Directorio actual: (ninguno)");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
