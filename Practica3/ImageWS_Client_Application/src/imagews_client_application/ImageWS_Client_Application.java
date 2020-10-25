/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagews_client_application;

import java.util.ArrayList;
import java.util.List;
import org.me.image.Image;

/**
 *
 * @author Samuel
 */
public class ImageWS_Client_Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Image> list = new ArrayList<>();
        listImages().forEach(o -> {
            if(o == null) System.out.println("nulo");
            else list.add((Image) o);
        });
        list.forEach(i -> { 
            System.out.println(ImageToString(i));
        });
    }

    private static String ImageToString(Image img) {
        return "Image{" + 
                "id=" + img.getId() 
                + ", title=" + img.getTitle() 
                + ", author=" + img.getAuthor()
                + ", description=" + img.getDescription() 
                + ", keywords=" + img.getKeywords() 
                + ", creationDate=" + img.getCreationDate()
                + ", storageDate=" + img.getStorageDate() 
                + ", fileName=" + img.getFileName() + '}';
    }

    private static java.util.List<java.lang.Object> listImages() {
        org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.listImages();
    }

}
