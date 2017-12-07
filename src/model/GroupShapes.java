package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;

public class GroupShapes extends ShapeAbstract{
  
  private ShapeList shapes;
  
  public GroupShapes(){
    shapes = new ShapeList();
  }

  @Override
  public void setColor(Color color){
    if (shapes != null) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if(shape != null){
          shape.setColor(color);
        }
      }
    }
  }
  
  @Override
  public void setSelected(boolean isSelected){
    if (shapes != null) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if(shape != null){
          shape.setSelected(isSelected);
        }
      }
    }
    selected = isSelected;
  }
  
  @Override
  public ShapeAbstract clonShape() {
    ShapeList clonShapes = new ShapeList();
    if (shapes != null) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if(shape != null){
          clonShapes.add(shape.clonShape());
        }
      }
    }
    GroupShapes clon = new GroupShapes();
    clon.setShapes(clonShapes);
    return (ShapeAbstract) clon;
  }

  @Override
  public void move(Point p) {
    if (shapes != null) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if(shape != null){
          shape.move(p);
        }
      }
    }
  }

  @Override
  public boolean isSelected(Point p) {
    if (shapes != null) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if(shape != null){
          if(shape.isSelected(p)){
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public void markSelected(Graphics g) {
    if (shapes != null) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if(shape != null){
          shape.markSelected(g);
        }
      }
    }
  }

  @Override
  public void fillColor(Color color) {
    if (shapes != null) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if(shape != null){
          shape.fillColor(color);
        }
      }
    }
  }

  @Override
  public void draw(Graphics g) {
    if (shapes != null) {
      Iterator iter = shapes.iterator();
      while (iter.hasNext()) {
        ShapeAbstract shape = (ShapeAbstract) iter.next();
        if(shape != null){
          shape.draw(g);
        }
      }
    }
  }
  
  public ShapeList getShapes(){
    return shapes;
  }
  
  public void setShapes(ShapeList shapes){
    this.shapes = shapes;
  }
  
  public void addShape(ShapeAbstract shape){
    shapes.add(shape);
  }
  
}
