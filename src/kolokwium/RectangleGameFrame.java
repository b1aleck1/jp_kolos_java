package kolokwium;

import javax.swing.*;
import java.awt.*;

class RectangleGameFrame extends JFrame {
    private final DrawingPanel drawingPanel;
    private final JSlider sliderX;
    private final JSlider sliderY;

    public RectangleGameFrame() {
        setTitle("Polowanie na prostokąty");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());

        sliderX = new JSlider(0, 800, 400);
        sliderY = new JSlider(0, 600, 300);
        sliderY.setOrientation(SwingConstants.VERTICAL);

        JButton huntButton = new JButton("Poluj");
        huntButton.setFont(new Font("Arial", Font.BOLD, 30));
        huntButton.setBackground(new Color(0, 250, 150));
        huntButton.addActionListener(e -> drawingPanel.hunt(sliderX.getValue(), sliderY.getValue()));

        controlPanel.add(sliderX, BorderLayout.SOUTH);
        controlPanel.add(sliderY, BorderLayout.WEST);
        controlPanel.add(huntButton, BorderLayout.CENTER);

        add(controlPanel, BorderLayout.EAST);

        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}