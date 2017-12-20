package umlObjects.relations;

import shape.UmlElement;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import umlObjects.UmlRelationship;

public class DirectAssociationLink extends UmlRelationship {

  @Override
  public final void draw(Graphics g) {
    Graphics2D graph = (Graphics2D) g;
    graph.setStroke(new BasicStroke(1));
    graph.drawLine(x1, y1, x2, y2);
  }

  @Override
  public final UmlElement getFigure() {
    return new DirectAssociationLink();
  }

  @Override
  public void updateLink(Point p, Graphics g) {
  }
}
