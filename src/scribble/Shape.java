package scribble;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

public abstract class Shape implements Serializable, Drawable {

  public Color color = Color.black;

  int x1;
  int y1;
  int x2;
  int y2;

  public Shape(Color color) {
    this.color = color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public void setEnds(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  public abstract boolean isPoint(Point p);
}
