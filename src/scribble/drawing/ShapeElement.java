package scribble.drawing;

import java.awt.Color;
import java.io.Serializable;

public abstract class ShapeElement implements Serializable, Drawable, Selectable, Movable {

  protected Color contourColor;

  public abstract ShapeElement copy();

  @Override
  public final void setContour(Color c) {
    contourColor = c;
  }
}
