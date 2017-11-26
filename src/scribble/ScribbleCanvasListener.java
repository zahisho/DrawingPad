package scribble;

import drawing.Stroke;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import tool.DrawingTool;
import tool.Tool;

public class ScribbleCanvasListener
        implements MouseListener, MouseMotionListener {

  private final ScribbleCanvas canvas;
  private Tool tool;

  public ScribbleCanvasListener(ScribbleCanvas canvas) {
    this.canvas = canvas;
    tool = new DrawingTool(canvas, new Stroke(), "Scribble");
  }

  @Override
  public final void mousePressed(MouseEvent e) {
    Point p = e.getPoint();
    canvas.setSelectedShape(null);
    tool.startAction(p);
    canvas.setMouseButtonDown(true);
    canvas.setX(p.x);
    canvas.setY(p.y);
  }

  @Override
  public final void mouseDragged(MouseEvent e) {
    Point p = e.getPoint();
    if (canvas.getMouseButtonDown()) {
      tool.continueAction(p);
      canvas.setX(p.x);
      canvas.setY(p.y);
    }
  }

  @Override
  public final void mouseReleased(MouseEvent e) {
    Point p = e.getPoint();
    tool.endAction(p);
    canvas.repaint();
    canvas.setMouseButtonDown(false);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mouseMoved(MouseEvent e) {
  }

  public ScribbleCanvasListener(ScribbleCanvas canvas, Tool tool) {
    this.canvas = canvas;
    this.tool = tool;
  }

  public final Tool getTool() {
    return tool;
  }

  public final void setTool(Tool tool) {
    this.tool = tool;
  }
}
