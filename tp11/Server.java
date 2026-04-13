import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
  final static private int PORT = 8080 ; 
  public static void  main(String args[]) throws Exception{
    ServerSocket socket = new ServerSocket(Server.PORT) ; 
    Scanner input = new Scanner(System.in) ; 
    System.out.println("Server is listening on port 8080...");

    Socket sc = socket.accept() ; 
    System.out.println("Connection accepted" + sc.getInetAddress());
    InputStream IS = sc.getInputStream();
    InputStreamReader isr = new InputStreamReader(IS) ; 
    BufferedReader  reader = new BufferedReader(isr) ; 

    OutputStream out = sc.getOutputStream() ;
    PrintWriter writer = new PrintWriter(out) ; 


    ObjectOutputStream oos = new ObjectOutputStream(out) ; 
    Voiture v1 = new Voiture("Mercedec", 123) ; 
    oos.writeObject(v1);
    socket.close(); 
    input.close();
  }

}
