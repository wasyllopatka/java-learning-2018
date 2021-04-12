import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.*;
import java.util.List;

public class PaintPanel extends JPanel {
    private BufferedImage image, newImage;
    private String type = "Circle";
    private List<DrawShapeFactory> fShapes;
    private DrawShapeFactory currentFShape;
    private JPanel menuPanel = new JPanel();
    private JButton btnDelete = new JButton("Delete shape");
    private JButton btnMove = new JButton("Move shape");
    private JButton btnSaveChange = new JButton("Save changed");
    private final JTextField radiusTF = new JTextField(3);
    private final JTextField widthTF = new JTextField(3);
    private final JTextField heightTF = new JTextField(3);
    private int[] triangleXvalues;
    private int[] triangleYvalues;
    private MouseListener ml;
    private int count;
    private Point a = null;
    private Point b = null;
    private Point c = null;

    public PaintPanel() {
        fShapes = new ArrayList<>();
        setLayout(new BorderLayout());
        setBackground(new Color(66, 73, 73));
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.WHITE);
        JButton btnCircle = new JButton("Circle");
        JButton btnRectangle = new JButton("Rectangle");
        JButton btnTriangle = new JButton("Triangle");
        JButton btnSelect = new JButton("Select shape");
        JButton btnClear = new JButton("Clear panel");
        JLabel radiusLabel = new JLabel("Radius");
        JLabel widthLabel = new JLabel("Width");
        JLabel heightLabel = new JLabel("Height");
        btnMove.setVisible(false);
        btnSaveChange.setVisible(false);
        radiusTF.setText("50");
        widthTF.setText("100");
        heightTF.setText("50");

        menuPanel.add(btnCircle);
        menuPanel.add(radiusLabel);
        menuPanel.add(radiusTF);
        menuPanel.add(btnRectangle);
        menuPanel.add(widthLabel);
        menuPanel.add(widthTF);
        menuPanel.add(heightLabel);
        menuPanel.add(heightTF);
        menuPanel.add(btnTriangle);
        menuPanel.add(btnSelect);
        menuPanel.add(btnClear);
        menuPanel.add(btnDelete);
        menuPanel.add(btnMove);
        menuPanel.add(btnSaveChange);

        btnCircle.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setType("Circle");
                btnDelete.setVisible(false);
                btnMove.setVisible(false);
                repaintCurrentShape();
            }
        });

        btnRectangle.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setType("Rectangle");
                btnDelete.setVisible(false);
                btnMove.setVisible(false);
                repaintCurrentShape();
            }
        });

        btnTriangle.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setType("Triangle");
                btnDelete.setVisible(false);
                btnMove.setVisible(false);
                repaintCurrentShape();
            }
        });

        btnSelect.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setType("Select");
                btnDelete.setVisible(false);
                btnMove.setVisible(false);
                repaintCurrentShape();
            }
        });

        btnClear.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDelete.setVisible(false);
                image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
                fShapes.clear();
                repaint();
            }
        });


        btnMove.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setType("Move");
                btnSaveChange.setVisible(true);
            }
        });

        btnSaveChange.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaintCurrentShape();
                btnSaveChange.setVisible(false);
                setType("Default");
            }
        });

        btnDelete.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentFShape != null) {
                    Object[] options = {"Yes", "No"};
                    int deleteChoice = JOptionPane.showOptionDialog(getParent(), "Do you wand delete this shape?",
                            "According", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options,
                            options[0]);
                    if (deleteChoice == 0) {
                        fShapes.remove(currentFShape);
                        currentFShape = null;
                        repaint();
                    }
                }
            }
        });

        btnDelete.setVisible(false);
        ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                image = cloneImage(newImage);
                if (getType().equals("Triangle")) {
                    if (count < 1) {
                        a = e.getPoint();
                        count++;
                    } else if (count == 1) {
                        b = e.getPoint();
                        count++;
                    } else if (count == 2) {
                        c = e.getPoint();
                        count++;
                        triangleXvalues = new int[]{(int) a.getX(), (int) b.getX(), (int) c.getX()};
                        triangleYvalues = new int[]{(int) a.getY(), (int) b.getY(), (int) c.getY()};
                        image = cloneImage(newImage);
                        DrawShapeFactory factory;
                        if (count == 3) {
                            repaint();
                            count = 0;
                            factory = new DrawShapeFactory(triangleXvalues, triangleYvalues);
                            fShapes.add(factory);
                        }
                    }
                } else if (getType().equals("Select")) {
                    selectShape(e.getPoint());
                    setType("Default");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                DrawShapeFactory factory;
                switch (getType()) {
                    case "Circle":
                        factory = new DrawShapeFactory(e.getX(), e.getY(), Integer.parseInt(radiusTF.getText()));
                        fShapes.add(factory);
                        repaint();
                        break;
                    case "Rectangle":
                        factory = new DrawShapeFactory(e.getX(), e.getY(), Integer.parseInt(widthTF.getText()), Integer.parseInt(heightTF.getText()));
                        fShapes.add(factory);
                        repaint();
                        break;
                    case "Move":
                        currentFShape.move(e.getX(), e.getY());
                        repaint();
                        break;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        };

        btnCircle.setBackground(new Color(70, 215, 111));
        btnCircle.setForeground(Color.white);
        btnCircle.setFocusPainted(false);
        btnCircle.setFont(new Font("Courier", Font.BOLD, 11));
        btnCircle.setMargin(new Insets(7, 10, 7, 10));

        btnRectangle.setBackground(new Color(70, 215, 111));
        btnRectangle.setForeground(Color.white);
        btnRectangle.setFocusPainted(false);
        btnRectangle.setFont(new Font("Courier", Font.BOLD, 10));
        btnRectangle.setMargin(new Insets(7, 10, 7, 10));

        btnTriangle.setBackground(new Color(70, 215, 111));
        btnTriangle.setForeground(Color.white);
        btnTriangle.setFocusPainted(false);
        btnTriangle.setFont(new Font("Courier", Font.BOLD, 10));
        btnTriangle.setMargin(new Insets(7, 10, 7, 10));

        btnClear.setBackground(Color.white);
        btnClear.setForeground(new Color(70, 215, 111));
        btnClear.setFocusPainted(false);
        btnClear.setFont(new Font("Courier", Font.BOLD, 10));
        btnClear.setMargin(new Insets(10, 10, 10, 10));

        btnSelect.setBackground(Color.ORANGE);
        btnSelect.setForeground(Color.WHITE);
        btnSelect.setFocusPainted(false);
        btnSelect.setFont(new Font("Courier", Font.BOLD, 10));
        btnSelect.setMargin(new Insets(10, 10, 10, 10));

        btnDelete.setBackground(Color.RED);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFocusPainted(false);
        btnDelete.setFont(new Font("Courier", Font.BOLD, 10));
        btnDelete.setMargin(new Insets(10, 10, 10, 10));

        btnMove.setBackground(Color.BLUE);
        btnMove.setForeground(Color.WHITE);
        btnMove.setFocusPainted(false);
        btnMove.setFont(new Font("Courier", Font.BOLD, 10));
        btnMove.setMargin(new Insets(10, 10, 10, 10));

        btnSaveChange.setBackground(new Color(52, 152, 219));
        btnSaveChange.setForeground(Color.WHITE);
        btnSaveChange.setFocusPainted(false);
        btnSaveChange.setFont(new Font("Courier", Font.BOLD, 10));
        btnSaveChange.setMargin(new Insets(10, 10, 10, 10));

        addMouseListener(ml);
        add(menuPanel, BorderLayout.NORTH);
    }

    private void repaintCurrentShape() {
        if (currentFShape != null) {
            currentFShape.setColor(Color.white);
            repaint();
            currentFShape = null;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        g2d.drawRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        if (image == null)
            image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
        g2d.drawImage(image, 0, 0, null);
        for (DrawShapeFactory factory : fShapes) {
            image = cloneImage(newImage);
            factory.draw(g2d);
        }
    }

    private BufferedImage cloneImage(BufferedImage image) {
        if (image == null)
            return null;
        ColorModel cm = image.getColorModel();
        boolean isAlpha = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(cm, raster, isAlpha, null);
    }

    private String getType() {
        return type;
    }

    private void setType(String type) {
        this.type = type;
    }

    private void selectShape(Point point) {
        for (DrawShapeFactory factory : fShapes) {
            if (factory.contains(point)) {
                this.currentFShape = factory;
                btnDelete.setVisible(true);
                btnMove.setVisible(true);
                factory.setColor(Color.ORANGE);
                repaint();
                break;
            }
        }
    }
}

