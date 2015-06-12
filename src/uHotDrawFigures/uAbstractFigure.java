package uHotDrawFigures;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import listener.FigureListener;
/**
 *
 * @author Alejandro Dovale
 */
public class uAbstractFigure implements IFigure {
    
    public  Rectangle displayBox;
    public  List<FigureListener> observadores;
    
    public uAbstractFigure(){
        displayBox = new Rectangle(20,20,30,30);
        observadores = new ArrayList<FigureListener>();
    }
    
    public IFigure clone(int x, int y, int w, int h){
        displayBox = new Rectangle(x,y,w,h);
        observadores = new ArrayList<FigureListener>();
        return this;
    }
    public uAbstractFigure(int x, int y, int w, int h){
        displayBox = new Rectangle(x,y,w,h);
        observadores = new ArrayList<FigureListener>();
    }
    public void draw(Graphics g){
      g.drawRect((int)displayBox.getX(),(int) displayBox.getY(), 
                 (int) displayBox.getWidth(), (int) displayBox.getHeight()); 
        
    }
    
    public Rectangle getRectanlge() { return displayBox;}
    
    public void moveBy(double dx, double dy){
        int x = (int) dx;
        int y = (int) dy;
        displayBox.translate(x,y);
        notifyObservers();
    }

    public boolean containsPoint(double x, double y){
       return displayBox.contains(x,y);
    }

    public void setDisplayBox(Rectangle n){
        displayBox = n;
        n = null;
        notifyObservers();
    }
   
    public IFigure clone(){
        return new uAbstractFigure();
    }
    
    public void setSize(int w,int h){
        displayBox.setSize(w,h);
        notifyObservers();
    }

    public void notifyObservers(){
        for(FigureListener o : observadores)
            o.figureChanged();
    }

    public boolean addObserver(FigureListener o){
        return observadores.add(o);
    }
    
    public boolean removeObserver(FigureListener o){
        return observadores.remove(o);
    }
}
