package tools;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import uHotDrawFigures.IFigure;
import uHotFramework.uDrawingView;

/**
 *
 * @author Alejandro Dovale
 */
public class uDragTool extends uAbstractTool
{
    private uDrawingView v;
    private IFigure figure;
    
    public uDragTool(uDrawingView dv,IFigure f){
        v=dv;
        figure=f;
    }
    public uDragTool(){
    }
    
    public boolean setFigure(MouseEvent e){
        IFigure f = null;
        boolean bandera = false;
        Iterator it = v.getDrawing().getFigures().iterator();
        while(it.hasNext()){
            f = (IFigure) it.next();
            if(f.getRectanlge().contains(e.getX(),e.getY())){
                figure = f;
                bandera = true;
            }
        }
      return bandera;
    }
    
    
    
    public void mouseDown(MouseEvent e){
        setFigure(e);
    }
    public void mouseUp(MouseEvent e){
    }
    public void mouseDrag(MouseEvent e){
        Rectangle r=figure.getRectanlge();
        Point p=new Point(e.getX()-r.x,e.getY()-r.y);
        figure.moveBy(p.getX(),p.getY());
    }
    public void mouseMove(MouseEvent e){
    }
}
