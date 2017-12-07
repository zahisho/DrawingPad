package scribble.drawing;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class ScribbleStroke extends Figure {

  private final double EPS = 7;

  private List<Point> points = new ArrayList<>();

  public ScribbleStroke() {
    points = new ArrayList<>();
  }

  public ScribbleStroke(List<Point> ps) {
    points = new ArrayList<>();
    ps.forEach((p) -> {
      points.add(new Point(p.x, p.y));
    });
  }

  @Override
  public final void updateFigure(Point p, Graphics g) {
    if (points == null || points.isEmpty()) {
      startFigure(p, g);
    } else {
      Point lastPoint = points.get(points.size() - 1);
      points.add(p);
      g.drawLine(lastPoint.x, lastPoint.y, p.x, p.y);
    }
  }

  @Override
  public final void draw(Graphics g) {
    g.setColor(contourColor);
    Point prev = null;
    for (Point cur : points) {
      if (prev != null) {
        g.drawLine(prev.x, prev.y, cur.x, cur.y);
      }
      prev = cur;
    }
  }

  @Override
  public final void startFigure(Point p, Graphics g) {
    points.add(p);
  }

  @Override
  public final boolean isSelected(Point p) {
    boolean res = false;

    for (Point point : points) {
      res = Math.hypot(point.x - p.x, point.y - p.y) < EPS;
      if (res) {
        break;
      }
    }

    return res;
  }

  @Override
  public final Figure getFigure() {
    return new ScribbleStroke();
  }

  @Override
  public final void move(Point p) {
    points.stream().map((point) -> {
      point.x += p.x;
      return point;
    }).forEachOrdered((point) -> {
      point.y += p.y;
    });
  }

  @Override
  public Figure copy() {
    ScribbleStroke nFigure = new ScribbleStroke(points);
    nFigure.setContour(contourColor);
    return nFigure;
  }
}
