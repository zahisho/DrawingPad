
import java.awt.Color;
import java.awt.Graphics;
import scribble.Shape;

/**
 *
 * @author M16U3L
 */
public class ScribblePoint extends Shape {

  public ScribblePoint(Color color) {
    super(color);
  }

  @Override
  public void draw(Graphics g) {
    if (color != null) {
      g.setColor(color);
    }
    g.drawLine(x1, y1, x2, y2);
  }

}
