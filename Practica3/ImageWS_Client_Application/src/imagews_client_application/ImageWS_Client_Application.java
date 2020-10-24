/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagews_client_application;

import java.util.Scanner;
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
        System.out.println("Introduce el usuario: ");
        Scanner us = new Scanner(System.in);
        String user = us.nextLine();
        boolean exit = false;
        while (!exit) {//poner condicion q in<5
           System.out.println("1 - Registrar imagen");
           System.out.println("2 - Listar imagen");
           System.out.println("3 - Buscar imagen");
           System.out.println("4 - Modificar imagen");
           System.out.println("5 - Salir");
           Scanner sc = new Scanner(System.in); 
           int in = sc.nextInt();
           switch (in) {
                case 1:
                    Image tmp = new Image();
                    sc.nextLine();
                    System.out.println("Titulo: ");
                    tmp.setTitle(sc.nextLine());
                    System.out.println("Autor: ");
                    tmp.setAuthor(sc.nextLine());
                    System.out.println("Drescripción: ");
                    tmp.setDescription(sc.nextLine());
                    System.out.println("Keywords (separadas por un espacio) : ");
                    tmp.setKeywords(sc.nextLine());
                    System.out.println("Fecha de creación en formato yyyy/mm/dd: ");
                    tmp.setCreationDate(sc.nextLine());
                    registrerImage(tmp);
                    System.out.println("hola1");
                    break;
                case 2:
                    System.out.println("hola2");
                    break;
                case 3: 
                    System.out.println("Introduce el campo por el que buscar: (id, titulo, fecha, autor, palabras clave)");
                    //canviar aquest switch per fer cerca combinada
                    String s = sc.nextLine();
                    Scanner search = new Scanner(System.in);
                    switch (s) {
                        case "id":
                            //mhauria de sortir abaix
                            /*System.out.println("Introduce el id de la imagen: ");
                            int in2 = sc.nextInt();
                            Object image = SearchById(in2);
                            Image mobj = Image.class.cast(image);
                            System.out.println("Títol: " + mobj.getTitol());*/
                            break;
                        case "titulo":
                            
                            break;
                        case "fecha":
                            
                            break;
                        case "autor":
                            
                            break;
                        case "palabras clave":
                            
                            break;
                            
                        default: break;
                            
                    }
                    
                case 5: 
                    exit = true;
                    break;
                default: break;
                    
           }
        }
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
