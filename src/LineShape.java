
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;

public class LineShape extends TwoEndsShape {

  public LineShape(Color color) {
    super(color);
  }

  @Override
  public final void draw(final Graphics g) {
    Graphics2D g2g = (Graphics2D) g;
    Stroke previous = g2g.getStroke();
    shape = new Line2D.Double(x1, y1, x2, y2);
    if (selected) {
      setSelected((g));
    }
    if (color != null) {
      g.setColor(color);
    }
    g.drawLine(x1, y1, x2, y2);
    g2g.setStroke(previous);
    g2g.draw(shape);
  }
}
