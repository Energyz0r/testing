package javapixel.ui;

import javapixel.core.GridManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * TilingPanel shows 4 small previews in a single square and allows rotating each by click.
 * It also exposes rotation values to the main window.
 */
public class TilingPanel extends JPanel {
    private int[][] sourceGrid;
    private int sourceSize;
    private final PatternPanel[] panels = new PatternPanel[4];
    private boolean usePreviewRotations = false;
    private boolean invertBottom = false;

    public TilingPanel(int[][] initialGrid, int size) {
        setLayout(null);
        setPreferredSize(new Dimension(360, 360));
        setBackground(Color.LIGHT_GRAY);
        for (int i = 0; i < 4; i++) {
            panels[i] = new PatternPanel();
            add(panels[i]);
        }
        updateSource(initialGrid, size);
    }

    public void updateSource(int[][] g, int size) {
        this.sourceGrid = g;
        this.sourceSize = size;
        for (PatternPanel p : panels) p.regenerate();
        repaint();
    }

    public void refreshFromEditor() {
        updateSource(sourceGrid, sourceSize);
    }

    @Override
    public void doLayout() {
        int w = getWidth(), h = getHeight();
        int s = Math.min(w, h);
        int x0 = (w - s) / 2, y0 = (h - s) / 2;
        int q = s / 2, e = s % 2;
        panels[0].setBounds(x0, y0, q, q);
        panels[1].setBounds(x0 + q, y0, q + e, q);
        panels[2].setBounds(x0, y0 + q, q, q + e);
        panels[3].setBounds(x0 + q, y0 + q, q + e, q + e);
    }

    public int[] getRotations() {
        int[] r = new int[4];
        for (int i = 0; i < 4; i++) r[i] = panels[i].rotation;
        return r;
    }

    public boolean isUsePreviewRotations() { return usePreviewRotations; }
    public void setUsePreviewRotations(boolean v) { usePreviewRotations = v; }

    public boolean isInvertBottom() { return invertBottom; }
    public void setInvertBottom(boolean v) { invertBottom = v; }

    private class PatternPanel extends JPanel {
        int rotation = 0;
        BufferedImage preview;

        PatternPanel() {
            setBackground(Color.WHITE);
            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    rotation = (rotation + 90) % 360;
                    regenerate();
                    repaint();
                }
            });
        }

        void regenerate() {
            if (sourceGrid == null || sourceSize <= 0) { preview = null; return; }
            preview = new BufferedImage(sourceSize, sourceSize, BufferedImage.TYPE_INT_ARGB);
            for (int y = 0; y < sourceSize; y++)
                for (int x = 0; x < sourceSize; x++)
                    preview.setRGB(x, y, GridManager.rotatedPixel(sourceGrid, x, y, rotation));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (preview == null) return;
            int s = Math.min(getWidth(), getHeight());
            int ox = (getWidth() - s) / 2, oy = (getHeight() - s) / 2;
            g.drawImage(preview, ox, oy, s, s, null);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(ox, oy, s - 1, s - 1);
        }
    }
}
