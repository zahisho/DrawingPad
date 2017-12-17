package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author M16U3L
 */
public class RectangleShape extends TwoEndsShape implements Fillable, Initializable {

  private boolean filled;
  private final double EPS = 5;
  private Color fillColor;
  private String name;

  public RectangleShape(Color color, Color fillColor, String name) {
    super(color);
    this.fillColor = fillColor;
    filled = false;
    this.name = name;
  }

  @Override
  public void draw(final Graphics g) {
    Graphics2D g2g = (Graphics2D) g;
    Stroke previous = g2g.getStroke();
    int x = Math.min(x1, x2);
    int y = Math.min(y1, y2);
    int w = Math.abs(x1 - x2) + 1;
    int h = Math.abs(y1 - y2) + 1;
    if (filled) {
      g.setColor(fillColor);
      g.fillRect(x, y, w, h);
    }
    if (color != null) {
      g.setColor(color);
    }
    g.drawRect(x, y, w, h);
    shape = new Rectangle2D.Double(x, y, w, h);
    g2g.setStroke(previous);
    g2g.draw(shape);
    g2g.drawString(name, x + 2, y + 12);
    g2g.drawRect(x, y, w, 15);
  }

  @Override
  public boolean isSelected(Point p) {
    boolean res = false;
    if (!res) {
      res = overRect(p.x, p.y, x1, x2, y1);
      if (!res) {
        res = overRect(p.x, p.y, x1, x2, y2);
        if (!res) {
          res = overRect(p.y, p.x, y1, y2, x1);
          if (!res) {
            res = overRect(p.y, p.x, y1, y2, x2);
          }
        }
      }
    }
    return res;
  }

  private boolean overRect(int px, int py, int x1, int x2, int y) {
    return Math.abs(py - y) < EPS && Math.abs(px - x1) + Math.abs(px - x2)
      == Math.abs(x1 - x2);
  }

  @Override
  public final void fill(final Color c) {
    filled = true;
    fillColor = c;
  }

  @Override
  public boolean contents(Point p) {
    double x = p.getX();
    double y = p.getY();
    double boxX = x - HIT_BOX_SIZE / 2;
    double boxY = y - HIT_BOX_SIZE / 2;
    double width = HIT_BOX_SIZE;
    double height = HIT_BOX_SIZE;
    boolean res = shape.contains(boxX, boxY, width, height);
    return res;
  }
}
