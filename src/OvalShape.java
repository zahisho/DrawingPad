import java.awt.Graphics;
import java.awt.Point;

public class OvalShape extends TwoEndsShape {

  public void draw(Graphics g) {
    int x = Math.min(x1, x2);
    int y = Math.min(y1, y2);
    int w = Math.abs(x1 - x2) + 1;
    int h = Math.abs(y1 - y2) + 1;
    if (color != null) {
      g.setColor(color);
    }
    g.drawOval(x, y, w, h);
  }

  @Override
  public boolean belong(Point p) {
    return p.x > x1 && p.y > y1 && p.x < x2 && p.y < y2;
  }

}
