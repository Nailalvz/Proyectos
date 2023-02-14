
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.util.ArrayList;
import java.io.File;

public class Menu {
    public static void main(String[] args) {

        String vocal_counter = args[0].toString();

        //Si el primer argumento que le llega no contiene VocalCounter muestra un mensaje de error, si no ejecuta el menú
        //He puesto que contenga VocalCounter sin .java ya que he comprobado que en los diferentes ordenadores de mis compañeros
        //puede ser que se ejecute con el .java o sin él. 
        //En mi ordenador si ejecuto el VocalCounter con .class me ejecuta el menú pero no cuenta las vocales y no se como controlarlo
        if(!(vocal_counter.contains("VocalCounter"))){
            JOptionPane.showMessageDialog(null, "El argumento no es correcto", "ERROR", JOptionPane.ERROR_MESSAGE);

        } else {
            boolean salir = false;

            String array[] =  {"Procesar un nuevo archivo", "Mostrar resultados", "Salir"};
    
            while(!salir){
                int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, array, array[0]);
    
                switch(seleccion){
                    case 0:
                       String ruta = devolverFichero();
    
                       if(ruta.equals("cancelado")){
                            JOptionPane.showMessageDialog(null, "No se ha seleccionado el fichero correctamente", "ERROR", JOptionPane.ERROR_MESSAGE);
                            break;
                       } else {
                            getProcess(vocal_counter, ruta);
                       }
    
                    break;
                    
                    case 1:
                        mostrar();
                    break;
    
                    case 2:
                        salir = true;
                    break;
                }
            }
        }
	}

    //Devuelve la ruta del fichero elegido con el FileChooser
	public static String devolverFichero() {
		String ruta = "";
		JFileChooser fileChooser = new JFileChooser();
		int valor = fileChooser.showOpenDialog(fileChooser);
		//Si escoge un fichero se manda la ruta correcta
		if(valor == JFileChooser.APPROVE_OPTION) {
			ruta = fileChooser.getSelectedFile().getAbsolutePath();
		//Si cancela o no escoge un fichero se manda cancelado apra imprimir un mensaje de error
		} else if(valor == JFileChooser.CANCEL_OPTION || valor == JFileChooser.ERROR_OPTION) {
			ruta = "cancelado";
		} 
		return ruta;
	}

    //Ejecuta los procesos 
    public static void getProcess(String vocal_counter, String ruta){
        ProcessBuilder pb;
        try {
            pb = new ProcessBuilder("java", vocal_counter, ruta, "a", "FicheroSalida_A.txt");
            Process p = pb.start();
            p.waitFor();
            pb = new ProcessBuilder("java", vocal_counter, ruta, "e", "FicheroSalida_E.txt");
            Process p2 = pb.start();
            p2.waitFor();
            pb = new ProcessBuilder("java", vocal_counter, ruta, "i", "FicheroSalida_I.txt");
            Process p3  = pb.start();
            p3.waitFor();
            pb = new ProcessBuilder("java", vocal_counter, ruta, "o", "FicheroSalida_O.txt");
            Process p4 = pb.start();
            p4.waitFor();
            pb = new ProcessBuilder("java", vocal_counter, ruta, "u", "FicheroSalida_U.txt");
            Process p5 = pb.start();
            p5.waitFor();
            
            if(p.isAlive() || p2.isAlive() || p3.isAlive() || p4.isAlive() || p5.isAlive()){
                JOptionPane.showMessageDialog(null, "Se sigue ejecutando", "Waiting", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "Se ha acabado el conteo", "Finalizado", JOptionPane.INFORMATION_MESSAGE);
            }

        }catch(Exception e){
            System.err.println(e.getMessage());
        }

    }

    /*Lee los ficheros de salida de cada vocal y lo almacena en variables para luego sumarlas
        y añadirlas a un fichero final que muestro por pantalla
    */
    public static void mostrar(){
        File fich_A  = new File ("FicheroSalida_A.txt");
        File fich_E  = new File ("FicheroSalida_E.txt");
        File fich_I  = new File ("FicheroSalida_I.txt");
        File fich_O  = new File ("FicheroSalida_O.txt");
        File fich_U  = new File ("FicheroSalida_U.txt");

        //Si los ficheros existen los leo
        if(fich_A.exists() && fich_E.exists() && fich_I.exists() && fich_O.exists() && fich_U.exists()){
            int num_A = leerNumVocales(fich_A);
            int num_E = leerNumVocales(fich_E);
            int num_I = leerNumVocales(fich_I);
            int num_O = leerNumVocales(fich_O);
            int num_U = leerNumVocales(fich_U);
            int total = num_A + num_E + num_I + num_O + num_U;

            PrintWriter pw = FilesProperties.getPrintWriter("ResultadoFinal.txt");

            pw.println("Total 'a': " + String.valueOf(num_A));
            
            pw.println("Total 'e': " + String.valueOf(num_E));
            
            pw.println("Total 'i': " + String.valueOf(num_I));
            
            pw.println("Total 'o': " + String.valueOf(num_O));
            
            pw.println("Total 'u': " + String.valueOf(num_U));
        
            pw.println("En total hay: " + String.valueOf(total));
            pw.flush();
            
            pw.close();
            

            BufferedReader br = FilesProperties.getBufferedReader("ResultadoFinal.txt");

            try {
                String linea = br.readLine();
                ArrayList <String> fichero = new ArrayList<String>(); //Creo un ArrayList para guardar las lineas del fichero

                while(linea != null) {
                    fichero.add(linea); //Añado la lineas del fichero al ArrayList hasta que se acaben
                    linea = br.readLine();
                }
                br.close();
                
            Object[] lineasFichero = fichero.toArray(); //Convierto el ArrayList a un Array para mostrarlo con JOptionPane
            JOptionPane.showMessageDialog(null,lineasFichero);
            
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }	
        //Si no existen los ficheros porque no se ha realizado ningún conteo muestra un mensaje de error    
        } else {
            JOptionPane.showMessageDialog(null, "No se ha relizado ningun conteo", "Vacio", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Lee el fichero que le llega y devuelve un int
    public static int leerNumVocales(File fichero){
        BufferedReader br = FilesProperties.getBufferedReader(fichero);
        
        int num = 0;;

        try {
            String linea;
            while((linea = br.readLine()) != null){
                num = Integer.parseInt(linea);
            }    

        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        return num;
    }


}
