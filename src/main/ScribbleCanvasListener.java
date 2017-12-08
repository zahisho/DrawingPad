package main;

import main.ScribbleCanvas;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import toolkit.Tool;

public class ScribbleCanvasListener implements MouseListener, MouseMotionListener {

  private ScribbleCanvas canvas;
  protected Tool tool;

  public ScribbleCanvasListener(ScribbleCanvas canvas) {
    this.canvas = canvas;
    //tool = new ScribbleTool(canvas, "Scribble");
  }

  protected ScribbleCanvasListener(
    final ScribbleCanvas canvas, final Tool tool) {
    this.canvas = canvas;
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
  public final void mouseDragged(final MouseEvent e) {
    Point p = e.getPoint();
    if (canvas.mouseButtonDown) {
      tool.continueAction(p);
      canvas.x = p.x;
      canvas.y = p.y;
    }
  }

  @Override
  public final void mouseReleased(final MouseEvent e) {
    Point p = e.getPoint();
    tool.endAction(p);
    canvas.mouseButtonDown = false;
  }

  @Override
  public void mouseClicked(final MouseEvent e) {
  }

  @Override
  public void mouseEntered(final MouseEvent e) {
  }

  @Override
  public void mouseExited(final MouseEvent e) {
  }

  @Override
  public void mouseMoved(final MouseEvent e) {
  }

  public Tool getTool() {
    return tool;
  }

  public void setTool(Tool tool) {
    this.tool = tool;
  }

}
