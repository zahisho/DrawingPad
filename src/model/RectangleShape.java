package model;

import java.awt.Color;
import model.TwoEndsShape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.Stroke;

public class RectangleShape extends TwoEndsShape {
  
  private final double EPS = 5;

  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    Stroke previous = g2d.getStroke();
    int x = Math.min(x1, x2); 
    int y = Math.min(y1, y2); 
    int w = Math.abs(x1 - x2) + 1; 
    int h = Math.abs(y1 - y2) + 1;     
    if(withColorFill){
      g.setColor(colorFill);
      g.fillRect(x, y, w, h);
    }
    if(selected){
      markSelected(g);
    }
    if (color != null) {
      g.setColor(color);
    }
    g.setColor(color);
    g.drawRect(x, y, w, h);  
    shape = new Rectangle2D.Double(x, y, w, h);
    g2d.setStroke(previous);
    g2d.draw(shape);    
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

  public void fillColor(Color color) {
    withColorFill = true;
    colorFill = color;
  }

  @Override
  public ShapeAbstract clonShape() {
    RectangleShape clon = new RectangleShape();
    
    clon.color = color;
    clon.colorFill = colorFill;
    clon.withColorFill = withColorFill;
    clon.selected = selected;
    
    clon.x1 = x1;
    clon.x2 = x2;
    clon.y1 = y1;
    clon.y2 = y2;
    
    return (ShapeAbstract) clon;
  }

}
