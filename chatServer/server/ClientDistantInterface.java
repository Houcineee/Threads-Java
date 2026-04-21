import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientDistantInterface extends Remote{
  public void receiveMessage(String message) throws RemoteException ; 
  public String getNameDistant() throws RemoteException ; 
  public String getIdDistant() throws RemoteException ; 
}
