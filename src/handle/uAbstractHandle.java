package handle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import listener.FigureListener;
import uHotDrawFigures.IFigure;
import uHotDrawFigures.uRectangleFigure;
import uHotFramework.uDrawingView;

/**
 *
 * @author Alejandro Dovale
 */
public  class uAbstractHandle implements Handle {
    
    uDrawingView view;
    IFigure owner = null;
    uRectangleFigure handle = null;
    public  List<FigureListener> observadores;
    
    
    
    public uAbstractHandle(uDrawingView v){
        view = v;
        observadores = new ArrayList<FigureListener>();
    }
    
     public uAbstractHandle(){}
     
     public IFigure getHandle(){return handle;}
     
     public void setDrawingView(uDrawingView v){
         view = v;
     }
    
    @Override
    public IFigure owner() {
        return owner;
    }

    @Override
    public Point locate() {
       return new Point(0,0);
    }

    @Override
    public  void mouseDown(MouseEvent e) {}
    public  void mouseUp(MouseEvent e) {}
    public  void mouseMove(MouseEvent e) {}    
    public  void mouseDrag(MouseEvent e) {}

    @Override
    public IFigure clone() {
        return new uAbstractHandle();
    }

    @Override
    public boolean containsPoint(double x, double y) {
        return handle.containsPoint(x,y);
    }

    @Override
    public void moveBy(double dx, double dy) {
        int x = (int) dx;
        int y = (int) dy;
        handle.getRectanlge().translate(x,y);
        notifyObservers();
    }

    @Override
    public Rectangle getRectanlge() {
        return handle.getRectanlge();
    }

    @Override
    public void setSize(int w, int h) {
        handle.setSize(w,h);
        notifyObservers();
    }

    @Override
    public boolean addObserver(FigureListener o) {
        return observadores.add(o);
    }

    @Override
    public void notifyObservers() {
        for(FigureListener o : observadores)
            o.figureChanged();
    }

    @Override
    public boolean removeObserver(FigureListener o) {
        return observadores.remove(o);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int)handle.getRectanlge().getX(),(int) handle.getRectanlge().getY(), 
                    (int) handle.getRectanlge().getWidth(), (int) handle.getRectanlge().getHeight());
    }
    
    public IFigure clone(int x, int y, int w, int h){
        handle = new uRectangleFigure(x,y,w,h);
        observadores = new ArrayList<FigureListener>();
        return this;
    }
}
