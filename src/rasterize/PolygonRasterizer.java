package rasterize;

import model.Point;
import model.Polygon;

public class PolygonRasterizer {

    private LineRasterizer lineRasterizer;

    public PolygonRasterizer(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }

    public void rasterize(Polygon polygon) {

        // kontrola jestli mám alespoň 3 pointy
        if(polygon.getSize()>=3) {

            for (int i = 0; i < polygon.getSize(); i++) {
                int indexA = i;
                int indexB = i + 1;

                if (indexB == polygon.getSize()) {
                    indexB = 0;
                }

                // If indexB se rovná polygon.getSize
                // pokud ano, tak indexB = 0

                Point pA = polygon.getPoint(indexA);
                Point pB = polygon.getPoint(indexB);

                lineRasterizer.rasterize(pA, pB);

                // TODO: dodělat
            }
        }
    }

    public void setLineRasterizer(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }
}
