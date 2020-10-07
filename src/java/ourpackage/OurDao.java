/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author mo
 */
   
public class OurDao {
    
    static PreparedStatement statement; 
    static Connection connection = null; 
    
    public static void startDB () throws ClassNotFoundException, SQLException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
       }
        
    
    public static void stopDB (){
        try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
    }
    
    public static void enregistrar(int id, String titulo, String desc, String clave, 
            String author, String fechaC, String fechaS, String fileName) throws SQLException{
        
        String query = "insert into IMAGE  values(?, ?, ?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(query);
        Image myImg = new Image(1);
        statement.setInt(1, id);
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
}
