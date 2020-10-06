package world.ucode.inerface;

import javax.swing.JFrame;

public class Window extends JFrame {

    private Screen gScreen;

    public Window() {
        super("T-Rex");
        setSize(600, 175);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gScreen = new Screen();
        add(gScreen);
        addKeyListener(gScreen);
    }

    public void startGame() {
        gScreen.startGame();
    }


}
