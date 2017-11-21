package scribble;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Serializable {

  protected Color color = Color.black;

  public Shape() {}

  public Shape(Color color) {
    this.color = color;
  }

  public final void setColor(Color color) {
    this.color = color;
  }

  public final Color getColor() {
    return color;
  }

  public abstract void draw(Graphics g);
}
