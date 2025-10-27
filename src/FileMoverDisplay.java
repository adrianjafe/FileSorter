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

public class FileMoverDisplay extends Application{

    private Label iniDirLabel;
    private Label destDirLabel;

    @Override
    public void start(Stage stage){
        // Implementation for FileMoverDisplay

        iniDirLabel = new Label("Directorio inicial: (ninguno)");
        destDirLabel = new Label("Directorio destino: (ninguno)");

        Button browseDirIniButton = new Button("📂...");
        Button browseDirDestButton = new Button("📂...");

        HBox iniDirBox = new HBox(10, iniDirLabel, browseDirIniButton);
        HBox destDirBox = new HBox(10, destDirLabel, browseDirDestButton);

        iniDirBox.setPadding(new Insets(10));
        destDirBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(iniDirBox);
        root.setCenter(destDirBox);

        VBox ejeVBox = new VBox(20, iniDirBox, destDirBox);
        root.setCenter(ejeVBox);

        stage.setTitle("Moving Display");
        stage.setScene(new Scene(root, 600, 300));
        stage.show();

        //Poner que iniDirLabel sea el directorio en el que esta o que ponga la opción de elegir directorio
        //Poner que destDirLabel se pueda poner la opción de elegir directorio
        //Poner la opcion de aceptar o cancelar


    }

    public static void main(String[] args) {
        launch();
    }
}
