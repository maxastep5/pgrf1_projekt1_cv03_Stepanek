package controller;


import model.Line;
import model.Point;
import model.Polygon;
import rasterize.*;
import view.Panel;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Controller2D {
    private final Panel panel;
    private final PolygonRasterizer polygonRasterizer;

    // Rasterizers
    private LineRasterizer lineRasterizer;

    // To draw
    private Polygon polygon;

    //private Point firstPoint;
    //private List<Line> lines;
    private List<Point> points = new ArrayList<>();

    public Controller2D(Panel panel) {
        this.panel = panel;

        //lineRasterizer = new LineRasterizerColorTransition(panel.getRaster());
        lineRasterizer = new LineRasterizerColorTransition(panel.getRaster());
        polygonRasterizer = new PolygonRasterizer(lineRasterizer);

        //lines = new ArrayList<>();

        polygon = new Polygon();

        initListeners();
    }

    private void initListeners() {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                polygon.addPoint(new Point(e.getX(), e.getY()));
                drawScene();



               /* if (firstPoint == null) {
                    firstPoint = new Point(e.getX(), e.getY());
                    return;
                }

                Line line = new Line(firstPoint, new Point(e.getX(), e.getY()));
                lines.add(line);
                firstPoint = null;

                drawScene();

                */
            }
        });
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_C){
                    clearAll();
                }

            }


        });
    }

    private void drawScene() {
        panel.getRaster().clear();


        //for (Line line : lines)
        //  lineRasterizer.rasterize(line);

        if(polygon.getSize()>=2){

            polygonRasterizer.rasterize(polygon);

        }

        panel.repaint();
    }
    private void clearAll(){
        points.clear();
        polygon = new Polygon();

        panel.getRaster().clear();
        panel.repaint();
    }


}
