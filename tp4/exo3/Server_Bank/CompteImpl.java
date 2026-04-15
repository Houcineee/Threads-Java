import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CompteImpl extends UnicastRemoteObject implements Compte     {
  private Double solde ; 

  public CompteImpl(Double montant) throws RemoteException{
    super();
    this.solde = montant ; 
  }

  @Override
  public synchronized Double debiter(double montant) {
    if (montant > this.solde){
      throw new RuntimeException("Montant > this.solde") ; 
    }
    this.solde -= montant ; 
    return this.solde ; 
  }

  @Override
  public synchronized Double crediter(double montant) {
    this.solde += montant ; 
    return this.solde ; 
  }

  @Override
  public  Double lireSolde() {
    return this.solde ; 
  }

}
