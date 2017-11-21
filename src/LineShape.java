import java.awt.Graphics;

public class LineShape extends TwoEndsShape {

  @Override
  public final void draw(Graphics g) {
    if (color != null) {
      g.setColor(color);
    }
    g.drawLine(x1, y1, x2, y2);
  }

  @Override
  public final void drawOutline(Graphics g, int x1, int y1, int x2, int y2) {
    g.drawLine(x1, y1, x2, y2);
  }
}
