package bbdd;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

public class BDConnection {

	private static Connection conexion;
	
	public BDConnection() throws ClassNotFoundException, FileNotFoundException, IOException, SQLException {
		startConnection();
	}
	
	public void startConnection() throws ClassNotFoundException, FileNotFoundException, IOException, SQLException {
		conexion = null;
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties leyendo = new Properties();
			leyendo.load(new FileInputStream("config.props"));
			String user = leyendo.getProperty("user");
			String pass = leyendo.getProperty("pass");
			String ruta = leyendo.getProperty("url");
			
			conexion = DriverManager.getConnection(ruta, user, pass);
	}
	
	public static void endConnection() throws SQLException {
	
			if(conexion != null) {
				conexion.close();
			}

	}
	
	public Connection getConexion() {
		return conexion;
	}
	
	
}
