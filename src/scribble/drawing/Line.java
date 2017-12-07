package scribble.drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Line extends Figure {

  private final double EPS = 0.01;

  private int x1;
  private int y1;
  private int x2;
  private int y2;

  private void setEnds(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  private void overDraw(Graphics g) {
    g.setXORMode(Color.darkGray);
    g.setColor(Color.lightGray);
    draw(g);
  }

  @Override
  public final void draw(Graphics g) {
    g.setColor(contourColor);
    g.drawLine(x1, y1, x2, y2);
  }

  @Override
  public final void updateFigure(Point p, Graphics g) {
    overDraw(g);
    setEnds(x1, y1, p.x, p.y);
    overDraw(g);
  }

  @Override
  public final void startFigure(Point p, Graphics g) {
    setEnds(p.x, p.y, p.x, p.y);
    overDraw(g);
  }

  @Override
  public final boolean isSelected(Point p) {
    int xv1 = p.x - x1;
    int yv1 = p.y - y1;
    int xv2 = x2 - x1;
    int yv2 = y2 - y1;
    double v1xv2 = (xv1 * yv2 - xv2 * yv1) / (Math.hypot(x1, y1)
            * Math.hypot(x2, y2));
    double angle = Math.asin(v1xv2);
    boolean res = Math.abs(angle) < EPS && Math.abs(p.x - x2)
            + Math.abs(xv1) == Math.abs(xv2) && Math.abs(p.y - y2)
            + Math.abs(yv1) == Math.abs(yv2);

    return res;
  }

  @Override
  public final Figure getFigure() {
    return new Line();
  }

  @Override
  public final void move(Point p) {
    x1 += p.x;
    x2 += p.x;
    y1 += p.y;
    y2 += p.y;
  }

  @Override
  public Figure copy() {
    Line nFigure = new Line();
    nFigure.setContour(contourColor);
    nFigure.setEnds(x1, y1, x2, y2);
    return nFigure;
  }
}
