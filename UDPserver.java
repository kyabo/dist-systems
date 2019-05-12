//Connect
import java.net.DatagramSocket;
import java.net.DatagramPacket;

//Image
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

//File and Streams
import java.io.File;
import java.io.ByteArrayInputStream;

//Exception
import java.net.SocketException;
import java.net.InetAddress;
import java.io.IOException;

class UDPserver extends Thread {

    private DatagramSocket servidor;
    private boolean running;
    private byte[] recbuffer = new byte[8192];

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
		byte[] buff = pacote.getData();
		ByteArrayInputStream imgstream = new ByteArrayInputStream(buff);
		BufferedImage bimage = ImageIO.read(imgstream);
		ImageIO.write(bimage, "jpg", new File("received.jpg"));
		//teoricamente criou o arquivo recebido :)
		//é só mostrar o arquivo criado
		
		InetAddress endereco = pacote.getAddress();
		int port = pacote.getPort();
	        
	    }
	    catch (IOException e) {
		return;
	    }
	}

	servidor.close();
    }
    
}
