import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner clavier = new Scanner(System.in);
        try {
            SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", 8089));
            System.out.println("Connection accepted by the Server..");
            
            ByteBuffer buffer = ByteBuffer.allocate(4098); // create an empty buffer
           while(true){
             buffer.clear() ; 
             int x = client.read(buffer); // Reads a sequence of bytes from this channel into the given buffer
             if(x==-1) break; 

             String data = new String(buffer.array()).trim();
             System.out.println(data);
             System.out.print("Saisir le message à envoyer au serveur: ");
             String msg = clavier.nextLine();
             buffer.clear();                
             buffer.put(msg.getBytes()) ; 
             buffer.flip();
             client.write(buffer);
           }
            
            
            
            client.close();
            clavier.close(); 
            System.out.println("Client connection closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
