import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class serverGUI extends JFrame{
    private JFrame frame = new JFrame("Arquivo recebido!");
	
    public static void main(String[] args) throws Exception {
	UDPserver servidor = new UDPserver();
	System.out.println("Servidor esta executando!!!");
	servidor.run();
	
    }

    public void show_image(BufferedImage bimage) {
	frame.getContentPane().removeAll();
	frame.repaint();
	frame.getContentPane().setLayout(new FlowLayout());
	frame.getContentPane().add(new JLabel(new ImageIcon(bimage)));
	frame.pack();
	frame.setVisible(true);
	
    }
}
