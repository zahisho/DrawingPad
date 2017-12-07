package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;
import java.io.Serializable;

public abstract class ShapeAbstract implements Movable, Selectable, Fillable, Drawable{

  protected Color color;
  protected Color colorFill;
  protected boolean withColorFill;
  protected boolean selected;

  protected Shape shape;

  public ShapeAbstract() {
   color = Color.black;
  }

  public ShapeAbstract(Color color) {
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

  public abstract ShapeAbstract clonShape();

}
