package pruebas;

import java.awt.Color;
import java.awt.Graphics;
import scribble.ScribbleCanvas;

/**
 *
 * @author M16U3L
 */
public abstract class MyShape implements Drawable, Cloneable {

  public ScribbleCanvas canvas;
  public String name;

  public Color color;
  public int x1;
  public int y1;
  public int x2;
  public int y2;
  int xStart, yStart;

  public MyShape(Color color) {
    this.color = color;
  }

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

  @Override
  public String getName() {
    return name;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public MyShape(ScribbleCanvas canvas, String name) {
    this.canvas = canvas;
    this.name = name;
  }

  public static void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
    g.drawLine(x1, y1, x2, y2);
  }
}
