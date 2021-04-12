import java.awt.*;

public class Circle extends Shape {

    public int x, y, radius;
    private Color color;


    public Circle(int x, int y, int radius) {
        this.start = new Point(x, y);
        this.color = Color.white;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(getColor());
        g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    @Override
    public boolean contains(Point point) {
        if (Math.pow(point.getX() - x, 2) + Math.pow(point.getY() - y, 2) < Math.pow(radius, 2)) {
            return true;
        }
        return false;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void move(int newX, int newY) {
        x = newX;
        y = newY;
    }

}
