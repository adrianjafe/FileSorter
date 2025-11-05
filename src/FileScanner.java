package src;

import java.io.File;
import java.nio.file.Files;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.FileReader;

public class FileScanner {

    // -------------------------------------------------Main-----------------------------------------------------------//
    public static void main(String[] args) {
        String path = "./src/prueba"; //Ruta que se puede cambiar desde la app.
        File folder = getFile(path);
        System.out.println(folder.isDirectory());
        for (File file : folder.listFiles()) {
            System.out.println(file.getName() + " - " + file.length() / 1024 + " KB");
        }
        
        organizarPorTipo(folder);
    }

    public static JSONArray leerJSON(String tipo) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("./src/res/extensiones.json"));
            JSONArray lista = (JSONArray) jsonObject.get(tipo);
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File getFile(String path) {
        //File folder = new File(path);
        System.out.println("Obteniendo archivo o carpeta en: " + new File(path).isDirectory());
        return new File(path);
    }

    public File chooseAndLoadDirectory(Stage stage) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Selecciona un directorio");
        File selectedDir = chooser.showDialog(stage);

        if (selectedDir != null) {
            return selectedDir;
        }
        return null;
    }

    // Método para mover archivos a una carpeta de destino
    public static void moverArchivo(File file, File destino,String destinoStr) {
        if (destinoStr == null) {
            destinoStr = destino.getAbsolutePath();
        }
        File destinoFolder = getFile(destinoStr);
        if (destinoFolder.exists() == false) {
            destinoFolder.mkdirs();
        }
        System.out.println("Moviendo " + file + " a " + destinoFolder);
        try {
            Files.move(file.toPath(), destinoFolder.toPath().resolve(file.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------------- Metodos de organización
    // -------------------------------//
    // //
    // Método para organizar archivos por tipo //
    public static void organizarPorTipo(File folder) {
        for (File file : folder.listFiles()) {
            if (isImagen(file)) {
                moverArchivo(file, null,"./src/Imagen");
            } else if (isAudio(file)) {
                moverArchivo(file, null,"./src/Audio");
            } else if (isVideo(file)) {
                moverArchivo(file, null,    "./src/Video");
            } else if (isDocumento(file)) {
                moverArchivo(file, null,"./src/Documento");
                System.out.println("Documento: " + file.getName() + " movido.");
            }
        }
    }

    // //
    // ---------------------------------------------------------------------------------//

    // ----------------------------- Metodos de verificación
    // -------------------------------//
    // //
    // Método para verificar si un archivo es una imagen

    // To Do: Hacer JSON con extensiones y que lo recorra
    public static boolean isImagen(File file) {
        String name = file.getName().toLowerCase();
        JSONArray extension = leerJSON("Imagen");
        for (Object obj : extension) {
            if (name.endsWith((String) obj)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDocumento(File file) {
        String name = file.getName().toLowerCase();
        JSONArray extension = leerJSON("Documento");
        for (Object obj : extension) {
            if (name.endsWith((String) obj)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isVideo(File file) {
        String name = file.getName().toLowerCase();
        JSONArray extension = leerJSON("Video");
        for (Object obj : extension) {
            if (name.endsWith((String) obj)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAudio(File file) {
        String name = file.getName().toLowerCase();
        JSONArray extension = leerJSON("Audio");
        for (Object obj : extension) {
            if (name.endsWith((String) obj)) {
                return true;
            }
        }
        return false;
    }

    // //
    // ---------------------------------------------------------------------------------//
}