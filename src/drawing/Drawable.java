package drawing;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public interface Drawable extends Serializable {

  public void startFigure(Point p, Graphics g);

  public void updateFigure(Point p, Graphics g);

  public void draw(Graphics g);
}
