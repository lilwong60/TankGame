import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class PowerUp {
    private Image Pimg = null;
    private int x;
    private int y;
    private Rectangle hitBox;

    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
        try{
            Pimg = ImageIO.read(getClass().getResource("/resources/powerUp.png"));
            BufferedImage temp;
            temp = (BufferedImage) Pimg;
            this.hitBox = new Rectangle(x, y,60,60);
        }
        catch(IOException ex){
        }
    }

    public Rectangle getBounds(){
        return (new Rectangle (x, y,60, 60));
    }

    public void render(Graphics g){
        g.drawImage(Pimg, x, y, 75,75, null);
//        Graphics2D g2d =(Graphics2D)g;
//        g.setColor(Color.white);
//        g2d.draw(hitBox);
    }
}
