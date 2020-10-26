package world.ucode.objects;

import world.ucode.util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cactus extends Enemy {

    private BufferedImage image;
    private int posX, posY;
    private Rectangle rect;
    private Character character;
    private boolean isScoreGot = false;

    public Cactus(Character character) {
        this.character = character;
        image = Resource.getResourceImage("src/main/resources/cactus1.png");
        posX = 200;
        posY = 85;
        rect = new Rectangle();
    }

    public void update(int gameSpeed) {
        posX -= gameSpeed;
        rect.x = posX;
        rect.y = posY;
        rect.width = image.getWidth();
        rect.height = image.getHeight();
    }

    @Override
    public Rectangle getBound() {
        return rect;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, posX, posY, null);
    }

    public void setX(int x) {
        posX = x;
    }

    public void setY(int y) {
        posY = y;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        if (image.getWidth() == 49)
            posY = 95;
    }

    @Override
    public boolean isOutOfScreen() {
        return (posX + image.getWidth() < 0);
    }

    @Override
    public boolean isOver() {
        return (character.getX() > posX);
    }

    @Override
    public boolean isScoreGot() {
        return isScoreGot;
    }

    @Override
    public void setIsScoreGot(boolean isScoreGot) {
        this.isScoreGot = isScoreGot;
    }
}
