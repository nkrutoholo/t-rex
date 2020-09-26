package world.ucode.inerface;

import world.ucode.objects.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Screen extends JPanel implements Runnable, KeyListener {
    public static final float GRAVITY = 0.1f;
    public static final float GROUND = 300;

    private Character character;
    private Thread thread;

    public Screen() {
        thread = new Thread(this);
        character = new Character();
    }

    public void startGame() {
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                character.update();
                repaint();
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
        g.drawLine(0, (int)GROUND, getWidth(), (int)GROUND);
        character.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        character.jump();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key released");
    }
}
