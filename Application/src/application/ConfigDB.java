/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
//set witdh column
import javax.swing.table.TableColumn;

/**
 *
 * @author myusu
 */
public class ConfigDB {
    
    private String url = "jdbc:mysql://localhost:3306/dbfilm";
    private String user = "root";
    private String pass = "";
    
    public ConfigDB(){}
    
    
    //==== Create Connection to Database
    public Connection getConnect() throws SQLException {
        try {
            Driver myDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(myDriver);
            System.out.println("Connection Successfully");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return DriverManager.getConnection(url,user,pass);
    }
    
    //==== DOUBLE KEY CHECK ===========================================
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
    
    //==== INSERT DATA SEMI MANUAL
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
    
    //==== INSERT DATA DINAMIS PREPARED
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
    
    
    //==== HAPUS DINAMIS
    public void HapusDinamis(String Table, String Primary, String KodeFilm){
        try {
            String SQLHapus = "DELETE FROM "+Table+" WHERE "+Primary+" = '"+KodeFilm+"'";
            Statement commandDelete = getConnect().createStatement();
            commandDelete.executeUpdate(SQLHapus);
            commandDelete.close();
            getConnect().close();
            JOptionPane.showMessageDialog(null, "Data Deleted Successfully");
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    
//==================== METHOD DINAMIS FULL ============================================================================================
    
    //*********************** Fields
    public String getFieldArray(String[] Fields){
        String hasil = "";
        int detection = Fields.length - 1;
        try {
            for (int i = 0; i < Fields.length; i++) {
                if(i==detection){
                    hasil = hasil + Fields[i];
                }else{
                    hasil  = hasil + Fields[i]+",";
                }   
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "("+hasil+")";
    }
    
    //*********************** Values
    public String getValueArray(String[] Values){
        String hasil = "";
        int detection = Values.length - 1;
        
        try {
            for (int i = 0; i < Values.length; i++) {
                if(i==detection){
                    hasil = hasil +"'"+Values[i]+"'";
                }else{
                    hasil = hasil +"'"+ Values[i]+"',";
                }
            } 
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "("+hasil+")";   
    }
    
    //**************************** INSERT DATA
    public void simpanData(String Table, String[] Fields, String[] Values){
        try {
            String SQLSimpan = "INSERT INTO "+Table+""+getFieldArray(Fields)+" VALUES "+getValueArray(Values);
            Statement command = getConnect().createStatement();
            command.executeUpdate(SQLSimpan);
            command.close();
            getConnect().close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    //================ END INSERT DATA
    
   
    //================ UPDATE DATA
    public String getDoubleArrays(String[] Fields, String[] Values){
        String hasil = "";
        int detection = Fields.length - 1;
        try {
            for (int i = 0; i < Fields.length; i++) {
                if(i==detection){
                    hasil = hasil +Fields[i]+"='"+Values[i]+"'";
                }else{
                    hasil = hasil +Fields[i]+"='"+Values[i]+"',";
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return ""+hasil+"";
    }
    
    //***************** Update Data
    public void ubahData(String Table, String PrimaryKey, String Key, String[] Fields, String[] Values){
        try {
            String SQLUpdate = "UPDATE "+Table+" SET "+getDoubleArrays(Fields, Values)+" WHERE "+PrimaryKey+"='"+Key+"'";
            Statement Command = getConnect().createStatement();
            Command.executeUpdate(SQLUpdate);
            Command.close();
            getConnect().close();
            JOptionPane.showMessageDialog(null, "Updated Data Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    //================ END UPDATE DATA
    
    //================ DELETE DATA
   
    //================================================================ SHOW DATABASE TO TABLE ===================================================
    //===========================================================================================================================================
    
    //**************** METHOD TABLES
    public void setJudulKolom(JTable Table, String[] JudulKolom){
        try {
            DefaultTableModel model = new DefaultTableModel();
            Table.setModel(model);
            
            for (int i = 0; i < JudulKolom.length; i++) {
                model.addColumn(JudulKolom[i]);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    //-----------------------------------------------
    public void setWidhtTitColumn(JTable Table, int[] WidthColumnTo){
        try {
            TableColumn ColumnTo = new TableColumn();
            Table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            
            for (int i = 0; i < WidthColumnTo.length; i++) {
                ColumnTo = Table.getColumnModel().getColumn(i);
                ColumnTo.setPreferredWidth(WidthColumnTo[i]);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
   
}
