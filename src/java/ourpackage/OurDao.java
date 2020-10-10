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
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mo
 */
   
public class OurDao {
    
    static Connection connection = null; 
   // static PreparedStatement statement; 
    //static ResultSet rs = null;
    
    
    public static void startDB () throws ClassNotFoundException, SQLException{
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
       }
        
    
    public static void stopDB () throws SQLException{
        if(connection != null) {
            connection.close();
        }
    }
    public static boolean loggin(String usuari, String psw) throws SQLException{
        String query = "select * from usuarios";
        PreparedStatement statement;
        ResultSet rs; 
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
        ResultSet rs;
        
        String query = "SELECT ID from image";
        PreparedStatement statement = connection.prepareStatement(query);
        rs = statement.executeQuery();
        //rs.last();
        //int newId = rs.getInt("id") + 1; // Ids comenzando por 1?
       int idI=1;
       while (rs.next()){
            idI = rs.getInt("ID"); 
        }
        idI ++; 
         query = "insert into IMAGE  values(?, ?, ?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(query);
        statement.setInt(1, idI);
        statement.setString(2, titulo);
        statement.setString(3, desc);
        statement.setString(4, clave);
        statement.setString(5, author);
        statement.setString(6,fechaC);
        statement.setString(7, fechaS);
        statement.setString(8, fileName);
        statement.executeUpdate();
    }
    
    public static void eliminar(){
    }
    
    public static boolean enregistrarNou(String campo, String valor){
        PreparedStatement statement;
        String query;
        int id=10; 
       try { query = "UPDATE image set "+campo+" = '"+valor+"' where ID = "+ id;
        statement = connection.prepareStatement(query);
        statement.executeUpdate();
        
       } catch (SQLException e){
           return false;
       }
       return true; 
    }
    
    public static ArrayList<String> consultar(HashMap<String, String> palabra) throws SQLException{
        
        PreparedStatement statement;
        ResultSet rs;
        ArrayList<String> s = new ArrayList<>();

        String[] aux = {null};    

        palabra.forEach((String k, String v) -> {
            String query = "SELECT id FROM IMAGE WHERE ID is null";

            switch (k){
                case "title": 
                    query += " OR TITLE LIKE '"+v+"'";
                    break;
                    
                case "descrpition":
                    query += " OR DESCRIPTION LIKE '"+v+"'";
                    break;
                    
                case "keywords":
                    query += " OR KEYWORDS LIKE '"+v+"'";
                    break;
                    
                case "author":
                    
                    query += " OR AUTHOR LIKE '"+v+"'";
                    break;
                    
                case "cdate":
                    query += " OR CREATION_DATE LIKE '"+v+"'";
                    break;
                    
                case "filename":
                    query += " OR FILENAME LIKE '"+v+"'";
                    break;
                    
                default: 
                    query = null;
                    break;
            }
            aux[0] = query;
        });
        
            statement = connection.prepareStatement(aux[0]);

            rs = statement.executeQuery();
            
            while (rs.next()){
                String id = rs.getString("ID");
                s.add(id);
            }
            return s;
    }
    
    public static ResultSet getAllImages() throws SQLException {
        
        if(connection == null) return null; //No se ha iniciado la conexión
        String query = "select * from image";
        ResultSet res = connection.prepareStatement(query).executeQuery();
        return res;
    }
}
