package model;

import java.awt.Graphics;
import java.awt.Point;

public interface Selectable {
  
  public boolean isSelected(Point p);
  
  public void markSelected(Graphics g);
  
}
