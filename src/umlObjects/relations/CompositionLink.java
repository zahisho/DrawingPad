package umlObjects.relations;

import umlObjects.UmlRelationship;
import shape.UmlElement;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import umlObjects.UmlObject;

public class CompositionLink extends UmlRelationship {

  private final double DISCOUNT = 0.5;

  @Override
  public final void draw(Graphics g) {
    double k = 15;
    int j = 1;
    Graphics2D graph = (Graphics2D) g;
    double alfa = Math.atan2(y2 - y1, x2 - x1);

    graph.drawLine(x1, y1, x2, y2);
    graph.setStroke(new BasicStroke(1));

    while (k > 0) {
      int xa1 = (int) (x2 - k * Math.cos(alfa + 1));
      int ya1 = (int) (y2 - k * Math.sin(alfa + 1));
      g.drawLine(xa1, ya1, x2, y2);
      int xa2 = (int) (x2 - k * Math.cos(alfa - 1));
      int ya2 = (int) (y2 - k * Math.sin(alfa - 1));
      g.drawLine(xa2, ya2, x2, y2);
      int xa3 = (int) (x2 - k * Math.cos(alfa));
      int ya3 = (int) (y2 - k * Math.sin(alfa));
      g.drawLine(xa1, ya1, xa3, ya3);

      g.drawLine(xa2, ya2, xa3, ya3);

      k = k - DISCOUNT;
    }
  }

  @Override
  public final UmlElement getFigure() {
    return new CompositionLink();
  }

  @Override
  public boolean validAbstractions(UmlObject i, UmlObject e) {
    return false;
  }

  @Override
  public void update() {
  }
}
