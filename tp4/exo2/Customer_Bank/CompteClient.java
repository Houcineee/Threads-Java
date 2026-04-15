import java.rmi.Naming;

public class CompteClient{
  public static void main(String[] args) {
    
    try{
      Compte stub = (Compte) Naming.lookup("rmi://localhost:1099/CompteCourant") ; 

      stub.crediter(500) ; 
      stub.debiter(800) ; 

      System.out.println("Le solde de client est : " + stub.lireSolde());
    }catch(Exception e){
      e.printStackTrace();

    }
  }

}
