package drawer;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Serializable {

  protected Color color = Color.black;
  
  public Shape() {
  }

  public Shape(Color color) {
    this.color = color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  public abstract void draw(Graphics g);


}
