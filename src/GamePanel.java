import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    public Ship orc1, orc2;
    Timer timer;
    boolean gameOver = false;
    private Image backgroundImage, explosion;
    public GamePanel() {
        setFocusable(true);
        addKeyListener(this);
        timer = new Timer(50, this);
        Image imageShip1, imageShip2, imageBullet, imageBigBullet;
        try {
            backgroundImage = ImageIO.read(new File("src/starfield-1.png"));
            imageShip1 = ImageIO.read(new File("src/good_mini_xwing1.png"));
            imageShip2 = ImageIO.read(new File("src/good_mini_xwing2.png"));
            imageBullet = ImageIO.read(new File("src/bullet.png"));
            imageBigBullet = ImageIO.read(new File("src/BigBullet.png"));
            explosion = ImageIO.read(new File("src/explosion.png"));
        } catch (IOException e) {
            System.out.println("Облом(");
            throw new RuntimeException(e);
        }
        Random random = new Random();
        Weapon w_orc1 = new Blaster(imageBullet);
        orc1 = new Ship(random.nextInt(0, 1400), random.nextInt(0, 700), 30, 0, imageShip1, w_orc1, 100);
        Weapon w_orc2 = new BigBlaster(imageBigBullet);
        orc2 = new Ship(random.nextInt(0, 1400), random.nextInt(0, 700), 30, 0, imageShip2, w_orc2, 100);
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            orc1.direction.right = true;
            orc1.direction.left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            orc1.direction.left = true;
            orc1.direction.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            orc1.direction.up = true;
            orc1.direction.down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            orc1.direction.down = true;
            orc1.direction.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            orc2.direction.right = true;
            orc2.direction.left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            orc2.direction.left = true;
            orc2.direction.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            orc2.direction.up = true;
            orc2.direction.down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            orc2.direction.down = true;
            orc2.direction.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            orc1.toShoot();
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            orc2.toShoot();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            orc1.direction.right = false;
            orc1.direction.left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            orc1.direction.left = false;
            orc1.direction.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            orc1.direction.up = false;
            orc1.direction.down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            orc1.direction.down = false;
            orc1.direction.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            orc2.direction.right = false;
            orc2.direction.left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            orc2.direction.left = false;
            orc2.direction.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            orc2.direction.up = false;
            orc2.direction.down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            orc2.direction.down = false;
            orc2.direction.up = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            orc1.move();
            if (orc1.wasHeatBy(orc2)) {
                System.out.println("Ударили первого!");
                if (orc1.hp <= 0) {
                    finishGame(orc1);
                }
            }
            orc2.move();
            if (orc2.wasHeatBy(orc1)) {
                System.out.println("Ударили второго!");
                if (orc2.hp <= 0) {
                    finishGame(orc2);
                }
            }
            repaint();
        }
    }
    public void finishGame(Ship ship) {
        System.out.println("Корабль разбит!");
        gameOver = true;
        timer.stop();
        ship.image = explosion;
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
        orc1.draw(g);
        orc2.draw(g);
    }
}
