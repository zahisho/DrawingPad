package toolkit;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import main.ScribbleCanvas;
import shape.Shape;
import shape.ShapeList;

/**
 *
 * @author M16U3L
 */
public class SelectorTool extends Tool {

  private final ScribbleCanvas panelCanvas;
  private Point curPoint;

  public SelectorTool(ScribbleCanvas canvas) {
    this.panelCanvas = canvas;
  }

  @Override
  public final String getName() {
    return "Select";
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    ShapeList selectedShapes = panelCanvas.getSelectedShapes();
    ShapeList shapes = panelCanvas.getShapes();
    if (!shapes.isEmpty()) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        if (shape.isSelected(e.getPoint())) {
          if (!shape.getSelect()) {
            shape.setSelect(true);
            panelCanvas.addSelectedShape(shape);
          } else {
            shape.setSelect(false);
            panelCanvas.removeSelectedShape(shape);
          }
        }
      }
    }
    panelCanvas.repaint();
  }

  @Override
  public void mousePressed(MouseEvent e) {
    curPoint = e.getPoint();
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
    ShapeList selectedShapes = panelCanvas.getSelectedShapes();
    Point auxiliarPoint = e.getPoint();
    Point finalPoint = e.getPoint();
    if (!selectedShapes.isEmpty()) {
      finalPoint.x -= curPoint.x;
      finalPoint.y -= curPoint.y;
      Iterator it = selectedShapes.iterator();
      while (it.hasNext()) {
        Shape shape = (Shape) it.next();
        shape.move(finalPoint);
        panelCanvas.repaint();
        curPoint = auxiliarPoint;
      }
    }

    panelCanvas.repaint();
  }

  @Override
  public void mouseMoved(MouseEvent e) {
  }
}
