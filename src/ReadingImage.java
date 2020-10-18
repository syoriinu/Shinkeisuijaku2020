import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ReadingImage {
    public BufferedImage readImage(String fileName){
        BufferedImage bi = null;
        URL url = this.getClass().getResource("img/" + fileName);
        try{
            bi = ImageIO.read(url);
        }catch (IOException e){}

        return bi;
    }
    public BufferedImage readItemImage(String fileName){
        BufferedImage bi = null;
        URL url = this.getClass().getResource("img/item/" + fileName);
        try{
            bi = ImageIO.read(url);
        }catch (IOException e){}

        return bi;
    }

    public BufferedImage readItemImage(URL url){
        BufferedImage bi = null;
        try{
            bi = ImageIO.read(url);
        }catch (IOException e){}

        return bi;
    }

}
