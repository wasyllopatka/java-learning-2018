import java.awt.*;

public class DrawShapeFactory {

    private Shape shape;

    public DrawShapeFactory(int x, int y, int r) {
        shape = new Circle(x, y, r);
    }

    public DrawShapeFactory(int x, int y, int w, int h) {
        shape = new Rectangle(x, y, w, h);
    }

    public DrawShapeFactory(int[]xValues, int [] yValues) {
        shape = new Triangle(xValues, yValues);
    }

    // Methods

    public void draw(Graphics2D g) {
        shape.draw(g);
    }

    public void move(int offsetX, int offsetY) {
        shape.move(offsetX, offsetY);
    }

    public boolean contains(Point point) { return shape.contains(point); }

    public void setColor(Color color) {
        shape.setColor(color);
    }





}
