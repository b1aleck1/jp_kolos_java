package kolokwium;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class DrawingPanel extends JPanel {
    private final List<MovingRectangle> rectangles = new ArrayList<>();
    private Point startPoint;

    public DrawingPanel() {
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (startPoint == null) {
                    startPoint = e.getPoint();
                } else {
                    Point endPoint = e.getPoint();
                    addRectangle(startPoint, endPoint);
                    startPoint = null;
                }
            }
        });
    }

    private void addRectangle(Point p1, Point p2) {
        int x = Math.min(p1.x, p2.x);
        int y = Math.min(p1.y, p2.y);
        int width = Math.abs(p1.x - p2.x);
        int height = Math.abs(p1.y - p2.y);

        MovingRectangle rectangle = new MovingRectangle(x, y, width, height);
        rectangles.add(rectangle);
        new Thread(rectangle).start();
        repaint();
    }

    public void hunt(int x, int y) {
        rectangles.removeIf(rect -> rect.contains(x, y));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (MovingRectangle rectangle : rectangles) {
            rectangle.draw(g);
        }
    }

    private class MovingRectangle implements Runnable {
        private int x, y, width, height;
        private int dx, dy;
        private Color color;
        Random random = new Random();

        public MovingRectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
            Random random = new Random();
            dx = random.nextInt(5) + 1;
            dy = random.nextInt(5) + 1;
        }

        public boolean contains(int px, int py) {
            return px >= x && px <= x + width && py >= y && py <= y + height;
        }

        public void draw(Graphics g) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }

        @Override
        public void run() {
            while (true) {
                x += dx;
                y += dy;

                if (x < 0 || x + width > getWidth()) dx = -dx;
                if (y < 0 || y + height > getHeight()) dy = -dy;

                repaint();

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}


