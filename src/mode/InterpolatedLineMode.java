package mode;

import controller.Controller2D;
import model.Point;
import rasterize.LineRasterizer;
import rasterize.LineRasterizerColorTransition;
import rasterize.PolygonRasterizer;
import view.Panel;

import java.awt.event.MouseEvent;

public class InterpolatedLineMode implements DrawingMode{
    private final Controller2D controller;
    private final Panel panel;
    private final LineRasterizerColorTransition rasterizer;

    private Point firstPoint = null;

    public InterpolatedLineMode(Controller2D controller, Panel panel, LineRasterizerColorTransition rasterizer) {
        this.controller = controller;
        this.panel = panel;
        this.rasterizer = rasterizer;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (firstPoint == null) {
            firstPoint = new Point(e.getX(), e.getY());
        } else {
            Point secondPoint = new Point(e.getX(), e.getY());
            rasterizer.rasterize(firstPoint.getX(), firstPoint.getY(), secondPoint.getX(), secondPoint.getY());
            firstPoint = null;
            panel.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void draw() {

    }
}
