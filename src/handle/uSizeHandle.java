package handle;

import java.awt.Point;
import java.util.List;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import uHotDrawFigures.IFigure;
import uHotDrawFigures.uRectangleFigure;
import uHotFramework.uDrawingView;

/**
 *
 * @author Alejandro Dovale
 */
public class uSizeHandle extends uAbstractHandle {
    List<IFigure> seleccionadas;
    public List<IFigure> handleSeleccionadas;
    boolean sobreHandle;
    
    public uSizeHandle(uDrawingView v){
        super(v);
        seleccionadas = new ArrayList<IFigure>();
        handleSeleccionadas = new ArrayList<IFigure>();
    }

    @Override
    public  void mouseDown(MouseEvent e) {
        // SI TENEMOS UNA SELECCIÓN DE FIGURAS TENEMOS QUE 
        // REACCIONAR DE FORMA DIFERENTE A SI NO LA TENEMOS
        if(seleccionadas.size() != 0){
            
            int x,y;
            x = e.getX();
            y = e.getY();
            if( esHandle(x,y)){ // si estamos sobre un handle
                // creamos el handle y su owner
                handle = hallarHandle(x,y);
                handle.addObserver(view.getDrawing());
                owner = hallarOwner(handle);
                if(owner != null)
                    owner.addObserver(view.getDrawing());
            }
        }
        if((handle != null) &&(handle.containsPoint(e.getX(),e.getY())))
            sobreHandle = true; // ponemos esta bandera a true que nos servirá
                                // en el método mouseUp
        
        //Si no tenemos un owner al presionar, y si donde hemos presionado no es una figura seleccionada
        if(owner == null&&(!perteneceSeleccionadas(e.getX(),e.getY()))){ 
            // asignamos el owner y creamos un handle para él
            owner = view.getDrawing().findFigure(e.getX(),e.getY());
            if(owner != null){
                handle = new uRectangleFigure((int)owner.getRectanlge().getX(),(int)owner.getRectanlge().getY(),10,10);
                view.getDrawing().add(handle);
                handle.addObserver(view.getDrawing());
            }
        }
    }
    
    
    public  void mouseUp(MouseEvent e) {
        if(sobreHandle){ // si cuando hicimos mouseDown fue sobre un handle
            // quitamos del dibujo el owner y el handle así como de las colecciones de gestión de las seleccionadas
            view.getDrawing().remove(handle);
            view.getDrawing().remove(owner);
            seleccionadas.remove(owner);
            handleSeleccionadas.remove(handle);
            //luego crearemos un rectángulo nuevo, que tendrá como coordenadas donde suelto el ratón y 
            // como dimensiones las calculadas a continuación
            int w;
            int h;
            // asignamos valores al ancho y al largo dependiendo de donde soltamos el ratón
            
            if(handle.getRectanlge().getX() <= owner.getRectanlge().getX()){ 
                w =  (int)owner.getRectanlge().getWidth() +(int)owner.getRectanlge().getX() - 
                    (int)handle.getRectanlge().getX(); 
            }
            else{
                w = (int)owner.getRectanlge().getWidth() - modulo((int)handle.getRectanlge().getX(),
                    (int)owner.getRectanlge().getX()); 
            }
        
            if(handle.getRectanlge().getY() <= owner.getRectanlge().getY()){
                h =  (int)owner.getRectanlge().getHeight() + (int)owner.getRectanlge().getY() - 
                    (int)handle.getRectanlge().getY(); 
                   
            }
            else{
                h = (int)owner.getRectanlge().getHeight() - modulo((int)handle.getRectanlge().getY(),
                    (int)owner.getRectanlge().getY()); 
            }
      
            //asignamos al owner sus nuevas dimensiones
            owner = owner.clone((int)handle.getRectanlge().getX(),(int)handle.getRectanlge().getY(),w,h);
            view.getDrawing().add(owner);
            handle = null;
            sobreHandle = false;
            owner = null;
        }
            
    }
    public  void mouseMove(MouseEvent e) {}    
    public  void mouseDrag(MouseEvent e) {
        if(sobreHandle){ // si estamos sobre un handle lo movemos solo a él
            Rectangle r=handle.getRectanlge();
            Point p=new Point(e.getX()-r.x,e.getY()-r.y);
            handle.moveBy(p.getX(),p.getY());
        }
    }

    /*  A partir de aquí empiezan los métodos auxiliares de esta clase
        que hacen posible que funcione correctamente todo.
    */
    
    // crear una colección de selcciondas a partir de las seleccionadas por la
    // herramienta de selección
    public void addFigures(List<IFigure> l){ 
        for(IFigure f : l)
            seleccionadas.add(f);
    }
  
    
   // crear los handle para esas seleccionadas
    
    public void crearHandleSeleccionadas(){
        handle = null;
        for(IFigure f : seleccionadas){
            handle = new uRectangleFigure((int)f.getRectanlge().getX(),(int)f.getRectanlge().getY(),10,10);
            handle.addObserver(view.getDrawing());
            handleSeleccionadas.add(handle);
            view.getDrawing().add(handle);
        }
    }
    // calcular el módulo. Sirve para la asignación del ancho y el largo del 
    // nuevo rectángulo en mouseUp
    public int modulo(int n, int n1){
        int solucion = n - n1;
        return (solucion < 0)? -1*solucion : solucion;
    }

    public List<IFigure> getSeleccionadas(){
        return seleccionadas;
    }
    // halla el owner del handle que se le pasa
    public IFigure hallarOwner(IFigure f){
        if( f  != null){
            for(IFigure figura: seleccionadas){
                if(figura.getRectanlge().intersects(f.getRectanlge()))
                    return figura;
            }
        }
        return null;
    }

    // verifica si la figura sobre la que se ha pulsado(coordenadas x,y) es un handle
    public boolean esHandle(int x, int y){
        Iterator it = handleSeleccionadas.iterator();
        boolean esHandle = false;
        uRectangleFigure aux = null;
        while((it.hasNext())&&(!esHandle)){
            aux = (uRectangleFigure) it.next();
            esHandle = aux.containsPoint(x,y);
        }
        return esHandle;
    }
    //devuelve el handle que se encuentra en las coordenadas x,y
    public uRectangleFigure hallarHandle(int x, int y){
        for(IFigure f : handleSeleccionadas)
            if(f.containsPoint(x,y))
                return (uRectangleFigure) f;
        return null;
    }
    // verifica si las coordenadas x,y pertencen a una figura seleccionada
  public boolean  perteneceSeleccionadas(int x, int y){
      for(IFigure f : seleccionadas)
          if(f.containsPoint(x,y))
              return true;
      return false;
  }
    // limpia el dibujo de todos los handles
  public void limpiarDrawing(){
      view.getDrawing().getFigures().removeAll(handleSeleccionadas);
  }
  public IFigure getHandle(){return handle;}
  
  public List<IFigure> getHandleSeleccionadas(){return handleSeleccionadas;}
}
