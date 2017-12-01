package model;

import java.awt.Graphics;
import java.awt.Point;

public class OvalShape extends TwoEndsShape {
  
  private final double EPS = 8;

  public void draw(Graphics g) {
    int x = Math.min(x1, x2);
    int y = Math.min(y1, y2);
    int w = Math.abs(x1 - x2) + 1;
    int h = Math.abs(y1 - y2) + 1;
    if (color != null) {
      g.setColor(color);
    }
    if(withColorFill){
      g.setColor(colorFill);
      g.fillOval(x, y, w, h);
    }
    g.setColor(color);
    g.drawOval(x, y, w, h);      
  }

  @Override
  public boolean isSelected(Point p) {

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

  @Override
  public void fillColor() {
    withColorFill = true;
  }

  @Override
  public Shape clon() {
    OvalShape clon = new OvalShape();
    
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
