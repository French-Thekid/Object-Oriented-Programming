/*
Darryl Brown (1503803)
Lomar Lilly(1401375) 
Camille Simmonds (0906116) 
Ryan Newman (1501202) 
*/
package services;

import java.sql.*;

public class DBConnection {
	Connection conn =null;
	public static Connection dbConnector(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:H&AI.sqlite");
			System.out.println("Sucess");
			return conn;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
}
