import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;


public class Tank{


    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;
    private int origX;
    private int origY;
    private int origAngle;
    private Rectangle hitBox;
    public int hp = 100 * 2;
    private int lives = 3;

    private final int R = 2;
    private final int ROTATIONSPEED = 3;

    private ArrayList<Bullet> Bullets = new ArrayList<Bullet>() ;

    private BufferedImage img;
    private BufferedImage livesImg;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;

    Tank(int x, int y, int vx, int vy, int angle, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        this.origX = x;
        this.origY = y;
        this.origAngle = angle;
        this.hitBox = new Rectangle(img.getWidth(),img.getHeight());
        try{
            livesImg = ImageIO.read(getClass().getResource("/resources/lives.png"));
            BufferedImage temp;
            temp = (BufferedImage) livesImg;
            this.hitBox = new Rectangle(50,50);
        }
        catch(IOException ex){
        }
    }



    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleShootPressed() {
        this.ShootPressed = true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleShootPressed() {
        this.ShootPressed = false;
    }



    public void update() {
        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }
        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        hitBox.setLocation(this.x,this.y);

        //Lives
        if (hp <= 0 ) {
            lives--;
            hp = 100 * 2;
            x = origX;
            y = origY;
            angle = origAngle;
            vx = 0;
            vy = 0;
        }

    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
    }


    public void addBullet(){
        Bullets.add(new Bullet(this.x + vx, this.y + vy, this.vx, this.vy, this.angle));
    }

    public ArrayList<Bullet> getBullets() {
        return Bullets;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public float getHp() {
        if(hp <= 0 && getLives() > 0){
            hp = 20; //20 to fix it for some reason
        }
        return hp;
    }

    public BufferedImage getlivesImg(){
        return livesImg;
    }


    public void setHp(int hp){
        this.hp = hp;
    }


    public int getLives() {
        return lives;
    }

    public void setLives(int lives){
        this.lives = lives;
    }

    private void checkBorder() {
        if (x < 40) {
            x = 40;
        }
        if (x >= TRE.WORLD_WIDTH - 88) {
            x = TRE.WORLD_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= TRE.WORLD_HEIGHT - 88) {
            y = TRE.WORLD_HEIGHT - 88;
        }
    }

    public Rectangle getBounds(){
        return (new Rectangle (x, y, 50, 50 ));
    }


    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }


    void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, rotation,null);
//        g.setColor(Color.red);
//        Graphics2D gd =(Graphics2D)g;
//        gd.draw(hitBox);

    }


}
