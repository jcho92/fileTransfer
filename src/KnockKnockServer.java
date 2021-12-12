import java.net.*;
import java.io.*;
 
public class KnockKnockServer {
    public static void main(String[] args) throws IOException {
         
        if (args.length != 1) {
            System.err.println("Usage: java KnockKnockServer <port number>");
            System.exit(1);
        }
 
        int portNumber = Integer.parseInt(args[0]);
        System.out.println(portNumber);
        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
         
            String inputLine, outputLine;
             
            // Initiate conversation with client
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);
            while ((inputLine = in.readLine()) != null) {
                //outputLine = kkp.processInput(inputLine);
                //out.println(outputLine);
                //out.println(inputLine);
                out.println(inputLine);
//                if(inputLine.equals("build me something")) {
//                	
//                	System.out.println("we are in here lets do this");
//                   	FileInputStream inData = new FileInputStream("demo.txt");
//	                FileOutputStream outData = new FileOutputStream("testing.txt");
//	                boolean eof = false;
//        			while(!eof) {
//        				int input = inData.read();
//        				System.out.println(input);
//        				
//        				if (input == -1) {
//        					eof = true;
//        					break;
//        				}
//        				outData.write(input);
//        			}
//        			inData.close();
//        			outData.close();
//                }
                
                if (inputLine.equals("bye"));
                	out.print("shutting down");
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
        System.out.println("shutting down now");
    }
}


