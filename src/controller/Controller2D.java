package controller;


import mode.DrawingMode;
import mode.InterpolatedLineMode;
import mode.PolygonMode;
import mode.ShiftedLineMode;
import model.Point;
import model.Polygon;
import rasterize.LineRasterizer;
import rasterize.LineRasterizerColorTransition;
import rasterize.LineRasterizerTrivial;
import rasterize.PolygonRasterizer;
import view.Panel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Controller2D {
    private final Panel panel;
    private final PolygonRasterizer polygonRasterizer;
    private final LineRasterizerColorTransition lineRasterizerColorTransition;
    private DrawingMode currentMode;

    // Rasterizers
    private LineRasterizer lineRasterizer;

    // To draw


    //private Point firstPoint;
    //private List<Line> lines;
    private List<Point> points = new ArrayList<>();


    public Controller2D(Panel panel) {
        this.panel = panel;

        lineRasterizer = new LineRasterizerTrivial(panel.getRaster());
        lineRasterizerColorTransition = new LineRasterizerColorTransition(panel.getRaster());
        polygonRasterizer = new PolygonRasterizer(lineRasterizer);

        //lines = new ArrayList<>();





        initListeners();
    }

    private void initListeners() {
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_P:
                        currentMode = new PolygonMode(Controller2D.this, panel, lineRasterizer);
                        break;

                    case KeyEvent.VK_I:
                        currentMode = new InterpolatedLineMode(Controller2D.this, panel,lineRasterizerColorTransition );
                        break;
                        case KeyEvent.VK_M:
                            currentMode = new ShiftedLineMode(Controller2D.this,panel,lineRasterizer);
                            break;
                }
            }
        });





        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               currentMode.mousePressed(e);
               currentMode.draw();



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
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentMode.mouseDragged(e);
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
    /*
    private void drawScene() {
        panel.getRaster().clear();


        //for (Line line : lines)
          //  lineRasterizer.rasterize(line);

        if(polygon.getSize()>=2){

            polygonRasterizer.rasterize(polygon);

        }

        panel.repaint();
    }

     */
    private void clearAll(){
        points.clear();


        panel.getRaster().clear();
        panel.repaint();
    }


}
