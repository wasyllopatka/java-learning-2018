import javax.swing.*;
import java.awt.*;

public class Sprite {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;
    protected int direction;

    public Sprite(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    protected void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void move() {
        x += direction;
        if (x >= 700) {
            x = -120;
        }
        if (x < -120) {
            x = 700;
        }
    }
}