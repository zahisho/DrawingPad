package tool;

import drawing.Shape;
import drawing.ShapeList;
import java.awt.Point;
import java.util.Iterator;
import scribble.ScribbleCanvas;

public class SelectTool implements Tool {

  private final ScribbleCanvas canvas;

  public SelectTool(ScribbleCanvas canvas) {
    this.canvas = canvas;
  }

  @Override
  public final void startAction(Point p) {
    ShapeList shapes = canvas.getShapes();
    if (shapes != null) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        if (shape.isSelected(p)) {
          canvas.setSelectedShape(shape);
          break;
        }
      }
    }
  }

  @Override
  public final void continueAction(Point p) {
  }

  @Override
  public final void endAction(Point p) {
  }

  @Override
  public String getName() {
    return "Select";
  }
}
