import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Font;

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionEvent;

import java.io.IOException;

public class clienteGUI {
	private final JFileChooser openFile;
	private JFrame frame;
    private File arquivo;
    
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clienteGUI window = new clienteGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public clienteGUI() {
		initialize();
		openFile = new JFileChooser();
		openFile.setCurrentDirectory(new File("~/"));
		openFile.setFileFilter(new FileNameExtensionFilter(null, "JPEG","jpg"));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		UDPcliente cliente = new UDPcliente();
		
		frame = new JFrame("ClienteGUI versao 0.0");
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblCliente = new JLabel("Welcome to Cliente");
		lblCliente.setFont(new Font("Arial Black", Font.PLAIN, 29));
		lblCliente.setBackground(Color.CYAN);
		lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblCliente);
		
		JLabel nomeImagem = new JLabel("");
		frame.getContentPane().add(nomeImagem);
		
		JButton btnEscolhaUmaImagem = new JButton("Escolha uma imagem...");
		btnEscolhaUmaImagem.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnEscolhaUmaImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			    int result = openFile.showOpenDialog(null);
			    //Coloca o arquivo na memória
			    if (result == JFileChooser.APPROVE_OPTION) {
				arquivo = openFile.getSelectedFile();
			    }
			}
		});
		btnEscolhaUmaImagem.setForeground(SystemColor.desktop);
		btnEscolhaUmaImagem.setBackground(SystemColor.textHighlight);
		frame.getContentPane().add(btnEscolhaUmaImagem);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setFont(new Font("Tahoma", Font.BOLD, 19));
		frame.getContentPane().add(btnEnviar);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			    if (arquivo.exists()) {
				try {
				    cliente.sendFile(arquivo);
				} catch(IOException e) {
				    return;
				}
			    }
			}
		});
	}
	private void abrirImagem(ActionEvent evt) {
		int returnValue = openFile.showOpenDialog(frame);
	}
}
