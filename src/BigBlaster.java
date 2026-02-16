import java.awt.*;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class BigBlaster extends Weapon {
    public BigBlaster(Image image) {
        super(image);
        delay = 700;
    }

    @Override
    public void toAttack(HealthPoint enemy) {

    }
    @Override
    public void toShoot(double x, double y, double angle, Direction direction) {
        if (!isTimerActive) {
            isTimerActive = true;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    bullets.addLast(new Bullet(x, y, 15, angle, 100, direction, image_bullet));// 500 milliseconds delay
                    isTimerActive = false;
                }
            }, delay); // 500 миллисекунд задержка
        }
    }
    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(g);
        }
    }
    @Override
    public void moveBullets() {
        LinkedList<Bullet> new_bullets = new LinkedList<>();
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move();
            if (bullets.get(i).x <= 2000 && bullets.get(i).x >= 0 &&
                    bullets.get(i).y <= 2000 && bullets.get(i).y >= 0) {
                new_bullets.add(bullets.get(i));
            }
        }
        bullets = new_bullets;
    }
    @Override
    public LinkedList<Bullet> getBullets() {
        return bullets;
    }
    @Override
    public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
    }
}
