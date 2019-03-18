import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;


public class AnimatedButterfly extends JPanel implements ActionListener {

    private static int maxWidth;
    private static int maxHeight;

    private int indentWidth;
    private int indentHeight;
    private int borderWidth;

    private int imageWidth = 210;
    private int imageHeight = 130;


    double points[][] = {
            {0, 0}, {-105, -15}, {-45, -60}, {0, 0}, {105, -15},
            {45, -60}, {0, 0}, {105, 15}, {45, 65},
            {0, 0}, {-105, 15}, {-45, 65}, {0, 0}
    };
    Timer timer;

    private double scale = 1;
    private double delta = 0.01;
    private double dx = 2;
    private double tx = 0;
    private double dy = 2;
    private double ty = 0;

    public AnimatedButterfly() {
        timer = new Timer(10, this);
        timer.start();

        indentWidth = 100;
        indentHeight = 100;
        borderWidth = 10;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setBackground(Color.CYAN);
        g2d.setColor(Color.BLUE);
        g2d.clearRect(0, 0, maxWidth + 1, maxHeight + 1);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        BasicStroke bs1 = new BasicStroke(borderWidth, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs1);
        g2d.drawRect(indentWidth, indentHeight, maxWidth - indentWidth * 2, maxHeight - indentHeight * 2);

        g2d.setStroke(new BasicStroke());

        g2d.translate(maxWidth / 2, maxHeight / 2);

        g2d.translate(tx, ty);

        GeneralPath wings = new GeneralPath();
        wings.moveTo(points[0][0], points[0][1]);
        for (int k = 1; k < points.length; k++)
            wings.lineTo(points[k][0], points[k][1]);
        wings.closePath();

        g2d.scale(scale, 0.99);

        GradientPaint gpBody = new GradientPaint(5, 25,
                new Color(0xadd100), 20, 2, new Color(0x7b920a), true);
        g2d.setPaint(gpBody);
        g2d.fill(new Ellipse2D.Double(-10, -30, 10 * 2, 30 * 2));

        GradientPaint gpWings = new GradientPaint(5, 25,
                new Color(0xc31432), 25, 5, new Color(0x240b36), true);
        g2d.setPaint(gpWings);
        g2d.fill(wings);

        g2d.setColor(Color.BLACK);
        g2d.drawLine(-3, -15, -10, -65);
        g2d.drawLine(3, -15, 10, -65);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animated butterfly");
        frame.add(new AnimatedButterfly());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        if (scale < 0.01) {
            delta = -delta;
        } else if (scale > 0.99) {
            delta = -delta;
        }

        int xLimit = (maxWidth - 2 * (indentWidth + borderWidth)) / 2 - imageWidth/2;
        int yLimit = (maxHeight - 2 * (indentHeight + borderWidth)) / 2 - imageHeight/2;


        if (ty > -yLimit && tx <= -xLimit) {
            ty -= dy;
        } else if (ty < yLimit && tx >= xLimit) {
            ty += dy;
        } else if (tx > -xLimit && ty >= yLimit) {
            tx -= dx;
        } else
            tx += dx;

        scale += delta;
        repaint();
    }

}
