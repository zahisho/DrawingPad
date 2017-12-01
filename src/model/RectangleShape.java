package model;

import model.TwoEndsShape;
import java.awt.Graphics;
import java.awt.Point;

public class RectangleShape extends TwoEndsShape {
  
  private final double EPS = 5;

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
      g.fillRect(x, y, w, h);
    }
    g.setColor(color);
    g.drawRect(x, y, w, h);      
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
}
