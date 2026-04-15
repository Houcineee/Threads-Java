import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class BankServer{
  public static void main(String[] args) {
    try{
      LocateRegistry.createRegistry(1099) ; 
      CompteImpl ci = new CompteImpl(1000.0); 
      Naming.rebind("rmi://localhost:1099/CompteCourant", ci);
      System.out.println("Server is ready");

    }catch(Exception e){
      e.printStackTrace();

    }
    
  }

}
