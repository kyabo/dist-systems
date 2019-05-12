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

    public void sendFile(String image, String ext) throws IOException {
	    BufferedImage img = ImageIO.read(new File(image + "." + ext));
	    ByteArrayOutputStream imgstream = new ByteArrayOutputStream();
	    ImageIO.write(img, ext,imgstream);
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
