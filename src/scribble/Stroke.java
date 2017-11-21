package scribble;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Stroke extends Shape {

  // The list of points on the stroke
  // elements are instances of java.awt.Point
  protected List<Point> points = new ArrayList<>();

  public Stroke() {}

  public Stroke(Color color) {
    super(color);
  }

  public final void addPoint(Point p) {
    if (p != null) {
      points.add(p);
    }
  }

  public final List getPoints() {
    return points;
  }

  @Override
  public final void draw(Graphics g) {
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
