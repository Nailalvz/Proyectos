package modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Articulo {
	
	private int codigo, stock;
	private double precio;
	

	public Articulo (int codigo, int stock, double precio) {
		this.codigo = codigo;
		this.stock = stock;
		this.precio = precio;
	}
	
	public Articulo(int codigo) {
		this.codigo = codigo;
	}
	
	public Articulo() {
		super();
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public int getStock() {
		return stock;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Codigo de artículo: " + getCodigo() + "\n");
		sb.append("Stock: " + getStock() + "\n");
		BigDecimal bd = new BigDecimal(getPrecio()).setScale(2, RoundingMode.HALF_UP);
		double precio = bd.doubleValue();
		sb.append("Precio: " + precio + "\n");
		return sb.toString();
	}
	
	public String toStringCorto() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Codigo: " + getCodigo() + "\n");
		return sb.toString();
	}
	

}
