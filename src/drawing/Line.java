package drawing;

import java.awt.Graphics;
import java.awt.Point;

public class Line extends TwoEndsShape {

  @Override
  public final void draw(Graphics g) {
    g.drawLine(x1, y1, x2, y2);
  }

  @Override
  public final boolean isSelected(Point p) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
