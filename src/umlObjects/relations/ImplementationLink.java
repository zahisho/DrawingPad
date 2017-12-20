package umlObjects.relations;

import umlObjects.UmlRelationship;
import shape.UmlElement;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class ImplementationLink extends UmlRelationship {

  public float[] DASHED = new float[]{5, 2};

  @Override
  public final void draw(Graphics g) {
    Graphics2D graph = (Graphics2D) g;
    basicStroke(graph);

    double alfa = Math.atan2(y2 - y1, x2 - x1);

    graph.drawLine(x1, y1, x2, y2);
    graph.setStroke(new BasicStroke(1));
    int xa1 = (int) (x2 - TAMROW * Math.cos(alfa + 1));
    int ya1 = (int) (y2 - TAMROW * Math.sin(alfa + 1));
    g.drawLine(xa1, ya1, x2, y2);
    int xa2 = (int) (x2 - TAMROW * Math.cos(alfa - 1));
    int ya2 = (int) (y2 - TAMROW * Math.sin(alfa - 1));
    g.drawLine(xa2, ya2, x2, y2);
    g.drawLine(xa1, ya1, xa2, ya2);
  }

  @Override
  public final UmlElement getFigure() {
    return new ImplementationLink();
  }

  @Override
  public void updateLink(Point p, Graphics g) {
    setEndPoint(p);
    overDraw(g);
  }
}
