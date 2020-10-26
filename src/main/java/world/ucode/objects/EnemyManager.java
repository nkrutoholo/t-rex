package world.ucode.objects;

import world.ucode.inerface.Screen;
import world.ucode.util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyManager {
    private List<Enemy> enemies;
    private Random random;

    private BufferedImage imageCactus1, imageCactus2;
    private Character character;
    private Screen screen;


    public EnemyManager(Character character, Screen screen) {
        this.screen = screen;
        this.character = character;
        enemies = new ArrayList<Enemy>();
        imageCactus1 = Resource.getResourceImage("src/main/resources/cactus1.png");
        imageCactus2 = Resource.getResourceImage("src/main/resources/cactus2.png");
        random = new Random();

        enemies.add(getRandomEnemy());
    }

    public void update(int gameSpeed, boolean isDown) {
        for(Enemy e : enemies) {
            e.update(gameSpeed);
            if(e.isOver() && !e.isScoreGot()) {
                screen.plusScore(20);
                screen.plusSpeed();
                e.setIsScoreGot(true);
            }
            if(e.getBound().intersects(character.getBound(isDown))) {
                character.setAlive(false);
            }
        }
        Enemy firstEnemy = enemies.get(0);
        if(firstEnemy.isOutOfScreen()) {
            enemies.remove(firstEnemy);
            enemies.add(getRandomEnemy());
        }
    }

    public void draw(Graphics g) {
        for (Enemy e : enemies) {
            e.draw(g);
        }
    }

    public void reset() {
        enemies.clear();
        enemies.add(getRandomEnemy());
    }

    private Cactus getRandomCactus() {
        Cactus cactus;
        cactus = new Cactus(character);
        cactus.setX(600);
        if(random.nextBoolean()) {
            cactus.setImage(imageCactus1);
        } else {
            cactus.setImage(imageCactus2);
        }
        return cactus;
    }

    private Birds getRandomBird() {
        Birds birb;
        birb = new Birds(character);
        birb.setX(600);

        return birb;
    }

    private Enemy getRandomEnemy() {
        if(random.nextBoolean()) {
            return getRandomCactus();
        }
        else {
            return getRandomBird();
        }
    }
}
