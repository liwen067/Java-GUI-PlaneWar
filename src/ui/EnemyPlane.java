package ui;

import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemyPlane {
    BufferedImage image;
    private int x;
    private int y;

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

    public EnemyPlane() {
        Random random = new Random();
        int index = random.nextInt(15) + 1;
        String path = (index < 10) ? ("0" + index) : ("" + index);
        String name = "ep" + path + ".png";
        image = GetImg.getImg(name);
        setY(0);
        setX(random.nextInt(512-image.getWidth()));
    }
}
