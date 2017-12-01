package scribble;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public interface Shape extends Serializable, Selectable, Drawable, Movable {

  public void setColor(final Color color);

  public Color getColor();

  public boolean intersects(double x, double y, double width, double height);

}
