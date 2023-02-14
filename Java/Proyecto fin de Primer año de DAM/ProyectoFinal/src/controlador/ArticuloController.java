package controlador;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bbdd.BDConnection;
import modelo.Articulo;
import modelo.Decoracion;
import modelo.Hogar;
import modelo.Coleccion;

public class ArticuloController {
	private static ArrayList<Articulo> articulos;
	private static BDConnection conexion;
	
	private static ArrayList<Decoracion> art_decoraciones;
	private static ArrayList<Hogar> art_hogares;
	
	
	
	public ArrayList<Articulo> getArticulos() {
		return articulos;
	}

	public ArticuloController() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		conexion = new BDConnection();
		articulos = new ArrayList<Articulo>();
		
		art_decoraciones = new ArrayList<Decoracion>();
		art_hogares = new ArrayList<Hogar>();
		
			recuperarArticuloBD();
			vista_mas_vendidos();
			
	
	}
	
	private static void recuperarArticuloBD() throws SQLException {
	
		Statement s = conexion.getConexion().createStatement();
		ArrayList<Coleccion> colecciones = new ArrayList<>();
		String queryaux = "SELECT * FROM coleccion";
		ResultSet rsaux = s.executeQuery(queryaux);
		
		while(rsaux.next()) {
			Coleccion c = new Coleccion(rsaux.getString(1), rsaux.getString(2), rsaux.getInt(3));
			colecciones.add(c);
		} 
		rsaux.close();
		
		String query = "SELECT * FROM recuperar_decoracion";
		
		ResultSet rs = s.executeQuery(query);
		
		while (rs.next()) {
		
			Coleccion aux = getColeccion(colecciones, rs.getString(6));
			
			Decoracion d = new Decoracion(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4), rs.getString(5), aux);
			articulos.add(d);
		}
		rs.close();
		
		String query2 = "SELECT * FROM recuperar_hogar";
		ResultSet rs2 = s.executeQuery(query2);
		
		while (rs2.next()) {
			Hogar h = new Hogar(rs2.getInt(1), rs2.getInt(2), rs2.getDouble(3), rs2.getString(4), rs2.getString(5), rs2.getString(6));
			articulos.add(h);
		}
		rs2.close();
	}
	
	private static void vista_mas_vendidos() throws SQLException {
		
		Statement s = conexion.getConexion().createStatement();
		String query = "Select * FROM mas_vendidos_decoracion";
		ResultSet rs = s.executeQuery(query);
		
		while(rs.next()) {
			Decoracion d = new Decoracion(rs.getInt(1), rs.getString(2), rs.getInt(3));
			art_decoraciones.add(d);
		}
		
		String query2 = "SELECT * FROM mas_vendidos_hogar";
		
		ResultSet rs2 = s.executeQuery(query2);
		
		while(rs2.next()) {
			Hogar h = new Hogar(rs2.getInt(1), rs2.getString(2), rs2.getInt(3));
			art_hogares.add(h);
		}
	}
	
	public void mostrar_mas_vendidos_decoracion() {
		for(int i = 0; i < art_decoraciones.size(); i ++) {
			System.out.println((i + 1) + "º Artículo más vendido: \n"
					+"Codigo: " + art_decoraciones.get(i).getCodigo() + ""
							+ " \tTipo: " + art_decoraciones.get(i).getTipo() + ""
									+ " \tUnidades totales: " + art_decoraciones.get(i).getUds_totales() + "\n");
		}
	}
	
	public void mostrar_mas_vendidos_hogar() {
		for(int i = 0; i < art_hogares.size(); i ++) {
			System.out.println((i + 1) + "º Artículo más vendido: \n"
					+"Codigo: " + art_hogares.get(i).getCodigo() + ""
							+ " \tTipo: " + art_hogares.get(i).getTipo() + ""
									+ " \tUnidades totales: " + art_hogares.get(i).getUds_totales() + "\n");
		}
	}
	
	public static Coleccion getColeccion(ArrayList<Coleccion> colecciones, String nombre) {
		for(Coleccion coleccion: colecciones) {
			if(coleccion.getNombre().equals(nombre)) {
				return coleccion;
			}
		}
		return null;
	}

	public void mostrarArticulo() {
		for( Articulo articulo: articulos) {
			System.out.println(articulo.toString());
		}
	}
	
	public void mostrarArticuloCorto() {
		for( Articulo articulo: articulos) {
			System.out.println(articulo.toStringCorto());
		}
	}
	
	public void verArticulo(int codigo) {
		for(Articulo articulo: articulos) {
			if(articulo.getCodigo() == codigo) {
				System.out.println(articulo.toString());
				break;
			}
		}
	}
	
	public void mostraArticuloDecoracion() {
		for(Articulo articulo: articulos) {
			if(articulo instanceof Decoracion) {
				System.out.println(articulo.toString());
			}
		}
	}
	
	public void mostraArticuloHogar() {
		for(Articulo articulo: articulos) {
			if(articulo instanceof Hogar) {
				System.out.println(articulo.toString());
			}
		}
	}
	
	public int idCorrecto(int id) {
		for (int i = 0; i < articulos.size(); i++) {
			if(articulos.get(i).getCodigo() == id) {
				return i;
			}
		}
		return -1;
	}
	
	//Devuelve true si el artículo ue se le pasa es un artículo de decoración
	public boolean articuloDecoracion(int pos) {
		if(articulos.get(pos) instanceof Decoracion) {
			return true;
		} else {
			return false;
		}
	}
	
	public void addArticuloDecoracion(Decoracion d) throws SQLException {
			
			PreparedStatement ps = conexion.getConexion().prepareStatement("INSERT INTO articulo (codigo, stock, precio) values (?,?,?)");
			ps.setInt(1, d.getCodigo());
			ps.setInt(2, d.getStock());
			ps.setDouble(3, d.getPrecio());
			ps.executeUpdate();
			ps.close();
			
			PreparedStatement ps2 = conexion.getConexion().prepareStatement("insert into articulo_decoracion (codigo_articulo,tipo,color,coleccion) values (?,?,?,?)");
			ps2.setInt(1, d.getCodigo());
			ps2.setString(2, d.getTipo());
			ps2.setString(3, d.getColor());
			ps2.setObject(4, d.getColeccion().getNombre());
			ps2.executeUpdate();
			ps2.close();	
					
			articulos.add(d);

	}
	
	public void addArticuloHogar(Hogar h) throws SQLException {
		
			
			PreparedStatement ps = conexion.getConexion().prepareStatement("INSERT INTO articulo (codigo, stock, precio) values (?,?,?)");
			ps.setInt(1, h.getCodigo());
			ps.setInt(2, h.getStock());
			ps.setDouble(3, h.getPrecio());
			ps.executeUpdate();
			ps.close();
			
			PreparedStatement ps2 = conexion.getConexion().prepareStatement("insert into articulo_hogar (codigo_articulo,tipo,Color,Estancia) values (?,?,?,?)");
			ps2.setInt(1, h.getCodigo());
			ps2.setString(2, h.getTipo());
			ps2.setString(3, h.getColor());
			ps2.setString(4, h.getEstancia());
			ps2.executeUpdate();
			ps2.close();
			
			articulos.add(h);

	}
	
	public void editStock(int codigo, int stock, int pos) throws SQLException {
		
	
			Statement s = conexion.getConexion().createStatement();
			s.executeUpdate("UPDATE articulo SET stock=" + stock + " WHERE codigo=" + codigo);
			s.close();
	
		articulos.get(pos).setStock(stock);
		
	}
	
	public void editPrecio(int codigo, double precio, int pos) throws SQLException {
		
	
			Statement s = conexion.getConexion().createStatement();
			s.executeUpdate("UPDATE articulo SET precio=" + precio + " WHERE codigo=" + codigo);
			s.close();
		
		articulos.get(pos).setPrecio(precio);
	}
	
	public void editTipoDecoracion(int codigo, String tipo, int pos) throws SQLException {
		 
		
			 Statement s = conexion.getConexion().createStatement();
			 s.executeUpdate("UPDATE Articulo_Decoracion SET tipo='" + tipo + "' WHERE codigo_articulo=" + codigo);
			 s.close();
		
		 ((Decoracion) articulos.get(pos)).setTipo(tipo);
	}
	
	public void editcolorDecoracion(int codigo, String color, int pos) throws SQLException {
		
	
			Statement s = conexion.getConexion().createStatement();
			s.executeUpdate("UPDATE Articulo_Decoracion SET color='" + color + "' WHERE codigo_articulo=" + codigo);
			 s.close();
	
		((Decoracion) articulos.get(pos)).setColor(color);
		
	}
	
	public void editColeccion(int codigo, String coleccion, int pos, Coleccion aux) throws SQLException {
	
			Statement s = conexion.getConexion().createStatement();
			s.executeUpdate("UPDATE Articulo_Decoracion SET coleccion='" + coleccion + "' WHERE codigo_articulo=" + codigo);
			 s.close();
	
		((Decoracion) articulos.get(pos)).setColeccion(aux);
	}
	
	public void editTipoHogar(int codigo, String tipo, int pos) throws SQLException {
		
		
			Statement s = conexion.getConexion().createStatement();
			s.executeUpdate("UPDATE Articulo_Hogar SET tipo='" + tipo + "' WHERE codigo_articulo=" + codigo);
			s.close();
		
		((Hogar) articulos.get(pos)).setTipo(tipo);
	}
	
	public void editcolorHogar(int codigo, String color, int pos) throws SQLException {
		
		
			Statement s = conexion.getConexion().createStatement();
			s.executeUpdate("UPDATE Articulo_Hogar SET color='" + color + "' WHERE codigo_articulo=" + codigo);
			s.close();

		((Hogar) articulos.get(pos)).setColor(color);
		
	}
	
	public void editEstancia(int codigo, String estancia, int pos) throws SQLException {
		
		
			Statement s = conexion.getConexion().createStatement();
			s.executeUpdate("UPDATE Articulo_Hogar SET estancia='" + estancia + "' WHERE codigo_articulo=" + codigo);
			s.close();
		
		((Hogar) articulos.get(pos)).setEstancia(estancia);
	}
	
	public int idDisponible() {
		int toret = articulos.get(0).getCodigo();
		for(Articulo articulo: articulos) {
			if(articulo.getCodigo() > toret) {
				toret = articulo.getCodigo();
			}
		}
		
		return toret + 1;
	}
	
	public void deleteArticulo(int codigo, int pos) throws SQLException {
			Statement s = conexion.getConexion().createStatement();
			s.executeUpdate("DELETE FROM Articulo WHERE codigo=" + codigo);
			s.close();

		articulos.remove(pos);
	}
	
	

	
	
}
