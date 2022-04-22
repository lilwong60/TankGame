import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.ArrayList;


public class TRE extends JPanel{

    public static final int WORLD_WIDTH = 1880;
    public static final int WORLD_HEIGHT = 1200;
    public static final int SCREEN_WIDTH = 1400;
    public static final int SCREEN_HEIGHT = 920;
    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jf;
    public static Tank t1;
    public static Tank t2;
    public static int lastt1x;
    public static int lastt1y;
    public static int lastt2x;
    public static int lastt2y;

    private Image background = null;
    public static ArrayList<UnbreakableWall> UnbreakableWallList = new ArrayList<>() ;
    public static ArrayList<BreakableWall> BreakableWallList = new ArrayList<>() ;
    public static ArrayList<PowerUp> PowerUpList = new ArrayList<>() ;



    public static void main(String[] args) {
        Thread x;
        TRE trex = new TRE();
        trex.init();
        try {

            //while (trex.t1.getLives() > 0 && trex.t2.getLives() > 0) {
            while(true){
               if(trex.t1.getLives() > 0 && trex.t2.getLives() > 0) {
                   lastt1x = trex.t1.getX();
                   lastt1y = trex.t1.getY();
                   lastt2x = trex.t2.getX();
                   lastt2y = trex.t2.getY();
                   trex.t1.update();
                   trex.t2.update();
                   for (int i = 0; i < t1.getBullets().size(); i++) {
                       t1.getBullets().get(i).update();
                       System.out.println("T1 BULLETS:" + t1.getBullets().size());
                   }
                   for (int i = 0; i < t2.getBullets().size(); i++) {
                       t2.getBullets().get(i).update();
                       System.out.println("T2 BULLETS:" + t2.getBullets().size());
                   }

                   trex.repaint();
                   trex.checkTCollision(trex.t1, trex.t2);
                   trex.checkTCollision(trex.t2, trex.t1);
                   trex.checkUbWallCollision(trex.t1);
                   trex.checkUbWallCollision(trex.t2);
                   trex.checkBWallCollision(trex.t1);
                   trex.checkBWallCollision(trex.t2);
                   trex.checkPCollision(trex.t1);
                   trex.checkPCollision(trex.t2);
                   trex.checkTBWallCollision(trex.t1, lastt1x, lastt1y);
                   trex.checkTBWallCollision(trex.t2, lastt2x, lastt2y);
                   trex.checkTUbWallCollision(trex.t1, lastt1x, lastt1y);
                   trex.checkTUbWallCollision(trex.t2, lastt2x, lastt2y);

                   System.out.println(trex.t1);
                   System.out.println(trex.t2);

                   Thread.sleep(1000 / 144);
               }
            }
        } catch (InterruptedException ignored) {

        }

    }


    private void init() {
        this.jf = new JFrame("Tank Rotation");
        this.world = new BufferedImage(TRE.WORLD_WIDTH, TRE.WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage t1img = null, t2img = null;

          //Unbreakable Wall Placement
        for(int i =0;i<TRE.WORLD_HEIGHT; i+=40) {
            UnbreakableWallList.add(new UnbreakableWall(0, i));
        }
        for(int i =0;i<TRE.WORLD_HEIGHT; i+=40) {
            UnbreakableWallList.add(new UnbreakableWall(TRE.WORLD_WIDTH - 40, i));
        }
        for(int i =0;i<TRE.WORLD_WIDTH; i+=40) {
            UnbreakableWallList.add(new UnbreakableWall(i, 0));
        }
        for(int i =0;i<TRE.WORLD_WIDTH; i+=40) {
            UnbreakableWallList.add(new UnbreakableWall(i,TRE.WORLD_HEIGHT - 40 ));
        }
        for(int i = 0;i<TRE.WORLD_HEIGHT / 2 - 120; i+=40) {
            UnbreakableWallList.add(new UnbreakableWall(400, i));
        }
        for(int i = 0;i<TRE.WORLD_HEIGHT / 2 - 120; i+=40) {
            UnbreakableWallList.add(new UnbreakableWall(1360, i));
        }
        for(int i =0;i<TRE.WORLD_WIDTH / 4 - 200; i+=40) {
            UnbreakableWallList.add(new UnbreakableWall(i, 480));
        }
        for(int i =0;i<TRE.WORLD_WIDTH / 4 - 200; i+=40) {
            UnbreakableWallList.add(new UnbreakableWall(1600 + (i), 480));
        }
        for(int i =0;i<TRE.WORLD_WIDTH / 4 ; i+=40) {
            UnbreakableWallList.add(new UnbreakableWall(640 + (i), 360));
        }
        for(int i = 0;i<TRE.WORLD_HEIGHT / 2 - 120; i+=40) {
            UnbreakableWallList.add(new UnbreakableWall(640, 400 + (i)));
        }
        for(int i = 0;i<TRE.WORLD_HEIGHT / 2 - 120; i+=40) {
            UnbreakableWallList.add(new UnbreakableWall(1080, 400 + (i)));
        }
        for(int i = 0;i<TRE.WORLD_WIDTH / 4 - 240; i+=40) {
            UnbreakableWallList.add(new UnbreakableWall(WORLD_WIDTH/2 - 180 + (i), 760 ));
        }
        UnbreakableWallList.add(new UnbreakableWall(WORLD_WIDTH/2 - 180, 600));
        UnbreakableWallList.add(new UnbreakableWall(WORLD_WIDTH/2 + 20, 600));





        //Breakable Wall Placement
        for(int i = 0;i<TRE.WORLD_HEIGHT; i+=40) {
            BreakableWallList.add(new BreakableWall(1080, i));
        }
        for(int i = 0;i<TRE.WORLD_HEIGHT; i+=40) {
            BreakableWallList.add(new BreakableWall(640, i));
        }
        for(int i =0;i<TRE.WORLD_WIDTH / 2 + 360 ; i+=40) {
            BreakableWallList.add(new BreakableWall(280 + (i), 480));
        }
        for(int i =0;i<TRE.WORLD_WIDTH; i+=40) {
            BreakableWallList.add(new BreakableWall(i, 880));
        }

        //PowerUp Placement
        PowerUpList.add(new PowerUp(WORLD_WIDTH/2, 400));
        PowerUpList.add(new PowerUp(WORLD_WIDTH/2 - 200, 400));
        PowerUpList.add(new PowerUp(WORLD_WIDTH/2 - 80,  200));
        PowerUpList.add(new PowerUp(WORLD_WIDTH/2 - 80, 1000));




        try {
            BufferedImage tmp;
            System.out.println(System.getProperty("user.dir"));
            /*
             * note class loaders read files from the out folder (build folder in netbeans) and not the
             * current working directory.
             */
            t1img = ImageIO.read(getClass().getResource("/resources/tank1.png"));
            t2img = ImageIO.read(getClass().getResource("/resources/tank2.png"));
            background = ImageIO.read(getClass().getResource("/resources/EBbackground.png"));



        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //Tank Positions
        t1 = new Tank(200, 200, 0, 0, 0, t1img);
        t2 = new Tank(1650, 200, 0, 0, 180, t2img);

        //Tank Controls
        TankControl tc1 = new TankControl(t1, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);
        TankControl tc2 = new TankControl(t2, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);


        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);


        this.jf.addKeyListener(tc1);
        this.jf.addKeyListener(tc2);


        this.jf.setSize(TRE.WORLD_WIDTH + 20, TRE.WORLD_HEIGHT - 250);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);
    }



    public void checkTCollision(Tank tankA, Tank tankB){
        Rectangle r1 = tankB.getBounds();
        for(int i =0; i<tankA.getBullets().size();i++){
            Rectangle r2 = tankA.getBullets().get(i).getBounds();
            if (r1.intersects(r2)) {
                tankA.getBullets().remove(i);
                tankB.setHp((int) (tankB.getHp() - 40));
            }
        }
    }

    public void checkUbWallCollision(Tank tankA) {
        for (int i = 0; i < tankA.getBullets().size(); i++) {
            Rectangle r1 = tankA.getBullets().get(i).getBounds();
            for (int j = 0; j < UnbreakableWallList.size(); j++) {
                Rectangle r2 = UnbreakableWallList.get(j).getBounds();
                if (r1.intersects(r2)) {
                    tankA.getBullets().remove(i);
                    break;
                }
            }
        }
    }

    public void checkBWallCollision(Tank tankA) {
        for (int i = 0; i < tankA.getBullets().size(); i++) {
            Rectangle r1 = tankA.getBullets().get(i).getBounds();
            for (int j = 0; j < BreakableWallList.size(); j++) {
                Rectangle r2 = BreakableWallList.get(j).getBounds();
                if (r1.intersects(r2)) {
                    tankA.getBullets().remove(i);
                    BreakableWallList.remove(j);
                    break;
                }
            }
        }
    }

    public void checkPCollision(Tank tankA) {
            Rectangle r1 = tankA.getBounds();
            for (int i = 0; i < PowerUpList.size(); i++) {
                Rectangle r2 = PowerUpList.get(i).getBounds();
                if (r1.intersects(r2)) {
                    tankA.setLives(tankA.getLives() +1);
                    PowerUpList.remove(i);
                    break;
                }
            }

    }

    public void checkTBWallCollision(Tank tankA, int lastt1x, int lastt1y) {
        Rectangle r1 = tankA.getBounds();
        for (int j = 0; j < BreakableWallList.size(); j++) {
            Rectangle r2 = BreakableWallList.get(j).getBounds();
            if (r1.intersects(r2)) {
                tankA.setX(lastt1x);
                tankA.setY(lastt1y);
            }
        }
    }

    public void checkTUbWallCollision(Tank tankA, int lastt1x, int lastt1y) {
        Rectangle r1 = tankA.getBounds();
        for (int j = 0; j < UnbreakableWallList.size(); j++) {
            Rectangle r2 = UnbreakableWallList.get(j).getBounds();
            if (r1.intersects(r2)) {
                tankA.setX(lastt1x);
                tankA.setY(lastt1y);
            }
        }
    }



    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        Graphics2D buffer = world.createGraphics();
        //buffer.fillRect(0,0, TRE.SCREEN_WIDTH, TRE.SCREEN_HEIGHT );
        buffer.drawImage(background, 0, 0, WORLD_WIDTH,WORLD_HEIGHT, null);
        super.paintComponent(g2);
;
        //Drawing the Tanks
        this.t1.drawImage(buffer);
        this.t2.drawImage(buffer);

        //Drawing Breakable Wall
        for(int i=0; i<BreakableWallList.size(); i++){
            BreakableWallList.get(i).render(buffer);
        }

        //Drawing Unbreakable Wall
        for(int i=0; i<UnbreakableWallList.size(); i++){
            UnbreakableWallList.get(i).render(buffer);
        }

        //Drawing PowerUp
        for(int i=0; i<PowerUpList.size(); i++){
            PowerUpList.get(i).render(buffer);
        }

        //Drawing Bullets
        for(int i = 0; i< t1.getBullets().size(); i++){
            t1.getBullets().get(i).render(buffer);
        }
        for(int i = 0; i< t2.getBullets().size(); i++){
            t2.getBullets().get(i).render(buffer);
        }


        //Calculate SplitScreen
        int sub1x = t1.getX() - SCREEN_WIDTH * 7 / 20;
        int sub1y = t1.getY() - SCREEN_HEIGHT / 2;
        if ((t1.getX() - SCREEN_WIDTH * 7 / 20) < 0) {
            sub1x = 0;
        } else if ((t1.getX() + SCREEN_WIDTH * 7 / 20) > (WORLD_WIDTH)) {
            sub1x = WORLD_WIDTH - SCREEN_WIDTH * 7 / 10;
        }
        if ((t1.getY() - SCREEN_HEIGHT / 2) < 0) {
            sub1y = 0;
        } else if ((t1.getY() + SCREEN_HEIGHT / 2) > (WORLD_HEIGHT)) {
            sub1y = WORLD_HEIGHT - SCREEN_HEIGHT;
        }


        int sub2x = t2.getX() - SCREEN_WIDTH * 7 / 20;
        int sub2y = t2.getY() - SCREEN_HEIGHT / 2;
        if ((t2.getX() - SCREEN_WIDTH * 7 / 20) < 0) {
            sub2x = 0;
        } else if ((t2.getX() + SCREEN_WIDTH * 7 / 20) > (WORLD_WIDTH)) {
            sub2x = WORLD_WIDTH - SCREEN_WIDTH * 7 / 10;
        }
        if ((t2.getY() - SCREEN_HEIGHT / 2) < 0) {
            sub2y = 0;
        } else if ((t2.getY() + SCREEN_HEIGHT / 2) > (WORLD_HEIGHT)) {
            sub2y = WORLD_HEIGHT - SCREEN_HEIGHT;
        }


        //SplitScreen
        BufferedImage sub1 = this.world.getSubimage(sub1x, sub1y, SCREEN_WIDTH * 7 / 10, SCREEN_HEIGHT);
        BufferedImage sub2 = this.world.getSubimage(sub2x, sub2y, SCREEN_WIDTH * 7 / 10, SCREEN_HEIGHT);
        g2.drawImage(sub1, 0, 0, null);
        g2.drawImage(sub2, SCREEN_WIDTH * 13 / 20, 0, null);



        //Minimap
        g2.drawImage(world.getScaledInstance(300,300,0),740,WORLD_HEIGHT/2 + 20,null);

        //Tank 1 HP
        g2.setColor(Color.gray);
        g2.fillRect(WORLD_WIDTH-1810, 50,200,25);
        g2.setColor(Color.green);
        g2.fillRect(WORLD_WIDTH-1810, 50,t1.hp,25);
        for(int i = 0; i < t1.getLives();i++){
            g2.drawImage(t1.getlivesImg(),TRE.WORLD_WIDTH-1600+(40*i), 50, 40, 40, null);
        }


        //Tank 2 HP
        g2.setColor(Color.gray);
        g2.fillRect(WORLD_WIDTH-250, 50,200,25);
        g2.setColor(Color.green);
        g2.fillRect(WORLD_WIDTH-250, 50,t2.hp,25);
        for(int i = 0; i < t2.getLives();i++){
            g2.drawImage(t2.getlivesImg(),TRE.WORLD_WIDTH-300-(40*i), 50, 40, 40, null);
        }
    }


}
