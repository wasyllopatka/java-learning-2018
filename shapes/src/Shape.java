import java.awt.*;

abstract class Shape {
    public Point start;

    public abstract void draw(Graphics2D g);

    public abstract void move(int offsetX, int offsetY);

    public abstract boolean contains(Point point);

    public abstract void setColor(Color color);

    public abstract Color getColor();
}
