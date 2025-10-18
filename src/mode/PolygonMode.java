package mode;


import controller.Controller2D;
import model.Point;
import model.Polygon;
import rasterize.LineRasterizer;
import rasterize.PolygonRasterizer;
import view.Panel;

import java.awt.event.MouseEvent;

public class PolygonMode implements DrawingMode {
    private final Panel panel;
    private final Controller2D controller;
    private final PolygonRasterizer polygonRasterizer;

    // Rasterizers
    private LineRasterizer lineRasterizer;

    // To draw
    private Polygon polygon;

    public PolygonMode(Controller2D controller, Panel panel, LineRasterizer lineRasterizer) {
        this.panel = panel;
        this.controller = controller;
        this.lineRasterizer = lineRasterizer;
        this.polygonRasterizer = new PolygonRasterizer(lineRasterizer);
        this.polygon = new Polygon();
    }


    @Override
    public void mousePressed(MouseEvent e) {
        polygon.addPoint(new Point(e.getX(), e.getY()));
        draw();

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void draw() {
        panel.getRaster().clear();

        if(polygon.getSize()>=2){

            polygonRasterizer.rasterize(polygon);

        }

        panel.repaint();

    }
}
