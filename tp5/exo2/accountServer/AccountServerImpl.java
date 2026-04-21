package accountServer;

import common.AccountServer;
import common.BankException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class AccountServerImpl extends UnicastRemoteObject implements AccountServer {
  private final Map<String, Compte> accounts = new HashMap<>();

  public AccountServerImpl() throws RemoteException {
    super();
  }

  @Override
  public synchronized Double withdraw(String name, double montant) throws BankException {
    Compte compte = accounts.get(name);
    if (compte == null) {
      throw new BankException("Compte introuvable: " + name);
    }
    if (montant <= 0) {
      throw new BankException("Le montant doit etre > 0");
    }
    if (montant > compte.getSolde()) {
      throw new BankException("Solde insuffisant pour le compte: " + name);
    }
    compte.setSolde(compte.getSolde() - montant);
    return compte.getSolde();
  }

  @Override
  public synchronized Double deposit(String name, double montant) throws BankException {
    Compte compte = accounts.get(name);
    if (compte == null) {
      throw new BankException("Compte introuvable: " + name);
    }
    if (montant <= 0) {
      throw new BankException("Le montant doit etre > 0");
    }
    compte.setSolde(compte.getSolde() + montant);
    return compte.getSolde();
  }

  @Override
  public synchronized Double getBalance(String name) throws BankException {
    Compte compte = accounts.get(name);
    if (compte == null) {
      throw new BankException("Compte introuvable: " + name);
    }
    return compte.getSolde();
  }

  public synchronized void addAccount(Compte compte) {
    accounts.put(compte.getName(), compte);
  }
}
