package uHotDrawFigures;
import java.awt.Graphics;
import java.awt.Color;
/**
 *
 * @author Alejandro Dovale
 */
public class uEllipseFigure  extends uAbstractFigure{
       
        public uEllipseFigure(){}
        
        public uEllipseFigure(int x, int y, int w, int h){
            super(x,y,w,h);
        }
    
        public void draw(Graphics g){
            g.setColor(Color.BLUE);
            g.drawOval((int)displayBox.getX(),(int) displayBox.getMinY(), 
                 (int) displayBox.getWidth(), (int) displayBox.getHeight());
        }

        public uEllipseFigure clone(){
            return new uEllipseFigure();
        }

}
