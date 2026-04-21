package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccountServer extends Remote {
  String LOOKUP_NAME = "AccountServer";

  Double deposit(String name, double montant) throws RemoteException, BankException;

  Double withdraw(String name, double montant) throws RemoteException, BankException;

  Double getBalance(String name) throws RemoteException, BankException;
}
