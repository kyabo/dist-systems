import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

class UDPcliente {

    private DatagramSocket cliente;
    private BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
    private byte[] sendbuffer;
    private InetAddress address;
	
	public UDPcliente() {
	    try {
		cliente = new DatagramSocket();
	    }
	    catch (SocketException e) {
		return;
	    }

	    try {
		address = InetAddress.getByName("localhost");
	    }
	    catch (UnknownHostException e) {
		return;
	    }
	}

	public void sendFile(String msg) throws IOException {
	    sendbuffer = msg.getBytes();
	    DatagramPacket enviar = new DatagramPacket(sendbuffer, sendbuffer.length, address, 4444);
	    cliente.send(enviar);
	}

	public void close() {
	    cliente.close();
	}
}
