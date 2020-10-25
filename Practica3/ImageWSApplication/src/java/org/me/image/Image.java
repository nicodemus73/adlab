/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.image;

/**
 *
 * @author Samuel
 */
public class Image {

    private int id;
    private String title;
    private String author;
    private String description;
    private String keywords;
    private String creationDate;
    private String storageDate;
    private String fileName;

    public Image(){}

    public Image(int id, String title, String author, String description, String keywords, String creationDate, String storageDate, String fileName) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.keywords = keywords;
        this.creationDate = creationDate;
        this.storageDate = storageDate;
        this.fileName = fileName;
    }

    public Image(String title, String author, String description, String keywords, String creationDate, String storageDate, String fileName) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.keywords = keywords;
        this.creationDate = creationDate;
        this.storageDate = storageDate;
        this.fileName = fileName;
    }

    public String getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(String storageDate) {
        this.storageDate = storageDate;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
