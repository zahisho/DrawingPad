package scribble.action;

import java.awt.Color;
import scribble.ScribbleCanvas;
import scribble.drawing.Shape;

public class ChangeContourColorAction extends Action {

  private final Color previousColor;
  private final Color actualColor;
  private final Shape shape;

  public ChangeContourColorAction(Shape s, Color pre, Color act) {
    shape = s;
    previousColor = pre;
    actualColor = act;
  }

  @Override
  public final Action revertAction(ScribbleCanvas canvas) {
    shape.setContourColor(previousColor);
    return new ChangeContourColorAction(shape, actualColor, previousColor);
  }
}
