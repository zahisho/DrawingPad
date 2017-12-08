package scribble;

import java.awt.Graphics;
import java.awt.Point;
import main.ScribbleCanvas;
import shape.ScribbleStroke;
import toolkit.Tool;

public class ScribbleTool implements Tool {

  private final ScribbleCanvas canvas;
  private final String name;
  private ScribbleStroke curStroke = null;

  public ScribbleTool(final ScribbleCanvas canvas, final String name) {
    this.canvas = canvas;
    this.name = name;
  }

  @Override
  public final String getName() {
    return name;
  }

  @Override
  public final void startAction(final Point p) {
    canvas.resetSelected();
    canvas.repaint();
    curStroke = new ScribbleStroke(canvas.getCurColor());
    curStroke.addPoint(p);
  }

  @Override
  public final void continueAction(final Point p) {
    if (curStroke != null) {
      curStroke.addPoint(p);
      Graphics g = canvas.getGraphics();
      g.setColor(canvas.getCurColor());
      g.drawLine(canvas.x, canvas.y, p.x, p.y);
    }
  }

  @Override
  public final void endAction(final Point p) {
    if (curStroke != null) {
      curStroke.addPoint(p);
      canvas.addShape(curStroke);
      curStroke = null;
    }
  }
}
