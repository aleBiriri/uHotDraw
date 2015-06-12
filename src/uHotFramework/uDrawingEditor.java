package uHotFramework;
import handle.Handle;
import handle.uAbstractHandle;
import handle.uSizeHandle;
import tools.uAbstractTool;
import javax.swing.JFrame;
import java.util.List;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import listener.FigureListener;
import tools.ITool;
import tools.Juego;
import tools.uCreationTool;
import tools.uDragTool;
import tools.uPolylineCreationTool;
import tools.uSelectAreaTool;
import tools.uSelectionTool;
import uHotDrawFigures.uEllipseFigure;
import uHotDrawFigures.uPolylineFigure;
import uHotDrawFigures.uRectangleFigure;
/**
 *
 * @author Alejandro Dovale
 */
public class uDrawingEditor extends JFrame implements ActionListener{
    
    uDrawingView view;
    List<uAbstractTool> tools;
    ITool currentTool;
    JButton b4,b5,b6,b7,b8,juego;
    ITool rT,eT,pT,sT;;//,juegoTool; PROXIMAMENTE
    Handle hT;
    public uDrawingEditor(){
        super();
        JPanel barraHerramientas = new JPanel();
        b4 = new JButton("Rectangle");
        b4.setActionCommand("r");
        b4.addActionListener(this);
        barraHerramientas.add(b4);
        b5 = new JButton("Ellipse");
        b5.setActionCommand("e");
        b5.addActionListener(this);
        barraHerramientas.add(b5);
        b6 = new JButton("Polyline");
        b6.setActionCommand("p");
        b6.addActionListener(this);
        barraHerramientas.add(b6);
        b7 = new JButton("Select");
        b7.setActionCommand("s");
        b7.addActionListener(this);
        barraHerramientas.add(b7);
        barraHerramientas.setVisible(true);
        b8 = new JButton("Handle");
        b8.setActionCommand("h");
        b8.addActionListener(this);
        barraHerramientas.add(b8);
        barraHerramientas.setVisible(true);
        /* EN CONSTRUCCION
        juego = new JButton("Juego"); 
        juego.setActionCommand("j");
        juego.addActionListener(this);
        barraHerramientas.add(juego);
        barraHerramientas.setVisible(true);
       */
        view=new uDrawingView();
        view.setPreferredSize(new Dimension(500,500));
        view.setDrawingEditor(this);
        uDrawing d = new uDrawing();
        view.setDrawing(d);
        d.addObserver(view); // añadir el observador(la vista) al dibujo
        
        this.getContentPane().setLayout(new BorderLayout());             
        this.getContentPane().add(barraHerramientas, BorderLayout.NORTH);
        this.getContentPane().add(view, BorderLayout.SOUTH); // this.getContentPane().add((uDrawingView)view, BorderLayout.CENTER);

        this.setVisible(true);
        this.pack();
        
        rT =new uCreationTool( new uRectangleFigure(0,0,50,50),this.getDrawingView());
        eT = new uCreationTool(new uEllipseFigure(0,0,10,10),this.getDrawingView());
        pT = new uPolylineCreationTool( new uPolylineFigure(0,0,10,10),this.getDrawingView());
        sT = new uSelectionTool(this.getDrawingView());
        //juegoTool = new Juego(); PROXIMAMENTE
        currentTool = rT;
    }
    
    public void setDrawingView( uDrawingView v){
        view = v;
        this.setContentPane(v);
    }
    
    public uDrawingView getDrawingView(){return view;}
    
    public List<uAbstractTool> getTools(){return tools;}
    
    public void setTools(List<uAbstractTool> t){
        tools = t;
    }
    
    public ITool getCurrentTool(){return currentTool;}
    
    public void setCurrentTool(ITool t){
        currentTool = t;
    }

    /*********************************
     * ActionListener implementation
     *********************************/
    public void actionPerformed(ActionEvent e) {
        if(currentTool instanceof uSizeHandle){
                uSizeHandle aux = (uSizeHandle) currentTool;
                aux.limpiarDrawing();
                view.getDrawing().getFigures().remove(aux.getHandle());
            }
        if ("r".equals(e.getActionCommand())) {
           
            setCurrentTool(rT);
        }
        
        if ("e".equals(e.getActionCommand())) {
           
            setCurrentTool(eT);
        }
        if ("p".equals(e.getActionCommand())) {       
            uPolylineCreationTool p = (uPolylineCreationTool)pT;
            p.setPolyline(); // poner el polyline a null para crear uno nuevo
            p = null;
            setCurrentTool(pT);
            
        }
         if ("s".equals(e.getActionCommand())) {
           
            setCurrentTool(sT);
         }
         if ("h".equals(e.getActionCommand())) {
            hT =  new uSizeHandle(view);
            hT.addObserver((FigureListener)view.getDrawing()); // añadir observador(dibujo) al handle
            setCurrentTool(hT);
         }
        
    }

    
}
