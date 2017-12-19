package shape;

import java.awt.Graphics;
import java.awt.Point;

interface Drawable {

  public void startFigure(Point p, Graphics g);

  public void updateFigure(Point p, Graphics g);

  public void draw(Graphics g);  
  
}
