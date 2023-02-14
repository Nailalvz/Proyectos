package modelo;

public class Decoracion extends Articulo{
	private String tipo;
	private String color;
	private Coleccion coleccion;

	private int uds_totales;

	
	public Decoracion(int codigo, int stock, double precio, String tipo, String color, Coleccion coleccion) {
		super(codigo, stock, precio);
		this.tipo = tipo;
		this.color = color;
		this.coleccion = coleccion;
	}
	
	public Decoracion(int codigo, String tipo, int uds_totales) {
		super(codigo);
		this.tipo = tipo;
		this.uds_totales = uds_totales;
	}

	public Decoracion(int codigo, int stock, double precio) {
		super(codigo, stock, precio);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Coleccion getColeccion() {
		return coleccion;
	}

	public void setColeccion(Coleccion coleccion) {
		this.coleccion = coleccion;
	}

	public int getUds_totales() {
		return uds_totales;
	}

	public void setUds_totales(int uds_totales) {
		this.uds_totales = uds_totales;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Codigo: " + getCodigo() + "\n");
		sb.append("\tTipo: " + getTipo() + "\n");
		sb.append("\tColor: " + getColor() + "\n");
		sb.append(getColeccion());
		sb.append("\tStock: " + getStock() + " ");
		sb.append("Precio: " + getPrecio() + "\n");
		return sb.toString();
	}
	
	public String toStringCorto() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Codigo: " + getCodigo() + "\n");
		sb.append("\tTipo: " + getTipo() + "\n");
		sb.append("Precio: " + getPrecio() + "\n");
		return sb.toString();
	}
	
}
