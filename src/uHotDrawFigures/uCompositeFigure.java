package uHotDrawFigures;
import java.util.ArrayList;
import java.awt.Graphics;
import java.util.List;
/**
 *
 * @author Alejandro Dovale
 */
public class uCompositeFigure extends uAbstractFigure {
   protected List <IFigure> figures;
    
    public uCompositeFigure(){
        figures = new ArrayList<IFigure>();
    }

    public boolean add(IFigure o){
      return  figures.add(o);
    }
    
    public boolean remove(IFigure o){
      return  figures.remove(o);
    }

    public List getFigures(){
        return figures;
    }
    
    public void draw( Graphics g){
       if(figures.size() != 0){ 
            for(IFigure figure : figures){
                figure.draw(g);
            }
       }
    }
    
public uCompositeFigure clone(){
        return new uCompositeFigure();
    }
    
}
