package tools;

import java.awt.event.MouseEvent;

/**
 *
 * @author Alejandro Dovale
 */
public interface ITool {
    public abstract void mouseDown(MouseEvent e);
    public abstract void mouseUp(MouseEvent e);
    public abstract void mouseMove(MouseEvent e);
    public abstract void mouseDrag(MouseEvent e);
}
