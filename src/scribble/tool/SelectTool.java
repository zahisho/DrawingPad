package scribble.tool;

import java.awt.Point;
import scribble.drawing.Shape;
import scribble.drawing.ShapeList;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import scribble.ScribbleCanvas;

public class SelectTool extends Tool {

  private final ScribbleCanvas canvas;
  private Point startPoint;
  private Point curPoint;
  private boolean canDrag;

  public SelectTool(ScribbleCanvas canvas) {
    this.canvas = canvas;
  }

  @Override
  public final String getName() {
    return "Select";
  }

  @Override
  public final void mouseClicked(MouseEvent e) {
    startPoint = e.getPoint();
    ShapeList shapes = canvas.getShapes();
    if (!canvas.getKeyCtrlPressed()) {
      canvas.clearSelectedShapes();
    }
    if (shapes != null) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        if (shape.isSelected(startPoint)) {
          canvas.addSelectedShape(shape);
        }
      }
    }
    canvas.repaint();
  }

  @Override
  public final void mousePressed(MouseEvent e) {
    ShapeList selectedShapes = canvas.getSelectedShapes();
    canDrag = false;
    Iterator it = selectedShapes.iterator();
    while (!canDrag && it.hasNext()) {
      Shape s = (Shape) it.next();
      canDrag = s.isSelected(e.getPoint());
      startPoint = e.getPoint();
      curPoint = e.getPoint();
    }
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
    if (canDrag) {
      Point finalPoint = e.getPoint();
      Point auxiliarPoint = new Point(finalPoint.x, finalPoint.y);
      finalPoint.x -= curPoint.x;
      finalPoint.y -= curPoint.y;
      canvas.moveShapes(finalPoint);
      canvas.repaint();
      curPoint = auxiliarPoint;
    }
  }

  @Override
  public final void mouseMoved(MouseEvent e) {
  }
}
