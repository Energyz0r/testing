package javapixel.core;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * ExportManager: static helpers to write PNG and SVG from a grid (int[][] ARGB)
 */
public class ExportManager {

    public static void exportPNG(int[][] grid, File outFile, int sizePx) throws IOException {
        int gridSize = grid.length;
        int outSize = (sizePx <= 0) ? gridSize : sizePx;
        BufferedImage out = new BufferedImage(outSize, outSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = out.createGraphics();
        try {
            double box = outSize / (double) gridSize;
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, outSize, outSize);
            for (int y = 0; y < gridSize; y++) {
                for (int x = 0; x < gridSize; x++) {
                    g.setColor(new Color(grid[y][x], true));
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

    public static void exportSVG(int[][] grid, File outFile, int sizePx) throws IOException {
        int gridSize = grid.length;
        int outW = (sizePx <= 0) ? gridSize : sizePx;
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append(String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%d\" height=\"%d\" viewBox=\"0 0 %d %d\" shape-rendering=\"crispEdges\">\n", outW, outW, gridSize, gridSize));
        sb.append(String.format("<rect x=\"0\" y=\"0\" width=\"%d\" height=\"%d\" fill=\"#ffffff\" />\n", gridSize, gridSize));
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                int argb = grid[y][x];
                Color c = new Color(argb, true);
                if (c.getAlpha() == 0) continue;
                String hex = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
                if (c.getAlpha() == 255) {
                    sb.append(String.format("<rect x=\"%d\" y=\"%d\" width=\"1\" height=\"1\" fill=\"%s\" />\n", x, y, hex));
                } else {
                    sb.append(String.format("<rect x=\"%d\" y=\"%d\" width=\"1\" height=\"1\" fill=\"%s\" opacity=\"%.3f\" />\n",
                            x, y, hex, c.getAlpha() / 255.0));
                }
            }
        }
        sb.append("</svg>\n");
        try (Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8))) {
            w.write(sb.toString());
        }
    }
}
