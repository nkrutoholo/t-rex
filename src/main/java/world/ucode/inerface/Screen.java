package world.ucode.inerface;

import world.ucode.objects.Cactus;
import world.ucode.objects.Character;
import world.ucode.objects.Clouds;
import world.ucode.objects.Land;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Screen extends JPanel implements Runnable, KeyListener {
    public static final float GRAVITY = 0.1f;
    public static final float GROUND = 130;

    private Character character;
    private Thread thread;
    private Land land;
    private Clouds clouds;
    private Cactus cactus;

    public Screen() {
        thread = new Thread(this);
        character = new Character();
        character.setX(50);
        land = new Land(this);
        clouds = new Clouds();
        cactus = new Cactus();
    }

    public void startGame() {
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                character.update();
                land.update();
                clouds.update();
                cactus.update();
                repaint();
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.decode("#f7f7f7"));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.red);
        g.drawLine(0, (int)GROUND, getWidth(), (int)GROUND);
        clouds.draw(g);
        land.draw(g);
        character.draw(g);
        cactus.draw(g);
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
