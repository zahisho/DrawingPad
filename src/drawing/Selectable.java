package drawing;

import java.awt.Point;

public interface Selectable extends Drawable {

  public boolean isSelected(Point p);

}
