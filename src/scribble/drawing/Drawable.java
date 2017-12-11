package scribble.drawing;

import java.awt.Color;
import java.awt.Graphics;

interface Drawable {

  public void draw(Graphics g);

  public void setContour(Color c);
}
