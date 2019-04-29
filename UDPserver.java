import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.net.InetAddress;
import java.io.IOException;

class UDPserver extends Thread {

    private DatagramSocket servidor;
    private boolean running;
    private byte[] recbuffer = new byte[1024];

    public UDPserver() {
	try {
	    servidor = new DatagramSocket(4444);
	}
	catch (SocketException ex) {
	    return;
	}
    }

    public void run() {
	running = true;

	while (running) {
	    try {
		DatagramPacket pacote = new DatagramPacket(recbuffer, recbuffer.length);
		servidor.receive(pacote);
		
		InetAddress endereco = pacote.getAddress();
		int port = pacote.getPort();
		String recebido = new String (pacote.getData(), 0, pacote.getLength());
		
		System.out.println(recebido);
		
		if (recebido.equals("end")) {
		    running = false;
		    continue;
		}
	    }
	    catch (IOException e) {
		return;
	    }
	}

	servidor.close();
    }
    
}
