package rasterize;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RasterBufferedImage implements Raster {

    private final BufferedImage image;

    public RasterBufferedImage(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);;
    }

    @Override
    public void setPixel(int x, int y, int color) {
        // TODO: ošetřit vykreslní mimo obrazovku
        image.setRGB(x, y, color);
    }

    @Override
    public int getPixel(int x, int y) {
        // TODO: dodělat v druhé úloze
        return 0;
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public void clear() {
        Graphics g = image.getGraphics();
        g.clearRect(0, 0, image.getWidth(), image.getHeight());
    }

    public BufferedImage getImage() {
        return image;
    }
}
