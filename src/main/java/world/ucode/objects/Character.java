package world.ucode.objects;

import world.ucode.util.Animation;
import world.ucode.util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

import static world.ucode.inerface.Screen.GRAVITY;
import static world.ucode.inerface.Screen.GROUND;

public class Character {
    private float x = 0;
    private float y = 0;
    private float speedY = 0;
    private Animation characterRun;
    private Rectangle rect;
    private boolean isAlive = true;

    public Character() {
        characterRun = new Animation(200);
        characterRun.addFrame(Resource.getResourceImage("src/main/resources/main-character1.png"));
        characterRun.addFrame(Resource.getResourceImage("src/main/resources/main-character2.png"));
        rect = new Rectangle();
    }

    public void update() {
        characterRun.update();
        if (y >= GROUND - characterRun.getFrame().getHeight()) {
            speedY = 0;
            y = GROUND - characterRun.getFrame().getHeight();
        }
        else {
            speedY += GRAVITY;
            y += speedY;
        }
        rect.x = (int)x;
        rect.y = (int)y;
        rect.width = characterRun.getFrame().getWidth();
        rect.height = characterRun.getFrame().getHeight();
    }

    public Rectangle getBound() {
        return rect;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawRect((int)x, (int)y, characterRun.getFrame().getWidth(), characterRun.getFrame().getHeight());
        g.drawImage(characterRun.getFrame(), (int)x, (int)y, null);
    }

    public void jump() {
        speedY = -4;
        y += speedY;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean getAlive() {
        return isAlive;
    }
}
