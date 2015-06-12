package uHotDrawFigures;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Alejandro Dovale
 */
public class uPolylineFigure extends uAbstractFigure {
    List<Point> puntos;

    public uPolylineFigure(){
        puntos = new ArrayList<Point>();
    }

    public uPolylineFigure(int x, int y, int w, int h){
        super(x,y,w,h);
        
    }
    
    public boolean add(Point p){
        return puntos.add(p);
    }
    
    public void draw(Graphics g){  // hay que arreglar este método
     g.setColor(Color.ORANGE); 
     Point [] aux = hacerArray(puntos);
     for(int i = 0; i < aux.length - 1; i++){
         g.drawLine((int)aux[i].getX(),(int)aux[i].getY(),(int)aux[i+1].getX(),(int)aux[i+1].getY());
     }
    }

    
    private Point[] hacerArray(List<Point> lista){
        Point [] p = new Point[lista.size()];
        int i = 0;
        for(Point punto : lista){
            p[i] = punto;
            i++;
        }
            
     return p;
    }
}
