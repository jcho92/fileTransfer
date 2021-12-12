
import java.io.*;
public class BufferedFileTransfer {
	
	public static void main(String[] arguments) {
		int start = 0;
		int finish = 255;
		ArgStream as = new ArgStream(start, finish);
		//boolean success = as.writeStream();
		boolean successRead = as.readStream();
	}
	
	static class ArgStream {
		int start = 0;
		int finish = 255;
		ArgStream(int st, int fin){
			start = st;
			finish = fin;
		}
		
		Boolean writeStream() {
			try (FileOutputStream file = new FileOutputStream("numbers.dat");
					BufferedOutputStream buff = new BufferedOutputStream(file)){
						
				for (int out = start; out <= finish; out++) {
					buff.write(out);
					System.out.println(out);
				}
				
				return true;
			}catch(IOException e){
				System.out.println(e);
				return false;
			}
		}
		
		Boolean readStream() {
			try (FileInputStream file = new FileInputStream("numbers.dat");
					BufferedInputStream buff = new BufferedInputStream(file)
					){
				int in ;
				
				do  {
					in = buff.read();
					if (in!= -1);
					System.out.println(in);
				}while (in != -1);
				
				return true;	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
	}
}
