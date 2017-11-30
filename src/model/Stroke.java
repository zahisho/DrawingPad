package model;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Shape;

public class Stroke extends Shape {
  
  protected List<Point> points;
  private final double EPS = 5;
  
  public Stroke() {
    points = new ArrayList<>();
  }

  public Stroke(Color color) {
    super(color);
    points = new ArrayList<>();
  }

  public void addPoint(Point p) {
    if (p != null) {
      points.add(p);
    }
  }

  public List<Point> getPoints() {
    return points;
  }
  
  @Override
  public void draw(Graphics g) {
    if (color != null) {
      g.setColor(color);
    }
    Point prev = null;
    Iterator iter = points.iterator();
    while (iter.hasNext()) {
      Point cur = (Point) iter.next();
      if (prev != null) {
        g.drawLine(prev.x, prev.y, cur.x, cur.y);
      }
      prev = cur;
    }
  }

  @Override
  public boolean isSelected(Point p) {
    boolean res = false;
    for (Point point : points) {
      res = Math.hypot(point.x - p.x, point.y - p.y) < EPS;
      if (res) {
        System.out.println("Click Stroke");
        break;
      }
    }
    return res;
  }

  @Override
  public void fillColor() {
    withColorFill = true;
  }

  @Override
  public void move(Point p) {
    for (Point point : points) {
      point.x = point.x - p.x;
      point.y = point.y - p.y;
    }
  }
}
