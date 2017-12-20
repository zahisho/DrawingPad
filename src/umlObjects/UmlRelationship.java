package umlObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import shape.UmlElement;

/**
 *
 * @author M16U3L
 */
public abstract class UmlRelationship extends UmlElement implements Observer {

  private static final double ROT_ANGLE = Math.PI / 6;
  private static final double DIAM = 20;
  protected final double EPS = 0.01;
  protected final int TAMROW = 15;

  protected int x1;
  protected int y1;
  protected int x2;
  protected int y2;

  protected UmlObject initReference;
  protected UmlObject endReference;

  protected Point initPoint;
  protected Point endPoint;

  public final void setAbstractions(UmlObject i, UmlObject e) {
    System.out.println("asadc");
    initReference = i;
    endReference = e;
  }

  public void setInitPoint(Point p) {
    initPoint = p;
  }

  public void setEndPoint(Point p) {
    endPoint = p;
  }

  public Point getInitPoint() {
    return initPoint;
  }

  public Point getEndPoint() {
    return endPoint;
  }

  protected void setEnds(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  @Override
  public final boolean isSelected(final Point p) {
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

  protected void overDraw(Graphics g) {
    Color curColor = g.getColor();
    g.setXORMode(Color.darkGray);
    g.setColor(Color.lightGray);
    draw(g);
    g.setColor(curColor);
  }

}
