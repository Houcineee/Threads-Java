import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalTime;

public class Server {
    public static void main(String[] args) throws Exception {

        InetAddress group = InetAddress.getByName("230.0.0.0");

        int port = 4446;

        DatagramSocket socket = new DatagramSocket();

        while (true) {
            String time = LocalTime.now().toString();
            byte[] buffer = time.getBytes();

            DatagramPacket packet = new DatagramPacket(
                    buffer,
                    buffer.length,
                    group,
                    port
            );

            socket.send(packet);
            System.out.println("Sent: " + time);

            Thread.sleep(1000);
        }
    }
}
