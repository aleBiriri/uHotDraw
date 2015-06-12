package tools;

import handle.uSizeHandle;
import java.awt.event.MouseEvent;
import uHotDrawFigures.IFigure;
import uHotDrawFigures.uPolylineFigure;
import uHotDrawFigures.uRectangleFigure;
import uHotFramework.uDrawingView;

/**
 *
 * @author Alejandro Dovale
 */
public class uSelectAreaTool extends uAbstractTool{

    uDrawingView v;
    uRectangleFigure rf;
    public uSelectAreaTool(uDrawingView dv){
        v=dv;
    }
    public void mouseDown(MouseEvent e){
        rf=new uRectangleFigure(e.getX(),e.getY(),0,0);
        v.getDrawing().add(rf);
    }
    public void mouseUp(MouseEvent e){
        v.getDrawing().remove(rf);
        v.clearSelection();
        for(IFigure f:v.getDrawing().findFigures(rf.getRectanlge())){
            if(!(f instanceof uPolylineFigure)) // no queremos que se añadan las polyline porque no vamos a hacer nadas con ellas
                v.addToSelection(f);
        }
        // aquí creamos un handle con las figuras seleccionadas y además
        // establecemos dicho handle como la currentTool
        if(v.getSeleccionadas().size() != 0){
            uSizeHandle handle = new uSizeHandle(v);
            handle.addObserver(v.getDrawing());
            handle.addFigures(v.getSeleccionadas());
            handle.crearHandleSeleccionadas();
            v.getDrawingEditor().setCurrentTool(handle);
        }
    }
    public void mouseDrag(MouseEvent e){
        int rx=rf.getRectanlge().x;
        int ry=rf.getRectanlge().y;
        int w=e.getX()-rx;
        int h=e.getY()-ry;
        rf.setSize(w,h);
    }
    public void mouseMove(MouseEvent e){
    }
    

}
