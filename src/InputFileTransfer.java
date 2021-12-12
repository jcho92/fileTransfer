import java.io.*;
import java.nio.file.*;
public class InputFileTransfer {
	public static void main(String[] arguments) {
		//getInputStream();
		File file = new File("test.txt");
		File dest = new File("testing.txt");
		try {
			copyFileUsingStream(file, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getInputStream() {
		try (
			FileInputStream file = new FileInputStream("demo.txt");
			FileOutputStream nextFile = new FileOutputStream("test.txt");
			){
			
			boolean eof = false;
			while(!eof) {
				int input = file.read();
				System.out.println(input);
				
				if (input == -1) {
					eof = true;
					break;
				}
				nextFile.write(input);
			}
					
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		public static void copyFileUsingStream(File source, File dest) throws IOException {
		    InputStream is = null;
		    OutputStream os = null;
		    try {
		        is = new FileInputStream(source);
		        os = new FileOutputStream(dest);
		        byte[] buffer = new byte[1024];
		        int length;
		        while ((length = is.read(buffer)) > 0) {
		            os.write(buffer, 0, length);
		        }
		    } finally {
		        is.close();
		        os.close();
		    }
		}
	
}
	