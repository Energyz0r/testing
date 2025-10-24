package testdeleteafter;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Simple gravity-assist visualization:
 * - Planet at center (point mass)
 * - Two projectiles start at left with same speed, different impact parameters (y offsets)
 * - Shows straight (no-gravity) line and curved trajectories (gravity on)
 * - Displays deflection angles for each trajectory
 *
 * Paste into NetBeans as a single class file and run.
 */
public class TESTDELETEAFTER extends JPanel implements ActionListener {

    // UI / simulation parameters
    private final int WIDTH = 900;
    private final int HEIGHT = 600;

    // Planet (point mass) location
    private final double planetX = WIDTH / 2.0;
    private final double planetY = HEIGHT / 2.0;
    private final int planetRadius = 24;

    // Simulation control values (modifiable via sliders)
    private double gm = 20000;        // GM (gravitational parameter) - larger = stronger pull
    private double speed = 250;       // initial speed in pixels / second
    private double impactY = HEIGHT / 2.0 - 40; // y position for the close trajectory
    private final double compareDelta = 150;    // offset for the farther trajectory (impactY + compareDelta)

    // Integration parameters
    private final double dt = 0.02;   // time step (seconds)
    private final double maxTime = 10; // max simulation time (seconds)

    // Precomputed trajectories
    private java.util.List<Point.Double> straightLine;
    private java.util.List<Point.Double> trajClose;
    private java.util.List<Point.Double> trajFar;
    private double deflectionCloseDeg = 0;
    private double deflectionFarDeg = 0;

    // UI elements
    private final JSlider gmSlider;
    private final JSlider speedSlider;
    private final JSlider impactSlider;
    private final JButton recomputeButton;
    private final Timer repaintTimer;

    public TESTDELETEAFTER() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setLayout(null);

        // Gravity slider
        gmSlider = new JSlider(1000, 60000, (int) gm);
        gmSlider.setBounds(10, HEIGHT - 120, 300, 40);
        gmSlider.setMajorTickSpacing(10000);
        gmSlider.setPaintTicks(true);
        gmSlider.setPaintLabels(true);
        gmSlider.addChangeListener(e -> {
            gm = gmSlider.getValue();
            computeAllTrajectories();
            repaint();
        });
        add(gmSlider);

        JLabel gmLabel = new JLabel("Gravity (GM)");
        gmLabel.setForeground(Color.WHITE);
        gmLabel.setBounds(10, HEIGHT - 145, 120, 20);
        add(gmLabel);

        // Speed slider
        speedSlider = new JSlider(50, 600, (int) speed);
        speedSlider.setBounds(320, HEIGHT - 120, 300, 40);
        speedSlider.setMajorTickSpacing(150);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(e -> {
            speed = speedSlider.getValue();
            computeAllTrajectories();
            repaint();
        });
        add(speedSlider);

        JLabel speedLabel = new JLabel("Speed");
        speedLabel.setForeground(Color.WHITE);
        speedLabel.setBounds(320, HEIGHT - 145, 60, 20);
        add(speedLabel);

        // Impact slider (controls close trajectory initial Y)
        impactSlider = new JSlider(50, HEIGHT - 200, (int) impactY);
        impactSlider.setBounds(640, HEIGHT - 120, 230, 40);
        impactSlider.setMajorTickSpacing(100);
        impactSlider.setPaintTicks(true);
        impactSlider.setPaintLabels(true);
        impactSlider.addChangeListener(e -> {
            impactY = impactSlider.getValue();
            computeAllTrajectories();
            repaint();
        });
        add(impactSlider);

        JLabel impactLabel = new JLabel("Close trajectory Y (impact parameter)");
        impactLabel.setForeground(Color.WHITE);
        impactLabel.setBounds(520, HEIGHT - 145, 300, 20);
        add(impactLabel);

        // Recompute button (for explicit recalculation if needed)
        recomputeButton = new JButton("Recompute");
        recomputeButton.setBounds(10, HEIGHT - 60, 120, 30);
        recomputeButton.addActionListener(e -> {
            computeAllTrajectories();
            repaint();
        });
        add(recomputeButton);

        // Precompute initial trajectories
        computeAllTrajectories();

        // Timer to update animation (not a physics animation; trajectories are precomputed)
        repaintTimer = new Timer(40, this);
        repaintTimer.start();

        // Keyboard convenience: arrow keys change impact parameter
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    impactY = Math.max(10, impactY - 10);
                    impactSlider.setValue((int) impactY);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    impactY = Math.min(HEIGHT - 250, impactY + 10);
                    impactSlider.setValue((int) impactY);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    gm = Math.min(120000, gm + 1000);
                    gmSlider.setValue((int) gm);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    gm = Math.max(100, gm - 1000);
                    gmSlider.setValue((int) gm);
                }
                computeAllTrajectories();
                repaint();
            }
        });
    }

    // Compute straight line and two gravity-affected trajectories
    private void computeAllTrajectories() {
        straightLine = computeStraightLine();
        trajClose = computeUnderGravity(impactY);
        trajFar = computeUnderGravity(impactY + compareDelta);
        deflectionCloseDeg = computeDeflectionDegrees(trajClose);
        deflectionFarDeg = computeDeflectionDegrees(trajFar);
    }

    // Straight line: starting at x=30 moving right at given speed (no gravity)
    private java.util.List<Point.Double> computeStraightLine() {
        java.util.List<Point.Double> pts = new ArrayList<>();
        double x = 30;
        double y = impactY; // reference line (close's initial y)
        double vx = speed;  // to the right
        double vy = 0;
        for (double t = 0; t < maxTime; t += dt) {
            x += vx * dt;
            y += vy * dt;
            pts.add(new Point.Double(x, y));
            if (x > WIDTH + 50) break;
        }
        return pts;
    }

    // Integrate motion under point-mass gravity for a projectile that starts at x=30, y=initialY
    private java.util.List<Point.Double> computeUnderGravity(double initialY) {
        java.util.List<Point.Double> pts = new ArrayList<>();
        double x = 30;
        double y = initialY;
        // initial velocity to the right
        double vx = speed;
        double vy = 0;

        for (double t = 0; t < maxTime; t += dt) {
            // compute acceleration from planet at (planetX, planetY)
            double dx = x - planetX;
            double dy = y - planetY;
            double r2 = dx * dx + dy * dy;
            double r = Math.sqrt(r2);
            // avoid singularity: if inside planet radius, stop
            if (r < planetRadius) {
                pts.add(new Point.Double(x, y));
                break;
            }
            double accel = gm / r2; // magnitude
            // acceleration components (toward planet center)
            double ax = -accel * (dx / r);
            double ay = -accel * (dy / r);

            // simple semi-implicit Euler integration
            vx += ax * dt;
            vy += ay * dt;
            x += vx * dt;
            y += vy * dt;

            pts.add(new Point.Double(x, y));

            // stop if it flies off-screen to the right or down/up too far
            if (x > WIDTH + 200 || x < -200 || y < -200 || y > HEIGHT + 200) break;
        }
        return pts;
    }

    // Compute deflection angle: take average direction at start and at end, return angle difference in degrees
    private double computeDeflectionDegrees(java.util.List<Point.Double> traj) {
        if (traj.size() < 6) return 0;
        // approximate initial direction using first few points
        Point.Double p0 = traj.get(0);
        Point.Double p1 = traj.get(Math.min(4, traj.size() - 1));
        Point.Double peMinus5 = traj.get(Math.max(0, traj.size() - 5));
        Point.Double pe = traj.get(traj.size() - 1);

        double vx0 = p1.x - p0.x;
        double vy0 = p1.y - p0.y;
        double vxf = pe.x - peMinus5.x;
        double vyf = pe.y - peMinus5.y;

        double dot = vx0 * vxf + vy0 * vyf;
        double mag0 = Math.hypot(vx0, vy0);
        double magf = Math.hypot(vxf, vyf);
        if (mag0 == 0 || magf == 0) return 0;
        double cos = Math.max(-1, Math.min(1, dot / (mag0 * magf)));
        double angleRad = Math.acos(cos);
        return Math.toDegrees(angleRad);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // cast
        Graphics2D g2 = (Graphics2D) g.create();
        // enable antialias
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw planet
        g2.setColor(new Color(60, 140, 255));
        g2.fillOval((int) (planetX - planetRadius), (int) (planetY - planetRadius), planetRadius * 2, planetRadius * 2);
        g2.setColor(new Color(255, 255, 255, 60));
        g2.drawOval((int) (planetX - planetRadius * 3), (int) (planetY - planetRadius * 3), planetRadius * 6, planetRadius * 6);

        // Straight line (dashed)
        g2.setStroke(new BasicStroke(1.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1f, new float[]{6f, 6f}, 0f));
        g2.setColor(Color.LIGHT_GRAY);
        drawPath(g2, straightLine);

        // Close trajectory: bright color
        g2.setStroke(new BasicStroke(2.5f));
        g2.setColor(new Color(255, 200, 40)); // orange-ish
        drawPath(g2, trajClose);

        // Far trajectory: dim color
        g2.setStroke(new BasicStroke(2.0f));
        g2.setColor(new Color(180, 180, 255)); // bluish
        drawPath(g2, trajFar);

        // Legend & labels
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g2.drawString(String.format("GM (gravity parameter): %.0f — Speed: %.0f px/s", gm, speed), 10, 18);
        g2.drawString(String.format("Close impact Y: %.0f  (Far impact Y = %.0f)", impactY, impactY + compareDelta), 10, 34);
        g2.drawString(String.format("Deflection (close) = %.2f°", deflectionCloseDeg), 10, 54);
        g2.drawString(String.format("Deflection (far)   = %.2f°", deflectionFarDeg), 10, 72);

        // small notes
        g2.drawString("Straight path (no gravity) = dashed gray", 10, HEIGHT - 10);

        g2.dispose();
    }

    // Helper to draw list of points as a connected polyline
    private void drawPath(Graphics2D g2, java.util.List<Point.Double> pts) {
        if (pts == null || pts.size() < 2) return;
        Point.Double prev = pts.get(0);
        for (int i = 1; i < pts.size(); i++) {
            Point.Double cur = pts.get(i);
            g2.draw(new Line2D.Double(prev.x, prev.y, cur.x, cur.y));
            prev = cur;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // animation tick (trajectories are static until parameters change, but repaint occasionally so UI feels alive)
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Gravity-Assist: Basic deflection visualization");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            TESTDELETEAFTER panel = new TESTDELETEAFTER();
            f.setContentPane(panel);
            f.pack();
            f.setResizable(false);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
            panel.requestFocusInWindow();
        });
    }
}
