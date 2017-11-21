package scribble;

import java.awt.Graphics;
import java.awt.Point;

public class ScribbleTool extends AbstractTool { 

  protected Stroke curStroke;

  public ScribbleTool(ScribbleCanvas canvas, String name) {
    super(canvas, name);
    curStroke = null;
  }

  @Override
  public final void startShape(Point p) {
    curStroke = new Stroke(canvas.getCurColor());
    curStroke.addPoint(p);
  }

  @Override
  public final void addPointToShape(Point p) {
    if (curStroke != null) {
      curStroke.addPoint(p);
      Graphics g = canvas.getGraphics();
      g.setColor(canvas.getCurColor());
      g.drawLine(canvas.x, canvas.y, p.x, p.y);
    }
  }

  @Override
  public final void endShape(Point p) {
    if (curStroke != null) {
      curStroke.addPoint(p);
      canvas.addShape(curStroke);
      curStroke = null;
    }
  } 
}
