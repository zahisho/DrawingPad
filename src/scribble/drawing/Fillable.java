package scribble.drawing;

import java.awt.Graphics;

interface Fillable {

  public void setFilled(boolean f);

  public void fillInside(Graphics g);
}
