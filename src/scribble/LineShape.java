package scribble;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Line2D;

/**
 *
 * @author M16U3L
 */
public class LineShape extends Shape {

  Line2D line;

  public LineShape(Color color) {
    super(color);
  }

  @Override
  public void draw(Graphics g) {
    if (color != null) {
      g.setColor(color);
    }
    g.drawLine(x1, y1, x2, y2);
    line = new Line2D.Double(x1, y1, x2, y2);
  }

  @Override
  public boolean isPoint(Point point) {
    boolean res = false;

    if (line.intersects(point.x, point.y, point.x, point.y)) {
      res = true;
    }
    return res;
  }
}
