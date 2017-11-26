package drawing;

import java.awt.Graphics;
import java.awt.Point;

public interface Drawable {

  public void startFigure(Point p, Graphics g);

  public void updateFigure(Point p, Graphics g);

  public void draw(Graphics g);
}
