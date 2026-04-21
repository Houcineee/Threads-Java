import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EcommerceSynchrone {
public static void main(String[] args) throws InterruptedException, ExecutionException {
  long start = System.currentTimeMillis();

  ExecutorService executor = Executors.newFixedThreadPool(3);

  Future<String> produitFuture = executor.submit(EcommerceSynchrone::getProduit);
  Future<String> avisFuture = executor.submit(EcommerceSynchrone::getAvis);
  Future<String> prixFuture = executor.submit(EcommerceSynchrone::getPrix);

  String produit = produitFuture.get();
  String avis = avisFuture.get();
  String prix = prixFuture.get();

  executor.shutdown();

  System.out.println("\n=== PAGE PRODUIT ===");
  System.out.println(produit);
  System.out.println(avis);
  System.out.println(prix);

  long end = System.currentTimeMillis();
  System.out.println("\nTemps total : " + (end - start) / 1000 + " secondes");
}

// appel service produit
public static String getProduit() throws InterruptedException {
  System.out.println("Chargement produit...");
  Thread.sleep(3000);
  return "Produit : Smartphone XYZ";
}
// appel service avis
public static String getAvis() throws InterruptedException {
  System.out.println("Chargement avis...");
  Thread.sleep(4000);
  return "Avis : ★★★★☆";
}

// appel service prix
public static String getPrix() throws InterruptedException {
  System.out.println("Chargement prix...");
  Thread.sleep(3000);
  return "Prix : 499 DH";
}
}
