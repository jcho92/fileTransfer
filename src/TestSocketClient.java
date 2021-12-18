
import java.net.*;
import java.io.*;
public class TestSocketClient {
	 static Socket socket = null;
     static String host = "127.0.0.1";
     static Socket fileSocket = null;
    public static void main(String[] args) throws IOException {
       

        socket = new Socket(host, 4444);
        fileSocket = new Socket(host, 4445);
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
            		System.out.println("the server is ready to accept your call: ");
            		System.out.println("do you want to send a file?");
            		
            		if(stdIn.readLine().equals("yes")) {
            			System.out.println("alright sending file now");
            			sendFile();
            			System.out.println("file is completed");
            		}else {
            			System.out.println("yes was not typed, not sending file now");
            		}
            			out.println("file is done");
            	}else {
            		System.out.println(responseFromServer);
                	out.println(stdIn.readLine());
            	}
            	
            }
            System.out.println("ending");
            socket.close();
        }catch(IOException e) {
        	System.out.println(e);
        }
    }
        
        
    
    public static void sendFile() throws IOException {
    	
	   File file = new File("test.txt");
	   // Get the size of the file
	   byte[] bytes = new byte[8192];
	
	   try(
		   InputStream in = new FileInputStream(file);
		   OutputStream out = fileSocket.getOutputStream();
			   ){
		   int count;
		   while ((count = in.read(bytes)) > 0) {
		       out.write(bytes, 0, count);
		   }
		   System.out.println("file is done");
		   out.close();
		   in.close();
	   }catch(IOException e) {
		   System.out.println(e);
	   }
    }
}