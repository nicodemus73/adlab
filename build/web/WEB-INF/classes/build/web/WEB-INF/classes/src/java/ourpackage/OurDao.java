/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourpackage;

import static java.lang.System.out;
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
    public static boolean loggin(String usuari, String psw) throws SQLException{
        String query = "select * from usuarios";
    
        statement = connection.prepareStatement(query); 
            rs = statement.executeQuery();
            boolean found = false;
            while (rs.next() & !(found)){
                if ((rs.getString("id_usuario").equals(usuari)) & (psw).equals(rs.getString("password"))) {
                    found = true;
                }
            }
            return found;
    }    
    public static void enregistrar(String titulo, String desc, String clave, 
            String author, String fechaC, String fechaS, String fileName) throws SQLException{
        
        String query = "SELECT id from image";
        statement = connection.prepareStatement(query);
        rs = statement.executeQuery();
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
    
    public static void consultar(String palabra) throws SQLException{
        out.println("PROVA1");
        String query = "SELECT id from image WHERE AUTHOR = Silvia";
       /* String p = "test1";
        statement.setString(1,p);
       */ statement = connection.prepareStatement(query);
        rs = statement.executeQuery();
        out.println("PROVA2");
        while (rs.next()){
            String id = rs.getString("ID");
            out.println(id);
        }
        out.println("PROVA3");
    }
}
