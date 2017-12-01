package scribble;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Isaac
 */
public interface Selectable {

  public boolean isSelected(Point p);

  public void setSelected(Graphics g);
}
