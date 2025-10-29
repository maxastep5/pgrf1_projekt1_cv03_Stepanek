package fill;

import model.Edge;
import model.Point;
import model.Polygon;
import rasterize.LineRasterizer;
import rasterize.PolygonRasterizer;

import java.util.ArrayList;

public class ScanLine implements Filler {
    private Polygon polygon;
    private LineRasterizer rasterizer;
    private PolygonRasterizer polygonRasterizer;


    public ScanLine(Polygon polygon, LineRasterizer rasterizer, PolygonRasterizer polygonRasterizer) {
        this.polygon = polygon;
        this.rasterizer = rasterizer;
        this.polygonRasterizer = polygonRasterizer;

    }


    @Override
    public void fill() {
        //if polygon get size return, musí mít min 3 pointy
        //potřebuju seznam hran, získat ho ze seznamu pointů
        ArrayList<Edge> edges = new ArrayList<>();

        if(polygon.getSize()>=3) {

            for (int i = 0; i < polygon.getSize(); i++) {
                int indexA = i;
                int indexB = i + 1;

                if (indexB == polygon.getSize()) {
                    indexB = 0;
                }


                Point pA = polygon.getPoint(indexA);
                Point pB = polygon.getPoint(indexB);

                Edge edge = new Edge(pA, pB);
                if(!edge.isHorizontal()){
                    //edge.orientate
                    edges.add(edge);
                }
                //najdu yMin a yMax
                int yMin = polygon.getPoint(0).getY();
                int yMax = 0;
                //v projdu pointy a hledam yMin a yMax
                //for cyklus od yMin do yMax

                for(int y = yMin; y <= yMax; y++) {
                    ArrayList<Integer> intersections = new ArrayList<>();
                    for(Edge e : edges) {
                        //zeptám se, jestli existuje průsečík
                        if (e.isIntersection(y));{
                            continue;

                            e.getIntersection(y);

                            intersections.add(x);
                        }
                        //pokud ano spočítám
                        //uložím průsečík do seznamu pruseciku
                    }

                }

            }
        }
    }
}
