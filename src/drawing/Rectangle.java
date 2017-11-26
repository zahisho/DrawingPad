package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rectangle extends Figure {

  private final double EPS = 5;

  private int x1;
  private int y1;
  private int x2;
  private int y2;

  @Override
  public final void draw(Graphics g) {
    int x = Math.min(x1, x2);
    int y = Math.min(y1, y2);
    int w = Math.abs(x1 - x2) + 1;
    int h = Math.abs(y1 - y2) + 1;
    g.drawRect(x, y, w, h);
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
    boolean res = false;
    if (!res) {
      res = overRect(p.x, p.y, x1, x2, y1);
      if (!res) {
        res = overRect(p.x, p.y, x1, x2, y2);
        if (!res) {
          res = overRect(p.y, p.x, y1, y2, x1);
          if (!res) {
            res = overRect(p.y, p.x, y1, y2, x2);
          }
        }
      }
    }
    return res;
  }

  private boolean overRect(int px, int py, int x1, int x2, int y) {
    return Math.abs(py - y) < EPS && Math.abs(px - x1) + Math.abs(px - x2)
            == Math.abs(x1 - x2);
  }

  private void overDraw(Graphics g) {
    Color curColor = g.getColor();
    g.setXORMode(Color.darkGray);
    g.setColor(Color.lightGray);
    draw(g);
    g.setColor(curColor);
  }

  private void setEnds(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  @Override
  public final Figure getFigure() {
    return new Rectangle();
  }
}
