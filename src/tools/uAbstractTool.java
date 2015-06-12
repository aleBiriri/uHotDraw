package tools;
import java.awt.event.MouseEvent;
import uHotDrawFigures.IFigure;
import uHotFramework.uDrawingView;
/**
 *
 * @author Alejandro Dovale
 */
public abstract class uAbstractTool implements ITool {
    uDrawingView v;
    public abstract void mouseDown(MouseEvent e);
    public abstract void mouseUp(MouseEvent e);
    public abstract void mouseMove(MouseEvent e);
    public abstract void mouseDrag(MouseEvent e);
   
    public void setDrawingView(uDrawingView v){this.v = v;}
    

    public uDrawingView getView() {return v;}
}
