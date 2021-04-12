import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Road {
    private BufferedImage background;
    private ArrayList<Point> firstLine = new ArrayList<>();
    private ArrayList<Point> secondLine = new ArrayList<>();

    public Road() {
        setBackground();
        createLines();
        startThread();
    }

    public void drawRoad(Graphics2D g) {
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.ORANGE);
        for (int i = 0; i < 4; i++) {
            g.setStroke(new BasicStroke(7));
            g.drawLine(firstLine.get(i).x, firstLine.get(i).y, firstLine.get(i).x, firstLine.get(i).y + 40);
            g.drawLine(secondLine.get(i).x, secondLine.get(i).y, secondLine.get(i).x, secondLine.get(i).y + 40);
        }
    }

    private void setBackground() {
        try {
            background = ImageIO.read(this.getClass().getResource("icons/road.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createLines() {
        for (int i = 0; i < 4; i++) {
            firstLine.add(new Point(167, 75 + i * (200)));
            secondLine.add(new Point(334, 75 + i * (200)));
        }
    }

    public void startThread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 0; i < 4; i++) {
                        firstLine.get(i).y = (firstLine.get(i).y + 4) % 800;
                        secondLine.get(i).y = (secondLine.get(i).y + 4) % 800;
                    }
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
}
