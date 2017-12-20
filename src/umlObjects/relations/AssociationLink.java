package umlObjects.relations;

import umlObjects.UmlRelationship;
import shape.UmlElement;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class AssociationLink extends UmlRelationship {

  @Override
  public final void draw(Graphics g) {
    Graphics2D graph = (Graphics2D) g;
    double alfa = Math.atan2(y2 - y1, x2 - x1);
    graph.setStroke(new BasicStroke(1));
    graph.drawLine(x1, y1, x2, y2);

    int xa = (int) (x2 - TAMROW * Math.cos(alfa + 1));
    int ya = (int) (y2 - TAMROW * Math.sin(alfa + 1));
    g.drawLine(xa, ya, x2, y2);
    xa = (int) (x2 - TAMROW * Math.cos(alfa - 1));
    ya = (int) (y2 - TAMROW * Math.sin(alfa - 1));
    g.drawLine(xa, ya, x2, y2);
  }

  @Override
  public final UmlElement getFigure() {
    return new AssociationLink();
  }

  @Override
  public void updateLink(Point p, Graphics g) {
  }
}
