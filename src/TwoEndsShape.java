
import java.awt.Color;
import java.awt.Graphics;

public abstract class TwoEndsShape extends scribble.Shape implements Cloneable {
  protected int x1;
  protected int y1;
  protected int x2;
  protected int y2;

  public TwoEndsShape() {}

  public TwoEndsShape(Color color) {
    super(color);
  }

  @Override
  public final Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  public final void setEnds(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  abstract public void drawOutline(Graphics g, int x1, int y1, int x2, int y2);
}
