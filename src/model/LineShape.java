package model;

import model.TwoEndsShape;
import java.awt.Graphics;
import java.awt.Point;

public class LineShape extends TwoEndsShape {
  
  private final double EPS = 0.01;
  
  public void draw(Graphics g) {
    if (color != null) {
      g.setColor(color);
    }
    g.drawLine(x1, y1, x2, y2);
  }

  @Override
  public boolean isSelected(Point p) {
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
  public void fillColor() {
    withColorFill = true;
  }

  @Override
  public Shape clon() {
    LineShape clon = new LineShape();
    
    clon.color = color;
    clon.colorFill = colorFill;
    clon.withColorFill = withColorFill;
    clon.selected = selected;
    
    clon.x1 = x1;
    clon.x2 = x2;
    clon.y1 = y1;
    clon.y2 = y2;
    
    return (Shape) clon;
  }

  @Override
  public void groupFigure(Point p) {
    int newX2 = x1 - x2;
    int newY2 = y1 - y2;
    x1 = p.x;
    y1 = p.y;
    x2 = p.x + newX2;
    y2 = p.y + newY2;
  }
    
}
