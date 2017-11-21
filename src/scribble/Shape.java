package scribble;

import java.awt.Color;
import java.io.Serializable;

public abstract class Shape implements Serializable, Cloneable, Drawable {

  public Color color = Color.black;

  public Shape(Color color) {
    this.color = color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  protected int x1;
  protected int y1;
  protected int x2;
  protected int y2;

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  public void setEnds(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }
}
