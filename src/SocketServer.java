
import java.io.*;
import java.net.*;
import java.util.*;
public class SocketServer extends Thread{
private ServerSocket sock;
	public SocketServer() {
		super();
		try {
			sock = new ServerSocket(4415);
			System.out.println("server is now running");
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("shit broke");
		}
	}
	
	@Override
	public void run() {
		Socket client;
		
		while(true) {
			if (sock == null)
				return;
			try {
				client = sock.accept();
				BufferedOutputStream bb = new BufferedOutputStream(client.getOutputStream());
			PrintWriter os = new PrintWriter(bb, false);
			String outLine;
			File file = new File("test.txt");
			File dest = new File("testing.txt");
			try {
				copyFileUsingStream(file, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date now = new Date();
			os.println("WHAT UP BITCH YOU MADE IT TO MY LOCALHOST MOTHER FUCKER");
			os.println(now);
			os.flush();
			
			os.close();client.close();
			}catch(Exception e) {
				System.out.println(e);
			}
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
	
	public static void main (String[] arguments) {
		SocketServer server = new SocketServer();
		server.start();
	}
}
