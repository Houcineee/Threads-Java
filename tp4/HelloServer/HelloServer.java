import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
public class HelloServer {
  public static void main(String[] args) {
    try { /*Lance un registry localement sur le port d'écoute passé en paramètre : 1099*/
      LocateRegistry.createRegistry(1099);
      /* créer une instance de la classe Hello et l’enregistrer dans le serveur d’annuaire */
      HelloImpl od= new HelloImpl("Hello World !");
      Naming.rebind("rmi://localhost:1099/BK", od);
      System.out.println("Serveur prêt");
    }
    catch (Exception e) {
      System.out.println("Serveur erreur :"+e);

    }}}
