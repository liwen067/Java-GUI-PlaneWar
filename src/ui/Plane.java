package ui;

import java.awt.image.BufferedImage;

public class Plane {
    private int x;
    private int y;
    BufferedImage image;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Plane() {
        x = 200;
        y = 500;
        image = GetImg.getImg("hero.png");
    }
}
