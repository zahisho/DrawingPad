package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public class Shape implements Serializable {

  private final Color color;
  private final Figure figure;

  public Shape(Color color, Figure figure) {
    this.color = color;
    this.figure = figure;
  }

  public final void startFigure(Point p, Graphics g) {
    figure.startFigure(p, g);
  }

  public final void updateFigure(Point p, Graphics g) {
    g.setColor(color);
    figure.updateFigure(p, g);
  }

  public final void draw(Graphics g) {
    g.setColor(color);
    figure.draw(g);
  }

  public final void setSelected(Graphics g) {
    g.setColor(Color.gray);
    figure.draw(g);
  }

  public final boolean isSelected(Point p) {
    return figure.isSelected(p);
  }
}
