package uHotFramework;
import java.awt.Color;
import tools.uAbstractTool;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import listener.DrawingListener;
import tools.ITool;
import uHotDrawFigures.IFigure;

/**
 *
 * @author Alejandro Dovale
 */
public class uDrawingView extends JPanel  implements MouseListener,MouseMotionListener,DrawingListener{
    uDrawing drawing;
    uDrawingEditor editor;
    public List<IFigure> seleccionadas;
    
    public uDrawingView(){
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        seleccionadas = new ArrayList<IFigure>();
    }
    public uDrawing getDrawing(){ return drawing;}
    
    public void setDrawing(uDrawing d){ drawing = d;}

    public void paint(Graphics g){
        drawing.draw(g);
    }

    public uDrawingEditor getDrawingEditor(){return editor;}
    
    public void setDrawingEditor(uDrawingEditor e){
        editor = e;
    }

    public ITool tool(){
        return editor.getCurrentTool();
    }  

    
    // gestion de Eventos
    public void mousePressed(MouseEvent aEvent){
        tool().mouseDown(aEvent);
        //editor.repaint();
    }
    
    public void mouseReleased(MouseEvent aEvent){
        tool().mouseUp(aEvent);
       // editor.repaint();
    }

    public void mouseMoved(MouseEvent aEvent){
        tool().mouseMove(aEvent);
        //editor.repaint();
    } 

    public void mouseDragged(MouseEvent aEvent){
        tool().mouseDrag(aEvent);
        //editor.repaint();
 }

    @Override
    public void mouseExited(MouseEvent aEvent){} //implementación vacía
    public void mouseEntered(MouseEvent aEvent){} //implementación vacía
    public void mouseClicked(MouseEvent aEvent){} //implementación vacía

    public void clearSelection(){
        seleccionadas.clear();
    }

    public void addToSelection(IFigure f){
        seleccionadas.add(f);
    }
    public void cambiaSeleccionadas(){
        for(IFigure f : seleccionadas)
            f.setSize(100,100);
    }

    @Override
    // observer
    public void drawingChanged() {
        editor.repaint();
    }

    
    public List<IFigure> getSeleccionadas(){
        return seleccionadas;
    }

}
