package world.ucode.objects;

import world.ucode.util.Animation;
import world.ucode.util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Birds extends Enemy {
        private Animation framesBirb;
        private int posX, posY;
        private Rectangle rect;
        private Character character;
        private boolean isScoreGot = false;

        public Birds(Character character) {
            this.character = character;
            framesBirb = new Animation(200);
            framesBirb.addFrame(Resource.getResourceImage("src/main/resources/pteror.jpg"));
            framesBirb.addFrame(Resource.getResourceImage("src/main/resources/pteror1.jpg"));
            posX = 200;
            Random random = new Random();
            posY = random.nextInt((80 - 60) + 1) + 60;
            rect = new Rectangle();
        }

        public void update(int gameSpeed) {
            framesBirb.update();
            posX -= gameSpeed;
            rect.x = posX;
            rect.y = posY;
            rect.width = framesBirb.getFrame().getWidth();
            rect.height = framesBirb.getFrame().getHeight();
        }

        @Override
        public Rectangle getBound() {
            return rect;
        }

        @Override
        public void draw(Graphics g) {
            g.drawImage(framesBirb.getFrame(), posX, posY, null);
        }

        public void setX(int x) {
            posX = x;
        }

        public void setY(int y) {
            posY = y;
        }

        @Override
        public boolean isOutOfScreen() {
            return (posX + framesBirb.getFrame().getWidth() < 0);
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
