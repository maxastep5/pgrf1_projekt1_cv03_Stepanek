package controller;


import rasterize.LineRasterizer;
import rasterize.LineRasterizerGraphics;
import rasterize.LineRasterizerTrivial;
import view.Panel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller2D {
    private final Panel panel;
    private int color = 0xffffff;

    private LineRasterizer lineRasterizer;

    public Controller2D(Panel panel) {
        this.panel = panel;

        //lineRasterizer = new LineRasterizerGraphics(panel.getRaster());
        lineRasterizer = new LineRasterizerTrivial(panel.getRaster());

        initListeners();
    }

    private void initListeners() {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getX() < 0 || e.getY() < 0 ||
                        panel.getRaster().getWidth() <= e.getX() ||
                        panel.getRaster().getHeight() <= e.getY()
                )
                    return;

                panel.getRaster().setPixel(e.getX(), e.getY(), color);
                panel.repaint();
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
               // ze středu rasteru se vykreslní úsečka k myši
                int centerX = panel.getRaster().getWidth() / 2;
                int centerY = panel.getRaster().getHeight() / 2;

                panel.getRaster().clear();
                lineRasterizer.rasterize(centerX, centerY, e.getX(), e.getY());
                panel.repaint();
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_P)
                    color = 0xff0000;

                if(e.getKeyCode() == KeyEvent.VK_O)
                    color = 0xffffff;

                if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    int centerX = panel.getRaster().getWidth() / 2;
                    int centerY = panel.getRaster().getHeight() / 2;

                    for(int x = centerX; x < panel.getRaster().getWidth(); x++) {
                        panel.getRaster().setPixel(x, centerY, color);
                    }

                    panel.repaint();
                }
            }
        });
    }

}
