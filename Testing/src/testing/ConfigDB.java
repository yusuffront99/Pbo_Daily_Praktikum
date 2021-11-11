/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;
import java.sql.*;

/**
 *
 * @author myusu
 */
public class ConfigDB {
    private String url = "jdbc:mysql://localhost:3306/19630360_siakadsmpgambut";
    private String user = "root";
    private String pass = "";
    
    
    
    public ConfigDB(){};
    
    public Connection getKoneksi() throws SQLException{
        try {
            Driver myDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(myDriver);
            System.out.println("berhasil koneksi");
        } catch (Exception e){
            System.out.println(e.toString());
        }
        
        return DriverManager.getConnection(url, user, pass);
    }
}
