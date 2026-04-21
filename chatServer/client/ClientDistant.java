import java.rmi.RemoteException;

public class ClientDistant extends Client implements ClientDistantInterface {


  public ClientDistant( String name){
    // TODO : generate a random ID
    super("forTest" , name) ; 

  }

  @Override
  public void receiveMessage(String message) throws RemoteException {
    System.out.println("New Message : " + message);
  }

  @Override
  public String getNameDistant() throws RemoteException {
    return this.nom ; 
  }

  @Override
  public String getIdDistant() throws RemoteException {
    return this.Id ; 
  }

}
