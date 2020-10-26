package world.ucode.inerface;

import world.ucode.objects.*;
import world.ucode.objects.Character;
import world.ucode.util.Resource;
//import world.ucode.inerface.Screen;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;

public class Screen extends JPanel implements Runnable, KeyListener {
    public static final int FIRST_STATE = 0;
    public static final int PLAY_STATE = 1;
    public static final int OVER_STATE = 2;
    public static final float GRAVITY = 0.1f;
    public static final float GROUND = 130;
    public static final float GRAVITY_FALL = 1.0f;
    public static final int STARTSPEED = 2;

    private Character character;
    private Thread thread;
    private Land land;
    private Clouds clouds;
    private EnemyManager enemiesManager;

    private int score;
    private int curScore;

    private int gameSpeed;

    private boolean isDown = false;

    private int gameState = FIRST_STATE;

    private BufferedImage gameOver;
    private AudioClip scoreUpSound;
    private AudioClip deadSound;

    public Screen() {
        thread = new Thread(this);
        character = new Character();
        character.setX(50);
        character.setY(87);
        land = new Land(this);
        clouds = new Clouds();
        score = 0;
        curScore = 0;
        gameSpeed = STARTSPEED;
        enemiesManager = new EnemyManager(character, this);
        gameOver = Resource.getResourceImage("src/main/resources/gameover_text.png");
        try {
            scoreUpSound = Applet.newAudioClip(new URL("file", "", "src/main/resources/scoreup.wav"));
            deadSound = Applet.newAudioClip(new URL("file", "", "src/main/resources/dead.wav"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void update() {
        switch (gameState) {
            case PLAY_STATE:
                clouds.update(gameSpeed);
                land.update(gameSpeed);
                enemiesManager.update(gameSpeed, isDown);
                character.update(isDown);
                if(!character.getAlive()) {
                    gameState = OVER_STATE;
                    deadSound.play();
                }
                break;
        }
    }

    public void plusScore(int curScore) {
        this.curScore += curScore;
        if(this.curScore > this.score)
            this.score = this.curScore;
        scoreUpSound.play();
    }

    public void plusSpeed() {
        if(gameSpeed - STARTSPEED < curScore / 100) {
            gameSpeed = curScore / 100 + STARTSPEED;
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.decode("#f7f7f7"));
        g.fillRect(0, 0, getWidth(), getHeight());

        switch(gameState) {
            case FIRST_STATE:
                character.draw(g, isDown);
                g.setColor(Color.BLACK);
                g.drawString("TAP SPACE TO START GAME", 100, 100);
                break;
            case PLAY_STATE:
                clouds.draw(g);
                land.draw(g);
                enemiesManager.draw(g);
                character.draw(g, isDown);
                g.setColor(Color.BLACK);
                g.drawString("HI " + String.valueOf(score) + " SC " + String.valueOf(curScore), 500, 20);
                g.drawString("SP " + String.valueOf(gameSpeed) , 400, 20);
                break;
            case OVER_STATE:
                clouds.draw(g);
                land.draw(g);
                enemiesManager.draw(g);
                character.draw(g, isDown);
//                deadSound.play();
                g.setColor(Color.BLACK);
                g.drawString("HI " + String.valueOf(score) + " SC " + String.valueOf(curScore), 500, 20);
                g.drawImage(gameOver, 200, 50, null);
                break;
        }

    }

    private void resetGame() {
        character.setAlive(true);
        isDown = false;
        character.setX(50);
        character.setY(87);
        enemiesManager.reset();
        curScore = 0;
        gameSpeed = STARTSPEED;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                if(gameState == FIRST_STATE) {
                    gameState = PLAY_STATE;
                } else if(gameState == PLAY_STATE && character.getY() == GROUND - (character.getBound(isDown).getHeight() + 5) && isDown == false) {
                    character.jump();
                } else if(gameState == OVER_STATE) {
                    resetGame();
                    gameState = PLAY_STATE;
                }
                break;
            case KeyEvent.VK_DOWN:
                if(gameState == PLAY_STATE) {
                    isDown = true;
                    character.setY(character.getY() + 17);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                if(gameState == PLAY_STATE)
                    isDown = false;
                break;
        }
    }
}
