package model;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.ShapeAbstract;

public class Stroke extends ShapeAbstract {
  
  protected List<Point> points;
  private final double EPS = 5;
  protected final float[] DASHED_STROKE = new float[]{5, 2};
  
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
  
  public void setPoints(List<Point> points){
    this.points = points;
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

  public void fillColor(Color color) {
    withColorFill = true;
  }

  @Override
  public void move(Point p) {
    for (Point point : points) {
      point.x = point.x + p.x;
      point.y = point.y + p.y;
    }
  }

  @Override
  public ShapeAbstract clonShape() {
    Stroke clon = new Stroke();
    
    clon.color = color;
    clon.colorFill = colorFill;
    clon.withColorFill = withColorFill;
    clon.selected = selected;
    
    List<Point> pointsClon = new ArrayList<>();
    for (Point point : points) {
      Point p = new Point(point.x, point.y);
      pointsClon.add(p);
    }
    clon.setPoints(pointsClon);
    return (ShapeAbstract) clon;
  }

  @Override
  public void markSelected(Graphics g) {
   
  }
}
