/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagews_client_application;

/**
 *
 * @author Samuel
 */
public class ImageWS_Client_Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    private static int registrerImage(org.me.image.Image image) {
        org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.registrerImage(image);
    }

    private static int modifyImage(org.me.image.Image image) {
        org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.modifyImage(image);
    }

    private static int deleteImage(org.me.image.Image image) {
        org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.deleteImage(image);
    }

    private static java.util.List<java.lang.Object> listImages() {
        org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.listImages();
    }

    private static java.util.List<java.lang.Object> searchbyAuthor(java.lang.String author) {
        org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyAuthor(author);
    }

    private static java.util.List<java.lang.Object> searchbyCreaDate(java.lang.String creaDate) {
        org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyCreaDate(creaDate);
    }

    private static java.util.List<java.lang.Object> searchbyKeywords(java.lang.String keywords) {
        org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyKeywords(keywords);
    }

    private static java.util.List<java.lang.Object> searchbyTitle(java.lang.String title) {
        org.me.image.ImageWS_Service service = new org.me.image.ImageWS_Service();
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyTitle(title);
    }
    
}
