package world.ucode.objects;

import world.ucode.util.Animation;
import world.ucode.util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

import static world.ucode.inerface.Screen.*;

public class Character {
    private float x = 0;
    private float y = 0;
    private float speedY = 0;
    private Animation characterRun;
    private Animation downRun;
    private Animation bigEyes;
    private Rectangle rectCharacter;
    private Rectangle rectDown;
    private Rectangle rectDead;
    private boolean isAlive = true;

    public Character() {
        characterRun = new Animation(200);
        downRun = new Animation(200);
        bigEyes = new Animation(200);
        characterRun.addFrame(Resource.getResourceImage("src/main/resources/main-character1.png"));
        characterRun.addFrame(Resource.getResourceImage("src/main/resources/main-character2.png"));
        downRun.addFrame(Resource.getResourceImage("src/main/resources/main-character5.png"));
        downRun.addFrame(Resource.getResourceImage("src/main/resources/main-character6.png"));
        bigEyes.addFrame(Resource.getResourceImage("src/main/resources/main-character4.png"));
        rectCharacter = new Rectangle();
        rectDown = new Rectangle();
        rectDead = new Rectangle();
    }

    public void update(boolean isDown) {
        if(isDown) {
            downRun.update();
            if (y >= GROUND - downRun.getFrame().getHeight()) {
                speedY = 0;
                y = GROUND - downRun.getFrame().getHeight();
            }
            else {
                speedY += GRAVITY_FALL;
                y += speedY;
            }
            if(y == GROUND - 43 || y == GROUND - 26) {
                rectDown.x = (int)x;
                rectDown.y = (int)y;
                rectDown.width = downRun.getFrame().getWidth();
                rectDown.height = downRun.getFrame().getHeight();
            }
        }
        else {
            characterRun.update();
            if (y >= GROUND - characterRun.getFrame().getHeight()) {
                speedY = 0;
                y = GROUND - characterRun.getFrame().getHeight();
            }
            else {
                speedY += GRAVITY;
                y += speedY;
            }
            rectCharacter.x = (int)x;
            rectCharacter.y = (int)y;
            rectCharacter.width = characterRun.getFrame().getWidth() - 5;
            rectCharacter.height = characterRun.getFrame().getHeight() - 5;
        }
    }

    public Rectangle getBound(boolean isDown) {
        if(isDown) {
            return rectDown;
        }
        else {
            return rectCharacter;
        }
    }

    public void draw(Graphics g, boolean isDown) {
        if(isAlive == false) {
            if(isDown) {
                g.drawImage(bigEyes.getFrame(), (int) x, (int) y - 17, null);
            }
            else {
                g.drawImage(bigEyes.getFrame(), (int) x, (int) y, null);
            }
        }
        else if(isDown) {
            g.drawImage(downRun.getFrame(), (int)x, (int)y, null);
        }
        else {
            g.drawImage(characterRun.getFrame(), (int)x, (int)y, null);
        }
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
