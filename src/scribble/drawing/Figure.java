package scribble.drawing;

import java.awt.Color;
import java.io.Serializable;

public abstract class Figure implements Serializable, Drawable, Selectable, Movable {

  protected Color contourColor;

  public final void setContour(Color c) {
    contourColor = c;
  }

  public abstract Figure getFigure();

  public abstract Figure copy();
}
