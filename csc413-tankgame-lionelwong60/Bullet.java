import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;


import static javax.imageio.ImageIO.read;

public class Bullet{

    private double x;
    private double y;
    private int vx;
    private int vy;
    private int angle;
    private double R = 4;
    public Image bulletImg = null;
    private Rectangle hitBox;


    public Bullet(double x, double y, int vx, int vy, int angle){
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.vx = vx;
        this.vy = vy;
        try{
            bulletImg = ImageIO.read(getClass().getResource("/resources/mrSaturn.png"));
            BufferedImage temp;
            temp = (BufferedImage) bulletImg;
            this.hitBox = new Rectangle(20,20);
        }
        catch(IOException ex){
        }
    }



    public Rectangle getBounds(){
        return (new Rectangle ((int)x, (int)y,40, 40));
    }

    public void update(){
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        hitBox.setLocation((int)this.x,(int)this.y);

    }

    public void render(Graphics g){
        g.drawImage(bulletImg, (int)x, (int)y, 40,40, null);
//        g.setColor(Color.red);
//        Graphics2D g2d =(Graphics2D)g;
//        g2d.draw(hitBox);
    }
}
