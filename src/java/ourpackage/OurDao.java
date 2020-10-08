/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mo
 */
   
public class OurDao {
    
    static PreparedStatement statement; 
    static Connection connection = null; 
    static ResultSet rs = null;
    
    public static void startDB () throws ClassNotFoundException, SQLException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
       }
        
    
    public static void stopDB () throws SQLException{
        if(connection != null) {
            connection.close();
        }
    }
    
    public static void enregistrar(String titulo, String desc, String clave, 
            String author, String fechaC, String fechaS, String fileName) throws SQLException{
        
        String query = "SELECT id from image";
        statement = connection.prepareStatement(query);
        rs = statement.executeQuery();
        //rs.last();
        //int newId = rs.getInt("id") + 1; // Ids comenzando por 1?
        int idI=0; 
        while (rs.next()){
            idI = rs.getInt("id"); 
            System.out.println(idI);
        }
       
        
        
        query = "insert into IMAGE  values(?, ?, ?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(query);
        statement.setInt(1, idI+1);
        statement.setString(2, titulo);
        statement.setString(3, desc);
        statement.setString(4, clave);
        statement.setString(5, author);
        statement.setString(6,fechaC);
        statement.setString(7, fechaS);
        statement.setString(8, fileName);
        statement.executeUpdate();
    }
    
    public static int esborrar(){
        return 0;
    }
    
    public static void consultar(){
        
    }
    
    public static ResultSet getAllImages() throws SQLException {
        
        PreparedStatement ps = null;
        ResultSet res = null;
        try{
            String query = "select * from image";
            ps = connection.prepareStatement(query);
            res = ps.executeQuery();
        } catch(NullPointerException e){
            if(connection==null) System.out.println("connection is null");
            else if(ps==null) System.out.println("ps null");
            else if(res == null) System.out.println("res null");
        }
        return res;
    }
}
