/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourpackage;

/**
 *
 * @author mo
 */
public class Image {
    private int id; 
   /* private String titulo;
    private String descripcion;
    private String clave;
    private String author;
    private String fechaC;
    private String fechaS;
    private String fileName;*/
    
    public Image (int id){
        super();
        this.id = id;
    }

  
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
}
