package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author M16U3L
 */
public class ScribbleStroke implements Shape {

  private List<Point> points;
  private Color color;
  private final float[] DASHED_STROKE = new float[]{5, 2};
  private boolean selected;
  private static final int HIT_BOX_SIZE = 2;
  private static final double EPS = 7.0;

  public ScribbleStroke(final Color color) {
    this.color = color;
    selected = false;
    points = new ArrayList<>();
  }

  public final void addPoint(final Point p) {
    if (p != null) {
      points.add(p);
    }
  }

  @Override
  public void draw(final Graphics g) {
    Point prev = null;
    g.setColor(this.color);
    for (Point cur : points) {
      if (prev != null) {
        g.drawLine(prev.x, prev.y, cur.x, cur.y);
      }
      prev = cur;
    }
  }

  @Override
  public void setSelected(Graphics g) {
    Graphics2D graph = (Graphics2D) g;
    Stroke previous = graph.getStroke();
    graph.setColor(this.color);
    graph.setStroke(new BasicStroke(2,
      BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
      0, DASHED_STROKE, 0));
    draw(graph);
    graph.setStroke(previous);
  }

  @Override
  public final void setColor(final Color color) {
    this.color = color;
  }

  @Override
  public boolean isSelected(Point p) {
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
  public Color getColor() {
    return color;
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

  @Override
  public void setSelect(boolean b) {
    selected = b;
  }

  @Override
  public boolean getSelect() {
    return selected;
  }
}
