package rasterize;

import raster.RasterBufferedImage;
//Výhodou triviálního algoritmu je, že je jednoduchý na implementaci a postup je použitelný pro složitější křivky
//Nevýhodou je, že násobení a sčítání v plovoucí řádové čárce je neefektivní
public class LineRasterizerTrivial extends LineRasterizer {

    public LineRasterizerTrivial(RasterBufferedImage raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2) {

        float k = (y2 - y1) / (float) (x2 - x1);
        float q = y1 - k * x1;



        if (x1 == x2) {
            if (y1 > y2) {
                int tmp = y1; y1 = y2; y2 = tmp;
            }
            for (int y = y1; y <= y2; y++) {
                raster.setPixel(x1, y, 0xffffff);
            }
            return;
        }


        if (y1 == y2) {
            if (x1 > x2) {
                int tmp = x1; x1 = x2; x2 = tmp;
            }
            for (int x = x1; x <= x2; x++) {
                raster.setPixel(x, y1, 0xffffff);
            }
            return;
        }


        if (Math.abs(k) < 1) {
            if (x1 > x2) {
                int tmpX = x1; x1 = x2; x2 = tmpX;
                int tmpY = y1; y1 = y2; y2 = tmpY;
            }
            for (int x = x1; x <= x2; x++) {
                int y = Math.round(k * x + q);
                raster.setPixel(x, y, 0xffffff);
            }
        }

        else {
            if (y1 > y2) {
                int tmpY = y1; y1 = y2; y2 = tmpY;
                int tmpX = x1; x1 = x2; x2 = tmpX;
            }
            for (int y = y1; y <= y2; y++) {
                int x = Math.round((y - q) / k);
                raster.setPixel(x, y, 0xffffff);
            }
        }
    }
}
