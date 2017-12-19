package shape;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.awt.Shape;

public abstract class UmlElement implements Serializable, Drawable, Selectable {

  private Shape shape;
  private boolean selected;

  private final float[] DASHED_STROKE = new float[]{5, 2};

  public void basicStroke(Graphics2D g2d) {
    g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
      0, DASHED_STROKE, 0));
  }

  public Shape getShape() {
    return shape;
  }

  public void setShape(Shape shape) {
    this.shape = shape;
  }

  public boolean getSelect() {
    return selected;
  }

  public void setSelect(boolean b) {
    selected = b;
  }

  public abstract UmlElement getFigure();
}
