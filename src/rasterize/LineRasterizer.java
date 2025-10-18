package rasterize;

import model.Line;
import model.Point;
import raster.RasterBufferedImage;

public abstract class LineRasterizer {
    protected RasterBufferedImage raster;

    public LineRasterizer(RasterBufferedImage raster) {
        this.raster = raster;
    }

    public void rasterize(int x1, int y1, int x2, int y2) {

    }

    public void rasterize(Point p1, Point p2) {
        rasterize(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public void rasterize(Line line) {
        rasterize(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }
}
