package vista;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

import java.util.Scanner;




import controlador.ArticuloController;
import controlador.TicketController;
import modelo.Coleccion;
import modelo.Decoracion;
import modelo.Hogar;


public class ArticuloVista {
	private static ArticuloController controlador;
	private static Scanner scan = new Scanner(System.in);
	private static TicketController controladorTicket;

	
	public static void gestionaArticulos() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		
		String opcion;
		boolean salir = false;
		controlador = new ArticuloController();
		controladorTicket = new TicketController();


		
		while(!salir) {
			System.out.println("Seleccione una opción del menú:"
					+ "\n\t1. Añadir nuevos artículos."
					+ "\n\t2. Borrar artículos existentes."
					+ "\n\t3. Modificar artículos existentes."
					+ "\n\t4. Listar los artículos."
					+ "\n\t5. Ver el resumen de los tickets."
					+ "\n\t6. Ver el resumen de los artículos más vendidos."
					+ "\n\t0. Salir.");
			opcion = scan.nextLine();
			
			switch(opcion) {
			
			case "1":
				elegirArticulo();
				break;
			
			case "2":
				eliminarArticulo();
				break;
			
			case "3":
				elegirArticuloModificar();
				break;
				
			case "4":
				menuMostrarArticulos();
				break;
				
			case "5":
				controladorTicket.mostrarTicket();
				break;
			
			case "6":
				menu_mas_vendidos();
				break;
				
			case "0":
				salir = true;
				
				break;
				
			default:
				System.err.println("La opción no es válida.");
				break;
			}
		}
	}
	
	public static void elegirArticulo() throws SQLException {

		String opcion;
		boolean salir = false;
		
		while(!salir) {
			System.out.println("Que tipo de artículo desea añadir:"
					+ "\n\t1. Artículo de decoración."
					+ "\n\t2. Artículo de hogar."
					+ "\n\t0. Salir.");
			opcion = scan.nextLine();
			
			switch(opcion) {
				 
			case "1":
				controlador.addArticuloDecoracion(nuevoArticuloDecoracion());
				break;
				
			case "2":
				controlador.addArticuloHogar(nuevoArticuloHogar());
				break;
				
			case "0":
				System.out.println("Volviendo al menú.");
				salir = true;
				break;
				
			default:
				System.err.println("La opción no es válida.");
			}
		}
	}
	
	private static Decoracion nuevoArticuloDecoracion() {
		int id = controlador.idDisponible();
		System.out.println(id);
		String tipo = menuTipo();
		String color = menuColor();
		Coleccion coleccion = menuColeccion();
		int stock = 0;
		do {
			System.out.println("Stock del artículo: ");
				stock = Integer.parseInt(scan.nextLine());
			if(stock < 0) {
				System.err.println("El stock no puede ser inferior a 0.");
			}
		}while(stock < 0);
		double precio = 0;
		do {
			System.out.println("Precio del artículo: ");
			precio = Double.parseDouble(scan.nextLine());
			BigDecimal bd = new BigDecimal(precio).setScale(2, RoundingMode.HALF_UP);
			precio = bd.doubleValue();
			if(precio <= 0) {
				System.out.println("El precio no puede ser inferior o igual a 0");
			}
		}while(precio <= 0);
		Decoracion toret = new Decoracion(id, stock, precio, tipo, color, coleccion);
		System.out.println(toret.toString());
		
		return toret;
	}
	
	private static String menuTipo() {
		String opcion;
		String toret = "";
		boolean salir = false;
		
		while(!salir) {
			System.out.println("Tipo de decoración: \n"
					+ "\t1. Espejo\n"
					+ "\t2. Reloj\n"
					+ "\t3. Farol\n"
					+ "\t4. Plantas artificiales\n"
					+ "\t5. Macetero\n"
					+ "\t6. Jarron\n"
					+ "\t7. Cesto\n"
					+ "\t8. Portavelas\n"
					+ "\t9. Figuras decorativas\n"
					+ "\t10. Centros de mesa\n"
					+ "\t11. PortaFotos\n"
					+ "\t12. Otros\n"
					+ "Introduzca el tipo de decoración: ");
			opcion = scan.nextLine();
			
			switch(opcion) {
				case "1": toret = "Espejo";
					salir = true;
					break;
				case "2": toret = "Reloj";
					salir = true;
					break;
				case "3": toret = "Farol";
					salir = true;
					break;
				case "4": toret = "Plantas artificiales";
					salir = true;
					break;
				case "5": toret = "Macetero";
					salir = true;
					break;
				case "6": toret = "Jarron";
					salir = true;
					break;
				case "7": toret = "Cesto";
					salir = true;
					break;
				case "8": toret = "Portavelas";
					salir = true;
					break;
				case "9": toret = "Figuras decorativas";
					salir = true;
					break;
				case "10": toret = "Centros de mesa";
					salir = true;
					break;
				case "11": toret = "Portafotos";
					salir = true;
					break;
				case "12": toret = "Otros";
					salir = true;
					break;
				default:
					System.err.println("\n\tLa opción no es válida.\n");
					break;
			}
		}
			
		
		return toret;
		
	}
	
	private static String menuColor() {
		String opcion;
		String toret = "";
		boolean salir = false;
		
		while(!salir) {
			System.out.println("Color: \n"
					+ "\t1. Blanco\n"
					+ "\t2. Negro\n"
					+ "\t3. Gris\n"
					+ "\t4. Beige\n"
					+ "\t5. Azul\n"
					+ "\t6. Malva\n"
					+ "\t7. Burdeos\n"
					+ "\t8. Otro\n"
					+ "Introduzca el color: ");
			opcion = scan.nextLine();
			
			switch(opcion) {
				case "1": toret = "Blanco";
					salir = true;
					break;
				case "2": toret = "Negro";
					salir = true;
					break;
				case "3": toret = "Gris";
					salir = true;
					break;
				case "4": toret = "Beige";
					salir = true;
					break;
				case "5": toret = "Azul";
					salir = true;
					break;
				case "6": toret = "Malva";
					salir = true;
					break;
				case "7": toret = "Burdeos";
					salir = true;
					break;
				case "8": toret = "Otro";
					salir = true;
					break;
				default:
					System.err.println("\n\tLa opción no es válida.\n");
					break;
			}
		}
			
		
		return toret;
	}
	
	private static Coleccion menuColeccion() {
		String opcion;
		Coleccion toret = new Coleccion();
		boolean salir = false;
		
		while(!salir) {
			System.out.println("Colección:\n"
					+ "\t1. Wild\tTemporada de otoño\t2022\n"
					+ "\t2. Brave hearts\tTemporada de primavera\t2022\n"
					+ "\t3. Air\tTemporada de Invierno\t2022\n"
					+ "\t4. Talentiam\tTemporada de verano\t2022\n"
					+ "Introduzca la colección a la que pertenece el artículo: ");
			opcion = scan.nextLine();
			
			switch(opcion) {
				case "1": toret.setNombre("Wild");
					toret.setTemporada("Otoño");
					toret.setAnho_temporada(2022);
					salir = true;
					break;
				case "2": toret.setNombre("Brave hearts");
					toret.setTemporada("Primavera");
					toret.setAnho_temporada(2022);
					salir = true;
					break;
				case "3": toret.setNombre("Air");
					toret.setTemporada("Invierno");
					toret.setAnho_temporada(2022);
					salir = true;
					break;
				case "4": toret.setNombre("Talentiam");
					toret.setTemporada("Verano");
					toret.setAnho_temporada(2022);
					salir = true;
					break;
				default:
					System.err.println("\n\tLa opción no es válida.\n");
					break;
			}
		}
			
		
		return toret;
		
	}
	private static Hogar nuevoArticuloHogar() {
		int id = controlador.idDisponible();
		System.out.println(id);
		String tipo = tipoArticuloHogar();
		String color = menuColor();
		String estancia = estanciaArticuloHogar();
		
		int stock = 0;
		do {
			System.out.println("Stock del artículo: ");
			stock = Integer.parseInt(scan.nextLine());
			if(stock < 0) {
				System.err.println("El stock no puede ser inferior a 0.");
			}
		}while(stock < 0);
		double precio = 0;
		do {
			System.out.println("Precio del artículo: ");
			precio = Double.parseDouble(scan.nextLine());
			BigDecimal bd = new BigDecimal(precio).setScale(2, RoundingMode.HALF_UP);
			precio = bd.doubleValue();
			if(precio <= 0) {
				System.out.println("El precio no puede ser inferior o igual a 0");
			}
		}while(precio <= 0);
		
		Hogar toret = new Hogar(id, stock, precio, tipo, color, estancia);
		System.out.println(toret.toString());
		
		
		return toret;
		
	}
	
	private static String tipoArticuloHogar() {
		String opcion;
		String tipo = "";
		boolean salir = false;
		
		while(!salir) {
			System.out.println("Tipo de artículo de hogar:\n"
					+ "\t1. Toalla\n"
					+ "\t2. Manta\n"
					+ "\t3. Alfombra\n"
					+ "\t4. Cojines\n"
					+ "\t5. Botellero\n"
					+ "\t6. Salvamantel\n"
					+ "\t7. Plato llano\n"
					+ "\t8. Plato hondo\n"
					+ "\t9. Plato de postre\n"
					+ "\t10. Copa de cristal\n"
					+ "\t11. Vaso de cristal\n"
					+ "\t12. Frutero\n"
					+ "\t13. Taza\n"
					+ "\t14. Dispensador de jabón\n"
					+ "\t15. Jabonera\n");
			opcion = scan.nextLine();
			
			switch(opcion) {
				case "1": tipo = "Toalla";
					salir = true;
					break;
				case "2": tipo = "Manta";
					salir = true;
					break;
				case "3": tipo = "Alfombra";
					salir = true;
					break;
				case "4": tipo = "Cojines";
					salir = true;
					break;
				case "5": tipo = "Botellero";
					salir = true;
					break;
				case "6": tipo = "Salvamantel";
					salir = true;
					break;
				case "7": tipo = "Plato llano";
					salir = true;
					break;
				case "8": tipo = "Plato hondo";
					salir = true;
					break;
				case "9": tipo = "Plato de postre";
					salir = true;
					break;
				case "10": tipo = "Copa de cristal";
					salir = true;
					break;
				case "11": tipo = "Vaso de cristal";
					salir = true;
					break;
				case "12": tipo = "Frutero";
					salir = true;
					break;
				case "13": tipo = "Taza";
					salir = true;
					break;
				case "14": tipo = "Dispensador de jabón";
					salir = true;
					break;
				case "15": tipo = "Jabonera";
					salir = true;
					break;
				default: System.err.println("\n\tLa opción introducida no es válida.\n");
					break;
			}
		}
		
		return tipo;
		
	}

	private static String estanciaArticuloHogar() {
		String opcion;
		String toret = "";
		boolean salir = false;
		
		while(!salir) {
			System.out.println("Estancia a la que pertenece:\n"
					+ "\t1. Salón\n"
					+ "\t2. Cocina\n"
					+ "\t3. Cuarto de baño\n"
					+ "\t4. Comedor\n");
			
			opcion = scan.nextLine();
			switch(opcion) {
				case "1": toret = "Salón";
					salir = true;
					break;
				case "2": toret = "Cocina";
					salir = true;
					break;
				case "3": toret = "Cuarto de baño";
					salir = true;
					break;
				case "4": toret = "Comedor";
					salir = true;
					break;
				default: System.err.println("\n\tLa opción introducida no es válida.\n");
				break;
			}
		}
		
		return toret;
		
	}
	
	public static void elegirArticuloModificar() throws SQLException {
		
		controlador.mostrarArticuloCorto();
		int codigo;

		boolean encontrado = false;
		System.out.println("Introduce el codigo del artículo:");
		codigo = Integer.parseInt(scan.nextLine());
		int pos = -1;

		if(controlador.idCorrecto((codigo)) == -1) {
			System.err.println("El código del artículo no existe");
		} else {
			pos = controlador.idCorrecto(codigo);
			encontrado = true;
			controlador.verArticulo(codigo);
		}
		if(encontrado) {
			if(controlador.articuloDecoracion(codigo)) { //Comprueba si es un artículo de decoración
				menuModificarDecoracion(pos, codigo);
			} else {
				menuModificarHogar(pos, codigo);
			}
		}
	
	}
	
	public static void menuModificarDecoracion(int pos, int codigo) throws SQLException {
		String opcion;
		boolean salir = false;
		
		while(!salir) {
			System.out.println("\t--Menú Modificar--\n\tOpciones de modificación:\n" + "\t1. Stock.\n"
					+ "\t2. Precio.\n" + "\t3. Tipo.\n"
					+ "\t4. Color.\n" + "\t5. Colección.\n"
					+ "\t0. Regresar al menú principal." + "\nIntroduzca su opción: ");
			opcion = scan.nextLine();
			
			switch(opcion) {
			
			case "1":
				int stock = 0;
				do {
					System.out.println("Introduzca el nuevo stock del artículo: ");
					stock = Integer.parseInt(scan.nextLine());
					controlador.editStock(codigo, stock, pos);
				}while(stock < 0);
				
				controlador.verArticulo(codigo);
				break;
				
				
			case "2":
				double precio = 0;
				do {
					System.out.println("Introduzca el nuevo precio del artículo: ");
					precio = Double.parseDouble(scan.nextLine());
					BigDecimal bd = new BigDecimal(precio).setScale(2, RoundingMode.HALF_UP);
					precio = bd.doubleValue();
					controlador.editPrecio(codigo, precio, pos);
				
				}while(precio <= 0);
				controlador.verArticulo(codigo);
				break;
				
			case "3":
				String tipo = menuTipo();
				controlador.editTipoDecoracion(codigo, tipo, pos);
				controlador.verArticulo(codigo);
				break;
				
			case "4":
				String color = menuColor();
				controlador.editcolorDecoracion(codigo, color, pos);
				controlador.verArticulo(codigo);
				break;
				
			case "5":
				Coleccion aux = menuColeccion();
				String coleccion = aux.getNombre();
				controlador.editColeccion(codigo, coleccion, pos, aux);
				controlador.verArticulo(codigo);
				break;
			
			case "0":
				System.out.println("Volviendo\n");
				salir = true;
				break;
				
			default:
				System.err.println("\n\tLa opción no es válida\n");
				break;
			
			}
		}
		
	}

	public static void menuModificarHogar(int pos, int codigo) throws SQLException {
		String opcion;
		boolean salir = false;
		
		while(!salir) {
			System.out.println("\t--Menú Modificar--\n\tOpciones de modificación:\n" + "\t1. Stock.\n"
					+ "\t2. Precio.\n" + "\t3. Tipo.\n"
					+ "\t4. Color.\n" + "\t5. Estancia.\n"
					+ "\t0. Regresar al menú principal." + "\nIntroduzca su opción: ");
			opcion = scan.nextLine();
			
			switch(opcion) {
			
			case "1":
				int stock = 0;
				do {
					System.out.println("Introduzca el nuevo stock del artículo: ");
					stock = Integer.parseInt(scan.nextLine());
					controlador.editStock(codigo, stock, pos);
				}while(stock < 0);
				controlador.verArticulo(codigo);
				break;
				
			case "2":
				double precio = 0;
				do {
					System.out.println("Introduzca el nuevo precio del artículo: ");
					precio = Double.parseDouble(scan.nextLine());
					BigDecimal bd = new BigDecimal(precio).setScale(2, RoundingMode.HALF_UP);
					precio = bd.doubleValue();
					controlador.editPrecio(codigo, precio, pos);
				
				}while(precio <= 0);
				controlador.verArticulo(codigo);
				break;
				
			case "3":
				String tipo = tipoArticuloHogar();
				controlador.editTipoHogar(codigo, tipo, pos);
				controlador.verArticulo(codigo);
				break;
				
			case "4":
				String color = menuColor();
				controlador.editcolorHogar(codigo, color, pos);
				controlador.verArticulo(codigo);
				break;
				
			case "5":
				String estancia = estanciaArticuloHogar();
				controlador.editEstancia(codigo, estancia, pos);
				controlador.verArticulo(codigo);
				break;
				
			case "0":
				salir = true;
				System.out.println("Volviendo");
				break;
				
			default:
				System.err.println("\n\tLa opción no es válida.");
				break;
			}
		}

	}
	
	public static void eliminarArticulo() throws SQLException {
		String opcion;
		boolean salir = false;
		
		controlador.mostrarArticuloCorto();
		
		while(!salir) {
			System.out.println("\nOpciones:\n" + "\t1. Eliminar un artículo\n" + "\t0. Salir\n" + "Opcion:");
			opcion = scan.nextLine();
			
			switch(opcion) {
			
			case "1":
				int codigo;
				System.out.println("Introduce el codigo del artículo que desea eliminar:");
				codigo = Integer.parseInt(scan.nextLine());
				int pos = -1;
				boolean encontrado = false;
				if(controlador.idCorrecto(codigo) == -1) {
					System.err.println("\n\tEl código del artículo no existe.\n");
				} else {
					pos = controlador.idCorrecto(codigo);
					encontrado = true;
				}
				
				if(encontrado) {
					String opcion2;
					do {
						System.out.println("¿Está seguro de que desea eliminar el artículo?\n"
								+ "Introduzca 's' para si y 'n' para no.");
						opcion2 = scan.nextLine();
						opcion2 = opcion2.toLowerCase();
						
						if(opcion2.equals("s")) {
							controlador.deleteArticulo(codigo, pos);
							System.out.println("\tEl artículo ha sido eliminado con éxito");
							
						} else if (opcion2.equals("n")) {
							System.out.println("\tEl artículo no ha sido eliminado.\n");
						}
						
						if (!opcion2.equals("s") && !opcion2.equals("n")) {
							System.out.println("\tLa opción no es válida");
						}
					}while(!opcion2.equals("s") && !opcion2.equals("n"));
				}
				
				
				break;
				
			case "0":
				salir = true;
				System.out.println("\n\tVolviendo al menú principal.\n");
				
				break;
				
			default:
				System.err.println("\n\tLa opción introducida no es válida.\n");
				break;
			}
		}
		
	}
	
	public static void menu_mas_vendidos() {
		String opcion;
		boolean salir = false;
		
		while(!salir) {
			System.out.println("Seleccione una opción:\n"
					+ "1. Artículos de decoración.\n"
					+ "2. Artículos de hogar.\n"
					+ "0. Salir.\n"
					+ "\tIntroduzca su opción: ");
			opcion = scan.nextLine();
			
			switch(opcion) {
			
			case "1":
				controlador.mostrar_mas_vendidos_decoracion();
				break;
				
			case "2":
				controlador.mostrar_mas_vendidos_hogar();
				break;
				
			case "0":
				salir = true;
				System.out.println("\tVolviendo al menú principal\n");
				break;
				
			default:
				System.err.println("La opción introducida no es válida.");
				break;
			}
		}
	}
	
	public static void menuMostrarArticulos() {
		String opcion;
		boolean salir = false;
		
		while(!salir) {
			System.out.println("\t1. Mostrar todos los artículos.\n"
					+ "\t2. Mostrar los artículos de decoración.\n"
					+ "\t3. Mostrar los artículos de hogar.\n"
					+ "\t0. Salir\n"
					+ "\tIntroduzca la opción: ");
			opcion = scan.nextLine();
			
			switch(opcion) {
			
			case "1":
				controlador.mostrarArticulo();
				break;
				
			case "2":
				controlador.mostraArticuloDecoracion();
				break;
				
			case "3":
				controlador.mostraArticuloHogar();
				break;
				
			case "0":
				salir = true;
				System.out.println("Regresando al menú principal.");
				break;
				
			default:
				System.err.println("\tLa opción introducida no es válida.\n");
				break;
			}
		}
		
		
	}
}
