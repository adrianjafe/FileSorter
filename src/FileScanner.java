package src;
import java.io.File;
import java.nio.file.Files;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;


public class FileScanner {

    //-------------------------------------------------Main-----------------------------------------------------------//
    public static void main(String[] args) {
        String path = "./prueba";
        File folder = getFile(path);
        for( File file : folder.listFiles()){
            System.out.println(file.getName() + " - " + file.length()/1024 + " KB");
        }
    }

    public JSONArray leerJSON(String tipo){
        try{
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("./res/extensiones.json"));
            JSONArray lista = (JSONArray) jsonObject.get(tipo);
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File getFile(String path){
        return new File(path);
    }

    //Método para mover archivos a una carpeta de destino
    public void moverArchivo(File file, String destino){
        File destinoFolder = getFile(destino);
        if(destinoFolder.exists() == false){
            destinoFolder.mkdirs();
        }

        try{
            Files.move(file.toPath(), destinoFolder.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

      //----------------------------- Metodos de organización -------------------------------//
     //                                                                                     //
    //              Método para organizar archivos por tipo                                //    
    public void organizarPorTipo(String path, String destino){
        File folder = getFile(path);
        for( File file : folder.listFiles()){
            if(isImagen(file)){
                moverArchivo(file, destino);
            }
        }
    }

   //                                                                                 //
  //---------------------------------------------------------------------------------//

    
      //----------------------------- Metodos de verificación -------------------------------//
     //                                                                                     //
    //              Método para verificar si un archivo es una imagen    
    
                //To Do: Hacer JSON con extensiones y que lo recorra 
    public boolean isImagen(File file){
        String name = file.getName().toLowerCase();
        JSONArray extension = leerJSON("Imagen");
        for(Object obj : extension){
            if(name.endsWith((String) obj)){
                return true;
            }
        }
        return false;
    }

    public boolean isDocumento(File file){
        String name = file.getName().toLowerCase();
        JSONArray extension = leerJSON("Documento");
        for(Object obj : extension){
            if(name.endsWith((String) obj)){
                return true;
            }
        }
        return false;
    }

    public boolean isVideo(File file){
        String name = file.getName().toLowerCase();
        JSONArray extension = leerJSON("Video");
        for(Object obj : extension){
            if(name.endsWith((String) obj)){
                return true;
            }
        }
        return false;
    }

    public boolean isAudio(File file){
        String name = file.getName().toLowerCase();
        JSONArray extension = leerJSON("Audio");
        for(Object obj : extension){
            if(name.endsWith((String) obj)){
                return true;
            }
        }
        return false;
    }

   //                                                                                 //
  //---------------------------------------------------------------------------------//
}