package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public abstract class Shape implements Serializable {

  protected Color color;
  protected Color colorFill;
  protected boolean withColorFill;
  protected boolean selected;

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
  
  public void setColorFill(Color color){
    this.colorFill = color;
  }

  public Color getColorFill(){
    return colorFill;
  }
  
  public void setSelected(boolean isSelected){
    selected = isSelected;
  }
  public boolean getSelected(){
    return selected;
  }

  public abstract void draw(Graphics g);
  
  public abstract boolean isSelected(Point p);
  
  public abstract void move(Point p);
  
  public abstract void fillColor();

}
