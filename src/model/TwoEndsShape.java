package model;


import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import model.ShapeAbstract;

public abstract class TwoEndsShape extends ShapeAbstract{

  public int x1;
  public int y1;
  public int x2;
  public int y2;
  
  protected final float[] DASHED_STROKE = new float[]{5, 2};

  public TwoEndsShape() {
  }

  public TwoEndsShape(Color color) {
    super(color);
  }

  public void setEnds(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  public void setEnd1(int x1, int y1) {
    this.x1 = x1;
    this.y1 = y1;
  }

  public void setEnd2(int x2, int y2) {
    this.x2 = x2;
    this.y2 = y2;
  }

  public int getX1() {
    return x1;
  }

  public int getY1() {
    return y1;
  }

  public int getX2() {
    return x2;
  }

  public int getY2() {
    return y2;
  }
  
  @Override
  public void move(Point p) {
    x1 = x1 + p.x;
    x2 = x2 + p.x;
    y1 = y1 + p.y;
    y2 = y2 + p.y;
  }

  @Override
  public final void markSelected(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setStroke(new BasicStroke(2,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
            0, DASHED_STROKE, 0));
  }

}
