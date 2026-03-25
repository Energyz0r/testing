package javapixel.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * ColorPanel — displays recent color swatches that the user can click
 * to change the current drawing color.
 */
public class ColorPanel extends JPanel {

    // --- Listener interface for color selection ---
    public interface ColorSelectListener {
        void onColorSelected(Color color);
    }

    private final List<Color> recentColors = new ArrayList<>();
    private ColorSelectListener listener;
    private final JPanel colorContainer;

    public ColorPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Recent Colors"));

        colorContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4));
        add(colorContainer, BorderLayout.CENTER);
    }

    public void setColorSelectListener(ColorSelectListener l) {
        this.listener = l;
    }

    public void addRecentColor(Color c) {
        // Avoid duplicates
        for (Color existing : recentColors) {
            if (existing.getRGB() == c.getRGB()) {
                return;
            }
        }

        recentColors.add(0, c);
        if (recentColors.size() > 12) {
            recentColors.remove(12);
        }
        refreshRecentColors();
    }

    public List<Color> getRecentColors() {
        return new ArrayList<>(recentColors);
    }

    private void refreshRecentColors() {
        colorContainer.removeAll();

        for (Color c : recentColors) {
            JPanel swatch = new JPanel();
            swatch.setPreferredSize(new Dimension(32, 32));
            swatch.setBackground(c);
            swatch.setOpaque(true);
            swatch.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            swatch.setToolTipText(String.format("#%02X%02X%02X", c.getRed(), c.getGreen(), c.getBlue()));
            swatch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            swatch.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (listener != null) {
                        listener.onColorSelected(c);
                    }
                    // visually highlight selected color
                    for (Component comp : colorContainer.getComponents()) {
                        if (comp instanceof JPanel) {
                            ((JPanel) comp).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                        }
                    }
                    swatch.setBorder(BorderFactory.createLineBorder(Color.GREEN.darker(), 2));
                }
            });

            colorContainer.add(swatch);
        }

        colorContainer.revalidate();
        colorContainer.repaint();
    }
}
