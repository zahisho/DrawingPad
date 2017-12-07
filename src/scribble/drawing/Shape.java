package scribble.drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shape implements Serializable {

  private final List<Figure> figures;

  public Shape() {
    figures = new ArrayList<>();
  }

  public Shape(Figure figure) {
    figures = new ArrayList<>();
    if (figure instanceof Fillable) {
      ((Fillable) figure).setFillingColor(null);
    }
    figures.add(figure);
  }

  public final void addFigure(Figure f) {
    figures.add(f);
  }

  public final List<Figure> getFigures() {
    return figures;
  }

  public final void startFigure(Point p, Graphics g) {
    figures.forEach((figure) -> {
      figure.startFigure(p, g);
    });
  }

  public final void updateFigure(Point p, Graphics g) {
    figures.forEach((figure) -> {
      figure.updateFigure(p, g);
    });
  }

  public final void draw(Graphics g) {
    figures.forEach((figure) -> {
      figure.draw(g);
    });
  }

  public final boolean isSelected(Point p) {
    boolean selected = false;

    for (Figure figure : figures) {
      if (figure.isSelected(p)) {
        selected = true;
        break;
      }
    }
    return selected;
  }

  public final void move(Point p) {
    figures.forEach(figure -> {
      figure.move(p);
    });
  }

  public final void setContourColor(Color c) {
    figures.forEach(figure -> {
      figure.setContour(c);
    });
  }

  public final void setFillingColor(Color c) {
    figures.forEach(figure -> {
      if (figure instanceof Fillable) {
        ((Fillable) figure).setFillingColor(c);
      }
    });
  }

  public final Shape copy() {
    Shape nShape = new Shape();
    figures.forEach((f) -> {
      nShape.addFigure(f.copy());
    });
    return nShape;
  }
}
