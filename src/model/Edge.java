package model;

public class Edge {
    private int x1, y1, x2, y2;

    public Edge(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Edge(Point p1, Point p2) {
        this.x1 = p1.getX();
        this.y1 = p1.getY();
        this.x2 = p2.getX();
        this.y2 = p2.getY();
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public boolean isHorizontal() {
        if(y1==y2) {
            return true;
        }
        return false;

    }
    public void orientate(){
        if(y1>y2){
            int tmpX = x1; x1 = x2; x2 = tmpX;
            int tmpY = y1; y1 = y2; y2 = tmpY;
        }
    }
    public boolean isIntersection(int y){
        return y1<= y && y<= y2;

    }
    public int getIntersection(){
        return y1-y2;
    }
}

