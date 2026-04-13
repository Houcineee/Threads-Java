import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class client
{ final static int port = 7878;
  final static int taille = 1024;

  final static byte buffer[] = new byte[taille];
  public static void main(String argv[]) throws Exception
  {
    InetAddress serveur = InetAddress.getByName("localhost");
    String message = "Bonjour Mr le serveur";
    int length = message.length();
    byte buffer_send[] = message.getBytes();
    DatagramPacket dataSent = new DatagramPacket(buffer_send,length,serveur,port);
    DatagramSocket socket = new DatagramSocket();
    socket.send(dataSent);
    DatagramPacket dataRecieved = new DatagramPacket(buffer,taille);
    socket.receive(dataRecieved);
    System.out.println("Data recieved : " + new String(dataRecieved.getData()).trim());
    System.out.println("From : " + dataRecieved.getAddress() + ":" + dataRecieved.getPort());
    socket.close();
  } }
