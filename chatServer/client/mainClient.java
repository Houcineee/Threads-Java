import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class mainClient {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ClientDistant client = null;

    try {
      System.out.println("Enter client name:");
      String sender = sc.nextLine();

      client = new ClientDistant(sender);
      ChatServerInterface server =
          (ChatServerInterface) Naming.lookup("rmi://localhost:1099/ChatServer");
      ClientDistantInterface stubClient =
          (ClientDistantInterface) UnicastRemoteObject.exportObject(client, 0);

      server.signIn(stubClient);
      System.out.println("Connected as " + sender);

      System.out.println("Enter receiver name (or empty to skip):");
      String receiver = sc.nextLine();
      if (!receiver.isEmpty()) {
        System.out.println("Enter message:");
        String message = sc.nextLine();
        server.sentToOne(sender, receiver, message);
        System.out.println("Sent to " + receiver + ": " + message);
      }

      System.out.println("Press Enter to sign out...");
      String message = sc.nextLine();
      server.signOut(stubClient, client.nom +  " left with this message : " + message );
      UnicastRemoteObject.unexportObject(client, true);
      System.out.println("Signed out: " + sender);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
