package drawing;

import java.awt.Graphics;
import java.awt.Point;

public class Oval extends TwoEndsShape {

  @Override
  public final void draw(Graphics g) {
    int x = Math.min(x1, x2);
    int y = Math.min(y1, y2);
    int w = Math.abs(x1 - x2) + 1;
    int h = Math.abs(y1 - y2) + 1;
    g.drawOval(x, y, w, h);
  }

  @Override
  public final boolean isSelected(Point p) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
