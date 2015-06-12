package uHotDrawFigures;
import java.awt.Graphics;
import java.awt.Color;
/*
 * @author Alejandro Dovale
 */
public class uRectangleFigure extends uAbstractFigure {
    
    public uRectangleFigure(){}
    
    public uRectangleFigure(int x, int y, int w, int h){
        super(x,y,w,h);
    
    }
    
    public void draw(Graphics g){
        g.setColor(Color.GREEN);
        g.drawRect((int)displayBox.getX(),(int) displayBox.getY(), 
                    (int) displayBox.getWidth(), (int) displayBox.getHeight());
    }

    public uRectangleFigure clone(){
        return new uRectangleFigure();
    }

    public void setSize(int w, int h){
        displayBox.setSize(w,h);  
    }
}
