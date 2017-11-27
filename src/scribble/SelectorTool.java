package scribble;

import java.awt.Color;
import java.awt.Point;
import java.util.Iterator;

/**
 *
 * @author M16U3L
 */
public class SelectorTool extends AbstractTool implements Tool {

  public SelectorTool(Canvas canvas, String name) {
    super(canvas, name);
  }

  @Override
  public void startAction(Point point) {
    ShapeList shapes = canvas.getShapes();
    if (shapes != null) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        if (shape.isPoint(point)) {
          shape.setColor(Color.gray);
          break;
        } else {
          shape.setColor(shape.color);
        }
      }
    }
    canvas.repaint();
  }

  @Override
  public void continueAction(Point p) {
  }

  @Override
  public void endAction(Point p) {
  }

  @Override
  public String getName() {
    return name;
  }
}
