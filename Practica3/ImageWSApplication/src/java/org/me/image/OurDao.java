/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.image;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mo
 */
   
public class OurDao {
    
    static Connection connection = null; 
    
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
        String query = "select * from usuarios WHERE id_usuario = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,usuari);
        statement.setString(2,psw);
        ResultSet rs = statement.executeQuery();
        return rs.next();
    }   
    public static void newuser(String usuario, String passw) throws SQLException {
        
        PreparedStatement statement;
        String query = "insert into usuarios values(?,?)";
        statement = connection.prepareStatement(query);
        statement.setString(1, usuario);
        statement.setString(2, passw);
        statement.executeUpdate();

    }
    public static int enregistrar(String titulo, String desc, String clave, 
            String author, String fechaC, String fechaS, String fileName) throws SQLException{
        
        String query = "SELECT id from image";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

       int idI=0;
       while (rs.next()) idI = rs.getInt("ID");

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
        return idI;
    }
    
    public static boolean eliminar(int x){
        
        PreparedStatement statement;
        String query;
        try {
            query= "delete from image where ID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, x);
            statement.executeUpdate();   
        } catch (SQLException e){
            return false;
        }
        return true;
    }
    
   
    
      public static boolean enregistrarCanvi(String tituloU, String descU, String claveU,
            String dataU, String fN, int id) throws SQLException{
        String query;
        PreparedStatement st;
        try {
            query = "UPDATE IMAGE SET TITLE = ?, DESCRIPTION=?, KEYWORDS=?, CREATION_DATE = ?, FILENAME = ? WHERE ID = ?";
       
        
            st = connection.prepareStatement(query);
            st.setString(1, tituloU);
            st.setString(2, descU); 
            st.setString(3, claveU);
            st.setString(4, dataU);
            st.setString(5, fN);
            st.setInt(6, id);

            st.executeUpdate();
        
       } catch (SQLException e){
           return false;
       }
       return true; 
    }
    
    //public static ArrayList<String> consultar(HashMap<String, String> palabra) throws SQLException{
    public static ResultSet consultar(HashMap<String, String> palabra) throws SQLException{
        PreparedStatement statement;
        ResultSet rs;
        String[] aux = {""};
        String query2 = "SELECT * FROM IMAGE WHERE ID is null";
        palabra.forEach((String k, String v) -> {
            boolean b = false;
            String query = "";
            switch (k){
                case "title":
                    query    += " OR TITLE LIKE '"+v+"'";
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
                    query+="";
            }
            
            aux[0] += query;
            
        });
            statement = connection.prepareStatement(query2+aux[0]);
            rs = statement.executeQuery();
            return rs;
    }
    
     public static List<Image> getAllImages() throws SQLException {
        
        if(connection == null) return null; //No se ha iniciado la conexión
        String query = "select * from image";
        ResultSet res = connection.prepareStatement(query).executeQuery();
        ArrayList<Image> list = new ArrayList<>(); 
        while(res.next()){
            list.add(new Image(
                    res.getInt("ID"),
                    res.getString("TITLE"),
                    res.getString("AUTHOR"),
                    res.getString("DESCRIPTION"),
                    res.getString("KEYWORDS"),
                    res.getString("CREATION_DATE"),
                    res.getString("STORAGE_DATE"),
                    res.getString("FILENAME")
            ));
        }
        return list;
    }
    public static ResultSet getImage(int id) throws SQLException {
        
        if(connection == null) return null; //No se ha iniciado la conexión
        PreparedStatement st; 
        String query = "select * from image where id =?";
        st = connection.prepareStatement(query);
        st.setInt(1, id);
        ResultSet res = st.executeQuery();
        return res;
    }
    
    protected static boolean validateUsername(String username){
        
        return username!=null 
                && !username.isEmpty()
                && !username.contains(" ");
    }
    
    protected static boolean validatePassword(String password){
        
        return password!=null
                && !password.isEmpty()
                && !password.contains(" ")
                && password.length()>5;
    }
}
