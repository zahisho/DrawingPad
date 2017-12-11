package scribble.drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Rectangle extends Figure implements Fillable {

  private final double EPS = 5;

  private int x1;
  private int y1;
  private int x2;
  private int y2;

  private Color fillingColor;

  private boolean between(int x, int x1, int x2) {
    return x >= Math.min(x1, x2) && x <= Math.max(x1, x2);
  }

  private boolean overRect(int px, int py, int x1, int x2, int y) {
    return Math.abs(py - y) < EPS && Math.abs(px - x1) + Math.abs(px - x2)
            == Math.abs(x1 - x2);
  }

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
    int x = Math.min(x1, x2);
    int y = Math.min(y1, y2);
    int w = Math.abs(x1 - x2) + 1;
    int h = Math.abs(y1 - y2) + 1;
    if (fillingColor != null) {
      g.setColor(fillingColor);
      g.fillRect(x, y, w, h);
    }
    Graphics2D gr = (Graphics2D) g;
    gr.setColor(contourColor);
    gr.setStroke(new BasicStroke(thickness, BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_BEVEL, 0, lineStyle, 0));
    gr.drawRect(x, y, w, h);
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
    if (fillingColor != null) {
      res = between(p.x, x1, x2) && between(p.y, y1, y2);
    }
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

  @Override
  public final Figure getFigure() {
    return new Rectangle();
  }

  @Override
  public final void move(Point p) {
    x1 += p.x;
    x2 += p.x;
    y1 += p.y;
    y2 += p.y;
  }

  @Override
  public void setFillingColor(Color c) {
    fillingColor = c;
  }

  @Override
  public Figure copy() {
    Rectangle nFigure = new Rectangle();
    nFigure.setContour(contourColor);
    nFigure.setFillingColor(fillingColor);
    nFigure.setEnds(x1, y1, x2, y2);
    nFigure.setThickness(thickness);
    nFigure.setLineStyle(lineStyle);
    return nFigure;
  }
}
