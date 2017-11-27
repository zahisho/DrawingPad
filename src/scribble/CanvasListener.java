package scribble;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class CanvasListener
  implements MouseListener, MouseMotionListener {

  private Canvas canvas;
  private Tool tool;

  public CanvasListener(Canvas canvas) {
    this.canvas = canvas;
    tool = new ScribbleTool(canvas, "Scribble");
  }

  public Tool getTool() {
    return tool;
  }

  public void setTool(Tool tool) {
    this.tool = tool;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    Point p = e.getPoint();
    tool.startAction(p);
    canvas.mouseButtonDown = true;
    canvas.x = p.x;
    canvas.y = p.y;
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    Point p = e.getPoint();
    if (canvas.mouseButtonDown) {
      tool.continueAction(p);
      canvas.x = p.x;
      canvas.y = p.y;
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    Point p = e.getPoint();
    tool.endAction(p);
    canvas.mouseButtonDown = false;
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

  public CanvasListener(Canvas canvas, Tool tool) {
    this.canvas = canvas;
    this.tool = tool;
  }

}
