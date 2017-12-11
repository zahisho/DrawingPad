package scribble.drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shape implements Serializable {

  private final List<ShapeElement> elements;

  public Shape() {
    elements = new ArrayList<>();
  }

  public Shape(ShapeElement sElement) {
    elements = new ArrayList<>();
    elements.add(sElement);
  }

  public final void addShapeElement(ShapeElement sElement) {
    elements.add(sElement);
  }

  public final List<ShapeElement> getElements() {
    return elements;
  }

  public final void draw(Graphics g) {
    elements.forEach((element) -> {
      element.draw(g);
    });
  }

  public final boolean isSelected(Point p) {
    boolean selected = false;

    for (ShapeElement element : elements) {
      if (element.isSelected(p)) {
        selected = true;
        break;
      }
    }
    return selected;
  }

  public final void move(Point p) {
    elements.forEach(figure -> {
      figure.move(p);
    });
  }

  public final void setContourColor(Color c) {
    elements.forEach(figure -> {
      figure.setContour(c);
    });
  }

  public final void setFillingColor(Color c) {
    elements.forEach(figure -> {
      if (figure instanceof Fillable) {
        ((Fillable) figure).setFillingColor(c);
      }
    });
  }

  public final void setThickness(float t) {
    elements.forEach(element -> {
      if (element instanceof Figure) {
        ((Figure) element).setThickness(t);
      }
    });
  }

  public final void setLineStyle(float[] d) {
    elements.forEach(element -> {
      if (element instanceof Figure) {
        ((Figure) element).setLineStyle(d);
      }
    });
  }

  public final Shape copy() {
    Shape nShape = new Shape();
    elements.forEach((f) -> {
      nShape.addShapeElement(f.copy());
    });
    return nShape;
  }
}
