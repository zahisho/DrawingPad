package tools;

import java.awt.Graphics;
import java.awt.Point;
import model.Stroke;
import tools.AbstractTool;
import view.ScribbleCanvas;

public class ScribbleTool extends AbstractTool {
  
  protected Stroke curStroke = null;

  public ScribbleTool(ScribbleCanvas canvas, String name) {
    super(canvas, name);
  }

  public void startAction(Point p) {
    curStroke = new Stroke(canvas.getCurColor());
    curStroke.addPoint(p);
  }

  public void continueAction(Point p) {
    if (curStroke != null) {
      curStroke.addPoint(p);
      Graphics g = canvas.getGraphics();
      g.setColor(canvas.getCurColor());
      g.drawLine(canvas.x, canvas.y, p.x, p.y);
    }
  }

  public void endAction(Point p) {
    if (curStroke != null) {
      curStroke.addPoint(p);
      canvas.addShape(curStroke);
      curStroke = null;
    }
  }
  
}
