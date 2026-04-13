import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  final static int PORT=8080 ; 
  final static String HOST="127.0.0.1" ; 
  public static void main(String args[]) throws Exception{
    Scanner sc = new Scanner(System.in) ; 
    Socket socket = new Socket(HOST, PORT) ; 
    System.out.println("The TCP Request is sent");
    OutputStream OS = socket.getOutputStream() ; 
    PrintWriter out = new PrintWriter(OS) ; 
    InputStream IS = socket.getInputStream() ; 
    InputStreamReader ISR = new InputStreamReader(IS) ; 
    BufferedReader reader = new BufferedReader(ISR) ; 


    ObjectInputStream ois = new ObjectInputStream(IS) ; 
    Voiture v1 = (Voiture) ois.readObject(); 
    System.out.println("The object is received : " + v1.carburant + v1.mat);


    socket.close();
    sc.close();

  }
}
