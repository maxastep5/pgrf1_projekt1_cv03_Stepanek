package mode;

import controller.Controller2D;
import model.Line;
import model.Point;
import rasterize.LineRasterizer;
import rasterize.LineRasterizerColorTransition;
import rasterize.LineRasterizerTrivial;
import view.Panel;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ShiftedLineMode implements DrawingMode{
    private final Controller2D controller;
    private final Panel panel;
    private final LineRasterizer lineRasterizer;
    List<Line> lines = new ArrayList<>();
    Point firstPoint = null;
    Point currentPoint = null;


    public ShiftedLineMode(Controller2D controller, Panel panel, LineRasterizer lineRasterizer) {
        this.controller = controller;
        this.panel = panel;
        this.lineRasterizer = lineRasterizer;

    }

    @Override
    public void mousePressed(MouseEvent e) {
        firstPoint = new Point(e.getX(), e.getY());
        currentPoint=null;

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currentPoint = new Point(e.getX(), e.getY());

        if (e.isShiftDown()) {
            currentPoint = snapToShift(firstPoint, currentPoint);
        }

        draw();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        Point endPoint = new Point(e.getX(), e.getY());
        if (e.isShiftDown()) {
            endPoint = snapToShift(firstPoint, endPoint);
        }

        lines.add(new Line(firstPoint, endPoint));
        firstPoint = null;
        currentPoint = null;
        draw();
    }

    @Override
    public void draw() {
        panel.getRaster().clear();
        for (Line line : lines) {
            lineRasterizer.rasterize(
                    line.getX1(), line.getY1(),
                    line.getX2(), line.getY2()
            );
        }

        if (firstPoint != null && currentPoint != null) {
            lineRasterizer.rasterize(
                    firstPoint.getX(), firstPoint.getY(),
                    currentPoint.getX(), currentPoint.getY()
            );
        }

        panel.repaint();

    }

    @Override
    public void clear() {
        lines.clear();
        firstPoint = null;
        currentPoint = null;
    }

    private Point snapToShift(Point start, Point current) {
        int dx = current.getX() - start.getX();
        int dy = current.getY() - start.getY();

        if (Math.abs(dx) > 2 * Math.abs(dy)) {
            return new Point(current.getX(), start.getY());
        }

        else if (Math.abs(dy) > 2 * Math.abs(dx)) {
            return new Point(start.getX(), current.getY());
        }

        else {
            int signX = (int) Math.signum(dx);
            int signY = (int) Math.signum(dy);
            int delta = Math.min(Math.abs(dx), Math.abs(dy));

            return new Point(
                    start.getX() + signX * delta,
                    start.getY() + signY * delta
            );
        }
    }



}



