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
public class SelectorTool implements Tool {

  private final ScribbleCanvas panelCanvas;
  private Point curPoint;
  private boolean canDrag;
  private Point startPoint;

  public SelectorTool(ScribbleCanvas canvas) {
    this.panelCanvas = canvas;
  }

  @Override
  public final String getName() {
    return "Select";
  }

  @Override
  public void startAction(Point p) {
    ShapeList shapes = panelCanvas.getShapes();
    if (!shapes.isEmpty()) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        if (shape.isSelected(p)) {
          if (!shape.getSelect()) {
            shape.setSelect(true);
            panelCanvas.addSelectedShape(shape);
            break;
          } else {
            shape.setSelect(false);
            panelCanvas.removeSelectedShape(shape);
            break;
          }
        }
      }
    }
    panelCanvas.repaint();
  }

  @Override
  public void continueAction(Point p) {
  }

  @Override
  public void endAction(Point p) {
  }
}
