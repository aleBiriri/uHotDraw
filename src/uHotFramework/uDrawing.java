package uHotFramework;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import uHotDrawFigures.IFigure;
import uHotDrawFigures.uAbstractFigure;
import uHotDrawFigures.uCompositeFigure;
import java.util.Iterator;
import java.util.List;
import listener.DrawingListener;
import listener.FigureListener;
/**
 *
 * @author Alejandro Dovale
 */
public class uDrawing extends uCompositeFigure implements FigureListener {
    
    public List<DrawingListener> observadores;
   
    
    public uDrawing(){
        figures = new ArrayList<IFigure>();
        observadores = new ArrayList<DrawingListener>();
    }

    // método para herramienta de selección
    public IFigure findFigure(int x, int y){
        IFigure figure = null;
        IFigure aux = null;
        Iterator it = figures.iterator();
        boolean flag = false;
        while((it.hasNext())&&(!flag)){
            figure = (IFigure)it.next();
            if(figure.containsPoint(x,y)){
                flag = true;
                aux = figure;
            }
         }
        return aux;
    }
     // método para herramienta de selección
    public List<IFigure> findFigures(Rectangle rf){
        List<IFigure> l = new ArrayList<IFigure>();
        for(IFigure f: figures){
                if(rf.intersects(f.getRectanlge()))
                   l.add(f);
        }
        return l;
    }

    //******************Patrón Observer
    //Métodos de Patrón Observer
    //******************
    @Override
    public void figureChanged() { //setChanged()
       notifyObservers();
    }

    public void addObserver(DrawingListener o){
        observadores.add(o);
    }

    public void notifyObservers(){
        for(DrawingListener o: observadores)
            o.drawingChanged();
    }
    
    public void removeObserver(DrawingListener o){
        observadores.remove(o);
    }

    public boolean add(IFigure o){
        boolean bandera =  super.add(o);
        figureChanged();
        return bandera;
    }
    
    public boolean remove(IFigure o){
        boolean bandera =  super.remove(o);
        figureChanged();
        return bandera;
    }
    
    //fin métodos de patrón Observer 
    
    public void draw( Graphics g){
        super.draw(g);
        figureChanged();
    }
}
