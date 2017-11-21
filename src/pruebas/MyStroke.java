package pruebas;

import java.awt.Graphics;
import java.awt.Point;
import scribble.ScribbleCanvas;

/**
 *
 * @author M16U3L
 */
public class MyStroke extends MyShape {

  public MyStroke(ScribbleCanvas canvas, String name) {
    super(canvas, name);
  }

  @Override
  public void startShape(Point p) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void addPointToShape(Point p) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void endShape(Point p) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void draw(Graphics g) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
