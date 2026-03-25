package javapixel.ui;

import javapixel.config.Settings;
import javapixel.core.GridManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * MainWindow wires everything: top toolbar, DrawPanel, TilingPanel, ColorPanel.
 * Keep logic here minimal: respond to buttons and forward to GridManager.
 */
public class MainWindow extends JFrame {
    private final GridManager gridManager;
    private final Settings settings;

    private DrawPanel drawPanel;
    private TilingPanel tilingPanel;
    private ColorPanel colorPanel;

    public MainWindow(GridManager gm, Settings s) {
        super("JavaPixels - Modular");
        this.gridManager = gm;
        this.settings = s;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Initialize main panels FIRST ---
        drawPanel = new DrawPanel(gridManager);
        tilingPanel = new TilingPanel(gridManager.getGrid(), gridManager.getSize());
        colorPanel = new ColorPanel();

        // load recent colors from settings
        if (settings.recentColors != null) {
            for (String hex : settings.recentColors) {
                try {
                    colorPanel.addRecentColor(Color.decode(hex));
                } catch (Exception ignored) {
                }
            }
        }

        // Let color panel notify DrawPanel when a swatch is clicked
        colorPanel.setColorSelectListener(color -> {
            drawPanel.setSelectedColor(color);
        });

        // --- Toolbar after panels exist ---
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);

        toolbar.add(new JLabel("Grid Size: "));
        JSpinner sizeSpinner = new JSpinner(new SpinnerNumberModel(gm.getSize(), 2, 4096, 1));
        sizeSpinner.addChangeListener(e -> {
            int newSize = (int) sizeSpinner.getValue();
            gm.setSize(newSize);
            drawPanel.rebuildImage();
            tilingPanel.updateSource(gm.getGrid(), gm.getSize());
        });
        toolbar.add(sizeSpinner);

        JCheckBox mirrorX = new JCheckBox("Mirror X", s.mirrorX);
        mirrorX.addActionListener(e -> gm.setMirrorX(mirrorX.isSelected()));
        toolbar.add(mirrorX);

        JCheckBox mirrorY = new JCheckBox("Mirror Y", s.mirrorY);
        mirrorY.addActionListener(e -> gm.setMirrorY(mirrorY.isSelected()));
        toolbar.add(mirrorY);

        JCheckBox gridLines = new JCheckBox("Grid Lines", true);
        toolbar.add(gridLines);

        JButton pick = new JButton("Pick Color");
        pick.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Choose Pixel Color", Color.RED);
            if (c != null) {
                colorPanel.addRecentColor(c);
                drawPanel.setSelectedColor(c);
            }
        });
        toolbar.add(pick);

        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(e -> {
            gm.resetGrid();
            drawPanel.rebuildImage();
            drawPanel.repaint();
            tilingPanel.updateSource(gm.getGrid(), gm.getSize());
        });
        toolbar.add(refresh);

        JButton extrapolate = new JButton("Extrapolate");
        extrapolate.addActionListener(e -> {
            // use preview rotations by default here
            gridManager.extrapolate(true, tilingPanel.getRotations(), tilingPanel.isInvertBottom());
            drawPanel.rebuildImage();
            drawPanel.repaint();
            tilingPanel.updateSource(gridManager.getGrid(), gridManager.getSize());
        });
        toolbar.add(extrapolate);

        // Use preview rotations toggle and invert bottom
        JCheckBox usePreview = new JCheckBox("Use Preview Rotations", settings.usePreviewRotations);
        usePreview.addActionListener(e -> tilingPanel.setUsePreviewRotations(usePreview.isSelected()));
        toolbar.add(usePreview);

        JCheckBox invertBottom = new JCheckBox("Invert Bottom", settings.invertBottom);
        invertBottom.addActionListener(e -> tilingPanel.setInvertBottom(invertBottom.isSelected()));
        toolbar.add(invertBottom);

        // Zoom controls (delegated to draw panel)
        toolbar.addSeparator();
        toolbar.add(new JLabel("Zoom: "));
        JSpinner zoomSpinner = drawPanel.getZoomSpinner();
        toolbar.add(zoomSpinner);

        // Export button (restored)
        JButton export = new JButton("Export");
        export.addActionListener(e -> {
            ExportDialog dlg = new ExportDialog(this, gridManager.getGrid());
            dlg.setLocationRelativeTo(this);
            dlg.setVisible(true);
        });
        toolbar.add(export);

        add(toolbar, BorderLayout.NORTH);

        // --- Center & Right Layout ---
        drawPanel.setPreferredSize(new Dimension(700, 700));
        add(new JScrollPane(drawPanel), BorderLayout.CENTER);

        JPanel right = new JPanel(new BorderLayout());
        right.setPreferredSize(new Dimension(420, 420));
        right.add(tilingPanel, BorderLayout.CENTER);
        right.add(colorPanel, BorderLayout.SOUTH);
        add(right, BorderLayout.EAST);

        // pass checkbox ref
        drawPanel.setGridLinesCheckbox(gridLines);

        // refresh tiling whenever user paints
        drawPanel.setOnDrawListener(() ->
                tilingPanel.updateSource(gridManager.getGrid(), gridManager.getSize())
        );

        pack();
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // helper used by JavaPixels main for saving settings
    public double getZoom() {
        return drawPanel.getZoomFactor();
    }

    public java.util.List<String> getRecentColorsAsHex() {
        java.util.List<String> out = new ArrayList<>();
        for (Color c : colorPanel.getRecentColors()) {
            out.add(String.format("#%02X%02X%02X", c.getRed(), c.getGreen(), c.getBlue()));
        }
        return out;
    }
}
