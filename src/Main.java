
public class Main {
    public static void main(String[] args) {
        ControladorDeHoras controlador = new ControladorDeHoras();
        GUI gui = new GUI(controlador);
        gui.setVisible(true);
    }
}

