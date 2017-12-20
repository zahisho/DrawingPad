package toolkit;

import java.awt.Point;
import shape.Shape;
import shape.ShapeList;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import main.ScribbleCanvas;

public class SelectorTool extends Tool {

  private final ScribbleCanvas canvas;
  private Point startPoint;
  private Point curPoint;
  private boolean canDrag;

  public SelectorTool(ScribbleCanvas canvas) {
    this.canvas = canvas;
  }

  @Override
  public final String getName() {
    return "Selector";
  }

  @Override
  public final void mouseClicked(MouseEvent e) {
    ShapeList shapes = canvas.getShapes();
    if (!shapes.isEmpty()) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        if (shape.isSelected(e.getPoint())) {
          if (!shape.getSelect()) {
            shape.setSelect(true);
            canvas.addSelectedShape(shape);
          } else {
            shape.setSelect(false);
            canvas.removeSelectedShape(shape);
          }
        }
      }
    }
    canvas.repaint();
  }

  @Override
  public final void mousePressed(MouseEvent e) {
    curPoint = e.getPoint();
  }

  @Override
  public final void mouseReleased(MouseEvent e) {
  }

  @Override
  public final void mouseEntered(MouseEvent e) {
  }

  @Override
  public final void mouseExited(MouseEvent e) {
  }

  @Override
  public final void mouseDragged(MouseEvent e) {
    ShapeList selectedShapes = canvas.getSelectedShapes();
    Point auxiliarPoint = e.getPoint();
    Point finalPoint = e.getPoint();
    if (!selectedShapes.isEmpty()) {
      finalPoint.x -= curPoint.x;
      finalPoint.y -= curPoint.y;
      Iterator it = selectedShapes.iterator();
      while (it.hasNext()) {
        Shape shape = (Shape) it.next();
        shape.move(finalPoint);
        canvas.repaint();
        curPoint = auxiliarPoint;
      }
    }
    canvas.repaint();
  }

  @Override
  public final void mouseMoved(MouseEvent e) {
  }
}
