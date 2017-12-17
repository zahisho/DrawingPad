package shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;

/**
 *
 * @author M16U3L
 */
public class GroupShape implements Shape, Fillable {

  private ShapeList shapes;
  private boolean selected;

  public GroupShape(ShapeList groupedShapes) {
    this.shapes = groupedShapes;
    selected = true;
  }

  @Override
  public void setColor(Color color) {
    Iterator iter = shapes.iterator();
    while (iter.hasNext()) {
      Shape shape = (Shape) iter.next();
      shape.setColor(color);
    }
  }

  @Override
  public Color getColor() {
    return shapes.getInit(0).getColor();
  }

  @Override
  public boolean intersects(double x, double y, double width, double height) {
    boolean res = false;
    Iterator iter = shapes.iterator();
    while (iter.hasNext()) {
      Shape shape = (Shape) iter.next();
      if (shape.intersects(x, y, width, height)) {
        res = true;
        break;
      }
    }
    return res;
  }

  @Override
  public boolean isSelected(Point p) {
    boolean res = false;
    Iterator iter = shapes.iterator();
    while (iter.hasNext()) {
      Shape shape = (Shape) iter.next();
      if (shape.isSelected(p)) {
        res = true;
        break;
      }
    }
    return res;
  }

  @Override
  public void setSelected(Graphics g) {
    Iterator iter = shapes.iterator();
    while (iter.hasNext()) {
      Shape shape = (Shape) iter.next();
      shape.setSelected(g);
    }
  }

  @Override
  public void draw(Graphics g) {
    Iterator iter = shapes.iterator();
    while (iter.hasNext()) {
      Shape shape = (Shape) iter.next();
      shape.draw(g);
    }
  }

  @Override
  public void move(Point p) {
    Iterator iter = shapes.iterator();
    while (iter.hasNext()) {
      Shape shape = (Shape) iter.next();
      shape.move(p);
    }
  }

  @Override
  public void fill(Color c) {
    /*shapes.stream().filter((s) -> (s instanceof Fillable)).forEachOrdered((s) -> {
      ((Fillable) s).fill(c);
    });*/
  }

  public ShapeList getShapes() {
    return shapes;
  }

  @Override
  public void setSelect(boolean b) {
    selected = b;
  }

  @Override
  public boolean getSelect() {
    return selected;
  }

  @Override
  public void setEnds(int x, int y, int x0, int y0) {
  }
}
