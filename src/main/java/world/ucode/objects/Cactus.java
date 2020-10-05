package world.ucode.objects;

import world.ucode.util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cactus {

    private BufferedImage image;
    private int posX, posY;

    public Cactus() {
        image = Resource.getResourceImage("src/main/resources/cactus1.png");
        posX = 200;
        posY = 85;
    }

    public void update() {
        posX -= 2;
    }

    public void draw(Graphics g) {
        g.drawImage(image, posX, posY, null);
    }
}
