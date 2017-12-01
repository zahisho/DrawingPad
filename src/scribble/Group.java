package scribble;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

/**
 *
 * @author Isaac
 */
public class Group implements Shape, Fillable {

  private List<Shape> shapes;

  public Group(List<Shape> groupedShapes) {
    this.shapes = groupedShapes;
  }

  @Override
  public void setColor(Color color) {
    shapes.forEach((s) -> {
      s.setColor(color);
    });
  }

  @Override
  public Color getColor() {
    return shapes.get(0).getColor();
  }

  @Override
  public boolean intersects(double x, double y, double width, double height) {
    boolean res = false;
    for (Shape s : shapes) {
      if (s.intersects(x, y, width, height)) {
        res = true;
        break;
      }
    }
    return res;
  }

  @Override
  public boolean isSelected(Point p) {
    boolean res = false;
    for (Shape s : shapes) {
      if (s.isSelected(p)) {
        res = true;
        break;
      }
    }
    return res;
  }

  @Override
  public void setSelected(Graphics g) {
    shapes.forEach((s) -> {
      s.setSelected(g);
    });
  }

  @Override
  public void draw(Graphics g) {
    shapes.forEach((s) -> {
      s.draw(g);
    });
  }

  @Override
  public void move(Point p) {
    shapes.forEach((s) -> {
      s.move(p);
    });
  }

  @Override
  public void fill(Color c) {
    shapes.stream().filter((s)
            -> (s instanceof Fillable)).forEachOrdered((s) -> {
      ((Fillable) s).fill(c);
    });
  }

  public List<Shape> getShapes() {
    return shapes;
  }
}
