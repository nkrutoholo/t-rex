package world.ucode.inerface;

import javax.swing.JFrame;

public class Window extends JFrame {

    private Screen gScreen;

    public Window() {
        super("T-Rex");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gScreen = new Screen();
        add(gScreen);
        addKeyListener(gScreen);
    }

    public void startGame() {
        gScreen.startGame();
    }

    public static void main(String args[]) {
        Window rex = new Window();
        rex.setVisible(true);
        rex.startGame();
    }
}
