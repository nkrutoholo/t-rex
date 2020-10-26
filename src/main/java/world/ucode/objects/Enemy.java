package world.ucode.objects;

import java.awt.*;

public abstract class Enemy {
    public abstract Rectangle getBound();
    public abstract void draw(Graphics g);
    public abstract void update(int gameSpeed);
    public abstract boolean isOutOfScreen();
    public abstract boolean isOver();
    public abstract boolean isScoreGot();
    public abstract void setIsScoreGot(boolean isScoreGot);
}
