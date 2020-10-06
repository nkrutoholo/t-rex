package world.ucode.inerface;

import world.ucode.objects.*;
import world.ucode.objects.Character;
import world.ucode.util.Resource;
//import world.ucode.inerface.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;


public class Screen extends JPanel implements Runnable, KeyListener {
    public static final int FIRST_STATE = 0;
    public static final int PLAY_STATE = 1;
    public static final int OVER_STATE = 2;
    public static final float GRAVITY = 0.1f;
    public static final float GROUND = 130;

    private Character character;
    private Thread thread;
    private Land land;
    private Clouds clouds;
//    private Cactus cactus;
    private EnemyManager enemiesManager;
//    private Screen screen;
    private int score;

    private int gameState = FIRST_STATE;

    private BufferedImage gameOver;

    public Screen() {
        thread = new Thread(this);
        character = new Character();
        character.setX(50);
        land = new Land(this);
        clouds = new Clouds();
//        cactus = new Cactus();
        enemiesManager = new EnemyManager(character, this);
        gameOver = Resource.getResourceImage("src/main/resources/gameover_text.png");
    }

    public void startGame() {
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                update();
                repaint();
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void update() {
        switch (gameState) {
            case PLAY_STATE:
                character.update();
                land.update();
                clouds.update();
                enemiesManager.update();
                if(!character.getAlive()) {
                    gameState = OVER_STATE;
                }
                break;
        }
    }

    public void plusScore(int score) {
        this.score += score;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.decode("#f7f7f7"));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.red);
        g.drawLine(0, (int)GROUND, getWidth(), (int)GROUND);

        switch(gameState) {
            case FIRST_STATE:
                character.draw(g);
                break;
            case PLAY_STATE:
                clouds.draw(g);
                land.draw(g);
                character.draw(g);
                enemiesManager.draw(g);
                g.drawString("HI " + String.valueOf(score), 500, 20);
                break;
            case OVER_STATE:
                clouds.draw(g);
                land.draw(g);
                character.draw(g);
                enemiesManager.draw(g);
                g.drawImage(gameOver, 200, 50, null);
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("Key released");
        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                if(gameState == FIRST_STATE) {
                    gameState = PLAY_STATE;
                }
                character.jump();
//                if(character.getY() == GROUND + 50)
//                    character.jump();
                break;
        }
    }
}
