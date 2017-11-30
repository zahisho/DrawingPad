package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public abstract class Shape implements Serializable {

  protected Color color;
  protected boolean withColorFill;

  public Shape() {
   color = Color.black;
  }

  public Shape(Color color) {
    this.color = color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  public abstract void draw(Graphics g);
  
  public abstract boolean isSelected(Point p);
  
  public abstract void move(Point p);
  
  public abstract void fillColor();

}
