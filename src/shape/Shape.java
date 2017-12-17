package shape;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author M16U3L
 */
public interface Shape extends Serializable, Selectable, Movable, Drawable {

  public void setColor(Color color);

  public Color getColor();

  public boolean intersects(double x, double y, double width, double height);

  public void setSelect(boolean b);

  public boolean getSelect();

  public void setEnds(int x, int y, int x0, int y0);

}
