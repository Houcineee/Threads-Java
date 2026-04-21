import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {

  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(1099);
      ChatServer server = new ChatServer();
      Naming.rebind("rmi://localhost:1099/" + ChatServerInterface.LOOKUP_NAME, server);
      System.out.println("ChatServer is running on rmi://localhost:1099/" + ChatServerInterface.LOOKUP_NAME);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
