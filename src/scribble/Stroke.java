package scribble;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Stroke extends Shape {

  protected List<Point> points = new ArrayList<>();

  public Stroke(Color color) {
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

  @Override
  public boolean isPoint(Point p) {
    boolean res = false;
    int xNow = p.x;
    int yNow = p.y;
    int cantPoint = points.size();
    for (int i = 0; i < cantPoint; i++) {
      int x = points.get(i).x;
      int y = points.get(i).y;
      if (isAlreadyPoint(xNow, yNow, x, y)) {
        res = true;
        break;
      }
    }
    return res;
  }

  private boolean isAlreadyPoint(int xNow, int yNow, int x, int y) {
    boolean res = false;
    if (x == xNow && y == yNow) {
      res = true;
    } else if (x == xNow - 1 && y == yNow - 1) {
      res = true;
    } else if (x == xNow - 2 && y == yNow - 2) {
      res = true;
    } else if (x == xNow + 1 && y == yNow + 1) {
      res = true;
    } else if (x == xNow + 2 && y == yNow + 2) {
      res = true;
    }
    return res;
  }
}
