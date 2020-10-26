package world.ucode.objects;

import org.w3c.dom.ls.LSOutput;
import world.ucode.inerface.Screen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static world.ucode.inerface.Screen.GROUND;
import static world.ucode.util.Resource.getResourceImage;

public class Land {

    private List<ImageLand> listImg;
    private BufferedImage imageLand1, imageLand2, imageLand3;
    private Random random;

    public Land(Screen game) {
        random = new Random();
        imageLand1 = getResourceImage("src/main/resources/land1.png");
        imageLand2 = getResourceImage("src/main/resources/land2.png");
        imageLand3 = getResourceImage("src/main/resources/land3.png");
        listImg = new ArrayList<ImageLand>();
        int numberOfLandTitle = 600 / imageLand1.getWidth() + 2;
        for(int i = 0; i < numberOfLandTitle; i++) {
            ImageLand imageLand = new ImageLand();
            imageLand.posX = (int)(i * imageLand1.getWidth());
            imageLand.image = getImageLand();
            listImg.add(imageLand);
        }
    }

    public void update(int gameSpeed) {
        for(ImageLand imageLand : listImg) {
            imageLand.posX -= gameSpeed;
        }
        ImageLand fistElement = listImg.get(0);
        if(fistElement.posX + imageLand1.getWidth() < 0) {
            fistElement.posX = listImg.get(listImg.size() - 1).posX + imageLand1.getWidth();
            listImg.add(listImg.get(0));
            listImg.remove(0);
        }
    }

    public void draw(Graphics g) {
        for (ImageLand imageLand:listImg) {
            g.drawImage(imageLand.image, imageLand.posX, (int) GROUND - 20, null);
        }
    }

    private BufferedImage getImageLand() {
        int i = random.nextInt(10);
        switch(i) {
            case 0: return imageLand1;
            case 1: return imageLand3;
            default: return imageLand2;
        }
    }

    private class ImageLand {
        int posX;
        BufferedImage image;
    }
}
