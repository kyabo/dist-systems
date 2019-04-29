public class serverGUI {

    public static void main(String[] args) throws Exception {
	System.out.println("Server is running!!!");
	new UDPserver().start();
    }
}
