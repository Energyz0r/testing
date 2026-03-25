package javapixel.ui;

import javapixel.core.ExportManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Simple modal export dialog that calls ExportManager with chosen format and resolution.
 */
public class ExportDialog extends JDialog {
    private final JComboBox<String> formatBox;
    private final JComboBox<String> resBox;
    private final JTextField pathField;
    private final int[][] grid;

    public ExportDialog(Window owner, int[][] grid) {
        super(owner, "Export Image", ModalityType.APPLICATION_MODAL);
        this.grid = grid;
        setLayout(new BorderLayout(6,6));
        JPanel center = new JPanel(new GridLayout(0,2,6,6));
        center.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        center.add(new JLabel("Format:"));
        formatBox = new JComboBox<>(new String[]{"PNG","SVG"});
        center.add(formatBox);

        center.add(new JLabel("Resolution:"));
        resBox = new JComboBox<>(new String[]{"Original (grid size)", "2K (2048)", "4K (4096)"});
        center.add(resBox);

        center.add(new JLabel("Save to:"));
        JPanel pathRow = new JPanel(new BorderLayout(6,6));
        pathField = new JTextField();
        JButton browse = new JButton("Browse...");
        browse.addActionListener(e -> chooseFile());
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

        setSize(520,170);
        setResizable(false);
    }

    private void chooseFile() {
        String fmt = (String) formatBox.getSelectedItem();
        JFileChooser fc = new JFileChooser();
        if ("PNG".equals(fmt)) fc.setFileFilter(new FileNameExtensionFilter("PNG image","png"));
        else fc.setFileFilter(new FileNameExtensionFilter("SVG vector","svg"));
        int r = fc.showSaveDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            String path = f.getAbsolutePath();
            if ("PNG".equals(fmt) && !path.toLowerCase().endsWith(".png")) path += ".png";
            if ("SVG".equals(fmt) && !path.toLowerCase().endsWith(".svg")) path += ".svg";
            pathField.setText(path);
        }
    }

    private void doExport() {
        String fmt = (String) formatBox.getSelectedItem();
        String res = (String) resBox.getSelectedItem();
        String path = pathField.getText().trim();
        if (path.isEmpty()) { JOptionPane.showMessageDialog(this, "Choose a file path."); return; }
        int sizePx = "2K (2048)".equals(res) ? 2048 : "4K (4096)".equals(res) ? 4096 : -1;
        try {
            if ("PNG".equals(fmt)) ExportManager.exportPNG(grid, new File(path), sizePx);
            else ExportManager.exportSVG(grid, new File(path), sizePx);
            JOptionPane.showMessageDialog(this, "Exported to:\n" + path);
            setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Export failed: " + ex.getMessage());
        }
    }
}
