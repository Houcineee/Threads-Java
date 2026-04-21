package TransfertService;

import common.AccountServer;
import common.BankException;
import common.TransferService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TransfertServiceImpl extends UnicastRemoteObject implements TransferService {
  private final AccountServer accountServer;

  public TransfertServiceImpl(AccountServer accountServer) throws RemoteException {
    super();
    this.accountServer = accountServer;
  }

  @Override
  public synchronized boolean transfer(String sender, String receiver, double montant)
      throws RemoteException, BankException {
    if (montant <= 0) {
      throw new BankException("Le montant doit etre > 0");
    }
    accountServer.withdraw(sender, montant);
    try {
      accountServer.deposit(receiver, montant);
      return true;
    } catch (BankException e) {
      try {
        accountServer.deposit(sender, montant);
      } catch (BankException rollbackError) {
        throw new RemoteException(
            "Echec du transfert et echec du rollback pour " + sender + " -> " + receiver,
            rollbackError);
      }
      throw new BankException("Transfert annule: " + e.getMessage());
    }
  }
}
