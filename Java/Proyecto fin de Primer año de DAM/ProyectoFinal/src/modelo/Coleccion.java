package modelo;


public class Coleccion {
	private String nombre;
	private String temporada;
	private int anho_temporada;
	
	public Coleccion(String nombre, String temporada, int anho_temporada) {
		super();
		this.nombre = nombre;
		this.temporada = temporada;
		this.anho_temporada = anho_temporada;
	}

	public Coleccion() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public int getAnho_temporada() {
		return anho_temporada;
	}

	public void setAnho_temporada(int anho_temporada) {
		this.anho_temporada = anho_temporada;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\tColección: " + getNombre() + ", ");
		sb.append("Temporada: " + getTemporada() + ", ");
		sb.append("Año: " + getAnho_temporada() + "\n");
		return sb.toString();
	}
	
	
	
	
}
