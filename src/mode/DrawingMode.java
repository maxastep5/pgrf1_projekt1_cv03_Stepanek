package mode;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public interface DrawingMode {
    void mousePressed(MouseEvent e);
    void mouseDragged(MouseEvent e);
    void mouseReleased(MouseEvent e);
    void draw();
    void clear();
}
