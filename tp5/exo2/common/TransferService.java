package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TransferService extends Remote {
  String LOOKUP_NAME = "TransferService";

  boolean transfer(String sender, String receiver, double montant) throws RemoteException, BankException;
}
