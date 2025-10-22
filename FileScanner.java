import java.io.File;
import java.nio.file.Files;

public class FileScanner {

    public static void main(String[] args) {
        String path = "./prueba";
        File folder = getFile(path);
        for( File file : folder.listFiles()){
            System.out.println(file.getName() + " - " + file.length()/1024 + " KB");
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
            file.renameTo(destinoFolder);
            try{
                Files.move(file.toPath(), destinoFolder.toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try{
                Files.move(file.toPath(), destinoFolder.toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    //              Método para verificar si un archivo es una imagen                      // 
    public boolean isImagen(File file){
        String name = file.getName().toLowerCase();
        return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".gif")
                || name.endsWith(".bmp") || name.endsWith(".tiff") || name.endsWith(".svg") || name.endsWith(".webp") 
                || name.endsWith(".exif");
    }

   //                                                                                 //
  //---------------------------------------------------------------------------------//
}