package toolkit;

import java.awt.event.MouseEvent;
import main.ScribbleCanvas;
import shape.Shape;

/**
 *
 * @author M16U3L
 */
public class TextTool extends Tool {

  private ScribbleCanvas panelCanvas;
  private Shape shape;

  public TextTool(ScribbleCanvas panelCanvas) {
    this.panelCanvas = panelCanvas;
  }

  @Override
  public String getName() {
    return "Text";
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    
  }

  @Override
  public void mousePressed(MouseEvent e) {
   }

  @Override
  public void mouseReleased(MouseEvent e) {
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
 }

  @Override
  public void mouseDragged(MouseEvent e) {
 }

  @Override
  public void mouseMoved(MouseEvent e) {
  }
}
