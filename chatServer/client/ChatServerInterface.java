import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerInterface extends Remote{
  static String LOOKUP_NAME = "ChatServer" ; 
  public void sentToAll(String SenderName, String message) throws RemoteException ;
  public void sentToOne(String SenderName, String receverName , String message) throws RemoteException ;
  public void signIn(ClientDistantInterface c) throws RemoteException ;
  public void signOut(ClientDistantInterface c , String message) throws RemoteException ;
}
