package javapixel;

import javapixel.config.Settings;
import javapixel.core.GridManager;
import javapixel.ui.MainWindow;

import javax.swing.*;

public class JavaPixel {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
            catch (Exception ignored) {}

            // Load settings (if file missing, defaults used)
            Settings settings = Settings.load("javapixels_settings.json");

            // Create the core grid manager with saved grid size
            GridManager gm = new GridManager(settings.gridSize);

            // Create main UI window and pass core + settings
            MainWindow mw = new MainWindow(gm, settings);

            // On close, save settings
            mw.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    // collect state from UI / GridManager and save
                    settings.gridSize = gm.getSize();
                    settings.zoom = mw.getZoom();
                    settings.mirrorX = gm.isMirrorX();
                    settings.mirrorY = gm.isMirrorY();
                    settings.recentColors = mw.getRecentColorsAsHex();
                    settings.save("javapixels_settings.json");
                }
            });

            mw.setVisible(true);
        });
    }
}
