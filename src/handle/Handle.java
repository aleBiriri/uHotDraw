package handle;

import uHotDrawFigures.IFigure;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import listener.FigureListener;
import tools.ITool;
/**
 *
 * @author Alejandro Dovale
 */
public interface Handle extends  IFigure,ITool{
 
    IFigure owner();
    Point locate();
    
    // métodos de tools
    public  void mouseDown(MouseEvent e);
    public  void mouseUp(MouseEvent e);
    public  void mouseMove(MouseEvent e);
    public  void mouseDrag(MouseEvent e);
    // métodos de las figuras
    public IFigure clone();
    public boolean containsPoint(double x, double y);
    public void moveBy(double dx, double dy);
    public Rectangle getRectanlge();
    public void setSize(int w,int h);
    public boolean addObserver(FigureListener o);
    public void notifyObservers();
    public boolean removeObserver(FigureListener o);
    // método propio
    public IFigure getHandle();
}
