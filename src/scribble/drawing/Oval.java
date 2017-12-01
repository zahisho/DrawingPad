package scribble.drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Oval extends Figure implements Fillable {

  private final double EPS = 0.1;

  private int x1;
  private int y1;
  private int x2;
  private int y2;
  private boolean filled;

  private void setEnds(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  private void overDraw(Graphics g) {
    Color curColor = g.getColor();
    g.setXORMode(Color.darkGray);
    g.setColor(Color.lightGray);
    draw(g);
    g.setColor(curColor);
  }

  @Override
  public final void draw(Graphics g) {
    int x = Math.min(x1, x2);
    int y = Math.min(y1, y2);
    int w = Math.abs(x1 - x2) + 1;
    int h = Math.abs(y1 - y2) + 1;
    g.drawOval(x, y, w, h);
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
    double a = Math.abs(x2 - x1);
    double b = Math.abs(y2 - y1);
    a /= 2;
    b /= 2;
    double xc = (double) (x1 + x2) / 2;
    double yc = (double) (y1 + y2) / 2;
    double eq = (Math.pow(xc - p.x, 2) / Math.pow(a, 2)
            + Math.pow(yc - p.y, 2) / Math.pow(b, 2));
    boolean res;

    if (filled) {
      res = eq <= 1 + EPS;
    } else {
      res = Math.abs(1 - eq) < EPS;
    }

    return res;
  }

  @Override
  public final Figure getFigure() {
    return new Oval();
  }

  @Override
  public final void move(Point p) {
    x1 += p.x;
    x2 += p.x;
    y1 += p.y;
    y2 += p.y;
  }

  @Override
  public final void setFilled(boolean f) {
    filled = f;
  }

  @Override
  public final void fillInside(Graphics g) {
    if (filled) {
      int x = Math.min(x1, x2);
      int y = Math.min(y1, y2);
      int w = Math.abs(x1 - x2) + 1;
      int h = Math.abs(y1 - y2) + 1;
      g.fillOval(x, y, w, h);
    }
  }
}
