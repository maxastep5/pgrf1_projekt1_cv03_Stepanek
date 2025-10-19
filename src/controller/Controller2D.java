package controller;


import mode.DrawingMode;
import mode.InterpolatedLineMode;
import mode.PolygonMode;
import mode.ShiftedLineMode;
import rasterize.LineRasterizer;
import rasterize.LineRasterizerColorTransition;
import rasterize.LineRasterizerTrivial;
import rasterize.PolygonRasterizer;
import view.Panel;
import java.awt.event.*;



public class Controller2D {
    private final Panel panel;
    private DrawingMode currentMode;


    private LineRasterizer lineRasterizer;
    private final PolygonRasterizer polygonRasterizer;
    private final LineRasterizerColorTransition lineRasterizerColorTransition;


    public Controller2D(Panel panel) {
        this.panel = panel;

        lineRasterizer = new LineRasterizerTrivial(panel.getRaster());
        lineRasterizerColorTransition = new LineRasterizerColorTransition(panel.getRaster());
        polygonRasterizer = new PolygonRasterizer(lineRasterizer);
        currentMode = new InterpolatedLineMode(this, panel, lineRasterizerColorTransition);

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
                    case KeyEvent.VK_C:
                        clearAll();
                        break;
                }
            }
        });


        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentMode.mousePressed(e);
                currentMode.draw();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentMode.mouseReleased(e);
            }
        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentMode.mouseDragged(e);
            }
        });


    }

    private void clearAll() {
        panel.getRaster().clear();

            currentMode.clear();

        panel.repaint();
    }


}
