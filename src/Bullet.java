import java.awt.*;

public class Bullet extends Unit{
    public int damage;

    public Bullet(double x, double y, int speed, double angle, int damage, Direction direction, Image image) {
        super((int)x, (int)y, speed, angle, image);
        this.damage = damage;
        this.direction = direction;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.rotate(angle, x, y);
        g.drawImage(image, x, y, null);
        graphics2D.rotate(-angle, x, y);

    }

    @Override
    public void move() {
        int dx = (int)(Math.sin(angle) * speed);
        int dy = (int)(-Math.cos(angle) * speed);
        x += dx;
        y += dy;
    }
    public int getDamage() {
        return this.damage;
    }
}
