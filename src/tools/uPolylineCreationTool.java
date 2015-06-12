package tools;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import listener.FigureListener;
import uHotDrawFigures.IFigure;
import uHotDrawFigures.uPolylineFigure;
import uHotFramework.uDrawingView;

/**
 *
 * @author Alejandro Dovale
 */
public class uPolylineCreationTool extends uCreationTool {
    
   uPolylineFigure polyline;
    public uPolylineCreationTool(IFigure p, uDrawingView v){
        super(p,v);
    }
    
    public IFigure creationFigure (){
        return new uPolylineFigure();
    }

    public void mouseDown(MouseEvent e){
        if(polyline == null){
        polyline = new uPolylineFigure();
        polyline.add(new Point(e.getX(),e.getY()));
        Rectangle r = polyline.getRectanlge();
        Point p=new Point(e.getX()-r.x,e.getY()-r.y);
        polyline.moveBy(p.getX(),p.getY());
        v.getDrawing().add(polyline);
        polyline.addObserver((FigureListener)v.getDrawing());
        }
    }

    public void mouseUp(MouseEvent e){
       polyline.add(new Point(e.getX(),e.getY())); 
    
    }

    public void mouseDrag(MouseEvent e){
    }
    public void setPolyline(uPolylineFigure p){
        polyline = p;
    }
    public void setPolyline(){
        polyline = null;
    }
}
