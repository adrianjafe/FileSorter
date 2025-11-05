package src;

import java.io.File;

import javafx.application.Application; // Ya no lo necesitas, pero lo dejo por si acaso
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

// 1. QUITAMOS "extends Application"
public class FileMoverDisplay { 

    private Label iniDirLabel;
    private Label destDirLabel;
    private File iniDir;
    private File destDir;

    // 2. RENOMBRAMOS "start" a "showWindow" y ya no recibe "Stage" como parámetro
    public void showWindow() {
        // Implementation for FileMoverDisplay

        iniDirLabel = new Label("Directorio inicial: (ninguno)");
        destDirLabel = new Label("Directorio destino: (ninguno)");

        Button browseDirIniButton = new Button("📂...");
        Button browseDirDestButton = new Button("📂...");
        Button acceptButton = new Button("✔ Aceptar");
        Button cancelButton = new Button("✘ Cancelar");

        HBox iniDirBox = new HBox(10, iniDirLabel, browseDirIniButton);
        HBox destDirBox = new HBox(10, destDirLabel, browseDirDestButton);
        HBox actionBox = new HBox(10, acceptButton, cancelButton);

        iniDirBox.setPadding(new Insets(10));
        destDirBox.setPadding(new Insets(10));
        actionBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();

        // --- INICIO DE LA NUEVA LÓGICA DE LAYOUT ---

        // 1. El centro sigue siendo el VBox con los directorios
        VBox ejeVBox = new VBox(20, iniDirBox, destDirBox);
        root.setCenter(ejeVBox);

        // 2. Creamos un contenedor para la parte inferior
        HBox bottomContainer = new HBox();

        // 3. Creamos un "espaciador" que empujará el actionBox hacia la derecha
        Region spacer = new Region();
        // Le decimos que crezca horizontalmente para ocupar todo el espacio disponible
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // 4. Añadimos el espaciador y luego el actionBox al contenedor inferior
        bottomContainer.getChildren().addAll(spacer, actionBox);

        // 5. Asignamos este nuevo contenedor a la parte inferior del BorderPane
        root.setBottom(bottomContainer);


        //CREAMOS EL STAGE MANUALMENTE
        Stage stage = new Stage();
        stage.setTitle("Moving Display");
        stage.setScene(new Scene(root, 600, 300));
        stage.show();

        browseDirIniButton.setOnAction(e -> {
            this.iniDir = new FileScanner().chooseAndLoadDirectory(stage);
            if (this.iniDir != null) {
                iniDirLabel.setText("Directorio inicial: " + this.iniDir.getAbsolutePath());
            }
        });

        browseDirDestButton.setOnAction(e -> {
            this.destDir = new FileScanner().chooseAndLoadDirectory(stage);
            if (this.destDir != null) {
                destDirLabel.setText("Directorio destino: " + this.destDir.getAbsolutePath());
            }
        });

        acceptButton.setOnAction(e -> {
            if (this.iniDir != null && this.destDir != null) {
                new FileScanner().moverArchivo(this.iniDir, this.destDir, null);
                System.out.println("Archivos movidos de " + this.iniDir.getAbsolutePath() + " a " + this.destDir.getAbsolutePath());
                stage.close(); // Cierra esta ventana secundaria
            } else {
                System.out.println("Por favor, selecciona ambos directorios.");
            }
        });

        cancelButton.setOnAction(e -> stage.close());

    }

    // El método main ya no es necesario aquí si esta clase no es la principal
    // public static void main(String[] args) {
    //     launch();
    // }
}