package rasterize;

import raster.RasterBufferedImage;

import java.awt.*;

public class LineRasterizerColorTransition extends LineRasterizer {

    public LineRasterizerColorTransition(RasterBufferedImage raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2) {
        // TODO: pozor na dělení nulou
        float k = (y2 - y1) / (float) (x2 - x1);
        float q = y1 - k * x1;

        // TODO: x1 může být větší než x2

        Color c1 = Color.RED;
        Color c2 = Color.GREEN;

        float[] colorComponentsC1 = c1.getColorComponents(null);
        float[] colorComponentsC2 = c2.getColorComponents(null);
        float[] newColor = new float[3];


        int temp;


        if (Math.abs(k) < 1) {
            if (x1 > x2) {
                temp = x1;
                x1 = x2;
                x2 = temp;
            }
            for (int x = x1; x <= x2; x++) {
                // t = odečtu minimum, dělím rozsahem
                float t = (x - x1) / (float) (x2 - x1);

                for (int i = 0; i < 3; i++) {
                    newColor[i] = colorComponentsC1[i] + t * (colorComponentsC2[i] - colorComponentsC1[i]);
                }
                Color resultColor = new Color(newColor[0], newColor[1], newColor[2]);
                int rgb = resultColor.getRGB();


                int y = Math.round(k * x + q);
                raster.setPixel(x, y, rgb);
            }
        }
        if (Math.abs(k) >= 1) {
            if (y1 > y2) {
                temp = y1;
                y1 = y2;
                y2 = temp;
            }
            for (int y = y1; y <= y2; y++) {
                int x = Math.round((y - q) / k);

                float t = (y - y1) / (float) (y2 - y1);

                for (int i = 0; i < 3; i++) {
                    newColor[i] = colorComponentsC1[i] + t * (colorComponentsC2[i] - colorComponentsC1[i]);
                }
                Color resultColor = new Color(newColor[0], newColor[1], newColor[2]);
                int rgb = resultColor.getRGB();
                raster.setPixel(x, y, rgb);
            }
        }

        // TODO: dokončit algoritmus
    }
}
