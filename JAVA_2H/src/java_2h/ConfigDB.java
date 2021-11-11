/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_2h;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
//Statement for show
import java.sql.Statement;
//Prepared for edit show
import java.sql.PreparedStatement;
//Get data to database
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author myusu
 */
public class ConfigDB {
    
    private String url = "jdbc:mysql://localhost:3306/dbfilm";
    private String user = "root";
    private String pass = "";
    
    //CONSTRUCTOR    
    public ConfigDB(){}

    public Connection getConnect() throws SQLException{
        try {
            Driver myDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(myDriver);
            System.out.println("Connected successfully");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return DriverManager.getConnection(url,user,pass);
    }
    
    //========================== DINAMIS ================================
    public boolean getDupKey(String table, String Primary, String Isi){
        boolean hasil = false;
        //checking        
        try {
            String SQLcari = "SELECT * FROM "+table+" WHERE "+Primary+" = '"+Isi+"'";
            //            
            Statement CekData = getConnect().createStatement();
            //Get Data            
            ResultSet PK = CekData.executeQuery(SQLcari);
            
            if(PK.next()){
                hasil = true;
            }else {
                hasil = false;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return hasil;
    }
    
    //========================== SEMI MANUAL ============================
    public void SimpanFilmStatement(String KodeFilm, String Judul, String Genre, String Tahun, String Asal, String Stok){
        try {
            String SQLSimpan = "INSERT INTO film (KodeFilm, Judul, Genre, Tahun, Asal, Stok) VALUES ('"+KodeFilm+"','"+Judul+"','"+Genre+"','"+Tahun+"', '"+Asal+"','"+Stok+"')";
            Statement perintah = getConnect().createStatement();
            
            //For SQLSImpan is put in Excecute Update           
            perintah.executeUpdate(SQLSimpan);
            perintah.close();
            getConnect().close();
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
   
    
    //============================ HAPUS
    //============================ UPDATE  
    
    
    public void SimpanFilmPrepared(String KodeFilm, String Judul, String Genre, int Tahun, String Asal, int Stok){
        try {
            String SQLSimpan = "INSERT INTO film (KodeFilm, Judul, Genre, Tahun, Asal, Stok) VALUES (?,?,?,?,?,?)";
            //Fpr SQLSimpan is put in direct prepared            
            PreparedStatement perintah = getConnect().prepareStatement(SQLSimpan);
            perintah.setString(1, KodeFilm);            
            perintah.setString(2, Judul);
            perintah.setString(3, Genre);
            perintah.setInt(4, Tahun);
            perintah.setString(5, Asal);
            perintah.setInt(6, Stok);

            //Excetute
            perintah.executeUpdate();
            perintah.close();
            getConnect().close();

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    
    
    
    //============================    CRUD FULL DINAMIS ===================================
    //============    FIELD DATA
   public String getFieldArray(String[] Fields){
        String hasil = "";
        int deteksi = Fields.length - 1; // deteksi array terakhir
        try {
            for (int i = 0; i < Fields.length; i++) {
                if (i==deteksi){
                    hasil = hasil + Fields[i];
                }else{
                  hasil = hasil + Fields[i]+",";   
                }
               
            }
        } catch (Exception e) {
            System.out.println( e.toString());
        }
        
        return "("+hasil+")";
    }
    //============   Field Contents
   public String getValueFieldArray(String[] Values){
        String hasil = "";
        int deteksi = Values.length - 1;
        try {
            for (int i = 0; i < Values.length; i++) {
                if (i == deteksi)
                {
                    hasil = hasil +"'"+Values[i]+"'";
                }else{
                    hasil = hasil +"'"+Values[i]+"',";
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
         return "("+hasil+")";        
    }
    //============ INSERT DATA
    public void SimpanDinamis(String Tables, String[] Fields, String[] Values){
        try {
            String SQLSimpan = "INSERT INTO "+Tables+" "+getFieldArray(Fields)+" VALUES "+getValueFieldArray(Values);
            Statement perintah = getConnect().createStatement();
            
            //For SQLSImpan is put in Excecute Update           
            perintah.executeUpdate(SQLSimpan);
            perintah.close();
            getConnect().close();
            JOptionPane.showMessageDialog(null, "Saved Successfully ");
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    //===================== UPDATE DATA
    public String getCombineValueFieldArray(String[] Fieldnya, String[] Valuenya){
        String hasil = "";
        int deteksi = Fieldnya.length - 1;
        try {
             for (int i = 0; i < Fieldnya.length; i++) {
                 if (i == deteksi){
                     hasil = hasil+Fieldnya[i]+"='"+Valuenya[i]+"'";
                 }else{
                     hasil = hasil+Fieldnya[i]+"='"+Valuenya[i]+"',";
                 }
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
        return hasil;
    }
    
    public void UbahDinamis(String Tabelnya, String Primarynya, String isiPrimary, String[] Fieldnya, String[] Valuenya){
        
        try {
           String SQLUpdate ="UPDATE "+Tabelnya+" SET "+getCombineValueFieldArray(Fieldnya, Valuenya)+" WHERE "+Primarynya+"='"+isiPrimary+"'"; 
           Statement perintah = getConnect().createStatement();
           perintah.executeUpdate(SQLUpdate);
           perintah.close();
           getConnect().close();
           JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        
    }

   
}
