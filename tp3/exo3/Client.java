import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDate;

public class Client {
  private static final int PORT = 8081;
  private static final int TAILLE = 4096;

  public static void main(String[] args) throws Exception {
    InetAddress serveur = InetAddress.getByName("127.0.0.1");
    Personne personne = new Personne("Houcine", "Test", LocalDate.of(1900, 5, 14));

    try (DatagramSocket socket = new DatagramSocket()) {
      byte[] donneesPersonne = serialiser(personne);
      DatagramPacket paquetEnvoi =
          new DatagramPacket(donneesPersonne, donneesPersonne.length, serveur, PORT);
      socket.send(paquetEnvoi);

      byte[] buffer = new byte[TAILLE];
      DatagramPacket paquetReception = new DatagramPacket(buffer, buffer.length);
      socket.receive(paquetReception);

      try (DataInputStream input =
          new DataInputStream(
              new ByteArrayInputStream(paquetReception.getData(), 0, paquetReception.getLength()))) {
        int age = input.readInt();
        System.out.println(
            "Age de " + personne.getPrenom() + " " + personne.getNom() + " = " + age + " ans");
      }
    }
  }

  private static byte[] serialiser(Personne personne) throws Exception {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try (ObjectOutputStream output = new ObjectOutputStream(baos)) {
      output.writeObject(personne);
      output.flush();
      return baos.toByteArray();
    }
  }
}
