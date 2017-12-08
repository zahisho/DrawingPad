package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

/**
 *
 * @author M16U3L
 */
public abstract class TwoEndsShape implements Shape {

  protected int x1;
  protected int y1;
  protected int x2;
  protected int y2;

  protected Color color;
  protected java.awt.Shape shape;
  protected boolean selected;
  protected static final int HIT_BOX_SIZE = 2;
  private final float[] DASHED_STROKE = new float[]{5, 2};
  protected Color fillColor;

  public TwoEndsShape(Color color) {
    this.color = color;
    selected = false;
  }

  @Override
  public final void setColor(final Color color) {
    this.color = color;
  }

  public final void setEnds(final int x1, final int y1,
    final int x2, final int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  public final void setEnd1(final int x1, final int y1) {
    this.x1 = x1;
    this.y1 = y1;
  }

  public final void setEnd2(final int x2, final int y2) {
    this.x2 = x2;
    this.y2 = y2;
  }

  public final int getX1() {
    return x1;
  }

  public final int getY1() {
    return y1;
  }

  public final int getX2() {
    return x2;
  }

  public final int getY2() {
    return y2;
  }

  @Override
  public boolean isSelected(Point p) {
    double x = p.getX();
    double y = p.getY();
    double boxX = x - HIT_BOX_SIZE / 2;
    double boxY = y - HIT_BOX_SIZE / 2;
    double width = HIT_BOX_SIZE;
    double height = HIT_BOX_SIZE;
    boolean res = shape.intersects(boxX, boxY, width, height);
    return res;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public final void setSelected(Graphics g) {
    Graphics2D graph = (Graphics2D) g;
    Stroke previous = graph.getStroke();
    graph.setColor(this.color);
    graph.setStroke(new BasicStroke(2,
      BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
      0, DASHED_STROKE, 0));
    this.draw(graph);
    graph.setStroke(previous);
  }

  @Override
  public final void move(Point p) {
    x1 += p.x;
    x2 += p.x;
    y1 += p.y;
    y2 += p.y;
  }

  @Override
  public final boolean intersects(double x, double y,
    double width, double height) {
    return shape.intersects(x, y, width, height);
  }

  @Override
  public void setSelect(boolean b) {
    selected = b;
  }

  @Override
  public boolean getSelect() {
    return selected;
  }

  public abstract void setFillColor(Color fillColor);
}
