package mode;

import controller.Controller2D;
import rasterize.LineRasterizer;
import rasterize.LineRasterizerColorTransition;
import rasterize.LineRasterizerTrivial;
import view.Panel;

import java.awt.event.MouseEvent;

public class ShiftedLineMode implements DrawingMode{
    private final Controller2D controller;
    private final Panel panel;
    private final LineRasterizer lineRasterizer;

    public ShiftedLineMode(Controller2D controller, Panel panel, LineRasterizer lineRasterizer) {
        this.controller = controller;
        this.panel = panel;
        this.lineRasterizer = lineRasterizer;

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int centerX = panel.getRaster().getWidth() / 2;
        int centerY = panel.getRaster().getHeight() / 2;

        panel.getRaster().clear();
        lineRasterizer.rasterize(centerX, centerY, e.getX(), e.getY());
        panel.repaint();

    }

    @Override
    public void draw() {

    }
}
