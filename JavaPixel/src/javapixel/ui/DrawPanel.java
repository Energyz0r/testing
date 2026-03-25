package javapixel.ui;

import javapixel.core.GridManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * DrawPanel: handles painting pixels, zooming, and user drawing events.
 * Exposes:
 *  - setOnDrawListener(Runnable) to notify when pixels change
 *  - setSelectedColor(Color) so ColorPanel can set the brush
 *  - getZoomSpinner() returns the same spinner instance used by toolbar
 */
public class DrawPanel extends JPanel {

    private final GridManager gridManager;
    private double zoomFactor = 1.0;
    private JCheckBox gridLinesCheck;
    private Color selectedColor = Color.BLACK;

    private Runnable onDrawListener;

    // keep a spinner instance so toolbar and panel share the same control
    private final JSpinner zoomSpinner;

    public DrawPanel(GridManager gm) {
        this.gridManager = gm;
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.WHITE);

        zoomSpinner = new JSpinner(new SpinnerNumberModel(100, 10, 2000, 10));
        zoomSpinner.setPreferredSize(new Dimension(70, zoomSpinner.getPreferredSize().height));
        zoomSpinner.addChangeListener(e -> {
            int val = (int) zoomSpinner.getValue();
            zoomFactor = Math.max(0.1, Math.min(20.0, val / 100.0));
            repaint();
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleDrawMouse(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleDrawMouse(e.getX(), e.getY());
            }
        });

        addMouseWheelListener(e -> {
            if (e.isControlDown()) {
                double notches = e.getPreciseWheelRotation();
                changeZoomBy(-0.05 * notches);
            }
        });
    }

    // Allows MainWindow to listen for drawing updates
    public void setOnDrawListener(Runnable r) {
        this.onDrawListener = r;
    }

    // Allows MainWindow to sync color with ColorPanel
    public void setSelectedColor(Color c) {
        if (c != null) this.selectedColor = c;
    }

    public void setGridLinesCheckbox(JCheckBox cb) {
        this.gridLinesCheck = cb;
    }

    public double getZoomFactor() {
        return zoomFactor;
    }

    public JSpinner getZoomSpinner() {
        return zoomSpinner;
    }

    private void changeZoomBy(double delta) {
        zoomFactor = Math.max(0.1, Math.min(20.0, zoomFactor + delta));
        int percent = (int) Math.round(zoomFactor * 100);
        zoomSpinner.setValue(percent);
        repaint();
    }

    private void handleDrawMouse(int mx, int my) {
        int w = getWidth();
        int h = getHeight();
        int size = gridManager.getSize();
        if (size <= 0) return;

        int baseCell = Math.max(1, Math.min(w, h) / size);
        int cellSize = Math.max(1, (int) Math.round(baseCell * zoomFactor));
        int drawW = cellSize * size;
        int offsetX = (w - drawW) / 2;
        int offsetY = (h - drawW) / 2;
        int gx = (mx - offsetX) / cellSize;
        int gy = (my - offsetY) / cellSize;

        if (gx < 0 || gy < 0 || gx >= size || gy >= size) return;

        // apply selected color using GridManager (which handles mirroring)
        gridManager.setPixel(gx, gy, selectedColor);

        // repaint and notify listeners
        repaint();
        if (onDrawListener != null) onDrawListener.run();
    }

    public void rebuildImage() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0.create();
        try {
            int[][] grid = gridManager.getGrid();
            int size = gridManager.getSize();
            if (grid == null || size <= 0) return;

            int w = getWidth();
            int h = getHeight();
            int baseCell = Math.max(1, Math.min(w, h) / size);
            int cellSize = Math.max(1, (int) Math.round(baseCell * zoomFactor));
            int drawSize = cellSize * size;
            int ox = (w - drawSize) / 2;
            int oy = (h - drawSize) / 2;

            // draw pixels
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    g.setColor(new Color(grid[y][x], true));
                    g.fillRect(ox + x * cellSize, oy + y * cellSize, cellSize, cellSize);
                }
            }

            // optional grid lines
            if (gridLinesCheck != null && gridLinesCheck.isSelected() && cellSize >= 3) {
                g.setColor(new Color(0, 0, 0, 80));
                for (int i = 0; i <= size; i++) {
                    int yy = oy + i * cellSize;
                    g.drawLine(ox, yy, ox + drawSize, yy);
                    int xx = ox + i * cellSize;
                    g.drawLine(xx, oy, xx, oy + drawSize);
                }
            }

            // border
            g.setColor(Color.DARK_GRAY);
            g.drawRect(ox, oy, drawSize - 1, drawSize - 1);
        } finally {
            g.dispose();
        }
    }
}
