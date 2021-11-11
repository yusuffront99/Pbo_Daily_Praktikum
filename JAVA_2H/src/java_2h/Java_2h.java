/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_2h;

import java.sql.SQLException;

/**
 *
 * @author myusu
 */
public class Java_2h {
    public static void main(String[] args) throws SQLException {
        new ConfigDB().getConnect();

//    String[] F = {"KodeFilm","Judul","Genre","Tahun","Asal","Stok"};
//    String[] V = {"005","Naruto","Action Anime","2020","Jepang","12"};
//    ConfigDB praktikum = new ConfigDB();
//    
//    praktikum.SimpanDinamis("film",F, V);
    }  
    
    
//    ========= Statement
//    praktikum.SimpanFilmStatement("01", "Ada Cinta", "romance", "2019", "ID", "25");


//Prepare Statement
//    praktikum.SimpanFilmPrepared("03", "Impossible", "Action", 2018, "USA", 22);

////Dinamis code -- 
//    if(praktikum.getDupKey("film", "KodeFilm", "06")){
//        System.out.println("Data Exists");
//    }else{
//        praktikum.SimpanFilmPrepared("04", "Tsubasa", "anime", 2000, "Jepun", 30);
//    } 
//    }
}
