package modelo;


import java.sql.Timestamp;


public class Ticket {
	private int codigo;
	private Timestamp fecha_hora;
	
	private int codigo_articulo;
	private double precio;
	private int uds;
	
	
	public Ticket(int codigo, Timestamp timestamp, int codigo_articulo,  double precio, int uds) {
		super();
		this.codigo = codigo;
		this.fecha_hora = timestamp;
		this.codigo_articulo = codigo_articulo;
		this.precio = precio;
		this.uds = uds;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Timestamp getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(Timestamp fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public int getCodigo_articulo() {
		return codigo_articulo;
	}

	public void setCodigo_articulo(int codigo_articulo) {
		this.codigo_articulo = codigo_articulo;
	}

	public int getUds() {
		return uds;
	}

	public void setUds(int uds) {
		this.uds = uds;
	}

	public double getPrecio() {
		return precio;
	}

	public void setTotal(double precio) {
		this.precio = precio;
	}
	


	@Override
	public String toString() {
StringBuilder sb = new StringBuilder();
		
		sb.append("Ticket: " + getCodigo() + "\n");
		sb.append("Fecha y hora: " + getFecha_hora() + "\n");
		sb.append("Artículo: " + getCodigo_articulo() + "\tUnidades: " + getUds());
		sb.append("\tPrecio: " + getPrecio() + "\n");
		
		return sb.toString();
	}
	
	
	
	
	
	
	
}
