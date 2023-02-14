import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class VocalCounter {
    
	//Cuenta la vocal que le llega del fichero seleccionado y lo guarda en un fichero de salida
    public static boolean counter(String fileName, char vowel, String fileResultName){
	
        boolean toret = false;
		vowel = Character.toLowerCase(vowel);

        BufferedReader br = FilesProperties.getBufferedReader(fileName);
		int contador = 0;
		try {
			String linea = br.readLine();
			
			while(linea != null) {
				for(int i = 0; i < linea.length(); i++) {
					if(Character.toLowerCase(linea.charAt(i)) == vowel) {
						contador++;
					}
				}
				linea = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		//Escribo en el archivo
		PrintWriter pw = FilesProperties.getPrintWriter(fileResultName);
		pw.println(contador);
		pw.flush();
		pw.close();
			
		//Compruebo que la primera línea del fichero no es null
        BufferedReader br2 = FilesProperties.getBufferedReader(fileResultName);
         try {
            String lineaAux = br2.readLine();

            if(lineaAux == null){
                toret = false;
            } else {
                toret = true;
            }
         }catch(IOException e){
            System.err.println(e.getMessage());
         }

		return toret;

    }


    public static void main(String[] args) {
		String nombreFicheroEntrada = args[0];
        String letra = args[1];
        char letraAux = letra.charAt(0);
        String nombreFicheroResultado = args[2];

		counter(nombreFicheroEntrada, letraAux, nombreFicheroResultado);

	}


}
