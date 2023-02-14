package Pruebas;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controlador.ArticuloController;
import modelo.Coleccion;
import modelo.Decoracion;
import modelo.Hogar;

import vista.ArticuloVista;
import bbdd.BDConnection;

class Pruebas {
	
	static ArticuloController controlador;
	static ArticuloVista vista;
	static BDConnection conexion;
	
	@BeforeEach
	public void iniciarControlador() throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		controlador = new ArticuloController();
		conexion = new BDConnection();
	}
	
	
	@Test
	void testAnhadirArticuloHogar() throws SQLException {

		Hogar h = new Hogar (51, 3, 12.5, "Manta", "Negro", "Salón");
		controlador.addArticuloHogar(h);
		
		int actual = 51;
		boolean esperado = false;
		for (int i = 0; i < controlador.getArticulos().size(); i ++) {
			if(actual == controlador.getArticulos().get(i).getCodigo()) {
				esperado = true;
				break;
			}
		}
		
		assertTrue(esperado);
		
		try {
			Statement s = conexion.getConexion().createStatement();	
			String query2 = "SELECT * FROM articulo_hogar where codigo_articulo = 51";
			ResultSet rs2 = s.executeQuery(query2);
			while(rs2.next()) {
				System.out.println(rs2.getInt(1) + " " + rs2.getString(2) + " " + rs2.getString(3)+ " " + rs2.getString(4));
			}
		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
	}
		@Test
		void testAnhadirArticuloDecoracion() throws SQLException {
			
			Coleccion a = new Coleccion("Air", "Invierno", 2021);
			Decoracion d = new Decoracion(50, 3, 22.5, "Espejo", "Blanco", a);
			
			int actual = 50;
			boolean esperado = false;
			controlador.addArticuloDecoracion(d);
			
			for (int i = 0; i < controlador.getArticulos().size(); i ++) {
				if(actual == controlador.getArticulos().get(i).getCodigo()) {
					esperado = true;
					break;
				}
			}
			
			assertTrue(esperado);
		

			try {
				Statement s = conexion.getConexion().createStatement();
				String query = "SELECT * FROM articulo_decoracion where codigo_articulo = 50";
				ResultSet rs = s.executeQuery(query);
					while(rs.next()) {
					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)+ " " + rs.getString(4));
				}
			
			} catch (SQLException e) {

				System.out.println(e.getMessage());
			}
		}
		
		//No sabía muy bien como hacerla prueba negativa de los dos test anteriores.

			@Test
			void testAnhadirArticuloHogarNegativo() throws SQLException {

				Hogar h = new Hogar (52, 3, 12.5, "Manta", "Negro", "Salón");
				controlador.addArticuloHogar(h);
				
				int art = 52;
				boolean actual = false;
				boolean esperado = false;
				for (int i = 0; i < controlador.getArticulos().size(); i ++) {
					if(art == controlador.getArticulos().get(i).getCodigo()) {
						actual = true;
						break;
					}
				}
				
				assertNotEquals(actual, esperado);
				
				try {
					Statement s = conexion.getConexion().createStatement();	
					String query2 = "SELECT * FROM articulo_hogar where codigo_articulo = 52";
					ResultSet rs2 = s.executeQuery(query2);
					while(rs2.next()) {
						System.out.println(rs2.getInt(1) + " " + rs2.getString(2) + " " + rs2.getString(3)+ " " + rs2.getString(4));
					}
				} catch (SQLException e) {

					System.out.println(e.getMessage());
				}

	}
			
			@Test
			void testAnhadirArticuloDecoracionNegativo() throws SQLException {
				
				Coleccion a = new Coleccion("Air", "Invierno", 2021);
				Decoracion d = new Decoracion(53, 3, 22.5, "Espejo", "Blanco", a);
				
				int art = 53;
				boolean actual = false;
				boolean esperado = false;
				controlador.addArticuloDecoracion(d);
				
				for (int i = 0; i < controlador.getArticulos().size(); i ++) {
					if(art == controlador.getArticulos().get(i).getCodigo()) {
						actual = true;
						break;
					}
				}
				
				assertNotEquals(actual, esperado);

				try {
					Statement s = conexion.getConexion().createStatement();
					String query = "SELECT * FROM articulo_decoracion where codigo_articulo = 53";
					ResultSet rs = s.executeQuery(query);
						while(rs.next()) {
						System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)+ " " + rs.getString(4));
					}
				
				} catch (SQLException e) {

					System.out.println(e.getMessage());
				}
			}
			
			@Test
			void testEliminarArticuloHogar() throws SQLException {
				
				int pos = controlador.idCorrecto(52); //borramos el ID 52 que es un artículo de Hogar
				controlador.deleteArticulo(52, pos);
				
				boolean esperado = false;
				for (int i = 0; i < controlador.getArticulos().size(); i ++) {
					if(51 == controlador.getArticulos().get(i).getCodigo()) {
						esperado = true;
						break;
					}
				}
				
				assertFalse(esperado);
				
				try {
					Statement s = conexion.getConexion().createStatement();	
					String query2 = "SELECT * FROM articulo_hogar where codigo_articulo = 52";
					ResultSet rs2 = s.executeQuery(query2);
					while(rs2.next()) {
						System.out.println(rs2.getInt(1) + " " + rs2.getString(2) + " " + rs2.getString(3)+ " " + rs2.getString(4));
					}
				} catch (SQLException e) {

					System.out.println(e.getMessage());
				}
			}
				@Test
				void testEliminarArticuloDecoracion() throws SQLException {
					
					int pos = controlador.idCorrecto(50); //Eliminamos el artículo 50 que us un artículo de decoración
					controlador.deleteArticulo(50, pos);
					
					boolean esperado = false;
					for (int i = 0; i < controlador.getArticulos().size(); i ++) {
						if(50 == controlador.getArticulos().get(i).getCodigo()) {
							esperado = true;
							break;
						}
					}
					
					assertFalse(esperado);

					try {
						Statement s = conexion.getConexion().createStatement();
						String query = "SELECT * FROM articulo_decoracion where codigo_articulo = 50";
						ResultSet rs = s.executeQuery(query);
							while(rs.next()) {
							System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)+ " " + rs.getString(4));
						}
					
					} catch (SQLException e) {

						System.out.println(e.getMessage());
					}
				}

}
