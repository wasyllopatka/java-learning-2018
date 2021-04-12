import java.awt.*;

public class Rectangle extends Shape {

    private int width, height;
    private Color color;

    public Rectangle(int x, int y, int width, int height) {
        this.start = new Point(x, y);
        this.color = Color.white;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g) {
        g.setColor(getColor());
        g.drawRect(this.start.x - width / 2, this.start.y - height / 2, this.width, this.height);
    }

    @Override
    public boolean contains(Point point) {
        int rx = start.x - width / 2;
        int ry = start.y - height / 2;
        return point.getX() >= rx && point.getY() >= ry && point.getX() <= rx + width && point.getY() <= ry + height;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void move(int newX, int newY) {
        start.x = newX;
        start.y = newY;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
