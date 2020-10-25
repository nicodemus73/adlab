/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.image.client;

import javax.xml.ws.WebServiceRef;
import org.me.image.ImageWS_Service;

/**
 *
 * @author Samuel
 */
public class WSConnection {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/ImageWSApplication/ImageWS.wsdl")
    private ImageWS_Service service;

    public java.util.List<java.lang.Object> listImages() {
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.listImages();
    }

    public int deleteImage(org.me.image.Image image) {
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.deleteImage(image);
    }

    public int modifyImage(org.me.image.Image image) {
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.modifyImage(image);
    }

    public int registrerImage(org.me.image.Image image) {
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.registrerImage(image);
    }

    public java.util.List<java.lang.Object> searchbyAuthor(java.lang.String author) {
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyAuthor(author);
    }

    public java.util.List<java.lang.Object> searchbyCreaDate(java.lang.String creaDate) {
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyCreaDate(creaDate);
    }

    public java.util.List<java.lang.Object> searchbyKeywords(java.lang.String keywords) {
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyKeywords(keywords);
    }

    public java.util.List<java.lang.Object> searchbyTitle(java.lang.String title) {
        org.me.image.ImageWS port = service.getImageWSPort();
        return port.searchbyTitle(title);
    }
}
