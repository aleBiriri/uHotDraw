package uHotDrawFigures;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import listener.FigureListener;
/**
 *
 * @author Alejandro Dovale
 */
public interface IFigure {
    public void draw( Graphics g);
    
    public IFigure clone();
    public IFigure clone(int x, int y, int w, int h);
    public boolean containsPoint(double x, double y);
    
    public void moveBy(double dx, double dy);
    
    public Rectangle getRectanlge();
    
    public void setSize(int w,int h);
  
    public boolean addObserver(FigureListener o);
    
    public void notifyObservers();
    
    public boolean removeObserver(FigureListener o);
    
}
