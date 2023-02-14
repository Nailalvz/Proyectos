package controlador;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.sql.ResultSet;

import bbdd.BDConnection;

import modelo.Ticket;


public class TicketController {
	private static ArrayList<Ticket> tickets;

	private static BDConnection conexion;
	
	public TicketController() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		conexion = new BDConnection();
		tickets = new ArrayList<Ticket>();
		recuperarTicketBD();		
	}
	
	private static void recuperarTicketBD() throws SQLException{
		
		Statement s = conexion.getConexion().createStatement();
		String query = "SELECT * FROM resumen_ticket_articulos";
		ResultSet rs = s.executeQuery(query);
		
		while(rs.next()) {
			Ticket t = new Ticket(rs.getInt(1), rs.getTimestamp(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5));
			tickets.add(t);
		}
		
		rs.close();
	}
	
	public void mostrarTicket() {
		int ntic = tickets.get(0).getCodigo();
		Timestamp fecha_hora = tickets.get(0).getFecha_hora();
		int elemento = tickets.get(0).getCodigo_articulo();
		double precio = tickets.get(0).getPrecio();
		int uds = tickets.get(0).getUds();
		double sum = tickets.get(0).getPrecio();
		System.out.println("Ticket: " + ntic + "\n"
		 		+ "Fecha y hora: " + fecha_hora + "\n"
		 				+ "\tArtículo: " + elemento + "\t precio: " + precio + "\t unidades: " + uds);
		 
		for(Ticket ticket: tickets) {
			
			if(ticket.getCodigo() == ntic) {
				System.out.println("\tArtículo: " + ticket.getCodigo_articulo() + "\t precio: " + ticket.getPrecio() + "\t unidades: " + ticket.getUds());
				sum = sum + ticket.getPrecio();
			} else {
				BigDecimal bd = new BigDecimal(sum).setScale(2, RoundingMode.HALF_UP);
				sum = bd.doubleValue();
				System.out.println("\n\tTotal: " + sum + "\n");
				System.out.println("Ticket: " + ticket.getCodigo() + "\n"
						+ "Fecha y hora: " + ticket.getFecha_hora() + "\n"
								+ "\tArtículo: " + ticket.getCodigo_articulo() + "\t precio: " + ticket.getPrecio() + "\t unidades: " + ticket.getUds());
				
				ntic = ticket.getCodigo();
				sum = ticket.getPrecio();
			}
		
		}
		System.out.println("\n\tTotal: " + sum + "\n");
	}
	

}
