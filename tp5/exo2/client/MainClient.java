package client;

import common.AccountServer;
import common.BankException;
import common.TransferService;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class MainClient {
  public static void main(String[] args) {
    try {
      AccountServer accountStub =
          (AccountServer) Naming.lookup("rmi://localhost:1099/" + AccountServer.LOOKUP_NAME);
      TransferService serviceStub =
          (TransferService) Naming.lookup("rmi://localhost:1100/" + TransferService.LOOKUP_NAME);

      printBalance(accountStub, "compte1");
      printBalance(accountStub, "compte2");

      runTransfer(serviceStub, "Cas 1 - transfert valide", "compte1", "compte2", 200.0);
      printBalance(accountStub, "compte1");
      printBalance(accountStub, "compte2");

      runTransfer(serviceStub, "Cas 2 - montant negatif", "compte1", "compte2", -50.0);
      runTransfer(serviceStub, "Cas 3 - solde insuffisant", "compte2", "compte1", 1000.0);
      runTransfer(serviceStub, "Cas 4 - compte source introuvable", "inconnu", "compte1", 50.0);
      runTransfer(
          serviceStub, "Cas 5 - compte destination introuvable", "compte1", "introuvable", 50.0);

      System.out.println("Etat final des comptes:");
      printBalance(accountStub, "compte1");
      printBalance(accountStub, "compte2");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void printBalance(AccountServer accountStub, String accountName) {
    try {
      System.out.println("Solde de " + accountName + " = " + accountStub.getBalance(accountName));
    } catch (RemoteException | BankException e) {
      System.out.println("Erreur lors de la consultation du compte " + accountName + ": " + e.getMessage());
    }
  }

  private static void runTransfer(
      TransferService serviceStub, String label, String sender, String receiver, double montant) {
    try {
      boolean success = serviceStub.transfer(sender, receiver, montant);
      System.out.println(label + " -> succes = " + success);
    } catch (RemoteException | BankException e) {
      System.out.println(label + " -> echec: " + e.getMessage());
    }
  }
}
