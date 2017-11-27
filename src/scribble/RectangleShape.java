package scribble;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class RectangleShape extends Shape {

  private Rectangle2D rectangle;

  public RectangleShape(Color color) {
    super(color);
  }

  @Override
  public void draw(Graphics g) {
    int x = Math.min(x1, x2);
    int y = Math.min(y1, y2);
    int w = Math.abs(x1 - x2) + 1;
    int h = Math.abs(y1 - y2) + 1;
    if (color != null) {
      g.setColor(color);
    }
    g.drawRect(x, y, w, h);
    rectangle = new Rectangle2D.Double(x, y, w, h);
  }

  @Override
  public boolean isPoint(Point point) {
    boolean res = false;
    Rectangle2D react = this.rectangle;
    if (react.intersects(point.x, point.y, point.x, point.y)) {
      res = true;
    }
    return false;
  }
}
