import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;

public class Client {
    public static void main(String[] args) throws Exception {

        InetAddress groupIp = InetAddress.getByName("230.0.0.0");
        int port = 4446;

        SocketAddress group = new InetSocketAddress(groupIp, port);

        MulticastSocket socket = new MulticastSocket(port);

        NetworkInterface networkInterface =
                NetworkInterface.getByName("wlps0"); // change this!

        socket.joinGroup(group, networkInterface);

        byte[] buffer = new byte[256];

        long start = System.currentTimeMillis();


        while (true) {

            if (System.currentTimeMillis() - start > 60_000) {
                System.out.println("Leaving multicast group...");
                socket.leaveGroup(group, networkInterface);
                socket.close();
                break;
            }
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String msg = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Time: " + msg);
        }
    }
}
