package scribble.tool;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import scribble.drawing.Shape;
import scribble.frame.ShapeList;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import scribble.frame.ScribbleCanvas;

public class SelectTool extends Tool implements KeyListener {

  private final int CTRL_CODE = 17;

  private boolean ctrlPressed;

  private final ScribbleCanvas canvas;
  private Point curPoint;
  private boolean canDrag;
  private boolean startingDrag;

  public SelectTool(ScribbleCanvas canvas) {
    this.canvas = canvas;
    ctrlPressed = false;
  }

  @Override
  public final String getName() {
    return "Select";
  }

  @Override
  public final void mouseClicked(MouseEvent e) {
    Point startPoint = e.getPoint();
    ShapeList shapes = canvas.getCurLayer();
    if (!ctrlPressed) {
      canvas.clearSelectedShapes();
    }
    if (shapes != null) {
      Iterator iter = shapes.iterator();
      Shape selected = null;
      while (iter.hasNext()) {
        Shape shape = (Shape) iter.next();
        if (shape.isSelected(startPoint)) {
          selected = shape;
        }
      }
      if (selected != null) {
        canvas.addSelectedShape(selected);
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
      if (canDrag) {
        curPoint = e.getPoint();
        startingDrag = true;
      }
    }
    canvas.repaint();
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
      if (startingDrag) {
        canvas.startMovement();
        startingDrag = false;
      }

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

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == CTRL_CODE) {
      ctrlPressed = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == CTRL_CODE) {
      ctrlPressed = false;
    }
  }
}
