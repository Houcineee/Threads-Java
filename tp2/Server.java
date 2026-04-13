import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;


public class Server {
    private static Selector selector = null;

    private static void handleAccept(ServerSocketChannel mySocket, SelectionKey key) throws IOException {
        System.out.println("Connection Accepted..");
        SocketChannel client = mySocket.accept(); // Accept the connection
        client.configureBlocking(false); // set non-blocking mode
        // Register that client is reading this channel
        client.register(selector, SelectionKey.OP_READ);
        askTheUser(client , "");
    }
    private static void askTheUser(SocketChannel client , String phrase ) throws IOException {
      // SocketChannel client = (SocketChannel) key.channel();
      ByteBuffer buffer = ByteBuffer.allocate(4098) ; 
      LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
      String formattedDate = now.format(formatter);
      String message = phrase +"\nThis is the time: :" + formattedDate ; 
      buffer.put(message.getBytes()) ; 
      buffer.flip();
      client.write(buffer); // Writes a sequence of bytes to this channel from the given buffer

    }

    private static void handleRead(SelectionKey key) throws IOException {
        System.out.println("Reading client's message...");
        // create a SocketChannel to read the request
        SocketChannel client = (SocketChannel) key.channel();
        // Create buffer to read data
        ByteBuffer buffer = ByteBuffer.allocate(4098); // create an empty buffer
        client.read(buffer); // Reads a sequence of bytes from this channel into the given buffer
        
        // Parse data from buffer to String
        String data = new String(buffer.array()).trim();
        System.out.println("Received message: " + data);
        if (data.equals("bye")) {
          client.close(); 
          return ; 
        }
        askTheUser(client, data.toUpperCase());
        
    }

    public static void main(String[] args) {
        try {
            selector = Selector.open();
            // We have to set connection host, port and non-blocking mode
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress("localhost", 8089));
            serverSocketChannel.configureBlocking(false);
            
            int ops = serverSocketChannel.validOps();
            serverSocketChannel.register(selector, ops, null);
            System.out.println("Listening on 8089.......");

            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> i = selectedKeys.iterator();
                
                while (i.hasNext()) {
                    SelectionKey key = i.next();
                    if (key.isAcceptable()) {
                        handleAccept(serverSocketChannel, key); // New client has been accepted
                    }
                    else if (key.isReadable()) {
                        // We can run non-blocking operation READ on our client
                        handleRead(key);
                    }
                    i.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
