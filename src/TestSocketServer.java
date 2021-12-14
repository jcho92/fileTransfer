import java.net.*;
import java.io.*;
public class TestSocketServer {
	static Socket socket = null;
    static InputStream in = null;
    static OutputStream out = null;
     
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }
       
        try {
            socket = serverSocket.accept();
            
        } catch (IOException ex) {
            System.out.println("Can't accept client connection. ");
        }
        
        handleClientCommands();
        serverSocket.close();
    }
    
    public static void handleClientCommands() {
	  try(
			PrintWriter out =new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			  )  { 
		 String inputLine;
		 out.println("you have connected to the server");
		  while ((inputLine = in.readLine()) != null) {
			  switch(inputLine) {
			  case "upload":
				  createFile("text2.txt", out, inputLine);
				  break;
			  default:
				  out.println("server response: " + inputLine);
				  break;
			  }
			  if(inputLine.equals("end")) {
	        	  out.println("server response: ending your session");
	        	  break;
	          }
	         

		  }
	 }catch(IOException e) {
		 System.out.println(e);
		 System.out.println("no fucking clue why it is failing here");
	 }
    }
    
    public static void createFile(String name, PrintWriter output, String input) throws IOException {
    	Socket fileSocket = null; 
    	ServerSocket fileServerSocket = null;
		 InputStream in = null;
		 OutputStream out = null;
    	 
    	 try {
    		 fileServerSocket = new ServerSocket(4445);
    	 } catch(IOException e) {
    		 System.out.println(e);
    	 }
    	 
    	 try {
    		 output.println("ready");
    		 fileSocket = fileServerSocket.accept();   
    		 
         } catch (IOException ex) {
             System.out.println("Can't accept client connection. ");
         }
    	 
    		 try {
                 in =  fileSocket.getInputStream();
             } catch (IOException ex) {
                 System.out.println("Can't get socket input stream. ");
             }

             try {
                 out = new FileOutputStream("test2.txt");
             } catch (FileNotFoundException ex) {
                 System.out.println("File not found. ");
             }

             byte[] bytes = new byte[8192];

             int count;
             while ((count = in.read(bytes)) > 0) {
                 out.write(bytes, 0, count);
             }
	         out.close();
	         in.close();
    	
    	
    }
}