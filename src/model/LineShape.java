package model;

import java.awt.BasicStroke;
import java.awt.Color;
import model.TwoEndsShape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.Stroke;

public class LineShape extends TwoEndsShape {
  
  private final double EPS = 0.01;
  
  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    Stroke previous = g2d.getStroke();
    shape =   new Line2D.Double(x1, y1, x2, y2);
    if (color != null) {
      g.setColor(color);
    }
    if (selected){
      markSelected(g);
    }
    g.drawLine(x1, y1, x2, y2);      
    g2d.setStroke(previous);
    g2d.draw(shape);
  }

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

  public void fillColor(Color color) {
  }


  public ShapeAbstract clonShape() {
    LineShape clon = new LineShape();
    
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
