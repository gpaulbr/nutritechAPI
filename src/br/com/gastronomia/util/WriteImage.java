package br.com.gastronomia.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

public class WriteImage {

    private static ResourceBundle pathImage = ResourceBundle.getBundle(Constantes.AMBIENTE_PROPERTIES);

    public static String write(BufferedImage image, String fileName) {
        String fullPath = pathImage.getString(Constantes.PATH_IMAGE) + "//" + fileName + ".jpg";
        try {
            ImageIO.write(image, "jpg",new File(fullPath));
            //ImageIO.write(image, "gif",new File("C:\\out.gif"));
            //ImageIO.write(image, "png",new File("C:\\out.png"));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("Done");
        return fullPath;
    }

    public static BufferedImage imageFromBytes(byte[] imageBytes) {
        BufferedImage bi = null;
        try {
            InputStream in = new ByteArrayInputStream(imageBytes);
            bi = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bi;
    }
}