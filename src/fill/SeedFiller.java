package fill;

import raster.Raster;

public class SeedFiller implements Filler {
    private int fillColor;
    private Raster raster;
    private int backgroundColor;
    private int startX, startY;

    public SeedFiller(int fillColor, int startX, int startY, int backgroundColor, Raster raster) {
        this.fillColor = fillColor;
        this.startX = startX;
        this.startY = startY;
        this.backgroundColor = raster.getPixel(startX, startY);
        this.raster = raster;
    }


    @Override
    public void fill() {
        seedFill(startX, startY);


    }

    private void seedFill(int x, int y){
        if (x < 0 || x >= raster.getWidth() || y < 0 || y >= raster.getHeight()) {
            return;
        }

        //načtu barvu pixelu na který jsem klikl
        int currentColor=raster.getPixel(x, y);
        //podmínka, jestli mám obarvit
        //pokud ne - return/konec

        if(currentColor != backgroundColor || currentColor != fillColor){
            return;
        }


        //obarvit pixel
        raster.setPixel(x, y, fillColor);
        //zavolám fill pro svoje sousedy, potřebuju tam dostat nový x a y
        seedFill(x,y+1);
        seedFill(x+1,y);
        seedFill(x-1,y);
        seedFill(x,y-1);

    }
}
