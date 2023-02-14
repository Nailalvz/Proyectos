package vista;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;


public class Main {
	private static Scanner scan = new Scanner(System.in);


	public static void main(String[] args) {
		
		try {
			
			String opcion1;
			boolean salir1 = false;
			
			String user;
			String pass;
			boolean correcto = false;
			
			
			while(!salir1) {
				System.out.println("Bienvenido a la app de decoración y artículos para el hogar\n"
						+ "Seleccione una opción:\n"
						+ "\t1. Identificarse.\n"
						+ "\t0. Salir.");
				opcion1 = scan.nextLine();
				
				switch(opcion1) {
				
				case "1":
					while(!correcto) {
						System.out.println("Identificación:\n"
								+ "\tUsuario:");
						user = scan.nextLine();
						System.out.println("\tContraseña:");
						pass = scan.nextLine();
						
						Properties leyendo = new Properties();
						try {
							leyendo.load(new FileInputStream("config.props"));
						} catch (FileNotFoundException e) {

							escribirErrBD(e);
							System.err.println(e.getMessage());
						} catch (IOException e) {
							escribirErrBD(e);
							System.err.println(e.getMessage());
							e.printStackTrace();
						}
						String userP = leyendo.getProperty("user");
						String passP = leyendo.getProperty("pass");

				
						if(userP.equals(user) && passP.equals(pass)) {
							correcto = true;
							String opcion;
							boolean salir = false;
							while(!salir) {
								System.out.println("Seleccione una opción del menú: "
										+ "\n\t1. Gestionar artículos."
										+ "\n\t0. Cerrar sesión");
								opcion = scan.nextLine();
								switch(opcion) {
								case "1": 
									ArticuloVista.gestionaArticulos();
									break;
									
								case "0":
									System.out.println("Sesión cerrada");
									salir = true;
									break;
									
								default:
									System.err.println("La opción no es válida.\n\tVuelva a intentarlo.");
								}
							}
						} else {
							System.err.println("El usuario o la contraseña no son correctos.");
						}
						
					}
					break;
					
				case "0":
					salir1 = true;
					System.out.println("Fin del programa.");
					break;
					
				default:
					System.err.println("La opción introducida no es válida");
					break;
				}
			}

		} catch (SQLException e) {
			escribirErrBD(e);
			System.out.println(e.getMessage());
		} catch (Exception e) {
			escribirErrBD(e);
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public static void escribirErrBD(Exception e) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		FileReader fr = null;
		BufferedReader br = null;
		ArrayList<String> lineas = new ArrayList<String>();
		String lastLine = "";
		String linea;

		
		try {
			fr = new FileReader("errores.xml");
			br = new BufferedReader(fr);

		while(((linea = br.readLine()) != null)) {
			lineas.add(linea);
		}
		
		for (int i = 0; i < lineas.size(); i++) {
			lastLine = lineas.get(i);
		}

			fw = new FileWriter("errores.xml");
			
			bw = new BufferedWriter(fw);
			
			for(int i = 0; i < lineas.size() -1; i++) {
				bw.write(lineas.get(i));
				bw.write("\n");
			}
			bw.write("\n");
			bw.write("<error ");
			bw.write("codigo = \"Error sin código\">");
			bw.write(e.getMessage());
			bw.write("</error>");
			bw.write("\n");
			bw.write(lastLine);
			bw.flush();
			
		}catch (IOException e1) {
			System.out.println(e1.getMessage());
		} finally {
			try {
				if(fw != null) {
					fw.close();
				}
				
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e2){
				System.out.println(e2.getMessage());
			}
		}
	}
	
	public static void escribirErrBD(SQLException e) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		FileReader fr = null;
		BufferedReader br = null;
		ArrayList<String> lineas = new ArrayList<String>();
		String lastLine = "";
		String linea;

		
		try {
			fr = new FileReader("errores.xml");
			br = new BufferedReader(fr);

		while(((linea = br.readLine()) != null)) {
			lineas.add(linea);
		}
		
		for (int i = 0; i < lineas.size(); i++) {
			lastLine = lineas.get(i);
		}

			fw = new FileWriter("errores.xml");
			
			bw = new BufferedWriter(fw);
			
			for(int i = 0; i < lineas.size() -1; i++) {
				bw.write(lineas.get(i));
				bw.write("\n");
			}
			bw.write("\n");
			bw.write("<error ");
			bw.write("codigo =" + "\"" + e.getErrorCode()+"\"" +">");
			bw.write(e.getMessage());
			bw.write("</error>");
			bw.write("\n");
			bw.write(lastLine);
			bw.flush();
			
		}catch (IOException e1) {
			System.out.println(e1.getMessage());
			escribirErrBD(e);
		} finally {
			try {
				if(fw != null) {
					fw.close();
				}
				
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e2){
				System.out.println(e2.getMessage());
				escribirErrBD(e);
			}
		}
	}
	

}
