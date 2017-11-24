import java.awt.Graphics;
import java.awt.Point;

public class LineShape extends TwoEndsShape {

  public void draw(Graphics g) {
    if (color != null) {
      g.setColor(color);
    }
    g.drawLine(x1, y1, x2, y2);
  }

  @Override
  public boolean belong(Point p) {
    int x = p.x;
    int y = p.y;
    float m = (y2 - y1) / (x2 - x1);
    float m2 = (y - y1) / (x - x1);
    if (m == m2) {
      System.out.println("click in Line");
      return true;
    }
    return false;
  }
  
}
