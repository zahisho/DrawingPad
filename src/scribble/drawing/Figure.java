package scribble.drawing;

import java.io.Serializable;

public abstract class Figure implements Serializable, Drawable, Selectable, Movable {

  public abstract Figure getFigure();
}
