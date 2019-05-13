//Connect
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

//Image
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

//File and Streams
import java.io.File;
import java.io.ByteArrayOutputStream;

//Exceptions
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

class UDPcliente {

    private DatagramSocket cliente;
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

    public void sendFile(File image) throws IOException {
	    BufferedImage img = ImageIO.read(image);
	    ByteArrayOutputStream imgstream = new ByteArrayOutputStream();
	    ImageIO.write(img, "jpg",imgstream);
	    imgstream.flush();
	    sendbuffer = imgstream.toByteArray();
	    DatagramPacket enviar = new DatagramPacket(sendbuffer, sendbuffer.length, address, 4444);
	    System.out.println(sendbuffer.length);
	    cliente.send(enviar);
	}

	public void close() {
	    cliente.close();
	}
}
