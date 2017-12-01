package scribble.drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.io.Serializable;

public class Shape implements Serializable {

  private Color fillingColor;
  private Color contourColor;
  private final Figure figure;

  private final float[] DASHED_STROKE = new float[]{5, 2};

  public Shape(Color color, Figure figure) {
    contourColor = color;
    fillingColor = color;
    this.figure = figure;
  }

  public final void startFigure(Point p, Graphics g) {
    figure.startFigure(p, g);
  }

  public final void updateFigure(Point p, Graphics g) {
    g.setColor(contourColor);
    figure.updateFigure(p, g);
  }

  public final void draw(Graphics g) {
    if (figure instanceof Fillable) {
      g.setColor(fillingColor);
      ((Fillable) figure).fillInside(g);
    }
    g.setColor(contourColor);
    figure.draw(g);
  }

  public final void setSelected(Graphics g) {
    Graphics2D graph = (Graphics2D) g;
    Stroke previous = graph.getStroke();
    graph.setColor(Color.gray);
    graph.setStroke(new BasicStroke(2,
            BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
            0, DASHED_STROKE, 0));
    figure.draw(graph);
    if (figure instanceof Fillable) {
      ((Fillable) figure).fillInside(g);
    }
    graph.setStroke(previous);
  }

  public final boolean isSelected(Point p) {
    return figure.isSelected(p);
  }

  public final void setFilled(boolean f) {
    if (figure instanceof Fillable) {
      ((Fillable) figure).setFilled(f);
    }
  }

  public final void move(Point p) {
    figure.move(p);
  }

  public final void setContourColor(Color c) {
    contourColor = c;
  }

  public final void setFillingColor(Color c) {
    fillingColor = c;
  }
}
