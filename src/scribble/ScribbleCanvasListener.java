package scribble;

import tools.ScribbleTool;
import tools.Tool;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ScribbleCanvasListener
        implements MouseListener, MouseMotionListener {

  protected ScribbleCanvas canvas;
  protected Tool tool;

  public ScribbleCanvasListener(ScribbleCanvas canvas) {
    this.canvas = canvas;
    tool = new ScribbleTool(canvas, "Scribble");
  }

  @Override
  public final void mousePressed(MouseEvent e) {
    Point p = e.getPoint();
    tool.startShape(p);
    canvas.mouseButtonDown = true;
    canvas.x = p.x;
    canvas.y = p.y;
  }

  @Override
  public final void mouseDragged(MouseEvent e) {
    Point p = e.getPoint();
    if (canvas.mouseButtonDown) {
      tool.addPointToShape(p);
      canvas.x = p.x;
      canvas.y = p.y;
    }
  }

  @Override
  public final void mouseReleased(MouseEvent e) {
    Point p = e.getPoint();
    tool.endShape(p);
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

  protected ScribbleCanvasListener(ScribbleCanvas canvas, Tool tool) {
    this.canvas = canvas;
    this.tool = tool;
  }
}
