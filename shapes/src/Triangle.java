import java.awt.*;

public class Triangle extends Shape {

    private int[] xValues;
    private int[] yValues;
    private Color color;

    public Triangle(int[] xValues, int[] yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
        this.color = Color.white;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(getColor());
        int size = 3;
        g.drawPolygon(xValues, yValues, size);
    }

    @Override
    public boolean contains(Point point) {
        int x1 = xValues[0], y1 = yValues[0];
        int x2 = xValues[1], y2 = yValues[1];
        int x3 = xValues[2], y3 = yValues[2];
        int x = (int) point.getX();
        int y = (int) point.getY();
        double ABC = Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
        double ABP = Math.abs(x1 * (y2 - y) + x2 * (y - y1) + x * (y1 - y2));
        double APC = Math.abs(x1 * (y - y3) + x * (y3 - y1) + x3 * (y1 - y));
        double PBC = Math.abs(x * (y2 - y3) + x2 * (y3 - y) + x3 * (y - y2));

        return ABP + APC + PBC == ABC;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void move(int newX, int newY) {
    }

    @Override
    public Color getColor() {
        return color;
    }
}
