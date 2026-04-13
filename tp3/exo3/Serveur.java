import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Serveur {
  private static final int PORT = 8081;
  private static final int TAILLE = 4096;

  public static void main(String[] args) throws Exception {
    byte[] buffer = new byte[TAILLE];

    try (DatagramSocket socket = new DatagramSocket(PORT)) {
      System.out.println("Serveur UDP en attente sur le port " + PORT + "...");

      while (true) {
        DatagramPacket paquetReception = new DatagramPacket(buffer, buffer.length);
        socket.receive(paquetReception);

        Personne personne =
            deserialiser(paquetReception.getData(), paquetReception.getLength());
        int age = personne.calculAge();

        System.out.println("Personne recue : " + personne);
        System.out.println("Age calcule : " + age + " ans");

        byte[] reponse = serialiserAge(age);
        DatagramPacket paquetReponse =
            new DatagramPacket(
                reponse,
                reponse.length,
                paquetReception.getAddress(),
                paquetReception.getPort());
        socket.send(paquetReponse);
      }
    }
  }

  private static Personne deserialiser(byte[] donnees, int longueur) throws Exception {
    try (ObjectInputStream input =
        new ObjectInputStream(new ByteArrayInputStream(donnees, 0, longueur))) {
      return (Personne) input.readObject();
    }
  }

  private static byte[] serialiserAge(int age) throws Exception {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try (DataOutputStream output = new DataOutputStream(baos)) {
      output.writeInt(age);
      output.flush();
      return baos.toByteArray();
    }
  }
}
