package shape;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author M16U3L
 */
public interface Selectable {

  public boolean isSelected(Point p);

  public void setSelected(Graphics g);
}
