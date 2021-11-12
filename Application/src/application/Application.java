/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.PrintStream;
import java.sql.SQLException;

/**
 *
 * @author myusu
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        new ConfigDB().getConnect();
        
        String[] F = {"Judul","Genre","Tahun","Asal","Stok"};        
        String[] V = {"Captain","Actions","2010","ID","22"};

        
        ConfigDB praktikum = new ConfigDB();
        System.out.println(praktikum.getDoubleArrays(F, V));
        praktikum.ubahData("film", "KodeFilm", "01", F, V);
        
    }
    
}
