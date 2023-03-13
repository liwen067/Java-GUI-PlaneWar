package ui;

import java.awt.image.BufferedImage;

public class Fire {
    BufferedImage image;
    int x;
    int y;

    public Fire(Plane plane) {
        image = GetImg.getImg("fire.png");
        this.x = plane.getX() + plane.image.getWidth()/2 - plane.image.getWidth()/4/2;
        this.y = plane.getY() + plane.image.getHeight()/4/2;
    }
}
