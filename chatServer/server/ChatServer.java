import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ChatServer extends UnicastRemoteObject implements ChatServerInterface {
  private final Map<String, ClientDistantInterface> clients;

  public ChatServer() throws RemoteException {
    super();
    this.clients = new HashMap<>();
  }

  @Override
  public  void sentToAll(String SenderName, String message) throws RemoteException {
    String formatted = SenderName + " : " + message;
    for (ClientDistantInterface client : clients.values()) {
      client.receiveMessage(formatted);
    }
  }

  @Override
  public  void sentToOne(String SenderName, String receverName, String message) throws RemoteException {
    String formatted = SenderName + " : " + message;
    ClientDistantInterface client = clients.get(receverName);
    if (client != null) {
      client.receiveMessage(formatted);
    }
  }

  @Override
  public  void signIn(ClientDistantInterface c) throws RemoteException {
    clients.put(c.getNameDistant(), c);
  }

  @Override
  public  void signOut(ClientDistantInterface c, String message) throws RemoteException {
    String name = c.getNameDistant(); 
    clients.remove(name);
    sentToAll(name, name + "left and this is the last message " + message);
  }


}
