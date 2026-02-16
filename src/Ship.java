import java.awt.*;
import java.util.LinkedList;

public class Ship extends Unit implements HealthPoint {
    public Weapon weapon;
    public int hp;
    public Ship(int x, int y, int speed, double angle, Image image1, Weapon weapon_, int hp) {
        super(x, y, speed, angle, image1);
        this.weapon = weapon_;
        this.hp = hp;
    }

    @Override
    public void move() {
        int dx = (int)(Math.sin(angle) * speed);
        int dy = (int)(-Math.cos(angle) * speed);
        if (direction.up) {
            x = (x + dx + 1400) % 1400;
            y = (y + dy + 750) % 750;
        }
        if (direction.down) {
            x = (x - dx + 1400) % 1400;
            y = (y - dy + 750) % 750;
        }
        if (direction.left) {
            angle -= 0.1;
        }
        if (direction.right) {
            angle += 0.1;
        }
        weapon.moveBullets();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.rotate(angle, x + (double) image.getWidth(null) / 2, y + (double) image.getHeight(null) / 2);
        g.drawImage(image, x, y, null);
        graphics2D.rotate(-angle, x + (double) image.getWidth(null) / 2, y + (double) image.getHeight(null) / 2);
        weapon.draw(g);
    }
    public void toShoot() {
        weapon.toShoot(x + (double) image.getWidth(null) / 2, y + (double) image.getHeight(null) / 2, angle, direction);
    }

    public boolean intersectsWithBullet(Bullet bullet) {
        Rectangle shipBounds = new Rectangle(x, y, image.getWidth(null) - 20, image.getHeight(null));
        Rectangle bulletBounds = new Rectangle(bullet.x, bullet.y, bullet.image.getWidth(null), bullet.image.getHeight(null));
        return shipBounds.intersects(bulletBounds);
    }

    public boolean wasHeatBy(Ship other_ship) {
        LinkedList<Bullet> bullets = other_ship.weapon.getBullets();
        for (Bullet bullet : bullets) {
            if (intersectsWithBullet(bullet)) {
                this.hp -= bullet.getDamage();
                other_ship.weapon.removeBullet(bullet);
                return true;
            }
        }
        return false;
    }

    @Override
    public int getHP() {
        return hp;
    }

    @Override
    public void setHP(int healthPoint) {
        hp = healthPoint;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }
}
