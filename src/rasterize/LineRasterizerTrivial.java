package rasterize;

public class LineRasterizerTrivial extends LineRasterizer{
    public LineRasterizerTrivial(RasterBufferedImage raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2) {
        float k = (y2 - y1) / (float) (x2 - x1);
        float q = y1 - k * x1;

        // TODO: x1 může být větší než x2

        for(int x = x1; x <= x2; x++) {
            int y = Math.round(k * x + q);
            raster.setPixel(x, y, 0xffffff);
        }

        // TODO: dokončit algoritmus
    }
}
