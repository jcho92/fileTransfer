
import java.net.*;
import java.io.*;
public class TestSocketClient {
	 static Socket socket = null;
     static String host = "127.0.0.1";
    public static void main(String[] args) throws IOException {
       

        socket = new Socket(host, 4444);
        //sendFile();
        System.out.println("client");
        try( 
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
        	Boolean end = false;
        	
            while(!end) {
            	String responseFromServer = in.readLine();
            	if(responseFromServer.equals("ready")) {
            		out.println(stdIn.readLine());
            		sendFile();
            	}
            	System.out.println(responseFromServer);
            	out.println(stdIn.readLine());
            }
            System.out.println("ending");
            socket.close();
        }catch(IOException e) {
        	System.out.println(e);
        }
    }
        
        
    
    public static void sendFile() throws IOException {
    	Socket fileSocket = new Socket(host, 4445);
	   File file = new File("test.txt");
	   // Get the size of the file
	   byte[] bytes = new byte[8192];
	   InputStream in = new FileInputStream(file);
	   OutputStream out = fileSocket.getOutputStream();
	   
	   int count;
	   while ((count = in.read(bytes)) > 0) {
	       out.write(bytes, 0, count);
	   }
	   out.close();
	   in.close();
    }
}