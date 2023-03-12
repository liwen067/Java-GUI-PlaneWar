package ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GetImg {
    public static BufferedImage getImg(String name){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src/img/"+name));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return image;
    }
}
