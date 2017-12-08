package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author M16U3L
 */
public class OvalShape extends TwoEndsShape implements Fillable {

  private boolean filled;
  private final double EPS = 0.1;
  private Color fillColor;

  public OvalShape(Color color, Color fillColor) {
    super(color);
    this.fillColor = fillColor;
    filled = false;
  }

  @Override
  public final boolean isSelected(final Point p) {
    double a = Math.abs(x2 - x1);
    double b = Math.abs(y2 - y1);
    a /= 2;
    b /= 2;
    double xc = (double) (x1 + x2) / 2;
    double yc = (double) (y1 + y2) / 2;
    double eq = (Math.pow(xc - p.x, 2) / Math.pow(a, 2)
      + Math.pow(yc - p.y, 2) / Math.pow(b, 2));
    boolean res;
    if (filled) {
      res = eq <= 1 + EPS;
    } else {
      res = Math.abs(1 - eq) < EPS;
    }
    return res;
  }

  @Override
  public final void fill(final Color c) {
    filled = true;
    fillColor = c;
  }

  @Override
  public void draw(Graphics g) {
    Graphics2D g2g = (Graphics2D) g;
    Stroke previous = g2g.getStroke();
    int x = Math.min(x1, x2);
    int y = Math.min(y1, y2);
    int w = Math.abs(x1 - x2) + 1;
    int h = Math.abs(y1 - y2) + 1;
    if (filled) {
      g.setColor(fillColor);
      g.fillOval(x, y, w, h);
    }
    if (color != null) {
      g.setColor(color);
    }
    g.drawOval(x, y, w, h);
    shape = new Ellipse2D.Double(x, y, w, h);
    g2g.setStroke(previous);
    g2g.draw(shape);
  }

  @Override
  public final void setFillColor(Color fillColor) {
    this.fillColor = fillColor;
  }
}
