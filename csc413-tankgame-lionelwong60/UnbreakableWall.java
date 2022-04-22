import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class UnbreakableWall {
    private Image img = null;
    private int x;
    private int y;
    private Rectangle hitBox;

    public UnbreakableWall(int x, int y){
        this.x = x;
        this.y = y;
        try{
            img = ImageIO.read(getClass().getResource("/resources/Wall1.png"));
            this.hitBox = new Rectangle(x, y,40,40);
        }
        catch(IOException ex){
        }
    }

    public Rectangle getBounds(){
        return (new Rectangle (x, y,40, 40));
    }

    public void render(Graphics g){
        g.drawImage(img, x, y, 40,40, null);
        Graphics2D g2d =(Graphics2D)g;

    }
}
