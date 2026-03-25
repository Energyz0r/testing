package javapixel.core;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * GridManager holds the core int[][] grid and provides operations:
 * reset, setPixel (with mirror options), getGrid, rotate helpers, extrapolate, toImage.
 */
public class GridManager {
    private int[][] grid;
    private int size;
    private boolean mirrorX = false;
    private boolean mirrorY = false;

    public GridManager(int size) {
        this.size = 5;
        resetGrid();
    }

    public void resetGrid() {
        grid = new int[size][size];
        int white = Color.WHITE.getRGB();
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                grid[y][x] = white;
    }

    public void setSize(int newSize) {
        newSize = Math.max(1, newSize);
        if (newSize == size) return;
        size = newSize;
        resetGrid();
    }

    public int getSize() { return size; }

    public int[][] getGrid() { return grid; }

    public void setMirrorX(boolean v) { mirrorX = v; }
    public void setMirrorY(boolean v) { mirrorY = v; }
    public boolean isMirrorX() { return mirrorX; }
    public boolean isMirrorY() { return mirrorY; }

    public void setPixel(int gx, int gy, Color c) {
        if (gx < 0 || gy < 0 || gx >= size || gy >= size) return;
        int rgb = c.getRGB();
        grid[gy][gx] = rgb;
        if (mirrorX) grid[gy][size - 1 - gx] = rgb;
        if (mirrorY) grid[size - 1 - gy][gx] = rgb;
        if (mirrorX && mirrorY) grid[size - 1 - gy][size - 1 - gx] = rgb;
    }

    public BufferedImage toBufferedImage() {
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                img.setRGB(x, y, grid[y][x]);
        return img;
    }

    /**
     * rotatedPixel: sample src grid at logical coordinates (x,y) after rotating by rot deg.
     * rot in {0,90,180,270}
     */
    public static int rotatedPixel(int[][] src, int x, int y, int rot) {
        int n = src.length;
        return switch (rot) {
            case 90 -> src[n - 1 - y][x];
            case 180 -> src[n - 1 - x][n - 1 - y];
            case 270 -> src[y][n - 1 - x];
            default -> src[y][x];
        };
    }

    /**
     * Extrapolate using preview rotations (passed separately) OR use copy + invertBottom behavior.
     * This method builds a new grid (2x size).
     *
     * @param usePreviewRotations if true, rotatedSource[][] should be used by caller
     * @param previewRotations   length-4 array of rotations (0/90/180/270) matching quadrants
     * @param invertBottom       if true, bottom half is inverted vertically from top
     */
    public void extrapolate(boolean usePreviewRotations, int[] previewRotations, boolean invertBottom) {
        int old = size;
        int newSize = old * 2;
        int[][] newGrid = new int[newSize][newSize];

        if (usePreviewRotations && previewRotations != null && previewRotations.length == 4) {
            // build from source grid using each quadrant rotation
            for (int qy = 0; qy < 2; qy++) {
                for (int qx = 0; qx < 2; qx++) {
                    int idx = qy * 2 + qx;
                    int rot = previewRotations[idx];
                    for (int y = 0; y < old; y++) {
                        for (int x = 0; x < old; x++) {
                            int val = rotatedPixel(grid, x, y, rot);
                            newGrid[qy * old + y][qx * old + x] = val;
                        }
                    }
                }
            }
        } else {
            // Copy top-left to top-right then bottom optionally inverted
            for (int y = 0; y < old; y++) {
                for (int x = 0; x < old; x++) {
                    newGrid[y][x] = grid[y][x];
                    newGrid[y][x + old] = grid[y][x];
                }
            }
            for (int y = 0; y < old; y++) {
                for (int x = 0; x < old; x++) {
                    int sy = invertBottom ? (old - 1 - y) : y;
                    newGrid[y + old][x] = grid[sy][x];
                    newGrid[y + old][x + old] = grid[sy][x];
                }
            }
        }

        size = newSize;
        grid = newGrid;
    }
}
