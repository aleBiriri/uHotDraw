package tools;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import listener.FigureListener;
import uHotDrawFigures.IFigure;
import uHotDrawFigures.uAbstractFigure;
import uHotDrawFigures.uPolylineFigure;
import uHotFramework.uDrawingView;
/**
 *
 * @author Alejandro Dovale
 */
public class uCreationTool  extends uAbstractTool{
    
    IFigure prototype;
    
    public uCreationTool(IFigure p, uDrawingView v){
        prototype = p;
        this.v = v;
    }
    public IFigure creationFigure(){
        return prototype.clone();
    }

    public void mouseDown(MouseEvent e){
    }
   
    public void mouseUp(MouseEvent e){ 
        IFigure  f=creationFigure();
        Rectangle r =f.getRectanlge();
        Point p=new Point(e.getX()-r.x,e.getY()-r.y);
        f.moveBy(p.getX(),p.getY());
        v.getDrawing().add(f);
        f.addObserver((FigureListener)v.getDrawing());
    }
    
    public  void mouseMove(MouseEvent e){}
    
    public void mouseDrag(MouseEvent e){
        
    }
}
