package scribble;

import java.awt.BasicStroke;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScribbleStroke implements Shape {

  // The list of points on the stroke
  // elements are instances of java.awt.Point
  private final List<Point> points = new ArrayList();
  private Color color;
  private final float[] DASHED_STROKE = new float[]{5, 2};
  private boolean selected;
  private static final int HIT_BOX_SIZE = 2;

  public ScribbleStroke(final Color color) {
    this.color = color;
    selected = false;
  }

  public final void addPoint(final Point p) {
    if (p != null) {
      points.add(p);
    }
  }

  public final List getPoints() {
    return points;
  }

  @Override
  public final void draw(final Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    Stroke previous = g2d.getStroke();
    if (color != null) {
      g.setColor(color);
    }
    if (selected) {
      setSelected(g);
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
    g2d.setStroke(previous);
  }

  @Override
  public final void setColor(final Color color) {
    this.color = color;
  }

  @Override
  public boolean isSelected(Point p) {
    boolean res = false;
    double min = Double.MAX_VALUE;
    for (Object pt : points) {
      double dist = ((Point2D) p).distance((Point2D) pt);
      if (dist < min) {
        min = dist;
      }
    }
    if (min <= 4.0) {
      res = true;
    }
    selected = res;
    return res;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public void setSelected(Graphics g) {
    selected = true;
    Graphics2D g2d = (Graphics2D) g;
    g2d.setStroke(new BasicStroke(2,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
            0, DASHED_STROKE, 0));
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
  public boolean intersects(double x, double y, double width, double height) {
    boolean res = false;
    Rectangle2D shape = new Rectangle2D.Double(x, y, width, height);
    for (Point p : points) {
      double xP = p.getX();
      double yP = p.getY();
      double boxX = xP - HIT_BOX_SIZE / 2;
      double boxY = yP - HIT_BOX_SIZE / 2;
      double w = HIT_BOX_SIZE;
      double h = HIT_BOX_SIZE;
      if (shape.intersects(boxX, boxY, w, h)) {
        res = true;
        break;
      }
    }
    return res;
  }
}
