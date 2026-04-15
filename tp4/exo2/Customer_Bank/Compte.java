public interface Compte extends java.rmi.Remote {
  Double debiter(double montant)throws java.rmi.RemoteException ;  
  Double crediter(double montant) throws java.rmi.RemoteException;  
  Double lireSolde() throws java.rmi.RemoteException;  
}
