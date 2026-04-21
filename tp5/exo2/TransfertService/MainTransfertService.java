package TransfertService;

import common.AccountServer;
import common.TransferService;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class MainTransfertService {
  public static void main(String[] args) {
    try {
      AccountServer accountServer = (AccountServer) Naming.lookup(
          "rmi://localhost:1099/" + AccountServer.LOOKUP_NAME);

      TransferService transfertService = new TransfertServiceImpl(accountServer);
      LocateRegistry.createRegistry(1100);
      Naming.rebind("rmi://localhost:1100/" + TransferService.LOOKUP_NAME, transfertService);

      System.out.println("TransfertService is ready");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
