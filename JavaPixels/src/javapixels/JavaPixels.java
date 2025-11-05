package javapixels;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * JavaPixels v3.0 - Zoom (percentage) with buttons + spinner - Export dialog (PNG or SVG) with resolution choices (Original, 2K, 4K) and file chooser - Fixed recent color swatches (filled) - Performance: int[][] + BufferedImage - TilingPanel: always a single square, 4 quadrants aligned exactly
 */
public class JavaPixels extends JFrame {

    private int gridSize = 8;
    private int[][] grid; // grid[y][x] = ARGB
    private boolean mirrorX = false;
    private boolean mirrorY = false;

    private Color selectedColor = Color.RED;
    private final ArrayList<Color> recentColors = new ArrayList<>();
    private JPanel recentColorsPanel;

    private DrawPanel editorPanel;
    private TilingPanel tilingPanel;

    private BufferedImage editorImage;
    private boolean drawGridLines = true;

    // Zoom in editor (1.0 = 100%)
    private double zoomFactor = 1.0;
    private JLabel zoomLabel;
    private JSpinner zoomSpinner;

    public JavaPixels() {
        super("JavaPixels v3.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top controls
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 6));

        top.add(new JLabel("Grid Size:"));
        JSpinner sizeSpinner = new JSpinner(new SpinnerNumberModel(gridSize, 2, 1024, 1));
        sizeSpinner.addChangeListener(e -> {
            gridSize = (int) sizeSpinner.getValue();
            resetGrid();
            editorPanel.rebuildImage();
            editorPanel.repaint();
            tilingPanel.updateSource(grid, gridSize);
        });
        top.add(sizeSpinner);

        JCheckBox mx = new JCheckBox("Mirror X");
        mx.addActionListener(e -> mirrorX = mx.isSelected());
        top.add(mx);

        JCheckBox my = new JCheckBox("Mirror Y");
        my.addActionListener(e -> mirrorY = my.isSelected());
        top.add(my);

        JCheckBox gl = new JCheckBox("Grid Lines", true);
        gl.addActionListener(e -> {
            drawGridLines = gl.isSelected();
            editorPanel.repaint();
        });
        top.add(gl);

        JButton pick = new JButton("Pick Color");
        pick.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Choose Pixel Color", selectedColor);
            if (c != null) {
                selectedColor = c;
                addRecentColor(c);
            }
        });
        top.add(pick);

        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(e -> {
            resetGrid();
            editorPanel.rebuildImage();
            editorPanel.repaint();
            tilingPanel.updateSource(grid, gridSize);
        });
        top.add(refresh);

        JButton extrapolate = new JButton("Extrapolate");
        extrapolate.addActionListener(e -> {
            extrapolateFromTiling();
        });
        top.add(extrapolate);

        // Zoom controls
        JButton zoomOutBtn = new JButton("-");
        zoomOutBtn.setToolTipText("Zoom out (-10%)");
        zoomOutBtn.addActionListener(e -> changeZoomBy(-0.1));

        JButton zoomInBtn = new JButton("+");
        zoomInBtn.setToolTipText("Zoom in (+10%)");
        zoomInBtn.addActionListener(e -> changeZoomBy(0.1));

        zoomSpinner = new JSpinner(new SpinnerNumberModel(100, 10, 2000, 10));
        zoomSpinner.setPreferredSize(new Dimension(70, zoomSpinner.getPreferredSize().height));
        zoomSpinner.addChangeListener(e -> {
            int val = (int) zoomSpinner.getValue();
            zoomFactor = val / 100.0;
            zoomLabel.setText(val + "%");
            editorPanel.repaint();
        });

        zoomLabel = new JLabel("100%");
        top.add(new JLabel("Zoom:"));
        top.add(zoomOutBtn);
        top.add(zoomInBtn);
        top.add(zoomSpinner);
        top.add(zoomLabel);

        // Export button
        JButton exportBtn = new JButton("Export");
        exportBtn.addActionListener(e -> {
            ExportDialog dlg = new ExportDialog(this);
            dlg.setLocationRelativeTo(this);
            dlg.setVisible(true);
        });
        top.add(exportBtn);

        add(top, BorderLayout.NORTH);

        // Recent colors area (bottom)
        recentColorsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        add(recentColorsPanel, BorderLayout.SOUTH);

        // initialize grid and images
        resetGrid();

        // Editor panel center
        editorPanel = new DrawPanel();
        JScrollPane sc = new JScrollPane(editorPanel);
        add(sc, BorderLayout.CENTER);

        // Tiling panel right
        tilingPanel = new TilingPanel(grid, gridSize);
        JPanel eastWrap = new JPanel(new BorderLayout());
        eastWrap.setPreferredSize(new Dimension(420, 420));
        eastWrap.add(tilingPanel, BorderLayout.CENTER);
        add(eastWrap, BorderLayout.EAST);

        setSize(1200, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // change zoom by delta (e.g., 0.1 adds 10%)
    private void changeZoomBy(double delta) {
        zoomFactor = Math.max(0.1, Math.min(20.0, zoomFactor + delta));
        int percent = (int) Math.round(zoomFactor * 100);
        zoomSpinner.setValue(percent);
        zoomLabel.setText(percent + "%");
        editorPanel.repaint();
    }

    private void resetGrid() {
        grid = new int[gridSize][gridSize];
        int white = Color.WHITE.getRGB();
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                grid[y][x] = white;
            }
        }
        editorImage = new BufferedImage(Math.max(1, gridSize), Math.max(1, gridSize), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                editorImage.setRGB(x, y, grid[y][x]);
            }
        }
    }

    private void applyColorAt(int gx, int gy) {
        if (gx < 0 || gy < 0 || gx >= gridSize || gy >= gridSize) {
            return;
        }
        int rgb = selectedColor.getRGB();
        setPixel(gx, gy, rgb);
        if (mirrorX) {
            setPixel(gridSize - 1 - gx, gy, rgb);
        }
        if (mirrorY) {
            setPixel(gx, gridSize - 1 - gy, rgb);
        }
        if (mirrorX && mirrorY) {
            setPixel(gridSize - 1 - gx, gridSize - 1 - gy, rgb);
        }
    }

    private void setPixel(int gx, int gy, int rgb) {
        grid[gy][gx] = rgb;
        editorImage.setRGB(gx, gy, rgb);
    }

    private void extrapolateFromTiling() {
        int old = gridSize;
        int newSize = old * 2;

        if (newSize > 8192) {
            JOptionPane.showMessageDialog(this, "Extrapolation too large. Aborted.");
            return;
        }

        int[][] newGrid = new int[newSize][newSize];

        // Quadrant mapping:
        // [0,0] original
        // [1,0] mirror X
        // [0,1] mirror Y
        // [1,1] mirror both X and Y
        for (int qy = 0; qy < 2; qy++) {
            for (int qx = 0; qx < 2; qx++) {
                boolean flipX = (qx == 1);
                boolean flipY = (qy == 1);

                for (int y = 0; y < old; y++) {
                    for (int x = 0; x < old; x++) {
                        int sx = flipX ? (old - 1 - x) : x;
                        int sy = flipY ? (old - 1 - y) : y;
                        newGrid[qy * old + y][qx * old + x] = grid[sy][sx];
                    }
                }
            }
        }

        gridSize = newSize;
        grid = newGrid;

        editorImage = new BufferedImage(newSize, newSize, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < newSize; y++) {
            for (int x = 0; x < newSize; x++) {
                editorImage.setRGB(x, y, grid[y][x]);
            }
        }

        tilingPanel.updateSource(grid, gridSize);
        editorPanel.revalidate();
        editorPanel.repaint();
    }

    private int rotatedPixel(int[][] src, int x, int y, int rot) {
        int n = src.length;
        return switch (rot) {
            case 90 ->
                src[n - 1 - x][y];
            case 180 ->
                src[n - 1 - y][n - 1 - x];
            case 270 ->
                src[x][n - 1 - y];
            default ->
                src[y][x];
        };
    }

    private void addRecentColor(Color c) {
        for (Color rc : recentColors) {
            if (rc.getRGB() == c.getRGB()) {
                return;
            }
        }
        recentColors.add(0, c);
        if (recentColors.size() > 12) {
            recentColors.remove(12);
        }
        refreshRecentColors();
    }

    private void refreshRecentColors() {
        recentColorsPanel.removeAll();
        for (Color c : recentColors) {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(50, 50));
            b.setContentAreaFilled(false);
            b.setFocusPainted(false);
            b.setBorderPainted(true);
            b.setOpaque(true);
            b.setBackground(c);
            b.addActionListener(e -> selectedColor = c);
            recentColorsPanel.add(b);
        }
        recentColorsPanel.revalidate();
        recentColorsPanel.repaint();
    }

    // ---------- Editor Panel ----------
    private class DrawPanel extends JPanel {

        public DrawPanel() {
            setPreferredSize(new Dimension(800, 800));
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
            // mouse wheel zoom while CTRL pressed
            addMouseWheelListener(e -> {
                if (e.isControlDown()) {
                    double notches = e.getPreciseWheelRotation();
                    changeZoomBy(-0.05 * notches);
                } else {
                    // pass
                }
            });
        }

        private void handleDrawMouse(int mx, int my) {
            int w = getWidth();
            int h = getHeight();
            // compute cellSize based on zoomFactor and available area
            int baseCell = Math.max(1, Math.min(w, h) / gridSize);
            int cellSize = Math.max(1, (int) Math.round(baseCell * zoomFactor));
            int drawW = cellSize * gridSize;
            int offsetX = (w - drawW) / 2;
            int offsetY = (h - drawW) / 2;
            int gx = (mx - offsetX) / cellSize;
            int gy = (my - offsetY) / cellSize;
            applyColorAt(gx, gy);
            repaint();
            tilingPanel.refreshFromEditor();
        }

        public void rebuildImage() {
            editorImage = new BufferedImage(Math.max(1, gridSize), Math.max(1, gridSize), BufferedImage.TYPE_INT_ARGB);
            for (int y = 0; y < gridSize; y++) {
                for (int x = 0; x < gridSize; x++) {
                    editorImage.setRGB(x, y, grid[y][x]);
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g0) {
            super.paintComponent(g0);
            if (editorImage == null) {
                return;
            }
            Graphics2D g = (Graphics2D) g0.create();
            try {
                int w = getWidth();
                int h = getHeight();
                // base cell size fit to area
                int baseCell = Math.max(1, Math.min(w, h) / gridSize);
                int cellSize = Math.max(1, (int) Math.round(baseCell * zoomFactor));
                int drawSize = cellSize * gridSize;
                int ox = (w - drawSize) / 2;
                int oy = (h - drawSize) / 2;

                // draw scaled image using nearest-neighbor effect by drawing rectangles per pixel
                // faster approach: drawImage with nearest neighbor via rendering hints (but ensure no blur)
                Image scaled = editorImage.getScaledInstance(drawSize, drawSize, Image.SCALE_REPLICATE);
                g.drawImage(scaled, ox, oy, drawSize, drawSize, null);

                // grid lines
                if (drawGridLines && cellSize >= 3) {
                    g.setColor(new Color(0, 0, 0, 80));
                    for (int i = 0; i <= gridSize; i++) {
                        int x = ox + i * cellSize;
                        g.drawLine(x, oy, x, oy + drawSize);
                        int y = oy + i * cellSize;
                        g.drawLine(ox, y, ox + drawSize, y);
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

    // ---------- Tiling Panel ----------
    private class TilingPanel extends JPanel {

        private int[][] sourceGrid;
        private int sourceSize;
        private final PatternPanel[] panels = new PatternPanel[4];

        public TilingPanel(int[][] initialGrid, int size) {
            setLayout(null); // we'll position children manually to form one square subdivided into 4 quadrants
            setBackground(Color.LIGHT_GRAY);
            setPreferredSize(new Dimension(380, 380));
            for (int i = 0; i < 4; i++) {
                panels[i] = new PatternPanel();
                add(panels[i]);
            }
            updateSource(initialGrid, size);
        }

        public int getRotation(int index) {
            if (index < 0 || index >= panels.length) {
                return 0;
            }
            return panels[index].rotation;
        }

        public void updateSource(int[][] newSource, int newSize) {
            sourceGrid = newSource;
            sourceSize = newSize;
            for (PatternPanel p : panels) {
                p.regenerate();
            }
            repaint();
        }

        public void refreshFromEditor() {
            for (PatternPanel p : panels) {
                p.regenerate();
            }
            repaint();
        }

        @Override
        public void doLayout() {
            int w = getWidth();
            int h = getHeight();
            int size = Math.min(w, h);
            int x0 = (w - size) / 2;
            int y0 = (h - size) / 2;

            int q = size / 2;
            int extra = size % 2;

            // top-left
            panels[0].setBounds(x0, y0, q, q);
            // top-right
            panels[1].setBounds(x0 + q, y0, q + extra, q);
            // bottom-left
            panels[2].setBounds(x0, y0 + q, q, q + extra);
            // bottom-right
            panels[3].setBounds(x0 + q, y0 + q, q + extra, q + extra);
        }

        private class PatternPanel extends JPanel {

            int rotation = 0; // 0,90,180,270
            BufferedImage preview;

            PatternPanel() {
                setBackground(Color.WHITE);
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        rotation = (rotation + 90) % 360;
                        regenerate();
                        repaint();
                    }
                });
                regenerate();
            }

            void regenerate() {
                if (sourceGrid == null || sourceSize <= 0) {
                    preview = null;
                    return;
                }
                preview = new BufferedImage(sourceSize, sourceSize, BufferedImage.TYPE_INT_ARGB);
                for (int y = 0; y < sourceSize; y++) {
                    for (int x = 0; x < sourceSize; x++) {
                        preview.setRGB(x, y, rotatedPixel(sourceGrid, x, y, rotation));
                    }
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (preview == null) {
                    return;
                }
                int s = Math.min(getWidth(), getHeight());
                int ox = (getWidth() - s) / 2;
                int oy = (getHeight() - s) / 2;
                g.drawImage(preview, ox, oy, s, s, null);
                g.setColor(Color.DARK_GRAY);
                g.drawRect(ox, oy, s - 1, s - 1);
            }
        }
    }

    // ---------- Export Dialog ----------
    private class ExportDialog extends JDialog {

        private final JComboBox<String> formatBox;
        private final JComboBox<String> resBox;
        private final JTextField pathField;

        ExportDialog(JFrame owner) {
            super(owner, "Export Image", true);
            setLayout(new BorderLayout(8, 8));

            JPanel center = new JPanel(new GridLayout(0, 2, 6, 6));
            center.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

            center.add(new JLabel("Format:"));
            formatBox = new JComboBox<>(new String[]{"PNG", "SVG"});
            center.add(formatBox);

            center.add(new JLabel("Resolution:"));
            resBox = new JComboBox<>(new String[]{"Original (grid size)", "2K (2048)", "4K (4096)"});
            center.add(resBox);

            center.add(new JLabel("Save to:"));
            JPanel pathRow = new JPanel(new BorderLayout(6, 6));
            pathField = new JTextField();
            JButton browse = new JButton("Browse...");
            browse.addActionListener(e -> chooseFileAction());
            pathRow.add(pathField, BorderLayout.CENTER);
            pathRow.add(browse, BorderLayout.EAST);
            center.add(pathRow);

            add(center, BorderLayout.CENTER);

            JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(e -> setVisible(false));
            JButton export = new JButton("Export");
            export.addActionListener(e -> doExport());
            bottom.add(cancel);
            bottom.add(export);
            add(bottom, BorderLayout.SOUTH);

            setSize(560, 180);
            setResizable(false);
        }

        private void chooseFileAction() {
            String fmt = (String) formatBox.getSelectedItem();
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if ("PNG".equals(fmt)) {
                fc.setFileFilter(new FileNameExtensionFilter("PNG image", "png"));
            } else {
                fc.setFileFilter(new FileNameExtensionFilter("SVG vector", "svg"));
            }
            int ret = fc.showSaveDialog(this);
            if (ret == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                String path = f.getAbsolutePath();
                // append extension if missing
                if ("PNG".equals(fmt) && !path.toLowerCase().endsWith(".png")) {
                    path += ".png";
                }
                if ("SVG".equals(fmt) && !path.toLowerCase().endsWith(".svg")) {
                    path += ".svg";
                }
                pathField.setText(path);
            }
        }

        private void doExport() {
            String fmt = (String) formatBox.getSelectedItem();
            String resChoice = (String) resBox.getSelectedItem();
            String path = pathField.getText().trim();
            if (path.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please choose a path to save the exported file.");
                return;
            }
            int sizePx;
            if ("2K (2048)".equals(resChoice)) {
                sizePx = 2048;
            } else if ("4K (4096)".equals(resChoice)) {
                sizePx = 4096;
            } else {
                sizePx = -1; // original
            }
            try {
                if ("PNG".equals(fmt)) {
                    exportPNG(new File(path), sizePx);
                } else {
                    exportSVG(new File(path), sizePx);
                }
                JOptionPane.showMessageDialog(this, "Export successful:\n" + path);
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Export failed:\n" + ex.getMessage());
            }
        }
    }

    // ---------- Export helpers ----------
    private void exportPNG(File outFile, int sizePx) throws IOException {
        // If sizePx == -1, export at resolution = gridSize pixels (1 logical pixel per pixel)
        int outSize = (sizePx <= 0) ? gridSize : sizePx;
        BufferedImage out = new BufferedImage(outSize, outSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = out.createGraphics();
        try {
            // compute pixel box size
            double box = outSize / (double) gridSize;
            // fill background white first
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, outSize, outSize);

            // draw each pixel as a rectangle (no anti-alias)
            for (int y = 0; y < gridSize; y++) {
                for (int x = 0; x < gridSize; x++) {
                    int argb = grid[y][x];
                    // skip fully white transparent? we will draw all
                    g.setColor(new Color(argb, true));
                    int rx = (int) Math.round(x * box);
                    int ry = (int) Math.round(y * box);
                    int rw = (int) Math.ceil((x + 1) * box) - rx;
                    int rh = (int) Math.ceil((y + 1) * box) - ry;
                    g.fillRect(rx, ry, Math.max(1, rw), Math.max(1, rh));
                }
            }
        } finally {
            g.dispose();
        }
        ImageIO.write(out, "PNG", outFile);
    }

    private void exportSVG(File outFile, int sizePx) throws IOException {
        // SVG vector: create a rect per pixel inside viewBox 0 0 gridSize gridSize
        // but set width/height attributes to sizePx if provided (so programs open at that size)
        int viewW = gridSize;
        int viewH = gridSize;
        int outputW = (sizePx <= 0) ? gridSize : sizePx;
        int outputH = outputW; // square

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append(String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%d\" height=\"%d\" viewBox=\"0 0 %d %d\" shape-rendering=\"crispEdges\">\n",
                outputW, outputH, viewW, viewH));
        // white background
        sb.append(String.format("<rect x=\"0\" y=\"0\" width=\"%d\" height=\"%d\" fill=\"#ffffff\" />\n", viewW, viewH));

        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                int argb = grid[y][x];
                Color c = new Color(argb, true);
                // skip fully white opaque to reduce file size? we will include all, but skip transparent pixels
                if (c.getAlpha() == 0) {
                    continue;
                }
                // convert to hex (without alpha if alpha==255)
                String hex;
                if (c.getAlpha() == 255) {
                    hex = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
                    sb.append(String.format("<rect x=\"%d\" y=\"%d\" width=\"1\" height=\"1\" fill=\"%s\" />\n", x, y, hex));
                } else {
                    // include rgba with opacity attribute
                    hex = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
                    double op = c.getAlpha() / 255.0;
                    sb.append(String.format("<rect x=\"%d\" y=\"%d\" width=\"1\" height=\"1\" fill=\"%s\" opacity=\"%s\" />\n", x, y, hex, trim(op)));
                }
            }
        }
        sb.append("</svg>\n");

        try (Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8))) {
            w.write(sb.toString());
        }
    }

    // helper to trim double to 3 decimal places
    private static String trim(double d) {
        return String.format("%.3f", d);
    }

    // ---------- main ----------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            new JavaPixels();
        });
    }
}
