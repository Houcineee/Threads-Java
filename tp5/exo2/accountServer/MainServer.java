package accountServer;

import common.AccountServer;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class MainServer {
  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(1099);
      AccountServerImpl ci = new AccountServerImpl();
      ci.addAccount(new Compte("compte1", 1000.0));
      ci.addAccount(new Compte("compte2", 500.0));
      Naming.rebind("rmi://localhost:1099/" + AccountServer.LOOKUP_NAME, ci);
      System.out.println("Server is ready");

    } catch (Exception e) {
      e.printStackTrace();

    }

  }

}
