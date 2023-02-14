import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;


public class FilesProperties {

    public static BufferedReader getBufferedReader(String fileName) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			
			System.err.println(e.getMessage());
		}
		
		return br;
	}
	
	public static PrintWriter getPrintWriter(String fileName) {
		
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(new FileWriter(fileName));
			
		}catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return pw;
	}

	public static BufferedReader getBufferedReader(File fileName) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			
			System.err.println(e.getMessage());
		}
		
		return br;
	}
}
