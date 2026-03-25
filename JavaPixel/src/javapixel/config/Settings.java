package javapixel.config;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple JSON-like settings loader/saver with no external libs.
 * Saved fields:
 *  - gridSize (int)
 *  - zoom (double)
 *  - mirrorX, mirrorY (boolean)
 *  - usePreviewRotations, invertBottom (boolean)
 *  - recentColors (array of "#RRGGBB" strings)
 *
 * Not a full JSON parser — this is intentionally minimal and readable.
 */
public class Settings {
    public int gridSize = 8;
    public double zoom = 1.0;
    public boolean mirrorX = false;
    public boolean mirrorY = false;
    public boolean usePreviewRotations = false;
    public boolean invertBottom = false;
    public List<String> recentColors = new ArrayList<>();

    public static Settings load(String path) {
        Settings s = new Settings();
        File f = new File(path);
        if (!f.exists()) return s;
        try {
            String txt = new String(java.nio.file.Files.readAllBytes(f.toPath()), java.nio.charset.StandardCharsets.UTF_8);
            s.gridSize = extractInt(txt, "gridSize", s.gridSize);
            s.zoom = extractDouble(txt, "zoom", s.zoom);
            s.mirrorX = extractBool(txt, "mirrorX", s.mirrorX);
            s.mirrorY = extractBool(txt, "mirrorY", s.mirrorY);
            s.usePreviewRotations = extractBool(txt, "usePreviewRotations", s.usePreviewRotations);
            s.invertBottom = extractBool(txt, "invertBottom", s.invertBottom);
            s.recentColors = extractArray(txt, "recentColors");
        } catch (Exception ex) {
            // ignore, use defaults
        }
        return s;
    }

    public void save(String path) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"gridSize\": ").append(gridSize).append(",\n");
        sb.append("  \"zoom\": ").append(zoom).append(",\n");
        sb.append("  \"mirrorX\": ").append(mirrorX).append(",\n");
        sb.append("  \"mirrorY\": ").append(mirrorY).append(",\n");
        sb.append("  \"usePreviewRotations\": ").append(usePreviewRotations).append(",\n");
        sb.append("  \"invertBottom\": ").append(invertBottom).append(",\n");
        sb.append("  \"recentColors\": [");
        for (int i = 0; i < recentColors.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append("\"").append(recentColors.get(i)).append("\"");
        }
        sb.append("]\n}\n");
        try {
            java.nio.file.Files.write(new File(path).toPath(), sb.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8));
        } catch (IOException ignored) {}
    }

    // minimal extract helpers
    private static int extractInt(String txt, String key, int def) {
        String pattern = "\"" + key + "\"\\s*:\\s*([0-9]+)";
        java.util.regex.Matcher m = java.util.regex.Pattern.compile(pattern).matcher(txt);
        if (m.find()) try { return Integer.parseInt(m.group(1)); } catch (Exception ignored) {}
        return def;
    }
    private static double extractDouble(String txt, String key, double def) {
        String pattern = "\"" + key + "\"\\s*:\\s*([0-9]+(?:\\.[0-9]+)?)";
        java.util.regex.Matcher m = java.util.regex.Pattern.compile(pattern).matcher(txt);
        if (m.find()) try { return Double.parseDouble(m.group(1)); } catch (Exception ignored) {}
        return def;
    }
    private static boolean extractBool(String txt, String key, boolean def) {
        String pattern = "\"" + key + "\"\\s*:\\s*(true|false)";
        java.util.regex.Matcher m = java.util.regex.Pattern.compile(pattern).matcher(txt);
        if (m.find()) return Boolean.parseBoolean(m.group(1));
        return def;
    }
    private static List<String> extractArray(String txt, String key) {
        List<String> out = new ArrayList<>();
        String pattern = "\"" + key + "\"\\s*:\\s*\\[(.*?)\\]";
        java.util.regex.Matcher m = java.util.regex.Pattern.compile(pattern, java.util.regex.Pattern.DOTALL).matcher(txt);
        if (m.find()) {
            String inside = m.group(1);
            java.util.regex.Matcher q = java.util.regex.Pattern.compile("\"(#?[0-9A-Fa-f]{6})\"").matcher(inside);
            while (q.find()) out.add(q.group(1));
        }
        return out;
    }
}
