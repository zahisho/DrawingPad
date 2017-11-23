package drawing;

import java.awt.Point;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Stroke implements Selectable {

  private List<Point> points = new ArrayList<>();

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
    points = new ArrayList<>();
    points.add(p);
  }

  @Override
  public final boolean isSelected(Point p) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
