package mode;


import controller.Controller2D;
import fill.SeedFiller;
import model.Point;
import model.Polygon;
import raster.Raster;
import rasterize.LineRasterizer;
import rasterize.PolygonRasterizer;
import view.Panel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PolygonMode implements DrawingMode {
    private final Panel panel;
    private final Controller2D controller;
    private final PolygonRasterizer polygonRasterizer;
    private LineRasterizer lineRasterizer;
    private Polygon polygon;

    private Raster raster;
    private int color = Color.BLUE.getRGB();

    public PolygonMode(Controller2D controller, Panel panel, LineRasterizer lineRasterizer) {
        this.panel = panel;
        this.controller = controller;
        this.lineRasterizer = lineRasterizer;
        this.polygonRasterizer = new PolygonRasterizer(lineRasterizer);
        this.polygon = new Polygon();
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if(e.isShiftDown())
        polygon.addPoint(new Point(e.getX(), e.getY()));
        draw();
        if(e.isShiftDown()) {
            SeedFiller filler = new SeedFiller(color,e.getX(),e.getY(),panel.getRaster().getPixel(e.getX(),e.getY()),panel.getRaster());
            filler.fill();
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void draw() {
        panel.getRaster().clear();

        if(polygon.getSize()>=2){

            polygonRasterizer.rasterize(polygon);

        }

        panel.repaint();

    }

    @Override
    public void clear() {
        polygon = new Polygon();
    }


}
