import java.io.IOException;  
import java.net.*;  


public class SocketTest {
 
	    public static void main(String[] args) throws IOException {  
	      Socket socket = new Socket();  
	      InetAddress inetAddress=InetAddress.getByName("localhost");  
	      //the port should be greater or equal to 0, else it will throw an error  
	        int port=4435;  
	        //calling the constructor of the SocketAddress class  
	        SocketAddress socketAddress=new InetSocketAddress(inetAddress, port);  
	        //binding the  socket with the inetAddress and port number  
	        socket.bind(socketAddress);  
	        //connect() method connects the specified socket to the server  
	        socket.connect(socketAddress);  
	        System.out.println("Inet address: "+socket.getInetAddress());  
	        System.out.println("Port number: "+socket.getLocalPort());  
	    }  
	} 
