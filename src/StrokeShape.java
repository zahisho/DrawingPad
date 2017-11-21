package scribble;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class StrokeShape extends Shape {

  protected List<Point> points = new ArrayList<>();

  public StrokeShape(Color color) {
    super(color);
  }

  public void addPoint(Point p) {
    if (p != null) {
      points.add(p);
    }
  }

  public List getPoints() {
    return points;
  }

  @Override
  public void draw(Graphics g) {
    if (color != null) {
      g.setColor(color);
    }
    Point prev = null;
    for (Point cur : points) {
      if (prev != null) {
        g.drawLine(prev.x, prev.y, cur.x, cur.y);
      }
      prev = cur;
    }
  }
}
