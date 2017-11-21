package pruebas;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author M16U3L
 */
public interface Drawable {

  public String getName();

  public void draw(Graphics g);

  public void startShape(Point p);

  public void addPointToShape(Point p);

  public void endShape(Point p);

}
