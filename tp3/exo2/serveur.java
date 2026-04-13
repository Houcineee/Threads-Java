import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class serveur {

  final static int port = 7878;
  final static int taille = 1024;

  final static byte buffer[] = new byte[taille];

  public static void main(String argv[]) throws Exception
  {
    DatagramSocket socket = new DatagramSocket(port);
    while(true)
    {
      DatagramPacket datarecv = new DatagramPacket(buffer,taille);
      socket.receive(datarecv);
      System.out.println(new String(datarecv.getData()).trim());
      String message = "Bonjour Mr le client";
      int length = message.length();
      byte buffer_send[] = message.getBytes();

      DatagramPacket dataSent = new
        DatagramPacket(buffer_send,length,datarecv.getAddress(),datarecv.getPort());

      socket.send(dataSent);
    }
  }

}
