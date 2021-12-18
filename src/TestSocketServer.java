import java.net.*;
import java.io.*;
public class TestSocketServer {
	static int portNumber = 4446;
	static Socket socket = null;
	
    static InputStream in = null;
    static OutputStream out = null;
    static Socket fileSocket = null; 
	static ServerSocket fileServerSocket = null;
	static ServerSocket serverSocket = null;
	static Socket useThisSocket = null;
    public static void main(String[] args) throws IOException {
       
        try {
            serverSocket = new ServerSocket(4444);
            fileServerSocket = new ServerSocket(4445);
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }
       
        try {
            socket = serverSocket.accept();
            fileSocket = fileServerSocket.accept();
        } catch (IOException ex) {
            System.out.println("Can't accept client connection. ");

        	System.out.println(ex);
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
			  System.out.println(inputLine);
			  switch(inputLine) {
			  case "upload":
				  out.println(String.valueOf(portNumber));
				  useThisSocket = setUpNewSocket(portNumber);
				  break;
			  case "socket ready":
				  System.out.println("the socket is ready");
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
    
    public static boolean createFile(String name, PrintWriter output, String input) throws IOException {
    	
		 InputStream in = null;
		 OutputStream out = null;
    	 System.out.println("we are now ready to do some file creation");
    	 output.println("ready");
    		 try {
                 in =  useThisSocket.getInputStream();
             } catch (IOException ex) {
            	 System.out.println(ex);
                 System.out.println("Can't get socket input stream. ");
             }

             try {
                 out = new FileOutputStream("downloadTest/" + name);
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
    	return true;
    }
    
    public static Socket setUpNewSocket(int port) {
    	ServerSocket secondServerSocket = null;
    	Socket secondTransferSocket = null;
    	try {
    		secondServerSocket = new ServerSocket(port);
    	} catch(IOException e) {
    		System.out.println(e);
    	}
    	
    	try {
    		secondTransferSocket = secondServerSocket.accept();
    	} catch(IOException e) {
    		System.out.println(e);
    	}
    	portNumber ++;
    	return secondTransferSocket;
    	
    }
}

