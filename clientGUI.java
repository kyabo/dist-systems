public class clientGUI {

    public static void main(String[] args) throws Exception {
	UDPcliente cliente = new UDPcliente();
	cliente.sendFile("teste", "jpg");
    }
}
