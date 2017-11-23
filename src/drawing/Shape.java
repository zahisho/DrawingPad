package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public class Shape implements Serializable {

  private final Color color;
  private final Selectable figure;

  public Shape(Color color, Selectable figure) {
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
}
