package scribble.drawing;

import java.awt.Graphics;
import java.awt.Point;

public abstract class Figure extends ShapeElement {

  protected float thickness;
  protected float[] lineStyle;

  public final void setThickness(float t) {
    thickness = t;
  }

  public final void setLineStyle(float[] f) {
    lineStyle = f;
  }

  public abstract Figure getFigure();

  public abstract void startFigure(Point p, Graphics g);

  public abstract void updateFigure(Point p, Graphics g);
}
