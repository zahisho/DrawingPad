package drawing;

import java.io.Serializable;

public abstract class Figure implements Serializable, Drawable, Selectable {

  public abstract Figure getFigure();
}
