package scribble;

import java.awt.Graphics;
import java.awt.Point;

public class ScribbleTool extends AbstractTool {

  protected Stroke curStroke = null;

  public ScribbleTool(Canvas canvas, String name) {
    super(canvas, name);
  }

  @Override
  public void startAction(Point p) {
    curStroke = new Stroke(canvas.getCurColor());
    curStroke.addPoint(p);
  }

  @Override
  public void continueAction(Point p) {
    if (curStroke != null) {
      curStroke.addPoint(p);
      Graphics g = canvas.getGraphics();
      g.setColor(canvas.getCurColor());
      g.drawLine(canvas.x, canvas.y, p.x, p.y);
    }
  }

  @Override
  public void endAction(Point p) {
    if (curStroke != null) {
      curStroke.addPoint(p);
      canvas.addShape(curStroke);
      curStroke = null;
    }
  }

}
