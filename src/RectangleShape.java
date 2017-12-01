
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import scribble.Fillable;

public class RectangleShape extends TwoEndsShape implements Fillable {

  private boolean filled;
  private final double EPS = 5;
  private Color fillColor;

  public RectangleShape(Color color) {
    super(color);
    filled = false;
  }

  @Override
  public final void draw(final Graphics g) {
    Graphics2D g2g = (Graphics2D) g;
    Stroke previous = g2g.getStroke();
    int x = Math.min(x1, x2);
    int y = Math.min(y1, y2);
    int w = Math.abs(x1 - x2) + 1;
    int h = Math.abs(y1 - y2) + 1;
    if (selected) {
      setSelected((g));
    }
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
  }

  @Override
  public boolean isSelected(Point p) {
    if (filled) {
      super.isSelected(p);
    } else {
      selected = false;
      if (!selected) {
        selected = overRect(p.x, p.y, x1, x2, y1);
        if (!selected) {
          selected = overRect(p.x, p.y, x1, x2, y2);
          if (!selected) {
            selected = overRect(p.y, p.x, y1, y2, x1);
            if (!selected) {
              selected = overRect(p.y, p.x, y1, y2, x2);
            }
          }
        }
      }
    }
    return selected;
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
}
