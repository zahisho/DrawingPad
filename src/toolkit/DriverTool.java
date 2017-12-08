package toolkit;

import java.awt.Point;
import java.util.Iterator;
import main.ScribbleCanvas;
import shape.Shape;
import shape.ShapeList;

/**
 *
 * @author M16U3L
 */
public class DriverTool implements Tool {

  private final ScribbleCanvas panelCanvas;
  private static final int HIT_BOX_SIZE = 4;
  private Point startPoint;
  private Point curPoint;
  private boolean catchAShape;

  public DriverTool(ScribbleCanvas panelCanvas) {
    this.panelCanvas = panelCanvas;
    catchAShape = false;
  }

  @Override
  public String getName() {
    return "Driver";
  }

  @Override
  public void startAction(Point p) {
    curPoint = p;
    ShapeList selectedShapes = panelCanvas.getSelectedShapes();
    if (!selectedShapes.isEmpty()) {
      panelCanvas.groupSelectedShapes();
      //un-group
      System.out.println(panelCanvas.getSelectedShapes().isEmpty());
    }
  }

  @Override
  public void continueAction(Point p) {
    ShapeList selectedShapes = panelCanvas.getSelectedShapes();
    if (!selectedShapes.isEmpty()) {
      Iterator it = selectedShapes.iterator();
      while (it.hasNext()) {
        Shape shape = (Shape) it.next();
        Point auxiliarPoint = new Point(p.x, p.y);
        p.x -= curPoint.x;
        p.y -= curPoint.y;
        shape.move(p);
        panelCanvas.repaint();
        curPoint = auxiliarPoint;

      }
    }
    panelCanvas.repaint();
  }

  @Override
  public void endAction(Point p) {
  }

}
